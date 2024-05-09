package com.steppi.steppifitness.network.response.corporate

import com.squareup.moshi.Json
import java.io.Serializable

class STCorporateUser(
    @Json(name = "corporation")
    val corporation: Corporation? = null,
    @Json(name = "corporationId")
    val corporationId: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "isActive")
    val isActive: Boolean,
    @Json(name = "isVerified")
    val isVerified: Boolean,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "userId")
    val userId: String
) : Serializable
