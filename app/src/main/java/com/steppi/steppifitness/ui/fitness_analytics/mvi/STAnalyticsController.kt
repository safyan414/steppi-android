package com.steppi.steppifitness.ui.fitness_analytics.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class STAnalyticsController : MviBaseController<STAnalyticsState>() {
    override fun execute(
        intent: MviIntent,
        application: Application
    ): Observable<STAnalyticsState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STAnalyticsIntent.GetAnalyticsData -> getAnalyticsList(
                    application,
                    incomingIntent.date
                )
                else -> null
            }
        }

    private fun getAnalyticsList(
        application: Application,
        date: String
    ): Observable<STAnalyticsState> {
        return STRetrofitClient.create(application).getAnalyticsData(date)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getAnalyticsData(date)
                )
            }
            .map {
                STAnalyticsState.AnalyticsList(it)
            }
            .cast(STAnalyticsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STAnalyticsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STAnalyticsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STAnalyticsState.Loading)
    }

    private fun retry(
        application: Application,
        errors: Observable<Throwable>,
        currentApiObservable: Observable<*>
    ): Observable<*> {
        return errors.flatMap {
            var errorData: STErrorData? = STErrorData()
            if (STUtils.isInternetOn(application)) {
                errorData = STUtils.getErrorMessage(application, it)
            } else {
                errorData?.message = application.resources.getString(R.string.no_internet)
            }

            when (errorData?.statusCode) {
                STAPIConstants.STATUS_CODE_TOKEN_EXPIRED -> STRetrofitClient.create(application).refereshToken()
                //STAPIConstants.STATUS_CODE_SESSION_EXPIRED, STAPIConstants.STATUS_CODE_INVALID_SESSION -> STRetrofitClient.create(application).refereshToken()
                else -> currentApiObservable
            }
        }.flatMap {
            currentApiObservable
        }
    }
}
