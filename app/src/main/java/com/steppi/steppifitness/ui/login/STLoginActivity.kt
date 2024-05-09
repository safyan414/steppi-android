package com.steppi.steppifitness.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.widget.addTextChangedListener
import butterknife.OnClick
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.gson.Gson
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.biomatric.STBiometricPromptManager
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.instagram.InstagramApp
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.model.STFacebookData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.request.login.STLoginRequest
import com.steppi.steppifitness.network.request.login.STSocialAccountLoginRequest
import com.steppi.steppifitness.network.response.login.STSocialRegisterResponse
import com.steppi.steppifitness.network.response.login.SocialRegisterData
import com.steppi.steppifitness.network.response.user.STUserData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.country.STCountrySelectionActivity
import com.steppi.steppifitness.ui.device_connection.STChooseDeviceActivity
import com.steppi.steppifitness.ui.login.mvi.STLogInController
import com.steppi.steppifitness.ui.login.mvi.STLoginIntent
import com.steppi.steppifitness.ui.login.mvi.STLoginState
import com.steppi.steppifitness.ui.otp.STOtpActivity
import com.steppi.steppifitness.ui.register.STRegisterActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class STLoginActivity :
    STBaseViewModelActivity<STLoginIntent, STLoginState,
            STLogInController>(STLogInController::class.java) {
    private var callbackManager: CallbackManager? = null
    private var facebookAccessToken: String? = null
    private var mApp: InstagramApp? = null
    private var userInfoHashmap = HashMap<String, String>()
    private var selectedCountry: STCountryData? = null
    private var isSignInAnimated = false
    private lateinit var biometricPromptManager: STBiometricPromptManager

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {
        animateLayout(logo, container)
        setFirebaseToken()
        initFaceBook()
        initInstagram()
        setListeners()
        passwordTextInputLayout.setPasswordTransformation(userPassword)
        STPreference.getBioMetricToken(activityContext)?.let {
            biometricPromptManager = STBiometricPromptManager(this)
            setBioMetricLogin()
        }
    }

    private fun setBioMetricLogin() {
        biometricPromptManager.normalBiometricPrompt(
            failedAction = { /*showToast(it)*/ },
            successAction = { loginWithFingerPrint() }
        )
    }

    private fun loginWithFingerPrint() {
        val loginRequest = STLoginRequest()
        loginRequest.fingerprint = STPreference.getBioMetricToken(activityContext)
        loginRequest.deviceId = STUtils.getDeviceId(activityContext)
        loginRequest.deviceType = STConstants.DEVICE_TYPE
        loginRequest.deviceToken = STPreference.getDeviceId(activityContext)
        invokeIntent(STLoginIntent.LogIn(loginRequest))
    }

    private fun setFirebaseToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(object : OnCompleteListener<InstanceIdResult> {
                override fun onComplete(@NonNull task: Task<InstanceIdResult>) {
                    if (!task.isSuccessful) {
                        return
                    }
                    val token = task.result?.token
                    STPreference.saveDeviceId(activityContext, token)

                }
            })
    }

    private fun setListeners() {
        userPassword.addTextChangedListener { validateAllData() }
        var isPhoneAnimated = false
        userMobile.textChangeObserver {
            if (it.trim().isNotEmpty()) {
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

    private fun validateAllData() {
        if (STUtils.getValueFromView(userMobile).isNullOrEmpty() || STUtils.getValueFromView(
                userPassword
            ).isNullOrEmpty()
        ) {
            signIn.background =
                STUtils.getDrawable(activityContext, R.drawable.button_selector_normal)
            signIn.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setLightFont(activityContext, signIn)
            if (isSignInAnimated) {
                signIn.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isSignInAnimated = false
            }
        } else {
            signIn.background =
                STUtils.getDrawable(activityContext, R.drawable.button_selector_enabled)
            signIn.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setBoldFont(activityContext, signIn)
            if (!isSignInAnimated) {
                signIn.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isSignInAnimated = true
            }
        }
    }

    override fun onViewModelReady() {
        STConstants.IS_SYNC_INITIALLY = true
    }

    override fun processState(state: STLoginState) {
        when (state) {
            is STLoginState.Loading -> {
                requestDidStart()
            }
            is STLoginState.LogInWithFaceBook -> {
                requestDidFinish()
                loginWithFaceBook(state.fbAccountLoginData)
            }
            is STLoginState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message ?: "")
            }
            is STLoginState.LogIn -> {
                requestDidFinish()
                setUserData(state.userDataResponse.data)

            }
            is STLoginState.LogInWithInstagram -> {
                requestDidFinish()
                loginWithInstagram(state.instaAccountLoginData)
            }
        }
    }

    private fun setUserData(userData: STUserData?) {
        STPreference.saveRegToken(activityContext, userData?.regToken)
        STPreference.saveAccessToken(activityContext, userData?.accessToken)
        userData?.user?.let { user ->
            STPreference.setName(activityContext, user.name)
            STPreference.setEmail(activityContext, user.email)
            STPreference.setCorporateEmail(activityContext, user.corporateEmail)
            STPreference.setProfilePic(activityContext, user.picture)
            STPreference.setUserId(activityContext, user.id)
            STPreference.setUserLevel(activityContext, user.activeLevel)

            user.device?.let {
                //STPreference.saveFitnessDevice(activityContext, it.deviceCode)
                STPreference.saveFitnessDeviceName(activityContext, it.name)
                STPreference.saveFitnessDeviceID(activityContext, it.id)
            }
            when (user.activeLevel) {
                STConstants.ACTIVE_LEVEL_OTP -> {
                    showOtpDialog()
                }
                STConstants.ACTIVE_LEVEL_DEVICE_SELECTION -> {
                    showDeviceSelection()
                }
                else -> {
                    //                    goToHome()
                    //                    user?.device?.let { device ->
                    //                        deviceSelected = device
                    //                    } ?: kotlin.run {
                    //                    }
                    showDeviceSelection()
                }
            }
        }
    }

    private fun setUserSocialData(userData: SocialRegisterData?) {
        STPreference.saveRegToken(activityContext, userData?.regToken)
        STPreference.saveAccessToken(activityContext, userData?.accessToken)
        userData?.user?.let { user ->
            STPreference.setName(activityContext, user.name)
            STPreference.setEmail(activityContext, user.email)
            STPreference.setCorporateEmail(activityContext, user.corporateEmail)
            STPreference.setProfilePic(activityContext, user.picture)
            STPreference.setUserId(activityContext, user.id)
            STPreference.setUserLevel(activityContext, user.activeLevel)
            STPreference.setUserGender(activityContext, user.gender)

            user.device?.let {
                //STPreference.saveFitnessDevice(activityContext, it.deviceCode)
                STPreference.saveFitnessDeviceName(activityContext, it.name)
                STPreference.saveFitnessDeviceID(activityContext, it.id)
            }
        }
    }

    private fun showOtpDialog() {
        //finish()
        val otpIntent = Intent(activityContext, STOtpActivity::class.java)
        otpIntent.putExtra(STConstants.IS_FROM_LOGIN_REGISTER, true)
        startActivity(otpIntent)

    }

    private fun showDeviceSelection() {
        finish()
        val otpIntent = Intent(activityContext, STChooseDeviceActivity::class.java)
//        deviceSelected?.let {
//            otpIntent.putExtra(STConstants.CHOOSE_DEVICE_CONNECTION_DATA, it)
//        }
        startActivity(otpIntent)

    }

    private fun loginWithFaceBook(fbAccountLoginData: STSocialRegisterResponse) {
        when {
            STAPIConstants.USER_NOT_FOUND == fbAccountLoginData.statusCode -> {
                val registerIntent = Intent(activityContext, STRegisterActivity::class.java)
                registerIntent.putExtra(STConstants.USER_DATA, fbAccountLoginData.data?.user)
                startActivity(registerIntent)
            }
            STAPIConstants.USER_NOT_VERIFIED == fbAccountLoginData.statusCode -> {
                showOtpDialog()
            }
            else -> {
                setUserSocialData(fbAccountLoginData.data)
//                fbAccountLoginData.data?.user?.device?.let { device ->
//                    deviceSelected = device
//                } ?: kotlin.run {
//                }
//                goToHome()
                showDeviceSelection()
            }
        }
    }

    private fun loginWithInstagram(instaAccountLoginData: STSocialRegisterResponse) {
        when {
            STAPIConstants.USER_NOT_FOUND == instaAccountLoginData.statusCode -> {
                val userData = instaAccountLoginData.data?.user
                userData?.name = userInfoHashmap[InstagramApp.TAG_FULL_NAME]
                userData?.picture = userInfoHashmap[InstagramApp.TAG_PROFILE_PICTURE]
                val registerIntent = Intent(activityContext, STRegisterActivity::class.java)
                registerIntent.putExtra(STConstants.USER_DATA, userData)
                startActivity(registerIntent)
            }
            STAPIConstants.USER_NOT_VERIFIED == instaAccountLoginData.statusCode -> showOtpDialog()
            else -> {
                setUserSocialData(instaAccountLoginData.data)
//                goToHome()
//                instaAccountLoginData.data?.user?.device?.let { device ->
//                    deviceSelected = device
//                } ?: kotlin.run {
//                }
                showDeviceSelection()
            }
        }
    }

    @OnClick(R.id.signFacebook)
    fun signWithFacebook() {
        if (STUtils.isInternetOn(this)) {
            if (!BuildConfig.DEBUG)
                STFireBaseAnalytics.trackEvent(
                    STFireBaseAnalyticsConstants.EVENT_SIGN_IN_WITH_FACEBOOK,
                    "", ""
                )
            LoginManager.getInstance().logOut()
            val permissionNeeds = listOf("public_profile", "email")
            LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds)
        } else {
            showToast(resources.getString(R.string.check_internet_connection))
        }
    }

    @OnClick(R.id.signInstagram)
    fun loginWithInstagram() {
        if (STUtils.isInternetOn(activityContext)) {
            mApp!!.authorize()
        }
    }

    @OnClick(R.id.signUp)
    fun signUp() {
        startActivity(Intent(activityContext, STRegisterActivity::class.java))
    }

    @OnClick(R.id.signIn)
    fun signIn() {
        validateAndSubmit()
    }

    @OnClick(R.id.forgotPassword)
    fun forgotPassword() {
        startActivity(Intent(activityContext, STForgotPasswordActivity::class.java))
    }

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

    private fun validateAndSubmit() {
        if (STUtils.getValueFromView(userMobile).isNullOrEmpty()) {
            showToast(getString(R.string.validation_enter_phone))
            userMobile.requestFocus()
            return
        }

        if (STUtils.getValueFromView(userPassword).isNullOrEmpty()) {
            showToast(getString(R.string.validation_password))
            userPassword.requestFocus()
            return
        }

        if (STUtils.getValueFromView(userPassword)?.length ?: 0 < STConstants.PASSWORD_MINIMUM_LENGTH) {
            showToast(
                String.format(
                    getString(R.string.validation_minimum_password),
                    STConstants.PASSWORD_MINIMUM_LENGTH
                )
            )
            userPassword.requestFocus()
            return
        }
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_SIGN_IN_WITH_EMAIL,
                "", ""
            )
        val loginRequest = STLoginRequest()
        loginRequest.phoneNumber =
            STUtils.getValueFromView(mobileCode).plus(STUtils.getValueFromView(userMobile))
        loginRequest.password = STUtils.getValueFromView(userPassword)
        loginRequest.deviceId = STUtils.getDeviceId(activityContext)
        loginRequest.deviceType = STConstants.DEVICE_TYPE
        loginRequest.deviceToken = STPreference.getDeviceId(activityContext)
        invokeIntent(STLoginIntent.LogIn(loginRequest))
    }

    private fun onFacebookLoginSuccess() {
        val fbAccountLoginRequest =
            STSocialAccountLoginRequest()
        fbAccountLoginRequest.fbAccessToken = STPreference.getFacebookAccessToken(activityContext)
        fbAccountLoginRequest.deviceToken = STPreference.getDeviceId(activityContext)
        fbAccountLoginRequest.deviceId = STUtils.getDeviceId(activityContext)
        fbAccountLoginRequest.deviceType = STConstants.DEVICE_TYPE
        invokeIntent(STLoginIntent.LogInWithFaceBook(fbAccountLoginRequest))
    }

    private fun onInstagramLoginSuccess(userInfo: HashMap<String, String>) {
        val instaAccountLoginRequest =
            STSocialAccountLoginRequest()
        instaAccountLoginRequest.instagramId = userInfo[InstagramApp.TAG_ID]
        instaAccountLoginRequest.deviceToken = STPreference.getDeviceId(activityContext)
        instaAccountLoginRequest.deviceId = STUtils.getDeviceId(activityContext)
        instaAccountLoginRequest.deviceType = STConstants.DEVICE_TYPE
        invokeIntent(STLoginIntent.LogInWithInstagram(instaAccountLoginRequest))
    }

    private fun initFaceBook() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback)
    }

    private fun initInstagram() {
        mApp = InstagramApp(
            activityContext, STConstants.INSTAGRAM_CLIENT_ID,
            STConstants.INSTAGRAM_CLIENT_SECRET, STConstants.INSTAGRAM_CALLBACK_URL
        )
        mApp!!.setListener(object : InstagramApp.OAuthAuthenticationListener {
            override fun onSuccess() {
                mApp!!.fetchUserName(handler)
            }

            override fun onFail(error: String) {}
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            callbackManager?.onActivityResult(requestCode, resultCode, data)
        }
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_COUNTRY == requestCode) {
                data?.let {
                    selectedCountry =
                        it.getSerializableExtra(STConstants.COUNTRY_DATA) as STCountryData
                    STUtils.setValueToView(mobileCode, selectedCountry?.phoneCode)
                    flagSelected.load(activityContext, selectedCountry?.flag)
                }
            }
        }
    }

    private val facebookCallback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            facebookAccessToken = loginResult.accessToken.token.toString()
            STPreference.setFacebookAccessToken(activityContext, facebookAccessToken!!)
            val request = GraphRequest.newMeRequest(loginResult.accessToken)
            { `object`, _ ->
                try {
                    val isFacebookResponse =
                        Gson().fromJson(`object`.toString(), STFacebookData::class.java)
                    isFacebookResponse?.let {
                        onFacebookLoginSuccess()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val parameters = Bundle()
            parameters.putString("fields", "id,name,link,email,picture,first_name,last_name,gender")
            request.parameters = parameters
            request.executeAsync()
        }

        override fun onCancel() {
            Log.i("Login", "Cancelled")
        }

        override fun onError(exception: FacebookException) {
            Log.i("Login", "" + exception.stackTrace)
        }
    }


    private val handler = Handler(Handler.Callback { msg ->
        if (msg.what == InstagramApp.WHAT_FINALIZE) {
            userInfoHashmap = mApp!!.userInfo
            onInstagramLoginSuccess(mApp!!.userInfo)
        }
        false
    })

//    private fun goToHome() {
//        startActivity(Intent(activityContext, STHomeActivity::class.java))
//        finish()
//    }

}
