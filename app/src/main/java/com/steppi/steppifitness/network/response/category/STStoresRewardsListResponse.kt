package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STStoresRewardsListResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    var data: List<STStoresRewardsListData>? = null
) : STBaseResponse()

data class STStoresRewardsListData(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "rewardType")
    val rewardType: Int?,
    @Json(name = "requiredSteps")
    val requiredSteps: String?,
    @Json(name = "estimateSaving")
    val estimateSaving: String?,
    @Json(name = "redeemed")
    val redeemed: String?,
    @Json(name = "merchantId")
    val merchantId: String?,
    @Json(name = "expireOn")
    val expireOn: String?
) : Serializable
