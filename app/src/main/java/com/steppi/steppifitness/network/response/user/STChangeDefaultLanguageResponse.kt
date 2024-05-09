package com.steppi.steppifitness.network.response.user

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse

class STChangeDefaultLanguageResponse(
    @Json(name = "data")
    var data: Boolean? = false
) : STBaseResponse()