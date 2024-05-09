package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

data class STRemoveChallengeResponse(
    @SerializedName("data")
    var `data`: RemoveChallengeData?
) : STBaseResponse()

data class RemoveChallengeData(
    @SerializedName("archived")
    var archived: Boolean?
) : Serializable
