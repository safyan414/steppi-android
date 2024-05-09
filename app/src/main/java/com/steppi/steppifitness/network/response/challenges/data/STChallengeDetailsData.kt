package com.steppi.steppifitness.network.response.challenges.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.Serializable

//data class STChallengeDetailsData(
//    @Json(name = "id")
//    var id: String? = null,
//    @Json(name = "status")
//    var status: String? = null,
//    @Json(name = "joined")
//    var joined: Boolean? = false,
//    @Json(name = "name")
//    var name: String? = null,
//    @Json(name = "description")
//    var description: String? = null,
//    @Json(name = "voucherDescription")
//    var voucherDescription: String? = null,
//    @Json(name = "challengeType")
//    var challengeType: String? = null,
//    @Json(name = "type")
//    var type: String? = null,
//    @Json(name = "theme")
//    var theme: String? = null,
//    @Json(name = "startDate")
//    var startDate: String? = null,
//    @Json(name = "allowedParticipants")
//    var allowedParticipants: String? = null,
//    @Json(name = "corporateChallengeCap")
//    var corporateChallengeCap: String? = null,
//    @Json(name = "endDate")
//    var endDate: String? = null,
//    @Json(name = "announcementDate")
//    var announcementDate: String? = null,
//    @Json(name = "targetSteps")
//    var targetSteps: String? = null,
//    @SerializedName("totalSteps")
//    var totalSteps: String?,
//    @Json(name = "merchantId")
//    var merchantId: String? = null,
//    @Json(name = "corporateId")
//    var corporateId: String? = null,
//    @Json(name = "participants")
//    var participants: String? = null,
//    @Json(name = "joinCode")
//    var joinCode: String? = null,
//    @Json(name = "isPrivate")
//    var isPrivate: Boolean? = false,
//    @Json(name = "users")
//    var users: List<STChallengeUsers>? = null,
//    @Json(name = "merchant")
//    var merchant: STMerchant? = null,
//    @Json(name = "corporation")
//    var corporation: STCorporationData? = null,
//    @SerializedName("costSteps")
//    var costSteps: Int?,
//    @SerializedName("teams")
//    var teams: Int?,
//    @SerializedName("createdAt")
//    var createdAt: String?,
//    @SerializedName("createdBy")
//    var createdBy: String?,
//    @Json(name = "states")
//    var states: STStates? = null,
//    @Json(name = "userStates")
//    var userStates: STUserStates? = null,
//    @Json(name = "participantTeams")
//    var participantTeams: List<STParticipantTeams>? = null,
//    @Json(name = "mvps")
//    var mvps: List<STMVPTeamList>? = null,
//    @Json(name = "winnerTeam")
//    var winnerTeam: STParticipantTeams? = null,
//    @Json(name = "isCompleted")
//    var isCompleted: Boolean? = false,
//    @Json(name = "images")
//    var images: List<String?>?,
//    @Json(name = "logo")
//    var logo: String? = null,
//    @Json(name = "endType")
//    var endType: String? = null,
//    @Json(name = "privateChallengeLink")
//    var privateChallengeLink: String? = null,
//    @Json(name = "challengeUsers")
//    var challengeUsers: List<STLeaderBoardChallengeUsers>? = null,
//    @SerializedName("admin")
//    var admin: Admin? = null,
//    @Json(name = "myUser")
//    val myUser: STLeaderBoardMyUser?
//) : Serializable

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

class STChallengeUsers(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "challengeId")
    var challengeId: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null,
    @Json(name = "achievedDailyTargets")
    var achievedDailyTargets: String? =null
) : Serializable

class STStates(
    @Json(name = "achievedSteps")
    var achievedSteps: Long? = null,
    @Json(name = "perUserAverageDailySteps")
    var perUserAverageDailySteps: Long? = null,
    @Json(name = "averageDailySteps")
    var averageDailySteps: Long? = null
) : Serializable

class STUserStates(
    @Json(name = "averageSteps")
    var averageSteps: Long? = null,
    @Json(name = "totalSteps")
    var totalSteps: Long? = null
) : Serializable

class STMVPTeamList(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "challengeId")
    val challengeId: String? = null,
    @Json(name = "teamName")
    val teamName: String? = null,
    @Json(name = "userId")
    val userId: String? = null,
    @Json(name = "challengeTeamId")
    val challengeTeamId: String? = null,
    @Json(name = "totalSteps")
    val totalSteps: String? = null,
    @Json(name = "averageDaily")
    val averageDaily: String? = null,
    @Json(name = "leftOn")
    val leftOn: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "updatedAt")
    val updatedAt: String? = null,
    @Json(name = "cheerReceived")
    val cheerReceived: String? = null,
    @Json(name = "cheered")
    var cheered: Boolean? = false,
    @Json(name = "user")
    val user: STMVPUserDetails? = null
) : Serializable

data class STMVPUserDetails(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picture")
    var picture: String?,
    @SerializedName("steps")
    var steps: String?
) : Serializable
