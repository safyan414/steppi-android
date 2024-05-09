package com.steppi.steppifitness.model

import com.squareup.moshi.Json
import java.io.Serializable

class STDeviceData(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "deviceCode")
    var deviceCode: String? = null
) : Serializable
