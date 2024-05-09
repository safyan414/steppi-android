package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import java.io.Serializable

data class STMyUser(
    @Json(name = "id")
    val id: String?,
    @Json(name = "userId")
    val userId: String?,
    @Json(name = "totalSteps")
    val totalSteps: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "picture")
    val picture: String?,
    @Json(name = "cheerReceived")
    var cheerReceived: String?,
    @Json(name = "cheered")
    var cheered: Boolean? = false,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "achievedDailyTargets")
    var achievedDailyTargets: Int? = 0
) : Serializable
