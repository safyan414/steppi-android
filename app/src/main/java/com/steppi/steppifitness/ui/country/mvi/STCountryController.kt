package com.steppi.steppifitness.ui.country.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class STCountryController : MviBaseController<STCountryState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STCountryState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STCountryIntent.GetCountries -> getCountries(application)
                is STCountryIntent.SearchCountryIntent -> searchCountry(
                    incomingIntent.countryList,
                    incomingIntent.searchKey
                )
                else -> null
            }
        }

    private fun getCountries(
        application: Application

    ): Observable<STCountryState> {
        return STRetrofitClient.create(application).countries()
            .doOnError { }
            .map { STCountryState.GetCountries(it) }
            .cast(STCountryState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STCountryState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STCountryState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STCountryState.Loading)
    }

    private fun searchCountry(
        countryList: List<STCountryData>?,
        searchKey: String
    ): Observable<STCountryState> =
        Single.create(SingleOnSubscribe<STCountryState.SearchCountryState> { emitter ->

            /*
            * Filter arraylist using kotlin filter method
            * */
            val result = countryList?.filter {
                it.name?.toLowerCase(Locale.getDefault())?.contains(searchKey.toLowerCase()) ?: false || it.phoneCode?.toLowerCase(
                    Locale.getDefault()
                )?.contains(searchKey.toLowerCase()) ?: false
            }
            result?.let {
                emitter.onSuccess(STCountryState.SearchCountryState(it))
            }


        })
            .cast(STCountryState::class.java)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .toObservable()

}
