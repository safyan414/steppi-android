package com.steppi.steppifitness.network.response.corporate

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse

class STCorporateUserDataResponse(
    @Json(name = "data")
    var data: STCorporateUserData? = null
) : STBaseResponse()

class STCorporateUserData(
    @Json(name = "corporateUser")
    val corporateUser: STCorporateUser
)
