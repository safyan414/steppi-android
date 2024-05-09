package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams

data class STTeamDetailsResponse(
    @SerializedName("data")
    var data: STParticipantTeams?
) : STBaseResponse()
