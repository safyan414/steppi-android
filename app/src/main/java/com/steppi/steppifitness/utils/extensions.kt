package com.steppi.steppifitness.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.Uri
import android.text.Editable
import android.text.SpannableString
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.textfield.TextInputLayout
import com.steppi.steppifitness.R
import kotlinx.android.synthetic.main.custom_toast_layout.view.*
import java.util.*

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun Activity.showToast(message: String?) {
    showToast(applicationContext, message)
}

fun Fragment.showToast(message: String?) {
    showToast(activity?.applicationContext, message)
}

private fun showToast(context: Context?, message: String?) {
    if (null == context || null == message)
        return
    Toast(context).showOnTop(context, message)
}

@SuppressLint("InflateParams")
fun Toast.showOnTop(context: Context, title: String): Toast {
    val view = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null)
    val titleDialog = view.textCustomToast as TextView
    titleDialog.text = title
    setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
    setView(view)
    duration = Toast.LENGTH_SHORT
    show()
    return this
}

fun AppCompatActivity.changeFragment(
    containerId: Int,
    fragment: Fragment,
    tag: String = fragment.javaClass.simpleName
) {
    supportFragmentManager.beginTransaction().replace(containerId, fragment, tag).commit()
}

fun Fragment.changeFragment(
    containerId: Int,
    fragment: Fragment,
    tag: String = fragment.javaClass.simpleName
) {
    childFragmentManager.beginTransaction().replace(containerId, fragment, tag).commit()
}

fun RecyclerView.setHorizontalItemDecoration(space: Int, initialPadding: Int, isRtl: Boolean) {
    addItemDecoration(
        HorizontalSpacesItemDecoration(
            space, initialPadding, isRtl
        )
    )
}

fun RecyclerView.setVerticalItemDecoration(space: Int, initialPadding: Int) {
    addItemDecoration(
        VerticalSpacesItemDecoration(
            space, initialPadding
        )
    )
}

fun RecyclerView.setHorizontalLayoutManager() {
    layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.setVerticalLayoutManager() {
    layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

@SuppressLint("ClickableViewAccessibility", "RestrictedApi")
fun TextInputLayout.setPasswordTransformation(editText: EditText) {
    val passwordIcon =
        findViewById<CheckableImageButton>(R.id.text_input_end_icon)
    passwordIcon?.setOnTouchListener { view, event ->
        if (passwordIcon.isChecked) {
            passwordIcon.setImageResource(R.drawable.password_eye_icon)
            postDelayed({
                editText.setPasswordTransformationMethod()
                editText.textColor(STUtils.getColor(context, R.color.theme_color))
                editText.setSelection(editText.text.length)
            }, 100)
        } else {
            passwordIcon.setImageResource(R.drawable.password_eye_icon_enabled)
            postDelayed({
                editText.textColor(STUtils.getColor(context, R.color.white))
                editText.setPasswordTransformationMethodEmpty()
                editText.requestLayout()
                editText.setSelection(editText.text.length)
            }, 100)
        }
        false
    }
    postDelayed({
        editText.setPasswordTransformationMethod()
        editText.textColor(STUtils.getColor(context, R.color.theme_color))
    }, 100)
}

fun TextView.setPasswordTransformationMethod() {
    transformationMethod =
        CustomPasswordTransformationMethod('\u25A0', 100)
}

fun TextView.setPasswordTransformationMethodEmpty() {
    transformationMethod =
        null
}

internal class HorizontalSpacesItemDecoration(
    private val space: Int,
    private val initialPadding: Int = 0,
    private val isRtl: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
//        Log.e("ItemPadding", "itemPosition: $itemPosition count: ${parent.adapter?.itemCount}")

        if (isRtl) {
            if (itemPosition == 0) {
                outRect.left = if (initialPadding == 0) space else initialPadding
            } else {
                outRect.left = space
            }


            if (itemPosition == state.itemCount - 1) {
                outRect.right = if (initialPadding == 0) space else initialPadding
            } else {
                outRect.right = 0
            }

        } else {
            if (itemPosition == 0) {
                outRect.right = if (initialPadding == 0) space else initialPadding
            } else {
                outRect.right = space
            }

            if (itemPosition == state.itemCount - 1) {
                outRect.left = if (initialPadding == 0) space else initialPadding
            } else {
                outRect.left = 0
            }
        }
    }
}

internal class VerticalSpacesItemDecoration(
    private val space: Int,
    private val initialPadding: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)

        if (itemPosition == 0) {
            outRect.top = if (initialPadding == 0) space else initialPadding
        } else {
            outRect.top = space
        }

        if (itemPosition == state.itemCount - 1) {
            outRect.bottom = if (initialPadding == 0) space else initialPadding
        } else {
            outRect.bottom = 0
        }
    }
}

fun ImageView.load(
    context: Context,
    imageUrl: String?,
    cornerRadius: Int? = null,
    loadFailed: (() -> Unit)? = null
) {
    imageUrl?.let {
        cornerRadius?.let {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(
                CenterCrop(),
                RoundedCorners(cornerRadius)
            )
//            requestOptions.error()
            Glide.with(context).load(GlideUrl(imageUrl))
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadFailed?.invoke()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(this)
        } ?: run {
            val glide = Glide.with(context)
            glide.clear(this)
            glide.load(GlideUrl(imageUrl))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadFailed?.invoke()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                }).into(this)
        }
    }
}

fun ImageView.loadImage(context: Context, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context).load(it).into(this)
    }
}

fun Activity.getBitmapFromUrl(url: String?, callback: ((Bitmap) -> Unit)) {
    Glide.with(this)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                callback.invoke(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

fun EditText.textChangeObserver(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun TextView.textColor(color: Int) {
    setTextColor(color)
}

fun TextView.setRtlControlForDrawables(resourceID: Int) {
    val isLeftToRight =
        TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR
    if (isLeftToRight) {
        this.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(this.context, resourceID),
            null
        )
    } else {
        this.setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(this.context, resourceID),
            null,
            null,
            null
        )
    }
}

fun ViewPager.onPageSelected(pageSelected: (currentPage: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            pageSelected.invoke(position)
        }
    })
}

class STVerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
    }
}

fun Dialog.setSize(activity: Activity) {
    val wm =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager // for activity use context instead of getActivity()
    val display = wm.defaultDisplay // getting the screen size of device
    val size = Point()
    display.getSize(size)
    val width = size.x - 80  // Set your heights
    val height = WindowManager.LayoutParams.WRAP_CONTENT // set your widths

    val lp = window?.attributes
    lp?.width = width
    lp?.height = height
    window!!.attributes = lp
    window!!.attributes.dimAmount = 0.9f
    window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
}

fun SpannableString.spanWith(target: String, apply: SpannableBuilder.() -> Unit) {
    val builder = SpannableBuilder()
    apply(builder)

    val start = this.indexOf(target)
    val end = start + target.length

    setSpan(builder.what, start, end, builder.flags)
}

fun Activity.startActivityFinishAll(intent: Intent) {
    startActivity(
        intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    overridePendingTransition(
        R.anim.slide_in_right,
        R.anim.slide_out_left
    )
}

fun Context.copyToClipBoard(label: String, text: String) {
    val clipboard: ClipboardManager =
        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)
}

class SpannableBuilder {
    lateinit var what: Any
    var flags: Int = 0
}

fun getToolTipSpace(activityContext: AppCompatActivity, value: Double): Int {
    val display = activityContext.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    val screenWidth = size.x
//    val screenHeight = size.y
    val toolTipSpace = screenWidth / value
    return toolTipSpace.toInt()
}

fun call(context: Context, number: String) {
    if (!TextUtils.isEmpty(number)) {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CALL_PHONE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun checkPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun checkPermissions(context: Context): Boolean {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    }
    return false
}

fun isLocationEnabled(context: Context): Boolean {
    val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}
