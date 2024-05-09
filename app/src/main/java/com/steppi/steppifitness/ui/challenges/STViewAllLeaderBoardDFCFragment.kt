package com.steppi.steppifitness.ui.challenges

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STLeaderBoardListPC2Adapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.EndlessScrollListener
import com.steppi.steppifitness.utils.invisible
import com.steppi.steppifitness.utils.showToast
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.fragment_dfc_view_all_leaderboard.*
import java.util.ArrayList

class STViewAllLeaderBoardDFCFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var challengesId: String? = null
    private var challengeDetails: STChallengesListData? = null
    private var leaderBoardListPC2Adapter: STLeaderBoardListPC2Adapter? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var teamsList: List<STParticipantTeams>? = null
    private var isChallengeStarted: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_dfc_view_all_leaderboard
    }

    override fun initViews() {
        initData()
    }

    override fun onViewModelReady() {
        getLeaderBoardList()
    }

    private fun initData() {
        challengesId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        challengeDetails =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA) as? STChallengesListData
        arguments?.getBoolean(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        initPullToRefresh()
        initLeaderBoardAdapter()
    }

    private fun getLeaderBoardList() {
        challengesId?.let { challengeId ->
            invokeIntent(STChallengesIntent.GetTeamLeaderBoardAnimation(challengeId, offset))
        }
    }

    private fun initPullToRefresh() {
        pullTolRefresh.setOnRefreshListener(this)
        pullTolRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
    }

    override fun onRefresh() {
        leaderBoardListPC2Adapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        pageLayoutMain.invisible()
        getLeaderBoardList()
    }

    private fun initLeaderBoardAdapter() {
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
        leaderBoardList.layoutManager = layoutManager
        leaderBoardListPC2Adapter = STLeaderBoardListPC2Adapter(activityContext)
        leaderBoardList.adapter = leaderBoardListPC2Adapter
        addScrollListener(layoutManager)
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        leaderBoardList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getLeaderBoardList()
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
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
                activityContext.finish()
            }
            is STChallengesState.GetTeamLeaderBoardAnimation -> {
                requestDidFinish()
                pullTolRefresh?.isRefreshing = false
                pageLayoutMain.visible()
                state.teamLeaderBoardAnimationResponse?.let {
                    listTotal = it.total!!
                    it.data?.let { teamLeaderBoardAnimationData ->
                        teamLeaderBoardAnimationData.teams?.let { listTeam ->
                            teamsList = listTeam
                            setLeaderBoardList()
                        }
                    }
                }
            }
        }
    }

    private fun setLeaderBoardList() {
        leaderBoardList.visible()
        teamsList?.let { teamsListData ->
            if (offset > 0) {
                leaderBoardListPC2Adapter!!.removeLoadingFooter()
                isLoading = false
            }

            leaderBoardListPC2Adapter?.setLeaderBoardLists(teamsListData as ArrayList<STParticipantTeams>)
            leaderBoardListPC2Adapter?.isChallengeStarted(isChallengeStarted)
            leaderBoardListPC2Adapter?.setType(challengeDetails?.type)
            leaderBoardListPC2Adapter?.setIsViewAllList(true)
            if (challengeDetails?.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                leaderBoardListPC2Adapter?.showParticipants(challengeDetails?.showParticipants)
            }

            if (listTotal > leaderBoardListPC2Adapter!!.getListSize()) leaderBoardListPC2Adapter!!.addLoadingFooter()
            else isLastPage = true
            offset = leaderBoardListPC2Adapter!!.getListSize() - 1
        }
    }
}
