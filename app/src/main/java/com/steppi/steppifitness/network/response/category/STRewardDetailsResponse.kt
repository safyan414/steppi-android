package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import java.io.Serializable

class STRewardDetailsResponse(
    @Json(name = "data")
    var data: RewardDetails? = null
) : STBaseResponse()

data class RewardDetails(
    @Json(name = "description")
    val description: String,
    @Json(name = "estimateSaving")
    val estimateSaving: String,
    @Json(name = "expireOn")
    val expireOn: String? = null,
    @Json(name = "id")
    val id: String,
    @Json(name = "merchantId")
    val merchantId: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "redeemed")
    val redeemed: String,
    @Json(name = "requiredSteps")
    val requiredSteps: String,
    @Json(name = "store")
    val store: Store? = null,
    @Json(name = "rewardShareUrl")
    val rewardShareUrl: String,
    @Json(name = "rewardType")
    val rewardType: Int?,
    @Json(name = "howTo")
    val howTo: String? = null,
    @Json(name = "merchant")
    val merchant: STMerchantData
) : Serializable

data class Store(
    @Json(name = "contactEmail")
    val contactEmail: String,
    @Json(name = "country")
    val country: Country? = null,
    @Json(name = "id")
    val id: String,
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "location")
    val location: String,
    @Json(name = "longitude")
    val longitude: Double,
    @Json(name = "name")
    val name: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String
) : Serializable

data class Country(
    @Json(name = "currency")
    val currency: String,
    @Json(name = "flag")
    val flag: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "phoneCode")
    val phoneCode: String,
    @Json(name = "shortCode")
    val shortCode: String
) : Serializable
