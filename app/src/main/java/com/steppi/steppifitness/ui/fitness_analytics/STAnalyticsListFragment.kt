package com.steppi.steppifitness.ui.fitness_analytics

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.steps_analytics.StepsAnalyticsData
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.STUtils.replaceArabicNumbers
import kotlinx.android.synthetic.main.fragment_analytics_list.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.math.floor

class STAnalyticsListFragment : STBaseFragment() {
    private var stepsArrayCount: ArrayList<StepsAnalyticsData>? = null
    private var yOffsetValue = 11f
    private var analyticsType: String? = null
//    val isFromDistance: Boolean? = true

    override fun getLayoutId(): Int {
        return R.layout.fragment_analytics_list
    }

    override fun initViews() {
        initData()
    }

    private fun initData() {
        arguments?.getString(STConstants.ANALYTICS_TYPE)?.let {
            analyticsType = it
        }
        arguments?.getSerializable(STConstants.ANALYTICS_DATA)?.let {
            stepsArrayCount = it as ArrayList<StepsAnalyticsData>
            if (STUtils.getDate(stepsArrayCount!![0].date) > STUtils.getDate(stepsArrayCount!![1].date)) {
                stepsArrayCount!!.reverse()
            }
            setBarChartData()
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            if (stepsArrayCount != null) {
                setBarChartData()
            }
        }
    }

    private fun setBarChartData() {
        barChart.clear()
        setBarChart(stepsArrayCount)
    }

    private var isGranularity: Boolean? = false
    private fun setBarChart(stepsArrayCount: ArrayList<StepsAnalyticsData>?) {
        val entries = ArrayList<BarEntry>()
        val xAxisLabel = ArrayList<String>()
        for (position in stepsArrayCount!!.indices) {
            stepsArrayCount[position].let { stepsArrayCountPosition ->
                analyticsType?.let { analyticsType ->
                    when (analyticsType) {
                        STConstants.ANALYTICS_TYPE_STEPS -> {
                            entries.add(
                                BarEntry(
                                    stepsArrayCountPosition.steps!!.toFloat(),
                                    position
                                )
                            )
                        }

                        STConstants.ANALYTICS_TYPE_ACTIVEMINUTES -> {
                            entries.add(
                                BarEntry(
                                    stepsArrayCountPosition.activeMinutes!!.toFloat(),
                                    position
                                )
                            )
                        }

                        STConstants.ANALYTICS_TYPE_CALORIES -> {
//                        entries.add(BarEntry(it.calories!!.toDouble(), position))
                            entries.add(
                                BarEntry(
                                    BigDecimal(stepsArrayCountPosition.calories!!.toDouble()).setScale(
                                        4,
                                        RoundingMode.HALF_EVEN
                                    ).toFloat(), position
                                )
                            )
                        }
                        STConstants.ANALYTICS_TYPE_DISTANCE -> {
                            STPreference.getUnitSelected(activityContext)?.let { unitSelected ->
                                if (unitSelected == activityContext.getString(R.string.kilometer)) {
                                    if ((stepsArrayCountPosition.distance!!.toDouble() / 1000) > 1) {
                                        isGranularity = true
                                    }
                                    entries.add(
                                        BarEntry(
                                            BigDecimal(stepsArrayCountPosition.distance!!.toDouble() / 1000).setScale(
                                                4,
                                                RoundingMode.HALF_EVEN
                                            ).toFloat(), position
                                        )
                                    )
                                } else {
                                    entries.add(
                                        BarEntry(
                                            BigDecimal(STUtils.getMiles(stepsArrayCountPosition.distance!!.toDouble())).setScale(
                                                4,
                                                RoundingMode.HALF_EVEN
                                            ).toFloat(), position
                                        )
                                    )

                                }
                            }
                        }
                        else -> {
                            entries.add(
                                BarEntry(
                                    stepsArrayCountPosition.steps!!.toFloat(),
                                    position
                                )
                            )
                        }
                    }
                }
                xAxisLabel.add(STUtils.getDate(stepsArrayCountPosition.date, "yyyy-MM-dd", "EEE"))
            }
        }
        val barDataSet = BarDataSet(entries, "Cells")

        barDataSet.let {
            val data = BarData(xAxisLabel, it)
            barChart.data = data // set the data and list of lables into chart
            barChart.setDescription("")  // set the description
        }
        barDataSet.color = STUtils.getColor(activityContext, R.color.button_bg_enabled_color)
        barDataSet.setDrawValues(false)
        barDataSet.barSpacePercent = 30f
        barChart.animateY(500)
//        barChart.xAxis.isEnabled = false
//        barChart.axisRight.isEnabled = false
        barChart.setPinchZoom(false)
        barChart.setTouchEnabled(false)
        barChart.isDragEnabled = false
        barChart.setScaleEnabled(false)
        barChart.isScaleXEnabled = false
        !barChart.xAxis.isDrawAxisLineEnabled
        barChart.xAxis.axisLineColor = STUtils.getColor(activityContext, R.color.text_white)
        barChart.xAxis.axisLineWidth = 1F
        barChart.xAxis.gridColor = STUtils.getColor(activityContext, R.color.transparent)
        barChart.xAxis.textColor = STUtils.getColor(activityContext, R.color.text_white)
        if (STPreference.getLanguageCode(activityContext)!! == STConstants.ARABIC_CODE)
            barChart.xAxis.textSize = 6F
        barChart.xAxis.disableGridDashedLine()
        barChart.setDrawGridBackground(false)
//        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.axisRight.setDrawGridLines(false)
        // hide legend
        val legend = barChart.legend
        legend.isEnabled = false
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(true)
        xAxis.yOffset = yOffsetValue
        val yAxis: YAxis = barChart.axisLeft // Show left y-axis line
        yAxis.gridColor = STUtils.getColor(activityContext, R.color.black_transparent)
        yAxis.axisLineColor = STUtils.getColor(activityContext, R.color.text_white)
        yAxis.axisLineWidth = 1F
        yAxis.textColor = STUtils.getColor(activityContext, R.color.text_white)
        yAxis.setAxisMinValue(0F)
        barChart.axisLeft.setValueFormatter { value, yAxis ->
            floor(
                value.toDouble()
            ).toInt().toString().replaceArabicNumbers()
        }
//        if (isGranularity!!) {
//            yAxis.granularity = 1f
//        } else {
//            yAxis.granularity = .2f
//        }
        barChart.isClickable = false
        barChart.fitScreen()

//        yAxis.valueFormatter = MyYAxisValueFormatter()

    }
}
