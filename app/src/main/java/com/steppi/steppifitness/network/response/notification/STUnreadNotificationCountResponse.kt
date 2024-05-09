package com.steppi.steppifitness.network.response.notification

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STUnreadNotificationCountResponse(
    @Json(name = "data")
    val data: UnreadNotificationCountData? = null
) : STBaseResponse()

data class UnreadNotificationCountData(
    @Json(name = "count")
    val count: Int? = null
) : Serializable
