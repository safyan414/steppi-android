package com.steppi.steppifitness.ui.fitness_analytics.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.steps_analytics.STStepsAnalyticsResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STAnalyticsState : MviViewState {
    object Loading : STAnalyticsState()

    data class Error(val errorData: STErrorData?) : STAnalyticsState()

    data class AnalyticsList(val stepsAnalyticsResponse: STStepsAnalyticsResponse) : STAnalyticsState()

}
