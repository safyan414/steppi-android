package com.steppi.steppifitness.ui.challenges.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.*
import com.steppi.steppifitness.network.response.challenges.data.STLeaderboardResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STChallengesState : MviViewState {
    object Loading : STChallengesState()
    data class Error(val errorData: STErrorData?) : STChallengesState()

    data class GetSteppiChallengeList(val steppiChallengeListResponse: STSteppiChallengeListResponse?) :
        STChallengesState()

    data class GetMyChallenges(val myChallengeListResponse: STSteppiChallengeListResponse?) :
        STChallengesState()

    data class GetChallengeDetails(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class GetCorporateChallengeDetails(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class JoinLeaveChallenge(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class CompleteToughMudderChallenge(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class GetViewAllLeaderboardData(val leaderboardResponse: STLeaderboardResponse?) :
        STChallengesState()

    data class CheerChallengeUser(val cheerResponse: STBaseResponse?) : STChallengesState()

    data class GetPrivateChallengeTemplatesList(val privateChallengesResponse: STPrivateChallengeResponse) :
        STChallengesState()

    data class FindChallenge(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class CreatePrivateChallenge(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class GetMyChallengesByType(val myChallengeListResponse: STSteppiChallengeListResponse?) :
        STChallengesState()

    data class JoinLeavePrivateChallenge(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class GetPrivateChallengeDetails(val challengeDetailsResponse: STChallengeDetailsResponse?) :
        STChallengesState()

    data class JoinPublicTeamChallenge(val challengeDetailsResponse: STChallengeDetailsResponse) :
        STChallengesState()

    data class GetDailyStepOfUser(val dailyStepResponse: STDailyStepsResponse) : STChallengesState()
    data class GetTeamDetails(val teamDetailsResponse: STTeamDetailsResponse) : STChallengesState()
    data class GetTeamLeaderBoardDetails(val teamLeaderBoardDetailsResponse: STTeamLeaderBoardDetailsResponse) :
        STChallengesState()
    data class GetTeamLeaderBoardAnimation(val teamLeaderBoardAnimationResponse: STTeamLeaderBoardAnimationResponse?) :
        STChallengesState()
    data class RemoveChallenge(val deleteResponse: STRemoveChallengeResponse, val pos : Int): STChallengesState()
    data class JoinToughMudderChallenge(val deleteResponse: STRemoveChallengeResponse, val pos : Int): STChallengesState()

}
