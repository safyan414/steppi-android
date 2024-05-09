package com.steppi.steppifitness.network.request.home

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STWhatsNewVersionUpdateRequest : STBaseRequest() {
    @Json(name = "version")
    var version: Int? = 0
}
