package com.steppi.steppifitness.ui.splash

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.version.STVersionCheckResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.device_connection.STChooseDeviceActivity
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.ui.language_selection.STLanguageSelectionActivity
import com.steppi.steppifitness.ui.login.STLoginActivity
import com.steppi.steppifitness.ui.splash.mvi.STSplashController
import com.steppi.steppifitness.ui.splash.mvi.STSplashIntent
import com.steppi.steppifitness.ui.splash.mvi.STSplashState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_splash.*
// [Modified By HMSConvertor] import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import org.xms.f.dynamiclinks.ExtensionDynamicLinks

class STSplashActivity :
    STBaseViewModelActivity<STSplashIntent, STSplashState, STSplashController>(
        STSplashController::class.java
    ) {
    private var selectedLanguage: String? = null
    var version: String? = null
    private var rewardId: String? = null
    private var merchantId: String? = null
    private var challengeId: String? = null
    var challengeType: String? = null
    var type: String? = null
    var theme: String? = null
    var isPrivate: String? = null
    private var challengeJoinCode: String? = null
    private var endType: String? = null
    private var isFromDynamicLink: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initViews() {
        initDynamicLinks()
    }

    override fun onViewModelReady() {
//        invokeIntent(STSplashIntent.TimerIntent)
        facebookHashKey(this)
    }

    override fun processState(state: STSplashState) {
        when (state) {
            is STSplashState.Loading -> {
                requestDidStart()
            }
            is STSplashState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message ?: "")
            }
            is STSplashState.VersionCheck -> {
                requestDidFinish()
                versionCheckData(state.versionCheckResponse)
            }
            STSplashState.TimerState -> {
                STConstants.IS_SYNC_INITIALLY = true
                when {
                    STConstants.ACTIVE_LEVEL_OTP == STPreference.getUserLevel(activityContext) -> {
                        setLanguage(STPreference.getLanguageCode(activityContext)!!)
                        selectedLanguage?.let {
                            startActivity(Intent(applicationContext, STLoginActivity::class.java))
                            finish()
                        }
                    }
                    STConstants.ACTIVE_LEVEL_DEVICE_SELECTION == STPreference.getUserLevel(
                        activityContext
                    ) -> {
                        if (isFromDynamicLink) {
                            dynamicLinkRoutingScreen()
                        } else {
                            showDeviceSelection()
                        }

                    }
                    STConstants.ACTIVE_LEVEL_REGISTERED == STPreference.getUserLevel(activityContext) -> {
                        if (isFromDynamicLink) {
                            dynamicLinkRoutingScreen()

                        } else {
                            if (STPreference.getFitnessDevice(activityContext).isNullOrEmpty()) {
                                showDeviceSelection()
                            } else {
                                startActivityFinishAll(
                                    Intent(
                                        activityContext,
                                        STHomeActivity::class.java
                                    )
                                )
                            }
                        }
                    }
                    else -> {
                        startActivityFinishAll(
                            Intent(
                                activityContext,
                                STLanguageSelectionActivity::class.java
                            )
                        )// STOnBoardingActivity
                    }
                }
                finish()
            }
        }
    }

    private fun versionCheckData(versionCheckResponse: STVersionCheckResponse) {
        versionCheckResponse.data?.let {
            if (it.updateRequired!!) {
                appUpdateDialog(it.appLink)
            } else {
                animate()
            }
        } ?: kotlin.run {
            animate()
        }
    }

    private var isDialogVisible: Boolean? = false
    private fun appUpdateDialog(appLink: String?) {
        isDialogVisible = true
        val successDialog: Dialog = updateAppDialog {
            closeClick {
                dismissDialog()
                finish()
            }
            confirmClick {
                dismissDialog()
                try {
                    STUtils.openLink(this@STSplashActivity, appLink)
                } catch (e: ActivityNotFoundException) {
                    animate()
                    e.printStackTrace()
                }
            }
        }
        successDialog.setSize(activityContext)
        successDialog.setCancelable(false)
        successDialog.show()
    }

    private fun setLanguage(selectedLanguage: String) {
        this.selectedLanguage = selectedLanguage
        STPreference.saveLanguageCode(activityContext, selectedLanguage)
    }

    private fun showDeviceSelection() {
        val otpIntent = Intent(activityContext, STChooseDeviceActivity::class.java)
        startActivity(otpIntent)
    }

    override fun onResume() {
        logo.invisible()
        titleTv.invisible()
        if (BuildConfig.DEBUG) {
            animate()
        } else {
            versionCheck()
        }
        super.onResume()
    }

    private fun animate() {
        animateTogether(
            arrayListOf(
                logo.animateTranslateHorizontalAndZoom(0f, 1f, 1000f, 0f, 500),
                titleTv.animateTranslateHorizontalAndZoom(0f, 1f, -1000f, 0f, 500)
                /*, logoContainer.animateBounce(1f,3f,500, startDelay = 800)*/
            )
        ) {
            invokeIntent(STSplashIntent.TimerIntent)
        }
    }

    private fun versionCheck() {
//        val versionCheckRequest = STVersionCheckRequest()
//        val appVersion = STUtils.getVersionCode(activityContext).toString()
//        val deviceType = STConstants.DEVICE_TYPE
        invokeIntent(
            STSplashIntent.VersionCheck(
                STUtils.getVersionCode(activityContext).toString(),
                STConstants.DEVICE_TYPE
            )
        )
    }

    /**
     * init firebase Dynamic Links on gms device
     * and agconnect app linking on huawei hms device
     */
    private fun initDynamicLinks() {
//    FirebaseDynamicLinks.getInstance()
        ExtensionDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                val deepLink: Uri?
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                    Log.e("DeepLinkData", deepLink.toString())

                    deepLink?.let {
                        Log.e("DynamicLink", deepLink.toString())
                        rewardId = deepLink.getQueryParameter("reward_id")
                        merchantId = deepLink.getQueryParameter("merchant_id")
                        challengeId = deepLink.getQueryParameter("challenge_id")

                        challengeType = deepLink.getQueryParameter("challengeType")
                        type = deepLink.getQueryParameter("type")
                        theme = deepLink.getQueryParameter("theme")
                        isPrivate = deepLink.getQueryParameter("isPrivate")
                        challengeJoinCode = deepLink.getQueryParameter("challenge_join_code")
                        endType = deepLink.getQueryParameter("endType")
                        isFromDynamicLink = true
                    }
                }
            }
            .addOnFailureListener(this) { e ->
                Log.w("", "getDynamicLink:onFailure", e)
            }

    }

    private fun dynamicLinkRoutingScreen() {
        when {
            rewardId != null -> {
                // open reward page
                //https://dynamicsteppi.page.link/?link=https://steppi-app.com/?reward_id=1dc9ba4c-4485-4103-8be6-1bff78d89491&ibi=com.steppi.steppi&apn=com.steppi.steppifitness&afl=https://app.steppi.com/share-app-page
                openRewardDetail()
            }
            challengeId != null -> {
                // open challenge page
                //https://dynamicsteppi.page.link/?link=https://steppi-app.com/?challenge_id=e0ace320-5fcb-4d69-93b4-1f63a8eabed8&ibi=com.steppi.steppi&apn=com.steppi.steppifitness&afl=https://app.steppi.com/share-app-page
                openChallengeDetail()
            }
            else -> {
                startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
            }
        }
    }

    private fun openRewardDetail() {
        val intent = Intent(this, STHomeActivity::class.java)
        intent.putExtra(STConstants.IS_DEEP_LINKING_NOTIFICATION, true)
        intent.putExtra(STConstants.NOTIFICATION_REWARD_ID, rewardId)
        startActivityFinishAll(intent)
//        val container = Intent(activityContext, STContainerActivity::class.java)
//        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.details))
//        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_REWARDS_REDEEM)
//        container.putExtra(STConstants.NOTIFICATION_REWARD_ID, rewardId)
//        container.putExtra(STConstants.IS_NOTIFICATION, true)
//        startActivityFinishAll(container)
    }

    private fun openChallengeDetail() {
        val challengesData: STChallengesListData? = STChallengesListData()
        challengesData?.id = challengeId
        challengesData?.challengeType = challengeType
        challengesData?.type = type
        challengesData?.theme = theme
        challengesData?.joinCode = challengeJoinCode
        challengesData?.isPrivate = isPrivate == "true"
        val intent = Intent(this, STHomeActivity::class.java)
        intent.putExtra(STConstants.IS_DEEP_LINKING_NOTIFICATION, true)
        intent.putExtra(STConstants.NOTIFICATION_CHALLENGE_ID, challengeId)
        intent.putExtra(STConstants.NOTIFICATION_CHALLENGE_TYPE, challengeType)
        intent.putExtra(STConstants.CHALLENGES_DATA, challengesData)
        isPrivate?.let { isPrivate ->
            intent.putExtra(STConstants.CHALLENGES_ISPRIVATE_NOTIFICATION, isPrivate)
        }
        challengeJoinCode?.let { challengeJoinCode ->
            intent.putExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE_NOTIFICATION, challengeJoinCode)
        }
        startActivityFinishAll(intent)
//        val container = Intent(activityContext, STContainerActivity::class.java)
//        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.challenge_details))
//        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_CHALLENGE_DETAILS)
//        container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
//        container.putExtra(STConstants.CHALLENGES_TYPE, challengeType)
//        startActivityFinishAll(container)
    }
}
