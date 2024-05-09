package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import java.io.Serializable

data class STTeamLeaderBoardAnimationResponse(
    @Json(name = "total")
    var total: Int? = null,
    @SerializedName("data")
    var data: STTeamLeaderBoardData?
) : STBaseResponse()

data class STTeamLeaderBoardData(
    @SerializedName("teams")
    var teams: List<STParticipantTeams>? = null,
    @Json(name = "myTeam")
    val myTeam: STMyTeam? = null
) : Serializable

data class STMyTeam(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "status")
    val status: Int?,
    @Json(name = "teamSteps")
    val teamSteps: String?,
    @Json(name = "myTeam")
    var myTeam: Boolean?,
    @Json(name = "participants")
    val participants: String?,
    @Json(name = "totalScore")
    val totalScore: String? = "0",
    @Json(name = "dailyAverage")
    val dailyAverage: String?,
    @Json(name = "challengeId")
    val challengeId: String?,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "engagementRate")
    val engagementRate: Double?
) : Serializable
