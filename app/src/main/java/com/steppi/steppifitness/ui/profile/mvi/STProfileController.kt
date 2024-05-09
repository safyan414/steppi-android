package com.steppi.steppifitness.ui.profile.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.otp.STValidateOtpRequest
import com.steppi.steppifitness.network.request.password.STChangePasswordRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

class STProfileController : MviBaseController<STProfileState>() {
    override fun execute(intent: MviIntent, application: Application): Observable<STProfileState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STProfileIntent.GetProfile -> getProfile(
                    application
                )
                is STProfileIntent.UpdateProfile -> updateProfile(
                    application,
                    incomingIntent.updateProfileRequest
                )
                is STProfileIntent.GetCountries -> getCountries(
                    application
                )
                is STProfileIntent.UpdateProfilePic -> updateProfilePic(
                    application,
                    incomingIntent.picture
                )
                is STProfileIntent.UpdateMobile -> updateMobile(
                    application,
                    incomingIntent.updateMobileRequest
                )
                is STProfileIntent.ValidateOtp -> verifyMobile(
                    application,
                    incomingIntent.validateOtpRequest
                )
                is STProfileIntent.ChangePassword -> changePassword(
                    application,
                    incomingIntent.changePasswordRequest
                )
                is STProfileIntent.ResendOtp -> resendOtp(
                    application,
                    incomingIntent.resendOtpRequest
                )
                is STProfileIntent.GetRedeemedRewardsList -> getRedeemedRewardDetails(
                    application,
                    incomingIntent.rewardType,
                    incomingIntent.pageOffset
                )
                is STProfileIntent.UserLogout -> logoutUser(
                    application
                )
                else -> null
            }
        }

    private fun logoutUser(
        application: Application
    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).userLogout()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).userLogout()
                )
            }
            .map {
                STProfileState.LogoutUser(it)
            }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STProfileState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun getProfile(
        application: Application

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).getProfile()
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getProfile()
                )
            }
            .map {
                STProfileState.GetProfile(it)
            }
            .cast(STProfileState::class.java)

            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }

            }

            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun updateProfile(
        application: Application,
        updateProfileRequest: STUser

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application)
            .updateProfile(updateProfileRequest.id, updateProfileRequest)
            .doOnError {}
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).updateProfile(
                        updateProfileRequest.id,
                        updateProfileRequest
                    )
                )
            }
            .map { STProfileState.UpdateProfile(it) }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun updateProfilePic(
        application: Application,
        file: MultipartBody.Part?

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).updateProfilePic(file)
            .doOnError {}
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).updateProfilePic(file)
                )
            }
            .map { STProfileState.UpdateProfilePic(it) }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun updateMobile(
        application: Application,
        updateMobileRequest: STUser

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).updateMobile(updateMobileRequest)
            .doOnError {}
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).updateMobile(updateMobileRequest)
                )
            }
            .map { STProfileState.UpdateMobile(it) }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun verifyMobile(
        application: Application,
        verifyMobileRequest: STValidateOtpRequest

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).verifyPhoneNumberChange(verifyMobileRequest)
            .doOnError {}
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .verifyPhoneNumberChange(verifyMobileRequest)
                )
            }
            .map { STProfileState.ValidateOtp(it) }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun changePassword(
        application: Application,
        changePasswordRequest: STChangePasswordRequest

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).changePassword(changePasswordRequest)
            .doOnError {}
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).changePassword(changePasswordRequest)
                )
            }
            .map { STProfileState.ChangePassword(it) }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun getCountries(
        application: Application

    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).countries()
            .doOnError { }
            .map { STProfileState.GetCountries(it) }
            .cast(STProfileState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .subscribeOn(Schedulers.io())
    }

    private fun resendOtp(
        application: Application,
        resendOtpRequest: STValidateOtpRequest
    ): Observable<STProfileState> {
        return STRetrofitClient.create(application).phoneNumberChangeResendOtp(resendOtpRequest)
            .doOnError {}
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .phoneNumberChangeResendOtp(resendOtpRequest)
                )
            }
            .map { STProfileState.ResendOtp(it) }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STProfileState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
    }

    private fun getRedeemedRewardDetails(
        application: Application,
        rewardType: String?,
        pageOffset: Int
    ): Observable<STProfileState> {
        return STRetrofitClient.create(application)
            .getRedeemedRewards(rewardType, pageOffset.toString())
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getRedeemedRewards(rewardType, pageOffset.toString())
                )
            }
            .map {
                STProfileState.GetRedeemedRewardList(it)
            }
            .cast(STProfileState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STProfileState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STProfileState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STProfileState.Loading)
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
                // STAPIConstants.STATUS_CODE_SESSION_EXPIRED, STAPIConstants.STATUS_CODE_INVALID_SESSION ->  STRetrofitClient.create(application).refereshToken()
                else -> currentApiObservable
            }

        }.flatMap {
            currentApiObservable
        }
    }
}
