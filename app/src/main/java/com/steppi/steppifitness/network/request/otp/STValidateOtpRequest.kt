package com.steppi.steppifitness.network.request.otp

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STValidateOtpRequest : STBaseRequest() {
    @Json(name = "userId")
    var userId: String? = null
    @Json(name = "otp")
    var otp: String? = null
    @Json(name = "verToken")
    var verToken: String? = null
}
