package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STCategoryResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    var data: List<STCategory>? = null
) : STBaseResponse()

class STCategory(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "logo")
    var logo: String? = null,
    @Json(name = "colorCode")
    var colorCode: String? = null
) : Serializable
