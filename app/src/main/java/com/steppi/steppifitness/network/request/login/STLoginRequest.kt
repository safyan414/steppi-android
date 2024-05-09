package com.steppi.steppifitness.network.request.login

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STLoginRequest : STBaseRequest() {
    @Json(name = "phoneNumber")
    var phoneNumber: String? = null
    @Json(name = "password")
    var password: String? = null
}
