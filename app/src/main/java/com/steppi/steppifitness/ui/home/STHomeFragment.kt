package com.steppi.steppifitness.ui.home

import android.animation.Animator
import android.animation.AnimatorSet
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.google.android.material.appbar.AppBarLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STItemEventListAdapter
import com.steppi.steppifitness.adapter.STRewardPagerAdapter
import com.steppi.steppifitness.app.STApplication
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.fitness_manager.fitbit.FitBitDataSource
import com.steppi.steppifitness.fitness_manager.googlefit.GoogleFitDataSource
import com.steppi.steppifitness.fitness_manager.hihealth.HuaweiHealthDataSource
import com.steppi.steppifitness.fitness_manager.samsung_health.SamsungHealthKitDataSource
import com.steppi.steppifitness.model.STFitnessDataModel
import com.steppi.steppifitness.network.request.home.STStepsRequestData
import com.steppi.steppifitness.network.request.home.STSyncFitnessDataRequest
import com.steppi.steppifitness.network.request.home.STWhatsNewVersionUpdateRequest
import com.steppi.steppifitness.network.response.category.STCategory
import com.steppi.steppifitness.network.response.category.STCategoryResponse
import com.steppi.steppifitness.network.response.home.*
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.device_connection.STChooseDeviceActivity
import com.steppi.steppifitness.ui.device_connection.STDeviceConnectionActivity
import com.steppi.steppifitness.ui.home.callbacks.IRefreshHomeActivityCallback
import com.steppi.steppifitness.ui.home.mvi.STHomeController
import com.steppi.steppifitness.ui.home.mvi.STHomeIntent
import com.steppi.steppifitness.ui.home.mvi.STHomeState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STHomeFragment :
    STBaseViewModelFragment<STHomeIntent, STHomeState, STHomeController>(STHomeController::class.java),
    SwipeRefreshLayout.OnRefreshListener, FitnessCallback {
    private var rewardPagerAdapter: STRewardPagerAdapter? = null
    private var categoryListAdapter: STItemEventListAdapter? = null
    private var selection: String? = null
    private var googleFitDataSource: GoogleFitDataSource? = null
    private var fitBitDataSource: FitBitDataSource? = null
    private var samsungDataSource: SamsungHealthKitDataSource? = null
    private var huaweiHealthDataSource: HuaweiHealthDataSource? = null
    private var requestHuaweiHealthAuthorization = false

    private var summaryAnimator: Animator? = null
    private var lastSyncStamp: String? = null
    private val syncFitnessDataRequest = STSyncFitnessDataRequest()
    private val stepsArray = ArrayList<STStepsRequestData>()
    private val stepsArrayData = ArrayList<STStepsRequestData>()
    private var currentPage = 0
    private var timer: Timer? = null
    private val delayInMilliSeconds: Long = 500
    private val periodInMilliSeconds: Long = 3000
    private var dailyGoalValue: Int? = 10000
    private var lifeTimeStepsValue: Int? = 0
    private var screenLists: ArrayList<String>? = ArrayList()

    private lateinit var refreshCallback: IRefreshHomeActivityCallback

    override fun getLayoutId(): Int {
        return R.layout.fragment_home 
    }

    override fun initViews() {
        initSummary()
        setUpAppbarLayout()
//        setUpRewardPager()
        setCategoryList()
//        init()
    }

    override fun onViewModelReady() {
        if (STConstants.IS_SYNC_INITIALLY) {
            invokeIntent(STHomeIntent.GetCategory)
        } else {
            setUpRewardPager()
            setCategories(STApplication.categoryResponseData!!, true)
            updateUI()
        }
        //init()
    }

    //todo refresh fragment in case of network available
    private fun refreshFragment() {
        refreshCallback.refreshHome()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is IRefreshHomeActivityCallback) {
            try {
                refreshCallback = context           //To Check if activity Implemented or Not...
            } catch (e: ClassCastException) {
                throw ClassCastException(activity.toString() + " must override onMessage Read...");
            }
        }
    }

    override fun onRefresh() {
        timer?.cancel()
        timer = null

        if (STUtils.isInternetOn(activity!!.application)) {         //network On
            if (STApplication.categoryResponseData != null)
                setCategories(STApplication.categoryResponseData!!, false)
            else
                refreshFragment()
        } else {                            //no network
            showToast(resources.getString(R.string.no_internet))
            homePagePullRefresh?.isRefreshing = false
        }
//        invokeIntent(STHomeIntent.GetCategory)
    }


    override fun processState(state: STHomeState) {
        when (state) {
            is STHomeState.Loading -> requestDidStart()
            is STHomeState.Error -> {
                requestDidFinish()
                homePagePullRefresh?.isRefreshing = false
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STHomeState.GetCategory -> {
                //requestDidFinish()
                STApplication.categoryResponseData = state.categoryResponse
                setCategories(STApplication.categoryResponseData!!)
            }
            is STHomeState.GetHomeScreenFeaturedRewards -> {
//                requestDidFinish()
                setFeaturedRewardsList(state.featuredRewardsResponse)
            }
            is STHomeState.SyncFitnessData -> {
                requestDidFinish()
                homePagePullRefresh?.isRefreshing = false
                setSyncedDataToHome(state.syncFitnessDataResponse)
            }
            is STHomeState.MarkHomeNotificationRead -> {
                requestDidFinish()
            }
            is STHomeState.WhatsNewOnBoardingScreen -> {
//                if (STPreference.isFirstTimeInstalled(activityContext)) {
//                    STPreference.setFirstTimeInstalled(activityContext, false)
                setUpWhatsNew(state.whatsNewLatestOnBoardingScreensResponse)
//                }
            }
            is STHomeState.WhatsNewVersionUpdate -> {
            }
            else -> {
            }
        }
    }

    private fun setUpWhatsNew(whatsNewLatestOnBoardingScreensResponse: STWhatsNewLatestOnBoardingScreensResponse?) {
        whatsNewLatestOnBoardingScreensResponse?.let { whatsNewOnBoardingResponse ->
            whatsNewOnBoardingResponse.data?.let { whatsNewOnBoardingDataList ->
                if (!whatsNewOnBoardingDataList.isNullOrEmpty()) {
                    screenLists?.clear()
                    for (i in whatsNewOnBoardingDataList.indices) {
                        whatsNewOnBoardingDataList[i].androidVersion?.let {
//                            if (STUtils.getVersionCode(activityContext) == it.toInt()) {
                            for (screen in 0..i) {
                                screenLists?.addAll(whatsNewOnBoardingDataList[screen].screens as ArrayList<String>)
                            }
//                            }
                        }
                    }
                    goTOWhatsNewPage(screenLists)
                }
            }
        }
    }

    private fun goTOWhatsNewPage(screenLists: ArrayList<String>?) {
        screenLists?.let { screenListData ->
            if (!screenListData.isNullOrEmpty()) {
                val intent = Intent(activityContext, STWhatsNewActivity::class.java)
                intent.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.whats_new))
                intent.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_WHATS_NEW)
                intent.putExtra(STConstants.WHATS_NEW_SCREEN_LIST_DATA, screenListData)
                startActivityForResult(intent, STConstants.REQUEST_CODE_WHATS_NEW)
            }
        }
    }

    private fun setSyncedDataToHome(syncFitnessDataResponse: STSyncFitnessDataResponse) {
        syncFitnessDataResponse.data?.let { stepsListData ->
            STApplication.homeScreenData?.let { homeScreenData ->
                stepsListData.steps?.let { stepsData ->
                    // Log.e("steps", "steps : " + stepsData.steps)
                    homeScreenData.steps = stepsData
                    /*stepsTodayCount?.text = STUtils.formatNumber(stepsData.steps!!.toInt())
                    distanceKm?.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(stepsData.distance!!.toDouble())
                    calory?.text = STUtils.formatNumber(stepsData.calories!!.toInt())

                    if ((dailyGoalValue?.minus(stepsData.steps!!.toInt()))!! <= 0) {
                        dailyGoal.gone()
                        dailyGoalLabel.text = activityContext.getString(R.string.reached_daily_goal)
                    } else {
                        dailyGoal.visible()
                        dailyGoal.text =
                            STUtils.formatNumber(dailyGoalValue?.minus(stepsData.steps!!.toInt()) as Int)
                        dailyGoalLabel.text =
                            activityContext.getString(R.string.steps_to_reach_daily_goal)
                    }
                    setSummaryData(stepsData.steps!!.toInt())*/
                }
                stepsListData.totalSteps?.let {
                    homeScreenData.totalSteps = it
                }
            }
            if (STConstants.IS_SYNC_INITIALLY) {
                STConstants.IS_SYNC_INITIALLY = false
                // Whats New
                invokeIntent(
                    STHomeIntent.WhatsNewOnBoardingScreen(
                        STUtils.getVersionCode(activityContext).toString()
                    )
                )
            }
            updateUI()
        }
    }

    @OnClick(R.id.closeNotification)
    fun closeNotification() {
        notificationTextHomeLayout.gone()
        invokeIntent(STHomeIntent.MarkHomeNotificationRead)
    }

    @OnClick(R.id.closeNotification)
    fun openActiveMinutesActivity() {
        //notificationTextHomeLayout.gone()
        //  invokeIntent(STHomeIntent.MarkHomeNotificationRead)
    }

    private fun updateUI() {
        requestDidFinish()
        STUtils.setValueToView(day, STUtils.getFormattedDate(Calendar.getInstance(), "EEEE"))
        STUtils.setValueToView(
            date,
            STUtils.getFormattedDate(Calendar.getInstance(), "dd MMM yyyy")
        )
        STApplication.homeScreenData?.let { homeScreenData ->
            homeScreenData.rewards?.let {
                if (it.size > 1 && it.isNotEmpty()) {
                    indicator?.visible()
                } else {
                    indicator?.gone()
                }
                rewardPagerAdapter?.setFeaturedRewardsData(it as ArrayList<STFeaturedData>)
                setUpTimer(it)
            }
            lifeTimeStepsValue = homeScreenData.totalSteps!!.toInt()
//            lifeTimeSteps.text =
//                STUtils.formatNumber(featuredRewardsResponseData.totalSteps!!.toInt())
//            lifeTimeStepsTitle.text =
//                STUtils.formatNumber(featuredRewardsResponseData.totalSteps!!.toInt())
            dailyGoalValue = homeScreenData.dailyGoal?.toInt()
//            dailyGoal.text = (dailyGoalValue?.minus(stepsToday)).toString()
            this.lastSyncStamp = homeScreenData.lastSyncStamp
            homeScreenData.steps?.let { stepsData ->
                /*if (null == STPreference.getFitnessDevice(activityContext)) {
                    STPreference.saveFitnessDevice(activityContext, stepsData.fitnessDeviceId)
                }*/
//                Log.e("stepsDataHome", "stepsDataHome : " + stepsData.steps)
            }
            homeScreenData.notification?.let {
                if (it == "") {
                    notificationTextHomeLayout.gone()
                } else {
                    notificationTextHomeLayout.visible()
                    notificationTextHome.text = it
                }
            } ?: kotlin.run {
                notificationTextHomeLayout.gone()
            }
            homeScreenData.isDubaiFitnessAvailable?.let { isDubaiFitnessAvailable ->
                if (isDubaiFitnessAvailable) {
                    dfcLayout.visible()
                } else {
                    dfcLayout.gone()
                }
            } ?: kotlin.run {
                dfcLayout.gone()
            }
            progressBar.max = homeScreenData.dailyGoal?.toInt() ?: 0
            progressBar.secondaryProgress = homeScreenData.dailyGoal?.toInt() ?: 0
            homeScreenData.steps?.let { stepsData ->
                STPreference.getUnitSelected(activityContext)?.let { unitSelected ->
                    if (unitSelected == getString(R.string.kilometer)) {
                        distanceKm?.text = NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(stepsData.distance!!.toDouble() / 1000).setScale(
                                    4,
                                    RoundingMode.HALF_EVEN
                                )
                            )
                        distanceLabel.text = getString(R.string.distance_label_km)
                    } else {
                        distanceKm?.text = NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(STUtils.getMiles(stepsData.distance!!.toDouble())).setScale(
                                    4,
                                    RoundingMode.HALF_EVEN
                                )
                            )
                        distanceLabel.text = getString(R.string.distance_label_mile)
                    }
                }
                stepsData.calories?.let {
                    calory?.text = STUtils.formatNumber(it.toInt())
                }
                stepsData.activeMinutes?.let {
                    timeDistance?.text = STUtils.formatNumber(it.toInt())
                } ?: kotlin.run {
                    timeDistance?.text = "0"
                }
                stepsData.steps?.let {
                    if ((dailyGoalValue?.minus(it.toInt()))!! <= 0) {
                        dailyGoal.gone()
                        dailyGoalMaximum.gone()
                        dailyGoalMaximumLabel.gone()
                        dailyGoalLabel.text = activityContext.getString(R.string.reached_daily_goal)
                        dailyGoalReachedDialog()
                    } else {
                        dailyGoal.visible()
                        dailyGoalMaximum.visible()
                        dailyGoalMaximumLabel.visible()
                        stepsData.steps?.let {
                            dailyGoal.text =
                                STUtils.formatNumber(dailyGoalValue?.minus(it.toInt()) as Int)
                        } ?: kotlin.run {
                            dailyGoal.text = STUtils.formatNumber(dailyGoalValue)
                        }
                        dailyGoalMaximum.text = STUtils.formatNumber(dailyGoalValue)
                        dailyGoalLabel.text =
                            activityContext.getString(R.string.steps_to_reach_daily_goal)
                    }
                } ?: kotlin.run {
                }
                remainingGoalContainer.visible()
                setSummaryData(stepsData.steps!!.toInt())
            }
        } ?: kotlin.run {
            progressBar.max = 0
            progressBar.secondaryProgress = 0
        }
    }

    private fun setUpTimer(featuredDataList: List<STFeaturedData>) {
        featuredDataList.let {
            val handler = Handler()
            val update = Runnable {
                if (currentPage == it.size) {
                    if (it.size > 1 && it.isNotEmpty()) {
                        indicator?.visible()
                    } else {
                        indicator?.gone()
                    }
                    currentPage = 0
                }
                if (it.size > 1)
                    featuredRewardPager?.setCurrentItem(currentPage++, true)
            }
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, delayInMilliSeconds, periodInMilliSeconds)
        }
    }

    private fun dailyGoalReachedDialog() {
        val goalsReachedDialog: Dialog = dailyGoalReachedDialog {
            closeClick {
                dismissDialog()
            }
            confirmClick {
                dismissDialog()
            }
            setAnimation(activityContext)
        }
        goalsReachedDialog.setSize(activityContext)
        goalsReachedDialog.setCancelable(false)
        goalsReachedDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        goalsReachedDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        goalsReachedDialog.show()
    }

    private fun setFeaturedRewardsList(featuredRewardsResponse: STFeaturedRewardsResponse) {
        setUpRewardPager()
        featuredRewardsResponse.data?.let { featuredRewardsResponseData ->
            this.lastSyncStamp = featuredRewardsResponseData.lastSyncStamp
            STApplication.homeScreenData = featuredRewardsResponseData
            //homeScreenData = featuredRewardsResponseData
            /*homeScreenData?.let {
                it.rewards?.let {
                    if (it.size > 1)
                        indicator.visibility = View.VISIBLE
                    rewardPagerAdapter?.setFeaturedRewardsData(it as ArrayList<STFeaturedData>)
                }
                lifeTimeStepsValue = it.totalSteps!!.toInt()
//            lifeTimeSteps.text =
//                STUtils.formatNumber(featuredRewardsResponseData.totalSteps!!.toInt())
//            lifeTimeStepsTitle.text =
//                STUtils.formatNumber(featuredRewardsResponseData.totalSteps!!.toInt())
                dailyGoalValue = it.dailyGoal?.toInt()
//            dailyGoal.text = (dailyGoalValue?.minus(stepsToday)).toString()
                this.lastSyncStamp = it.lastSyncStamp
                it.steps?.let { stepsData ->
                    *//*if (null == STPreference.getFitnessDevice(activityContext)) {
                        STPreference.saveFitnessDevice(activityContext, stepsData.fitnessDeviceId)
                    }*//*
                    Log.e("stepsDataHome", "stepsDataHome : " + stepsData.steps)
                }
                progressBar.max = it.dailyGoal?.toInt() ?: 0
                progressBar.secondaryProgress = it.dailyGoal?.toInt() ?: 0
                it.steps?.let { stepsData ->
                    //                    progressBar.progress = stepsData.steps!!.toInt()
//                    stepsTodayCount?.text = STUtils.formatNumber(stepsData.steps!!.toInt())
                    distanceKm?.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(stepsData.distance!!.toDouble())
                    stepsData.calories?.let {
                        calory?.text = STUtils.formatNumber(it.toInt())
                    }

                    if ((dailyGoalValue?.minus(stepsData.steps!!.toInt()))!! <= 0) {
                        dailyGoal.gone()
                        dailyGoalLabel.text = activityContext.getString(R.string.reached_daily_goal)
                    } else {
                        dailyGoal.visible()
                        dailyGoal.text =
                            STUtils.formatNumber(dailyGoalValue?.minus(stepsData.steps!!.toInt()) as Int)
                        dailyGoalLabel.text =
                            activityContext.getString(R.string.steps_to_reach_daily_goal)
                    }
                    remainingGoalContainer.visibility = View.VISIBLE
                    //setSummaryData(stepsData.steps!!.toInt())
                }
            } ?: kotlin.run {
                progressBar.max = 0
                progressBar.secondaryProgress = 0
            }*/
        }
        init()
    }

    private fun setUpRewardPager() {
        rewardPagerAdapter = STRewardPagerAdapter(childFragmentManager)
        featuredRewardPager.adapter = rewardPagerAdapter
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            pagerLayout.rotationY = 180F
        }
        indicator?.setViewPager(featuredRewardPager)
    }

    private fun init() {
        lastSyncStamp?.let { lastSyncStampValue ->// 2020-07-10T09:39:26.851Z
//            val timeValueTest = "2020-07-12T23:58:26.851Z"
            val lastSyncDate = STUtils.getSyncedDateFromStringMethodSeconds(lastSyncStampValue)
//            Log.e("lastSyncDate", "lastSyncDate : $lastSyncDate")
            val lastSyncDay = Calendar.getInstance()
            lastSyncDay.time = lastSyncDate!!
            val cal = Calendar.getInstance()
            val endTime = cal.timeInMillis
//            Log.e("lastSyncDate","endTime : $endTime")
            val startTime = lastSyncDay.timeInMillis
            selection = STPreference.getFitnessDevice(activityContext)
            initTrackingSdk(startTime, endTime)
        } ?: kotlin.run {
//            calendarSetPrevious(0)
        }
    }

//    private fun initOld() {
//        STUtils.setValueToView(day, STUtils.getFormattedDate(Calendar.getInstance(), "EEEE"))
//        STUtils.setValueToView(
//            date,
//            STUtils.getFormattedDate(Calendar.getInstance(), "dd MMM yyyy")
//        )
//        lastSyncStamp?.let { lastSyncStampValue ->
//            val lastSyncDate = STUtils.getSyncedDateFromString(lastSyncStampValue)
//            val lastSyncDay = Calendar.getInstance()
//            lastSyncDay.time = lastSyncDate!!
//            val today = Calendar.getInstance()
//            val diff = today.timeInMillis - lastSyncDay.timeInMillis //result in millis
//            var days = diff / (24 * 60 * 60 * 1000)
//            if (days < 0) {
//                days = 0
//            } else {
//                if (days != 0L)
//                    days += 1
//            }
//            /*else if (days >= 4)
//                days = 4*/
////            calendarSetPrevious(days.toInt())
//        } ?: kotlin.run {
////            calendarSetPrevious(0)
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        if (!lastSyncStamp.isNullOrBlank()) {
//            init()
//        }

//        if (GlobalEnvSetting.isHms()) {//on Huawei device
//            //if steppi does not have permission redirect user to huawei authorization screen
//            huaweiHealthDataSource?.let {
//                //when coming back from huawei health and user denied permissions, don't ask again for authorization
//                if (requestHuaweiHealthAuthorization) {
//                    //if STEPPI has permission [dailySummary] will be invoked to return result to previous activity
//                    it.checkForUserPermission(false)
//                    requestHuaweiHealthAuthorization = false
//                }
//            }
//        }
//    }

//    private fun calendarSetPrevious(value: Int) {
//        val cal = Calendar.getInstance()
//        val endTime = cal.timeInMillis
//        if (value != 0)
//            cal.add(Calendar.DATE, -value)
//        cal.set(Calendar.HOUR_OF_DAY, 0)
//        cal.set(Calendar.MINUTE, 0)
//        cal.set(Calendar.SECOND, 0)
//        cal.set(Calendar.MILLISECOND, 0)
//        val startTime = cal.timeInMillis
////        Log.e("lastSyncDate", "lastSyncDate : startTime : $startTime")
////        Log.e("lastSyncDate", "lastSyncDate : endTime : $endTime")
////        calenderFrom.timeInMillis = startTime
////        calenderTo.timeInMillis = endTime
//
//        selection = STPreference.getFitnessDevice(activityContext)
//        initTrackingSdk(startTime, endTime)
//    }

    private fun initTrackingSdk(startTime: Long, endTime: Long) {
        when (selection) {
            STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                // In the previous version, Fitness device name is being saved as "Google Fit" even in case of Huawei Health,
                // so this check is to differentiate if user has already setup the Huawei Health and try to upgrade their app
                // This check can be discarded, in the coming releases
                if (STPreference.getFitnessDeviceName(activityContext) == STConstants.HUAWEI_DEVICE_NAME)
                    initHuaweiHealth(startTime, endTime)
                else
                    initGoogleFit(startTime, endTime)
            }
            STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> initHuaweiHealth(startTime, endTime)
            STUtils.EnumFitnessDevice.FITBIT.name -> initFitBit(startTime, endTime)
            STUtils.EnumFitnessDevice.SAMSUNG_HEALTH.name -> initSamsung(startTime, endTime)
            STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> {
                homePagePullRefresh?.isRefreshing = false
                updateUI()
            }
            else -> {
                homePagePullRefresh?.isRefreshing = false
                requestDidFinish()
                deviceNotConnectedDialog()
                /*homeScreenData?.steps?.let {
                    setSummaryData(it.steps!!.toInt())
                }*/
//                showToast(activityContext.resources.getString(R.string.please_choose_any_device))
            }
        }
    }

    private fun deviceNotConnectedDialog() {
        val deviceNotConnectedDialog: Dialog = deviceNotConnectedDialog {
            closeClick {
                dismissDialog()
                updateUI()
            }
            confirmClick {
                dismissDialog()
                val otpIntent = Intent(activityContext, STChooseDeviceActivity::class.java)
                startActivityForResult(
                    otpIntent, STConstants.REQUEST_CODE_DEVICE_CONNECTION
                )
            }
        }
        deviceNotConnectedDialog.setSize(activityContext)
        deviceNotConnectedDialog.setCancelable(false)
        deviceNotConnectedDialog.show()
    }

    private fun initGoogleFit(startTime: Long, endTime: Long) {
        googleFitDataSource = GoogleFitDataSource(
            WeakReference(activityContext), this,
            startTime, endTime
        )
    }

    private fun initFitBit(startTime: Long, endTime: Long) {
        fitBitDataSource = FitBitDataSource(
            WeakReference(activityContext), this,
            startTime, endTime
        )
    }

    private fun initHuaweiHealth(startTime: Long, endTime: Long) {
        //if (huaweiHealthDataSource == null) {//avoid double initializing
        // requestHuaweiHealthAuthorization = true
        huaweiHealthDataSource = HuaweiHealthDataSource(
            WeakReference(activityContext), this,
            startTime, endTime
        )
        //}
    }

    private fun initSamsung(startTime: Long, endTime: Long) {
        samsungDataSource = SamsungHealthKitDataSource(
            WeakReference(activityContext), this,
            startTime,
            endTime
        )
        samsungDataSource?.init()
    }

    private fun setUpAppbarLayout() {
        homePagePullRefresh.setOnRefreshListener(this)
        homePagePullRefresh.setColorSchemeResources(
            R.color.theme_color,
            R.color.theme_color,
            R.color.theme_color,
            R.color.theme_color
        )
        appBarLayout.setExpanded(false)
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.visibility = View.VISIBLE
                    isShow = true
                } else if (isShow) {
                    toolbar.visibility = View.INVISIBLE
                    isShow = false
                }
                homePagePullRefresh.isEnabled = verticalOffset == 0
            }
        })
    }

    private fun setCategories(
        categoryResponse: STCategoryResponse,
        isNotRefresh: Boolean? = false
    ) {
        categoryResponse.data?.let {
            categoryListAdapter?.setCategory(it as ArrayList<STCategory>, null)
            if (activityContext is STHomeActivity) {
                (activityContext as STHomeActivity).setCategoryList(it as ArrayList<STCategory>)
            }
        }
        val controller = AnimationUtils.loadLayoutAnimation(
            activityContext,
            R.anim.layout_animation_left_to_right
        )
        steppiEventTypeList.layoutAnimation = controller
        categoryListAdapter?.notifyDataSetChanged()
        steppiEventTypeList.scheduleLayoutAnimation()
        if (!isNotRefresh!!)
            invokeIntent(STHomeIntent.GetHomeScreenFeaturedRewards)
    }

    private fun setCategoryList() {
        steppiEventTypeList.setHorizontalLayoutManager()
        steppiEventTypeList.setHorizontalItemDecoration(
            space = STUtils.getDimen(activityContext, R.dimen.margin_small),
            initialPadding = STUtils.getDimen(activityContext, R.dimen.margin_normal_xx),
            isRtl = STPreference.getLanguageCode(activityContext) == STConstants.ENGLISH_CODE
        )
        categoryListAdapter = STItemEventListAdapter(activityContext)
        steppiEventTypeList.adapter = categoryListAdapter
        categoryListAdapter?.setClickListener(object :
            STItemEventListAdapter.OnItemClickListener {
            override fun onItemClick(selectedReward: STCategory?, selectedPosition: Int?) {
                showReward(selectedReward!!, selectedPosition!!)
            }
        })
//        categoryListAdapter?.setOnItemClickListener {
//            showReward(it)
//        }

        if (categoryListAdapter?.getRewards()?.size ?: 0 > 0) {
            showReward(categoryListAdapter?.getRewards()!![0], 0, true)
        }
        STPreference.getUnitSelected(activityContext)?.let { unitSelected ->
            if (unitSelected == getString(R.string.kilometer)) {
                distanceLabel.text = getString(R.string.distance_label_km)
            } else {
                distanceLabel.text = getString(R.string.distance_label_mile)
            }
        }
    }

    @OnClick(R.id.dfcLayout)
    fun dfcLayoutCLick() {
        showDfcChallenge()
    }

    private fun showDfcChallenge(
        needToSetBottomMenu: Boolean = true
    ) {
        (activityContext as STHomeActivity).showDfcChallenge(needToSetBottomMenu)
    }

    @OnClick(R.id.analyticsDistance)
    fun analyticsDistance() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.distance))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_DISTANCE)
        startActivity(container)
    }

    @OnClick(R.id.activityMinutes)
    fun analyticsMinutes() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.active_minutes))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_MINUTES)
        startActivity(container)
    }

    @OnClick(R.id.analyticsCalorie)
    fun analyticsCalorie() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.calories))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_CALORIES)
        startActivity(container)
    }

    @OnClick(R.id.progressViewContainer)
    fun analyticsSteps() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.steps))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_STEPS)
        startActivity(container)
    }

    private fun showReward(
        rewardMenuModel: STCategory,
        selectedPosition: Int = 0,
        needToSetBottomMenu: Boolean = true
    ) {
        (activityContext as STHomeActivity).showReward(
            rewardMenuModel, selectedPosition,
            needToSetBottomMenu
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_WHATS_NEW == requestCode) {
                val whatsNewVersionUpdateRequest = STWhatsNewVersionUpdateRequest()
                whatsNewVersionUpdateRequest.version = STUtils.getVersionCode(activityContext)
                invokeIntent(STHomeIntent.WhatsNewVersionUpdate(whatsNewVersionUpdateRequest))
            }
        }
        googleFitDataSource?.onActivityResult(requestCode, resultCode, data)
        fitBitDataSource?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccess(stepCount: Int, calorieCount: Float, distance: Float) {
//        Log.e("Success=> ", "FitnessDataSuccess")
    }

    override fun onError(error: String) {
        Log.e("Error=> ", error)
        requestDidFinish()
        homePagePullRefresh?.isRefreshing = false
        updateUI()
        if (error == STConstants.FITBIT_TOKEN_EXPIRED_OR_INVALID) {
            val intent =
                Intent(activityContext, STDeviceConnectionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStepCountReceived(stepCount: Int, userInput: Int) {
//        this.stepsToday = stepCount - userInput
//        if ((dailyGoalValue?.minus(stepsToday))!! <= 0) {
//            dailyGoal.gone()
//            dailyGoalLabel.text = activityContext.getString(R.string.reached_daily_goal)
//        } else {
//            dailyGoal.visible()
//            dailyGoal.text = (dailyGoalValue?.minus(stepsToday)).toString()
//            dailyGoalLabel.text = activityContext.getString(R.string.steps_to_reach_daily_goal)
//        }
//        setSummaryData()
    }

    override fun onCalorieReceived(calorie: Float, userInput: Float) {
//        calory?.text = calorie.toInt().toString()
    }

    override fun onDistanceReceived(distance: Float, userInput: Float) {
//        distanceKm?.text = STUtils.decimalFOrmatter(distance)
    }

    private fun initSummary() {
//        stepCountContainer.invisible()
//        remainingGoalContainer.invisible()
//        activityDistance.invisible()
//        activityMinutes.invisible()
//        activityCalorie.invisible()
    }

    private fun setSummaryData(stepsToday: Int) {
//        remainingGoalContainer.visibility = View.VISIBLE
        if (stepsToday == 0) {
            dailyGoal.text = STUtils.formatNumber(10000)
        }
        summaryAnimator = animateSequentially(
            arrayListOf(
//                stepCountContainer.animateZoomIn(duration = 600),
//                progressBar.animateProgress(stepsToday),
                AnimatorSet().apply {
                    playTogether(
                        lifeTimeSteps.animateCount(lifeTimeStepsValue!!, duration = 1800),
                        lifeTimeStepsTitle.animateCount(lifeTimeStepsValue!!, duration = 1800),
                        progressBar.animateProgress(stepsToday, duration = 1800),
                        stepsTodayCount.animateCount(stepsToday, duration = 1800)
                    )
                }, remainingGoalContainer.animateWidth(duration = 600),
                analyticsDistance.animateEnableDisable(),
                activityMinutes.animateEnableDisable(),
                analyticsCalorie.animateEnableDisable()
            )
        )
    }

    override fun onDestroyView() {
        summaryAnimator?.cancel()
        summaryAnimator = null
        fitBitDataSource?.onDestroy()
        timer?.cancel()
        timer = null
        super.onDestroyView()
    }

    override fun dailySummary(dataMap: HashMap<String, STFitnessDataModel>) {
        stepsArray.clear()
        stepsArrayData.clear()
        dataMap.forEach {
            Log.d(
                "TimeCheckMap",
                "Date: ${it.key} Steps: ${it.value.steps} Calorie: ${it.value.callorie}" +
                        " distance: ${it.value.distance}"
            )
            val stepsData = STStepsRequestData()
            stepsData.steps = it.value.steps.toInt() - it.value.stepsUserInput.toInt()
            stepsData.calories = it.value.callorie.toInt() - it.value.callorieUserInput.toInt()
            stepsData.distance =
                it.value.distance.toDouble() - it.value.distanceUserInput.toDouble()
            stepsData.date = STUtils.getDateEn(it.key, "yyyy-mm-dd", "mm/dd/yyyy")//it.key
            stepsData.provider = STPreference.getFitnessDeviceID(activityContext)
            stepsData.activeMinutes = it.value.activeMinutes.toInt()
            stepsArray.add(stepsData)
//            if (stepsData.steps != 0 || stepsData.calories != 0 || stepsData.distance != 0.0 ||
//            stepsData.activeMinutes != 0) {
//                stepsArrayData.add(stepsData)
//            }
            if (stepsData.steps == 0 && stepsData.calories == 0 && stepsData.distance == 0.0 &&
                stepsData.activeMinutes == 0
            ) {
            } else {
                stepsArrayData.add(stepsData)
            }
        }
        syncFitnessDataRequest.logs = stepsArray
        if (!stepsArrayData.isNullOrEmpty()) {
            invokeIntent(STHomeIntent.GetFitnessData(syncFitnessDataRequest))
        } else {
            updateUI()
        }
    }
}
