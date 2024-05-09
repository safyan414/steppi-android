package com.steppi.steppifitness.network.response.challenges.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.Serializable

data class STParticipantTeams(
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "status")
    val status: Int? = null,
    @Json(name = "rank")
    val rank: Int? = 0,
    @Json(name = "teamMembers")
    val teamMembers: MutableList<STTeamMember>? = null,
    @Json(name = "myUser")
    val myUser: STMyUser? = null,
    @Json(name = "teamSteps")
    val teamSteps: String? = null,
    @Json(name = "totalScore")
    val totalScore: String? = "0",
    @Json(name = "engagementRate")
    val engagementRate: Double? = null,
    @Json(name = "dailyAverage")
    val dailyAverage: String? = "0",
    @Json(name = "picture")
    val picture: String? = null,
    @Json(name = "myTeam")
    val myTeam: Boolean = false,
    @Json(name = "participants")
    val participants: String? = null,
    @Json(name = "challengeId")
    val challengeId: String? = null,
    @SerializedName("totalSteps")
    var totalSteps: String?,
    @Json(name = "cheerReceived")
    var cheerReceived: String? = null,
    @Json(name = "cheered")
    var cheered: Boolean? = false,
    @Json(name = "teamRank")
    var teamRank: Int? = 0
) : Serializable
