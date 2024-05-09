package com.steppi.steppifitness.network.response.user

import com.squareup.moshi.Json
import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STUserDataResponse(
    @Json(name = "data")
    var data: STUserData? = null
) : STBaseResponse()

class STUserData(
    @Json(name = "user")
    var user: STUser? = null,
    @Json(name = "regToken")
    var regToken: String? = null,
    @Json(name = "accessToken")
    var accessToken: String? = null
) : Serializable
