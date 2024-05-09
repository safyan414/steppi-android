package com.steppi.steppifitness.network.request.login

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STSocialAccountLoginRequest : STBaseRequest() {
    @Json(name ="fbAccessToken")
    var fbAccessToken: String? = null
    @Json(name ="instagramId")
    var instagramId: String? = null
}
