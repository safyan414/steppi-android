package com.steppi.steppifitness.network.response.challenges

import com.squareup.moshi.Json
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember

class STChallengeTeamMemberResponse(
        @Json(name = "teamMembers")
        var teamMembers: List<STTeamMember>? = null,

        @Json(name = "data")
        var data: List<STTeamMember>? = null,

        @Json(name = "myStats")
        var myStats: MyStats? = null
) : STBaseResponse()


class STChallengeTeamMemberAndStatsResponse(
        @Json(name = "data")
        var data: STChallengeTeamMemberResponse? = null
) : STBaseResponse()



class MyStats(
        @Json(name = "id")
        var id: String = "",

        @Json(name = "leftOn")
        var leftOn: String = "",

        @Json(name = "cheered")
        var cheered: Boolean = false,

        @Json(name = "cheerReceived")
        var cheerReceived: String = "",

        @Json(name = "achievedDailyTargets")
        var achievedDailyTargets: Int = 0,

        @Json(name = "totalSteps")
        var totalSteps: String = "",

        @Json(name = "totalDistance")
        var totalDistance: String = "",

        @Json(name = "userId")
        var userId: String = "",

        @Json(name = "totalCalories")
        var totalCalories: String = "",

        @Json(name = "rank")
        var rank: Int = 0,

        @Json(name = "challengeId")
        var challengeId: String = "",

        @Json(name = "user")
        var user: MyStatsUser? = null,

        @Json(name = "totalActiveMinutes")
        var totalActiveMinutes: String = "",
        @Json(name = "challengeTeamId")
        var challengeTeamId: String = ""

) : STBaseResponse()


class MyStatsUser(
        @Json(name = "id")
        var id: String = "",

        @Json(name = "picture")
        var picture: String = "",

        @Json(name = "steps")
        var steps: String = "",

        @Json(name = "name")
        var name: String = ""

) : STBaseResponse()

