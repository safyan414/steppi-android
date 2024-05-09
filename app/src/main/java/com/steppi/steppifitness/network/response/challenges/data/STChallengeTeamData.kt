package com.steppi.steppifitness.network.response.challenges.data

import com.google.gson.annotations.SerializedName
import com.steppi.steppifitness.network.response.challenges.User
import java.io.Serializable

data class STChallengeTeamData(
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("teamMembers")
    var teamMembers: List<TeamMember?>?,
    @SerializedName("teamSteps")
    var teamSteps: String?
) : Serializable

data class TeamMember(
    @SerializedName("challengeId")
    var challengeId: String?,
    @SerializedName("challengeTeamId")
    var challengeTeamId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("userId")
    var userId: String?
): Serializable

data class User(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picture")
    var picture: String?,
    @SerializedName("steps")
    var steps: String?
):Serializable
