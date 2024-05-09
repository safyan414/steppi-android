package com.steppi.steppifitness.network.response.challenges.data

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STLeaderboardResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "data")
    val data: STLeaderboardDetail?
) : STBaseResponse()

data class STLeaderboardDetail(
    @Json(name = "challengeUser")
    val challengeUser: List<STLeaderBoardChallengeUsers>?,
    @Json(name = "myUser")
    val myUser: STLeaderBoardMyUser?
) : Serializable
