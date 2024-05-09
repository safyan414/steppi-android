package com.steppi.steppifitness.network.response.challenges

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import java.io.Serializable

class STSteppiChallengeListResponse(
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "isToughMudderAvailable")
    var isToughMudderAvailable: Boolean? = null,
    @Json(name = "isDubaiFitnessAvailable")
    var isDubaiFitnessAvailable: Boolean? = null,
    @Json(name = "data")
    var data: List<STChallengesListData>? = null,
    @Json(name = "corporate")
    var corporate: CorporateTab? = null,
    @Json(name = "toughMudderTab")
    var toughMudderTab: STTabDetails? = null,
    @Json(name = "dubaiFitnessTab")
    var dubaiFitnessTab: STTabDetails? = null
) : STBaseResponse()

data class STTabDetails(
    @SerializedName("title")
    var title: String? = null,
    @Json(name = "logo")
    val logo: String? = null
) : Serializable


data class CorporateTab(
        @Json(name="tabTitle")
        var tabTitle: String? = "",
        @Json(name="tabLogo")
        val tabLogo: String? = null
) : Serializable
