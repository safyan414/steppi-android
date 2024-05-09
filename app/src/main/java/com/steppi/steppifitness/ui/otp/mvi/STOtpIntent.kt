package com.steppi.steppifitness.ui.otp.mvi

import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import okhttp3.MultipartBody
import okhttp3.RequestBody

sealed class STOtpIntent : MviIntent {

    //data class ValidateOtp(val validateOtpRequest: STValidateOtpRequest) : STOtpIntent()

    data class ValidateOtp(
        val file: MultipartBody.Part?,
        val map: HashMap<String, RequestBody>
    ) : STOtpIntent()

    data class ResendOtp(val resendOtpRequest: STBaseRequest) : STOtpIntent()
}