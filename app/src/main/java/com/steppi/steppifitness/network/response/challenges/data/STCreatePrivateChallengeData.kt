package com.steppi.steppifitness.network.response.challenges.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class STCreatePrivateChallengeData(
    @SerializedName("costSteps")
    var costSteps: Int?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("logo")
    var logo: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("noOf")
    var noOf: Int?,
    @SerializedName("type")
    var type: String?
): Serializable
