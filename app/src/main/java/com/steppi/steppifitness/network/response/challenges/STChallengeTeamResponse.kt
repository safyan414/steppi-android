package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengeTeamData

data class STChallengeTeamResponse(
    @SerializedName("data")
    var `data`: List<STChallengeTeamData?>?,
    @SerializedName("total")
    var total: Int?
) : STBaseResponse()
