package com.steppi.steppifitness.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Handler
import butterknife.OnClick
import butterknife.OnTextChanged
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.network.request.STForgotPasswordRequest
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.country.STCountrySelectionActivity
import com.steppi.steppifitness.ui.login.mvi.STLogInController
import com.steppi.steppifitness.ui.login.mvi.STLoginIntent
import com.steppi.steppifitness.ui.login.mvi.STLoginState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.header_layout.*

class STForgotPasswordActivity :
    STBaseViewModelActivity<STLoginIntent, STLoginState, STLogInController>(STLogInController::class.java) {
    private var selectedCountry: STCountryData? = null
    private var isPhoneAnimated = false
    private var isButtonGoAnimated = false

    override fun getLayoutId(): Int {
        return R.layout.activity_forgot_password
    }

    override fun initViews() {
        setHeaderName(activityContext.resources.getString(R.string.forgot_password_title))
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

    @OnTextChanged(R.id.userMobile)
    fun mobileNumberTextChange(text: CharSequence) {
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

    private fun validateAllData() {
        if (STUtils.getValueFromView(userMobile).isNullOrEmpty()) {
            btnGo.background =
                STUtils.getDrawable(activityContext, R.drawable.button_selector_normal)
            btnGo.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setBookFont(activityContext, btnGo)
            if (isButtonGoAnimated) {
                btnGo.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isButtonGoAnimated = false
            }
        } else {
            btnGo.background =
                STUtils.getDrawable(activityContext, R.drawable.button_selector_enabled)
            btnGo.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setBoldFont(activityContext, btnGo)
            if (!isButtonGoAnimated) {
                btnGo.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isButtonGoAnimated = true
            }
        }
    }

    override fun onViewModelReady() {

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

    override fun processState(state: STLoginState) {
        when (state) {
            is STLoginState.Loading -> {
                requestDidStart()
            }
            is STLoginState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
            }
            is STLoginState.ForgotPassword -> {
                requestDidFinish()
                showToast(state.forgotPasswordResponse.message)
                Handler().postDelayed({
                    activityContext.finish()
                }, 1500)
            }
        }
    }

    @OnClick(R.id.btnGo)
    fun btnGo() {
        validateAndSubmit()
    }

    private fun validateAndSubmit() {
        if (STUtils.getValueFromView(userMobile).isNullOrEmpty()) {
            showToast(getString(R.string.validation_mobile_number))
            userMobile.requestFocus()
            return
        }

        val zero = userMobile.text.toString().substring(0,1)
        var number = ""
       if (zero == "0") {
           number = userMobile.text.toString().removeRange(0,1)
        }
        val forgotPasswordRequest = STForgotPasswordRequest()
        forgotPasswordRequest.phoneNumber =
            STUtils.getValueFromView(mobileCode).plus(number)
        invokeIntent(STLoginIntent.ForgotPassword(forgotPasswordRequest))
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
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}