package com.steppi.steppifitness.ui.rewards

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STImageViewPager
import com.steppi.steppifitness.adapter.STRewardsDetailsListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.network.request.reward.STRedeemRewardRequest
import com.steppi.steppifitness.network.response.category.*
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsController
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsIntent
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_reward_details.*

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
// [Modified By HMSConvertor] import com.google.android.gms.common.api.ApiException
import org.xms.g.common.api.ApiException
// [Modified By HMSConvertor] import com.google.android.gms.common.api.ResolvableApiException
import org.xms.g.common.api.ResolvableApiException

class STRewardsDetailsFragment :
    STBaseViewModelFragment<STRewardsIntent, STRewardsState, STRewardsController>(
        STRewardsController::class.java
    ) {
    private var rewardsDetailsAdapter: STRewardsDetailsListAdapter? = null
    private var merchantListData: STMerchantData? = null
    private var selectedMerchantStore: STMerchantStoresListData? = null
    private var merchantId: String? = null
    private var selectedMerchantRewardsData: STStoresRewardsListData? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var isRefreshList = true
    private val permissionId = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val REQUEST_CHECK_SETTINGS = 1000
    var isLocationInIt: Boolean? = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_reward_details
    }

    override fun initViews() {
        initLocationData()
        initData()
        logo.loadImage(activityContext, merchantListData?.logo)
        initViewPager()
        setAdapter()
    }

    private fun initLocationData() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
    }

    private fun setAdapter() {
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
        rewardsDetailsList.layoutManager = layoutManager
        rewardsDetailsAdapter = STRewardsDetailsListAdapter()
        rewardsDetailsList.adapter = rewardsDetailsAdapter
        rewardsDetailsAdapter?.setClickListener(object :
            STRewardsDetailsListAdapter.OnItemClickListener {
            override fun onItemClick(merchantRewardsData: STStoresRewardsListData?) {
                if (STUtils.singleClick()) {
                    val container = Intent(activityContext, STContainerActivity::class.java)
                    container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.details))
                    container.putExtra(
                        STConstants.FRAGMENT_ID,
                        STConstants.FRAGMENT_ID_REWARDS_REDEEM
                    )
                    container.putExtra(STConstants.SELECTED_REWARD, merchantRewardsData)
                    container.putExtra(STConstants.SELECTED_MERCHANT_STORE, selectedMerchantStore)
                    container.putExtra(STConstants.MERCHANT_LIST, merchantListData)
                    startActivity(container)
                }
            }
        })

        rewardsDetailsAdapter?.setRedeemRewardListener(object :
            STRewardsDetailsListAdapter.RedeemReward {
            override fun redeemReward(rewardData: STStoresRewardsListData?) {
                rewardData?.let {
                    selectedMerchantRewardsData = rewardData
                    if (rewardData.rewardType == STConstants.IN_STORE && !selectedMerchantStore?.id.isNullOrEmpty()) {
                        invokeIntent(
                            STRewardsIntent.GetInStoreRewardDetails(
                                rewardData.id!!,
                                selectedMerchantStore?.id!!
                            )
                        )
                    } else {
                        invokeIntent(STRewardsIntent.GetDigitalRewardDetails(rewardData?.id!!))
                    }
                }
            }
        })
        addScrollListener(layoutManager)
    }


    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        rewardsDetailsList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getStoresRewardList()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading
        })
    }

    private fun initData() {
        merchantListData = arguments?.getSerializable(STConstants.MERCHANT_LIST) as? STMerchantData
        merchantListData?.let { merchantListData ->
            merchantId = merchantListData.id
        }
    }

    override fun onViewModelReady() {
        isLocationInIt = false
        requestNewLocationData()
        getLastLocation()
    }

    var locationValue: Location? = null

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions(activityContext)) {
            if (isLocationEnabled(activityContext)) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(activityContext) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        if (!isLocationInIt!!) {
                            requestNewLocationData()
                        } else {
                            getMerchantsStoresList()
                        }
                    } else {
                        locationValue = location
                        if (!isLocationInIt!!) {
                            requestNewLocationData()
                        } else {
                            getMerchantsStoresList()
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
                    //getMerchantsStoresList()
                }
            } else {
//                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                /*val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)*/
                showSettingDialog()
            }
        } else {
            requestPermissions()
        }
    }

    /* Show Location Access Dialog */
    private fun showSettingDialog() {
        val locationRequest = LocationRequest.create()
        //Setting priotity of Location request to high
        //locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 30 * 1000
        //5 sec Time interval for location update
        locationRequest.fastestInterval = 5 * 1000
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true) //this is the key ingredient to show dialog always when GPS is off
        val result = LocationServices.getSettingsClient(activityContext)
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
            try {
                val response: LocationSettingsResponse? =
                    task.getResult(ApiException::class.java)
                //  updateGPSStatus("GPS is Enabled in your device")
                // All location settings are satisfied. The client can initialize location
                // requests here.
                //setupLocationListener()
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.getRESOLUTION_REQUIRED() ->
                        // Location settings are not satisfied. But could be fixed by showing the user a dialog.
                        try { // Cast to a resolvable exception.
                            val resolvable = exception as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().

                            // getting result on fragment
                            startIntentSenderForResult(
                                exception.resolution.intentSender,
                                REQUEST_CHECK_SETTINGS, null, 0, 0, 0, null
                            )
                            // getting result on activity
                            /*resolvable.startResolutionForResult(
                                activityContext,
                                REQUEST_CHECK_SETTINGS//IntentConstant.REQ_LOCATION_SETTINGS
                            )*/
                        } catch (e: IntentSender.SendIntentException) { // Ignore the error.
                        } catch (e: ClassCastException) {
                        }
                    LocationSettingsStatusCodes.getSETTINGS_CHANGE_UNAVAILABLE() -> {
                    }
                }
            }
        }


        /*result.setResultCallback { result ->
            val status: Status = result.status
            val state = result.locationSettingsStates
            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS ->  // All location settings are satisfied. The client can initialize location
                    // requests here.
                    //  updateGPSStatus("GPS is Enabled in your device")
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                ->  // Location settings are not satisfied. But could be fixed by showing the user
                    // a dialog.
                    try { // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        *//* status.startResolutionForResult(
                                            this@MainActivity,
                                            REQUEST_CHECK_SETTINGS
                                        )*//*
                    } catch (e: IntentSender.SendIntentException) {
                        e.printStackTrace()
                        // Ignore the error.
                    }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                }
            }
        }*/
    }

    private fun requestNewLocationData() {
        isLocationInIt = true
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.getPRIORITY_HIGH_ACCURACY()
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    var isMerchantListAvailable = false

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            locationValue = mLastLocation
            if (isLocationInIt!!) {
                if (!isMerchantListAvailable) {
                    getMerchantsStoresList()
                }
            }
        }
    }

    override fun processState(state: STRewardsState) {
        when (state) {
            is STRewardsState.Loading -> {
                requestDidStart()
            }
            is STRewardsState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STRewardsState.GetStoresRewardList -> {
                requestDidFinish()
                listTotal = state.storesRewardsList.total!!
                setMerchantsRewardsList(state.storesRewardsList)
            }
            is STRewardsState.GetMerchantStoresList -> {
                //requestDidFinish()
                isLocationInIt = false
                selectedMerchantStore = state.merchantStoresList.data?.getOrNull(0)
                getStoresRewardList()
            }
            is STRewardsState.GetDigitalRewardDetails -> {
                requestDidFinish()
                openRedeemRewardDialog(state.rewardDetails.data)
            }
            is STRewardsState.GetInStoreRewardDetails -> {
                requestDidFinish()
                openRedeemRewardDialog(state.rewardDetails.data)
            }
            is STRewardsState.RedeemReward -> {
                requestDidFinish()
                selectedMerchantRewardsData?.let {
                    if (selectedMerchantRewardsData?.rewardType == STConstants.IN_STORE) {
                        successDialog(state.redeemRewardResponse.data!!)
                    } else {
                        val container = Intent(activityContext, STContainerActivity::class.java)
                        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.details))
                        container.putExtra(
                            STConstants.FRAGMENT_ID,
                            STConstants.FRAGMENT_ID_REWARDS_REDEEM
                        )
                        container.putExtra(STConstants.SELECTED_REWARD, selectedMerchantRewardsData)
                        container.putExtra(
                            STConstants.SELECTED_MERCHANT_STORE,
                            selectedMerchantStore
                        )
                        container.putExtra(
                            STConstants.DIGITAL_REWARD_REDEEM_DATA,
                            state.redeemRewardResponse.data
                        )
                        container.putExtra(STConstants.MERCHANT_LIST, merchantListData)
                        startActivity(container)
                    }
                }
                refreshList()
            }
        }
    }

    private fun setMerchantsRewardsList(merchantRewardsList: STStoresRewardsListResponse) {
        pageLayout.visible()
        merchantRewardsList.data?.let {
            if (it.isNullOrEmpty()) {
                noRewardsData.visible()
            } else {
                noRewardsData.gone()
            }
            if (offset > 0) {
                rewardsDetailsAdapter!!.removeLoadingFooter()
                isLoading = false
            }

            rewardsDetailsAdapter?.setMerchantRewardsLists(
                isRefreshList,
                it as ArrayList<STStoresRewardsListData>
            )

            if (listTotal > rewardsDetailsAdapter!!.getListSize()) rewardsDetailsAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = rewardsDetailsAdapter!!.getListSize() - 1
        } ?: kotlin.run {
            noRewardsData.visible()
        }
        isRefreshList = false
    }

    @OnClick(R.id.locationSpinner)
    fun locationSpinnerSelection() {
        openLocationSelection()
    }

    private fun initViewPager() {
        val adapter = STImageViewPager(activityContext)
        adapter.setItemImage(merchantListData?.images)
        STUtils.setImageSize(activityContext, pager, 0)
        pager?.adapter = adapter
        pager.requestLayout()
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                setNextPreviousButton(position)
            }

            override fun onPageSelected(position: Int) {
            }
        })
        indicator.setViewPager(pager)
    }

    @OnClick(R.id.previous)
    fun previous() {
        pager.setCurrentItem(getItem(-1), true)
        setNextPreviousButton(pager.currentItem)
    }

    @OnClick(R.id.next)
    fun next() {
        pager.setCurrentItem(getItem(+1), true)
        setNextPreviousButton(pager.currentItem)
    }

    @OnClick(R.id.shopNumber)
    fun shopLocation() {
        selectedMerchantStore?.let { rewardDetailList ->
            rewardDetailList.let {
                STUtils.navigate(activityContext, it.latitude, it.longitude)
            }
        }
    }

    @OnClick(R.id.phoneNumberLayout)
    fun phoneNumberClick() {
        if (checkPermission(activityContext, Manifest.permission.CALL_PHONE)) {
            call(activityContext, selectedMerchantStore?.phoneNumber!!)
        } else {
            ActivityCompat.requestPermissions(
                activityContext,
                arrayOf(Manifest.permission.CALL_PHONE),
                STConstants.MAKE_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getItem(i: Int): Int {
        return pager.currentItem + i
    }

    private fun setNextPreviousButton(position: Int) {
        merchantListData?.images?.let {
            if (it.size > 1) {
                if (position in 0..it.size - 2) {
                    if (position <= 0) {
                        previous.setImageResource(R.color.transparent)
                    } else {
                        previous.setImageResource(R.drawable.view_pager_previous_button)
                    }
                    next.setImageResource(R.drawable.view_pager_next_button)
                } else {
                    if (position == it.size - 1) {
                        previous.setImageResource(R.drawable.view_pager_previous_button)
                        next.setImageResource(R.color.transparent)
                    }
                }
            } else {
                previous.setImageResource(R.color.transparent)
                next.setImageResource(R.color.transparent)
            }
        }
    }

    private fun openLocationSelection() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.location))
        container.putExtra(STConstants.MERCHANT_LIST, merchantListData)
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_LOCATION_SELECTION)
        container.putExtra(STConstants.SELECTED_MERCHANT_STORE, selectedMerchantStore)
        startActivityForResult(container, STConstants.REQUEST_CODE_STORE_LOCATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_STORE_LOCATION == requestCode) {
                data?.let {
                    selectedMerchantStore =
                        it.getSerializableExtra(STConstants.SELECTED_MERCHANT_STORE) as STMerchantStoresListData
                    refreshList()
                }
            }
        }

        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                Log.e("Success", "GPS enabled")
                getLastLocation()
            } else {
                if (isLocationEnabled(activityContext)) {
                    Log.e("Success", "GPS enabled")
                    getLastLocation()
                } else {
                    Log.e("Failure", "GPS is not enabled")
                    getMerchantsStoresList()
                }
            }
        }
    }

    private fun refreshList() {
        isRefreshList = true
        offset = 0
        isLastPage = false
        getStoresRewardList()
    }


    private fun getStoresRewardList() {
        merchantListData?.let {
            if (it.isDigital!!) {
                it.name?.let { merchantName ->
                    val eventNameList: HashMap<String, String> = HashMap()
                    eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_VENDOR_NAME] =
                        merchantName
                    it.id?.let { vendorId ->
                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_VENDOR_ID] =
                            vendorId
                    }
                    if (!BuildConfig.DEBUG)
                        STFireBaseAnalytics.trackEventValue(
                            STFireBaseAnalyticsConstants.EVENT_DIGITAL_VENDOR_DETAIL_VISIT,
                            eventNameList
                        )
                }
                merchantId?.let { merchantIdData ->
                    storesData.visible()
                    locationSpinner.gone()
                    shopNumber.gone()
                    invokeIntent(STRewardsIntent.GetStoresRewardList(merchantIdData, null))
                    phoneNumberLayout.gone()
                } ?: kotlin.run {
                    previous.invisible()
                    next.invisible()
                }
            } else {
                it.name?.let { merchantName ->
                    val eventNameList: HashMap<String, String> = HashMap()
                    eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_VENDOR_NAME] =
                        merchantName
                    it.id?.let { vendorId ->
                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_VENDOR_ID] =
                            vendorId
                    }
                    if (!BuildConfig.DEBUG)
                        STFireBaseAnalytics.trackEventValue(
                            STFireBaseAnalyticsConstants.EVENT_IN_STORE_VENDOR_DETAIL_VISIT,
                            eventNameList
                        )
                }
                selectedMerchantStore?.let { selectedMerchantStoreData ->
                    noRewardsListData.gone()
                    selectedMerchantStoreData.name?.let { selectedMerchantStoreName ->
                        STUtils.setValueToView(locationSpinner, selectedMerchantStoreName)
                    }
                    locationSpinner.visibility = View.VISIBLE
                    storesData.visible()
                    phoneNumber.text = selectedMerchantStoreData.phoneNumber
                    if (selectedMerchantStoreData.phoneNumber.isNullOrEmpty()) {
                        phoneNumberLayout.gone()
                    }
                    shopNumber.text = selectedMerchantStoreData.location

                    selectedMerchantStoreData.id?.let { selectedMerchantStoreId ->
                        merchantId?.let { merchantIdData ->
                            invokeIntent(
                                STRewardsIntent.GetStoresRewardList(
                                    merchantIdData, selectedMerchantStoreId
                                )
                            )
                        }
                    }
                } ?: kotlin.run {
                    locationSpinner.visible()
                    requestDidFinish()
                    noRewardsListData.visible()
                    pageLayout.visible()
                    previous.invisible()
                    next.invisible()
                }
            }
        } ?: kotlin.run {
            pageLayout.visible()
            noRewardsListData.visible()
            previous.invisible()
            next.invisible()
        }
    }

    fun openRedeemRewardDialog(rewardDetail: RewardDetails?) {
        requestNewLocationData()
        val successDialog: Dialog =
            showVoucherRedeemDialog(merchantListData, selectedMerchantRewardsData, rewardDetail) {
                confirmClick {
                    dismissDialog()
                    redeemReward(getPin())
                }

                closeClick { dismissDialog() }
            }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun successDialog(redeemRewardResponse: RedeemRewardData?) {
        redeemRewardResponse.let {
            val successDialog: Dialog =
                showVoucherRedeemSuccessDialog(merchantListData, redeemRewardResponse) {
                    closeClick {
                        dismissDialog()
                        merchantListData?.let {
                            it.id?.let { id ->
                                invokeIntent(
                                    STRewardsIntent.GetMerchantsStoresList(
                                        id,
                                        if (locationValue != null) "" + locationValue?.latitude else "0.0",//"25.2048"
                                        if (locationValue != null) "" + locationValue?.longitude else "0.0"//"55.2708"
                                    )
                                )
                            }
                        }
                    }
                }
            successDialog.setSize(activityContext)
            successDialog.show()
            successDialog.setCancelable(false)
        }
    }

    private fun getMerchantsStoresList() {
        merchantListData?.let {
            isMerchantListAvailable = true
            if (it.isDigital!!) {
                getStoresRewardList()
                return@let
            }
            it.id?.let { id ->
                invokeIntent(
                    STRewardsIntent.GetMerchantsStoresList(
                        id,
                        if (locationValue != null) "" + locationValue?.latitude else "0.0",//"25.2048"
                        if (locationValue != null) "" + locationValue?.longitude else "0.0"//"55.2708"
                    )
                ) //"21.1702", "72.8311"
            }
        } ?: kotlin.run {
            pageLayout.visible()
            noRewardsListData.visible()
        }
    }


    private fun redeemReward(pin: String?) {
        val redeemRewardRequest = STRedeemRewardRequest()

        if (selectedMerchantRewardsData?.rewardType == STConstants.IN_STORE) {
            pin?.let {
                redeemRewardRequest.pin = Integer.parseInt(it)
            }

            redeemRewardRequest.latitude =
                if (locationValue != null) locationValue!!.latitude else 0.0 //  "25.2048"
            redeemRewardRequest.longitude =
                if (locationValue != null) locationValue!!.longitude else 0.0 // "55.2708"
            redeemRewardRequest.storeId = selectedMerchantStore?.id
        }
        invokeIntent(
            STRewardsIntent.RedeemReward(
                selectedMerchantRewardsData?.id!!,
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
                    call(activityContext, selectedMerchantStore?.phoneNumber!!)
                }
                return
            }
            permissionId -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLastLocation()
                } else {
                    getMerchantsStoresList()
                }
            }
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
