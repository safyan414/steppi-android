package com.steppi.steppifitness.network.response.challenges.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.Serializable

data class STChallengesListData(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "toughMudderId")
    var toughMudderId: String? = null,
    @Json(name = "status")
    var status: String? = null,
    @Json(name = "challengeStatus")
    var challengeStatus: Int? = null,
    @Json(name = "joined")
    var joined: Boolean? = false,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "logoName")
    var logoName: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "voucherDescription")
    var voucherDescription: String? = null,
    @Json(name = "challengeType")
    var challengeType: String? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "corporateChallengeType")
    var corporateChallengeType: String? = null,
    @Json(name = "theme")
    var theme: String? = null,
    @Json(name = "startDate")
    var startDate: String? = null,
    @Json(name = "allowedParticipants")
    var allowedParticipants: String? = null,
    @Json(name = "corporateChallengeCap")
    var corporateChallengeCap: String? = null,
    @Json(name = "endDate")
    var endDate: String? = null,
    @Json(name = "surveyNumber")
    var surveyNumber: String? = null,
    @Json(name = "announcementDate")
    var announcementDate: String? = null,
    @Json(name = "targetSteps")
    var targetSteps: String? = null,
    @Json(name = "urlVideo")
    var urlVideo: String = "",
    @Json(name = "urlVideoType")
    var urlVideoType: String = "",
    @Json(name = "targetDistance")
    var targetDistance: String? = null,
    @Json(name = "targetCalories")
    var targetCalories: String? = null,
    @Json(name = "targetActiveMinutes")
    var targetActiveMinutes: String? = null,
    @SerializedName("totalSteps")
    var totalSteps: String? = null,
    @SerializedName("totalActiveMinutes")
    var totalActiveMinutes: String? = null,
    @SerializedName("totalDistance")
    var totalDistance: String? = null,
    @SerializedName("totalCalories")
    var totalCalories: String? = null,
    @Json(name = "merchantId")
    var merchantId: String? = null,
    @Json(name = "corporateId")
    var corporateId: String? = null,
    @Json(name = "participants")
    var participants: String? = null,
    @Json(name = "joinCode")
    var joinCode: String? = null,
    @Json(name = "publicJoinCode")
    var publicJoinCode: Boolean? = null,
    @Json(name = "isPrivate")
    var isPrivate: Boolean? = false,
    @Json(name = "users")
    var users: List<STChallengeUsers>? = null,
    @Json(name = "merchant")
    var merchant: STMerchant? = null,
    @Json(name = "corporation")
    var corporation: STCorporationData? = null,
    @SerializedName("costSteps")
    var costSteps: Int? = null,
    @SerializedName("teams")
    var teams: String? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("createdBy")
    var createdBy: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null,
    @SerializedName("UserSubChallenge")
    var UserSubChallenge: STUserSubChallenge? = null,
    @Json(name = "hasJoined")
    var hasJoined: Boolean? = false,
    @Json(name = "states")
    var states: STStates? = null,
    @Json(name = "userStates")
    var userStates: STUserStates? = null,
    @Json(name = "participantTeams")
    var participantTeams: List<STParticipantTeams>? = null,
    @Json(name = "myTeam")
    var myTeam: STParticipantTeams? = null,
    @Json(name = "mvps")
    var mvps: List<STMVPTeamList>? = null,
    @Json(name = "winnerTeam")
    var winnerTeam: STParticipantTeams? = null,

    //Countdown timer
    @Json(name = "startTime")
    var startTime: String? = null,
    @Json(name = "endTime")
    var endTime: String? = null,

    @Json(name = "isCompleted")
    var isCompleted: Boolean? = false,
    @Json(name = "images")
    var images: List<String?>? = null,
    @Json(name = "logo")
    var logo: String? = null,
    @Json(name = "logoUrl")
    var logoUrl: String? = null,
    @Json(name = "endType")
    var endType: String? = null,
    @Json(name = "privateChallengeLink")
    var privateChallengeLink: String? = null,
    @Json(name = "challengeUsers")
    var challengeUsers: List<STLeaderBoardChallengeUsers>? = null,
    @SerializedName("admin")
    var admin: Admin? = null,
    @Json(name = "myUser")
    val myUser: STLeaderBoardMyUser? = null,
    @Json(name = "daysLeft")
    val daysLeft: Int? = null,
    @Json(name = "challengeDays")
    val challengeDays: Int? = null,
    @Json(name = "toughMudderChallengeType")
    val toughMudderChallengeType: String? = null,
    @Json(name = "isChallengeCompleted")
    val isChallengeCompleted: Boolean? = false,
    @Json(name = "isSurveyCompleted")
    val isSurveyCompleted: Boolean? = false,
    @Json(name = "showParticipants")
    val showParticipants: Boolean? = true
) : Serializable

data class STUserSubChallenge(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "toughMudderSubChallen")
    var toughMudderSubChallen: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "initialStepCount")
    var initialStepCount: String? = null,
    @Json(name = "initialDistanceCount")
    var initialDistanceCount: String? = null,
    @Json(name = "initialCaloriesCount")
    var initialCaloriesCount: String? = null,
    @Json(name = "initialActiveMinutesCount")
    var initialActiveMinutesCount: String? = null,
    @Json(name = "status")
    var status: Int? = null,
    @Json(name = "stepsInChallenge")
    var stepsInChallenge: String? = null,
    @Json(name = "distanceInChallenge")
    var distanceInChallenge: String? = null,
    @Json(name = "caloriesInChallenge")
    var caloriesInChallenge: String? = null,
    @Json(name = "activeMinutesInChallenge")
    var activeMinutesInChallenge: String? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null
) : Serializable
