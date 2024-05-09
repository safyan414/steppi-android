package com.steppi.steppifitness.ui.profile.corporate.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.corporate.STJoinCorporateUserRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class STCorporateController : MviBaseController<STCorporateState>() {
    override fun execute(
        intent: MviIntent,
        application: Application
    ): Observable<STCorporateState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STCorporateIntent.LeaveCorporation -> leaveCorporation(
                    application
                )
                is STCorporateIntent.JoinCorporation -> joinCorporation(
                    application,
                    incomingIntent.joinCorporationRequest
                )
                is STCorporateIntent.GetCorporateUser -> getCorporateUser(
                    application
                )
                is STCorporateIntent.ResendCorporateEmail -> resendCorporateEmail(
                    application
                )
                is STCorporateIntent.TimerIntent -> performTimer()
                else -> null
            }
        }

    private fun getCorporateUser(
        application: Application
    ): Observable<STCorporateState> {
        return STRetrofitClient.create(application).getCorporateUser()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getCorporateUser()
                )
            }
            .map {
                STCorporateState.GetCorporateUser(it)
            }
            .cast(STCorporateState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STCorporateState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STCorporateState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STCorporateState.Loading)
    }

    private fun joinCorporation(
        application: Application,
        leaveCorporationRequest: STJoinCorporateUserRequest
    ): Observable<STCorporateState> {
        return STRetrofitClient.create(application).joinCorporation(leaveCorporationRequest)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).joinCorporation(leaveCorporationRequest)
                )
            }
            .map {
                STCorporateState.JoinCorporation(it)
            }
            .cast(STCorporateState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STCorporateState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STCorporateState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STCorporateState.Loading)
    }

    private fun leaveCorporation(
        application: Application
    ): Observable<STCorporateState> {
        return STRetrofitClient.create(application).leaveCorporation()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).leaveCorporation()
                )
            }
            .map {
                STCorporateState.LeaveCorporation(it)
            }
            .cast(STCorporateState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STCorporateState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STCorporateState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STCorporateState.Loading)
    }

    private fun resendCorporateEmail(application: Application): Observable<STCorporateState> {
        return STRetrofitClient.create(application).resendCorporateEmail()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).resendCorporateEmail()
                )
            }
            .map {
                STCorporateState.ResendCorporateEmail(it)
            }
            .cast(STCorporateState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STCorporateState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STCorporateState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STCorporateState.Loading)
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

    private fun performTimer(): Observable<STCorporateState>? =
        Observable.just(STCorporateState.TimerState).delay(1000, TimeUnit.MILLISECONDS).cast(
            STCorporateState::class.java
        )
}
