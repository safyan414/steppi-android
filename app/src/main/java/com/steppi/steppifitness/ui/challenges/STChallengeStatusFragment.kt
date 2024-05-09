package com.steppi.steppifitness.ui.challenges

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.steppi.steppifitness.R
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.utils.STUtils
import kotlinx.android.synthetic.main.fragment_challenge_status.*
import java.util.ArrayList

class STChallengeStatusFragment : STBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_challenge_status
    }

    override fun initViews() {
        setBarChart()
    }

    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))
        entries.add(BarEntry(13f, 6))
        entries.add(BarEntry(11f, 7))
        entries.add(BarEntry(20f, 8))

        val barDataSet = BarDataSet(entries, "")

        val labels = ArrayList<String>()
        labels.add("")
        labels.add("")
        labels.add("")
        labels.add("")
        labels.add("")
        labels.add("")
        labels.add("")
        labels.add("")
        labels.add("")
        val data = BarData(labels, barDataSet)
        data.isHighlightEnabled = false
        barChart.data = data // set the data and list of lables into chart

        barChart.setDescription("")  // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = STUtils.getColor(activityContext, R.color.light_blue)

        barDataSet.setDrawValues(false)
        barChart.animateY(3000)
        barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        // hide legend
        val legend = barChart.legend
        legend.isEnabled = false
    }
}
