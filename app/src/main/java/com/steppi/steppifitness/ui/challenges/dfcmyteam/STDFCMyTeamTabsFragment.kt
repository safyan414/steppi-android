package com.steppi.steppifitness.ui.challenges.dfcmyteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedViewPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.leaderboard.STDFCChallengeMyTeamAnimationLeaderBoardFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.fragment_dfc_challenge_my_team_tab.*
import java.util.*
import kotlin.collections.ArrayList

class STDFCMyTeamTabsFragment :
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
    private var challengesData: STChallengesListData? = null
    private var challengeDfcMyTeamFragment: Fragment? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_dfc_challenge_my_team_tab
    }

    override fun processState(state: STChallengesState) {
    }

    override fun onViewModelReady() {
    }

    override fun initViews() {
        getIntentData()
        initData()
    }

    private fun initData() {
        val fragmentList = ArrayList<Fragment>()
        val mFragmentTitleList = ArrayList<String>()

        val leaderBoardData = Bundle()
        challengeDfcMyTeamFragment = STFragmentDFCMyTeamLeaderBoard()
        challengeId?.let {
            leaderBoardData.putString(STConstants.CHALLENGES_DATA_ID, it)
        }
        participantTeamId?.let {
            leaderBoardData.putString(STConstants.PARTICIPANT_TEAM_ID, it)
        }
        challengesData.let {
            leaderBoardData.putSerializable(STConstants.CHALLENGES_DATA, it)
        }
        challengesData?.let { challengesData ->
            if (challengesData.theme == STConstants.CHALLENGE_THEME_1) {
                setLeaderBoard(fragmentList, mFragmentTitleList, leaderBoardData)
            } else if (challengesData.theme == STConstants.CHALLENGE_THEME_2) {
                setLeaderBoard(fragmentList, mFragmentTitleList, leaderBoardData)
            }

            val data = Bundle()
            participantTeam?.let {
                data.putSerializable(STConstants.PARTICIPANT_TEAM, it)
            }
            participantTeamId?.let {
                data.putString(STConstants.PARTICIPANT_TEAM_ID, it)
            }
            rankPosition?.let {
                data.putInt(STConstants.PARTICIPANT_TEAM_RANK_POSITION, it)
            }
            isChallengeStarted.let {
                data.putBoolean(STConstants.IS_CHALLENGE_STARTED, it)
            }
            isChallengeCompleted.let {
                data.putBoolean(STConstants.IS_CHALLENGE_COMPLETED, it)
            }
            challengeId?.let {
                data.putString(STConstants.CHALLENGES_DATA_ID, it)
            }
            challengeEndDate?.let {
                data.putString(STConstants.CHALLENGES_END_DATE, it)
            }
            challengeStartDate?.let {
                data.putString(STConstants.CHALLENGES_START_DATE, it)
            }
            challengesData.let {
                data.putSerializable(STConstants.CHALLENGES_DATA, it)
            }
            challengeDfcMyTeamFragment?.arguments = data
            challengeDfcMyTeamFragment?.let { fragmentList.add(it) }
            mFragmentTitleList.add(activityContext.resources.getString(R.string.info))
            challengeDetailsViewpager.adapter =
                STRedeemedViewPagerAdapter(fragmentManager!!, fragmentList, mFragmentTitleList)
            challengeDetailsTabLayout.setupWithViewPager(challengeDetailsViewpager)
            challengesData.startDate?.let { startDate ->
                val currentDate = STUtils.getFormattedDate(
                    Calendar.getInstance(),
                    "yyyy-MM-dd"
                )
                when (STUtils.compareDate(startDate, currentDate)) {
                    STConstants.CHALLENGE_NOT_STARTED -> {
                        challengeDetailsViewpager.currentItem = 1
                        challengeDetailsTabLayout.gone()
                    }
                    STConstants.CHALLENGE_STARTED -> {
                        when (challengesData.theme) {
                            STConstants.CHALLENGE_THEME_1 -> {
                                challengeDetailsViewpager.currentItem = 0
                                challengeDetailsTabLayout.visible()
                            }
                            STConstants.CHALLENGE_THEME_2 -> {
                                challengeDetailsViewpager.currentItem = 0
                                challengeDetailsTabLayout.visible()
                            }
                            else -> {
                                challengeDetailsViewpager.currentItem = 1
                                challengeDetailsTabLayout.gone()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setLeaderBoard(
        fragmentList: ArrayList<Fragment>,
        mFragmentTitleList: ArrayList<String>,
        leaderBoardData: Bundle
    ) {
        val leaderBoardFragment = STDFCChallengeMyTeamAnimationLeaderBoardFragment()
        leaderBoardFragment.arguments = leaderBoardData
        fragmentList.add(leaderBoardFragment)
        mFragmentTitleList.add(activityContext.resources.getString(R.string.leader_board))
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
        challengesData =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA) as? STChallengesListData
    }
}
