package com.steppi.steppifitness.ui.rewards.mvi

import android.app.Application
import android.util.Log
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.reward.STRedeemRewardRequest
import com.steppi.steppifitness.network.response.category.STMerchantStoresListData
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class STRewardsController : MviBaseController<STRewardsState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STRewardsState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STRewardsIntent.GetMerchantList -> getMerchantList(
                    application,
                    incomingIntent.categoryId,
                    incomingIntent.latitude,
                    incomingIntent.longitude,
                    incomingIntent.offset
                )
//                is STRewardsIntent.GetSearchMerchantList -> getSearchMerchantsList(
//                    application,
//                    incomingIntent.categoryId,
//                    incomingIntent.latitude,
//                    incomingIntent.longitude,
//                    /*incomingIntent.offset,*/
//                    incomingIntent.query
//                )
                is STRewardsIntent.GetStoresRewardList -> getStoresRewardList(
                    application,
                    incomingIntent.merchantId,
                    incomingIntent.storeId,
                    incomingIntent.offset
                )
                is STRewardsIntent.GetMerchantsStoresList -> getMerchantStoresList(
                    application,
                    incomingIntent.merchantId,
                    incomingIntent.latitude,
                    incomingIntent.longitude,
                    incomingIntent.rewardId,
                    incomingIntent.query
                )

                is STRewardsIntent.GetInStoreRewardDetails -> getInStoreRewardDetails(
                    application,
                    incomingIntent.rewardId,
                    incomingIntent.storeId
                )

                is STRewardsIntent.GetDigitalRewardDetails -> getDigitalRewardDetails(
                    application,
                    incomingIntent.rewardId
                )
                is STRewardsIntent.RedeemReward -> redeemReward(
                    application,
                    incomingIntent.rewardId,
                    incomingIntent.redeemRewardRequest
                )
                is STRewardsIntent.MerchantAddRemoveFavorite -> merchantAddRemoveFavorite(
                    application,
                    incomingIntent.merchantId
                )
                is STRewardsIntent.SearchStoreLocationIntent -> searchStoreLocation(
                    incomingIntent.countryList,
                    incomingIntent.searchKey
                )
                else -> null
            }
        }

    private fun getMerchantList(
        application: Application,
        categoryId: String,
        latitude: String,
        longitude: String,
        offset: Int = 0
    ): Observable<STRewardsState> {
        return STRetrofitClient.create(application).getMerchantsList(
            categoryId,
            latitude,
            longitude,
            offset.toString()
        )
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getMerchantsList(
                        categoryId,
                        latitude,
                        longitude,
                        offset.toString()
                    )
                )
            }
            .map {
                STRewardsState.GetMerchantList(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STRewardsState.Loading)
    }

//    private fun getSearchMerchantsList(
//        application: Application,
//        categoryId: String,
//        latitude: String,
//        longitude: String,
//        /*offset: Int = 0,*/
//        query: String
//    ): Observable<STRewardsState> {
//        return STRetrofitClient.create(application).getSearchMerchantsList(
//            categoryId,
//            latitude,
//            longitude,
//            /*offset.toString(),*/
//            query
//        )
//            .doOnError { }
//            .retryWhen { errors ->
//                retry(
//                    application,
//                    errors,
//                    STRetrofitClient.create(application).getSearchMerchantsList(
//                        categoryId,
//                        latitude,
//                        longitude,
//                        /*offset.toString(),*/
//                        query
//                    )
//                )
//            }
//            .map {
//                STRewardsState.GetMerchantList(it)
//            }
//            .cast(STRewardsState::class.java)
//            .onErrorReturn {
//                val errorDate = STErrorData()
//                if (STUtils.isInternetOn(application)) {
//                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
//                } else {
//                    errorDate.message = application.resources.getString(R.string.no_internet)
//                    STRewardsState.Error(errorDate)
//                }
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//    }

    private fun getStoresRewardList(
        application: Application,
        merchantId: String,
        storeId: String?,
        offset: Int
    ): Observable<STRewardsState> {
        return if (!storeId.isNullOrBlank()) {
            STRetrofitClient.create(application)
                .getStoresRewardList(merchantId, storeId, offset.toString())
        } else {
            STRetrofitClient.create(application).getDigitalRewardList(merchantId, offset.toString())
        }
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    if (!storeId.isNullOrBlank()) {
                        STRetrofitClient.create(application)
                            .getStoresRewardList(merchantId, storeId, offset.toString())
                    } else {
                        STRetrofitClient.create(application)
                            .getDigitalRewardList(merchantId, offset.toString())
                    }
                    //STRetrofitClient.create(application).getStoresRewardList(merchantId, storeId)
                )
            }
            .map {
                STRewardsState.GetStoresRewardList(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STRewardsState.Loading)
    }

    private fun getMerchantStoresList(
        application: Application,
        merchantId: String,
        latitude: String,
        longitude: String,
        rewardId: String,
        query: String
    ): Observable<STRewardsState> {

        return if (rewardId.isNullOrEmpty()) {
            STRetrofitClient.create(application)
                .getMerchantStoreList(merchantId, latitude, longitude, query)
        } else {
            STRetrofitClient.create(application)
                .getMerchantStoreListForReward(merchantId, rewardId, latitude, longitude, query)
        }

            /*return STRetrofitClient.create(application)
                .getMerchantStoreList(merchantId, latitude, longitude, query)*/
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getMerchantStoreList(
                        merchantId,
                        latitude,
                        longitude
                    )
                )
            }
            .map {
                STRewardsState.GetMerchantStoresList(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STRewardsState.Loading)
    }

    private fun merchantAddRemoveFavorite(
        application: Application,
        merchantId: String
    ): Observable<STRewardsState> {
        return STRetrofitClient.create(application)
            .merchantAddRemoveFavorite(merchantId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).merchantAddRemoveFavorite(merchantId)
                )
            }
            .map {
                STRewardsState.MerchantAddRemoveFavorite(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STRewardsState.Loading)
    }

    private fun getInStoreRewardDetails(
        application: Application,
        rewardId: String?,
        storeId: String
    ): Observable<STRewardsState> {
        return STRetrofitClient.create(application)
            .getInStoreRewardDetails(rewardId, storeId)
            .doOnError { error ->
                //                Log.e("error", error.message)
            }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getInStoreRewardDetails(rewardId, storeId)
                )
            }
            .map {
                STRewardsState.GetInStoreRewardDetails(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STRewardsState.Loading)
    }

    private fun getDigitalRewardDetails(
        application: Application,
        rewardId: String?
    ): Observable<STRewardsState> {
        return STRetrofitClient.create(application)
            .getDigitalRewardDetails(rewardId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getDigitalRewardDetails(rewardId)
                )
            }
            .map {
                STRewardsState.GetDigitalRewardDetails(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STRewardsState.Loading)
    }


    private fun redeemReward(
        application: Application,
        rewardId: String?,
        redeemRewardRequest: STRedeemRewardRequest
    ): Observable<STRewardsState> {
        return STRetrofitClient.create(application).redeemReward(rewardId, redeemRewardRequest)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).redeemReward(rewardId, redeemRewardRequest)
                )
            }
            .map {
                STRewardsState.RedeemReward(it)
            }
            .cast(STRewardsState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STRewardsState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STRewardsState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STRewardsState.Loading)
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


    private fun searchStoreLocation(
        countryList: List<STMerchantStoresListData>?,
        searchKey: String
    ): Observable<STRewardsState> =
        Single.create(SingleOnSubscribe<STRewardsState.SearchStoreLocationState> { emitter ->
            /*
            * Filter arraylist using kotlin filter method
            * */
            val result = countryList?.filter {
                it.name?.toLowerCase(Locale.getDefault())?.contains(searchKey.toLowerCase())
                    ?: false
            }
            result?.let {
                emitter.onSuccess(STRewardsState.SearchStoreLocationState(it))
            }
        })
            .cast(STRewardsState::class.java)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .toObservable()
}
