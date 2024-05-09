package com.steppi.steppifitness.ui.login.mvi

import com.steppi.steppifitness.network.request.STForgotPasswordRequest
import com.steppi.steppifitness.network.request.login.STLoginRequest
import com.steppi.steppifitness.network.request.login.STSocialAccountLoginRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STLoginIntent : MviIntent {

    data class LogInWithFaceBook(val socialAccountLoginRequest: STSocialAccountLoginRequest) :
        STLoginIntent()

    data class LogInWithInstagram(val socialAccountLoginRequest: STSocialAccountLoginRequest) :
        STLoginIntent()
    data class ForgotPassword(val forgotPasswordRequest: STForgotPasswordRequest) : STLoginIntent()

    data class LogIn(val loginRequest: STLoginRequest) :
        STLoginIntent()


}