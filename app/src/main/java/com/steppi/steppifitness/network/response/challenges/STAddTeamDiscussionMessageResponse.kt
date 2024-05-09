package com.steppi.steppifitness.network.response.challenges

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STTeamDiscussionMessageListData

data class STAddTeamDiscussionMessageResponse(
    @Json(name = "total")
    val total: Int? = null,
    @Json(name = "data")
    var data: STTeamDiscussionMessageListData? = null
) : STBaseResponse()
