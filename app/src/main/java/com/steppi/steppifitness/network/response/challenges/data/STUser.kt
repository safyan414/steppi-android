package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import java.io.Serializable

class STUser(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "picture")
    val picture: String? = null,
    @Json(name = "steps")
    val steps: String? = null
) : Serializable
