package com.steppi.steppifitness.ui.challenges.private_challenge

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STPrivateChallengeListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STPrivateChallengeResponse
import com.steppi.steppifitness.network.response.challenges.data.STCreatePrivateChallengeData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_create_private_challenge.*

class STCreatePrivateChallengeFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var offset: Int = 0
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var adapter: STPrivateChallengeListAdapter? = null
    var selectedData: STCreatePrivateChallengeData? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_private_challenge
    }

    override fun initViews() {
        initPullToRefresh()
        challengeList.setVerticalLayoutManager()
        challengeList.setVerticalItemDecoration(
            activityContext.resources.getDimension(R.dimen.margin_small).toInt(),
            0
        )
        adapter = STPrivateChallengeListAdapter(activityContext)
        challengeList.adapter = adapter
        addScrollListener(challengeList.layoutManager as LinearLayoutManager)
        adapter?.setClickListener(object : STPrivateChallengeListAdapter.OnItemClickListener {
            override fun onItemClick(challengeCreate: STCreatePrivateChallengeData?) {
                selectedData = challengeCreate
                nextButton.setBackgroundResource(R.drawable.button_bg_enabled)
            }
        })
    }

    private fun initPullToRefresh() {
        pullToRefresh.setOnRefreshListener(this)
        pullToRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
    }

    override fun onRefresh() {
        adapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        invokeIntent(STChallengesIntent.GetPrivateChallengeList(offset))
    }

    private fun getPrivateChallengeList() {
        invokeIntent(STChallengesIntent.GetPrivateChallengeList(offset))
    }


    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        challengeList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getPrivateChallengeList()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    override fun processState(state: STChallengesState) {
        when (state) {
            is STChallengesState.Loading -> {
                requestDidStart()
            }
            is STChallengesState.Error -> {
                pullToRefresh?.isRefreshing = false
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
                activityContext.finish()
            }

            is STChallengesState.GetPrivateChallengeTemplatesList -> {
                pullToRefresh?.isRefreshing = false
                requestDidFinish()
                setPrivateChallengeTemplatesList(state.privateChallengesResponse)
            }
        }

    }

    private fun setPrivateChallengeTemplatesList(response: STPrivateChallengeResponse) {
        listTotal = response.total ?: 0
        if (offset > 0) {
            adapter?.removeLoadingFooter()
            isLoading = false
        }
        response.data?.let {
            if (it.isNullOrEmpty()) {
                noData.visible()
            } else {
                noData.gone()
            }
            adapter?.setPrivateChallengeData(it as ArrayList<STCreatePrivateChallengeData>)
        } ?: kotlin.run {
            noData.visible()
        }

        if (listTotal > adapter!!.getListSize()) adapter!!.addLoadingFooter()
        else isLastPage = true

        offset = adapter!!.getListSize() - 1
    }

    override fun onViewModelReady() {
        invokeIntent(STChallengesIntent.GetPrivateChallengeList(offset))
    }

    @OnClick(R.id.nextButton)
    fun nextButtonClick() {
        if (STUtils.singleClick()) {
            if (selectedData == null) {
                showToast(getString(R.string.please_select_one_challenge))
            } else {
                val container = Intent(activityContext, STContainerActivity::class.java)
                container.putExtra(
                    STConstants.FRAGMENT_NAME,
                    getString(R.string.choose_theme_heading)
                )
                container.putExtra(
                    STConstants.FRAGMENT_ID,
                    STConstants.FRAGMENT_ID_PRIVATE_CHALLENGE_CHOOSE_THEME
                )
                container.putExtra(STConstants.SELECTED_PRIVATE_CHALLENGE, selectedData)
                startActivity(container)
            }
        }
    }
}
