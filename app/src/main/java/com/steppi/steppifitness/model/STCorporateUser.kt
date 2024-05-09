package com.steppi.steppifitness.model

import com.squareup.moshi.Json
import java.io.Serializable

class STCorporateUser(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "corporationId")
    var corporationId: String? = null,
    @Json(name = "isActive")
    var isActive: Boolean? = null,
    @Json(name = "isVerified")
    var isVerified: Boolean? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null,
    @Json(name = "corporation")
    var corporation: STCorporation? = null
) : Serializable

class STCorporation(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "password")
    var password: String? = null,
    @Json(name = "location")
    var location: String? = null,
    @Json(name = "logo")
    var logo: String? = null,
    @Json(name = "domains")
    var domains: List<String>? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "isActive")
    var isActive: Boolean? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null
) : Serializable
