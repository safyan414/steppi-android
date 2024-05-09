package com.steppi.steppifitness.ui.home

import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STImageViewPagerWhatsNew
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.home.mvi.STHomeController
import com.steppi.steppifitness.ui.home.mvi.STHomeIntent
import com.steppi.steppifitness.ui.home.mvi.STHomeState
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.whats_new.*

class STWhatsNewFragment :
    STBaseViewModelFragment<STHomeIntent, STHomeState, STHomeController>(STHomeController::class.java) {
    var screenLists: ArrayList<String>? = null

    override fun processState(state: STHomeState) {
    }

    override fun onViewModelReady() {
    }

    override fun getLayoutId(): Int {
        return R.layout.whats_new
    }

    override fun initViews() {
        getIntentData()
        setUpWhatsNewPager()
    }

    private fun setUpWhatsNewPager() {
        val adapter = STImageViewPagerWhatsNew(activityContext)
        screenLists?.let {
            adapter.setItemImage(it)
        }
        whatsNewViewPager?.adapter = adapter
        whatsNewViewPager.requestLayout()
        indicator.setViewPager(whatsNewViewPager)
        indicator.visible()
    }

    private fun getIntentData() {
        arguments?.getSerializable(STConstants.WHATS_NEW_SCREEN_LIST_DATA)?.let {
            screenLists = it as ArrayList<String>
        }
    }
}
