package com.steppi.steppifitness.network.request

import com.squareup.moshi.Json
import java.io.Serializable

open class STBaseRequest : Serializable {
    @Json(name = "deviceId")
    var deviceId: String? = null
    @Json(name = "deviceType")
    var deviceType: String? = null
    @Json(name = "deviceToken")
    var deviceToken: String? = null
    @Json(name = "regToken")
    var regToken: String? = null
    @Json(name = "lang")
    var lang: String? = null
    @Json(name = "fingerprint")
    var fingerprint: String? = null
}
