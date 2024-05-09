package com.steppi.steppifitness.network.response.challenges

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STDailyStepsResponse(
    @Json(name = "data")
    var data: Data?,
    @Json(name = "total")
    var total: Int?
) : STBaseResponse()

data class Data(
    @Json(name = "myUser")
    var myUser: MyUser?,
    @Json(name = "steps")
    var steps: List<ChallengeUserSteps>? = null
) : Serializable

data class MyUser(
    @Json(name = "cheerReceived")
    var cheerReceived: String?,
    @Json(name = "cheered")
    var cheered: Boolean?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "averageDaily")
    var averageDaily: String?,
    @Json(name = "next")
    var next: String?,
    @Json(name = "picture")
    var picture: String?,
    @Json(name = "rank")
    var rank: String?,
    @Json(name = "totalSteps")
    var totalSteps: String?,
    @Json(name = "userId")
    var userId: String?,
    @Json(name = "achievedDailyTargets")
    var achievedDailyTargets: String? = null,
    @Json(name = "showAccomplishment")
    var showAccomplishment: Boolean = false,
    @Json(name = "showStars")
    var showStars: Boolean = false,
    @Json(name = "challengeDays")
    var challengeDays : Int? = null
) : Serializable

data class ChallengeUserSteps(
    @Json(name = "calories")
    var calories: String? = null,
    @Json(name = "date")
    var date: String? = null,
    @Json(name = "distance")
    val distance: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "steps")
    var steps: String? = null,
    @Json(name = "challengedUserId")
    val challengedUserId: String? = null,
    @Json(name = "stared")
    var stared: Boolean = false
) : Serializable
