package com.steppi.steppifitness.ui.device_connection.mvi

import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STDeviceIntent : MviIntent {
    data class GetFitnessDevices(val includeGarmin: Boolean) : STDeviceIntent()
    data class SelectFitnessDevice(
        val deviceId: String?,
        val accountId: String?,
        val garminAccessToken: String?,
        val garminTokenSecret: String?
    ) :
        STDeviceIntent()
}
