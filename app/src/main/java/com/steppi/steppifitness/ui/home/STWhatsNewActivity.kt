package com.steppi.steppifitness.ui.home

import android.app.Activity
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STImageViewPagerWhatsNew
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.home.mvi.STHomeController
import com.steppi.steppifitness.ui.home.mvi.STHomeIntent
import com.steppi.steppifitness.ui.home.mvi.STHomeState
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.header_layout.*
import kotlinx.android.synthetic.main.whats_new.*

class STWhatsNewActivity :
    STBaseViewModelActivity<STHomeIntent, STHomeState, STHomeController>(STHomeController::class.java) {
    var screenLists: ArrayList<String>? = null
    private var fragmentId: Int = 0
    private var fragmentTitle: String? = null

    override fun processState(state: STHomeState) {
    }

    override fun onViewModelReady() {
    }

    override fun getLayoutId(): Int {
        return R.layout.whats_new
    }

    override fun initViews() {
        getIntentData()
        STUtils.setValueToView(header_name, fragmentTitle)
        skip.visible()
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
        fragmentId = intent.getIntExtra(STConstants.FRAGMENT_ID, 0)
        fragmentTitle = intent.getStringExtra(STConstants.FRAGMENT_NAME)
        intent.getSerializableExtra(STConstants.WHATS_NEW_SCREEN_LIST_DATA)?.let {
            screenLists = it as ArrayList<String>
        }
    }

    @OnClick(R.id.skip)
    fun skip() {
        onBackPressed()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}
