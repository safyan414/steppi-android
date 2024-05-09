package com.steppi.steppifitness.ui.otp.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class STOtpController : MviBaseController<STOtpState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STOtpState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STOtpIntent.ValidateOtp -> validateOtp(
                    application,
                    incomingIntent.file,
                    incomingIntent.map
                )
                is STOtpIntent.ResendOtp -> resendOtp(
                    application,
                    incomingIntent.resendOtpRequest
                )
                else -> null
            }
        }

    private fun validateOtp(
        application: Application,
        file: MultipartBody.Part?,
        map: HashMap<String, RequestBody>
    ): Observable<STOtpState> {
        return STRetrofitClient.create(application).validateOtp(file, map)
            .doOnError {}
            .map { STOtpState.ValidateOtp(it) }
            .cast(STOtpState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STOtpState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STOtpState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STOtpState.Loading)
    }

    private fun resendOtp(
        application: Application,
        resendOtpRequest: STBaseRequest

    ): Observable<STOtpState> {
        return STRetrofitClient.create(application).resendOtp(resendOtpRequest)
            .doOnError {}
            .map { STOtpState.ResendOtp(it) }
            .cast(STOtpState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STOtpState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STOtpState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STOtpState.Loading)
    }

}
