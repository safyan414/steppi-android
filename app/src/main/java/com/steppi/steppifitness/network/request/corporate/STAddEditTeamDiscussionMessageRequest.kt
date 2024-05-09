package com.steppi.steppifitness.network.request.corporate

import com.squareup.moshi.Json

class STAddEditTeamDiscussionMessageRequest {
    @Json(name ="message")
    var message: String? = null
}
