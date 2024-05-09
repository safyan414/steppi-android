package com.steppi.steppifitness.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.home.STFeaturedData
import com.steppi.steppifitness.ui.home.STHomeRewardFragment

class STRewardPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var featuredDataList: ArrayList<STFeaturedData>? = null

    override fun getItem(position: Int): Fragment {
        return STHomeRewardFragment().apply {
            arguments = Bundle().apply {
                putSerializable(
                    STConstants.FEATURED_ARG_POSITION,
                    featuredDataList?.get(position) as STFeaturedData
                )
            }
        }
    }

    override fun getCount(): Int {
        return featuredDataList?.size ?: 0
    }

    fun setFeaturedRewardsData(featuredDataList: ArrayList<STFeaturedData>) {
        this.featuredDataList = featuredDataList
        notifyDataSetChanged()
    }
}
