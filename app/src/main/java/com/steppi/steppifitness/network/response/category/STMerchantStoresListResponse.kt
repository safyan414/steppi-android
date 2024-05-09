package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import java.io.Serializable

class STMerchantStoresListResponse(
    @Json(name = "data")
    var data: List<STMerchantStoresListData>? = null
) : STBaseResponse()

data class STMerchantStoresListData(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "contactEmail")
    val contactEmail: String?,
    @Json(name = "phoneNumber")
    val phoneNumber: String?,
    @Json(name = "site")
    val site: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "latitude")
    val latitude: Double?,
    @Json(name = "longitude")
    val longitude: Double?,
    @Json(name = "merchants")
    var merchants: List<STMerchantsData>? = null
) : Serializable

data class STMerchantsData(
    @Json(name = "id")
    val id: String?,
    @Json(name = "merchant")
    var merchant: List<STMerchantData>? = null
) : Serializable
