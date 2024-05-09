package com.steppi.steppifitness.network.response.category.data

import com.squareup.moshi.Json
import java.io.Serializable

data class STMerchantData(
    @Json(name = "description")
    val description: String?,
    @Json(name = "featured")
    val featured: Boolean?,
    @Json(name = "isDigital")
    val isDigital: Boolean?,
    @Json(name = "favorite")
    var favorite: Boolean?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "images")
    val images: List<String?>?,
    @Json(name = "logo")
    val logo: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "site")
    val site: String?
) : Serializable
