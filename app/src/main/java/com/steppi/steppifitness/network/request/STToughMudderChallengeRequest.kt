package com.steppi.steppifitness.network.request

import com.squareup.moshi.Json

class STToughMudderChallengeRequest : STBaseRequest() {
    @Json(name = "subToughMudderChallengeId")
    var subToughMudderChallengeId: String? = null
}
