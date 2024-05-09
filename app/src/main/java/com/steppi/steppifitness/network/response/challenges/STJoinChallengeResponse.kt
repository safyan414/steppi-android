package com.steppi.steppifitness.network.response.challenges

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse

class STJoinChallengeResponse(
    @Json(name = "data")
    var data: Boolean? = true
) : STBaseResponse()
