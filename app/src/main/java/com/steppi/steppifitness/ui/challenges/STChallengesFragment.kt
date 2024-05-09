package com.steppi.steppifitness.ui.challenges

import androidx.fragment.app.Fragment
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedViewPagerAdapter
import com.steppi.steppifitness.ui.base.STBaseFragment
import kotlinx.android.synthetic.main.fragment_challenges.*

class STChallengesFragment : STBaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_challenges
    }

    override fun initViews() {
        val fragmentList = ArrayList<Fragment>()
        val mFragmentTitleList = ArrayList<String>()
        fragmentList.add(STCSteppiChallengesFragment())
        mFragmentTitleList.add(activityContext.resources.getString(R.string.steppi_challenge))
        fragmentList.add(STCMyChallengesFragment())
        mFragmentTitleList.add(activityContext.resources.getString(R.string.my_challenge))
        challengesViewpager.adapter =
            STRedeemedViewPagerAdapter(fragmentManager!!, fragmentList, mFragmentTitleList)
        tabLayout.setupWithViewPager(challengesViewpager)
    }
}
