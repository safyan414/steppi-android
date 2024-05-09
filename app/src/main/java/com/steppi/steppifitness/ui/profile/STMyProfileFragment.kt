package com.steppi.steppifitness.ui.profile

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat
import butterknife.OnClick
import com.github.florent37.viewtooltip.ViewTooltip
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.network.response.user.STUserData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.device_connection.STChooseDeviceActivity
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STMyProfileFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ) {
    private var deviceSelected: STDeviceData? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_profile
    }

    override fun initViews() {
//        val space = getToolTipSpace(activityContext, 2.3)
        versionNumber.text = "${getString(R.string.version_)} ${BuildConfig.VERSION_NAME}"
        info?.setOnClickListener {
            ViewTooltip
                .on(this, info)
                .autoHide(true, 3000)
                .clickToHide(true)
                .align(ViewTooltip.ALIGN.CENTER)
                .position(ViewTooltip.Position.TOP)
                .text(resources.getString(R.string.referral_code_hint))
                .textColor(STUtils.getColor(activityContext, R.color.theme_color))
                .color(STUtils.getColor(activityContext, R.color.text_white))
                .corner(10)
                .arrowWidth(15)
                .arrowHeight(15)
                .distanceWithView(0)
                .show()
        }
    }

    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Loading -> {
                requestDidStart()
            }
            is STProfileState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STProfileState.GetProfile -> {
                requestDidFinish()
                setUserDataValues(state.userDataResponse.data)
            }
            is STProfileState.LogoutUser -> {
                requestDidFinish()
                logout()
            }
        }
    }

    private fun setUserDataValues(userData: STUserData?) {
        userData?.let {
            it.user?.let { user ->
                userImage.load(activityContext, user.picture)
                STUtils.setValueToView(
                    totalSteps, NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(user.lifetimeSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )/*NumberFormat.getNumberInstance(Locale.US)
                        .format(user.lifetimeSteps!!.toInt())*/
                )
                STUtils.setValueToView(
                    totalSavings, NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(user.totalSavings?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )/*NumberFormat.getNumberInstance(Locale.US)
                        .format(user.totalSavings!!.toInt())*/
                )
                STUtils.setValueToView(userName, user.name)
                STUtils.setValueToView(email, user.email)
//                STUtils.setValueToView(referralCodeText, user.referralCode?.referralCode)
                if (user.corporateUser != null && user.corporateUser?.isVerified!!) {
                    user.corporateUser?.let { it ->
                        STUtils.setValueToView(viewCorporate, it.corporation?.name)
                        it.corporation?.logo?.let { corporationLogo ->
                            corporateIcon.load(activityContext, corporationLogo)
                        }
                    }
                } else {
                    corporateIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            activityContext,
                            R.drawable.corporate_icon
                        )
                    )
                    STUtils.setValueToView(viewCorporate, getString(R.string.corporate_label))
                }
                STPreference.setCorporateEmail(activityContext, user.corporateEmail)

                STPreference.getFitnessDevice(activityContext)?.let {
                    user.device?.let { device ->
                        connectedVia.visible()

                        // Once the Huawei Health Device is added in the backend api device list, this logic can be streamlined
                        if (device.id == "1" && STPreference.getFitnessDevice(activityContext) == STConstants.HUAWEI_DEVICE_CODE) {
                            STUtils.setValueToView(connectedVia, "Huawei Health")
                        } else {
                            STUtils.setValueToView(connectedVia, device.name)
                        }
                        deviceSelected = device
                    } ?: kotlin.run {
                        connectedVia.gone()
                    }
                } ?: kotlin.run {
                    connectedVia.gone()
                }
            }
        }
        topLayout.visible()
        updateNotificationCount()
    }

    override fun onResume() {
        super.onResume()
        if (STConstants.PROFILE_UPDATE) {
            STConstants.PROFILE_UPDATE = false
            userImage.load(activityContext, STPreference.getProfilePic(activityContext))
            // update bottom navigation icon when profile update
            (activity as STHomeActivity).loadProfileImage()
            invokeIntent(STProfileIntent.GetProfile)
        }
    }

    override fun onViewModelReady() {
        invokeIntent(STProfileIntent.GetProfile)
    }

    @OnClick(R.id.connectedViaLayout)
    fun editDeviceConnection() {
        val countryIntent = Intent(activityContext, STChooseDeviceActivity::class.java)
        countryIntent.putExtra(STConstants.CHOOSE_DEVICE_CONNECTION_DATA, deviceSelected)
        countryIntent.putExtra(STConstants.FROM_MY_PROFILE, true)
        startActivityForResult(
            countryIntent,
            STConstants.REQUEST_CODE_DEVICE_CONNECTION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_DEVICE_CONNECTION == requestCode) {
                data?.let {
                    deviceSelected =
                        it.getSerializableExtra(STConstants.CHOOSE_DEVICE_CONNECTION_DATA) as STDeviceData
                    deviceSelected?.let { deviceData ->
                        connectedVia.visible()
                        STUtils.setValueToView(connectedVia, deviceData.name)
                    } ?: kotlin.run {
                        connectedVia.gone()
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @OnClick(R.id.viewCorporateLayout)
    fun viewCorporate() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.corporate_label))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_VIEW_CORPORATE)
        startActivity(container)
    }

    @OnClick(R.id.settingsLayout)
    fun viewSettings() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.settings))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_SETTINGS)
        startActivity(container)
    }

    @OnClick(R.id.redeemDetailsLayout)
    fun redeemDetails() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(
            STConstants.FRAGMENT_NAME,
            resources.getString(R.string.vouchers_redeemed)
        )
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_REDEEM_DETAILS)
        startActivity(container)
    }

    @OnClick(R.id.notificationLayout)
    fun notification() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.notifications))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_NOTIFICATION)
        startActivity(container)
    }

    @OnClick(R.id.logOutLayout)
    fun logOutFromView() {
        showOkayCancelDialog {
            setMessage(activityContext.resources.getString(R.string.logout_message))
            setOkayText(activityContext.resources.getString(R.string.ok))
            setCancelText(activityContext.resources.getString(R.string.cancel))
            isLogout()
            okayClickListener {
                invokeIntent(STProfileIntent.UserLogout)
                dismissDialog()
            }
            cancelClickListener { dismissDialog() }
        }.show()
    }

    @OnClick(R.id.shareAppLayout)
    fun shareApp() {
        STUtils.shareApp(activityContext, BuildConfig.API_URL + STConstants.SHARE_APP_URL)
    }

    @OnClick(R.id.editProfile)
    fun editProfile() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.edit_profile))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_EDIT_PROFILE)
        startActivity(container)
    }

    //    @OnClick(R.id.copyReferralCode)
//    fun copyReferralCode() {
//        STUtils.getValueFromView(referralCodeText)?.let {
//            activityContext.copyToClipBoard(
//                STConstants.REFERRAL_CODE,
//                STUtils.getValueFromView(referralCodeText)!!
//            )
//            showToast(getString(R.string.referral_code_copied))
//        }
//    }
    fun updateNotificationCount() {
        if (STConstants.NOTIFICATION_COUNT > 0) {
            notificationCount.text = STConstants.NOTIFICATION_COUNT.toString()
        } else {
            notificationCount.text = ""
        }
    }
}
