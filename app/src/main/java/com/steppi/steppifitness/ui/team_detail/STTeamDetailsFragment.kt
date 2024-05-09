package com.steppi.steppifitness.ui.team_detail

import android.os.Bundle
import android.os.TransactionTooLargeException
import android.util.Log
import androidx.fragment.app.Fragment
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.redeemed.STRedeemedViewPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.ui.base.STBaseFragment
import kotlinx.android.synthetic.main.team_details.*

class STTeamDetailsFragment : STBaseFragment() {
    private var participantTeam: STParticipantTeams? = null
    private var challengesId: String? = null
    private var isChallengeStarted: Boolean? = null
    private var isChallengeCompleted: Boolean? = null
    private var isNewMessage = false

    override fun getLayoutId(): Int {
        return R.layout.team_details
    }

    override fun initViews() {
        getExtras()
        participantTeam?.let {
            val fragmentList = ArrayList<Fragment>()
            val mFragmentTitleList = ArrayList<String>()

            // add members fragment
//            val newMembersList = ArrayList<STTeamMember>()
//            for (i in it.teamMembers!!.indices) {
//                if (it.teamMembers[i].user?.name.equals(
//                        STPreference.getName(
//                            activityContext
//                        ), true
//                    )
//                ) {
//                    newMembersList.add(0, it.teamMembers[i])
//                } else {
//                    newMembersList.add(it.teamMembers[i])
//                }
//            }

            val membersFragment = STMembersFragment()
            val membersFragmentData = Bundle()
            it.teamMembers?.let { teamMembersList ->
                //membersFragmentData.putSerializable(STConstants.TEAM_MEMBER_LIST,teamMembersList as ArrayList<STTeamMember>)
            }
            membersFragmentData.putSerializable("teamId", participantTeam!!.id)
            membersFragmentData.putBoolean(STConstants.CHALLENGE_STARTED, isChallengeStarted!!)
            membersFragmentData.putBoolean(STConstants.CHALLENGE_COMPLETED, isChallengeCompleted!!)
            membersFragmentData.putString(STConstants.CHALLENGE_ID, challengesId)
            membersFragment.arguments = membersFragmentData
            fragmentList.add(membersFragment)
            mFragmentTitleList.add(activityContext.resources.getString(R.string.members))
            // add discussion fragment
            val discussionFragment = STDiscussionFragment()
            val discussionFragmentData = Bundle()
            discussionFragmentData.putString(STConstants.CHALLENGE_ID, challengesId)
            discussionFragmentData.putString(STConstants.TEAM_ID, it.id)
            discussionFragment.arguments = discussionFragmentData

            fragmentList.add(discussionFragment)
            mFragmentTitleList.add(activityContext.resources.getString(R.string.chat))

            teamDetailViewpager.adapter =
                STRedeemedViewPagerAdapter(fragmentManager!!, fragmentList, mFragmentTitleList)
            tabLayout.setupWithViewPager(teamDetailViewpager)

            if (isNewMessage)
                teamDetailViewpager.currentItem = 1
        }
    }

    private fun getExtras() {
        try {
            participantTeam =
                arguments?.getSerializable(STConstants.PARTICIPANT_TEAM) as? STParticipantTeams

            challengesId = arguments?.getString(STConstants.CHALLENGE_ID)
            isChallengeStarted = arguments?.getBoolean(STConstants.CHALLENGE_STARTED, false)
            isChallengeCompleted = arguments?.getBoolean(STConstants.CHALLENGE_COMPLETED, false)
            arguments?.getBoolean(STConstants.IS_NEW_MESSAGE)?.let {
                isNewMessage = it
            }
        } catch (e: TransactionTooLargeException) {
            Log.e("sdfsdf", "sdfsdf");
        }

    }
}
