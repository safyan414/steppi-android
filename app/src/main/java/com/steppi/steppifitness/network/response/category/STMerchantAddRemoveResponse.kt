package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STMerchantAddRemoveResponse(
    @Json(name = "data")
    var data: STMerchantAddRemoveData? = null
) : STBaseResponse()

data class STMerchantAddRemoveData(
    @Json(name = "favorite")
    val favorite: Boolean?
) : Serializable
