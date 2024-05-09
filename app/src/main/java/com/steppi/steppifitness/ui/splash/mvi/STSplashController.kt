package com.steppi.steppifitness.ui.splash.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class STSplashController : MviBaseController<STSplashState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STSplashState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STSplashIntent.VersionCheck -> versionCheck(
                    application,
                    incomingIntent.appVersion,
                    incomingIntent.deviceType
                )
                is STSplashIntent.TimerIntent -> performTimer()
                else -> null
            }
        }

    private fun versionCheck(
        application: Application,
        appVersion: String?,
        deviceType: String?
    ): Observable<STSplashState> {
        return STRetrofitClient.create(application).versionCheck(/*appVersion, deviceType*/)
            .doOnError {}
            .map { STSplashState.VersionCheck(it) }
            .cast(STSplashState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STSplashState.Error(error)
                } else {
                    errorData?.message = application.resources.getString(R.string.no_internet)
                    STSplashState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STSplashState.Loading)
    }

    private fun performTimer(): Observable<STSplashState>? =
        Observable.just(STSplashState.TimerState).delay(1000, TimeUnit.MILLISECONDS).cast(
            STSplashState::class.java
        )


}
