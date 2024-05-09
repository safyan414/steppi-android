package com.steppi.steppifitness.ui.device_connection.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.devices.STFitnessDeviceResponse
import com.steppi.steppifitness.network.response.devices.STSelectedFitnessDeviceResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STDeviceState : MviViewState {

    object Loading : STDeviceState()

    data class Error(val errorData: STErrorData?) : STDeviceState()

    data class GetFitnessDevices(val deviceResponse: STFitnessDeviceResponse) :
        STDeviceState()

    data class SelectFitnessDevice(val deviceSelectionResponse: STSelectedFitnessDeviceResponse) :
        STDeviceState()


}