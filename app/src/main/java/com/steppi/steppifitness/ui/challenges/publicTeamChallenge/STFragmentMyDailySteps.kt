package com.steppi.steppifitness.ui.challenges.publicTeamChallenge

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STMyDailyStepsListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.ChallengeUserSteps
import com.steppi.steppifitness.network.response.challenges.STDailyStepsResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_my_daily_steps.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STFragmentMyDailySteps :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var adapter: STMyDailyStepsListAdapter? = null
    private var challengeId: String? = null
    private var myRank: Int? = null

    //    private var offset = 0
    private var isChallengeStarted: Boolean = false
    private var isChallengeCompleted: Boolean = false
    private var challengeStartDate: String? = null
    private var challengeType: String? = null
    private var challengeEndDate: String? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_daily_steps
    }

    override fun initViews() {
        initData()
        initPullToRefresh()
    }

    private fun initData() {
        challengeId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        arguments?.getInt(STConstants.RANK)?.let {
            myRank = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_COMPLETED, false)?.let {
            isChallengeCompleted = it
        }
        arguments?.getString(STConstants.CHALLENGES_END_DATE)?.let {
            challengeEndDate = it
        }
        arguments?.getString(STConstants.CHALLENGES_START_DATE)?.let {
            challengeStartDate = it
        }
        arguments?.getString(STConstants.CHALLENGES_TYPE)?.let {
            challengeType = it
        }
    }

    private fun initPullToRefresh() {
        pullTolRefresh.setOnRefreshListener(this)
        pullTolRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
    }

    override fun onRefresh() {
        /*offset = 0*/
        challengeId?.let {
            invokeIntent(STChallengesIntent.GetDailyStepOfUser(challengeId!!/*, offset*/))
        }
    }

    override fun processState(state: STChallengesState) {
        when (state) {
            is STChallengesState.Loading -> {
                requestDidStart()
            }
            is STChallengesState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
                activityContext.finish()
            }
            is STChallengesState.GetDailyStepOfUser -> {
                requestDidFinish()
                pullTolRefresh?.isRefreshing = false
                setMyDailyStepsData(state.dailyStepResponse)
            }
            is STChallengesState.CheerChallengeUser -> {
            }
        }
    }

    private fun setMyDailyStepsData(dailyStepResponse: STDailyStepsResponse) {
        dailyStepResponse.data?.let {
            backgroundMain.visible()
            it.myUser?.let { myUserData ->
                myUserData.showAccomplishment.let {
                    if (it) {
                        accomplishmentLayout.visible()
                        if (myUserData.challengeDays != null) {
                            currentScore.text = myUserData.achievedDailyTargets
                            totalScore.text =
                                getString(R.string.slash) + myUserData.challengeDays.toString()
                        } else {
                            currentScore.text = "0"
                            totalScore.text = "0"
                        }
                    }
                }
                participantName.text = myUserData.name
                myUserTotalSteps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(myUserData.totalSteps?.toDouble()!!).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )/*NumberFormat.getNumberInstance(Locale.US)
                    .format(myUserData.totalSteps?.toInt())*/
                totalDailySteps.text = "${NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(myUserData.averageDaily?.toDouble()!!).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )/*NumberFormat.getNumberInstance(Locale.US)
                    .format(myUserData.averageDaily?.toInt())*/} ${getString(R.string.steps)}"
                myUserData.picture?.let { pictureUrl ->
                    userImageManRunning.gone()
                    userImage.visible()
                    userImage.load(
                        activityContext, pictureUrl
                    ) {
                        userImageManRunning.visible()
                        userImage.gone()
                    }
                } ?: kotlin.run {
                    userImageManRunning.visible()
                    userImage.gone()
                }
                myRank?.let {
                    myUserData.totalSteps?.let {
                        if (it != "0") {
                            if (myRank!! <= 2) {
                                rankingImage.visible()
                                when (myRank!!) {
                                    1 -> {
                                        if (challengeType == STConstants.STEPPI_CHALLENGE_3) {
                                            myUserData.achievedDailyTargets?.let { achievedDailyTargets ->
                                                if (achievedDailyTargets.toInt() > 0) {
                                                    rankingImage.visible()
                                                    rankingImage.setImageResource(R.drawable.win_label_one)
                                                } else {
                                                    rankingImage.gone()
                                                }
                                            }
                                        } else {
                                            rankingImage.setImageResource(R.drawable.win_label_one)
                                        }
                                    }
                                    2 -> {
                                        if (challengeType == STConstants.STEPPI_CHALLENGE_3) {
                                            rankingImage.gone()
                                        } else {
                                            rankingImage.setImageResource(R.drawable.win_label_two)
                                        }
                                    }
                                    3 -> {
                                        if (challengeType == STConstants.STEPPI_CHALLENGE_3) {
                                            rankingImage.gone()
                                        } else {
                                            rankingImage.setImageResource(R.drawable.win_label_three)

                                        }
                                    }
                                    else -> {
                                        rankingImage.gone()
                                    }
                                }
                            } else {
                                rankingImage.gone()
                            }
                        } else {
                            rankingImage.gone()
                        }
                    } ?: kotlin.run {
                        rankingImage.gone()
                    }
                } ?: kotlin.run {
                    rankingImage.gone()
                }
                myUserData.cheered?.let { myUserCheered ->
                    if (!myUserCheered) {
                        myUserData.cheerReceived?.let { cheerReceived ->
                            if (cheerReceived == "0") {
                                myUserCheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                                myUserCheerCount.text =
                                    activityContext.resources.getString(R.string.cheer_count_label)
                            } else {
                                myUserCheers.setImageResource(R.drawable.cheer_with_count_disabled)
                                myUserCheerCount.text = myUserData.cheerReceived
                            }
                        }
                        myUserCheerCount.textColor(
                            STUtils.getColor(
                                activityContext,
                                R.color.edit_text_bg_stroke_color
                            )
                        )
                    } else {
                        myUserCheers.setImageResource(R.drawable.cheer_with_count_enabled)
                        myUserCheerCount.text = myUserData.cheerReceived
                        myUserCheerCount.textColor(
                            STUtils.getColor(
                                activityContext,
                                R.color.button_bg_enabled_color
                            )
                        )
                    }
                }
                cheerLayout.setOnClickListener {
                    if (!myUserData.cheered!!) {
                        myUserCheerCount.text =
                            (myUserData.cheerReceived!!.toInt() + 1).toString()
                        myUserData.cheered = true
                        myUserCheerCount.textColor(
                            STUtils.getColor(
                                activityContext,
                                R.color.button_bg_enabled_color
                            )
                        )
                        myUserCheers.setImageResource(R.drawable.cheer_with_count_enabled)
                        invokeIntent(
                            STChallengesIntent.CheerChallengeUser(
                                challengeId!!,
                                myUserData.id!!
                            )
                        )
                    }
                }
                if (!isChallengeStarted || isChallengeCompleted) {
                    cheerLayout.gone()
                }
            }
            dailyStepsList.setVerticalLayoutManager()
            adapter = STMyDailyStepsListAdapter(activityContext)
            it.steps?.let { stepData ->
                val datesCompare: ArrayList<String>?
                val today = Calendar.getInstance()
                val todayDate = STUtils.getFormattedDate(today, "yyyy-MM-dd")
                datesCompare = if (todayDate < challengeEndDate!!) {
                    STUtils.getDatesInBetween(challengeStartDate!!, todayDate)
                } else {
                    STUtils.getDatesInBetween(challengeStartDate!!, challengeEndDate!!)
                }
                val challengeUserSteps: ArrayList<ChallengeUserSteps>? = ArrayList()
                datesCompare?.let { datesData ->
                    for (i in datesData.indices) {
                        val analyticsData: ChallengeUserSteps? = ChallengeUserSteps()
                        analyticsData?.date = datesData[i]
                        analyticsData?.steps = "0"
                        challengeUserSteps?.add(analyticsData!!)
                    }
                    for (serStepsPosition in challengeUserSteps!!.indices) {
                        for (posi in stepData.indices) {
                            if (challengeUserSteps[serStepsPosition].date == stepData[posi].date) {
                                challengeUserSteps[serStepsPosition].steps = stepData[posi].steps
                                challengeUserSteps[serStepsPosition].stared = stepData[posi].stared
                            }
                        }
                    }
                    adapter?.setData(
                        challengeUserSteps as List<ChallengeUserSteps>,
                        it.myUser?.showStars!!
                    )
                    dailyStepsList.adapter = adapter
                }
            }
        }
    }

    override fun onViewModelReady() {
        challengeId?.let {
            invokeIntent(STChallengesIntent.GetDailyStepOfUser(challengeId!!/*, offset*/))
        }
    }
}
