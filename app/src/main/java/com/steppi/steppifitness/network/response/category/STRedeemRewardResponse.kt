package com.steppi.steppifitness.network.response.category

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STRedeemRewardResponse(
    @Json(name = "data")
    var data: RedeemRewardData? = null
) : STBaseResponse()

data class RedeemRewardData(
    @Json(name = "id")
    val id: String,
    @Json(name = "redeemCode")
    val redeemCode: String,
    @Json(name = "redemptionCode")
    val redemptionCode: String,
    @Json(name = "reward")
    val reward: Reward
): Serializable

data class Reward(
    @Json(name = "description")
    val description: String,
    @Json(name = "estimateSaving")
    val estimateSaving: String,
    @Json(name = "expireOn")
    val expireOn: String,
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
    @Json(name = "rewardType")
    val rewardType: Int
) : Serializable
