package com.steppi.steppifitness.network.request.password

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STChangePasswordRequest : STBaseRequest() {
    @Json(name = "oldPassword")
    var oldPassword: String? = null
    @Json(name = "newPassword")
    var newPassword: String? = null
}
