package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.category.data.STMerchantData

class STMerchantListResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    var data: List<STMerchantData>? = null
) : STBaseResponse()
