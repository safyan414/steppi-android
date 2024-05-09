package com.steppi.steppifitness.ui.rewards

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.provider.Settings
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.NonNull
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.network.request.reward.STRedeemRewardRequest
import com.steppi.steppifitness.network.response.category.RedeemRewardData
import com.steppi.steppifitness.network.response.category.RewardDetails
import com.steppi.steppifitness.network.response.category.STMerchantStoresListData
import com.steppi.steppifitness.network.response.category.STStoresRewardsListData
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsController
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsIntent
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsState
import com.steppi.steppifitness.utils.*
import com.steppi.steppifitness.utils.STUtils.navigate
import com.steppi.steppifitness.utils.STUtils.replaceArabicNumbers
import kotlinx.android.synthetic.main.fragment_reward_details.*
import kotlinx.android.synthetic.main.fragment_reward_redeem.*
import kotlinx.android.synthetic.main.fragment_reward_redeem.locationSpinner
import kotlinx.android.synthetic.main.fragment_reward_redeem.logo
import kotlinx.android.synthetic.main.fragment_reward_redeem.phoneNumber
import kotlinx.android.synthetic.main.fragment_reward_redeem.phoneNumberLayout
import kotlinx.android.synthetic.main.fragment_reward_redeem.shopNumber
import kotlinx.android.synthetic.main.layout_after_redeem.*
import kotlinx.android.synthetic.main.layout_before_redeem.*
import java.util.*

// [Modified By HMSConvertor] import com.google.android.gms.location.FusedLocationProviderClient
import org.xms.g.location.FusedLocationProviderClient
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationCallback
import org.xms.g.location.LocationCallback
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationRequest
import org.xms.g.location.LocationRequest
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationResult
import org.xms.g.location.LocationResult
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationSettingsStatusCodes
import org.xms.g.location.LocationSettingsStatusCodes
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationServices
import org.xms.g.location.LocationServices
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationSettingsRequest
import org.xms.g.location.LocationSettingsRequest
// [Modified By HMSConvertor] import com.google.android.gms.location.LocationSettingsResponse
import org.xms.g.location.LocationSettingsResponse

class STRewardRedeemFragment :
    STBaseViewModelFragment<STRewardsIntent, STRewardsState, STRewardsController>(
        STRewardsController::class.java
    ) {
    private var selectedMerchantRewardsData: STStoresRewardsListData? = null
    private var selectedMerchantStore: STMerchantStoresListData? = null
    private var merchantData: STMerchantData? = null
    private var rewardDetail: RewardDetails? = null
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var isFromNotification: Boolean? = null
    private var notificationRewardId: String? = null
    private var notificationRewardType: String? = null
    private var isDigitalRewardSuccess: Boolean = false
    private val permissionId = 42
    var isLocationInIt: Boolean? = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_reward_redeem
    }

    override fun initViews() {
        initLocationData()
        initData()
    }

    private fun initData() {
        isFromNotification = arguments?.getBoolean(STConstants.IS_NOTIFICATION, false)
        if (isFromNotification!!) {
            arguments?.getString(STConstants.NOTIFICATION_REWARD_ID)?.let {
                notificationRewardId = it
            }
            arguments?.getString(STConstants.NOTIFICATION_REWARD_TYPE)?.let {
                notificationRewardType = it
            }
        } else {
            merchantData = arguments?.getSerializable(STConstants.MERCHANT_LIST) as? STMerchantData
            selectedMerchantRewardsData =
                arguments?.getSerializable(STConstants.SELECTED_REWARD) as? STStoresRewardsListData
            selectedMerchantStore =
                arguments?.getSerializable(STConstants.SELECTED_MERCHANT_STORE) as? STMerchantStoresListData
        }
    }

    private fun initLocationData() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
    }

    override fun processState(state: STRewardsState) {
        when (state) {
            is STRewardsState.Loading -> requestDidStart()
            is STRewardsState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STRewardsState.GetDigitalRewardDetails -> {
                requestDidFinish()
                rewardDetail = state.rewardDetails.data
                phoneNumberLayout.gone()
                setRewardDetails()
            }
            is STRewardsState.GetInStoreRewardDetails -> {
                requestDidFinish()
                rewardDetail = state.rewardDetails.data
                setRewardDetails()
            }
            is STRewardsState.RedeemReward -> {
                requestDidFinish()
                rewardDetail?.let {
                    if (rewardDetail?.rewardType == STConstants.IN_STORE) {
                        successDialog(state.redeemRewardResponse.data!!)
                        merchantData?.let { merchantData ->
                            if (!BuildConfig.DEBUG)
                                trackTheEvent(
                                    STFireBaseAnalyticsConstants.EVENT_IN_STORE_VENDOR_REWARD_REDEMPTION,
                                    it, merchantData
                                )
                        }
                    } else {
                        merchantData?.let { merchantData ->
                            if (!BuildConfig.DEBUG)
                                trackTheEvent(
                                    STFireBaseAnalyticsConstants.EVENT_DIGITAL_VENDOR_REWARD_REDEMPTION,
                                    it, merchantData
                                )
                        }
                        setDigitalRewardSuccessUI(state.redeemRewardResponse.data!!)
                        invokeIntent(
                            STRewardsIntent.GetDigitalRewardDetails(
                                rewardDetail?.id!!
                            )
                        )
                    }
                }
            }
        }
    }

    private fun setDigitalRewardSuccessUI(redeemRewardResponse: RedeemRewardData?) {
        redeemRewardResponse?.let {
            isDigitalRewardSuccess = true
            webLinkBeforeRedeem.visibility = GONE
            redeem.visibility = GONE
            inStoreRewardExpireDate.visibility = GONE
            phoneNumberLayout.gone()
            redeemCode.visibility = VISIBLE
            redeemSuccessDetail.visibility = VISIBLE
            webLinkAfterRedeem.visibility = VISIBLE


            redeemedDate.text = getString(R.string.redeemed_in).plus(
                STUtils.getFormattedDate(
                    Calendar.getInstance(),
                    "dd-MMM-yyyy"
                )
            )
            expireDate.text =
                getString(R.string.expire).plus(STUtils.formatServerDate(it.reward.expireOn))
            referralCodeText.text = it.redeemCode
        }
    }

    @OnClick(R.id.copyReferralCode)
    fun copyRedeemCode() {
        STUtils.getValueFromView(referralCodeText)?.let {
            activityContext.copyToClipBoard(
                STConstants.REFERRAL_CODE,
                STUtils.getValueFromView(referralCodeText)!!
            )
            showToast(getString(R.string.referral_code_copied))
        }
    }

    @OnClick(R.id.locationSpinner)
    fun locationSpinnerSelection() {
        if (isFromNotification!!) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.location))
            container.putExtra(STConstants.NOTIFICATION_REWARD_ID, notificationRewardId)
            container.putExtra(STConstants.MERCHANT_LIST, merchantData)
            container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_LOCATION_SELECTION)
            container.putExtra(STConstants.SELECTED_MERCHANT_STORE, selectedMerchantStore)
            container.putExtra(STConstants.IS_NOTIFICATION, true)
            startActivityForResult(container, STConstants.REQUEST_CODE_STORE_LOCATION)
        }
    }

    override fun onViewModelReady() {
        requestNewLocationData()
        getLastLocation()
        if (isFromNotification!!) {
            notificationRewardId?.let {
                invokeIntent(STRewardsIntent.GetDigitalRewardDetails(it))
            }
        } else {
            selectedMerchantRewardsData?.let { reward ->
                /*if (selectedMerchantRewardsData?.rewardType == STConstants.IN_STORE) {
                webLinkBeforeRedeem.visibility = GONE
                locationSpinner.visibility = VISIBLE
                shopNumber.visibility = VISIBLE

            } else {
                webLinkBeforeRedeem.visibility = VISIBLE
                locationSpinner.visibility = GONE
                shopNumber.visibility = GONE
                setDigitalRewardSuccessUI(arguments?.getSerializable(STConstants.DIGITAL_REWARD_REDEEM_DATA) as? RedeemRewardData)
            }*/

                // selectedMerchantStore?.id?.let {
                if (reward.rewardType == STConstants.IN_STORE && !selectedMerchantStore?.id.isNullOrEmpty()) {
                    inStoreLayout()
                    invokeIntent(
                        STRewardsIntent.GetInStoreRewardDetails(
                            reward.id!!,
                            selectedMerchantStore?.id!!
                        )
                    )
                } else {
                    digitalLayout()
                    invokeIntent(STRewardsIntent.GetDigitalRewardDetails(selectedMerchantRewardsData?.id!!))
                    setDigitalRewardSuccessUI(arguments?.getSerializable(STConstants.DIGITAL_REWARD_REDEEM_DATA) as? RedeemRewardData)
                }
                /*} ?: kotlin.run {
                digitalLayout()
                invokeIntent(STRewardsIntent.GetDigitalRewardDetails(selectedMerchantRewardsData?.id!!))
            }*/

                /*selectedMerchantRewardsData?.expireOn?.let {
                    inStoreRewardExpireDate.visible()
                    inStoreRewardExpireDate.text = getString(R.string.expire)
                        .plus(STUtils.formatServerDate(it))
                } ?: kotlin.run {
                    inStoreRewardExpireDate.gone()
                }*/

                /*selectedMerchantStore?.let { selectedMerchantStoreData ->
                phoneNumber.visible()
                locationSpinner.visible()
                shopNumber.visible()

            } ?: kotlin.run {
                phoneNumber.gone()
                locationSpinner.gone()
                shopNumber.gone()
            }*/
            }
        }
    }

    private fun inStoreLayout() {
        webLinkBeforeRedeem.gone()
        phoneNumberLayout.visible()
        locationSpinner.visible()
        shopNumber.visible()
    }

    private fun digitalLayout() {
        webLinkBeforeRedeem.visible()
        phoneNumberLayout.gone()
        locationSpinner.gone()
        shopNumber.gone()
    }

    private fun setRewardDetails() {
        rewardDetailsLayout.visible()
        rewardDetail?.let {
            merchantData = it.merchant
            merchantData?.let { merchantData ->
                if (it.rewardType == STConstants.IN_STORE) {
                    if (!BuildConfig.DEBUG)
                        trackTheEvent(
                            STFireBaseAnalyticsConstants.EVENT_IN_STORE_VENDOR_REWARD_VISIT,
                            it, merchantData
                        )
                } else {
                    if (!BuildConfig.DEBUG)
                        trackTheEvent(
                            STFireBaseAnalyticsConstants.EVENT_DIGITAL_VENDOR_REWARD_VISIT,
                            it, merchantData
                        )
                }
            }
            redeemLayout.visibility = VISIBLE
            logo.loadImage(activityContext, merchantData?.logo)
            name.text = merchantData?.name
            it.description.let { rewardDetailsDescription ->
                if (rewardDetailsDescription == "") {
                    rewardDetails.visibility = GONE
                } else {
                    rewardDetails.text = rewardDetailsDescription
                }
            }

            if (!it.howTo.isNullOrEmpty()) {
                howToRedeemDescription.text = it.howTo
            }

            rewardTitle.text = it.name
            it.requiredSteps.let { requiredSteps ->
                stepCount.text = STUtils.formatNumber(requiredSteps.toInt())
            }
            if (it.estimateSaving.contains("AED")) {
                savingCount.text =
                    it.estimateSaving
                        .replace("AED", "", true).trim()
            } else {
                savingCount.text =
                    it.estimateSaving
            }
            redeemCount.text = it.redeemed
            expireDate.text = getString(R.string.expire).plus(STUtils.formatServerDate(it.expireOn))
            webLinkBeforeRedeem.text = merchantData!!.site
            webLinkAfterRedeem.text = merchantData!!.site
            if (it.store != null) {
                it.store.phoneNumber.let { number ->
                    if (number == "") {
                        phoneNumberLayout.gone()
                    } else {
                        phoneNumber.text = number
                    }
                }
                it.store.name.let { storeName ->
                    locationSpinner.text = storeName
                }
                it.store.location.let { storeLocation ->
                    shopNumber.text = storeLocation
                }
            }
            it.requiredSteps.let { requiredSteps ->
                totalRedeemCount.text = STUtils.formatNumber(requiredSteps.toInt())
            }
            /*selectedMerchantStore?.let { selectedMerchantStoreData ->
                STUtils.setValueToView(locationSpinner, selectedMerchantStoreData.name)
                phoneNumber.text = selectedMerchantStoreData.phoneNumber
                shopNumber.text = selectedMerchantStoreData.location
            }*/

            if (isDigitalRewardSuccess || it.expireOn.isNullOrEmpty()) {
                inStoreRewardExpireDate.gone()
            } else {
                inStoreRewardExpireDate.visible()
                inStoreRewardExpireDate.text = getString(R.string.expire)
                    .plus(STUtils.formatServerDate(it.expireOn))
            }

            if (isFromNotification!!) {
                if (it.rewardType == STConstants.IN_STORE) {
                    webLinkBeforeRedeem.gone()
                    phoneNumberLayout.gone()
                    shopNumber.gone()
                    locationSpinner.visible()
                    // disable redeem button
                    redeem.setBackgroundResource(R.drawable.button_selector_normal)
                    redeem.isClickable = false
                    if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                        locationSpinner.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.arrow_down_white,
                            0,
                            R.drawable.location_icon,
                            0
                        )
                    } else {
                        locationSpinner.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.location_icon,
                            0,
                            R.drawable.arrow_down_white,
                            0
                        )
                    }
                } else {
                    if (!isDigitalRewardSuccess)
                        digitalLayout()
                }
            }
        }
    }

    private fun trackTheEvent(
        userAction: String,
        rewardDetails: RewardDetails,
        merchantData: STMerchantData
    ) {
        merchantData.let { merchantDataValue ->
            val eventNameList: HashMap<String, String> = HashMap()
            merchantDataValue.name?.let { merchantName ->
                eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_VENDOR_NAME] =
                    merchantName
            }
            merchantDataValue.id?.let { vendorId ->
                eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_VENDOR_ID] = vendorId
            }
            rewardDetails.name.let { rewardName ->
                eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_REWARD_NAME] = rewardName
            }
            rewardDetails.id.let { rewardId ->
                eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_REWARD_ID] = rewardId
            }
            STFireBaseAnalytics.trackEventValue(
                userAction,
                eventNameList
            )
        }
    }

    @OnClick(R.id.webLinkBeforeRedeem, R.id.webLinkAfterRedeem)
    fun webLinkBeforeRedeem() {
        try {
            merchantData!!.site?.let {
                STUtils.openLink(activityContext, it)
            }
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    @OnClick(R.id.shopNumber)
    fun shopLocation() {
        /*rewardDetail?.let { rewardDetailList ->
            rewardDetailList.store?.let {
                navigate(activityContext, it.latitude, it.longitude)
            }
        }*/
        selectedMerchantStore?.let {
            navigate(activityContext, it.latitude, it.longitude)
        }
    }

    @OnClick(R.id.redeem)
    fun redeem() {
        requestNewLocationData()
        val successDialog: Dialog =
            showVoucherRedeemDialog(merchantData, selectedMerchantRewardsData, rewardDetail) {
                confirmClick {
                    dismissDialog()
                    //successDialog()
                    redeemReward(getPin())
                }

                closeClick { dismissDialog() }
            }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    @OnClick(R.id.phoneNumberLayout)
    fun phoneNumberClick() {
        if (checkPermission(activityContext, Manifest.permission.CALL_PHONE)) {
            //call(activityContext, rewardDetail?.store!!.phoneNumber)
            call(activityContext, selectedMerchantStore?.phoneNumber!!)
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                STConstants.MAKE_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun successDialog(redeemRewardResponse: RedeemRewardData?) {
        redeemRewardResponse.let {
            val successDialog: Dialog =
                showVoucherRedeemSuccessDialog(merchantData, redeemRewardResponse) {
                    closeClick {
                        dismissDialog()
                        activityContext.finish()
                    }
                }
            successDialog.setSize(activityContext)
            successDialog.show()
            successDialog.setCancelable(false)
        }
    }

    private fun redeemReward(pin: String?) {
        val redeemRewardRequest = STRedeemRewardRequest()
        if (rewardDetail?.rewardType == STConstants.IN_STORE) {
            pin?.let {
                redeemRewardRequest.pin = Integer.parseInt(pin)
            }
            redeemRewardRequest.latitude =
                if (locationValue != null) locationValue!!.latitude else 25.1963744 //  "25.2048"
            redeemRewardRequest.longitude =
                if (locationValue != null) locationValue!!.longitude else 55.278995 // "55.2708"
            redeemRewardRequest.storeId = selectedMerchantStore?.id
        }
        invokeIntent(
            STRewardsIntent.RedeemReward(
                rewardDetail?.id!!,
                redeemRewardRequest
            )
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        when (requestCode) {
            STConstants.MAKE_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    //call(activityContext, rewardDetail?.store!!.phoneNumber)
                    call(activityContext, selectedMerchantStore?.phoneNumber!!)
                }
                return
            }
            permissionId -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLastLocation()
                }
            }
        }
    }

    fun shareReward() {
        STUtils.shareIntent(activityContext, rewardDetail?.rewardShareUrl!!)

        /* val sendIntent = Intent()
         sendIntent.action = Intent.ACTION_SEND
         sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Reward")
         sendIntent.putExtra(Intent.EXTRA_TEXT, rewardDetail?.rewardShareUrl)
         sendIntent.type = "text/plain"
         activityContext.startActivity(sendIntent)*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_STORE_LOCATION == requestCode) {
                data?.let {
                    selectedMerchantStore =
                        it.getSerializableExtra(STConstants.SELECTED_MERCHANT_STORE) as STMerchantStoresListData

                    selectedMerchantStore?.let { selectedMerchantStoreData ->
                        STUtils.setValueToView(locationSpinner, selectedMerchantStoreData.name)
                        phoneNumber.text = selectedMerchantStoreData.phoneNumber
                        shopNumber.text = selectedMerchantStoreData.location
                        shopNumber.visible()
                        phoneNumberLayout.visible()
                        redeem.setBackgroundResource(R.drawable.button_bg_enabled_small)
                        redeem.isClickable = true
                    }
                    //refreshList()
                }
            }
        }
    }

    var locationValue: Location? = null

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions(activityContext)) {
            if (isLocationEnabled(activityContext)) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(activityContext) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        if (!isLocationInIt!!)
                            requestNewLocationData()
                    } else {
                        locationValue = location
                        if (!isLocationInIt!!) {
                            requestNewLocationData()
                        }
                        /*merchantListData?.let {
                            if (it.isDigital!!) {
                                getStoresRewardList()
                                return@let
                            }
                            it.id?.let { id ->
                                invokeIntent(
                                    STRewardsIntent.GetMerchantsStoresList(
                                        id,
                                        "${location.latitude}"*//*"25.2048"*//*,
                                        "${location.longitude}"*//*"55.2708"*//*
                                    )
                                ) //"21.1702", "72.8311"
                            }
                        } ?: kotlin.run {
                            pageLayout.visible()
                            noRewardsListData.visible()
                        }*/
                    }
                }
            } else {
//                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        isLocationInIt = true
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.getPRIORITY_HIGH_ACCURACY()
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            locationValue = mLastLocation
            isLocationInIt = false
        }
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
}
