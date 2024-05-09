package com.steppi.steppifitness.ui.challenges.publicTeamChallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedViewPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STTeamDetailsResponse
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_my_team_leaderboard_pc_two.*
import kotlinx.android.synthetic.main.fragment_my_team_leaderboard_pc_two.challengeDetailsViewpager
import kotlinx.android.synthetic.main.layout_challenge_winner.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STFragmentMyTeamLeaderBoard :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private var participantTeam: STParticipantTeams? = null
    private var rankPosition: Int? = null
    private var isChallengeStarted: Boolean = false
    private var isChallengeCompleted: Boolean = false
    private var challengeId: String? = null
    private var participantTeamId: String? = null
    private var challengeStartDate: String? = null
    private var challengeEndDate: String? = null
    private var participantTeamsData: STParticipantTeams? = null
    private val myTeamUsersListFragment = STMyTeamUsersListFragment()

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_team_leaderboard_pc_two
    }

    override fun initViews() {
        getIntentData()
    }

    override fun onViewModelReady() {
        getTeamDetailsAPICall()
    }

    private fun getTeamDetailsAPICall() {
        participantTeamId?.let { participantTeamIdValue ->
            invokeIntent(STChallengesIntent.GetChallengeTeamDetails(participantTeamIdValue))
        }
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
            is STChallengesState.GetTeamDetails -> {
                requestDidFinish()
                setTeamDetails(state.teamDetailsResponse)
                setBottomListLayout()
            }
            is STChallengesState.CheerChallengeUser -> {
            }
            else -> {
            }
        }
    }

    private fun setBottomListLayout() {
        val fragmentList = ArrayList<Fragment>()
        val mFragmentTitleList = ArrayList<String>()
        val myTeamUsersListData = Bundle()
        myTeamUsersListData.putString(STConstants.CHALLENGES_DATA_ID, challengeId!!)
        myTeamUsersListData.putString(STConstants.PARTICIPANT_TEAM_ID, participantTeamId!!)
        myTeamUsersListData.putSerializable(
            STConstants.PARTICIPANT_TEAMS_DATA,
            participantTeamsData
        )
        myTeamUsersListData.putBoolean(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        myTeamUsersListData.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, isChallengeCompleted)
        myTeamUsersListData.putString(STConstants.CHALLENGES_END_DATE, challengeEndDate)
        myTeamUsersListData.putString(STConstants.CHALLENGES_START_DATE, challengeStartDate)
        myTeamUsersListFragment.arguments = myTeamUsersListData
        fragmentList.add(myTeamUsersListFragment)
        challengeDetailsViewpager.adapter =
            STRedeemedViewPagerAdapter(fragmentManager!!, fragmentList, mFragmentTitleList)
    }

    private fun setTeamDetails(teamDetailsResponse: STTeamDetailsResponse) {
        participantTeamsData = teamDetailsResponse.data
        setBottomListLayout()
        participantTeamsData?.let { participantTeams ->
            myTeamMain.visible()
            participantTeams.participants?.let { participantsValue ->
                participants.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(participantsValue.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
            }
            participantTeams.dailyAverage?.let { dailyAverage ->
                avgSteps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(dailyAverage.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
                /*NumberFormat.getNumberInstance(Locale.US).format(dailyAverage.toInt())*/
            }
            participantTeams.teamSteps?.let { teamSteps ->
                totalDailySteps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(teamSteps.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
            }
            participantTeams.rank?.let {
                if (!isChallengeStarted) {
                    rankingLayout.gone()
                } else {
                    rankingLayout.visible()
                    ranking.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(it.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    if (it == 1) {
                        if (participantTeams.totalScore == "0") {
                            winnerLayout_pc_two.gone()
                        } else {
                            if (isChallengeCompleted) {
                                winnerLayout_pc_two.visible()
                            }
                            pcTwoWinnerTeamName.text = participantTeams.name
                            participantTeams.participants?.let { participants ->
                                pcTwoWinnerTeamMembers.text =
                                    NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                            BigDecimal(participants.toDouble()).setScale(
                                                0,
                                                RoundingMode.HALF_EVEN
                                            )
                                        )
                            }
                            participantTeams.totalScore?.let { totalScore ->
                                pcTwoWinnerTeamTotalScore.text =
                                    NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                            BigDecimal(totalScore.toDouble()).setScale(
                                                0,
                                                RoundingMode.HALF_EVEN
                                            )
                                        )
                            }
                        }
                    }
                }
            } ?: kotlin.run {
                rankingLayout.gone()
            }
        } ?: kotlin.run {
        }
    }

    private fun getIntentData() {
        participantTeam =
            arguments?.getSerializable(STConstants.PARTICIPANT_TEAM) as? STParticipantTeams
        rankPosition =
            arguments?.getInt(STConstants.PARTICIPANT_TEAM_RANK_POSITION)
        arguments?.getBoolean(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_COMPLETED, false)?.let {
            isChallengeCompleted = it
        }
        challengeId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)

        arguments?.getString(STConstants.PARTICIPANT_TEAM_ID)?.let {
            participantTeamId = it
        }
        arguments?.getString(STConstants.CHALLENGES_END_DATE)?.let {
            challengeEndDate = it
        }
        arguments?.getString(STConstants.CHALLENGES_START_DATE)?.let {
            challengeStartDate = it
        }
    }
}
