package com.steppi.steppifitness.network.response

import com.squareup.moshi.Json
import java.io.Serializable

open class STBaseResponse : Serializable {
    @Json(name = "message")
    var message: String? = null

    @Json(name = "statusCode")
    var statusCode: Int? = null
}
