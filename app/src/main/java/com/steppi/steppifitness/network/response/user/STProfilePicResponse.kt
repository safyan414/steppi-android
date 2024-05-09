package com.steppi.steppifitness.network.response.user

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STProfilePicResponse(
    @Json(name = "data")
    var data: ProfileData? = null
) : STBaseResponse()

class ProfileData(
    @Json(name = "picture")
    var picture: String? = null
) : Serializable
