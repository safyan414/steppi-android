package com.steppi.steppifitness.ui.team_detail.mvi

import com.steppi.steppifitness.network.request.corporate.STAddEditTeamDiscussionMessageRequest
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent

sealed class STTeamDetailIntent : MviIntent {
    data class GetTeamDiscussionMessages(
        val challengeId: String,
        val teamId: String,
        val offset: Int = 0
    ) : STTeamDetailIntent()

    data class AddTeamDiscussionMessage(
        val challengeId: String,
        val teamId: String,
        val addDiscussionMessage: STAddEditTeamDiscussionMessageRequest
    ) :
        STTeamDetailIntent()

    data class EditTeamDiscussionMessage(
        val challengeId: String,
        val teamId: String,
        val messageId: String,
        val editDiscussionMessage: STAddEditTeamDiscussionMessageRequest
    ) : STTeamDetailIntent()

    data class SearchMembersIntent(
        val searchKey: String,
        val searchMembersList: List<STTeamMember>?
    ) : STTeamDetailIntent()

    data class CheerChallengeUser(val challengeId: String, val challengeUserId: String) :
        STTeamDetailIntent()
    data class GetChallengeDetails(val challengeId: String) : STTeamDetailIntent()

    data class GetChallengeTeamMembers(val teamId: String, val challengeId: String, val offset: Int) : STTeamDetailIntent()
    data class GetChallengeSearchedTeamMembers(val teamId: String, val challengeId: String, val name: String) : STTeamDetailIntent()
}
