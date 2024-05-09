package com.steppi.steppifitness.ui.rewards

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STStoreLocationAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.category.STMerchantStoresListData
import com.steppi.steppifitness.network.response.category.STMerchantStoresListResponse
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsController
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsIntent
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_store_location_list.*
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

class STStoreLocationSelectionFragment :
    STBaseViewModelFragment<STRewardsIntent, STRewardsState, STRewardsController>(
        STRewardsController::class.java
    ) {
    private var storeLocationAdapter: STStoreLocationAdapter? = null
    private var merchantListData: STMerchantData? = null
    private var selectedMerchantStore: STMerchantStoresListData? = null
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var query: String = ""
    private var isFromNotification: Boolean? = null
    private var notificationRewardId: String? = null
    private val permissionId = 42
    var isLocationInIt: Boolean? = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_store_location_list
    }

    override fun initViews() {
        initLocationData()
        initData()
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
            is STRewardsState.GetMerchantStoresList -> {
                requestDidFinish()
                loading_indicator.gone()
                isLocationInIt = false
                setMerchantStoresData(state.merchantStoresList)
            }
            is STRewardsState.SearchStoreLocationState -> {
                storeLocationAdapter?.setLocationList(state.storeList as ArrayList<STMerchantStoresListData>)
            }
        }
    }

    private fun initData() {
        isFromNotification = arguments?.getBoolean(STConstants.IS_NOTIFICATION, false)
        if (isFromNotification!!) {
            arguments?.getString(STConstants.NOTIFICATION_REWARD_ID)?.let {
                notificationRewardId = it
            }
        }
        merchantListData =
            arguments?.getSerializable(STConstants.MERCHANT_LIST) as? STMerchantData
        selectedMerchantStore =
            arguments?.getSerializable(STConstants.SELECTED_MERCHANT_STORE) as? STMerchantStoresListData

        initSearchView()
    }

    override fun onViewModelReady() {
//        requestNewLocationData()
        getLastLocation()
    }

    private fun getMerchantsStoresList() {
        loading_indicator.visible()
        merchantListData?.let {
            it.id?.let { merchantId ->
                invokeIntent(
                    STRewardsIntent.GetMerchantsStoresList(
                        merchantId,
                        if (locationValue != null) "${locationValue!!.latitude}" else "25.2048",
                        if (locationValue != null) "${locationValue!!.longitude}" else "55.2708",
                        (if (!notificationRewardId.isNullOrEmpty()) notificationRewardId else "")!!,
                        query
                        /*"25.2048",
                           "55.2708",*/
                    )
                )
            } ?: kotlin.run {
            }
        } ?: kotlin.run {
            loading_indicator.gone()
        }
    }

    var locationValue: Location? = null
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions(activityContext)) {
            if (isLocationEnabled(activityContext)) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(activityContext) { task ->
                    val location = task.result
                    if (location == null) {
//                        if (!isLocationInIt!!) {
                            requestNewLocationData()
//                        }
                    } else {
                        locationValue = location
//                        if (!isLocationInIt!!) {
//                            requestNewLocationData()
//                        } else {
                            getMerchantsStoresList()
//                        }

//                        merchantListData?.let {
//                            it.id?.let {
//                                invokeIntent(
//                                    STRewardsIntent.GetMerchantsStoresList(
//                                        it,
//                                        "25.2048",
//                                        "55.2708"
//                                        //"${location.latitude}"/*"25.2048"*/,
//                                        //"${location.longitude}"/*"55.2708"*/
//                                    )
//                                )
//                            } ?: kotlin.run {
//                            }
//                        } ?: kotlin.run {
//                        }
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

    private fun setMerchantStoresData(storeResponse: STMerchantStoresListResponse) {
        storeResponse.data?.let {
            if (it.isNotEmpty()) {
                locationList.visible()
                noData.gone()
                locationList.layoutManager = LinearLayoutManager(activityContext)
                locationList.addItemDecoration(STDividerItemDecoration(activityContext))

                storeLocationAdapter = STStoreLocationAdapter(
                    activityContext,
                    it as ArrayList<STMerchantStoresListData>,
                    selectedMerchantStore
                ) { store ->
                    activityContext.intent.putExtra(
                        STConstants.SELECTED_MERCHANT_STORE,
                        store as STMerchantStoresListData
                    )
                    activityContext.setResult(Activity.RESULT_OK, activityContext.intent)
                    activityContext.finish()
                }
                locationList.adapter = storeLocationAdapter
            } else {
                locationList.gone()
                noData.visible()
            }
        } ?: kotlin.run {
            locationList.gone()
            noData.visible()
        }
    }

    /*@OnTextChanged(R.id.locationSearch)
    fun locationSearch(text: CharSequence) {
        invokeIntent(
            STRewardsIntent.SearchStoreLocationIntent(
                text.toString(),
                storeLocationAdapter?.getLocationList()
            )
        )
    }*/

    private fun initSearchView() {
        locationSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // user typed: start the timer
//                timer = Timer()
//                timer!!.schedule(object : TimerTask() {
//                    override fun run() { // do your actual work here
//                loading_indicator.visible()
                query = s.toString()
                getMerchantsStoresList()
//                    }
//                }, 400) // 400ms delay before the timer executes the „run“ method from TimerTask
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // user is typing: reset already started timer (if existing)
//                if (timer != null)
//                    timer?.cancel()
            }
        })
    }

    @SuppressLint("MissingPermission")
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
            getMerchantsStoresList()
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

    override fun onRequestPermissionsResult(
        requestCode: Int, @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        when (requestCode) {
            permissionId -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLastLocation()
                }
            }
        }
    }
}
