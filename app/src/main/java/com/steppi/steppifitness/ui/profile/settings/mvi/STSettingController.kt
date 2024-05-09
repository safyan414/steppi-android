package com.steppi.steppifitness.ui.profile.settings.mvi

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

class STSettingController : MviBaseController<STSettingState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STSettingState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STSettingIntent.GetNotificationList -> {
                    getNotificationList(application, incomingIntent.offset)
                }
                is STSettingIntent.MarkAllRead -> {
                    markAllRead(application)
                }
                is STSettingIntent.EnableFingerprint -> {
                    getUserEnableFingerprint(application)
                }
                is STSettingIntent.ChangeDefaultLanguage -> changeDefaultLanguage(application)
                else -> null
            }
        }

    private fun getNotificationList(
        application: Application, offset: Int
    ): Observable<STSettingState> {
        return STRetrofitClient.create(application).getNotificationList(offset.toString())
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getNotificationList(offset.toString())
                )
            }
            .map { STSettingState.GetNotificationList(it) }
            .cast(STSettingState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STSettingState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STSettingState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STSettingState.Loading)
    }

    private fun markAllRead(application: Application): Observable<STSettingState> {
        return STRetrofitClient.create(application).mallAllRead()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).mallAllRead()
                )
            }
            .map { STSettingState.MarkAllRead(it) }
            .cast(STSettingState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STSettingState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STSettingState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
        //.startWith(STSettingState.Loading)
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
                STAPIConstants.STATUS_CODE_TOKEN_EXPIRED -> STRetrofitClient.create(application)
                    .refereshToken()
                //STAPIConstants.STATUS_CODE_SESSION_EXPIRED, STAPIConstants.STATUS_CODE_INVALID_SESSION -> STRetrofitClient.create(application).refereshToken()
                else -> currentApiObservable
            }
        }.flatMap {
            currentApiObservable
        }
    }

    private fun getUserEnableFingerprint(
        application: Application
    ): Observable<STSettingState> {
        return STRetrofitClient.create(application).getUserEnableFingerprint()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getUserEnableFingerprint()
                )
            }
            .map { STSettingState.EnableFingerprint(it) }
            .cast(STSettingState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STSettingState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STSettingState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STSettingState.Loading)
    }

    private fun changeDefaultLanguage(application: Application): Observable<STSettingState> {
        return STRetrofitClient.create(application).changeDefaultLanguage()
            .doOnError { }
            .map { STSettingState.ChangeDefaultLanguage(it) }
            .cast(STSettingState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STSettingState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STSettingState.Error(errorData)
                }
            }
            .subscribeOn(Schedulers.io())
            .startWith(STSettingState.Loading)
    }
}
