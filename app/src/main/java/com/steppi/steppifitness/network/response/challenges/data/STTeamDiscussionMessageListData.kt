package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import java.io.Serializable

class STTeamDiscussionMessageListData(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "teamId")
    val teamId: String? = null,
    @Json(name = "userId")
    val userId: String? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "updatedAt")
    val updatedAt: String? = null,
    @Json(name = "totalReplies")
    val totalReplies: String? = null,
    @Json(name = "user")
    val user: STUser? = null,
    @Json(name = "repliedTo")
    val repliedTo: Any? = null
) : Serializable
