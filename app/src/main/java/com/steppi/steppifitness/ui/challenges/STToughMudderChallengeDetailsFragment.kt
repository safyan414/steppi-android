package com.steppi.steppifitness.ui.challenges

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.core.text.bold
import androidx.core.text.color
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STImageTopCornerCurvedViewPager
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.request.STToughMudderChallengeRequest
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.common.STWebViewActivity
import com.steppi.steppifitness.utils.*
import com.survicate.surveys.Survicate
import com.survicate.surveys.SurvicateAnswer
import com.survicate.surveys.SurvicateEventListener
import kotlinx.android.synthetic.main.fragment_my_challenge_details.*
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.*
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.challengeDescription
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.challengeDetailsMainLayout
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.challengeName
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.challengeStatusText
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.challengeThemeType
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.indicator
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.joinNow
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.next
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.pager
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.participantLayout
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.previous
import kotlinx.android.synthetic.main.fragment_tough_mudder_challenge_details.startDate
import vimeoextractor.OnVimeoExtractionListener
import vimeoextractor.VimeoExtractor
import vimeoextractor.VimeoVideo
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STToughMudderChallengeDetailsFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var challengesId: String? = null
    private var challengesData: STChallengesListData? = null
    private var challengeDetails: STChallengesListData? = null
    private var isCompleted: Boolean? = false
    private var isCompleteSurvey: Boolean? = false
    private var isChallengeStarted: Boolean = false
    var uri = ""
    var challengeStatus: Int? = -1


    override fun getLayoutId(): Int {
        return R.layout.fragment_tough_mudder_challenge_details
    }

    override fun initViews() {
        getIntentData()
        initPullToRefresh()
        surveyListener()

        TM_youtubeButton.setOnClickListener{

            if (challengeStatus != STConstants.TOUGH_MUDDER_CHALLENGE_NOT_JOINED) {
                showPlayer()

            } else {
                showToast("You need to join the challenge to watch the video.")
            }
        }
    }

    private  fun showPlayer () {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(uri), "video/*")
        startActivity(intent)
    }

    private fun surveyListener() {
        Survicate.setEventListener(object : SurvicateEventListener() {
            override fun onSurveyDisplayed(surveyId: String) {

            }

            override fun onQuestionAnswered(
                surveyId: String,
                questionId: Long,
                answer: SurvicateAnswer
            ) {
            }

            override fun onSurveyClosed(surveyId: String) {
                Survicate.reset()
            }

            override fun onSurveyCompleted(surveyId: String) {
                completeToughMudderChallenge()
               // activity?.finish()
            }
        }) 
    }

    private fun getIntentData() {
        challengesId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        challengesData =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA) as? STChallengesListData
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
        challengesId?.let {
            invokeIntent(STChallengesIntent.GetToughMudderChallengeDetails(it))
        }
    }

    override fun onViewModelReady() {
        challengesId?.let {
            invokeIntent(STChallengesIntent.GetToughMudderChallengeDetails(it))
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
            is STChallengesState.GetChallengeDetails -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
            is STChallengesState.JoinLeaveChallenge -> {
                STConstants.UPDATE_MY_CHALLENGE_LIST = true
                STConstants.UPDATE_STEPPI_CHALLENGE_LIST = true
                requestDidFinish()
                state.challengeDetailsResponse?.data?.let {
                    setChallengeDetailsData(state.challengeDetailsResponse)
                }
            }
            is STChallengesState.CompleteToughMudderChallenge -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Survicate.reset()
    }

    fun getVideo(url: String) {
        TM_youtubeButton.visible()

        VimeoExtractor.getInstance().fetchVideoWithURL(
            url,
            null,
            object : OnVimeoExtractionListener {
                override fun onSuccess(video: VimeoVideo) {
                    uri = video.streams[video.streams.keys.first()].toString()
                }

                override fun onFailure(throwable: Throwable?) {

                    Log.e("error player", throwable.toString())
                }
            })
    }

    private fun setChallengeDetailsData(challengeDetailsResponse: STChallengeDetailsResponse?) {
        pullTolRefresh?.isRefreshing = false
        isCompleteSurvey = false
        challengeDetailsMainLayout.visible()
        challengeDetails = challengeDetailsResponse?.data
        challengeDetails?.let { challengeDetailsData ->
            initViewPager()
            TM_youtubeButton.gone()
            challengeDetailsData.urlVideo?.let {

                if (it.isNotEmpty()) {
                    getVideo(it)
                }
            }

            challengeDetailsData.name?.let {
                challengeName.text = it
                (activityContext as STContainerActivity).setHeader(it)
            } ?: kotlin.run {
                (activityContext as STContainerActivity).setHeader(resources.getString(R.string.challenge_details))
            }
            challengeThemeType.gone()
            challengeThemeType.text = STConstants.THUGH_MUDDER
            challengeDetailsData.description?.let {
                if (it == "") {
                    challengeDescriptionLabel.gone()
                    challengeDescription.gone()
                }
                challengeDescription.text = it
            } ?: kotlin.run {
                challengeDescriptionLabel.gone()
                challengeDescription.gone()
            }
            challengeDetailsData.startDate?.let { startDate ->
                val currentDate = STUtils.getFormattedDate(
                    Calendar.getInstance(),
                    "yyyy-MM-dd"
                )
                when (STUtils.compareDate(startDate, currentDate)) {
                    STConstants.CHALLENGE_NOT_STARTED -> {
                        Log.e("CHALLENGE_NOT_STARTED", "CHALLENGE_NOT_STARTED")
                        isChallengeStarted = false
                    }
                    STConstants.CHALLENGE_STARTED -> {
                        isChallengeStarted = true
                    }
                }
            }
            challengeDetailsData.participants?.let { participants ->
                participantCount.text = participants
            } ?: kotlin.run {
                participantLayout.gone()
            }
            challengeDetailsData.startDate?.let {
                startDate.text = STUtils.getDate(
                    it, "yyyy-MM-dd", "dd-MMM-yyyy"
                )
            } ?: kotlin.run {
                startDateLayout.gone()
            }
            when (challengeDetailsData.toughMudderChallengeType) {
                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_STEPS -> {
                    challengeGoalLayout.visible()
                    challengeDetailsData.targetSteps?.let {
                        goalSteps.text = NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(it.toDouble()).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            ) + " " +
                                activityContext.getString(R.string.steps_per_day)
                        /*STUtils.formatNumber(challengeDetailsData.targetSteps?.toInt())*/
                    }
                }
                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_DISTANCE -> {
                    challengeGoalLayout.visible()
                    goalSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeDetailsData.targetDistance?.toDouble()!! / 1000).setScale(
                                4,
                                RoundingMode.HALF_EVEN
                            )
                        ) + " " + activityContext.getString(R.string.km_per_day)
                }
                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_CALORIES -> {
                    challengeGoalLayout.visible()
                    goalSteps.text =
                            /*STUtils.formatNumber(challengeDetailsData.targetCalories?.toInt())*/
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(challengeDetailsData.targetCalories?.toDouble()!!).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            ) + " " + activityContext.getString(R.string.cal_per_day)
                }
                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_ACTIVE_MINUTES -> {
                    challengeGoalLayout.visible()
                    goalSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeDetailsData.targetActiveMinutes?.toInt()!!) +
                            " " + activityContext.getString(R.string.minutes_per_day)
                }
                else -> {
                    challengeGoalLayout.gone()
                }
            }

            challengeDetailsData.UserSubChallenge?.let { UserSubChallenge ->
                when (challengeDetailsData.toughMudderChallengeType) {
                    STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_STEPS -> {
                        todaysProgress.text =
                                /*STUtils.formatNumber(UserSubChallenge.stepsInChallenge?.toInt())*/
                            NumberFormat.getNumberInstance(Locale.US)
                                .format(
                                    BigDecimal(UserSubChallenge.stepsInChallenge?.toDouble()!!).setScale(
                                        0,
                                        RoundingMode.HALF_EVEN
                                    )
                                ) + " " +
                                    activityContext.getString(R.string.steps)
                    }
                    STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_DISTANCE -> {
                        todaysProgress.text = NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(UserSubChallenge.distanceInChallenge?.toDouble()!! / 1000).setScale(
                                    3,
                                    RoundingMode.HALF_EVEN
                                )
                            ) + " " + activityContext.getString(R.string.distance_label_km)
                    }
                    STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_CALORIES -> {
                        todaysProgress.text =
                                /*STUtils.formatNumber(UserSubChallenge.caloriesInChallenge?.toInt())*/
                            NumberFormat.getNumberInstance(Locale.US)
                                .format(
                                    BigDecimal(UserSubChallenge.caloriesInChallenge?.toDouble()!!).setScale(
                                        3,
                                        RoundingMode.HALF_EVEN
                                    )
                                ) + " " +
                                    activityContext.getString(R.string.calorie_label)
                    }
                    STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_ACTIVE_MINUTES -> {
                        todaysProgress.text =
                                /*STUtils.formatNumber(UserSubChallenge.caloriesInChallenge?.toInt())*/
                            NumberFormat.getNumberInstance(Locale.US)
                                .format(UserSubChallenge.activeMinutesInChallenge?.toInt()!!) + " " +
                                    activityContext.getString(R.string.minutes_label)
                    }
                }
            }

            challengeStatus = challengeDetailsData.challengeStatus!!

            if (challengeDetailsData.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_NOT_JOINED
            ) {
                joinNow.visible()
                progressLayout.gone()
//                if (isCompleted!!) {
//                    joinNow.text = getString(R.string.join_another_challenge)
//                    challengeCompleteViewEnable(false)
//                } else {
                joinNow.text = getString(R.string.join)
//                }
            } else if (challengeDetailsData.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_JOINED_NOTSTARTED
            ) {
                joinNow.visible()
                joinNow.text = getString(R.string.start_)
                progressLayout.gone()
            } else if (challengeDetailsData.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_JOINED_STARTED
            ) {
                progressLayout.visible()
                if (challengeDetailsData.isChallengeCompleted!!) {
                    if (challengeDetailsData.isSurveyCompleted!!) {
                        joinNow.gone()
//                        isCompleteSurvey = true
                    } else {
//                        isCompleteSurvey = false
                        joinNow.visible()
                        joinNow.text = getString(R.string.complete_survey_)
                    }
                } else {
                    joinNow.gone()
                }
            } else if (challengeDetailsData.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_FINISHED
            ) {
                progressLayout.visible()
                joinNow.gone()
            } else if (challengeDetailsData.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_FAILED
            ) {
                progressLayout.visible()
                joinNow.gone()
            }
            if (!isChallengeStarted) {
                progressLayout.gone()
            }
            /*if (challengeDetailsData.challengeStatus!! !=
                STConstants.TOUGH_MUDDER_CHALLENGE_NOT_JOINED) {
                when {
                    challengeDetailsData.challengeStatus!! ==
                            STConstants.TOUGH_MUDDER_CHALLENGE_JOINED_NOTSTARTED
                    -> {
                        joinNow.visible()
                        joinNow.text = getString(R.string.start_)
                        progressLayout.gone()
                    }
                    challengeDetailsData.challengeStatus!! ==
                            STConstants.TOUGH_MUDDER_CHALLENGE_JOINED_STARTED
                    -> {
                        progressLayout.visible()
                        challengeDetailsData.UserSubChallenge?.let { UserSubChallenge ->
                            when (challengeDetailsData.toughMudderChallengeType) {
                                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_STEPS -> {
                                    if (UserSubChallenge.stepsInChallenge?.toInt() != 0 &&
                                        (UserSubChallenge.stepsInChallenge?.toInt()!! >=
                                                challengeDetailsData.targetSteps?.toInt()!!)
                                    ) {
                                        joinNow.text = getString(R.string.complete_survey_)
                                        isCompleteSurvey = true
                                        joinNow.visible()
                                    } else {
                                        joinNow.gone()
                                    }
                                }
                                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_DISTANCE -> {
                                    if (0 != UserSubChallenge.distanceInChallenge?.toInt() &&
                                        (UserSubChallenge.distanceInChallenge?.toInt()!! >=
                                                challengeDetailsData.targetDistance?.toInt()!!)
                                    ) {
                                        joinNow.text = getString(R.string.complete_survey_)
                                        isCompleteSurvey = true
                                        joinNow.visible()
                                    } else {
                                        joinNow.gone()
                                    }
                                }
                                STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_CALORIES -> {
                                    if (0 != UserSubChallenge.caloriesInChallenge?.toInt() &&
                                        (UserSubChallenge.caloriesInChallenge?.toInt()!! >=
                                                challengeDetailsData.targetCalories?.toInt()!!)
                                    ) {
                                        joinNow.text = getString(R.string.complete_survey_)
                                        isCompleteSurvey = true
                                        joinNow.visible()
                                    } else {
                                        joinNow.gone()
                                    }
                                }
                            }
                        }
                    }
                    challengeDetailsData.challengeStatus!! ==
                            STConstants.TOUGH_MUDDER_CHALLENGE_JOINED_COMPLETED
                    -> {
                        progressLayout.visible()
                        joinNow.visible()
                        joinNow.text =
                            activityContext.resources.getString(R.string.join_another_challenge)
                        challengeCompleteViewEnable(true)
                    }
                    challengeDetailsData.challengeStatus!! ==
                            STConstants.TOUGH_MUDDER_CHALLENGE_FAILED
                    -> {
                        joinNow.visible()
                        joinNow.text =
                            activityContext.resources.getString(R.string.join_another_challenge)
                    }
                    else -> {
                    }
                }
            } else {
                joinNow.visible()
                progressLayout.gone()
                if (isCompleted!!) {
                    joinNow.text = getString(R.string.join_another_challenge)
                    challengeCompleteViewEnable(false)
                } else {
                    joinNow.text = getString(R.string.join)
                }
            }*/
            challengeDetailsData.status?.let {
                challengeStatusText.visible()
                when {
                    it.equals(STConstants.CHALLENGE_STATUS_UPCOMING, true) -> {
                        challengeStatusText.text = activityContext.getString(R.string.upcoming)
                        challengeStatusText.setTextColor(
                            STUtils.getColor(
                                activityContext,
                                R.color.orange_color
                            )
                        )
                        isCompleted = false
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_ONGOING, true) -> {
                        challengeStatusText.text = activityContext.getString(R.string.ongoing)
                        challengeStatusText.setTextColor(
                            STUtils.getColor(
                                activityContext,
                                R.color.button_bg_enabled_color
                            )
                        )
                        isCompleted = false
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_COMPLETED, true) -> {
                        challengeStatusText.text = activityContext.getString(R.string.done)
                        challengeStatusText.setTextColor(
                            STUtils.getColor(
                                activityContext, R.color.button_bg_enabled_color
                            )
                        )
                        isCompleted = true
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_FAILED, true) -> {
                        challengeStatusText.text = activityContext.getString(R.string.failed)
                        challengeStatusText.setTextColor(
                            STUtils.getColor(
                                activityContext, R.color.red_color
                            )
                        )
                        isCompleted = false
                        joinNow.gone()
                    }
                }
            } ?: kotlin.run {
                challengeStatusText.gone()
            }
        }
    }

    @OnClick(R.id.joinNow)
    fun joinNow() {
        if (challengeDetails?.challengeStatus!! !=
            STConstants.TOUGH_MUDDER_CHALLENGE_NOT_JOINED
        ) {
            if (challengeDetails?.status == "Completed") {
                if (challengeDetails?.isChallengeCompleted!! && !challengeDetails?.isSurveyCompleted!!) {
                    challengeDetails?.surveyNumber?.let { surveyNumber ->
                        if (surveyNumber != "") {

                            try {
//                            Survicate.reset()
                                Survicate.invokeEvent(surveyNumber)
                            } catch (ex:Exception) {
                                Log.e("testErrorSurvicate", "joinNow: "+ex.toString())
                            }
                        }
                    }
                }
            }
            if (challengeDetails?.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_FAILED
            ) {
                activityContext.finish()
            }
            if (challengeDetails?.challengeStatus!! ==
                STConstants.TOUGH_MUDDER_CHALLENGE_JOINED_NOTSTARTED
            ) {
                startChallenge()
            }

        } else {
            if (joinNow.text == activityContext.getString(R.string.join_another_challenge)) {
                activityContext.finish()
            } else {
                joinChallengeDialog()
            }
        }
    }

    private fun completeToughMudderChallenge() {
        challengeDetails?.let {
            it.id?.let { id ->
                val toughMudderChallengeRequest = STToughMudderChallengeRequest()
                toughMudderChallengeRequest.subToughMudderChallengeId = id
                invokeIntent(
                    STChallengesIntent.CompleteToughMudderChallenge(
                        toughMudderChallengeRequest
                    )
                )
            }
        }
    }

    private fun startChallenge() {
        challengeDetails?.let {
            it.id?.let { id ->
                invokeIntent(STChallengesIntent.StartToughMudderChallenge(id))
            }
        }
    }

    private fun joinChallengeDialog() {
        val successDialog: Dialog = showJoinChallengeDialog {
            clickToAgree {
            }
            onTCClick()
            clickCheckboxLayout {}
            dialogOK {
                if (isPleaseAgree()) {
                    joinChallenge()
                    dismissDialog()
                }
            }
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun joinChallenge() {
        challengeDetails?.let {
            it.id?.let { id ->
                invokeIntent(
                    STChallengesIntent.JoinToughMudderChallenge(
                        STConstants.CHALLENGE_OPERATION_JOIN,
                        id
                    )
                )
            }
        }
    }

    private fun initViewPager() {
        val adapter = STImageTopCornerCurvedViewPager(activityContext)
        adapter.setItemImage(challengeDetails?.images)
        STUtils.setImageSize(
            activityContext,
            pager,
            STUtils.getDimen(activityContext, R.dimen.margin_small)
        )
        pager?.adapter = adapter
        pager.requestLayout()
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                setNextPreviousButton(position)
            }

            override fun onPageSelected(position: Int) {
            }

        })
        indicator.setViewPager(pager)
    }

    private fun setNextPreviousButton(position: Int) {
        challengeDetails?.images?.let {
            if (it.size > 1) {
                if (position in 0..it.size - 2) {
                    if (position <= 0) {
                        previous.setImageResource(R.color.transparent)
                    } else {
                        previous.setImageResource(R.drawable.view_pager_previous_button)
                    }
                    next.setImageResource(R.drawable.view_pager_next_button)
                } else {
                    if (position == it.size - 1) {
                        previous.setImageResource(R.drawable.view_pager_previous_button)
                        next.setImageResource(R.color.transparent)
                    }
                }
            } else {
                previous.setImageResource(R.color.transparent)
                next.setImageResource(R.color.transparent)
            }
        }
    }

    @OnClick(R.id.previous)
    fun previous() {
        pager.setCurrentItem(getItem(-1), true)
        setNextPreviousButton(pager.currentItem)
    }

    @OnClick(R.id.next)
    fun next() {
        pager.setCurrentItem(getItem(+1), true)
        setNextPreviousButton(pager.currentItem)
    }

    private fun getItem(i: Int): Int {
        return pager.currentItem + i
    }

    @OnClick(R.id.rulesLayout)
    fun rulesLayoutClick() {
        val learnMoreIntent = Intent(activityContext, STWebViewActivity::class.java)
        var url = ""
        url = BuildConfig.API_URL + STConstants.TOUGH_MUDDER_CHALLENGE_RULES
        learnMoreIntent.putExtra(
            STConstants.WEB_URL,
            url
        )
        learnMoreIntent.putExtra(
            STConstants.WEB_HEADER,
            activityContext.resources.getString(R.string.rules)
        )
        startActivity(learnMoreIntent)
    }

    private fun challengeCompleteViewEnable(isJoined: Boolean) {
        isCompleted = true
        challengeCompletedLayout.visible()
        pagerLayout.gone()
        val challengeTitleString = SpannableStringBuilder()
        val challengeCompletedDescriptionString = SpannableStringBuilder()
        when (challengeDetails?.toughMudderChallengeType) {
            STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_STEPS -> {
                challengeTitleString.append(resources.getString(R.string.case_1_reach_the_goal_1))
                    .color(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    ) {
                        append(
                            resources.getString(
                                R.string.reach_the_goal_2,
                                STUtils.formatNumber(challengeDetails?.targetSteps?.toInt())
                            )
                        )
                    }
                    .append(resources.getString(R.string.case_1_reach_the_goal_3))
            }
            STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_DISTANCE -> {
                challengeTitleString.append(resources.getString(R.string.case_1_reach_the_goal_1))
                    .color(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    ) {
                        append(
                            resources.getString(
                                R.string.reach_the_goal_2_distance,
                                STUtils.formatNumber(challengeDetails?.targetDistance?.toInt())
                            )
                        )
                    }
                    .append(resources.getString(R.string.case_1_reach_the_goal_3))
            }
            STConstants.TOUGH_MUDDER_CHALLENGE_TARGET_CALORIES -> {
                challengeTitleString.append(resources.getString(R.string.case_1_reach_the_goal_1))
                    .color(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    ) {
                        append(
                            resources.getString(
                                R.string.reach_the_goal_2_calories,
                                STUtils.formatNumber(challengeDetails?.targetCalories?.toInt())
                            )
                        )
                    }
                    .append(resources.getString(R.string.case_1_reach_the_goal_3))
            }
        }
        challengeCompletedDescriptionString.bold { append(resources.getString(R.string.challenge_complete_description_1)) }
            .color(
                STUtils.getColor(
                    activityContext,
                    R.color.button_bg_enabled_color
                )
            ) { append(STPreference.getName(activityContext)!!) }
            .append(resources.getString(R.string.case_1_reach_the_goal_2))
            .bold {
                append(
                    resources.getString(
                        R.string.challenge_complete_description_4,
                        challengeDetails?.challengeType!!
                    )
                )
            }
            .append(resources.getString(R.string.challenge_complete_description_5))
        completedTitleText.text = challengeTitleString
        challengeCompletedDescription.text = challengeCompletedDescriptionString
        if (!isJoined) {
            challengeCompletedDescription.gone()
        } else {
            challengeCompletedDescription.visible()
        }
    }


}


