package com.steppi.steppifitness.ui.login.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.login.STSocialRegisterResponse
import com.steppi.steppifitness.network.response.user.STUserDataResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STLoginState : MviViewState {

    object Loading : STLoginState()

    data class Error(val errorData: STErrorData?) : STLoginState()

    data class LogInWithFaceBook(val fbAccountLoginData: STSocialRegisterResponse) :
        STLoginState()

    data class LogInWithInstagram(val instaAccountLoginData: STSocialRegisterResponse) :
        STLoginState()

    data class ForgotPassword(val forgotPasswordResponse: STBaseResponse) :
        STLoginState()

    data class LogIn(val userDataResponse: STUserDataResponse) :
        STLoginState()


}