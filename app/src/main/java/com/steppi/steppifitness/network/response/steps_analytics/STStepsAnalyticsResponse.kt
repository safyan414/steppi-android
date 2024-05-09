package com.steppi.steppifitness.network.response.steps_analytics

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STStepsAnalyticsResponse(
    @Json(name = "data")
    val data: List<StepsAnalyticsData>? = null,
    @Json(name = "total")
    val total: Int? = null
) : STBaseResponse()

data class StepsAnalyticsData(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "steps")
    var steps: String? = null,
    @Json(name = "calories")
    var calories: String? = null,
    @Json(name = "distance")
    var distance: String? = null,
    @Json(name = "activeMinutes")
    var activeMinutes: String? = "0",
    @Json(name = "userId")
    val userId: String? = null,
    @Json(name = "fitnessDeviceId")
    val fitnessDeviceId: String? = null,
    @Json(name = "date")
    var date: String? = null
) : Serializable
