package com.steppi.steppifitness.network.request.reward

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STRedeemRewardRequest : STBaseRequest() {
    @Json(name = "storeId")
    var storeId: String? = null

    @Json(name = "latitude")
    var latitude: Double? = null

    @Json(name = "longitude")
    var longitude: Double? = null

    @Json(name = "pin")
    var pin: Int? = null
}
