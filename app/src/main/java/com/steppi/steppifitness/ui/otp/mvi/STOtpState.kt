package com.steppi.steppifitness.ui.otp.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.user.STResendOtpResponse
import com.steppi.steppifitness.network.response.user.STUserDataResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STOtpState : MviViewState {

    object Loading : STOtpState()
    data class Error(val errorData: STErrorData?) : STOtpState()

    data class ValidateOtp(val validateOtpResponse: STUserDataResponse) : STOtpState()
    data class ResendOtp(val resendOtpResponse: STResendOtpResponse) : STOtpState()
}