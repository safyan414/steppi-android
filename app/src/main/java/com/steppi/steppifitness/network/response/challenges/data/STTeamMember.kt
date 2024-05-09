package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import java.io.Serializable

class STTeamMember(
    @Json(name = "challengeId")
    val challengeId: String? = null,
    @Json(name = "challengeTeamId")
    val challengeTeamId: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "picture")
    val picture: String? = null,
    @Json(name = "cheerReceived")
    var cheerReceived: String? = null,
    @Json(name = "cheered")
    var cheered: Boolean? = false,
    @Json(name = "updatedAt")
    val updatedAt: String? = null,
    @Json(name = "totalSteps")
    val totalSteps: String? = null,
    @Json(name = "totalActiveMinutes")
    val totalActiveMinutes: String? = null,
    @Json(name = "totalDistance")
    val totalDistance: String? = null,
    @Json(name = "totalCalories")
    val totalCalories: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "user")
    val user: STUser? = null,
    @Json(name = "userId")
    val userId: String? = null,
    @Json(name = "achievedDailyTargets")
    val achievedDailyTargets: String? = null,
    @Json(name = "steps")
    val steps: Steps? = null
) : Serializable

class Steps(
    @Json(name = "date")
    var date: String? = null,
    @Json(name = "steps")
    var steps: String? = null,
    @Json(name = "calories")
    var calories: String? = null,
    @Json(name = "distance")
    var distance: String? = null,
    @Json(name = "activeMinutes")
    var activeMinutes: String? = null
) : Serializable
