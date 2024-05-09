package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STMyUser
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import java.io.Serializable

data class STTeamLeaderBoardDetailsResponse(
    @Json(name = "total")
    var total: Int? = null,
    @SerializedName("data")
    var data: STChallengeUserData?
) : STBaseResponse()

data class STChallengeUserData(
    @Json(name = "challengeUser")
    val challengeUser: List<STTeamMember>? = null,
    @Json(name = "myUser")
    val myUser: STMyUser? = null
) : Serializable
