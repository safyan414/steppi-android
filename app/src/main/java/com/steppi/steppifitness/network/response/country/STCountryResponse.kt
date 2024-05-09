package com.steppi.steppifitness.network.response.country

import com.squareup.moshi.Json
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.network.response.STBaseResponse

class STCountryResponse : STBaseResponse() {
    @Json(name = "data")
    var data: List<STCountryData>? = null
}
