package com.steppi.steppifitness.ui.fitness_analytics

import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STActivityDetailsAdapter
import com.steppi.steppifitness.adapter.STAnalyticsPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.steps_analytics.STStepsAnalyticsResponse
import com.steppi.steppifitness.network.response.steps_analytics.StepsAnalyticsData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.fitness_analytics.mvi.STAnalyticsController
import com.steppi.steppifitness.ui.fitness_analytics.mvi.STAnalyticsIntent
import com.steppi.steppifitness.ui.fitness_analytics.mvi.STAnalyticsState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_activity_details.*
import kotlinx.android.synthetic.main.header_layout.*
import java.util.*
import kotlin.collections.ArrayList

class STFitnessAnalyticsFragment :
    STBaseViewModelFragment<STAnalyticsIntent, STAnalyticsState, STAnalyticsController>(
        STAnalyticsController::class.java
    ) {
    private var activityDetailsAdapter: STActivityDetailsAdapter? = null
    private var analyticsType: String? = null
    private var analyticsPagerAdapter: STAnalyticsPagerAdapter? = null
    private var previousDate: String? = null
    private var previousDateValue: String? = null
    private var stepsArrayCountList: List<List<StepsAnalyticsData>>? = null
    private var stepsArrayCountToAdd: MutableList<List<StepsAnalyticsData>>? = ArrayList()
    private var stepsArrayCount: ArrayList<StepsAnalyticsData>? = ArrayList()
    private var stepsString: ArrayList<String>? = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_activity_details
    }

    override fun initViews() {
        initData()
        steppiActivityDetailsList.layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)

    }

    override fun onViewModelReady() {
        val date = STUtils.getFormattedDateEn(Calendar.getInstance(), "MM/dd/yyyy")
        invokeIntent(
            STAnalyticsIntent.GetAnalyticsData(
                STUtils.getFormattedDate(
                    Calendar.getInstance(),
                    date
                )
            )
        )
    }

    override fun processState(state: STAnalyticsState) {
        when (state) {
            is STAnalyticsState.Loading -> requestDidStart()
            is STAnalyticsState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STAnalyticsState.AnalyticsList -> {
                requestDidFinish()
                setAnalyticsData(state.stepsAnalyticsResponse)
            }
        }
    }

    private fun setAnalyticsData(stepsAnalyticsResponse: STStepsAnalyticsResponse) {
        setUpAnalyticsPager()
        setUpAnalyticDetailsList()
        stepsAnalyticsResponse.data?.let { stepsAnalyticsList ->
            if (stepsAnalyticsList.isNullOrEmpty()) {
                noDataLayout.visible()
                pagerMainLayout.gone()
            } else {
                noDataLayout.gone()
                pagerMainLayout.visible()
            }
            stepsArrayCountList =
                STUtils.chopped(Collections.unmodifiableList(stepsAnalyticsList), STConstants.SEVEN)
            stepsArrayCountList?.let { stepsArrayCountList ->
                if (!stepsArrayCountList.isNullOrEmpty()) {
                    for (position in stepsArrayCountList.indices) {
                        if (stepsArrayCountList[position].size < STConstants.SEVEN) {
                            stepsArrayCountList[position].let {
                                stepsArrayCount = it as ArrayList<StepsAnalyticsData>
                                previousDate = stepsArrayCount!![stepsArrayCount?.size!! - 1].date
                                for (i in 0..(6 - stepsArrayCount?.size!!)) {
                                    val analyticsData: StepsAnalyticsData? = StepsAnalyticsData()
                                    analyticsData?.steps = STConstants.ZERO.toString()
                                    analyticsData?.calories = STConstants.ZERO.toString()
                                    analyticsData?.distance = STConstants.ZERO.toString()
                                    previousDateValue = STUtils.setPreviousDate(previousDate)
                                    analyticsData?.date = previousDateValue
                                    previousDate = previousDateValue
                                    stepsArrayCount?.add(analyticsData!!)
                                }
                                stepsArrayCountToAdd?.add(stepsArrayCount!!.toList())
                            }
                        } else {
                            stepsArrayCountToAdd?.add(stepsArrayCountList[position])
                        }
                    }
                    analyticsPagerAdapter?.setStepsAnalytics(stepsArrayCountToAdd as ArrayList<List<StepsAnalyticsData>>)
                    stepsArrayCountToAdd?.let {
                        if (!it[0].isNullOrEmpty()) {
                            setInitialPosition(
                                it.size - 1,
                                it[0] as ArrayList<StepsAnalyticsData>
                            )
                        }
                    }
                }
            }
            stepsArrayCountToAdd?.reverse()
            stepsArrayCountToAdd?.let { stepsArrayCountList ->
                if (!stepsArrayCountList.isNullOrEmpty()) {
                    for (position in stepsArrayCountList.indices) {
                        viewPager.onPageSelected { pagerPosition ->
                            if (pagerPosition == position) {
                                setNextPreviousButton(position)
                                stepsArrayCountList[position].let { stepsAnalyticsList ->
                                    setDateLabel(stepsAnalyticsList)
                                    activityDetailsAdapter?.setStepsAnalyticsList(stepsAnalyticsList as ArrayList<StepsAnalyticsData>)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setInitialPosition(
        position: Int,
        stepsArrayList: ArrayList<StepsAnalyticsData>
    ) {
        viewPager.currentItem = position
        setNextPreviousButton(position)
//        viewPager.setOnTouchListener { v, event ->
//            return@setOnTouchListener true
//        }
        setDateLabel(stepsArrayList.reversed())
        if (STUtils.getDate(stepsArrayList[0].date) < STUtils.getDate(
                stepsArrayList[1].date
            )
        ) {
            stepsArrayList.reverse()
        }
        activityDetailsAdapter?.setStepsAnalyticsList(stepsArrayList.reversed() as ArrayList<StepsAnalyticsData>)
    }

    private fun setNextPreviousButton(position: Int) {
        stepsArrayCountToAdd?.let {
            if (it.size > 1) {
                if (position <= 0) {
                    arrowPrevious.setImageResource(R.color.transparent)
                    arrowPreviousLayout.gone()
                    arrowNext.setImageResource(R.drawable.arrow_next_green)
                    arrowNextLayout.visible()
                } else {
                    if (position >= it.size - 1) {
                        arrowNext.setImageResource(R.color.transparent)
                        arrowNextLayout.gone()
                        arrowPrevious.setImageResource(R.drawable.arrow_previous_green)
                        arrowPreviousLayout.visible()
                    } else {
                        arrowNext.setImageResource(R.drawable.arrow_next_green)
                        arrowNextLayout.visible()
                        arrowPrevious.setImageResource(R.drawable.arrow_previous_green)
                        arrowPreviousLayout.visible()
                    }
                }
            } else {
                arrowPrevious.setImageResource(R.color.transparent)
                arrowNext.setImageResource(R.color.transparent)
                arrowPreviousLayout.gone()
                arrowNextLayout.gone()
            }
        }
    }

    var count = 0
    private fun setDateLabel(stepsAnalyticsList: List<StepsAnalyticsData>) {
        stepsString?.clear()
        stepsAnalyticsList.let { stepsAnalyticsListData ->
            startDate.text = STUtils.getDate(
                stepsAnalyticsListData[STConstants.ZERO].date,
                "yyyy-MM-dd",
                "dd MMMM"
            )
            endDate.text = STUtils.getDate(
                stepsAnalyticsListData[STConstants.SIX].date,
                "yyyy-MM-dd",
                "dd MMMM"
            )
            for (i in stepsAnalyticsListData.indices) {
                when (analyticsType) {
                    STConstants.ANALYTICS_TYPE_STEPS -> {
                        stepsString?.add(stepsAnalyticsListData[i].steps!!)

                    }
                    STConstants.ANALYTICS_TYPE_DISTANCE -> {
                        stepsString?.add(stepsAnalyticsListData[i].distance!!)
                    }
                    STConstants.ANALYTICS_TYPE_CALORIES -> {
                        stepsString?.add(stepsAnalyticsListData[i].calories!!)
                    }
                    STConstants.ANALYTICS_TYPE_ACTIVEMINUTES -> {
                        stepsString?.add(stepsAnalyticsListData[i].calories!!)
                    }
                }
            }
        }
    }

    private fun initData() {
        arguments?.getString(STConstants.ANALYTICS_TYPE)?.let {
            analyticsType = it

        }
    }

    private fun setUpAnalyticsPager() {
        analyticsPagerAdapter = STAnalyticsPagerAdapter(childFragmentManager, analyticsType)
        viewPager.adapter = analyticsPagerAdapter
    }

    private fun setUpAnalyticDetailsList() {
        activityDetailsAdapter = STActivityDetailsAdapter(activityContext, analyticsType)
        steppiActivityDetailsList.adapter = activityDetailsAdapter
    }

    @OnClick(R.id.arrowPreviousLayout)
    fun previous() {
        viewPager.setCurrentItem(getItem(-1), true)
        setNextPreviousButton(viewPager.currentItem)
    }

    @OnClick(R.id.arrowNextLayout)
    fun next() {
        viewPager.setCurrentItem(getItem(+1), true)
        setNextPreviousButton(viewPager.currentItem)
    }

    private fun getItem(i: Int): Int {
        return viewPager.currentItem + i
    }
}
