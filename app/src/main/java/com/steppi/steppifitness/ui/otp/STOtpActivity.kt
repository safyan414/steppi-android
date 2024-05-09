package com.steppi.steppifitness.ui.otp

import android.animation.Animator
import android.content.Intent
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.network.request.login.STRegisterRequest
import com.steppi.steppifitness.network.response.user.STUserData
import com.steppi.steppifitness.network.response.user.STUserDataResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.device_connection.STChooseDeviceActivity
import com.steppi.steppifitness.ui.otp.mvi.STOtpController
import com.steppi.steppifitness.ui.otp.mvi.STOtpIntent
import com.steppi.steppifitness.ui.otp.mvi.STOtpState
import com.steppi.steppifitness.utils.*
import com.steppi.steppifitness.views.OtpView
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.header_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class STOtpActivity :
    STBaseViewModelActivity<STOtpIntent, STOtpState, STOtpController>(STOtpController::class.java) {
    var otpData: String? = ""
    private var isFromLoginRegister = false
    private var fallDownAnimation: Animator? = null
    private var isButtonContinueAnimated = false
    private var registerRequest: STRegisterRequest? = null
    private var imageFile: File? = null
    private var facebookImageUrl: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_otp
    }

    override fun initViews() {
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_SIGN_UP_OTP_SCREEN,
                "", ""
            )
        animateLayout(headerContainer, mainContainer)
        getDataIntent()
        setHeaderName(activityContext.resources.getString(R.string.mobile_confirmation))//password_reset
        otp.callback = object : OtpView.CallBack {
            override fun onComplete(otp: String) {
                otpData = otp
                otpReadComplete()
                if (!isButtonContinueAnimated) {
                    buttonContinue.animateEnableDisable().start()
                    isButtonContinueAnimated = true
                }
            }

            override fun onRemoved(otp: String) {
                otpData = otp
                otpRemoved()
                if (isButtonContinueAnimated) {
                    buttonContinue.animateEnableDisable().start()
                    isButtonContinueAnimated = false
                }
            }

        }
    }

    private fun getDataIntent() {
        isFromLoginRegister = intent.getBooleanExtra(STConstants.IS_FROM_LOGIN_REGISTER, false)
        registerRequest =
            intent.getSerializableExtra(STConstants.REGISTER_DATA) as STRegisterRequest?
        imageFile = intent.getSerializableExtra(STConstants.IMAGE_FILE) as File?
        facebookImageUrl = intent.getStringExtra(STConstants.FACEBOOK_IMAGE_URL)
    }

    private fun otpRemoved() {
        resetOtpCompleteAnimation()
        passwordResetUmbrellaLeft.animateResetFallDown().start()
        buttonContinue.background =
            STUtils.getDrawable(activityContext, R.drawable.button_selector_normal)
        buttonContinue.textColor(STUtils.getColor(activityContext, R.color.white))
        STUtils.setBookFont(activityContext, buttonContinue)
    }

    private fun otpReadComplete() {
        STUtils.hideSoftKeyboard(activityContext, window.decorView)
        buttonContinue.background =
            STUtils.getDrawable(activityContext, R.drawable.button_selector_enabled)
        buttonContinue.textColor(STUtils.getColor(activityContext, R.color.theme_color))
        STUtils.setBoldFont(activityContext, buttonContinue)
        startOtpCompleteAnimation()
    }

    private fun startOtpCompleteAnimation() {
        fallDownAnimation = passwordResetUmbrellaLeft.animateFallDown(
            toX = passwordResetUmbrellaRight.x,
            toY = passwordResetUmbrellaRight.y + 100,
            rotation = 60f,
            duration = 1200,
            toScale = 1.5f,
            startDelay = 200
        )
        fallDownAnimation?.start()
    }

    private fun resetOtpCompleteAnimation() {
        if (fallDownAnimation?.isRunning == true) {
            fallDownAnimation?.cancel()
            fallDownAnimation = null
        }
    }


    override fun processState(state: STOtpState) {
        when (state) {
            is STOtpState.Loading -> requestDidStart()
            is STOtpState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
            }
            is STOtpState.ValidateOtp -> {
                requestDidFinish()
                validateOtpResponse(state.validateOtpResponse)
            }
            is STOtpState.ResendOtp -> {
                requestDidFinish()
                STPreference.saveRegToken(activityContext, state.resendOtpResponse.data?.regToken)
                showToast(state.resendOtpResponse.message)
            }
        }
    }

    private fun validateOtpResponse(validateOtpResponse: STUserDataResponse) {
        if (STAPIConstants.SUCCESS_CODE == validateOtpResponse.statusCode) {
            if (isFromLoginRegister) {
                setUserData(validateOtpResponse.data)
            }
        }

    }

    private fun setUserData(userData: STUserData?) {
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_SIGN_UP_COMPLETION,
                "", ""
            )
        STPreference.saveRegToken(activityContext, userData?.regToken)
        STPreference.saveAccessToken(activityContext, userData?.accessToken)
        userData?.user?.let { user ->
            STPreference.setName(activityContext, user.name)
            STPreference.setEmail(activityContext, user.email)
            STPreference.setCorporateEmail(activityContext, user.corporateEmail)
            STPreference.setProfilePic(activityContext, user.picture)
            STPreference.setUserId(activityContext, user.id)
            STPreference.setUserLevel(activityContext, user.activeLevel)
        }
        selectDevice()
    }

    private fun selectDevice() {
        startActivityFinishAll(Intent(activityContext, STChooseDeviceActivity::class.java))
        /*startActivity(Intent(activityContext, STChooseDeviceActivity::class.java))
        finish()*/
    }

    override fun onViewModelReady() {
    }

    @OnClick(R.id.resendOtp)
    fun resendOtp() {
        val resendOtpRequest = STBaseRequest()
        resendOtpRequest.regToken = STPreference.getRegToken(activityContext)
        invokeIntent(STOtpIntent.ResendOtp(resendOtpRequest))
    }

    @OnClick(R.id.buttonContinue)
    fun validateOtp() {
        otpData?.let { otp ->
            if (otp.isNotEmpty() && otp.length == 4) {
                registerRequest?.let {
                    val map = HashMap<String, RequestBody>()
                    map["name"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.name ?: ""
                    )
                    map["phoneNumber"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.phoneNumber ?: ""
                    )
                    map["email"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.email ?: ""
                    )
                    map["password"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.password ?: ""
                    )
                    map["gender"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.gender.toString()
                    )
                    map["dob"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.dob ?: ""
                    )
                    map["countryId"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.countryId ?: ""
                    )
                    map["corporateEmail"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.corporateEmail ?: ""
                    )
                    map["referralCode"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.referralCode ?: ""
                    )
                    map["facebookId"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.facebookId ?: ""
                    )
                    map["instagramId"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        it.instagramId ?: ""
                    )
                    map["otp"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        otp
                    )
                    map["regToken"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        STPreference.getRegToken(activityContext) ?: ""
                    )
                    map["deviceId"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        STUtils.getDeviceId(activityContext)
                    )
                    map["deviceToken"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        STPreference.getDeviceId(activityContext) ?: ""
                    )
                    map["deviceType"] = RequestBody.create(
                        MediaType.parse("text/plain"),
                        STConstants.DEVICE_TYPE
                    )
                    var imagePart: MultipartBody.Part? = null
                    if (null != imageFile) {
                        imagePart = STUtils.prepareFilePart(imageFile!!)
                    } else {
                        map["picture"] = RequestBody.create(
                            MediaType.parse("text/plain"),
                            facebookImageUrl ?: ""
                        )
                    }
                    if (!BuildConfig.DEBUG)
                        STFireBaseAnalytics.trackEvent(
                            STFireBaseAnalyticsConstants.EVENT_SIGN_UP_OTP_SUBMISSION,
                            "", ""
                        )
                    invokeIntent(STOtpIntent.ValidateOtp(imagePart, map))
                }
            } else {
                showToast(activityContext.resources.getString(R.string.enter_valid_otp))
            }
        } ?: run {
            showToast(activityContext.resources.getString(R.string.enter_valid_otp))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        resetOtpCompleteAnimation()
    }
}
