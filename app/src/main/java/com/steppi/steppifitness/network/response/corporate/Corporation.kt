package com.steppi.steppifitness.network.response.corporate

import com.squareup.moshi.Json
import java.io.Serializable

class Corporation(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "domains")
    val domains: List<String>,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "isActive")
    val isActive: Boolean,
    @Json(name = "location")
    val location: String,
    @Json(name = "logo")
    val logo: String,
    @Json(name = "cover")
    val cover: String? = null,
    @Json(name = "name")
    val name: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "updatedAt")
    val updatedAt: String
) : Serializable
