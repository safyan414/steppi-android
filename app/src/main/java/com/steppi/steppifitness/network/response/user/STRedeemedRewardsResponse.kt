package com.steppi.steppifitness.network.response.user

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STRedeemedRewardsResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    var data: RedeemedRewardsData? = null
) : STBaseResponse()

class RedeemedRewardsData(
    @Json(name = "lifetimeSteps")
    var lifetimeSteps: String? = null,
    @Json(name = "totalSavings")
    var totalSavings: String? = null,
    @Json(name = "redeemedSteps")
    var redeemedSteps: String? = null,
    @Json(name = "stepsRemaining")
    var stepsRemaining: String? = null,
    @Json(name = "redeemedRewards")
    var redeemedRewards: List<RedeemedRewards>? = null
) : Serializable

class RedeemedRewards(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "redeemCode")
    var redeemCode: String? = null,
    @Json(name = "redemptionCode")
    var redemptionCode: String? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "reward")
    var reward: RewardData? = null
) : Serializable

class RewardData(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "rewardType")
    var rewardType: Int? = null,
    @Json(name = "requiredSteps")
    var requiredSteps: String? = null,
    @Json(name = "estimateSaving")
    var estimateSaving: Int? = null,
    @Json(name = "redeemed")
    var redeemed: String? = null,
    @Json(name = "merchantId")
    var merchantId: String? = null,
    @Json(name = "expireOn")
    var expireOn: String? = null,
    @Json(name = "merchant")
    var merchant: MerchantData? = null
) : Serializable

class MerchantData(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "logo")
    var logo: String? = null
) : Serializable
