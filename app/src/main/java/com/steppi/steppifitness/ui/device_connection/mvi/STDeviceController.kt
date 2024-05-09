package com.steppi.steppifitness.ui.device_connection.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class STDeviceController : MviBaseController<STDeviceState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STDeviceState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STDeviceIntent.GetFitnessDevices -> getDevices(
                    application,
                    incomingIntent.includeGarmin
                )
                is STDeviceIntent.SelectFitnessDevice -> selectFitnessDevice(
                    application,
                    incomingIntent.deviceId,
                    incomingIntent.accountId,
                    incomingIntent.garminAccessToken,
                    incomingIntent.garminTokenSecret
                )
                else -> null
            }
        }

    private fun getDevices(
        application: Application,
        includeGarmin: Boolean
    ): Observable<STDeviceState> {
        return STRetrofitClient.create(application).getFitnessDevices(STConstants.DEVICE_TYPE, includeGarmin)
            .doOnError {}
            .map { STDeviceState.GetFitnessDevices(it) }
            .cast(STDeviceState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STDeviceState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STDeviceState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STDeviceState.Loading)
    }

    private fun selectFitnessDevice(
        application: Application,
        deviceId: String?,
        accountId: String?,
        garminAccessToken: String?,
        garminTokenSecret: String?
    ): Observable<STDeviceState> {
        return STRetrofitClient.create(application)
            .addFitnessDevice(deviceId, accountId, garminAccessToken, garminTokenSecret)
            .doOnError {}
            .map { STDeviceState.SelectFitnessDevice(it) }
            .cast(STDeviceState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STDeviceState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STDeviceState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STDeviceState.Loading)
    }

}
