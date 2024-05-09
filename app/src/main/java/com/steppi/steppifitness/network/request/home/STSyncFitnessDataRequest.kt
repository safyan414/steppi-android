package com.steppi.steppifitness.network.request.home

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest
import java.io.Serializable

class STSyncFitnessDataRequest : STBaseRequest() {
    @Json(name = "logs")
    var logs: List<STStepsRequestData>? = null
}

class STStepsRequestData(
    @Json(name = "steps")
    var steps: Int? = null,
    @Json(name = "calories")
    var calories: Int? = null,
    @Json(name = "distance")
    var distance: Double? = null,
    @Json(name = "date")
    var date: String? = null,
    @Json(name = "provider")
    var provider: String? = null,
    @Json(name = "activeMinutes")
    var activeMinutes: Int? = null
) : Serializable
