package com.steppi.steppifitness.network.response.challenges

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData

class STChallengeDetailsResponse(
    @Json(name = "data")
    var data: STChallengesListData? = null
) : STBaseResponse()
