package com.steppi.steppifitness.network.request.login

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.request.STBaseRequest
import java.io.Serializable

class STRegisterRequest : STBaseRequest(), Serializable {
    @Json(name = "email")
    var email: String? = null
    @Json(name = "phoneNumber")
    var phoneNumber: String? = null
    @Json(name = "name")
    var name: String? = null
    @Json(name = "password")
    var password: String? = null
    @Json(name = "gender")
    var gender: Int? = null
    @Json(name = "dob")
    var dob: String? = null
    @Json(name = "countryId")
    var countryId: String? = null
    @Json(name = "corporateEmail")
    var corporateEmail: String? = null
    @Json(name = "referralCode")
    var referralCode: String? = null
    @Json(name = "facebookId")
    var facebookId: String? = null
    @Json(name = "instagramId")
    var instagramId: String? = null
    //var imageFile: File? = null
}
