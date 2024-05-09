package com.steppi.steppifitness.network.response.home.data

import com.squareup.moshi.Json
import java.io.Serializable

data class STSteps(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "steps")
    var steps: String? = null,
    @Json(name = "calories")
    var calories: String? = null,
    @Json(name = "distance")
    var distance: String? = null,
    @Json(name = "activeMinutes")
    var activeMinutes: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "fitnessDeviceId")
    var fitnessDeviceId: String? = null,
    @Json(name = "date")
    var date: String? = null
) : Serializable
