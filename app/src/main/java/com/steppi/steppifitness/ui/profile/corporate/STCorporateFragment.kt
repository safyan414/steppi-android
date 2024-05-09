package com.steppi.steppifitness.ui.profile.corporate

import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.request.corporate.STJoinCorporateUserRequest
import com.steppi.steppifitness.network.response.corporate.STCorporateUserDataResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.corporate.mvi.STCorporateController
import com.steppi.steppifitness.ui.profile.corporate.mvi.STCorporateIntent
import com.steppi.steppifitness.ui.profile.corporate.mvi.STCorporateState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_corporate.*

class STCorporateFragment :
    STBaseViewModelFragment<STCorporateIntent, STCorporateState, STCorporateController>(
        STCorporateController::class.java
    ) {

    override fun getLayoutId(): Int {
        return R.layout.fragment_corporate
    }

    override fun initViews() {
        corporateEmail.textChangeObserver {
            validateAllData()
        }
    }

    private fun validateAllData() {
        if (STUtils.getValueFromView(corporateEmail).isNullOrEmpty()) {
            joinCorporate.background =
                STUtils.getDrawable(activityContext, R.drawable.light_blue_bg)
            joinCorporate.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        } else {
            joinCorporate.background =
                STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
            joinCorporate.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        }
    }

    override fun processState(state: STCorporateState) {
        when (state) {
            is STCorporateState.Loading -> {
                requestDidStart()
            }
            is STCorporateState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STCorporateState.GetCorporateUser -> {
                requestDidFinish()
                setCorporateData(state.getCorporateUserResponse)
            }
            is STCorporateState.JoinCorporation -> {
                STConstants.PROFILE_UPDATE = true
                requestDidFinish()
                showSuccessDialog(state.joinCorporationResponse)
                STPreference.setCorporateEmail(
                    activityContext,
                    STUtils.getValueFromView(corporateEmail)
                )
            }
            is STCorporateState.LeaveCorporation -> {
                STConstants.PROFILE_UPDATE = true
                requestDidFinish()
                STPreference.setCorporateEmail(activityContext, null)
                //showToast(state.leaveCorporationResponse.message)
                activityContext.finish()
            }
            is STCorporateState.ResendCorporateEmail -> {
                requestDidFinish()
                showSuccessDialog(null)
                //showToast(state.resendCorporateEmail.message)
            }
            is STCorporateState.TimerState -> {
                invokeIntent(STCorporateIntent.GetCorporateUser)
            }
        }
    }

    private fun showSuccessDialog(corporateUserResponse: STCorporateUserDataResponse?) {
        val dialog = Dialog(activityContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.update_profile_success_dialog)
        dialog.show()
        val lp = WindowManager.LayoutParams()
        val message =
            activityContext.resources.getString(R.string.email_sent_message_corporate)
        STUtils.setValueToView(dialog.findViewById(R.id.successMessage), message)
        dialog.findViewById<View>(R.id.okay).visible()
        dialog.findViewById<View>(R.id.okay).setOnClickListener {
            dialog.dismiss()
            corporateUserResponse?.let { res -> setCorporateData(res) }
        }
        lp.copyFrom(dialog.window?.attributes)
        lp.width = STUtils.getDeviceWidth(activityContext)
        lp.height = STUtils.getDeviceHeight(activityContext)
        dialog.window?.attributes = lp
    }

    private fun setCorporateData(corporateUserResponse: STCorporateUserDataResponse) {
        topLayout.visible()
        corporateUserResponse.data?.corporateUser?.let {
            if (it.isVerified) {
                joinMessage.visible()
                joinMessage.text =
                    getText(R.string.join_your_corporate_challenge_and_challenge_your_colleague_teams)
                corporateReviewMessage.gone()
                resendEmail.gone()
                infoIcon.visible()
                removeCorporateAccount.visible()
                infoIcon.setImageResource(R.drawable.tick_country)

                it.corporation?.logo?.let { corporationLogo ->
                    corporateLogo.load(activityContext, corporationLogo)
                    logo.load(activityContext, corporationLogo)
                    logo.visible()
                    corporateLogoLayout.gone()
                } ?: kotlin.run {
                    logo.gone()
                    corporateLogoLayout.visible()
                    corporateLogo.setImageResource(R.drawable.corporate_account_icon)
                }
                it.corporation?.cover?.let { corporationCoverPic ->
                    steppiLogo.visibility = View.VISIBLE

                    if (corporationCoverPic.isNotEmpty()) {
                        coverImage.load(activityContext, corporationCoverPic)
                        steppiLogo.visibility = View.GONE
                    }

                } ?: kotlin.run {
                    steppiLogo.visibility = View.VISIBLE
                }
                STUtils.setImageSize(activityContext, coverImage, 0)
            } else {
                corporateReviewMessage.visible()
                resendEmail.visible()
                infoIcon.visible()
                joinMessage.gone()
                infoIcon.setImageResource(R.drawable.info_icon_red)
                removeCorporateAccount.visible()
                removeCorporateAccount.text =
                    activityContext.resources.getString(R.string.leave_corporate_account)
                invokeIntent(STCorporateIntent.TimerIntent)
            }
            corporateEmail.isEnabled = false
            //STUtils.setValueToView(corporateEmail, it.corporation?.email)
            STUtils.setValueToView(corporateEmail, STPreference.getCorporateEmail(activityContext))
            joinCorporate.gone()
        } ?: run {
            corporateEmail.isEnabled = true
            joinCorporate.visible()
            infoIcon.invisible()
            removeCorporateAccount.gone()
        }
    }

    override fun onViewModelReady() {
        requestDidStart()
        invokeIntent(STCorporateIntent.GetCorporateUser)
    }

    @OnClick(R.id.resendEmail)
    fun resendEmail() {
        invokeIntent(STCorporateIntent.ResendCorporateEmail)
    }

    @OnClick(R.id.removeCorporateAccount)
    fun removeCorporateAccount() {
        showOkayCancelDialog {
            setMessage(activityContext.resources.getString(R.string.corporate_leave_message))
            setOkayText(activityContext.resources.getString(R.string.ok))
            setCancelText(activityContext.resources.getString(R.string.cancel))
            okayClickListener {
                dismissDialog()
                invokeIntent(STCorporateIntent.LeaveCorporation)
            }
            cancelClickListener { dismissDialog() }
        }.show()

    }

    @OnClick(R.id.joinCorporate)
    fun joinCorporateAccount() {
        if (null == STUtils.getValueFromView(corporateEmail)) {
            return
        }
        if (!STUtils.emailValidator(STUtils.getValueFromView(corporateEmail)!!)) {
            showToast(getString(R.string.invalid_organization_email))
            return
        }
        val joinCorporationRequest = STJoinCorporateUserRequest()
        joinCorporationRequest.corporateEmail = STUtils.getValueFromView(corporateEmail)
        invokeIntent(STCorporateIntent.JoinCorporation(joinCorporationRequest))
    }
}
