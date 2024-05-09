package com.steppi.steppifitness.ui.register

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Patterns
import android.widget.CompoundButton
import butterknife.OnCheckedChanged
import butterknife.OnClick
import butterknife.OnTextChanged
import com.bumptech.glide.Glide
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.request.login.STRegisterRequest
import com.steppi.steppifitness.network.response.user.STUserData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.common.STWebViewActivity
import com.steppi.steppifitness.ui.country.STCountrySelectionActivity
import com.steppi.steppifitness.ui.otp.STOtpActivity
import com.steppi.steppifitness.ui.register.mvi.STRegisterController
import com.steppi.steppifitness.ui.register.mvi.STRegisterIntent
import com.steppi.steppifitness.ui.register.mvi.STRegisterState
import com.steppi.steppifitness.utils.*
import com.tsongkha.spinnerdatepicker.DatePicker
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.header_layout.*
import java.io.File
import java.util.*

class STRegisterActivity :
    STBaseViewModelActivity<STRegisterIntent, STRegisterState, STRegisterController>(
        STRegisterController::class.java
    ), com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener {
    private var imageFile: File? = null
    private var gender: Int = 0
    private var countryId: Int = 0
    private var facebookId: String? = null
    private var facebookImageUrl: String? = null
    private var instagramId: String? = null
    private var user: STUser? = null
    private var selectedCountry: STCountryData? = null
    private var selectedNationality: STCountryData? = null
    private var isPhoneAnimated = false
    private var isSignUpAnimated = false
    private lateinit var registerRequest: STRegisterRequest
    private val dpd = SpinnerDatePickerDialogBuilder()
    val c = Calendar.getInstance()
    private var yearDefault = c.get(Calendar.YEAR)
    private var monthDefault = c.get(Calendar.MONTH)
    private var dayDefault = c.get(Calendar.DAY_OF_MONTH)
    private var dateOfBirth: Date? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViews() {
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_SIGN_UP_FORM_VISIT,
                "", ""
            )
        setHeaderName(activityContext.resources.getString(R.string.sign_up_here))
        getDataIntent()
        user?.let {
            STUtils.setValueToView(name, it.name)
            STUtils.setValueToView(personalEmail, it.email)
            facebookId = it.facebookId
            instagramId = it.instagramId
            facebookImageUrl = it.picture
            userImage.load(activityContext, it.picture)
        }
        passwordTextInputLayout.setPasswordTransformation(userPassword)
        animateLayout(headerContainer, mainContainer)
        userMobile.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                if ((STUtils.getValueFromView(view) ?: "").startsWith("0")) {
                    if (STUtils.getValueFromView(view) == "0") {
                        userMobile.text?.clear()
                    } else {
                        userMobile.setText(userMobile.text?.substring(1))
                    }
                }
            }
        }
    }

    private fun getDataIntent() {
        intent.getSerializableExtra(STConstants.USER_DATA)?.let {
            user = it as STUser
        }
    }

    private fun validateAllData() {
        if (STUtils.getValueFromView(name).isNullOrEmpty()
            || STUtils.getValueFromView(userMobile).isNullOrEmpty()
            || STUtils.getValueFromView(personalEmail).isNullOrEmpty()
            || STUtils.getValueFromView(userPassword).isNullOrEmpty()
            || gender == 0
            || STUtils.getValueFromView(dob).isNullOrEmpty()
            || !checkboxTermsAndConditions.isChecked
        ) {
            register.background =
                STUtils.getDrawable(activityContext, R.drawable.button_selector_normal)
            register.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setLightFont(activityContext, register)
            if (isSignUpAnimated) {
                register.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isSignUpAnimated = false
            }
        } else {
            register.background =
                STUtils.getDrawable(activityContext, R.drawable.button_selector_enabled)
            register.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setBoldFont(activityContext, register)
            if (!isSignUpAnimated) {
                register.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isSignUpAnimated = true
            }
        }
    }

    override fun onViewModelReady() {
        invokeIntent(STRegisterIntent.GetCountries)
    }

    override fun processState(state: STRegisterState) {
        when (state) {
            is STRegisterState.Loading -> {
                requestDidStart()
            }
            is STRegisterState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
            }
            is STRegisterState.Register -> {
                requestDidFinish()
                registerSuccess(state.userResponse.data)
            }
            is STRegisterState.GetCountries -> {
                setCountries(state.countryResponse.data)
            }

        }
    }

    private fun registerSuccess(data: STUserData?) {
        data?.let { userData ->
            STPreference.saveRegToken(activityContext, userData.regToken)
            /*userData.user?.let { user ->
                STPreference.setName(activityContext, user.name)
                STPreference.setEmail(activityContext, user.email)
                STPreference.setCorporateEmail(activityContext, user.corporateEmail)
                STPreference.setProfilePic(activityContext, user.picture)
                STPreference.setUserId(activityContext, user.id)
            }*/
            showOtpDialog()
        }
    }

    private fun showOtpDialog() {
        val otpIntent = Intent(activityContext, STOtpActivity::class.java)
        otpIntent.putExtra(STConstants.IS_FROM_LOGIN_REGISTER, true)
        otpIntent.putExtra(STConstants.REGISTER_DATA, registerRequest)
        otpIntent.putExtra(STConstants.IMAGE_FILE, imageFile)
        otpIntent.putExtra(STConstants.FACEBOOK_IMAGE_URL, facebookImageUrl)
        //otpIntent.putExtra("registerData",imageFile)
        startActivity(otpIntent)
    }

    private fun setCountries(countries: List<STCountryData>?) {
        countries?.let {
            if (it.isNotEmpty()) {
                countryId = (it[0].id ?: "0").toInt()
            }
        }
    }

    @OnClick(R.id.register)
    fun register() {
        if (validateData()) {
            registerRequest = STRegisterRequest()
            registerRequest.name = STUtils.getValueFromView(name) ?: ""
            registerRequest.phoneNumber = STUtils.getValueFromView(mobileCode)
                .plus(STUtils.getValueFromView(userMobile) ?: "")
            registerRequest.email = STUtils.getValueFromView(personalEmail) ?: ""
            registerRequest.password = STUtils.getValueFromView(userPassword) ?: ""
            registerRequest.gender = gender

            registerRequest.dob = STUtils.getDateEn(
                STUtils.getValueFromView(dob) ?: "",
                "dd-MMM-yyyy",//dd-MM-yyyy
                "MM/dd/yyyy"
            )

            selectedNationality?.let {
                registerRequest.countryId = it.id
            }
//            if (countryId > 0) {
//                registerRequest.countryId = countryId.toString()
//            }

//            STUtils.getValueFromView(corporateEmail)?.let {
//                registerRequest.corporateEmail = it
//            }
//            STUtils.getValueFromView(referralCode)?.let {
//                registerRequest.referralCode = it
//            }
            facebookId?.let {
                registerRequest.facebookId = it
            }
            instagramId?.let {
                registerRequest.instagramId = it
            }
            if (!BuildConfig.DEBUG)
                STFireBaseAnalytics.trackEvent(
                    STFireBaseAnalyticsConstants.EVENT_SIGN_UP_FORM_SUBMISSION,
                    "", ""
                )
            //invokeIntent(STRegisterIntent.Register(imagePart, map))
            invokeIntent(STRegisterIntent.Register(registerRequest))
        }
    }

    private fun validateData(): Boolean {
        if (STUtils.getValueFromView(name).isNullOrEmpty()) {
            showToast(getString(R.string.validation_enter_name))
            name.requestFocus()
            return false
        }
        if (STUtils.getValueFromView(userMobile).isNullOrEmpty()) {
            showToast(getString(R.string.validation_enter_phone))
            userMobile.requestFocus()
            return false
        }
        if (STUtils.getValueFromView(personalEmail).isNullOrEmpty()) {
            showToast(getString(R.string.validation_enter_personal_email))
            personalEmail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(STUtils.getValueFromView(personalEmail).toString())
                .matches()
        ) {
            showToast(getString(R.string.validation_enter_valid_email))
            personalEmail.requestFocus()
            return false
        }
//        if (!STUtils.getValueFromView(corporateEmail).isNullOrEmpty()) {
//            if (!Patterns.EMAIL_ADDRESS.matcher(STUtils.getValueFromView(corporateEmail).toString()).matches()) {
//                showToast(getString(R.string.validation_enter_corporate_valid_email))
//                corporateEmail.requestFocus()
//                return false
//            }
//        }
        if (STUtils.getValueFromView(userPassword).isNullOrEmpty()) {
            showToast(getString(R.string.validation_password))
            userPassword.requestFocus()
            return false
        }
        if (STUtils.getValueFromView(userPassword)?.length ?: 0 < STConstants.PASSWORD_MINIMUM_LENGTH) {
            showToast(
                String.format(
                    getString(R.string.validation_minimum_password),
                    STConstants.PASSWORD_MINIMUM_LENGTH
                )
            )
            userPassword.requestFocus()
            return false
        }
        if (gender == 0) {
            showToast(getString(R.string.validation_select_gender))
            return false
        }
        if (STUtils.getValueFromView(dob).isNullOrEmpty()) {
            showToast(getString(R.string.validation_enter_dob))
            dob.requestFocus()
            return false
        }
        if (!checkboxTermsAndConditions.isChecked) {
            showToast(getString(R.string.validation_select_tnc))
            return false
        }
        return true
    }

    @OnClick(R.id.termAndConditions)
    fun termAndConditions() {
        val termsIntent = Intent(activityContext, STWebViewActivity::class.java)
        termsIntent.putExtra(STConstants.WEB_URL, BuildConfig.API_URL + STConstants.TERMS_URL)
        termsIntent.putExtra(
            STConstants.WEB_HEADER,
            activityContext.resources.getString(R.string.terms_condition)
        )
        startActivity(termsIntent)
    }

    fun setImage(resultUri: Uri?) {
        resultUri?.let {
            imageFile = File(STUtils.getRealPathFromURI(this, it))
            Glide.with(this)
                .load(resultUri)
                .into(userImage)
        }

    }

    @OnClick(R.id.dob)
    fun addDateOfBirth() {
//        val dpd = DatePickerDialog(
//            activityContext,
//            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
//                STUtils.setValueToView(
//                    dob,
//                    STUtils.getDate(
//                        "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth,
//                        "yyyy-MM-dd",
//                        "dd-MMM-yyyy"// dd-MM-yyyy
//                    )
//                )
//            },
//            year,
//            month,
//            day
//        )
//        dpd.show()
//        dpd.datePicker.maxDate = System.currentTimeMillis()

        val today = Calendar.getInstance()
        dpd.context(this@STRegisterActivity)
            .callback(this@STRegisterActivity)
            .spinnerTheme(R.style.NumberPickerStyle)
            .defaultDate(yearDefault, monthDefault, dayDefault)
            .maxDate(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            )
            .build()
            .show()
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
//        val calendar: Calendar = GregorianCalendar(year, monthOfYear, dayOfMonth)
        yearDefault = year
        monthDefault = monthOfYear
        dayDefault = dayOfMonth
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, yearDefault)
        cal.set(Calendar.MONTH, monthDefault)
        cal.set(Calendar.DAY_OF_MONTH, dayDefault)
        dateOfBirth = cal.time
        STUtils.setValueToView(
            dob,
            STUtils.getDate(
                "" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth,
                "yyyy-MM-dd",
                "dd-MMM-yyyy"// dd-MM-yyyy
            )
        )
//        dateTextView!!.text = simpleDateFormat!!.format(calendar.time)
    }

//    override fun onCancelled(view: DatePicker) {
////        dateTextView!!.setText(R.string.cancelled)
//    }

    @OnClick(R.id.mobileCodeLayout)
    fun selectCountryCode() {
        val countryIntent = Intent(activityContext, STCountrySelectionActivity::class.java)
        countryIntent.putExtra(STConstants.NEED_MOBILE_CODE, true)
        countryIntent.putExtra(STConstants.SELECTED_COUNTRY_DATA, selectedCountry)
        startActivityForResult(
            countryIntent,
            STConstants.REQUEST_CODE_COUNTRY
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_COUNTRY == requestCode) {
                data?.let {
                    selectedCountry =
                        it.getSerializableExtra(STConstants.COUNTRY_DATA) as STCountryData
                    STUtils.setValueToView(mobileCode, selectedCountry?.phoneCode)
                    flagSelected.loadImage(activityContext, selectedCountry?.flag)
                }
            } else if (STConstants.REQUEST_CODE_NATIONALITY == requestCode) {
                data?.let {
                    selectedNationality =
                        it.getSerializableExtra(STConstants.COUNTRY_DATA) as STCountryData
                    STUtils.setValueToView(nationality, selectedNationality?.name)
                    getBitmapFromUrl(selectedNationality?.flag) { bitmap ->

                        val resizedBitmap = Bitmap.createScaledBitmap(
                            bitmap,
                            STUtils.getDimen(
                                activityContext,
                                R.dimen.margin_extra_large_xxxx
                            ),
                            STUtils.getDimen(activityContext, R.dimen.margin_large_x),
                            false
                        )

                        nationality.setDrawableAndAnimate(
                            BitmapDrawable(
                                resources, resizedBitmap
                            )
                        )
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @OnClick(R.id.male)
    fun setMale() {
        gender = STConstants.MALE
        male.background = STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
        feMale.background = STUtils.getDrawable(activityContext, R.drawable.edit_text_bg)
        male.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        feMale.setTextColor(STUtils.getColor(activityContext, R.color.white))
        STUtils.setBoldFont(activityContext, male)
        STUtils.setLightFont(activityContext, feMale)
        validateAllData()
        male.animateEnableDisable()
    }

    @OnClick(R.id.feMale)
    fun setFeMale() {
        gender = STConstants.FEMALE
        male.background = STUtils.getDrawable(activityContext, R.drawable.edit_text_bg)
        feMale.background = STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
        male.setTextColor(STUtils.getColor(activityContext, R.color.white))
        feMale.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        STUtils.setBoldFont(activityContext, feMale)
        STUtils.setLightFont(activityContext, male)
        validateAllData()
        feMale.animateEnableDisable()
    }

    @OnClick(R.id.nationality)
    fun selectNationality() {
        val countryIntent = Intent(activityContext, STCountrySelectionActivity::class.java)
        countryIntent.putExtra(STConstants.NEED_MOBILE_CODE, false)
        countryIntent.putExtra(STConstants.SELECTED_COUNTRY_DATA, selectedNationality)
        startActivityForResult(
            countryIntent,
            STConstants.REQUEST_CODE_NATIONALITY
        )
    }

    @OnCheckedChanged(R.id.checkboxTermsAndConditions)
    fun termsAndConditionCheckedChange(button: CompoundButton, checked: Boolean) {
        validateAllData()
        checkboxTermsAndConditions.animateCheckedChange(checked).start()
    }

    @OnTextChanged(R.id.userMobile)
    fun userMobileTextChange(text: CharSequence) {
        if (text.trim().isNotEmpty()) {
            mobileNumberIcon.setImageResource(R.drawable.mobile_icon_active)
            if (!isPhoneAnimated) {
                mobileNumberIcon.animateEnableDisable().start()
                isPhoneAnimated = true
            }
        } else {
            if (isPhoneAnimated) {
                mobileNumberIcon.animateEnableDisable().start()
                isPhoneAnimated = false
                mobileNumberIcon.setImageResource(R.drawable.mobile_icon)
            }
        }
        validateAllData()
    }

    @OnTextChanged(R.id.name, R.id.userMobile, R.id.userPassword, R.id.personalEmail, R.id.dob)
    fun validateTextChange(text: CharSequence) {
        validateAllData()
    }

    @OnClick(R.id.changeUserImage, R.id.imageLayout)
    fun addProfilePic() {
        pickImage()
    }
}
