package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import java.io.Serializable

class STMerchant(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "logo")
    var logo: String? = null
) : Serializable
