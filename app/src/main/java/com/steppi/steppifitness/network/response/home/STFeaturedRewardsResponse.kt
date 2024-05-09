package com.steppi.steppifitness.network.response.home

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.network.response.home.data.STSteps
import java.io.Serializable

class STFeaturedRewardsResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    var data: STHomeScreenData? = null
) : STBaseResponse()

class STHomeScreenData(
    @Json(name = "totalSteps")
    var totalSteps: String? = null,
    @Json(name = "lastSyncStamp")
    var lastSyncStamp: String? = null,
    @Json(name = "dailyGoal")
    var dailyGoal: String? = null,
    @Json(name = "steps")
    var steps: STSteps? = null,
    @Json(name = "rewards")
    var rewards: List<STFeaturedData>? = null,
    @Json(name = "notification")
    var notification: String? = null,
    @Json(name = "isDubaiFitnessAvailable")
    var isDubaiFitnessAvailable: Boolean? = false
) : Serializable

class STFeaturedData(
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
    var merchant: STMerchantData? = null
) : Serializable
