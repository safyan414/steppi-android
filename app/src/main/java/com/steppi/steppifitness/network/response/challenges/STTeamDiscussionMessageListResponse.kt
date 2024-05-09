package com.steppi.steppifitness.network.response.challenges

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STTeamDiscussionMessageListData

data class STTeamDiscussionMessageListResponse(
    @Json(name = "total")
    val total: Int? = null,
    @Json(name = "data")
    var data: List<STTeamDiscussionMessageListData>? = null
) : STBaseResponse()
