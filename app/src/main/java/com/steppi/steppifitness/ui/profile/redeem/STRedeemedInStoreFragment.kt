package com.steppi.steppifitness.ui.profile.redeem

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedInStoreAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.user.RedeemedRewards
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_redeemed_in_store.*

class STRedeemedInStoreFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ), SwipeRefreshLayout.OnRefreshListener, PaginationAdapterCallback {
    private var redeemedInStoreAdapter: STRedeemedInStoreAdapter? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var mCallback: PaginationAdapterCallback? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_redeemed_in_store
    }

    override fun initViews() {
        redeemListPullRefresh.setOnRefreshListener(this)
        redeemListPullRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
        mCallback = this
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
        inStoreList.layoutManager = layoutManager

        inStoreList.addItemDecoration(
            STVerticalSpaceItemDecoration(
                STUtils.getDimen(
                    activityContext,
                    R.dimen.margin_normal
                )
            )
        )
        redeemedInStoreAdapter = STRedeemedInStoreAdapter(activityContext)
        inStoreList.adapter = redeemedInStoreAdapter
        addScrollListener(layoutManager)
    }

    override fun onRefresh() {
        redeemedInStoreAdapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        noData.gone()
        invokeIntent(
            STProfileIntent.GetRedeemedRewardsList(STConstants.REWARD_TYPE_IN_STORE, offset)
        )
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        inStoreList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getRedeemedRewardList()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Error -> {
                requestDidFinish()
                if (offset > 0) {
                    redeemedInStoreAdapter!!.showRetry(true, state.errorData?.message)
                }
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STProfileState.GetRedeemedRewardList -> {
                requestDidFinish()
                listTotal = state.redeemedRewardsResponse.total!!
                setInStoreData(state.redeemedRewardsResponse.data!!.redeemedRewards)

                //(parentFragment as STRedeemedFragment).setStepsData(state.redeemedRewardsResponse.data!!)
                val frag =
                    activityContext.supportFragmentManager.findFragmentByTag("redeemedFragment")
                if (frag is STRedeemedFragment) {
                    frag.setStepsData(state.redeemedRewardsResponse.data!!)
                }
            }
        }
    }

    private fun setInStoreData(redeemedRewardsList: List<RedeemedRewards>?) {
        redeemListPullRefresh?.isRefreshing = false
        redeemedRewardsList.let {
            if (it.isNullOrEmpty()) {
                noData.visibility = View.VISIBLE
            }

            if (offset > 0) {
                redeemedInStoreAdapter!!.removeLoadingFooter()
                isLoading = false
            }

            redeemedInStoreAdapter?.setRedeemedData(it as ArrayList<RedeemedRewards>)

            if (listTotal > redeemedInStoreAdapter!!.getListSize()) redeemedInStoreAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = redeemedInStoreAdapter!!.getListSize() - 1
        }
    }

    override fun onViewModelReady() {
        getRedeemedRewardList()
    }

    private fun getRedeemedRewardList() {
        invokeIntent(
            STProfileIntent.GetRedeemedRewardsList(STConstants.REWARD_TYPE_IN_STORE, offset)
        )
    }

    override fun retryPageLoad() {
        getRedeemedRewardList()
    }
}
