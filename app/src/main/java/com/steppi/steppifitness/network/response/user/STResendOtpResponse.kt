package com.steppi.steppifitness.network.response.user

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STResendOtpResponse(
    @Json(name = "data")
    var data: STResendOtp? = null
) : STBaseResponse()

class STResendOtp(
    @Json(name = "regToken")
    var regToken: String? = null,
    @Json(name = "verToken")
    var verToken: String? = null
) : Serializable
