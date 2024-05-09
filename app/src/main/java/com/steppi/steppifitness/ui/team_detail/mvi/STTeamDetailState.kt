package com.steppi.steppifitness.ui.team_detail.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.*
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STTeamDetailState : MviViewState {
    object Loading : STTeamDetailState()
    data class Error(val errorData: STErrorData?) : STTeamDetailState()

    data class GetTeamDiscussionMessages(val teamDiscussionMessagesResponse: STTeamDiscussionMessageListResponse?) :
        STTeamDetailState()

    data class AddTeamDiscussionMessage(val addTeamDiscussionMessageResponse: STAddTeamDiscussionMessageResponse?) :
        STTeamDetailState()

    data class EditTeamDiscussionMessage(val editTeamDiscussionMessageResponse: STAddTeamDiscussionMessageResponse?) :
        STTeamDetailState()

    data class SearchMembersState(val teamMembersList: List<STTeamMember>?) : STTeamDetailState()
    data class CheerChallengeUser(val cheerResponse: STBaseResponse?) : STTeamDetailState()
    data class GetChallengeDetails(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STTeamDetailState()
    data class GetChallengeTeamMember(val challengeDetailsResponse: STChallengeTeamMemberAndStatsResponse?) :
            STTeamDetailState()
    data class GetChallengeSearcedTeamMember(val challengeDetailsResponse: STChallengeTeamMemberResponse?) :
            STTeamDetailState()
}
