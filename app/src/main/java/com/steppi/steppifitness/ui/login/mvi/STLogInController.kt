package com.steppi.steppifitness.ui.login.mvi

import android.app.Application
import android.util.Log
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.STForgotPasswordRequest
import com.steppi.steppifitness.network.request.login.STLoginRequest
import com.steppi.steppifitness.network.request.login.STSocialAccountLoginRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class STLogInController : MviBaseController<STLoginState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STLoginState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STLoginIntent.LogInWithFaceBook -> loginWithFaceBook(
                    application,
                    incomingIntent.socialAccountLoginRequest
                )
                is STLoginIntent.ForgotPassword -> forgotPassword(
                    application,
                    incomingIntent.forgotPasswordRequest
                )
                is STLoginIntent.LogIn -> login(
                    application,
                    incomingIntent.loginRequest
                )
                is STLoginIntent.LogInWithInstagram -> loginWithInstagram(
                    application,
                    incomingIntent.socialAccountLoginRequest
                )


                else -> null
            }
        }

    private fun loginWithFaceBook(
        application: Application,
        socialAccountLoginRequest: STSocialAccountLoginRequest

    ): Observable<STLoginState> {
        return STRetrofitClient.create(application).fbLogin(socialAccountLoginRequest)
            .doOnError {}
            .map { STLoginState.LogInWithFaceBook(it) }
            .cast(STLoginState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STLoginState.Error(error)
                } else {
                    errorData?.message = application.resources.getString(R.string.no_internet)
                    STLoginState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STLoginState.Loading)
    }

    private fun loginWithInstagram(
        application: Application,
        socialAccountLoginRequest: STSocialAccountLoginRequest

    ): Observable<STLoginState> {
        return STRetrofitClient.create(application).instaLogin(socialAccountLoginRequest)
            .doOnError {}
            .map { STLoginState.LogInWithInstagram(it) }
            .cast(STLoginState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STLoginState.Error(error)
                } else {
                    errorData?.message = application.resources.getString(R.string.no_internet)
                    STLoginState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STLoginState.Loading)
    }

    private fun login(
        application: Application,
        loginRequest: STLoginRequest

    ): Observable<STLoginState> {
        return STRetrofitClient.create(application).login(loginRequest)
            .doOnError {}
            .map { STLoginState.LogIn(it) }
            .cast(STLoginState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STLoginState.Error(error)
                } else {
                    errorData?.message = application.resources.getString(R.string.no_internet)
                    STLoginState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STLoginState.Loading)
    }

    private fun forgotPassword(
        application: Application,
        forgotPasswordRequest: STForgotPasswordRequest

    ): Observable<STLoginState> {
        return STRetrofitClient.create(application).forgotPassword(forgotPasswordRequest)
            .doOnError {
//                Log.e("ErrorCheck", "Error ${it.message}")
            }
            .map { STLoginState.ForgotPassword(it) }
            .cast(STLoginState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STLoginState.Error(error)
                } else {
                    errorData?.message = application.resources.getString(R.string.no_internet)
                    STLoginState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STLoginState.Loading)
    }

}
