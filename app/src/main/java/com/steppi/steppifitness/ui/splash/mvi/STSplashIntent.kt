package com.steppi.steppifitness.ui.splash.mvi

import com.steppi.steppifitness.ui.base.mvi.MviIntent


sealed class STSplashIntent : MviIntent {

    data class VersionCheck(
        val appVersion: String?,
        val deviceType: String?
    ) : STSplashIntent()

    object TimerIntent : STSplashIntent()
}
