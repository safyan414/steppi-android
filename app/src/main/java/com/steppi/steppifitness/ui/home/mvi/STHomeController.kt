package com.steppi.steppifitness.ui.home.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.network.request.home.STSyncFitnessDataRequest
import com.steppi.steppifitness.network.request.home.STWhatsNewVersionUpdateRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class STHomeController : MviBaseController<STHomeState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STHomeState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STHomeIntent.GetCategory -> getCategory(application)
                is STHomeIntent.GetMerchantList -> getMerchantList(
                    application,
                    incomingIntent.categoryId,
                    incomingIntent.latitude,
                    incomingIntent.longitude
                )
                is STHomeIntent.GetHomeScreenFeaturedRewards -> getHomeScreenFeaturedRewards(
                    application
                )
                is STHomeIntent.GetFitnessData -> syncFitness(
                    application, incomingIntent.syncFitnessDataRequest
                )
                is STHomeIntent.GetUnreadNotificationCount -> getUnreadNotificationCount(application)
                is STHomeIntent.DeviceTokenUpdate -> deviceTokenUpdate(
                    application,
                    incomingIntent.deviceTokenUpdate
                )
                is STHomeIntent.MarkHomeNotificationRead -> {
                    markHomeNotificationRead(application)
                }
                is STHomeIntent.WhatsNewOnBoardingScreen -> {
                    whatsNewOnBoardingScreen(application, incomingIntent.version)
                }
                is STHomeIntent.WhatsNewVersionUpdate -> {
                    whatsNewVersionUpdate(application, incomingIntent.whatsNewVersionUpdateRequest)
                }
                else -> null
            }
        }

    private fun deviceTokenUpdate(
        application: Application,
        deviceTokenRequest: STBaseRequest

    ): Observable<STHomeState> {
        return STRetrofitClient.create(application).deviceTokenUpdate(deviceTokenRequest)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getUnreadNotificationCount()
                )
            }
            .map { STHomeState.DeviceTokenUpdateResponse(it) }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STHomeState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    private fun getCategory(
        application: Application/*,
        offset: String*/
    ): Observable<STHomeState> {
        return STRetrofitClient.create(application).getCategory(/*offset*/)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getCategory(/*offset*/)
                )
            }
            .map {
                STHomeState.GetCategory(it)
            }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STHomeState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STHomeState.Loading)
    }

    private fun getMerchantList(
        application: Application,
        categoryId: String,
        latitude: String,
        longitude: String
    ): Observable<STHomeState> {
        return STRetrofitClient.create(application).getMerchantsList(
            categoryId,
            latitude,
            longitude
        )
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getMerchantsList(
                        categoryId,
                        latitude,
                        longitude
                    )
                )
            }
            .map {
                STHomeState.GetMerchantList(it)
            }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STHomeState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STHomeState.Loading)
    }

    private fun getHomeScreenFeaturedRewards(
        application: Application
    ): Observable<STHomeState> {
        return STRetrofitClient.create(application).getHomeScreenFeaturedRewards()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getHomeScreenFeaturedRewards()
                )
            }
            .map { STHomeState.GetHomeScreenFeaturedRewards(it) }
            .cast(STHomeState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STHomeState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STHomeState.Loading)
    }


    private fun syncFitness(
        application: Application,
        syncFitnessDataRequest: STSyncFitnessDataRequest
    ): Observable<STHomeState> {
        return STRetrofitClient.create(application).syncFitnessData(syncFitnessDataRequest)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).syncFitnessData(syncFitnessDataRequest)
                )
            }
            .map {
                STHomeState.SyncFitnessData(it)
            }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STHomeState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STHomeState.Loading)
    }

    private fun getUnreadNotificationCount(application: Application): Observable<STHomeState> {
        return STRetrofitClient.create(application).getUnreadNotificationCount()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getUnreadNotificationCount()
                )
            }
            .map { STHomeState.GetUnreadNotificationCount(it) }
            .cast(STHomeState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STHomeState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
        //.startWith(STHomeState.Loading)
    }

    private fun markHomeNotificationRead(application: Application): Observable<STHomeState> {
        return STRetrofitClient.create(application).mallHomeNotificationRead()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).mallHomeNotificationRead()
                )
            }
            .map { STHomeState.MarkHomeNotificationRead(it) }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STHomeState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
        //.startWith(STHomeState.Loading)
    }

    private fun whatsNewOnBoardingScreen(application: Application,  version: String): Observable<STHomeState> {
        return STRetrofitClient.create(application).whatsNewOnBoardingScreen(version)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).whatsNewOnBoardingScreen(version)
                )
            }
            .map { STHomeState.WhatsNewOnBoardingScreen(it) }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STHomeState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STHomeState.Loading)
    }

    private fun whatsNewVersionUpdate(
        application: Application,
        whatsNewVersionUpdateRequest: STWhatsNewVersionUpdateRequest
    ): Observable<STHomeState> {
        return STRetrofitClient.create(application)
            .whatsNewVersionUpdate(whatsNewVersionUpdateRequest)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .whatsNewVersionUpdate(whatsNewVersionUpdateRequest)
                )
            }
            .map { STHomeState.WhatsNewVersionUpdate(it) }
            .cast(STHomeState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STHomeState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STHomeState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STHomeState.Loading)
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

}