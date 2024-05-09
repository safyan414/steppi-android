package com.steppi.steppifitness.ui.team_detail.mvi

import android.app.Application
import android.os.TransactionTooLargeException
import android.util.Log
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STRetrofitClient
import com.steppi.steppifitness.network.request.corporate.STAddEditTeamDiscussionMessageRequest
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.ui.base.mvi.MviBaseController
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import com.steppi.steppifitness.utils.STUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.security.auth.login.LoginException

class STTeamDetailController : MviBaseController<STTeamDetailState>() {
    override fun execute(
        intent: MviIntent,
        application: Application
    ): Observable<STTeamDetailState> =
        Observable.just(intent).flatMap { incomingIntent ->
            when (incomingIntent) {
                is STTeamDetailIntent.GetTeamDiscussionMessages -> getTeamDiscussionMessages(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.teamId,
                    incomingIntent.offset
                )
                is STTeamDetailIntent.AddTeamDiscussionMessage -> addTeamDiscussionMessage(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.teamId,
                    incomingIntent.addDiscussionMessage
                )
                is STTeamDetailIntent.EditTeamDiscussionMessage -> editTeamDiscussionMessage(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.teamId,
                    incomingIntent.messageId,
                    incomingIntent.editDiscussionMessage
                )
                is STTeamDetailIntent.SearchMembersIntent -> searchMembersList(
                    incomingIntent.searchMembersList,
                    incomingIntent.searchKey
                )
                is STTeamDetailIntent.CheerChallengeUser -> cheerChallengeUser(
                    application,
                    incomingIntent.challengeId,
                    incomingIntent.challengeUserId
                )
                is STTeamDetailIntent.GetChallengeDetails -> getChallengeDetails(
                    application,
                    incomingIntent.challengeId
                )
                is STTeamDetailIntent.GetChallengeTeamMembers -> getChallengeTeamMembers(
                        application,
                        incomingIntent.teamId,
                        incomingIntent.challengeId,
                        incomingIntent.offset)

                is STTeamDetailIntent.GetChallengeSearchedTeamMembers -> getChallengeSearchedTeamMembers(
                        application,
                        incomingIntent.teamId,
                        incomingIntent.challengeId,
                        incomingIntent.name)
                else -> null
            }
        }


    private fun getChallengeTeamMembers(
            application: Application,
            teamId: String,
            challengeId: String?,
            offset: Int
    ): Observable<STTeamDetailState> {
        return STRetrofitClient.create(application)
                .getChallengeTeamMembers(teamId,challengeId, offset)
                .doOnError { }
                .retryWhen { errors ->
                    retry(
                            application,
                            errors,
                            STRetrofitClient.create(application).getChallengeTeamMembers(teamId, challengeId,offset)
                    )
                }
                .map {
                    Log.e("value", it.toString())
                    try {
                        STTeamDetailState.GetChallengeTeamMember(it)
                    }
                    catch (e: TransactionTooLargeException)
                    {
                        Log.e("sdfsdf","sdfsdf");
                    }

                }
                .cast(STTeamDetailState::class.java)
                .onErrorReturn {
                    val errorDate = STErrorData()
                    if (STUtils.isInternetOn(application)) {
                        STTeamDetailState.Error(STUtils.getErrorMessage(application, it))
                    } else {
                        errorDate.message = application.resources.getString(R.string.no_internet)
                        STTeamDetailState.Error(errorDate)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .startWith(STTeamDetailState.Loading)
    }

    private fun getChallengeSearchedTeamMembers(
            application: Application,
            teamId: String,
            challengeId: String?,
            name: String
    ): Observable<STTeamDetailState> {
        return STRetrofitClient.create(application)
                .getChallengeSearchedTeamMembers(teamId,challengeId, name)
                .doOnError { }
                .retryWhen { errors ->
                    retry(
                            application,
                            errors,
                            STRetrofitClient.create(application).getChallengeSearchedTeamMembers(teamId, challengeId,name)
                    )
                }
                .map {
                    Log.e("value", it.toString())
                    try {
                        STTeamDetailState.GetChallengeSearcedTeamMember(it)
                    }
                    catch (e: TransactionTooLargeException)
                    {
                        Log.e("sdfsdf","sdfsdf");
                    }

                }
                .cast(STTeamDetailState::class.java)
                .onErrorReturn {
                    val errorDate = STErrorData()
                    if (STUtils.isInternetOn(application)) {
                        STTeamDetailState.Error(STUtils.getErrorMessage(application, it))
                    } else {
                        errorDate.message = application.resources.getString(R.string.no_internet)
                        STTeamDetailState.Error(errorDate)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                //.startWith(STTeamDetailState.Loading)
    }

    private fun getChallengeDetails(
        application: Application,
        challengeId: String?
    ): Observable<STTeamDetailState> {
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
                STTeamDetailState.GetChallengeDetails(it)
            }
            .cast(STTeamDetailState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STTeamDetailState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STTeamDetailState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STTeamDetailState.Loading)
    }


    private fun cheerChallengeUser(
        application: Application,
        challengeId: String?,
        challengeUserId: String?
    ): Observable<STTeamDetailState> {
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
                STTeamDetailState.CheerChallengeUser(it)
            }
            .cast(STTeamDetailState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STTeamDetailState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STTeamDetailState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
//            .startWith(STChallengesState.Loading)
    }

    private fun searchMembersList(
        membersList: List<STTeamMember>?,
        searchKey: String
    ): Observable<STTeamDetailState> =
        Single.create(SingleOnSubscribe<STTeamDetailState.SearchMembersState> { emitter ->
            /*
            * Filter arraylist using kotlin filter method
            * */
            val result = membersList?.filter {
                it.user?.name?.toLowerCase(Locale.getDefault())?.contains(searchKey.toLowerCase())
                    ?: false
            }
            result?.let {
                emitter.onSuccess(STTeamDetailState.SearchMembersState(it))
            }
        })
            .cast(STTeamDetailState::class.java)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .toObservable()

    private fun getTeamDiscussionMessages(
        application: Application,
        challengeId: String,
        teamId: String,
        offset: Int
    ): Observable<STTeamDetailState> {
        return STRetrofitClient.create(application)
            .getTeamDiscussionMessages(challengeId, teamId, offset.toString())
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .getTeamDiscussionMessages(challengeId, teamId, offset.toString())
                )
            }
            .map { STTeamDetailState.GetTeamDiscussionMessages(it) }
            .cast(STTeamDetailState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STTeamDetailState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STTeamDetailState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STTeamDetailState.Loading)
    }

    private fun addTeamDiscussionMessage(
        application: Application,
        challengeId: String,
        teamId: String,
        addDiscussionMessage: STAddEditTeamDiscussionMessageRequest
    ): Observable<STTeamDetailState> {
        return STRetrofitClient.create(application)
            .addTeamDiscussionMessage(challengeId, teamId, addDiscussionMessage)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .addTeamDiscussionMessage(challengeId, teamId, addDiscussionMessage)
                )
            }
            .map { STTeamDetailState.AddTeamDiscussionMessage(it) }
            .cast(STTeamDetailState::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                val errorData = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    val error = STUtils.getErrorMessage(application, it)
                    STTeamDetailState.Error(error)
                } else {
                    errorData.message = application.resources.getString(R.string.no_internet)
                    STTeamDetailState.Error(errorData)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STTeamDetailState.Loading)
    }

    private fun editTeamDiscussionMessage(
        application: Application,
        challengeId: String?,
        teamId: String,
        messageId: String,
        editDiscussionMessage: STAddEditTeamDiscussionMessageRequest
    ): Observable<STTeamDetailState> {
        return STRetrofitClient.create(application)
            .editTeamDiscussionMessage(challengeId, teamId, messageId, editDiscussionMessage)
            .doOnError { }
            .retryWhen { errors ->
                retry(
                    application,
                    errors,
                    STRetrofitClient.create(application)
                        .editTeamDiscussionMessage(
                            challengeId,
                            teamId,
                            messageId,
                            editDiscussionMessage
                        )
                )
            }
            .map {
                STTeamDetailState.EditTeamDiscussionMessage(it)
            }
            .cast(STTeamDetailState::class.java)
            .onErrorReturn {
                val errorDate = STErrorData()
                if (STUtils.isInternetOn(application)) {
                    STTeamDetailState.Error(STUtils.getErrorMessage(application, it))
                } else {
                    errorDate.message = application.resources.getString(R.string.no_internet)
                    STTeamDetailState.Error(errorDate)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .startWith(STTeamDetailState.Loading)
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
