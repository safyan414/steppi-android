package com.steppi.steppifitness.network.response.version

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STVersionCheckResponse(
    @Json(name = "data")
    var data: ANVersionData? = null
) : STBaseResponse()

class ANVersionData(
    @Json(name = "updateRequired")
    var updateRequired: Boolean? = false,
    @Json(name = "appLink")
    var appLink: String? = null
) : Serializable
