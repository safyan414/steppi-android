package com.steppi.steppifitness.network.request.version

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STVersionCheckRequest : STBaseRequest() {
    @Json(name = "appVersion")
    var appVersion: String? = null
}
