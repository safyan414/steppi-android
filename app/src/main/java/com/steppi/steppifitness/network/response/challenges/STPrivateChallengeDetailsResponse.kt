package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STPrivateChallengeDetailsResponse(
    @SerializedName("data")
    var data: ChallengeData?
) : STBaseResponse()

data class ChallengeData(
    @SerializedName("admin")
    var admin: Admin?,
    @SerializedName("allowedParticipants")
    var allowedParticipants: String?,
    @SerializedName("announcementDate")
    var announcementDate: String?,
    @SerializedName("challengeStatus")
    var challengeStatus: String?,
    @SerializedName("challengeType")
    var challengeType: String?,
    @SerializedName("challengeUsers")
    var challengeUsers: List<ChallengeUser?>?,
    @SerializedName("corporateChallengeCap")
    var corporateChallengeCap: Int?,
    @SerializedName("corporateId")
    var corporateId: String?,
    @SerializedName("corporation")
    var corporation: Corporation?,
    @SerializedName("costSteps")
    var costSteps: Int?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("createdBy")
    var createdBy: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("endDate")
    var endDate: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("isCompleted")
    var isCompleted: Boolean?,
    @SerializedName("isPrivate")
    var isPrivate: Boolean?,
    @SerializedName("joinCode")
    var joinCode: String?,
    @SerializedName("logo")
    var logo: String?,
    @SerializedName("merchantId")
    var merchantId: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("participantTeams")
    var participantTeams: List<ParticipantTeam?>?,
    @SerializedName("participants")
    var participants: String?,
    @SerializedName("startDate")
    var startDate: String?,
    @SerializedName("states")
    var states: States?,
    @SerializedName("targetSteps")
    var targetSteps: String?,
    @SerializedName("totalSteps")
    var totalSteps: String?,
    @SerializedName("theme")
    var theme: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("userStates")
    var userStates: UserStates?,
    @SerializedName("voucherDescription")
    var voucherDescription: String?,
    @SerializedName("winnerTeam")
    var winnerTeam: WinnerTeam?,
    @SerializedName("joined")
    var joined: Boolean? = false,
    @SerializedName("teams")
    var teams: Int?,
    @SerializedName("mvps")
    var mvps: List<Mvp?>?
) : Serializable

data class Admin(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picture")
    var picture: String?,
    @SerializedName("totalSteps")
    var totalSteps: String?,
    @SerializedName("userId")
    var userId: String?
) : Serializable

data class ChallengeUser(
    @SerializedName("cheerReceived")
    var cheerReceived: String?,
    @SerializedName("cheered")
    var cheered: Boolean?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picture")
    var picture: String?,
    @SerializedName("totalSteps")
    var totalSteps: String?,
    @SerializedName("userId")
    var userId: String?
) : Serializable

data class Corporation(
    @SerializedName("id")
    var id: String?,
    @SerializedName("logo")
    var logo: String?,
    @SerializedName("name")
    var name: String?
) : Serializable

data class ParticipantTeam(
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("teamMembers")
    var teamMembers: List<TeamMember?>?,
    @SerializedName("teamSteps")
    var teamSteps: String?
) : Serializable

data class TeamMember(
    @SerializedName("challengeId")
    var challengeId: String?,
    @SerializedName("challengeTeamId")
    var challengeTeamId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("userId")
    var userId: String?
) : Serializable

data class User(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picture")
    var picture: String?,
    @SerializedName("steps")
    var steps: String?
) : Serializable

data class States(
    @SerializedName("achievedSteps")
    var achievedSteps: Int?,
    @SerializedName("averageDailySteps")
    var averageDailySteps: Int?,
    @SerializedName("perUserAverageDailySteps")
    var perUserAverageDailySteps: Int?
) : Serializable

data class UserStates(
    @SerializedName("averageSteps")
    var averageSteps: Int?,
    @SerializedName("totalSteps")
    var totalSteps: Int?
) : Serializable

data class WinnerTeam(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("teamSteps")
    var teamSteps: String?
) : Serializable

data class Mvp(
    @SerializedName("challengeId")
    var challengeId: String?,
    @SerializedName("challengeTeamId")
    var challengeTeamId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("userId")
    var userId: String?
) : Serializable
