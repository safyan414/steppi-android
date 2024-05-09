package com.steppi.steppifitness.ui.challenges.mvi

import android.app.Application
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.STToughMudderChallengeRequest
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class STChallengesController : MviBaseController<STChallengesState>() {
    override fun execute(
        intent: MviIntent,
        application: Application
    ): Observable<STChallengesState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STChallengesIntent.GetSteppiChallengesList -> getSteppiChallengeList(
                    application, incomingIntent.type,
                    incomingIntent.offset
                )
                is STChallengesIntent.ToughMudderChallengeList -> toughMudderChallengeList(
                    application
                )
                is STChallengesIntent.GetMyChallenges -> getMyChallenges(
                    application,
                    incomingIntent.offset
                )
                is STChallengesIntent.GetChallengeDetails -> getChallengeDetails(
                    application,
                    incomingIntent.challengeId
                )

                is STChallengesIntent.GetCorporateChallengeDetails -> getCorporateChallengeDetails(
                    application,
                    incomingIntent.challengeId
                )

                is STChallengesIntent.JoinLeaveChallenge -> joinLeaveChallenge(
                    application,
                    incomingIntent.operationType,
                    incomingIntent.challengeId
                )
                is STChallengesIntent.JoinLeavePrivateChallenge -> joinLeavePrivateChallenge(
                    application,
                    incomingIntent.operationType,
                    incomingIntent.challengeId,
                    incomingIntent.joinCode
                )
                is STChallengesIntent.GetViewAllLeaderboardData -> getViewAllLeaderboardData(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.offset
                )
                is STChallengesIntent.CheerChallengeUser -> cheerChallengeUser(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.challengeUserId
                )
                is STChallengesIntent.GetPrivateChallengeList -> getPrivateChallengeList(
                    application,
                    incomingIntent.offset.toString()
                )
                is STChallengesIntent.FindChallenge -> findChallenge(
                    application,
                    incomingIntent.joinCode
                )
                is STChallengesIntent.CreatePrivateChallenge -> createPrivateChallenge(
                    application,
                    incomingIntent.privateChallengeTemplateId,
                    incomingIntent.currentDate,
                    incomingIntent.theme
                )
                is STChallengesIntent.GetMyChallengesByType -> getMyChallengeByType(
                    application,
                    incomingIntent.offset.toString(),
                    incomingIntent.listType
                )
                is STChallengesIntent.GetPrivateChallengeDetails -> getPrivateChallengeDetails(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.joinCode
                )
                is STChallengesIntent.JoinPublicChallengeTeam -> joinPublicTeamChallenge(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.challengeTeamId,
                    incomingIntent.publicJoinCode
                )
                is STChallengesIntent.GetDailyStepOfUser -> getDailySteps(
                    application,
                    incomingIntent.challengeId/*,
                    incomingIntent.offset.toString()*/
                )
                is STChallengesIntent.GetChallengeTeamDetails -> getTeamDetails(
                    application,
                    incomingIntent.teamId
                )
                is STChallengesIntent.GetChallengeTeamLeaderBoardDetails -> getTeamLeaderBoardDetails(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.teamId,
                    incomingIntent.offset.toString()
                )
                is STChallengesIntent.GetTeamLeaderBoardAnimation -> getTeamLeaderBoardAnimation(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.offset.toString()
                )
                is STChallengesIntent.RemoveChallenge -> removeCompletedChallenge(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.position
                )
                is STChallengesIntent.JoinToughMudderChallenge -> joinToughMudderChallenge(
                    application,
                    incomingIntent.operationType,
                    incomingIntent.id
                )
                is STChallengesIntent.GetToughMudderChallengeDetails -> getToughMudderChallengeDetails(
                    application,
                    incomingIntent.subChallengeId
                )
                is STChallengesIntent.StartToughMudderChallenge -> startToughMudderChallenge(
                    application,
                    incomingIntent.subChallengeId
                )
                is STChallengesIntent.CompleteToughMudderChallenge -> completeToughMudderChallenge(
                    application,
                    incomingIntent.toughMudderChallengeRequest
                )
                is STChallengesIntent.JoinLeaveDFCChallenge -> joinLeaveDfcChallenge(
                    application,
                    incomingIntent.operationType,
                    incomingIntent.challengeId,
                    incomingIntent.joinCode
                )
                else -> null
            }
        }

    private fun getSteppiChallengeList(
        application: Application,
        type: String,
        offset: Int
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application).getSteppiChallengeList(type, offset.toString())
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getSteppiChallengeList(type, offset.toString())
                )
            }
            .map { STChallengesState.GetSteppiChallengeList(it) }
            .cast(STChallengesState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STChallengesState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun toughMudderChallengeList(application: Application): Observable<STChallengesState> {
        return STRetrofitClient.create(application).toughMudderChallengeList()
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).toughMudderChallengeList()
                )
            }
            .map { STChallengesState.GetSteppiChallengeList(it) }
            .cast(STChallengesState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STChallengesState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getMyChallenges(
        application: Application,
        offset: Int
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application).getMyChallenges(offset.toString())
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getMyChallenges(offset.toString())
                )
            }
            .map { STChallengesState.GetMyChallenges(it) }
            .cast(STChallengesState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STChallengesState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getCorporateChallengeDetails(
        application: Application,
        challengeId: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getChallengeDetails(challengeId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getCorporateChallengeDetails(challengeId)
                )
            }
            .map {
                STChallengesState.GetCorporateChallengeDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getChallengeDetails(
        application: Application,
        challengeId: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getChallengeDetails(challengeId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getChallengeDetails(challengeId)
                )
            }
            .map {
                STChallengesState.GetChallengeDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getPrivateChallengeDetails(
        application: Application,
        challengeId: String?,
        joinCode: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getPrivateChallengeDetails(challengeId, joinCode)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getPrivateChallengeDetails(
                        challengeId,
                        joinCode
                    )
                )
            }
            .map {
                STChallengesState.GetPrivateChallengeDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun joinPublicTeamChallenge(
        application: Application,
        challengeId: String,
        challengeTeamId: String,
        publicJoinCode: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .joinTeamPublicChallenge(challengeId, challengeTeamId, publicJoinCode)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).joinTeamPublicChallenge(
                        challengeId,
                        challengeTeamId,
                        publicJoinCode
                    )
                )
            }
            .map {
                STChallengesState.JoinPublicTeamChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getDailySteps(
        application: Application,
        challengeId: String/*,
        offset: String*/
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getDailyStepsOfUser(challengeId/*, offset*/)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getDailyStepsOfUser(
                        challengeId/*,
                        offset*/
                    )
                )
            }
            .map {
                STChallengesState.GetDailyStepOfUser(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getTeamDetails(
        application: Application,
        teamId: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getTeamDetails(teamId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).getTeamDetails(teamId)
                )
            }
            .map {
                STChallengesState.GetTeamDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getTeamLeaderBoardDetails(
        application: Application,
        challengeId: String,
        teamId: String,
        offset: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getTeamLeaderBoardDetails(challengeId, teamId, offset)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getTeamLeaderBoardDetails(challengeId, teamId, offset)
                )
            }
            .map {
                STChallengesState.GetTeamLeaderBoardDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getTeamLeaderBoardAnimation(
        application: Application,
        challengeId: String,
        offset: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getTeamLeaderBoardAnimation(challengeId, offset)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getTeamLeaderBoardAnimation(challengeId, offset)
                )
            }
            .map {
                STChallengesState.GetTeamLeaderBoardAnimation(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }


    private fun removeCompletedChallenge(
        application: Application,
        challengeId: String,
        position: Int
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .removeCompletedChallenge(challengeId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .removeCompletedChallenge(challengeId)
                )
            }
            .map {
                STChallengesState.RemoveChallenge(it, position)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun joinLeaveChallenge(
        application: Application,
        operationType: String,
        challengeId: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .joinLeaveChallenge(operationType, challengeId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .joinLeaveChallenge(operationType, challengeId)
                )
            }
            .map {
                STChallengesState.JoinLeaveChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun joinLeaveDfcChallenge(
        application: Application,
        operationType: String,
        challengeId: String?,
        joinCode: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .joinLeaveDfcChallenge(operationType, challengeId, joinCode)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .joinLeaveDfcChallenge(operationType, challengeId, joinCode)
                )
            }
            .map {
                STChallengesState.JoinLeaveChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun joinToughMudderChallenge(
        application: Application,
        operationType: String,
        id: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application).joinToughMudderChallenge(operationType, id)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .joinToughMudderChallenge(operationType, id)
                )
            }
            .map {
                STChallengesState.JoinLeaveChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getToughMudderChallengeDetails(
        application: Application,
        subChallengeId: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application).getToughMudderChallengeDetails(subChallengeId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getToughMudderChallengeDetails(subChallengeId)
                )
            }
            .map {
                STChallengesState.GetChallengeDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun startToughMudderChallenge(
        application: Application,
        subChallengeId: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application).startToughMudderChallenge(subChallengeId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application).startToughMudderChallenge(subChallengeId)
                )
            }
            .map {
                STChallengesState.GetChallengeDetails(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun completeToughMudderChallenge(
        application: Application,
        toughMudderChallengeRequest: STToughMudderChallengeRequest
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .completeToughMudderChallenge(toughMudderChallengeRequest)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .completeToughMudderChallenge(toughMudderChallengeRequest)
                )
            }
            .map {
                STChallengesState.CompleteToughMudderChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun joinLeavePrivateChallenge(
        application: Application,
        operationType: String?,
        challengeId: String?,
        joinCode: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .joinLeavePrivateChallenge(operationType, challengeId, joinCode)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .joinLeavePrivateChallenge(operationType, challengeId, joinCode)
                )
            }
            .map {
                STChallengesState.JoinLeavePrivateChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getViewAllLeaderboardData(
        application: Application,
        challengeId: String?,
        offset: Int
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getChallengeLeaderboard(challengeId, offset.toString())
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getChallengeLeaderboard(challengeId, offset.toString())
                )
            }
            .map {
                STChallengesState.GetViewAllLeaderboardData(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun cheerChallengeUser(
        application: Application,
        challengeId: String?,
        challengeUserId: String?
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .cheerChallengeUser(challengeId, challengeUserId)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .cheerChallengeUser(challengeId, challengeUserId)
                )
            }
            .map {
                STChallengesState.CheerChallengeUser(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STChallengesState.Loading)
    }

    private fun getPrivateChallengeList(
        application: Application, offset: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getPrivateChallengeTemplatesList(offset)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getPrivateChallengeTemplatesList(offset)
                )
            }
            .map {
                STChallengesState.GetPrivateChallengeTemplatesList(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun createPrivateChallenge(
        application: Application, templateId: String,
        currentDate: RequestBody,
        theme: RequestBody
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .createPrivateChallenge(templateId, currentDate, theme)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .createPrivateChallenge(templateId, currentDate, theme)
                )
            }
            .map {
                STChallengesState.CreatePrivateChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun findChallenge(
        application: Application, joinCode: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .findChallenge(joinCode)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .findChallenge(joinCode)
                )
            }
            .map {
                STChallengesState.FindChallenge(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
    }

    private fun getMyChallengeByType(
        application: Application, offset: String, listType: String
    ): Observable<STChallengesState> {
        return STRetrofitClient.create(application)
            .getMyChallengesByType(listType, offset)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getMyChallengesByType(listType, offset)
                )
            }
            .map {
                STChallengesState.GetMyChallengesByType(it)
            }
            .cast(STChallengesState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STChallengesState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STChallengesState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STChallengesState.Loading)
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