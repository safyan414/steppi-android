package com.steppi.steppifitness.ui.profile.otp

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.request.otp.STValidateOtpRequest
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import com.steppi.steppifitness.views.OtpView
import kotlinx.android.synthetic.main.fragment_otp.*

class STOtpFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ) {
    var otpData: String? = ""

    private var isButtonGoAnimated = false

    override fun getLayoutId(): Int {

        return R.layout.fragment_otp
    }

    override fun initViews() {
        otp.callback = object : OtpView.CallBack {
            override fun onComplete(otp: String) {
                otpData = otp
                otpReadComplete()
                if (!isButtonGoAnimated) {
                    buttonContinue.animateEnableDisable().start()
                    isButtonGoAnimated = true
                }
            }

            override fun onRemoved(otp: String) {
                otpData = otp
                otpRemoved()
                if (isButtonGoAnimated) {
                    buttonContinue.animateEnableDisable().start()
                    isButtonGoAnimated = false
                }
            }

        }
    }

    override fun onViewModelReady() {
    }

    private fun otpRemoved() {
        passwordResetUmbrellaRight.invisible()
        passwordResetUmbrellaLeft.visible()
        buttonContinue.background =
            STUtils.getDrawable(activityContext, R.drawable.button_bg_normal)
        buttonContinue.textColor(STUtils.getColor(activityContext, R.color.white))
        STUtils.setBookFont(activityContext, buttonContinue)
    }

    private fun otpReadComplete() {
        passwordResetUmbrellaRight.visible()
        passwordResetUmbrellaLeft.invisible()
        buttonContinue.background =
            STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
        buttonContinue.textColor(STUtils.getColor(activityContext, R.color.theme_color))
        STUtils.setBoldFont(activityContext, buttonContinue)
    }

    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Loading -> requestDidStart()
            is STProfileState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STProfileState.ValidateOtp -> {
                requestDidFinish()
                validateOtpResponse(state.validateOtpResponse)
            }
            is STProfileState.ResendOtp -> {
                requestDidFinish()
                showToast(state.resendOtpResponse.message)
                STPreference.saveVerifyToken(
                    activityContext,
                    state.resendOtpResponse.data?.verToken
                )
            }
        }
    }

    private fun validateOtpResponse(validateOtpResponse: STBaseResponse) {
        if (STAPIConstants.SUCCESS_CODE == validateOtpResponse.statusCode) {
            showToast(validateOtpResponse.message)
            STConstants.PROFILE_UPDATE = true
            showProfileSuccessDialog()
        }
    }

    @OnClick(R.id.buttonContinue)
    fun validateOtp() {
        otpData?.let { otp ->
            if (otp.isNotEmpty() && otp.length == 4) {
                val validateOtpRequest = STValidateOtpRequest()
                validateOtpRequest.verToken = STPreference.getVerifyToken(activityContext)
                validateOtpRequest.otp = otp
                invokeIntent(STProfileIntent.ValidateOtp(validateOtpRequest))
            } else {
                showToast(activityContext.resources.getString(R.string.enter_valid_otp))
            }
        } ?: run {
            showToast(activityContext.resources.getString(R.string.enter_valid_otp))
        }
    }

    private fun showProfileSuccessDialog() {
        val updateMobileDialog = Dialog(activityContext)
        updateMobileDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        updateMobileDialog.setContentView(R.layout.update_mobile_password_success_dialog)
        updateMobileDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        updateMobileDialog.setCancelable(false)
        val wm =
            activityContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager // for activity use context instead of getActivity()
        val display = wm.defaultDisplay // getting the screen size of device
        val size = Point()
        display.getSize(size)
        val width = size.x - 20  // Set your heights
        val height = size.y - 80 // set your widths
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(updateMobileDialog.window?.attributes)
        lp.width = width
        lp.height = height
        updateMobileDialog.window?.attributes = lp
        updateMobileDialog.show()
        updateMobileDialog.findViewById<ImageView>(R.id.successTick)
            .setImageResource(R.drawable.edit_phone_success)
        STUtils.setValueToView(
            updateMobileDialog.findViewById(R.id.successMessage),
            activityContext.resources.getString(R.string.mobile_updated_message)
        )
        val okButton = updateMobileDialog.findViewById<TextView>(R.id.successOk)
        okButton.visible()
        okButton.setOnClickListener {
            updateMobileDialog.dismiss()
            activityContext.finish()
        }
        Handler().postDelayed({
            updateMobileDialog.dismiss()
            activityContext.finish()
        }, 3000)
    }

    @OnClick(R.id.resendOtp)
    fun resendOtp() {
        val validateOtpRequest = STValidateOtpRequest()
        validateOtpRequest.verToken = STPreference.getVerifyToken(activityContext)
        invokeIntent(STProfileIntent.ResendOtp(validateOtpRequest))
    }
}
