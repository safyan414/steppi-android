package com.steppi.steppifitness.model

import com.squareup.moshi.Json
import java.io.Serializable

class STCountryData : Serializable {
    @Json(name = "id")
    var id: String? = null
    @Json(name = "name")
    var name: String? = null
    @Json(name = "shortCode")
    var shortCode: String? = null
    @Json(name = "phoneCode")
    var phoneCode: String? = null
    @Json(name = "currency")
    var currency: String? = null
    @Json(name = "flag")
    var flag: String? = null
    @Json(name = "locale")
    var locale: String? = null
}
