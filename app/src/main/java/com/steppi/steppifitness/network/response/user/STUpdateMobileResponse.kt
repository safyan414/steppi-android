package com.steppi.steppifitness.network.response.user

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STUpdateMobileResponse(
    @Json(name = "data")
    var data: UpdateMobileData? = null
) : STBaseResponse()

class UpdateMobileData(
    @Json(name = "verToken")
    var verToken: String? = null
) : Serializable
