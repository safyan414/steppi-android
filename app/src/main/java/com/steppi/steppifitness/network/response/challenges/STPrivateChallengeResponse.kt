package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STCreatePrivateChallengeData

data class STPrivateChallengeResponse(
    @Json(name = "total")
    var total: Int? = null,
    @SerializedName("data")
    var `data`: List<STCreatePrivateChallengeData?>?
) : STBaseResponse()
