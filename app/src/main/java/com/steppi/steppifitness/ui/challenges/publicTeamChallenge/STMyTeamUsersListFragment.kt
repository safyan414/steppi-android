package com.steppi.steppifitness.ui.challenges.publicTeamChallenge

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STMyTeamLeaderBoardListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STTeamLeaderBoardDetailsResponse
import com.steppi.steppifitness.network.response.challenges.data.STMyUser
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_my_team_users_list.*
import kotlinx.android.synthetic.main.list_item_leader_board_list.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STMyTeamUsersListFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var challengeId: String? = null
    private var participantTeamId: String? = null
    private var adapter: STMyTeamLeaderBoardListAdapter? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var myUserData: STMyUser? = null
    private var participantTeamsData: STParticipantTeams? = null
    private var isChallengeStarted: Boolean = false
    private var isChallengeCompleted: Boolean = false
    private var challengeStartDate: String? = null
    private var challengeEndDate: String? = null
    private var isRefreshList: Boolean? = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_team_users_list
    }

    override fun initViews() {
        getIntentData()
        initParticipantListAdapter()
        initPullToRefresh()
    }

    override fun onRefresh() {
        adapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        isRefreshList = true
        getParticipantList()
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

    override fun onViewModelReady() {
        getParticipantList()
    }

    private fun initParticipantListAdapter() {
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)

        participantList.layoutManager = layoutManager
        adapter = STMyTeamLeaderBoardListAdapter(activityContext)
        adapter!!.setClickListener(object : STMyTeamLeaderBoardListAdapter.OnItemClickListener {
            override fun onItemClick(userId: String?) {
                if (userId != null && userId == STPreference.getUserId(activityContext)) {
                    val container = Intent(activityContext, STContainerActivity::class.java)
                    container.putExtra(
                        STConstants.FRAGMENT_NAME,
                        activityContext.resources.getString(R.string.daily_steps) /*participantTeamsData?.name*/
                    )
                    container.putExtra(
                        STConstants.FRAGMENT_ID,
                        STConstants.FRAGMENT_ID_DAILY_STEP
                    )
                    container.putExtra(STConstants.RANK, myUserData?.rank)
                    container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
                    container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
                    container.putExtra(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
                    container.putExtra(STConstants.CHALLENGES_END_DATE, challengeEndDate)
                    container.putExtra(STConstants.CHALLENGES_START_DATE, challengeStartDate)
                    startActivity(container)
                }
            }

            override fun onCheerClick(
                teamMember: STTeamMember,
                position: Int
            ) {
                invokeIntent(
                    STChallengesIntent.CheerChallengeUser(
                        challengeId!!,
                        teamMember.id!!
                    )
                )
                myUserData?.let {
                    if (it.rank!!.toInt() == position.plus(1)) {
                        it.cheerReceived = (it.cheerReceived!!.toInt() + 1).toString()
                        it.cheered = true
                        setMyUserLayout(myUserData)
                    }
                }
            }

        })
        participantList.adapter = adapter
        addScrollListener(layoutManager)
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        participantList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getParticipantList()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    private fun getParticipantList() {
        invokeIntent(
            STChallengesIntent.GetChallengeTeamLeaderBoardDetails(
                challengeId!!,
                participantTeamId!!,
                offset
            )
        )
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
            is STChallengesState.GetTeamLeaderBoardDetails -> {
                requestDidFinish()
                pullTolRefresh?.isRefreshing = false
                listTotal = state.teamLeaderBoardDetailsResponse.total!!
                setLeaderBoardAdapter(state.teamLeaderBoardDetailsResponse)
            }
            is STChallengesState.CheerChallengeUser -> {
            }
        }
    }

    private fun setLeaderBoardAdapter(teamLeaderBoardDetailsResponse: STTeamLeaderBoardDetailsResponse) {
        teamLeaderBoardDetailsResponse.data?.let { challengeUserData ->
            if (offset > 0) {
                adapter!!.removeLoadingFooter()
                isLoading = false
            }
            challengeUserData.myUser?.let {
                myUserData = it
                setMyUserLayout(myUserData)
            }
//            participantList.setVerticalLayoutManager()
            if (challengeUserData.challengeUser.isNullOrEmpty()) {
                noTeamListData.visible()
            } else {
                noTeamListData.gone()
            }
            adapter?.setValues(challengeUserData.challengeUser)
            adapter?.isChallengeStarted(isChallengeStarted)
            adapter?.isChallengeCompleted(isChallengeCompleted)
            if (listTotal > adapter!!.getListSize()) adapter!!.addLoadingFooter()
            else isLastPage = true

            offset = adapter!!.getListSize() - 1
        } ?: kotlin.run {
            noTeamListData.visible()
        }
    }

    @OnClick(R.id.cheerLayout)
    fun cheerLayoutClick() {
        myUserData?.let {
            if (!it.cheered!!) {
                invokeIntent(
                    STChallengesIntent.CheerChallengeUser(
                        challengeId!!,
                        it.id!!
                    )
                )
                it.cheerReceived = (it.cheerReceived!!.toInt() + 1).toString()
                it.cheered = true
                setMyUserLayout(it)
                participantTeamsData?.teamMembers?.let { leaderBoardList ->
                    if (leaderBoardList.size >= it.rank!!.toInt()) {
                        val mData = leaderBoardList[it.rank.toInt() - 1]
                        mData.cheerReceived = (mData.cheerReceived!!.toInt() + 1).toString()
                        mData.cheered = true
                        adapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun setMyUserLayout(myUser: STMyUser?) {
        myUser?.let {
            userName.text = it.name
            it.totalSteps?.let {
                steps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    ) // NumberFormat.getNumberInstance(Locale.US).format(it.toInt())
            }
            it.picture?.let { pictureUrl ->
                userImageManRunning.gone()
                userImage.visible()
                userImage.load(
                    activityContext, pictureUrl
                ) {
                    userImageManRunning.visible()
                    userImage.gone()
                }
            } ?: kotlin.run {
                userImageManRunning.visible()
                userImage.gone()
            }
            position.text = it.rank.toString()
            it.rank?.let {
                position.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    ) /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
            }
            it.next?.let { stepsToNextLevel ->
                if (stepsToNextLevel == "0") {
                    stepsRemainingLayout.gone()
                } else {
                    stepsRemainingLayout.visible()
                    stepsRemaining.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(stepsToNextLevel.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                    .format(stepsToNextLevel.toInt()) */
                }
            } ?: kotlin.run {
                stepsRemainingLayout.gone()
            }
            it.cheered?.let {
                if (!it) {
                    myUser.cheerReceived?.let { cheerReceived ->
                        if (cheerReceived == "0") {
                            cheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                            cheerCount.text =
                                activityContext.resources.getString(R.string.cheer_count_label)
                        } else {
                            cheers.setImageResource(R.drawable.cheer_with_count_disabled)
                            cheerCount.text = myUser.cheerReceived
                        }
                    }
                    cheerCount.textColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.edit_text_bg_stroke_color
                        )
                    )
                } else {
                    cheers.setImageResource(R.drawable.cheer_with_count_enabled)
                    cheerCount.text = myUser.cheerReceived
                    cheerCount.textColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                }
            }
            if (!isChallengeStarted || isChallengeCompleted) {
                cheerLayout.gone()
                stepsRemainingLayout.gone()
            }
            it.rank?.let {
                if (it.toInt() > 10) {
                    myUserLayout.visible()
                } else {
                    myUserLayout.gone()
                }
            } ?: kotlin.run {
                myUserLayout.gone()
            }
        } ?: kotlin.run {
            myUserLayout.gone()
        }
    }

    @OnClick(R.id.myUserLayout)
    fun myUserLayout() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(
            STConstants.FRAGMENT_NAME,
            activityContext.resources.getString(R.string.daily_steps) /*participantTeamsData?.name*/
        )
        container.putExtra(
            STConstants.FRAGMENT_ID,
            STConstants.FRAGMENT_ID_DAILY_STEP
        )
        container.putExtra(STConstants.RANK, myUserData?.rank)
        container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
        container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        container.putExtra(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
        container.putExtra(STConstants.CHALLENGES_END_DATE, challengeEndDate)
        container.putExtra(STConstants.CHALLENGES_START_DATE, challengeStartDate)
        startActivity(container)
    }

    private fun getIntentData() {
        challengeId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        arguments?.getString(STConstants.PARTICIPANT_TEAM_ID)?.let {
            participantTeamId = it
        }
        arguments?.getSerializable(STConstants.PARTICIPANT_TEAMS_DATA)?.let {
            participantTeamsData = it as STParticipantTeams
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_COMPLETED, false)?.let {
            isChallengeCompleted = it
        }
        arguments?.getString(STConstants.CHALLENGES_END_DATE)?.let {
            challengeEndDate = it
        }
        arguments?.getString(STConstants.CHALLENGES_START_DATE)?.let {
            challengeStartDate = it
        }
    }

    fun setRefresh() {
        adapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        getParticipantList()
    }
}
