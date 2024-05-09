@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.steppi.steppifitness.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import android.provider.Settings
import android.text.Html
import android.text.format.DateFormat
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.fitness.Fitness
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.cache.STPCache
import com.steppi.steppifitness.model.STErrorData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object STUtils {
    private var lastClickTime: Long = 0

    fun getDeviceHeight(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun getDeviceWidth(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun setValueToView(view: View?, text: String?) {
        var textString = text
        if (view == null) {
            return
        }
        if (textString == null) {
            return
        }
        if (textString.contains("null")) {
            textString = textString.replace("null", "")
        }
        if (view is EditText) {
            view.setText(textString)
        } else if (view is TextView) {
            view.text = textString
        }
    }

    fun getValueFromView(view: View): String? {
        if (view is EditText) {
            return if (view.text.isNotEmpty()) view.text.toString() else null
        } else if (view is TextView) {
            return if (view.text.isNotEmpty()) view.text.toString() else null
        }
        return null
    }

    fun isInternetOn(context: Context?): Boolean {
        if (null == context) {
            return true
        }
        val connection =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                ?: return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connection.run {
                connection.getNetworkCapabilities(connection.activeNetwork)?.run {
                    return when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            connection.activeNetworkInfo?.let {
                if (it.state == android.net.NetworkInfo.State.CONNECTED ||
                    it.state == android.net.NetworkInfo.State.CONNECTING ||
                    it.state == android.net.NetworkInfo.State.CONNECTING ||
                    it.state == android.net.NetworkInfo.State.CONNECTED
                ) {
                    return true
                } else if (
                    it.state == android.net.NetworkInfo.State.DISCONNECTED ||
                    it.state == android.net.NetworkInfo.State.DISCONNECTED
                ) {
                    return false
                }
            } ?: kotlin.run {
                return false
            }
        }
        return false
    }

    fun setAppLanguage(_context: Context?, language: String?): Context? {
        var context = _context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context?.resources
        val config = Configuration(res?.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
            res?.updateConfiguration(config, res.displayMetrics)
            context = context?.createConfigurationContext(config)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale)
            } else {
                setSystemLocaleLegacy(config, locale);
            }
            res?.updateConfiguration(config, res.displayMetrics)
        }
        return context
    }

    @SuppressWarnings("deprecation")
    fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
        config.locale = locale
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun setSystemLocale(config: Configuration, locale: Locale) {
        config.setLocale(locale);
    }

    fun getRealPathFromURI(context: Context, contentURI: Uri): String {
        val result: String
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = context.contentResolver.query(
            contentURI, projection,
            null, null, null
        )
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.path.toString()
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    //            getBitmapFromUrl(it.picture) { bitmap ->
//                val uri = STUtils.bitmapToFile(activityContext, bitmap)
//                imageFile = File(
//                    STUtils.getRealPathFromURI(
//                        activityContext, uri
//                    )
//                )
//            }
    // Method to save an bitmap to a file
    fun bitmapToFile(context: Context, bitmap: Bitmap): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(context.applicationContext)
        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")
        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }

    fun shareIntent(context: Context, message: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message)
        // Verify the intent will resolve to at least one activity
        if (sharingIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(sharingIntent, "Share..."))
        }
//        activity.startActivity(Intent.createChooser(sharingIntent, activity.resources.getString(R.string.share_using)))
    }

    fun getVerticalLayoutManager(context: Context): LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        return linearLayoutManager
    }

    fun getHorizontalLayoutManager(context: Context): LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        return linearLayoutManager
    }

    fun getGridLayoutManager(context: Context, columnCount: Int = 2): LinearLayoutManager {
        return GridLayoutManager(context, columnCount)
    }

    fun hideSoftKeyboard(context: Context, view: View) {
        val inputMethodManager =
            (context as Activity).getSystemService(Activity.INPUT_METHOD_SERVICE)
                    as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showSoftKeyboard(context: Context) {
        val inputMethodManager =
            (context as Activity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun closeKeyboard(context: Context) {
        val inputMethodManager =
            (context as Activity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun prepareFilePart(partName: String, imageFile: File): MultipartBody.Part {
        val requestFile = RequestBody.create(
            MediaType.parse("image/*"),
            imageFile
        )
        return MultipartBody.Part.createFormData(partName, imageFile.name, requestFile)
    }//

    fun prepareFileArrayPart(
        partName: String,
        imageFile: List<File>
    ): MutableList<MultipartBody.Part> {
        var body: MutableList<MultipartBody.Part>? = mutableListOf()
        for (file in imageFile) {
            val requestFile = RequestBody.create(
                MediaType.parse("image/*"),
                file
            )
            body?.add(MultipartBody.Part.createFormData(partName, file.name, requestFile))
        }
        return body!!
    }

    fun prepareVideoArrayPart(
        partName: String,
        imageFile: List<File>
    ): MutableList<MultipartBody.Part> {
        var body: MutableList<MultipartBody.Part>? = mutableListOf()
        for (file in imageFile) {
            val requestFile = RequestBody.create(
                MediaType.parse("video/*"),
                file
            )
            body?.add(MultipartBody.Part.createFormData(partName, file.name, requestFile))
        }
        return body!!
    }

    fun emailValidator(email: String): Boolean {
//        val pattern: Pattern
//        val matcher: Matcher
//        val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
//        pattern = Pattern.compile(EMAIL_PATTERN)
//        matcher = pattern.matcher(email)
//        return matcher.matches()
        return isValidEmail(email)
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun getDate(date: String?, originalFormat: String, targetFormat: String): String {
        var formattedDate = ""
        date?.let {
            if (it.isNotEmpty()) {
                val originalDateFormat = SimpleDateFormat(originalFormat, Locale.getDefault())
                val targetDateFormat = SimpleDateFormat(targetFormat, Locale.getDefault())
                try {
                    formattedDate = targetDateFormat.format(
                        originalDateFormat.parse(
                            it.replace(
                                "T",
                                " "
                            ).replace("Z", "")
                        )
                    ).replaceArabicNumbers()
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
        }
        return formattedDate
    }

    fun String.replaceArabicNumbers(): String {
        val newValue =
            this.replace("١", "1")
                .replace("٢", "2")
                .replace("٣", "3")
                .replace("٤", "4")
                .replace("٥", "5")
                .replace("٦", "6")
                .replace("۷", "7")
                .replace("٧", "7")
                .replace("۸", "8")
                .replace("٨", "8")
                .replace("٩", "9")
                .replace("٠", "0")

                .replace("١", "1")
                .replace("٢", "2")
                .replace("٣", "3")
                .replace("٤", "4")
                .replace("٥", "5")
                .replace("٦", "6")
                .replace("٧", "7")
                .replace("٨", "8")
                .replace("٩", "9")
                .replace("٠", "0")
        return newValue
    }

    fun getDateEn(date: String?, originalFormat: String, targetFormat: String): String {
        var formattedDate = ""
        date?.let {
            if (it.isNotEmpty()) {
                val originalDateFormat = SimpleDateFormat(originalFormat)
                val targetDateFormat = SimpleDateFormat(targetFormat, Locale.US)
                try {
                    formattedDate = targetDateFormat.format(
                        originalDateFormat.parse(
                            it.replace(
                                "T",
                                " "
                            ).replace("Z", "")
                        )
                    ).replaceArabicNumbers()
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
        }
        return formattedDate
    }

    fun getDatesInBetween(dateString1: String, dateString2: String): ArrayList<String>? {
        val dates = ArrayList<String>()
        val df1 = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = df1.parse(dateString1)
            date2 = df1.parse(dateString2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val cal2 = Calendar.getInstance()
        cal2.time = date2
        while (!cal1.after(cal2)) {
            dates.add(getFormattedDate(cal1, "yyyy-MM-dd"))
//            dates.add(cal1.time)
            cal1.add(Calendar.DATE, 1)
        }
        return dates
    }

    fun getFormattedDateEn(date: Calendar, targetFormat: String): String {
        var formattedDate = ""
        val targetDateFormat = SimpleDateFormat(targetFormat, Locale.ENGLISH)
        try {
            formattedDate = targetDateFormat.format(
                date.time
            )
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun getFormattedDate(date: Calendar, targetFormat: String): String {
        var formattedDate = ""
        val targetDateFormat = SimpleDateFormat(targetFormat, Locale.getDefault())
        try {
            formattedDate = targetDateFormat.format(
                date.time
            ).replaceArabicNumbers()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun formatServerDate(date: String?): String {
        date?.let {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            parser.timeZone = TimeZone.getTimeZone("UTC")
            val formatter = SimpleDateFormat("dd-MMM-yyyy")
            formatter.timeZone = (TimeZone.getDefault())
            return formatter.format(parser.parse(date)).replaceArabicNumbers()
        }
        return date!!.replaceArabicNumbers()
    }

    fun setPreviousDate(date: String?): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        val dateValue = dateFormat.parse(date)
        val oneDayBefore = Date(dateValue.time - 2)
        return dateFormat?.format(oneDayBefore)
    }

    fun getDate(date: String?): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        val dateValue = dateFormat.parse(date)
        val dateValueReturn = Date(dateValue.time)
        return dateValue
    }

    fun getSyncedDateFromString(syncedDate: String): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.parse(syncedDate)
    }

    fun getSyncedDateFromStringMethodSeconds(syncedDate: String): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val strValue = syncedDate
        val dateOnlyString = syncedDate.substring(0, 10) // dateFormat.format(syncedDate)
//        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.parse(dateOnlyString)
    }

    fun getMyPrettyDate(context: Context, neededTimeMillis: Long): String {
        val nowTime = Calendar.getInstance()
        val neededTime = Calendar.getInstance()
        neededTime.timeInMillis = neededTimeMillis
        if ((neededTime.get(Calendar.YEAR) == nowTime.get(Calendar.YEAR))) {
            return if ((neededTime.get(Calendar.MONTH) == nowTime.get(Calendar.MONTH))) {
                when {
                    nowTime.get(Calendar.DATE) == neededTime.get(Calendar.DATE) -> {
                        //here return like "Today at 12:00"
                        //                        "" + DateFormat.format("hh:mm a", neededTime)
                        if (nowTime.timeInMillis - neededTime.timeInMillis <= 1000) {
                            context.getString(R.string.just_now)
                        } else {
                            /*DateUtils.getRelativeTimeSpanString(
                                neededTime.timeInMillis,
                                nowTime.timeInMillis, DateUtils.MINUTE_IN_MILLIS
                            ).toString()*/
                            ("" + DateFormat.format("hh:mm a", neededTime)).replaceArabicNumbers()
                        }
                    }
                    nowTime.get(Calendar.DATE) - neededTime.get(Calendar.DATE) == 1 ->
                        //here return like "Yesterday at 12:00"
                        context.getString(R.string.text_yesterday)// + DateFormat.format("hh:mm a", neededTime)
                    nowTime.get(Calendar.DATE) - neededTime.get(Calendar.DATE) in 2..50 ->
                        /*"" + (nowTime.get(Calendar.DATE) - neededTime.get(Calendar.DATE)) + " days ago"*/
                        DateFormat.format("dd-MMM-yyyy", neededTime).toString()
                            .replaceArabicNumbers()
                    neededTime.get(Calendar.DATE) - nowTime.get(Calendar.DATE) == 1 ->
                        //here return like "Tomorrow at 12:00"
                        context.getString(R.string.tomorrow_at) + DateFormat.format(
                            "hh:mm a",
                            neededTime
                        )
                    else ->
                        //here return like "May 31, 12:00"
                        DateFormat.format("dd-MMM-yyyy", neededTime).toString()
                            .replaceArabicNumbers()
                }
            } else {
                //here return like "May 31, 12:00"
                DateFormat.format("dd-MMM-yyyy", neededTime).toString().replaceArabicNumbers()
            }
        } else {
            //here return like "May 31 2010, 12:00" - it's a different year we need to show it
            return DateFormat.format("dd-MMM-yyyy", neededTime).toString().replaceArabicNumbers()
        }
    }

    fun compareDate(startDate: String, currentDate: String): String? {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val startDateValue = sdf.parse(startDate) //"2018-08-30"
            val currentDateValue = sdf.parse(currentDate) //"2018-08-28"
            when {
                startDateValue > currentDateValue -> {
                    Log.d("app", "startDateValue is after currentDateValue")
                    return STConstants.CHALLENGE_NOT_STARTED
                }
                startDateValue < currentDateValue -> {
                    Log.d("app", "startDateValue is before currentDateValue")
                    return STConstants.CHALLENGE_STARTED
                }
                startDateValue.compareTo(currentDateValue) == 0 -> {
                    Log.d("app", "startDateValue is equal to currentDateValue")
                    return STConstants.CHALLENGE_STARTED
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun compareToughMudderChallengeDate(startDate: String, currentDate: String): String? {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val startDateValue = sdf.parse(startDate) //"2018-08-30"
            val currentDateValue = sdf.parse(currentDate) //"2018-08-28"
            when {
                startDateValue > currentDateValue -> {
                    Log.d("app", "startDateValue is after currentDateValue")
                    return STConstants.CHALLENGE_NOT_STARTED
                }
                startDateValue < currentDateValue -> {
                    Log.d("app", "startDateValue is before currentDateValue")
                    return STConstants.CHALLENGE_STARTED
                }
                startDateValue.compareTo(currentDateValue) == 0 -> {
                    Log.d("app", "startDateValue is equal to currentDateValue")
                    return STConstants.CHALLENGE_TODAY
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun compareEndDate(endDate: String, currentDate: String): String? {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val endDateValue = sdf.parse(endDate) //"2018-08-30"
            val currentDateValue = sdf.parse(currentDate) //"2018-08-28"
            when {
                endDateValue > currentDateValue -> {
                    Log.d("app", "endDateValue is after currentDateValue")
                    return STConstants.CHALLENGE_NOT_ENDED
                }
                endDateValue < currentDateValue -> {
                    Log.d("app", "endDateValue is before currentDateValue")
                    return STConstants.CHALLENGE_COMPLETED
                }
                endDateValue.compareTo(currentDateValue) == 0 -> {
                    Log.d("app", "endDateValue is equal to currentDateValue")
                    return STConstants.CHALLENGE_NOT_ENDED
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun convertToUtc(date: String?, isDate: Boolean): String? {
        Log.d("date", date!!)
        var orginal = SimpleDateFormat("yyyy-MM-dd")
        var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        if (!isDate) {   // is time

            orginal = SimpleDateFormat("HH:mm")
            dateFormat = SimpleDateFormat("HH:mm", Locale.US)
        }

        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.format(orginal.parse(date))
    }

    fun convertAM_PM(date: String?): String? {
        var localDate = date
        if (date?.contains("p.m.", true) == true) {
            localDate = date.replace("p.m.", "PM", true)
        } else if (date?.contains("a.m.", true) == true) {
            localDate = date.replace("a.m.", "AM", true)
        }
        return localDate
    }

    fun convertToLocal(date: String?, isDate: Boolean): String? {

        Log.d("date", date!!)
        try {
            var orginal = SimpleDateFormat("yyyy-MM-dd")
            var dateFormat = SimpleDateFormat("yyyy-MM-dd")
            if (!isDate) {   // is time

                orginal = SimpleDateFormat("HH:mm")
                dateFormat = SimpleDateFormat("hh:mm a")
                orginal.timeZone = TimeZone.getTimeZone("UTC")
            }
            dateFormat.timeZone = (TimeZone.getDefault())
            return dateFormat.format(orginal.parse(date))
        } catch (e: Exception) {
            e.printStackTrace()
            return date
        }
    }

    fun isValidUserName(userName: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val USER_NAME_PATTERN = "^[a-z0-9_-]{4,15}$"
        pattern = Pattern.compile(USER_NAME_PATTERN)
        matcher = pattern.matcher(userName)
        return matcher.matches()
    }

    fun getColor(context: Context, id: Int): Int {
        val version = Build.VERSION.SDK_INT
        return if (version >= 23) {
            ContextCompat.getColor(context, id)
        } else {
            context.resources.getColor(id)
        }
    }

    fun getColor(color: String?): Int? {
        return try {
            Color.parseColor(color)
        } catch (e: Exception) {
            null
        }
    }

    fun getDrawable(context: Context, id: Int): Drawable? {
        val version = Build.VERSION.SDK_INT
        return if (version >= 23) {
            ContextCompat.getDrawable(context, id)
        } else {
            context.resources.getDrawable(id)
        }
    }

    fun getDimen(context: Context, id: Int): Int {
        return context.resources.getDimension(id).toInt()
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun showProgressDialog(context: Context): Dialog {
        //=====================================================
        // loader was not visible if the background colour is same as loader colour.
        // Added dark overlay for dialog for solving this issue
        val theme = android.R.style.Theme_Translucent_NoTitleBar

        //================================================
        val dialog = Dialog(
            context,
            theme
        )
        dialog.setContentView(R.layout.custom_progress)
        dialog.setCancelable(false)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            dialog.window?.statusBarColor = ContextCompat.getColor(context, R.color.progress_dialog_status)
//        }

        dialog.show()
        return dialog
    }

    fun getProgressDialog(context: Context): Dialog {
        //=====================================================
        // loader was not visible if the background colour is same as loader colour.
        // Added dark overlay for dialog for solving this issue
        val theme = android.R.style.Theme_Translucent_NoTitleBar
        //================================================
        val dialog = Dialog(
            context,
            theme
        )
        dialog.setContentView(R.layout.custom_progress)
        dialog.setCancelable(false)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            dialog.window?.statusBarColor = ContextCompat.getColor(context, R.color.progress_dialog_status)
//        }
        return dialog
    }

    fun getErrorMessage(application: Application, error: Throwable): STErrorData? {
        var errorData: STErrorData? = STErrorData()
        if (isInternetOn(application)) {
            if (error is HttpException) {
                val moshi = Moshi.Builder().build()
                val type = Types.newParameterizedType(STErrorData::class.java)
                val adapter = moshi.adapter<STErrorData>(type)
                error.response()?.errorBody()?.string()?.let {
                    try {
                        errorData = adapter.fromJson(it)
                    } catch (e: java.lang.Exception) {
                        errorData?.message = "Something went wrong"
                    }
                }
                errorData?.errorCode = error.code()
            }
        }
        return errorData
    }

    fun prepareFilePart(
        imageFile: File
    ): MultipartBody.Part {
        val requestFile = RequestBody.create(
            MediaType.parse("image/*"),
            imageFile
        )
        return MultipartBody.Part.createFormData("picture", imageFile.name, requestFile)
    }

    fun setBoldFont(context: Context, textView: TextView) {
        textView.textDirection = View.TEXT_DIRECTION_LOCALE
        synchronized(STPCache.sharedGCache.getFontCache()) {
            if (!STPCache.sharedGCache.getFontCache()
                    .containsKey("GothamRounded-Bold")
            ) {
                val tf = Typeface.createFromAsset(
                    context.assets,
                    "fonts/GothamRounded-Bold.otf"
                )
                STPCache.sharedGCache.setFontCache("GothamRounded-Bold", tf)
            }
            textView.setTypeface(
                STPCache.sharedGCache.getFontCache()["GothamRounded-Bold"],
                Typeface.NORMAL
            )
            textView.includeFontPadding = false
        }
    }

    fun setLightFont(context: Context, textView: TextView) {
        textView.textDirection = View.TEXT_DIRECTION_LOCALE
        synchronized(STPCache.sharedGCache.getFontCache()) {
            if (!STPCache.sharedGCache.getFontCache()
                    .containsKey("GothamRounded-Light")
            ) {
                val tf = Typeface.createFromAsset(
                    context.assets,
                    "fonts/GothamRounded-Light.otf"
                )
                STPCache.sharedGCache.setFontCache("GothamRounded-Light", tf)
            }

            textView.setTypeface(
                STPCache.sharedGCache.getFontCache()["GothamRounded-Light"],
                Typeface.NORMAL
            )
            textView.includeFontPadding = false
        }
    }

    fun setBookFont(context: Context, textView: TextView) {
        textView.textDirection = View.TEXT_DIRECTION_LOCALE
        synchronized(STPCache.sharedGCache.getFontCache()) {
            if (!STPCache.sharedGCache.getFontCache()
                    .containsKey("GothamRounded-Book")
            ) {
                val tf = Typeface.createFromAsset(
                    context.assets,
                    "fonts/GothamRounded-Book.otf"
                )
                STPCache.sharedGCache.setFontCache("GothamRounded-Book", tf)
            }
            textView.setTypeface(
                STPCache.sharedGCache.getFontCache()["GothamRounded-Book"],
                Typeface.NORMAL
            )
            textView.includeFontPadding = false
        }
    }

    fun shareApp(activityContext: Context, shareUrl: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            activityContext.getString(R.string.app_share_text)
                .replace("$$$$", shareUrl)
        )
        sendIntent.type = "text/plain"
        activityContext.startActivity(sendIntent)
    }

    private fun shareReward(activityContext: Context, url: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey check out my reward at: $url"
        )
        sendIntent.type = "text/plain"
        activityContext.startActivity(sendIntent)
    }

    fun decimalFormatter(value: Any): String {
        return try {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            df.format(value)
        } catch (e: Exception) {
            "0"
        }
    }

    fun navigate(
        context: Context,
        latitude: Double?,
        longitude: Double?,
        locationName: String = ""
    ) {
        val uri = "geo:" + latitude.toString() + "," + longitude.toString() +
                "?q=" + latitude.toString() + "," + longitude.toString() + "(${locationName})&z=13"
        val mapsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        mapsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // Verify it resolves
        val activities: List<ResolveInfo> =
            context.packageManager.queryIntentActivities(mapsIntent, 0)
        // Start an activity if it's safe
        if (activities.isNotEmpty()) {
            context.startActivity(mapsIntent)
        }
    }

    fun openLink(context: Context, url: String?) {
        if (url == null || url.isEmpty()) {
            return
        }
        var newURL = url
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            newURL = "http://$url"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newURL))
        browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(browserIntent)
    }

    fun loadHtml(textView: TextView, text: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        } else {
            textView.text = Html.fromHtml(text)
        }
    }

    fun formatNumber(num: Int?): String {
        num?.let {
            return NumberFormat.getNumberInstance(Locale.US).format(num)
        } ?: kotlin.run {
            return ""
        }
        //NumberFormat.getIntegerInstance().format(num)
    }

    fun logoutFromGoogleFit(activityContext: Context) {
        try {
            GoogleSignIn.getLastSignedInAccount(activityContext.applicationContext)?.let {
                Fitness.getConfigClient(
                    activityContext.applicationContext, it
                ).disableFit()
            }
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            GoogleSignIn.getClient(activityContext.applicationContext, gso).revokeAccess()
                .addOnCompleteListener {
                    //                Log.e("Logout =>", "Steppi Logout from google revokeAccess")
                }
            GoogleSignIn.getClient(activityContext.applicationContext, gso).signOut()
                .addOnCompleteListener {
                    //                Log.e("Logout =>", "Steppi Logout from google logout")
                }
        } catch (e: Exception) {
            Log.e("exception =>", e.message!!)
        }
    }

    fun logoutFromHuaweiHealth(activityContext: Context) {
        //todo:
    }

    fun logoutFromFitBit(activityContext: Context) {
        STPreference.saveFitbitToken(activityContext, null)
    }

    fun logoutFromGarmin(activityContext: Context) {
        STPreference.saveGarminToken(activityContext, null)
        STPreference.saveGarminTokenSecret(activityContext, null)
    }

    fun getNotificationTitle(context: Context, type: String?): String {
        return when (type) {
            NotificationType.RECEIVED_REFERRAL_STEPS.name -> context.getString(R.string.referral_reward)
            NotificationType.CHALLENGE_TARGET_STEPS_ACHIEVED.name -> context.getString(R.string.challenge_complete)
            NotificationType.CORPORATE_CHALLENGE_COMPLETED.name -> context.getString(R.string.challenge_complete)
            NotificationType.NEW_TEAM_DISCUSSION_MESSAGE.name -> context.getString(R.string.new_team_discussion_message)
            NotificationType.REPLIED_TO_YOUR_TEAM_DISCUSSION_MESSAGE.name -> context.getString(R.string.team_discussion_message_reply)
            NotificationType.NEW_REWARD_AVAILABLE.name -> context.getString(R.string.new_reward)
            NotificationType.SYNC_NOW_NOTE.name -> context.getString(R.string.sync_now)
            NotificationType.NEW_PUBLIC_CHALLENGE_AVAILABLE.name -> context.getString(R.string.new_public_challenge)
            NotificationType.CORPORATE_CHALLENGE_ABOUT_TO_START.name -> context.getString(R.string.corporate_challenge_announced)
            NotificationType.CORPORATE_CHALLENGE_STARTING_SOON.name -> context.getString(R.string.corporate_challenge_starting_soon)
            NotificationType.REWARD_CUSTOM.name -> context.getString(R.string.new_reward_added)
            else -> type!!
        }
    }

    fun getVersionCode(context: Context): Int {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val versionCode: Int
        versionCode = if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return pInfo.longVersionCode.toInt() // avoid huge version numbers and you will be ok
        } else {
            return pInfo.versionCode
        }
    }

    enum class EnumFitnessDevice {
        NOT_CONNECTED,
        GOOGLE_FIT,
        FITBIT,
        GARMIN_HEALTH,
        XIAOMI,
        SAMSUNG_HEALTH,
        HUAWEI_HEALTH
    }

    enum class NotificationType {
        RECEIVED_REFERRAL_STEPS,
        CHALLENGE_TARGET_STEPS_ACHIEVED,
        CORPORATE_CHALLENGE_COMPLETED,
        NEW_TEAM_DISCUSSION_MESSAGE,
        REPLIED_TO_YOUR_TEAM_DISCUSSION_MESSAGE,
        NEW_REWARD_AVAILABLE,
        SYNC_NOW_NOTE,
        NEW_PUBLIC_CHALLENGE_AVAILABLE,
        CORPORATE_CHALLENGE_ABOUT_TO_START,
        CORPORATE_CHALLENGE_STARTING_SOON,
        REWARD_CUSTOM
    }

    fun setImageSize(context: Context, imageView: View, padding: Int) {
        val imageWidth = ((getDeviceWidth(context)) - (2 * padding)).toFloat()
        val aspectRatio = 1080F / 720F
        val imageHeight = imageWidth / aspectRatio
        imageView.layoutParams.height = imageHeight.toInt()
    }

    fun convertPixelsToDp(context: Context?, px: Float): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    fun convertDpToPixel(context: Context?, dp: Float): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    fun singleClick(): Boolean {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return false
        }
        lastClickTime = SystemClock.elapsedRealtime()
        return true
    }

    fun getMiles(i: Double): Double {
        return i * 0.000621371
    }

    fun <T> chopped(
        list: List<T>,
        L: Int
    ): List<List<T>>? {
        val parts: MutableList<List<T>> =
            ArrayList()
        val N = list.size
        var i = 0
        while (i < N) {
            parts.add(
                ArrayList(
                    list.subList(i, Math.min(N, i + L))
                )
            )
            i += L
        }
        return parts
    }

    fun isGMSAvailable(context: Context): Boolean {

        val gmsApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = gmsApiAvailability.isGooglePlayServicesAvailable(context)
        return resultCode == ConnectionResult.SUCCESS
    }

    fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}
