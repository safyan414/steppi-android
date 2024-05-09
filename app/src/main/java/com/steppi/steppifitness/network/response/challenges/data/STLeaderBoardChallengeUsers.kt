package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import java.io.Serializable

class STLeaderBoardChallengeUsers(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "totalSteps")
    var totalSteps: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "picture")
    var picture: String? = null,
    @Json(name = "cheerReceived")
    var cheerReceived: String? = null,
    @Json(name = "cheered")
    var cheered: Boolean? = false,
    @Json(name = "achievedDailyTargets")
    var achievedDailyTargets: Int?
) : Serializable
