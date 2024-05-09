package com.steppi.steppifitness.ui.splash.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.version.STVersionCheckResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STSplashState : MviViewState {
    object Loading : STSplashState()
    data class Error(val errorData: STErrorData?) : STSplashState()
    object TimerState : STSplashState()
    data class VersionCheck(val versionCheckResponse: STVersionCheckResponse) : STSplashState()
}
