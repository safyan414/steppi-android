package com.steppi.steppifitness.ui.rewards

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STRewardsTypeListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.category.STCategory
import com.steppi.steppifitness.network.response.category.STMerchantAddRemoveResponse
import com.steppi.steppifitness.network.response.category.STMerchantListResponse
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsController
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsIntent
import com.steppi.steppifitness.ui.rewards.mvi.STRewardsState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_rewards_featured.*

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

class STFeaturedRewardsFragment :
    STBaseViewModelFragment<STRewardsIntent, STRewardsState, STRewardsController>(
        STRewardsController::class.java
    ), PaginationAdapterCallback, SwipeRefreshLayout.OnRefreshListener {
    private var featuredRewardAdapter: STRewardsTypeListAdapter? = null
    private var category: STCategory? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var posi: Int? = -1
    private var listPosition: Int? = -1
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val requestCheckSettings = 1000
    private val permissionId = 42
    var isLocationInIt: Boolean? = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_rewards_featured
    }

    override fun initViews() {
        category = arguments?.getSerializable(STConstants.CATEGORY) as? STCategory
        setAdapter()
    }

    private fun initLocationData() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
    }

    override fun onRefresh() {
        featuredRewardAdapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        getMerchantList()
    }

    private fun setAdapter() {
        if (null == featuredRewardAdapter) {
            rewardListPullRefresh.setOnRefreshListener(this)
            rewardListPullRefresh.setColorSchemeResources(
                R.color.button_bg_enabled_color,
                R.color.button_bg_enabled_color,
                R.color.button_bg_enabled_color,
                R.color.button_bg_enabled_color
            )

            val layoutManager =
                LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)

            featuredRewardsList?.layoutManager = layoutManager
            featuredRewardsList.setVerticalItemDecoration(
                space = STUtils.getDimen(activityContext, R.dimen.margin_normal),
                initialPadding = STUtils.getDimen(activityContext, R.dimen.margin_normal)
            )
            featuredRewardAdapter = STRewardsTypeListAdapter(activityContext)
            featuredRewardsList.adapter = featuredRewardAdapter
            featuredRewardAdapter?.setClickListener(object :
                STRewardsTypeListAdapter.OnItemClickListener {
                override fun onItemClick(merchantData: STMerchantData?) {
                    val container = Intent(activityContext, STContainerActivity::class.java)
                    container.putExtra(STConstants.FRAGMENT_NAME, merchantData?.name)
                    container.putExtra(
                        STConstants.FRAGMENT_ID,
                        STConstants.FRAGMENT_ID_REWARDS_DETAILS
                    )
                    container.putExtra(STConstants.MERCHANT_LIST, merchantData)
                    startActivity(container)
                }

                override fun onFavoriteClick(
                    merchantData: STMerchantData?,
                    position: Int,
                    position1: Int
                ) {
                    posi = position
                    listPosition = position1
                    merchantData?.id?.let {
                        invokeIntent(STRewardsIntent.MerchantAddRemoveFavorite(it))
                    }
                }
            })

            addScrollListener(layoutManager)
        }
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        featuredRewardsList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getMerchantList()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    override fun onViewModelReady() {
        shimmer_view_container.startShimmerAnimation()
        shimmer_view_container.visible()
        initLocationData()
        getMerchantList()
    }

    private fun getMerchantList() {
        if (checkPermissions(activityContext)) {
            if (isLocationEnabled(activityContext)) {
                getMerchantWithLocation()
            } else {
                showSettingDialog()
            }
        } else {
            requestPermissions()
        }
    }

    var locationValue: Location? = null

    @SuppressLint("MissingPermission")
    private fun getMerchantWithLocation() {
        mFusedLocationClient.lastLocation.addOnCompleteListener(activityContext) { task ->
            val location: Location? = task.result
            if (location == null) {
//                if (!isLocationInIt!!) {
                requestNewLocationData()
//                } else {
//                    getMerchantListApiCall()
//                }
            } else {
                locationValue = location
//                if (!isLocationInIt!!) {
//                    requestNewLocationData()
//                } else {
                getMerchantListApiCall()
//                }
            }
        }
    }

    override fun processState(state: STRewardsState) {
        when (state) {
//            is STRewardsState.Loading -> {
//                requestDidStart()
//            }
            is STRewardsState.Error -> {
//                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STRewardsState.GetMerchantList -> {
//                requestDidFinish()
                isLocationInIt = false
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.gone()
                listTotal = state.merchantList.total!!
                setMerchantList(state.merchantList)
            }
            is STRewardsState.MerchantAddRemoveFavorite -> {
//                requestDidFinish()
                setFavoriteData(state.merchantAddRemoveResponse)
            }
        }
    }

    private fun requestNewLocationData() {
        isLocationInIt = true
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.getPRIORITY_HIGH_ACCURACY()
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
//        mLocationRequest.numUpdates = 1
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    var isMerchantListAvailable = false
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            isLocationInIt = false
            val mLastLocation: Location = locationResult.lastLocation
            locationValue = mLastLocation
            getMerchantListApiCall()
        }
    }

//    private fun stoplocationUpdates() {
//        mFusedLocationClient!!.removeLocationUpdates(mLocationCallback)
//    }

    private fun getMerchantListApiCall() {
//        Toast.makeText(activityContext, "locationValue : $locationValue", Toast.LENGTH_LONG).show()
        category?.let {
            isMerchantListAvailable = true
            it.id?.let { categoryId ->
                invokeIntent(
                    STRewardsIntent.GetMerchantList(
                        categoryId,
                        if (locationValue != null) "" + locationValue?.latitude else "0.0",//"25.2048"
                        if (locationValue != null) "" + locationValue?.longitude else "0.0",//"55.2708",
                        offset
                    )
                )
            } ?: kotlin.run {
                noData.visibility = View.VISIBLE
            }
        } ?: kotlin.run {
            noData.visibility = View.VISIBLE
        }
    }

    private fun setFavoriteData(merchantAddRemoveResponse: STMerchantAddRemoveResponse) {
        category?.let {
            it.id?.let { categoryId ->
                if (categoryId == "-2") {
                    featuredRewardAdapter?.removeItem(posi!!)
                    if (featuredRewardAdapter?.itemCount ?: 0 == 0) {
                        noData.visible()
                    } else {
                    }
                } else {
                    merchantAddRemoveResponse.data?.let {
                        featuredRewardAdapter?.setFavouriteData(it.favorite, posi)
                    }
                }
            }
        }
    }

    private fun setMerchantList(merchantList: STMerchantListResponse) {
        rewardListPullRefresh?.isRefreshing = false
        merchantList.data?.let {
            if (it.isEmpty()) {
                if (category?.id!! == "-2") {
                    noData.text =
                        activityContext.resources.getString(R.string.no_fav_rewards_data_list)
                } else {
                    noData.text =
                        activityContext.resources.getString(R.string.no_rewards_data_list)
                }
                noData.visible()
            } else {
                noData.gone()
            }

            if (offset > 0) {
                featuredRewardAdapter!!.removeLoadingFooter()
                isLoading = false
            }

            featuredRewardAdapter?.setMerchantLists(it as ArrayList<STMerchantData>)

            if (listTotal > featuredRewardAdapter!!.getListSize()) featuredRewardAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = featuredRewardAdapter!!.getListSize() - 1
        } ?: kotlin.run {
            noData.visible()
        }
    }

    override fun retryPageLoad() {
    }

    /* Show Location Access Dialog */
    private fun showSettingDialog() {
        val locationRequest = LocationRequest.create()
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
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.getRESOLUTION_REQUIRED() ->
                        // Location settings are not satisfied. But could be fixed by showing the user a dialog.
                        try { // Cast to a resolvable exception.
                            val resolvable = exception as ResolvableApiException
                            startIntentSenderForResult(
                                exception.resolution.intentSender,
                                requestCheckSettings, null, 0, 0, 0, null
                            )
                        } catch (e: IntentSender.SendIntentException) { // Ignore the error.
                        } catch (e: ClassCastException) {
                        }
                    LocationSettingsStatusCodes.getSETTINGS_CHANGE_UNAVAILABLE() -> {
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCheckSettings) {
            if (resultCode == Activity.RESULT_OK) {
                Log.e("Success", "GPS enabled")
                getMerchantWithLocation()
            } else {
                if (isLocationEnabled(activityContext)) {
                    Log.e("Success", "GPS enabled")
                    getMerchantWithLocation()
                } else {
                    Log.e("Failure", "GPS is not enabled")
                    getMerchantListApiCall()
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

    override fun onRequestPermissionsResult(
        requestCode: Int, @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        when (requestCode) {
            permissionId -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getMerchantList()
                }
            }
        }
    }
}
