package com.steppi.steppifitness.ui.rewards.search

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STRewardsTypeListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.STRetrofitClient
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
import kotlinx.android.synthetic.main.fragment_search_rewards.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

class STSearchListFragment :
    STBaseViewModelFragment<STRewardsIntent, STRewardsState, STRewardsController>(
        STRewardsController::class.java
    ), PaginationAdapterCallback, SwipeRefreshLayout.OnRefreshListener {
    private var searchRewardAdapter: STRewardsTypeListAdapter? = null
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var searchCategoryList: STCategory? = null
    private val requestCheckSettings = 1000
    private val permissionId = 42
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var posi: Int? = -1
    var searchString: String? = ""

    override fun getLayoutId(): Int {
        return R.layout.fragment_search_rewards
    }

    override fun onViewModelReady() {
    }

    override fun initViews() {
        initLocationData()
        initData()
        setAdapter()
    }

    private fun initLocationData() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
    }

    private fun initData() {
        searchCategoryList =
            arguments?.getSerializable(STConstants.SEARCH_CATEGORY_LIST) as? STCategory
    }

    override fun onRefresh() {
        searchRewardAdapter?.clear()
        resetPaginationParam()
        searchRewards()
    }

    private fun resetPaginationParam() {
        isLoading = false
        isLastPage = false
        offset = 0
        listTotal = 0
    }

    private fun setAdapter() {
        if (null == searchRewardAdapter) {
            searchRewardListPullRefresh.setOnRefreshListener(this)
            searchRewardListPullRefresh.setColorSchemeResources(
                R.color.button_bg_enabled_color,
                R.color.button_bg_enabled_color,
                R.color.button_bg_enabled_color,
                R.color.button_bg_enabled_color
            )

            val layoutManager =
                LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
            rewardSearchList?.layoutManager = layoutManager
            rewardSearchList?.setVerticalItemDecoration(
                space = STUtils.getDimen(activityContext, R.dimen.margin_normal),
                initialPadding = STUtils.getDimen(activityContext, R.dimen.margin_normal)
            )
            setListeners()
            addScrollListener(layoutManager)
        }
    }

    private fun setListeners() {
        search_view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (charSequence.toString()
                        .isNotEmpty() && charSequence?.length!! >= 2/*charSequence?.length?.rem(3) == 0*/) {
                    searchString = charSequence.toString()
                    resetPaginationParam()
                    getSearchMerchantListApiCall()
                } else {
                    searchRewardAdapter?.clear()
                    searchString = ""
                    if (searchRequest != null) {
                        searchRequest!!.cancel()
                    }
                }
            }

            override fun afterTextChanged(charSequence: Editable?) {
            }
        })
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        rewardSearchList?.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                searchRewards()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    private fun setFavoriteData(merchantAddRemoveResponse: STMerchantAddRemoveResponse) {
        merchantAddRemoveResponse.data?.let {
            searchRewardAdapter?.setFavouriteData(it.favorite, posi)
        }
    }

    override fun processState(state: STRewardsState) {
        when (state) {
            is STRewardsState.MerchantAddRemoveFavorite -> {
                setFavoriteData(state.merchantAddRemoveResponse)
            }
            is STRewardsState.Error -> {
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
//            is STRewardsState.GetMerchantList -> {
//                loading_indicator.gone()
//                isLocationInIt = false
////                listTotal = state.merchantList.total!!
//                setMerchantList(state.merchantList)
//            }
        }
    }

    private fun setMerchantList(merchantList: STMerchantListResponse) {
        searchRewardListPullRefresh?.isRefreshing = false
        listTotal = merchantList.total!!
        merchantList.data?.let {
            if (it.isEmpty()) {
                no_data?.visible()
                no_data?.text = getString(R.string.no_search_results)
            } else {
                no_data?.gone()
            }

            if (searchRewardAdapter == null) {
                searchRewardAdapter = STRewardsTypeListAdapter(activityContext)
            }

            if (rewardSearchList?.adapter == null) {
                rewardSearchList?.adapter = searchRewardAdapter
                searchRewardAdapter?.setClickListener(object :
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
                        merchantData?.id?.let {
                            invokeIntent(STRewardsIntent.MerchantAddRemoveFavorite(it))
                        }
                    }
                })
            }
            if (offset > 0) {
                searchRewardAdapter!!.removeLoadingFooter()
                isLoading = false
            }

            searchRewardAdapter?.setMerchantLists(it as ArrayList<STMerchantData>)

            if (listTotal > searchRewardAdapter!!.getListSize()) {
                searchRewardAdapter!!.addLoadingFooter()
                offset = searchRewardAdapter!!.getListSize() - 1
                //offset += 1
            } else isLastPage = true
        } ?: kotlin.run {
            no_data?.visible()
            no_data?.text = getString(R.string.no_search_results)
        }
    }

    override fun retryPageLoad() {
    }

    private fun searchRewards() {
        if (checkPermissions(activityContext)) {
            if (isLocationEnabled(activityContext)) {
                getSearchMerchantWithLocation()
            } else {
                showSettingDialog()
            }
        } else {
            requestPermissions()
        }
    }

    var locationValue: Location? = null
    var isLocationInIt: Boolean? = false

    @SuppressLint("MissingPermission")
    private fun getSearchMerchantWithLocation() {
        mFusedLocationClient.lastLocation.addOnCompleteListener(activityContext) { task ->
            val location: Location? = task.result
            if (location == null) {
//                if (!isLocationInIt!!) {
                requestNewLocationData()
//                } else {
//                    getSearchMerchantListApiCall()
//                }
            } else {
                locationValue = location
//                if (!isLocationInIt!!) {
//                    requestNewLocationData()
//                } else {
                getSearchMerchantListApiCall()
//                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCheckSettings) {
            if (resultCode == Activity.RESULT_OK) {
                getSearchMerchantWithLocation()
            } else {
                if (isLocationEnabled(activityContext)) {
                    getSearchMerchantWithLocation()
                } else {
                    getSearchMerchantListApiCall()
                }
            }
        }
    }

    private var searchRequest: Call<STMerchantListResponse>? = null
    private fun getSearchMerchantListApiCall() {
        if (searchString == "") {
            searchRewardListPullRefresh?.isRefreshing = false
            searchRewardAdapter?.clear()
            if (searchRequest != null) {
                searchRequest!!.cancel()
            }
        } else {
            if (!isLoading) {
                loading_indicator?.visible()
                searchRewardAdapter?.clear()
            }
            if (searchRequest != null) {
                searchRequest!!.cancel()
            }
            searchRequest = STRetrofitClient.create(activityContext).getSearchMerchantsList(
                if (locationValue != null) "" + locationValue?.latitude else "0.0",
                if (locationValue != null) "" + locationValue?.longitude else "0.0",
                offset.toString(), searchString ?: ""
            )
            searchRequest!!.enqueue(object : Callback<STMerchantListResponse> {
                override fun onFailure(call: Call<STMerchantListResponse>?, t: Throwable?) {
                    loading_indicator?.gone()
                }

                override fun onResponse(
                    call: Call<STMerchantListResponse>?,
                    responseTag: Response<STMerchantListResponse>?
                ) {
                    loading_indicator?.gone()
                    responseTag?.let {
                        val searchResponse = it.body()
                        searchResponse?.let { searchResponseData ->
                            setMerchantList(searchResponseData)
                        } ?: kotlin.run {
                            loading_indicator?.gone()
                            no_data?.visible()
                            no_data?.text = getString(R.string.no_search_results)
                        }
                    } ?: kotlin.run {
                        loading_indicator?.gone()
                        no_data?.visible()
                        no_data?.text = getString(R.string.no_search_results)
                    }
                }
            })
        }
    }

    private fun requestNewLocationData() {
        isLocationInIt = true
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.getPRIORITY_HIGH_ACCURACY()
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
//        mLocationRequest.numUpdates = 1
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            isLocationInIt = false
            val mLastLocation: Location = locationResult.lastLocation
            locationValue = mLastLocation
            getSearchMerchantListApiCall()
        }
    }

    private fun showSettingDialog() {
        val locationRequest = LocationRequest.create()
        locationRequest.interval = 30 * 1000
        locationRequest.fastestInterval = 5 * 1000
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result = LocationServices.getSettingsClient(activityContext)
            .checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response: LocationSettingsResponse? =
                    task.getResult(ApiException::class.java)
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.getRESOLUTION_REQUIRED() ->
                        try {
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
            } catch (throwable: Throwable) {
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
                    searchRewards()
                }
            }
        }
    }
}
