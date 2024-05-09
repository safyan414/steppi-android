package com.steppi.steppifitness.network.response.notification

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STNotificationListResponse(
    @Json(name = "data")
    val data: List<NotificationData>? = null,
    @Json(name = "total")
    val total: Int? = null
) : STBaseResponse()

data class NotificationData(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "challenge")
    val challenge: Challenge? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "from")
    val from: FromUser? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "isRead")
    val isRead: Boolean? = null,
    @Json(name = "isSent")
    val isSent: Boolean? = null,
    @Json(name = "relatedChallengeId")
    val relatedChallengeId: String? = null,
    @Json(name = "relatedRewardId")
    val relatedRewardId: String? = null,
    @Json(name = "relatedUserId")
    val relatedUserId: String? = null,
    @Json(name = "reward")
    val reward: Reward? = null,
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "notificationType")
    val notificationType: String? = null,
    @Json(name = "isInteractive")
    val isInteractive: Boolean? = false,
    @Json(name = "updatedAt")
    val updatedAt: String? = null,
    @Json(name = "userId")
    val userId: String? = null,
    @Json(name = "toughMudderChallenge")
    val toughMudderChallenge: ToughMudderChallenge? = null,
    @Json(name = "toughMudderSubChallenge")
    val toughMudderSubChallenge: ToughMudderSubChallenge? = null
) : Serializable

data class Challenge(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "challengeType")
    val challengeType: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "theme")
    val theme: String? = null
) : Serializable

data class ToughMudderChallenge(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "startDate")
    val startDate: String? = null,
    @Json(name = "endDate")
    val endDate: String? = null
) : Serializable

data class ToughMudderSubChallenge(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "targetSteps")
    val targetSteps: String? = null,
    @Json(name = "targetDistance")
    val targetDistance: String? = null,
    @Json(name = "targetCalories")
    val targetCalories: String? = null,
    @Json(name = "startDate")
    val startDate: String? = null,
    @Json(name = "endDate")
    val endDate: String? = null,
    @Json(name = "challengeType")
    val challengeType: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "theme")
    val theme: String? = null,
    @Json(name = "surveyNumber")
    val surveyNumber: String? = null
) : Serializable

data class Reward(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
) : Serializable

data class FromUser(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
) : Serializable
