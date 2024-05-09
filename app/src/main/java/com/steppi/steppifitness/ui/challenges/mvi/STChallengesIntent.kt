package com.steppi.steppifitness.ui.challenges.mvi

import com.steppi.steppifitness.network.request.STToughMudderChallengeRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import okhttp3.RequestBody

sealed class STChallengesIntent : MviIntent {
    data class GetSteppiChallengesList(val type: String, val offset: Int = 0) : STChallengesIntent()
    object ToughMudderChallengeList : STChallengesIntent()
    data class GetMyChallenges(val offset: Int = 0) : STChallengesIntent()
    data class GetChallengeDetails(val challengeId: String) : STChallengesIntent()
    data class GetCorporateChallengeDetails(val challengeId: String) : STChallengesIntent()
    data class JoinLeaveChallenge(val operationType: String, val challengeId: String) :
        STChallengesIntent()

    data class GetViewAllLeaderboardData(val challengeId: String, val offset: Int = 0) :
        STChallengesIntent()

    data class CheerChallengeUser(val challengeId: String, val challengeUserId: String) :
        STChallengesIntent()

    data class GetPrivateChallengeList(val offset: Int = 0) : STChallengesIntent()
    data class FindChallenge(val joinCode: String) : STChallengesIntent()
    data class CreatePrivateChallenge(
        val privateChallengeTemplateId: String,
        val currentDate: RequestBody,
        val theme: RequestBody
    ) : STChallengesIntent()

    data class GetMyChallengesByType(val offset: Int = 0, val listType: String) :
        STChallengesIntent()

    data class JoinLeavePrivateChallenge(
        val operationType: String,
        val challengeId: String,
        val joinCode: String
    ) :
        STChallengesIntent()

    data class GetPrivateChallengeDetails(val challengeId: String, val joinCode: String) :
        STChallengesIntent()

    data class JoinPublicChallengeTeam(
        val challengeId: String,
        val challengeTeamId: String,
        val publicJoinCode: String
    ) :
        STChallengesIntent()

    data class GetDailyStepOfUser(val challengeId: String/*, val offset: Int = 0*/) :
        STChallengesIntent()

    data class GetChallengeTeamDetails(val teamId: String) : STChallengesIntent()
    data class GetChallengeTeamLeaderBoardDetails(
        val challengeId: String,
        val teamId: String,
        val offset: Int = 0
    ) :
        STChallengesIntent()

    data class GetTeamLeaderBoardAnimation(val challengeId: String, val offset: Int = 0) :
        STChallengesIntent()

    data class RemoveChallenge(val challengeId: String, val position: Int) : STChallengesIntent()

    data class JoinToughMudderChallenge(val operationType: String, val id: String) :
        STChallengesIntent()

    data class GetToughMudderChallengeDetails(val subChallengeId: String) : STChallengesIntent()

    data class StartToughMudderChallenge(val subChallengeId: String) : STChallengesIntent()

    data class CompleteToughMudderChallenge(val toughMudderChallengeRequest: STToughMudderChallengeRequest) :
        STChallengesIntent()

    data class JoinLeaveDFCChallenge(
        val operationType: String,
        val challengeId: String,
        val joinCode: String
    ) : STChallengesIntent()
}
