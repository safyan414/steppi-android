package com.steppi.steppifitness.network.response.home

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STWhatsNewLatestOnBoardingScreensResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    var data: List<STLastUsedVersionData>? = null
) : STBaseResponse()

data class STLastUsedVersionData(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "iosVersion")
    var iosVersion: String? = null,
    @Json(name = "androidVersion")
    var androidVersion: String? = null,
    @Json(name = "screens")
    var screens: List<String>? = null,
    @Json(name = "isActive")
    var isActive: Boolean? = false,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null
) : Serializable
