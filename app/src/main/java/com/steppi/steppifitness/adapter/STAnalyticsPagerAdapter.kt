package com.steppi.steppifitness.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.steps_analytics.StepsAnalyticsData
import com.steppi.steppifitness.ui.fitness_analytics.STAnalyticsListFragment

class STAnalyticsPagerAdapter(
    fm: FragmentManager,
    private val analyticsType: String?
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        return STAnalyticsListFragment().apply {
            arguments = Bundle().apply {
                putString(
                    STConstants.ANALYTICS_TYPE, analyticsType
                )
                putSerializable(
                    STConstants.ANALYTICS_DATA,
                    stepsArrayCount!![position] as ArrayList<StepsAnalyticsData>
                )
            }
        }
    }

    override fun getCount(): Int {
        return stepsArrayCount?.size ?: 0
    }

    private var stepsArrayCount: ArrayList<List<StepsAnalyticsData>>? = null
    fun setStepsAnalytics(stepsArrayCountList: ArrayList<List<StepsAnalyticsData>>?) {
        stepsArrayCount = stepsArrayCountList
        notifyDataSetChanged()
    }
}
