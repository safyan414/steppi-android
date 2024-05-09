package com.steppi.steppifitness.ui.rewards

import android.os.Bundle
import android.view.animation.AnimationUtils
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STItemEventListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.category.STCategory
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.ui.home.mvi.STHomeController
import com.steppi.steppifitness.ui.home.mvi.STHomeIntent
import com.steppi.steppifitness.ui.home.mvi.STHomeState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_rewards.*

class STRewardsFragment : STBaseViewModelFragment<STHomeIntent, STHomeState, STHomeController>(
    STHomeController::class.java
) {
    private var rewardItemListAdapter: STItemEventListAdapter? = null
    private var selectedReward: STCategory? = null
    private var categoryList: ArrayList<STCategory>? = null
    private var scrollToPosition: Int? = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_rewards
    }

    override fun initViews() {
        initData()
        setUpRewardTypeList()
//        setRewardList()
    }

    override fun processState(state: STHomeState) {
        when (state) {
            is STHomeState.Loading -> requestDidStart()
            is STHomeState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STHomeState.GetCategory -> {
                requestDidFinish()
                state.categoryResponse.data?.let {
                    setCategories(it as ArrayList)
                }
            }
        }
    }

    override fun onViewModelReady() {
        if (null != categoryList) {
            setCategories(categoryList!!)
        } else {
            invokeIntent(STHomeIntent.GetCategory)
        }
    }

    private fun initData() {
        selectedReward = arguments?.getSerializable(STConstants.REWARD_TYPE) as? STCategory
        categoryList =
            arguments?.getSerializable(STConstants.CATEGORY_LIST) as? ArrayList<STCategory>
        scrollToPosition = arguments?.getInt(STConstants.REWARD_SELECTED_POSITION)
    }

    private fun setUpRewardTypeList() {
        if (null == rewardItemListAdapter) {
            rewardTypeList.setHorizontalLayoutManager()
            rewardTypeList.setHorizontalItemDecoration(
                space = STUtils.getDimen(activityContext, R.dimen.margin_small),
                initialPadding = STUtils.getDimen(activityContext, R.dimen.margin_normal),
                isRtl = STPreference.getLanguageCode(activityContext) == STConstants.ENGLISH_CODE
            )
            rewardTypeList.scrollToPosition(scrollToPosition!!)
            rewardItemListAdapter = STItemEventListAdapter(activityContext, isMenu = true)
            rewardTypeList.adapter = rewardItemListAdapter
            rewardItemListAdapter?.setCategory(categoryList, selectedReward)
            val controller = AnimationUtils.loadLayoutAnimation(
                activityContext,
                R.anim.layout_animation_left_to_right
            )

            rewardTypeList.layoutAnimation = controller
            rewardItemListAdapter?.notifyDataSetChanged()
            rewardTypeList.scheduleLayoutAnimation()
        }
        rewardItemListAdapter?.setClickListener(object :
            STItemEventListAdapter.OnItemClickListener {
            override fun onItemClick(selectedRewardData: STCategory?, selectedPositionData: Int?) {
                selectedReward = selectedRewardData
                scrollToPosition = selectedPositionData
                rewardTypeList.scrollToPosition(scrollToPosition!!)
                setRewardList()
            }
        })
//        rewardItemListAdapter?.setOnItemClickListener {
//            selectedReward = it
//            setRewardList()
//        }
    }

    private fun setRewardList() {
        changeFragment(R.id.rewardsContainer, STFeaturedRewardsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(STConstants.CATEGORY, selectedReward)
            }
        })
        (activityContext as STHomeActivity).setSelectedReward(selectedReward)
    }

    private fun setCategories(categoryList: ArrayList<STCategory>?) {
        categoryList?.let {
            selectedReward =
                if (null == selectedReward && it.size > 0) it[0] else selectedReward
            rewardItemListAdapter?.setCategory(categoryList, selectedReward)
            setRewardList()
        }
    }
}
