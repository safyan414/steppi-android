package com.steppi.steppifitness.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.onboarding.STOnboardingPagerFragment

class STOnBoardingPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return STOnboardingPagerFragment().apply {
            arguments = Bundle().apply {
                putInt(STConstants.ARG_POSITION, position)
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
