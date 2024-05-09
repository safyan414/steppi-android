package com.steppi.steppifitness.ui.profile.redeem

import androidx.fragment.app.Fragment
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedViewPagerAdapter
import com.steppi.steppifitness.network.response.user.RedeemedRewardsData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.fragment_redeemed.*

class STRedeemedFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ) {

    override fun getLayoutId(): Int {
        return R.layout.fragment_redeemed
    }

    override fun initViews() {
        val fragmentList = ArrayList<Fragment>()
        val mFragmentTitleList = ArrayList<String>()
        fragmentList.add(STRedeemedInStoreFragment())
        mFragmentTitleList.add(activityContext.resources.getString(R.string.instore))
        fragmentList.add(STRedeemedDigitalFragment())
        mFragmentTitleList.add(activityContext.resources.getString(R.string.online))
        redeemViewpager.adapter =
            STRedeemedViewPagerAdapter(fragmentManager!!, fragmentList, mFragmentTitleList)

        tabLayout.setupWithViewPager(redeemViewpager)
    }

    override fun processState(state: STProfileState) {
    }

    override fun onViewModelReady() {
    }

    fun setStepsData(redeemedRewardsData: RedeemedRewardsData?) {
        redeemedLayout.visible()
        redeemedRewardsData!!.let {
            it.lifetimeSteps?.let {
                totalSteps.text = STUtils.formatNumber(it.toInt())
            }
            it.redeemedSteps?.let {
                stepUsed.text = STUtils.formatNumber(it.toInt())
            }
            it.stepsRemaining?.let {
                stepsRemaining.text = STUtils.formatNumber(it.toInt())
            }
            it.totalSavings?.let {
                if (it.contains("AED")) {
                    totalSavings.text = STUtils.formatNumber(it.toInt())
                } else {
                    totalSavings.text =
                        "${STUtils.formatNumber(it.toInt())}${activityContext.resources.getString(R.string.aed)}"
                }
            }
        }
    }
}
