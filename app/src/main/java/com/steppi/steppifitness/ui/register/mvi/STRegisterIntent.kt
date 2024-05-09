package com.steppi.steppifitness.ui.register.mvi

import com.steppi.steppifitness.network.request.login.STRegisterRequest
import com.steppi.steppifitness.network.request.otp.STValidateOtpRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STRegisterIntent : MviIntent {

    object GetCountries : STRegisterIntent()

    /*data class Register(
        val file: MultipartBody.Part?,
        val map: HashMap<String, RequestBody>
    ) : STRegisterIntent()*/

    data class Register(val registerRequest: STRegisterRequest) : STRegisterIntent()

    data class ValidateOtp(val validateOtpRequest: STValidateOtpRequest) : STRegisterIntent()
}