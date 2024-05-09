package com.steppi.steppifitness.ui.profile.mobile

import android.app.Activity
import android.content.Intent
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.response.user.STUpdateMobileResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.country.STCountrySelectionActivity
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_edit_mobile.*

class STEditMobileFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ) {
    private var selectedCountry: STCountryData? = null

    private var isPhoneAnimated = false
    private var isButtonNextAnimated = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_edit_mobile
    }

    override fun initViews() {
        userMobile.textChangeObserver {
            if (it.trim().isNotEmpty()) {
                mobileNumberIcon.setImageResource(R.drawable.mobile_icon_active)
                if (!isPhoneAnimated) {
                    mobileNumberIcon.animateEnableDisable().start()
                    isPhoneAnimated = true
                }
            } else {
                mobileNumberIcon.setImageResource(R.drawable.mobile_icon)
                if (isPhoneAnimated) {
                    mobileNumberIcon.animateEnableDisable().start()
                    isPhoneAnimated = false
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
        if (STUtils.getValueFromView(userMobile).isNullOrEmpty()) {
            btnNext.background = STUtils.getDrawable(activityContext, R.drawable.button_bg_normal)
            btnNext.setTextColor(STUtils.getColor(activityContext, R.color.white))
            STUtils.setLightFont(activityContext, btnNext)
            if (isButtonNextAnimated) {
                btnNext.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isButtonNextAnimated = false
            }
        } else {
            btnNext.background = STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
            btnNext.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setBoldFont(activityContext, btnNext)
            if (!isButtonNextAnimated) {
                btnNext.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isButtonNextAnimated = true
            }
        }
    }

    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Loading -> requestDidStart()
            is STProfileState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STProfileState.UpdateMobile -> {
                requestDidFinish()
                updateMobile(state.updateMobileResponse)
            }
        }
    }

    private fun updateMobile(updateMobileResponse: STUpdateMobileResponse) {
        if (STAPIConstants.SUCCESS_CODE == updateMobileResponse.statusCode) {
            showToast(updateMobileResponse.message)
            STPreference.saveVerifyToken(activityContext, updateMobileResponse.data?.verToken)
            goToOtp()
        }
    }

    private fun goToOtp() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(
            STConstants.FRAGMENT_NAME,
            resources.getString(R.string.mobile_confirmation)
        )
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_VALIDATE_OTP)
        startActivity(container)
        activityContext.finish()
    }

    override fun onViewModelReady() {
    }

    @OnClick(R.id.btnNext)
    fun btnNext() {
        if (null == STUtils.getValueFromView(userMobile)) {
            return
        }
        val updateMobileRequest = STUser()
        updateMobileRequest.phoneNumber =
            STUtils.getValueFromView(mobileCode).plus(STUtils.getValueFromView(userMobile))
        invokeIntent(STProfileIntent.UpdateMobile(updateMobileRequest))
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_COUNTRY == requestCode) {
                data?.let {
                    selectedCountry =
                        it.getSerializableExtra(STConstants.COUNTRY_DATA) as STCountryData
                    STUtils.setValueToView(mobileCode, selectedCountry?.phoneCode)
                    flag.load(activityContext, selectedCountry?.flag)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
