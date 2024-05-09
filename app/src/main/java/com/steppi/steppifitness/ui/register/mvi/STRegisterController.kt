package com.steppi.steppifitness.ui.register.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.login.STRegisterRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class STRegisterController : MviBaseController<STRegisterState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STRegisterState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STRegisterIntent.Register -> register(
                    application,
                    incomingIntent.registerRequest
                    //incomingIntent.file,
                    //incomingIntent.map
                )
                is STRegisterIntent.GetCountries -> getCountries(application)
                else -> null
            }
        }

    private fun register(
        application: Application,
        registerRequest: STRegisterRequest
        //file: MultipartBody.Part?,
        //map: HashMap<String, RequestBody>

    ): Observable<STRegisterState> {
        return STRetrofitClient.create(application).register(registerRequest)
            .doOnError { }
            .map { STRegisterState.Register(it) }
            .cast(STRegisterState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STRegisterState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STRegisterState.Error(errorData)
                }
            }
            .subscribeOn(Schedulers.io())
            .startWith(STRegisterState.Loading)
    }

    private fun getCountries(
        application: Application

    ): Observable<STRegisterState> {
        return STRetrofitClient.create(application).countries()
            .doOnError { }
            .map { STRegisterState.GetCountries(it) }
            .cast(STRegisterState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STRegisterState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STRegisterState.Error(errorData)
                }
            }
            .subscribeOn(Schedulers.io())
    }
}
