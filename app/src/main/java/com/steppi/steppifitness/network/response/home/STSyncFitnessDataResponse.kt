package com.steppi.steppifitness.network.response.home

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.home.data.STSteps
import java.io.Serializable

class STSyncFitnessDataResponse(
    @Json(name = "data")
    var data: StepsListData? = null
) : STBaseResponse()

class StepsListData(
    @Json(name = "totalSteps")
    var totalSteps: String? = null,
    @Json(name = "lastSyncStamp")
    var lastSyncStamp: String? = null,
    @Json(name = "steps")
    var steps: STSteps? = null
) : Serializable

class STStepsData(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "steps")
    var steps: String? = null,
    @Json(name = "calories")
    var calories: String? = null,
    @Json(name = "distance")
    var distance: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "fitnessDeviceId")
    var fitnessDeviceId: String? = null,
    @Json(name = "date")
    var date: String? = null
) : Serializable
