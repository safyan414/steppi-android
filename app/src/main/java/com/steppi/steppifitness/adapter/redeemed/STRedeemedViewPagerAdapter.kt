package com.steppi.steppifitness.adapter.redeemed

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class STRedeemedViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList: ArrayList<Fragment>,
    private val mFragmentTitleList: ArrayList<String>
) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}
