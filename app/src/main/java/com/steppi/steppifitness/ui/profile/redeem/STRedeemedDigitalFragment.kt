package com.steppi.steppifitness.ui.profile.redeem

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedDigitalAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.user.RedeemedRewards
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_redeemed_in_store.*

class STRedeemedDigitalFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ), SwipeRefreshLayout.OnRefreshListener, PaginationAdapterCallback {
    private var redeemedDigitalAdapter: STRedeemedDigitalAdapter? = null
    private var isLoading = false
    private var isLastPage = false

    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private var totalList = 0
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

        redeemedDigitalAdapter = STRedeemedDigitalAdapter(activityContext, mCallback!!)
        inStoreList.adapter = redeemedDigitalAdapter
        redeemedDigitalAdapter?.setCopyReferralCodeListener(object :
            STRedeemedDigitalAdapter.CopyReferralCode {
            override fun copyCode(referralCode: String?) {
                referralCode?.let {
                    activityContext.copyToClipBoard(STConstants.REFERRAL_CODE, it)
                    showToast(getString(R.string.referral_code_copied))
                }
            }
        })
        addScrollListener(layoutManager)
    }

    override fun onRefresh() {
        redeemedDigitalAdapter?.clear()
        isLoading = false
        isLastPage = false
        totalList = 0
        offset = 0
        noData.gone()
        invokeIntent(
            STProfileIntent.GetRedeemedRewardsList(STConstants.REWARD_TYPE_DIGITAL, offset)
        )
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        inStoreList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                onViewModelReady()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading
        })
    }


    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Error -> {
                if (offset > 0) {
                    redeemedDigitalAdapter!!.showRetry(true, state.errorData?.message)
                }
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }

            is STProfileState.GetRedeemedRewardList -> {
                requestDidFinish()
                totalList = state.redeemedRewardsResponse.total!!
                setDigitalData(state.redeemedRewardsResponse.data!!.redeemedRewards)
            }
        }
    }

    private fun setDigitalData(redeemedRewardsList: List<RedeemedRewards>?) {
        redeemListPullRefresh?.isRefreshing = false
        redeemedRewardsList.let {
            if (it.isNullOrEmpty()) {
                noData.visibility = View.VISIBLE
            }
            if (offset > 0) {
                redeemedDigitalAdapter!!.removeLoadingFooter()
                isLoading = false
            }
            redeemedDigitalAdapter?.setRedeemedData(it as ArrayList<RedeemedRewards>)

            if (totalList > redeemedDigitalAdapter!!.getListSize()) redeemedDigitalAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = redeemedDigitalAdapter!!.getListSize() - 1
        }
    }

    override fun onViewModelReady() {
        invokeIntent(
            STProfileIntent.GetRedeemedRewardsList(STConstants.REWARD_TYPE_DIGITAL, offset)
        )
    }

    override fun retryPageLoad() {
        showToast("retry")
        onViewModelReady()
    }
}
