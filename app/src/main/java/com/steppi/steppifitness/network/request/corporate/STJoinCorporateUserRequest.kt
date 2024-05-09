package com.steppi.steppifitness.network.request.corporate

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest

class STJoinCorporateUserRequest : STBaseRequest(){
    @Json(name ="corporateEmail")
    var corporateEmail: String? = null
}
