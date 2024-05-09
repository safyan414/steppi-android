package com.steppi.steppifitness.ui.fitness_analytics.mvi

import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STAnalyticsIntent : MviIntent {
    data class GetAnalyticsData(
        val date: String
    ) : STAnalyticsIntent()
}
