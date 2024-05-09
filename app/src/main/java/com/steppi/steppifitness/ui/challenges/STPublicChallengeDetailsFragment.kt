package com.steppi.steppifitness.ui.challenges

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_public_challenge_details.*
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt
import android.text.Spanned
import android.view.Gravity
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.adapter.STImageTopCornerCurvedViewPager
import com.steppi.steppifitness.adapter.challenge.STLeaderBoardListAdapter
import com.steppi.steppifitness.adapter.challenge.STLeaderBoardListPC2Adapter
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.STAPIServices
import com.steppi.steppifitness.network.response.challenges.data.*
import com.steppi.steppifitness.ui.common.STWebViewActivity
import com.steppi.steppifitness.views.STGothamRoundedBoldTypefaceSpan
import com.steppi.steppifitness.views.STGothamRoundedMediumTypefaceSpan
import kotlinx.android.synthetic.main.fragment_public_challenge_details.challengeStatus
import kotlinx.android.synthetic.main.fragment_public_challenge_details.endDate
import kotlinx.android.synthetic.main.fragment_public_challenge_details.goalSteps
import kotlinx.android.synthetic.main.fragment_public_challenge_details.indicator
import kotlinx.android.synthetic.main.fragment_public_challenge_details.leaderBoardList
import kotlinx.android.synthetic.main.fragment_public_challenge_details.next
import kotlinx.android.synthetic.main.fragment_public_challenge_details.pager
import kotlinx.android.synthetic.main.fragment_public_challenge_details.previous
import kotlinx.android.synthetic.main.fragment_public_challenge_details.progressBar1
import kotlinx.android.synthetic.main.fragment_public_challenge_details.progressBarLayout
import kotlinx.android.synthetic.main.fragment_public_challenge_details.progressPercentage
import kotlinx.android.synthetic.main.fragment_public_challenge_details.pullTolRefresh
import kotlinx.android.synthetic.main.fragment_public_challenge_details.startDate
import kotlinx.android.synthetic.main.fragment_public_challenge_details.stepsToGo
import kotlinx.android.synthetic.main.fragment_public_challenge_details.userImageManRunningWinner
import kotlinx.android.synthetic.main.layout_challenge_winner.*
import kotlinx.android.synthetic.main.list_item_leader_board_list.*
import kotlinx.android.synthetic.main.public_challenge_two_info.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.math.RoundingMode

class STPublicChallengeDetailsFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var challengesId: String? = null
    private var challengeDetails: STChallengesListData? = null
    private var achievedStepsValue: Long = 0
    private var totalStepsValue: Long = 0
    private var stepsToGoValue: Long = 0
    private var challengeJoined = false
    private var leaderBoardListAdapter: STLeaderBoardListAdapter? = null
    private var leaderBoardListPC2Adapter: STLeaderBoardListPC2Adapter? = null
    private var isChallengeStarted: Boolean = false
    private var challengeDetailsDataList: STChallengeDetailsResponse? = null
    private var leaderBoardItemsList: List<STLeaderBoardChallengeUsers>? = null
    private var cheerClickPosition: Int? = null
    private var challengeIsPrivate: Boolean? = false
    private var privateChallengeJoinCode: String? = null
    private var privateChallengeLink: String? = null
    private var winnerTeamCheerReceived: String? = "0"
    private var isWinnerCheered: Boolean? = false
    private var isUpcomingChallenge: Boolean = false
    private var participantTeams: List<STParticipantTeams>? = null
    private var myUserData: STLeaderBoardMyUser? = null
    private var winnerTeamData: STLeaderBoardChallengeUsers? = null
    private var isMVPCheered: Boolean? = false
    private var isCompleted: Boolean? = false
    private var dfcDialog: Dialog? = null
    private var publicJoinDialog: Dialog? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_public_challenge_details
    }

    override fun initViews() {
        initData()
        initPullToRefresh()
        initMyChallengesList()
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
        if (challengeIsPrivate!!) {
            privateChallengeJoinCode?.let { privateChallengeJoinCode ->
                invokeIntent(
                    STChallengesIntent.GetPrivateChallengeDetails(
                        challengesId!!,
                        privateChallengeJoinCode
                    )
                )
            }
        } else {
            challengesId?.let {
                invokeIntent(STChallengesIntent.GetChallengeDetails(it))
            }
        }
    }

    private fun initData() {
        challengesId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        challengeDetailsDataList =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA_LIST) as? STChallengeDetailsResponse
        arguments?.getBoolean(STConstants.CHALLENGES_ISPRIVATE, false)?.let {
            challengeIsPrivate = it
        }
        arguments?.getString(STConstants.PRIVATE_CHALLENGE_JOIN_CODE)?.let {
            privateChallengeJoinCode = it
        }

    }

    override fun onViewModelReady() {
        challengeDetailsDataList?.let {
            setChallengeDetailsData(it)
        } ?: kotlin.run {
            if (challengeIsPrivate!!) {
                privateChallengeJoinCode?.let { privateChallengeJoinCode ->
                    invokeIntent(
                        STChallengesIntent.GetPrivateChallengeDetails(
                            challengesId!!,
                            privateChallengeJoinCode
                        )
                    )
                }
            } else {
                challengesId?.let {
                    invokeIntent(STChallengesIntent.GetChallengeDetails(it))
                }
            }
        }
    }

    private var testCallback: (() -> Unit)? = null
    fun setTestCallback(testCallback: (() -> Unit)?) {
        this.testCallback = testCallback
    }

    override fun processState(state: STChallengesState) {
        when (state) {
            is STChallengesState.Loading -> {
                requestDidStart()
            }
            is STChallengesState.Error -> {
                requestDidFinish()
                if (
                    !state.errorData?.message.toString().equals("already participated in challenge")
                ) showToast(state.errorData?.message)
                else showToast("Challenge Joined Successfully")

                manageError(state.errorData?.statusCode)
                if (challengeDetails?.type != STConstants.DUBAI_FITNESS_CHALLENGE) {
                    activityContext.finish()
                } /*else {
                    dfcDialog?.dismiss()
                }*/
            }
            is STChallengesState.GetChallengeDetails -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
            is STChallengesState.GetPrivateChallengeDetails -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
            is STChallengesState.JoinLeaveChallenge -> {

                STConstants.UPDATE_MY_CHALLENGE_LIST = true
                STConstants.UPDATE_STEPPI_CHALLENGE_LIST = true
                requestDidFinish()

                state.challengeDetailsResponse?.data?.let {
                    if (!it.joined!! && (it.type == STConstants.STEPPI_CHALLENGE_2 || it.type == STConstants.STEPPI_CHALLENGE_3)) {
                        activityContext.finish()
                    } else {
                        if (challengeDetails?.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                            dfcDialog?.let { dfcDialog_ ->
                                if (dfcDialog_.isShowing)
                                    dfcDialog_.dismiss()
                            }
                        }
                        setChallengeDetailsData(state.challengeDetailsResponse)
                    }
                }
                state.challengeDetailsResponse?.let {
                    trackJoinChallenge(it)
                }
            }
            is STChallengesState.JoinLeavePrivateChallenge -> {
                STConstants.UPDATE_MY_CHALLENGE_LIST = true
                STConstants.UPDATE_STEPPI_CHALLENGE_LIST = true
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
                state.challengeDetailsResponse?.let {
                    trackJoinChallenge(it)
                }
            }
            is STChallengesState.CheerChallengeUser -> {
//                requestDidFinish()
//                cheerClickPosition?.let {
//                    val leaderboardData = leaderBoardItemsList!![it]
//                    leaderboardData.cheered = true
//                    leaderboardData.cheerReceived =
//                        (leaderboardData.cheerReceived!!.toInt() + 1).toString()
//                    leaderBoardListAdapter!!.notifyDataSetChanged()
//                }
            }
            else -> {
            }
        }
    }

    private fun trackJoinChallenge(challengeDetailsResponse: STChallengeDetailsResponse) {
        challengeDetailsResponse.data?.let { challengeDetails ->
            if (challengeDetails.joined!!) {
                val eventNameList: HashMap<String, String> = HashMap()
                challengeDetails.name?.let { challengeName ->
                    eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_NAME] =
                        challengeName
                }
                challengeDetails.id?.let { challengeId ->
                    eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_ID] =
                        challengeId
                }
                if (!BuildConfig.DEBUG)
                    STFireBaseAnalytics.trackEventValue(
                        STFireBaseAnalyticsConstants.EVENT_PUBLIC_CHALLENGE_JOIN,
                        eventNameList
                    )
            } else {
                val eventNameList: HashMap<String, String> = HashMap()
                challengeDetails.name?.let { challengeName ->
                    eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_NAME] =
                        challengeName
                }
                challengeDetails.corporation?.let { challengeCorporation ->
                    challengeCorporation.name?.let { corporateName ->
                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CORPORATE_NAME] =
                            corporateName
                    }
                }
                challengeDetails.id?.let { challengeId ->
                    eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_ID] =
                        challengeId
                }
                challengeDetails.corporation?.let { challengeCorporation ->
                    challengeCorporation.id?.let { corporateId ->
                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CORPORATE_CHALLENGE_ID] =
                            corporateId
                    }
                }
                if (!BuildConfig.DEBUG)
                    STFireBaseAnalytics.trackEventValue(
                        STFireBaseAnalyticsConstants.EVENT_CORPORATE_CHALLENGE_LEAVE,
                        eventNameList
                    )
            }
        }
    }

    @OnClick(R.id.sharePrivateChallengeCode)
    fun sharePrivateChallengeCode() {
        privateChallengeLink?.let {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                activityContext.getString(R.string.private_challenge_share_text)
                    .replace("$$$$", it)
            )
            sendIntent.type = "text/plain"
            activityContext.startActivity(sendIntent)
        }
    }

    @OnClick(R.id.copyCode)
    fun copyPrivateChallengeCode() {
        STUtils.getValueFromView(challengeCodeText)?.let {
            activityContext.copyToClipBoard(
                STConstants.REFERRAL_CODE,
                STUtils.getValueFromView(challengeCodeText)!!
            )
            showToast(getString(R.string.copy_challenge_code))
        }
    }

    @OnClick(R.id.winnerCheerLayout)
    fun winnerCheerLayoutClick() {
        if (!isWinnerCheered!!) {
            winnerCheers.setImageResource(R.drawable.cheer_with_count_enabled)
            winnerCheerCount.text = (winnerTeamCheerReceived!!.toInt() + 1).toString()
            winnerCheerCount.textColor(
                STUtils.getColor(
                    activityContext,
                    R.color.button_bg_enabled_color
                )
            )
            winnerTeamData?.let {
                invokeIntent(
                    STChallengesIntent.CheerChallengeUser(
                        challengesId!!,
                        it.id!!
                    )
                )
            }
            leaderBoardItemsList?.let { leaderBoardList ->
                val mData = leaderBoardList[0]
                mData.cheerReceived = (mData.cheerReceived!!.toInt() + 1).toString()
                mData.cheered = true
                leaderBoardListAdapter!!.notifyDataSetChanged()
            }
        }
    }

    @OnClick(R.id.cheerLayout)
    fun cheerLayoutClick() {
        myUserData?.let {
            if (!it.cheered!!) {
                invokeIntent(
                    STChallengesIntent.CheerChallengeUser(
                        challengesId!!,
                        it.id!!
                    )
                )
                it.cheerReceived = (it.cheerReceived!!.toInt() + 1).toString()
                it.cheered = true
                setMyUserLayout(it)
                leaderBoardItemsList?.let { leaderBoardList ->
                    if (leaderBoardList.size >= it.rank!!.toInt()) {
                        val mData = leaderBoardList[it.rank.toInt() - 1]
                        mData.cheerReceived = (mData.cheerReceived!!.toInt() + 1).toString()
                        mData.cheered = true
                        leaderBoardListAdapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    @OnClick(R.id.rulesLayout)
    fun rulesLayoutClick() {
        val learnMoreIntent = Intent(activityContext, STWebViewActivity::class.java)
        var url = ""
        if (challengeIsPrivate!!) {
            url = BuildConfig.API_URL + STConstants.PRIVATE_CHALLENGE_RULES
        } else {
            when (challengeDetails?.type) {
                STConstants.STEPPI_CHALLENGE_1 -> {
                    url = BuildConfig.API_URL + STConstants.STEPPI_CHALLENGE_RULES
                }
                STConstants.STEPPI_CHALLENGE_2 -> {
                    url = BuildConfig.API_URL + STConstants.PC2_RULES
                }
                STConstants.DUBAI_FITNESS_CHALLENGE -> {
                    url = BuildConfig.API_URL + STConstants.DFC_CHALLENGE_RULES
                }
                STConstants.STEPPI_CHALLENGE_3 -> {
                    url = BuildConfig.API_URL + STConstants.PC3_RULES
                }
            }
        }
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

    private fun setMyUserLayout(myUser: STLeaderBoardMyUser?) {
        myUser?.let {
            userName.text = it.name
            it.picture?.let { pictureUrl ->
                userImageManRunning.gone()
                userImage.visible()
                userImage.load(activityContext, pictureUrl) {
                    userImageManRunning.visible()
                    userImage.gone()
                }
            } ?: kotlin.run {
                userImageManRunning.visible()
                userImage.gone()
//                        holder.itemView.userImageManRunning.setImageResource(R.drawable.woman_running)
            }
            it.rank?.let { rank ->
                position.text = rank.toString()
            }
            it.rank?.let { myUserRank ->
                position.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(myUserRank.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )/*NumberFormat.getNumberInstance(Locale.US).format(myUserRank.toInt())*/
            }
            if (challengeDetails?.type == STConstants.STEPPI_CHALLENGE_3) {
                stepsRemainingLayout.gone()
                it.achievedDailyTargets?.let { achievedDailyTargets ->
                    steps.text =
                        achievedDailyTargets.plus("/").plus(challengeDetails?.challengeDays)
                    stepsLabelMyUser.gone()
                }
            } else {
                it.totalSteps?.let { totalSteps ->
                    steps.text =
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(totalSteps.toDouble()).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            )
                    /*NumberFormat.getNumberInstance(Locale.US).format(totalSteps.toInt())*/
                }
                if (challengeDetails?.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                    stepsRemainingLayout.gone()
                } else {
                    it.next?.let { stepsToNextLevel ->
                        if (stepsToNextLevel == "0") {
                            stepsRemainingLayout.gone()
                        } else {
                            stepsRemainingLayout.visible()
                            stepsRemaining.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                    .format(
                                        BigDecimal(stepsToNextLevel.toDouble()).setScale(
                                            0,
                                            RoundingMode.HALF_EVEN
                                        )
                                    )
                            /*NumberFormat.getNumberInstance(Locale.US)
                            .format(stepsToNextLevel.toInt())*/
                        }
                    } ?: kotlin.run {
                        stepsRemainingLayout.gone()
                    }
                }
            }
            it.cheered?.let { isCheeredValue ->
                if (!isCheeredValue) {
                    myUser.cheerReceived?.let { cheerReceived ->
                        if (cheerReceived == "0") {
                            cheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                            cheerCount.text =
                                activityContext.resources.getString(R.string.cheer_count_label)
                        } else {
                            cheers.setImageResource(R.drawable.cheer_with_count_disabled)
                            cheerCount.text = myUser.cheerReceived
                        }
                    }
                    cheerCount.textColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.edit_text_bg_stroke_color
                        )
                    )
                } else {
                    cheers.setImageResource(R.drawable.cheer_with_count_enabled)
                    cheerCount.text = myUser.cheerReceived
                    cheerCount.textColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                }
            }
            if (!isChallengeStarted || isCompleted!!) {
                cheerLayout.gone()
                stepsRemainingLayout.gone()
            }
            it.rank?.let { myUserRank ->
                if (myUserRank.toInt() > 10) {
                    myUserLayout.visible()
//                    divider.visible()
                } else {
                    myUserLayout.gone()
//                    divider.gone()
                }
            } ?: kotlin.run {
                myUserLayout.gone()
            }
        } ?: kotlin.run {
            myUserLayout.gone()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setChallengeDetailsData(challengeDetailsResponse: STChallengeDetailsResponse?) {
        pullTolRefresh?.isRefreshing = false
        challengeDetails = challengeDetailsResponse?.data
        challengeDetails?.let { challengeDetailsData ->
            initViewPager()
            challengeDetailsMainLayout.visibility = View.VISIBLE
            //challengeType.text = challengeDetailsData.challengeType
            challengeDetailsData.name?.let {
                challengeName.text = it
            }
            challengeDetailsData.description?.let {
                if (it != "") {
                    challengeDescriptionLabel.visible()
                    challengeDescription.text = it
                } else {
                    challengeDescriptionLabel.gone()
                    challengeDescription.gone()
                }
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
                        isUpcomingChallenge = true
                    }
                    STConstants.CHALLENGE_STARTED -> {
                        isChallengeStarted = true
                        isUpcomingChallenge = false
                    }
                }
            }
            myUserData = challengeDetailsData.myUser
            setMyUserLayout(myUserData)
            if (activityContext is STContainerActivity) {
                challengeDetailsData.name?.let {
                    (activityContext as STContainerActivity).setHeader(it)
                }
            }

            challengeDetailsData.status?.let {
                //if (adapter.challengeListType == STConstants.MY_CHALLENGE_LIST && it.isNotEmpty()) {
                challengeStatusText.visible()
                when { // Upcoming Ongoing Completed
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
                }
            } ?: kotlin.run {
                challengeStatusText.gone()
            }


//            challengeDetailsData.challengeType?.let { challengeDetailsChallengeType ->
//                if (challengeDetailsChallengeType.equals(
//                        STConstants.STEPPI_CHALLENGE,
//                        ignoreCase = true
//                    )
//                ) {
            challengeDetailsData.challengeUsers?.let {
                setLeaderBoardAdapter(it)
            } ?: kotlin.run {
                leaderBoardLayout.gone()
//                noLeaderBoardData.visible()
            }
            if (challengeDetailsData.isPrivate!!) {
                setPrivateChallenge(challengeDetailsData)
            } else {
                privateChallengeCellLayout.gone() // private
                privateChallengeCodeAdminLayout.gone() // private
                when {
                    challengeDetailsData.type!! == STConstants.STEPPI_CHALLENGE_2 -> {
                        setPcTwoChallenge(challengeDetailsData)
                    }
                    challengeDetailsData.type!! == STConstants.DUBAI_FITNESS_CHALLENGE -> {
                        setPcTwoChallenge(challengeDetailsData)
                    }
                    challengeDetailsData.type == STConstants.STEPPI_CHALLENGE_3 -> {
                        setPcThreeChallenge(challengeDetailsData)
                    }
                    else -> {
                        setPcOneChallenge(challengeDetailsData)
                    }
                }
            }
        }
    }

    private fun setPcOneChallenge(challengeDetailsData: STChallengesListData) {
        pcThreeCellLayout.gone()
        pcTwoLeaderBoardLayout.gone()
        publicChallengeTwoCellLayout.gone()
        publicChallengeGoalCellLayout.visible()
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.challenge_theme_type,
                0
            )
        } else {
            challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.challenge_theme_type,
                0,
                0,
                0
            )
        }
        challengeDetailsData.type?.let {
            challengeThemeType.visible()
            when (it) {
                STConstants.STEPPI_CHALLENGE_1 -> {
                    challengeThemeType(
                        activityContext.getString(R.string.pc1)/*STConstants.PC1*/,
                        R.drawable.button_bg_violet,
                        R.drawable.challenge_theme_type
                    )
                }
                STConstants.STEPPI_CHALLENGE_2 -> {
                    challengeThemeType.text =
                        activityContext.getString(R.string.pc2)/*STConstants.PC2*/
                }
                STConstants.STEPPI_CHALLENGE_3 -> {
                    challengeThemeType.text =
                        activityContext.getString(R.string.pc3)/*STConstants.PC3*/
                }
                else -> {
                    challengeThemeType.text = it
                    challengeThemeType(
                        it,
                        R.drawable.button_bg_violet,
                        R.drawable.challenge_theme_type
                    )
                }
            }
        } ?: kotlin.run {
            challengeThemeType.gone()
        }
        challengeDetailsData.targetSteps?.let {
            val stepsValue =
                NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
            val stepsValueFontUpdate = SpannableStringBuilder(stepsValue)
            val stepsString =
                SpannableStringBuilder(activityContext.getString(R.string.steps))
            stepsValueFontUpdate.setSpan(
                STGothamRoundedBoldTypefaceSpan(activityContext, ""),
                0,
                stepsValue.length,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            stepsString.setSpan(
                STGothamRoundedMediumTypefaceSpan(activityContext, ""),
                0,
                4,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            val stepsText = SpannableStringBuilder()
            stepsText.bold { append(stepsValueFontUpdate) }
                .append(activityContext.getString(R.string.space)).append(stepsString)
            goalSteps.text = stepsText
            goalSteps.isSelected = true
        }
        totalStepsValue = challengeDetailsData.targetSteps?.toLong()!!
        challengeDetailsData.voucherDescription?.let {
            if (it.isEmpty()) {
                voucherDescriptionLayout.gone()
            } else {
                voucherDescriptionLayout.visible()
            }
            val voucherDescription =
                activityContext.findViewById<TextView>(R.id.giftVoucherDescription)
            voucherDescription.text = it
            voucherDescription.post {
                val lineCount: Int = voucherDescription.lineCount
                if (lineCount > 1) {
                    voucherDescription.gravity = Gravity.START
                    voucherDescription.textAlignment =
                        View.TEXT_ALIGNMENT_TEXT_START
                } else {
                    voucherDescription.gravity = Gravity.END
                    voucherDescription.textAlignment =
                        View.TEXT_ALIGNMENT_TEXT_END
                }
            }
        } ?: kotlin.run {
            voucherDescriptionLayout.gone()
        }
        challengeDetailsData.participants?.let {
            participantCount.text =
                NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
        }
        challengeDetailsData.states?.let { statesData ->
            statesData.achievedSteps?.let {
                achievedSteps.text =
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(it.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
                achievedStepsValue = it
            } ?: kotlin.run {
                achievedSteps.text = getString(R.string.zero)
                achievedStepsValue = 0
            }
            statesData.achievedSteps.toString()
            statesData.averageDailySteps?.let {
                averageDailySteps.text =
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(it.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
            } ?: kotlin.run {
                averageDailySteps.text = getString(R.string.zero)
            }
            statesData.perUserAverageDailySteps?.let {
                perUserAverageDailySteps.text =
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(it.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                /*NumberFormat.getNumberInstance(Locale.US)
                    .format(it)*/
            } ?: kotlin.run {
                perUserAverageDailySteps.text = getString(R.string.zero)
            }
        }
        val percentageValue: Int? =
            if (((achievedStepsValue * 100.00) / totalStepsValue) < 100 &&
                ((achievedStepsValue * 100.00) / totalStepsValue) > 99
            ) {
                99
            } else {
                if (totalStepsValue > 0) {
                    if (achievedStepsValue > totalStepsValue) {
                        100
                    } else {
                        ((achievedStepsValue * 100.00) / totalStepsValue).roundToInt()
                    }
                } else {
                    0
                }
            }
        progressBar1.progress = percentageValue!!
        progressPercentage.text = "$percentageValue%"
        challengeDetailsData.startDate?.let {
            startDate.text = STUtils.getDate(
                it, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        } ?: kotlin.run {
            startDateLayout.gone()
        }
        challengeDetailsData.endDate?.let {
            endDate.text = STUtils.getDate(
                it, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        } ?: kotlin.run {
            endDateLayout.gone()
        }
        if (isChallengeStarted) {
            stepsToGoValue = totalStepsValue - achievedStepsValue
            if (percentageValue == 100) {
                stepsToGo.gone()
            } else {
                stepsToGo.visible()
                stepsToGo.text =
                    "${
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(stepsToGoValue.toDouble()).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            )/*NumberFormat.getNumberInstance(Locale.US)
                                    .format(stepsToGoValue)*/
                    } ${
                        activityContext.resources.getString(
                            R.string.steps_to_go
                        )
                    }"
            }
            challengeDetailsData.endDate?.let {
                if (totalStepsValue == 0L) {
                    progressBarLayout.gone()
                    stepsToGo.gone()
                } else {
                    progressBarLayout.visible()
                }
            } ?: kotlin.run {
                endDateLayout.gone()
                progressBarLayout.visible()
                if (percentageValue == 100) {
                    stepsToGo.gone()
                } else {
                    stepsToGo.visible()
                }
            }
            challengeDetailsData.userStates?.let { myContributionData ->
                myContributionLayout.visible()
                myContributionData.totalSteps?.let {
                    totalSteps.text =
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(it.toDouble()).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            )
                    /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
                }
                myContributionData.averageSteps?.let {
                    averageSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(it.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
                }
                /*profileImage.load(
                        activityContext,
                        STPreference.getProfilePic(activityContext)
                    )*/
            } ?: kotlin.run {
                joinNow.visible()
            }
        } else {
            stepsTillNowLayout.gone()
            stepsToGo.gone()
            progressBarLayout.gone()
        }
        if (challengeDetailsData.targetSteps.isNullOrEmpty() || challengeDetailsData.targetSteps == "0") {
            challengeStatus.gone()
            stepsTillNowLayout.gone()
            stepsToGo.gone()
        }
        val challengeTitleString = SpannableStringBuilder()
        val challengeCompletedDescriptionString = SpannableStringBuilder()
        if (challengeDetailsData.joined!!) {
            if (isChallengeStarted) {
                stepsTillNowLayout.visible()
                if (challengeDetailsData.participants?.toInt()!! > 0) {
                    challengeDetailsData.challengeUsers?.let {
                        leaderBoardLayout.visible()
                    } ?: kotlin.run {
                        leaderBoardLayout.gone()
                    }
                }
            } else {
                leaderBoardLayout.gone()
            }
            if (isChallengeStarted &&
                challengeDetailsData.theme == STConstants.CHALLENGE_THEME_1 &&
                challengeDetailsData.type == STConstants.STEPPI_CHALLENGE_1
            ) {
                if (STConstants.UPDATE_MY_CHALLENGE_LIST) {
                    testCallback?.invoke()
                }
            }
            challengeJoined = true
            if (challengeDetailsData.status == STConstants.CHALLENGE_STATUS_COMPLETED) {
                isCompleted = true
                stepsToGo.gone()
                challengeCompletedLayout.visible()
                pagerLayout.gone()

                challengeDetailsData.endDate?.let {
                    if (totalStepsValue == 0L) {
                        progressBarLayout.gone()
                        stepsToGo.gone()
                        challengeTitleString.append(resources.getString(R.string.case_2_reach_the_goal_1))
                    } else {
                        progressBarLayout.visible()
                        val stepsReached =
                            if (totalStepsValue.toInt() > achievedStepsValue.toInt()) {
                                achievedStepsValue.toInt()
                            } else {
                                totalStepsValue.toInt()
                            }
                        challengeTitleString.append(resources.getString(R.string.case_3_reach_the_goal_1))
                            .color(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            ) {
                                append(
                                    resources.getString(
                                        R.string.case_3_reach_the_goal_3,
                                        NumberFormat.getNumberInstance(Locale.US)
                                            .format(
                                                BigDecimal(stepsReached.toDouble()).setScale(
                                                    0,
                                                    RoundingMode.HALF_EVEN
                                                )
                                            )
                                        /*STUtils.formatNumber(stepsReached)*/
                                    )
                                )
                            }
                    }
                } ?: kotlin.run {
                    endDateLayout.gone()
                    progressBarLayout.visible()
                    if (percentageValue == 100) {
                        stepsToGo.gone()
                    } else {
                        stepsToGo.visible()
                    }
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
                                    NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                            BigDecimal(totalStepsValue.toDouble()).setScale(
                                                0,
                                                RoundingMode.HALF_EVEN
                                            )
                                        )
                                    /*STUtils.formatNumber(totalStepsValue.toInt())*/
                                )
                            )
                        }
                        .append(resources.getString(R.string.case_1_reach_the_goal_3))
                }
                challengeCompletedDescriptionString.bold { append(resources.getString(R.string.challenge_complete_description_1)) }
                    //.append(resources.getString(R.string.challenge_complete_description_2))
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
                                challengeDetailsData.challengeType!!
                            )
                        )
                    }
                    .append(resources.getString(R.string.challenge_complete_description_5))

                completedTitleText.text = challengeTitleString
                challengeCompletedDescription.text = challengeCompletedDescriptionString
                joinNow.visible()
                joinNow.text =
                    activityContext.resources.getString(R.string.join_another_challenge)
            } else {
                isCompleted = false
                joinNow.gone()
                joinedStatus.gone()
            }
        } else {
            if (isChallengeStarted) {
                stepsTillNowLayout.gone()
                progressBarLayout.visible()
                stepsToGo.visible()
            } else {
                stepsTillNowLayout.gone()
                progressBarLayout.gone()
                stepsToGo.gone()
            }
            leaderBoardLayout.gone()
            challengeJoined = false
            joinNow.visible()
            if (/*achievedStepsValue >= totalStepsValue && achievedStepsValue.toInt() != 0
                            || */challengeDetailsData.status == STConstants.CHALLENGE_STATUS_COMPLETED
            ) {
                stepsToGo.gone()
//                            progressPercentage.text = getString(R.string.hundreds_percentage)
                challengeCompletedLayout.gone()
                pagerLayout.visible()
                challengeDetailsData.endDate?.let {
                    if (totalStepsValue == 0L) {
                        progressBarLayout.gone()
                        stepsToGo.gone()
                        challengeTitleString.append(resources.getString(R.string.case_2_reach_the_goal_1))
                    } else {
                        progressBarLayout.visible()
                        challengeTitleString.append(resources.getString(R.string.case_3_reach_the_goal_1))
                            .color(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            ) {
                                append(
                                    resources.getString(
                                        R.string.case_3_reach_the_goal_3,
                                        NumberFormat.getNumberInstance(Locale.US)
                                            .format(
                                                BigDecimal(totalStepsValue.toDouble()).setScale(
                                                    0,
                                                    RoundingMode.HALF_EVEN
                                                )
                                            )
                                        /*STUtils.formatNumber(totalStepsValue.toInt())*/
                                    )
                                )
                            }
                    }
                } ?: kotlin.run {
                    endDateLayout.gone()
                    progressBarLayout.visible()
                    if (percentageValue == 100) {
                        stepsToGo.gone()
                    } else {
                        stepsToGo.visible()
                    }
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
                                    NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                            BigDecimal(totalStepsValue.toDouble()).setScale(
                                                0,
                                                RoundingMode.HALF_EVEN
                                            )
                                        )
                                    /*STUtils.formatNumber(totalStepsValue.toInt())*/
                                )
                            )
                        }
                        .append(resources.getString(R.string.case_1_reach_the_goal_3))
                }
                completedTitleText.text = challengeTitleString

                challengeCompletedDescription.gone()
                joinNow.text =
                    activityContext.resources.getString(R.string.join_another_challenge)
            }
        }
        val eventNameList: HashMap<String, String> = HashMap()
        challengeDetailsData.name?.let { challengeName ->
            eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_NAME] =
                challengeName
        }
        challengeDetailsData.id?.let { challengeId ->
            eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_ID] =
                challengeId
        }
        when {
            !BuildConfig.DEBUG -> {
                STFireBaseAnalytics.trackEventValue(
                    STFireBaseAnalyticsConstants.EVENT_PUBLIC_CHALLENGE_DETAIL_VISIT,
                    eventNameList
                )
            }
            else -> {
            }
        }
    }

    private fun setPcThreeChallenge(challengeDetailsData: STChallengesListData) {
        pcTwoLeaderBoardLayout.gone() //pc2
        publicChallengeTwoCellLayout.gone() //pc2
        publicChallengeGoalCellLayout.gone() // public

        pcThreeCellLayout.visible()
        pc3InfoLayout.visible()
        leaderBoardLayout.gone()

        challengeThemeType(
            activityContext.getString(R.string.pc3)/*STConstants.PC3*/,
            R.drawable.button_bg_orange_small,
            R.drawable.pc_three_icon
        )

        challengeDetailsData.participants?.let {
            pc3Participant.text = NumberFormat.getNumberInstance(Locale.US)
                .format(
                    BigDecimal(it.toDouble()).setScale(
                        0,
                        RoundingMode.HALF_EVEN
                    )
                )/*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
        } ?: kotlin.run {
            pc3Participant.text = "0"
        }

        challengeDetailsData.startDate?.let { startDate ->
            pc3StartDate.text = STUtils.getDate(
                startDate, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        } ?: kotlin.run {
            pc3StartDateLayout.gone()
        }

        challengeDetailsData.endDate?.let { endDate ->
            pc3EndDate.text = STUtils.getDate(
                endDate, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        } ?: kotlin.run {
            pc3EndDateLayout.gone()
        }

        challengeDetailsData.voucherDescription?.let { reward ->
            if (reward == "") {
                pc3RewardLayout.gone()
            } else {
                pc3RewardLayout.visible()
                pc3Gift.text = reward
            }
        } ?: kotlin.run {
            pc3RewardLayout.gone()
        }
        challengeDetailsData.targetSteps?.let {
            pc3ChallengeStatus.visible()
            val stepsValue = NumberFormat.getNumberInstance(Locale.US)
                .format(
                    BigDecimal(it.toDouble()).setScale(
                        0,
                        RoundingMode.HALF_EVEN
                    )
                )
            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
            val stepsValueFontUpdate = SpannableStringBuilder(stepsValue)
            val stepsString =
                SpannableStringBuilder(getString(R.string.steps_per_day))
            stepsValueFontUpdate.setSpan(
                STGothamRoundedBoldTypefaceSpan(activityContext, ""),
                0,
                stepsValue.length,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            stepsString.setSpan(
                STGothamRoundedMediumTypefaceSpan(activityContext, ""),
                0,
                4,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            val stepsText = SpannableStringBuilder()
            stepsText.bold { append(stepsValueFontUpdate) }
                .append(activityContext.getString(R.string.space)).append(stepsString)
            pc3GoalSteps.text = stepsText
            pc3GoalSteps.isSelected = true
        } ?: kotlin.run {
            pc3ChallengeStatus.gone()
        }

        totalStepsValue = challengeDetailsData.targetSteps?.toLong()!!
        challengeDetailsData.states?.let { statesData ->
            statesData.achievedSteps?.let {
                achievedStepsValue = it
            } ?: kotlin.run {
                achievedStepsValue = 0
            }
        }
//        val percentageValue: Int? =
//            if (((achievedStepsValue * 100.00) / totalStepsValue) < 100 &&
//                ((achievedStepsValue * 100.00) / totalStepsValue) > 99
//            ) {
//                99
//            } else {
//                if (totalStepsValue > 0) {
//                    if (achievedStepsValue > totalStepsValue) {
//                        100
//                    } else {
//                        ((achievedStepsValue * 100.00) / totalStepsValue).roundToInt()
//                    }
//                } else {
//                    0
//                }
//            }
//        pc3ProgressBar.progress = percentageValue!!
//        pc3ProgressPercentage.text = "$percentageValue%"

        challengeDetailsData.joined?.let {
            challengeJoined = it
        }

        if (isChallengeStarted && challengeJoined) {
            challengeDetailsData.daysLeft?.let {
                daysLeftLayout.visible()
                pc3DaysLeft.text = it.toString()
            } ?: kotlin.run {
                daysLeftLayout.gone()
            }

            challengeDetailsData.states?.averageDailySteps?.let {
                averageStepsLayout.visible()
                pc3AvgSteps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
                /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
            } ?: kotlin.run {
                averageStepsLayout.gone()
            }
            challengeDetailsData.challengeUsers?.let {
                leaderBoardLayout.visible()
                setLeaderBoardAdapter(it)
            } ?: kotlin.run {
                leaderBoardLayout.gone()
                noLeaderBoardData.visible()
            }
//            pc3ChallengeOngoingLayout.visible()

//            stepsToGoValue = totalStepsValue - achievedStepsValue
//            if (percentageValue == 100) {
//                pc3StepsToGo.gone()
//            } else {
//                pc3StepsToGo.visible()
//                pc3StepsToGo.text =
//                    "${NumberFormat.getNumberInstance(Locale.US)
//                        .format(stepsToGoValue)} ${activityContext.resources.getString(
//                        R.string.steps_to_go
//                    )}"
//            }
//            if (totalStepsValue == 0L) {
//                pc3ProgressBarLayout.gone()
//                pc3StepsToGo.gone()
//            } else {
//                pc3ProgressBarLayout.visible()
//            }
//
//            if (percentageValue == 100) {
//                pc3StepsToGo.gone()
//            } else {
//                pc3StepsToGo.visible()
//            }


        } else {
            leaderBoardLayout.gone()
//            pc3ChallengeOngoingLayout.visible()
        }

        if (challengeDetailsData.joined!!) {
            challengeDetailsData.isCompleted?.let {
                if (it) {
                    joinNow.visible()
                    isCompleted = true
                    joinNow.text = activityContext.getString(R.string.join_another_challenge)
                } else {
                    isCompleted = false
                    joinNow.gone()
                }
            } ?: kotlin.run {
                joinNow.gone()
            }
        } else {
            joinNow.visible()
            challengeDetailsData.isCompleted?.let {
                if (it) {
                    isCompleted = true
                    joinNow.text = activityContext.getString(R.string.join_another_challenge)
                } else {
                    isCompleted = false
                }
            } ?: kotlin.run {
            }
        }

        if (activityContext is STContainerActivity) {
            (activityContext as STContainerActivity)
                .manageMoreMenuPrivateChallengeVisibility(
                    challengeDetailsData.joined!!,
                    challengeDetailsData.isCompleted!!
                )
        }

    }

    private fun setPcTwoChallenge(challengeDetailsData: STChallengesListData) {
        pcThreeCellLayout.gone() //pc3
        leaderBoardLayout.gone() // public
        publicChallengeGoalCellLayout.gone() //public
        publicChallengeTwoCellLayout.visible()//public
        if (challengeDetailsData.type!! == STConstants.DUBAI_FITNESS_CHALLENGE) {
            challengeThemeType.gone()
            pc2ViewAllList.visible()
        } else {
            challengeThemeType.visible()
            pc2ViewAllList.gone()
        }
        challengeThemeType(
            activityContext.getString(R.string.pc2)/*STConstants.PC2*/,
            R.drawable.button_bg_light_blue_small,
            R.drawable.private_challenge_icon
        )
        if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
            if (!challengeDetailsData.showParticipants!!) {
                participantsTwoLayout.gone()
            } else {
                participantsTwoLayout.visible()
            }
        }
        challengeDetailsData.participants?.let {
            pcTwoParticipants.text = it
        } ?: kotlin.run {
            pcTwoParticipants.text = "0"
        }
        challengeDetailsData.teams?.let {
            pcTwoTeams.text = it
        } ?: kotlin.run {
            pcTwoTeams.text = "0"
        }
        challengeDetailsData.startDate?.let { startDate ->
            pcTwoStartDate.text = STUtils.getDate(
                startDate, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        }
        challengeDetailsData.endDate?.let { endDate ->
            pcTwoEndDate.text = STUtils.getDate(
                endDate, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        }
        if (!challengeDetailsData.joined!!) {
            pcTwoMyTeamLayout.gone()
        } else {
            challengeDetailsData.participantTeams?.let {
                if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                    challengeDetailsData.myTeam?.let { myTeam ->
                        pcTwoMyTeamLayout.visible()
                        pcTwoMyTeamLayout.setOnClickListener {
                            openDFCTeamDetail(
                                myTeam,
                                myTeam.teamRank!!,
                                challengeDetailsData.id,
                                challengeDetailsData
                            )
                        }

                        if (myTeam.engagementRate!! == 0.0) {
                            pcTwoMyTeamWinLabel.gone()
                            pcTwoMyTeamPosition.visible()
                        } else {
                            when (myTeam.teamRank) {
                                1 -> {
                                    pcTwoMyTeamWinLabel.visible()
                                    pcTwoMyTeamPosition.gone()
                                    pcTwoMyTeamWinLabel.setImageResource(R.drawable.win_label_one)
                                }
                                2 -> {
                                    pcTwoMyTeamWinLabel.visible()
                                    pcTwoMyTeamPosition.gone()
                                    pcTwoMyTeamWinLabel.setImageResource(R.drawable.win_label_two)
                                }
                                3 -> {
                                    pcTwoMyTeamWinLabel.visible()
                                    pcTwoMyTeamPosition.gone()
                                    pcTwoMyTeamWinLabel.setImageResource(R.drawable.win_label_three)
                                }
                                else -> {
                                    pcTwoMyTeamWinLabel.gone()
                                    pcTwoMyTeamPosition.visible()
                                }
                            }
                        }
                        pcTwoMyTeamPosition.text = myTeam.teamRank.toString()
                        pcTwoMyTeamName.text = myTeam.name
                        if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                            if (!challengeDetailsData.showParticipants!!) {
                                pcTwoMyTeamMembersCountLayout.gone()
                            } else {
                                pcTwoMyTeamMembersCountLayout.visible()
                            }
                        }
                        myTeam.participants?.let { myTeamParticipants ->
                            pcTwoMyTeamMembersCount.text = myTeamParticipants
                        } ?: kotlin.run {
                            pcTwoMyTeamMembersCount.text = getString(R.string.zero)
                        }
                        myTeam.engagementRate.let { myTeamTotalScore ->
                            pcTwoMyTeamTotalScore.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                    .format(
                                        BigDecimal(myTeamTotalScore).setScale(
                                            0,
                                            RoundingMode.HALF_EVEN
                                        )
                                    )
                        } ?: kotlin.run {
                            pcTwoMyTeamTotalScore.text = getString(R.string.zero)
                        }
                    } ?: kotlin.run {
                        pcTwoMyTeamLayout.gone()
                    }
                } else {
                    for (i in it.indices) {
                        if (it[i].myTeam) {
                            pcTwoMyTeamLayout.visible()
                            val myTeam = it[i]


                            pcTwoMyTeamLayout.setOnClickListener {
                                if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                                    openDFCTeamDetail(
                                        myTeam,
                                        i + 1,
                                        challengeDetailsData.id,
                                        challengeDetailsData
                                    )
                                } else {
                                    openTeamDetail(myTeam, i + 1, challengeDetailsData.id)
                                }
                            }
                            if (myTeam.totalScore!! == "0") {
                                pcTwoMyTeamWinLabel.gone()
                                pcTwoMyTeamPosition.visible()
                            } else {
                                when (i) {
                                    0 -> {
                                        pcTwoMyTeamWinLabel.visible()
                                        pcTwoMyTeamPosition.gone()
                                        pcTwoMyTeamWinLabel.setImageResource(R.drawable.win_label_one)
                                    }
                                    1 -> {
                                        pcTwoMyTeamWinLabel.visible()
                                        pcTwoMyTeamPosition.gone()
                                        pcTwoMyTeamWinLabel.setImageResource(R.drawable.win_label_two)
                                    }
                                    2 -> {
                                        pcTwoMyTeamWinLabel.visible()
                                        pcTwoMyTeamPosition.gone()
                                        pcTwoMyTeamWinLabel.setImageResource(R.drawable.win_label_three)
                                    }
                                    else -> {
                                        pcTwoMyTeamWinLabel.gone()
                                        pcTwoMyTeamPosition.visible()
                                    }
                                }
                            }
                            pcTwoMyTeamPosition.text = (i + 1).toString()
                            pcTwoMyTeamName.text = myTeam.name
                            myTeam.participants?.let { myTeamParticipants ->
                                pcTwoMyTeamMembersCount.text = myTeamParticipants
                            } ?: kotlin.run {
                                pcTwoMyTeamMembersCount.text = getString(R.string.zero)
                            }
                            if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                                myTeam.engagementRate?.let { myTeamTotalScore ->
                                    pcTwoMyTeamTotalScore.text =
                                        NumberFormat.getNumberInstance(Locale.US)
                                            .format(
                                                BigDecimal(myTeamTotalScore).setScale(
                                                    0,
                                                    RoundingMode.HALF_EVEN
                                                )
                                            )
                                } ?: kotlin.run {
                                    pcTwoMyTeamTotalScore.text = getString(R.string.zero)
                                }
                            } else {
                                myTeam.totalScore.let { myTeamTotalScore ->
                                    pcTwoMyTeamTotalScore.text =
                                        NumberFormat.getNumberInstance(Locale.US)
                                            .format(
                                                BigDecimal(myTeamTotalScore.toDouble()).setScale(
                                                    0,
                                                    RoundingMode.HALF_EVEN
                                                )
                                            )
                                    /*STUtils.formatNumber(myTeamTotalScore.toInt())*/
                                } ?: kotlin.run {
                                    pcTwoMyTeamTotalScore.text = getString(R.string.zero)
                                }
                            }
                        }
                    }
                }
                setParticipantAdapter(it, challengeDetailsData)
            } ?: kotlin.run {
                pcTwoMyTeamLayout.gone()
            }
        }
        if (isChallengeStarted) {
            if (challengeDetailsData.type != STConstants.DUBAI_FITNESS_CHALLENGE) {
                challengeDetailsData.mvps?.let { mvpParticipant ->
                    if (mvpParticipant.isNotEmpty()) {
                        mvpParticipant[0].let { mvpTeamsList ->
                            mvpTeamsList.user?.let { mvpUser ->
                                if (mvpUser.steps == "0") {
                                    pcTwoMvpParticipant.gone()
                                } else {
                                    pcTwoMvpParticipant.visible()
                                    mvpUser.picture?.let { picture ->
                                        pcTwoMVPUserImage.visible()
                                        pcTwoMVPUserImageManRunning.gone()
                                        pcTwoMVPUserImage.load(activityContext, picture)
                                    } ?: kotlin.run {
                                        pcTwoMVPUserImage.gone()
                                        pcTwoMVPUserImageManRunning.visible()
                                    }
                                    pcTwoMVPUserName.text = mvpUser.name
                                    mvpUser.steps?.let {
                                        pcTwoMVPSteps.text =
                                            NumberFormat.getNumberInstance(Locale.US)
                                                .format(
                                                    BigDecimal(it.toDouble()).setScale(
                                                        0,
                                                        RoundingMode.HALF_EVEN
                                                    )
                                                )
                                        /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
                                    } ?: kotlin.run {
                                        pcTwoMvpParticipant.gone()
                                    }
                                }
                            } ?: kotlin.run {
                                pcTwoMvpParticipant.gone()
                            }
                            mvpTeamsList.cheered?.let { isCheered ->
                                if (!isCheered) {
                                    isMVPCheered = false
                                    mvpTeamsList.cheerReceived?.let { cheerReceived ->
                                        if (cheerReceived == "0") {
                                            pcTwoMVPCheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                                            pcTwoMVPCheerCount.text =
                                                activityContext.resources.getString(R.string.cheer_count_label)
                                        } else {
                                            pcTwoMVPCheers.setImageResource(R.drawable.cheer_with_count_disabled)
                                            pcTwoMVPCheerCount.text = cheerReceived
                                        }
                                    }
                                    pcTwoMVPCheerCount.textColor(
                                        STUtils.getColor(
                                            activityContext,
                                            R.color.edit_text_bg_stroke_color
                                        )
                                    )
                                } else {
                                    isMVPCheered = true
                                    pcTwoMVPCheers.setImageResource(R.drawable.cheer_with_count_enabled)
                                    pcTwoMVPCheerCount.text = mvpTeamsList.cheerReceived
                                    pcTwoMVPCheerCount.textColor(
                                        STUtils.getColor(
                                            activityContext,
                                            R.color.button_bg_enabled_color
                                        )
                                    )
                                }
                            }
                            pcTwoMVPCheerLayout.setOnClickListener {
                                if (!isMVPCheered!!) {
                                    pcTwoMVPCheers.setImageResource(R.drawable.cheer_with_count_enabled)
                                    pcTwoMVPCheerCount.text =
                                        (winnerTeamCheerReceived!!.toInt() + 1).toString()
                                    pcTwoMVPCheerCount.textColor(
                                        STUtils.getColor(
                                            activityContext,
                                            R.color.button_bg_enabled_color
                                        )
                                    )
                                    invokeIntent(
                                        STChallengesIntent.CheerChallengeUser(
                                            challengesId!!,
                                            mvpTeamsList.id!!
                                        )
                                    )
                                }
                            }
                        }
                        pcTwoMvpParticipant.setOnClickListener {
                            onMvpParticipantClick(mvpParticipant as ArrayList<STMVPTeamList>)
                        }
                    }
                } ?: kotlin.run {
                    pcTwoMvpParticipant.gone()
                }
            } else {
                pcTwoMvpParticipant.gone()
            }
        } else {
            pcTwoMvpParticipant.gone()
        }
        pcTwoLeaderBoardLayout.visible()
        if (isChallengeStarted &&
            challengeDetailsData.theme != STConstants.CHALLENGE_THEME_NONE /*&&
            challengeDetailsData.type == STConstants.STEPPI_CHALLENGE_2*/
        ) {
            if (STConstants.UPDATE_MY_CHALLENGE_LIST) {
                testCallback?.invoke()
            }
        }
        challengeDetailsData.isCompleted?.let { isCompleted ->
            if (isCompleted) {
                pcTwoWinnerLayout.visible()
                challengeDetailsData.participantTeams?.let { participantTeams ->
                    val winnerTeam = participantTeams[0]
                    pcTwoWinnerTeamName.text = winnerTeam.name
                    if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                        if (!challengeDetailsData.showParticipants!!) {
                            pcTwoWinnerTeamMembersCountLayout.gone()
                        } else {
                            pcTwoWinnerTeamMembersCountLayout.visible()
                        }
                    }
                    winnerTeam.participants?.let { participants ->
                        pcTwoWinnerTeamMembers.text = participants
                    } ?: kotlin.run {
                        pcTwoWinnerTeamMembers.text = getString(R.string.zero)
                    }

                    if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                        winnerTeam.engagementRate?.let { totalScore ->
                            if (totalScore == 0.0) {
                                pcTwoWinnerLayout.gone()
                            } else {
                                pcTwoWinnerTeamTotalScore.text =
                                    NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                            BigDecimal(totalScore).setScale(
                                                0, RoundingMode.HALF_EVEN
                                            )
                                        )
                            }
                        } ?: kotlin.run {
                            pcTwoWinnerTeamTotalScore.text = getString(R.string.zero)
                            pcTwoWinnerLayout.gone()
                        }
                    } else {
                        winnerTeam.totalScore?.let { totalScore ->
                            if (totalScore == "0") {
                                pcTwoWinnerLayout.gone()
                            } else {
                                pcTwoWinnerTeamTotalScore.text =
                                    NumberFormat.getNumberInstance(Locale.US)
                                        .format(
                                            BigDecimal(totalScore.toDouble()).setScale(
                                                0,
                                                RoundingMode.HALF_EVEN
                                            )
                                        )
                            }
                        } ?: kotlin.run {
                            pcTwoWinnerTeamTotalScore.text = getString(R.string.zero)
                            pcTwoWinnerLayout.gone()
                        }
                    }
                }
                joinNow.text = activityContext.getString(R.string.join_another_challenge)
                joinNow.visible()
            } else {
                pcTwoWinnerLayout.gone()
            }
        } ?: kotlin.run {
            pcTwoWinnerLayout.gone()
        }
        if (challengeDetailsData.type!! != STConstants.DUBAI_FITNESS_CHALLENGE) {
            if (activityContext is STContainerActivity) {
                (activityContext as STContainerActivity)
                    .manageMoreMenuPrivateChallengeVisibility(
                        challengeDetailsData.joined!!,
                        challengeDetailsData.isCompleted!!
                    )
            }
        }
        if (challengeDetailsData.joined!!) {
            challengeDetailsData.isCompleted?.let {
                if (it) {
                    joinNow.visible()
                    isCompleted = true
                    joinNow.text = activityContext.getString(R.string.join_another_challenge)
                } else {
                    isCompleted = false
                    joinNow.gone()
                }
            } ?: kotlin.run {
                joinNow.gone()
            }
        } else {
            joinNow.visible()
            pcTwoMvpParticipant.gone()
            pcTwoLeaderBoardLayout.gone()
            pcTwoWinnerLayout.gone()
            challengeDetailsData.isCompleted?.let {
                if (it) {
                    isCompleted = true
                    joinNow.text = activityContext.getString(R.string.join_another_challenge)
                } else {
                    isCompleted = false
                }
            } ?: kotlin.run {
            }
        }
        if (!isChallengeStarted || isCompleted!!) {
            pcTwoMVPCheerLayout.gone()
        }
    }

    private fun openDFCTeamDetail(
        participantTeam: STParticipantTeams?,
        position: Int,
        challengeId: String?,
        challengeDetailsData: STChallengesListData
    ) {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, participantTeam?.name)
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_MY_TEAM_LEADER_BOARD_DFC
            )
            container.putExtra(STConstants.PARTICIPANT_TEAM, participantTeam)
            container.putExtra(STConstants.PARTICIPANT_TEAM_ID, participantTeam?.id)
            container.putExtra(STConstants.PARTICIPANT_TEAM_RANK_POSITION, position)
            container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
            container.putExtra(STConstants.IS_CHALLENGE_COMPLETED, isCompleted)
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
            container.putExtra(STConstants.CHALLENGES_END_DATE, challengeDetails?.endDate)
            container.putExtra(STConstants.CHALLENGES_START_DATE, challengeDetails?.startDate)
            container.putExtra(STConstants.CHALLENGES_DATA, challengeDetailsData)
            startActivity(container)
        }
    }

    private fun openTeamDetail(
        participantTeam: STParticipantTeams?,
        position: Int,
        challengeId: String?
    ) {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, participantTeam?.name)
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_MY_TEAM_LEADERBOARD
            )
            container.putExtra(STConstants.PARTICIPANT_TEAM, participantTeam)
            container.putExtra(STConstants.PARTICIPANT_TEAM_ID, participantTeam?.id)
            container.putExtra(STConstants.PARTICIPANT_TEAM_RANK_POSITION, position)
            container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
            container.putExtra(STConstants.IS_CHALLENGE_COMPLETED, isCompleted)
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
            container.putExtra(STConstants.CHALLENGES_END_DATE, challengeDetails?.endDate)
            container.putExtra(STConstants.CHALLENGES_START_DATE, challengeDetails?.startDate)
            startActivity(container)
        }
    }

    fun challengeThemeType(
        themeTypeText: String?,
        backGroundDrawable: Int,
        drawableIcon: Int
    ) {
        challengeThemeType.text = themeTypeText
        challengeThemeType.setBackgroundResource(backGroundDrawable)
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                drawableIcon,
                0
            )
        } else {
            challengeThemeType.setCompoundDrawablesWithIntrinsicBounds(
                drawableIcon,
                0,
                0,
                0
            )
        }
    }

    private fun setPrivateChallenge(challengeDetailsData: STChallengesListData) {
        challengeThemeType(
            activityContext.getString(R.string.private_label)/*STConstants.PRIVATE*/,
            R.drawable.button_bg_enabled_small,
            R.drawable.private_challenge_icon
        )
        publicChallengeTwoCellLayout.gone() //pc2
        pcTwoLeaderBoardLayout.gone() //pc2
        pcThreeCellLayout.gone() //pc3
        publicChallengeGoalCellLayout.gone() // public

        privateChallengeCellLayout.visible()
        privateChallengeCodeAdminLayout.visible()

        challengeDetailsData.costSteps?.let { costSteps ->
            privateChallengeCost.text =
                "${
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(costSteps.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )/*NumberFormat.getNumberInstance(Locale.US).format(costSteps)*/
                } ${
                    getString(
                        R.string.steps
                    )
                }"
            challengeDetailsData.participants?.let { participants ->
                val totalRewards = costSteps * participants.toInt()
                privateChallengeTotalRewards.text =
                    "${
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(totalRewards.toDouble()).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            )/*NumberFormat.getNumberInstance(Locale.US).format(totalRewards)*/
                    } ${
                        getString(
                            R.string.steps
                        )
                    }"
            } ?: kotlin.run {
                privateChallengeTotalRewards.text = "0  ${
                    getString(
                        R.string.step
                    )
                }"
            }
        }
        challengeDetailsData.participants?.let {
            privateChallengeParticipants.text = NumberFormat.getNumberInstance(Locale.US)
                .format(
                    BigDecimal(it.toDouble()).setScale(
                        0,
                        RoundingMode.HALF_EVEN
                    )
                )
            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
        }
//                privateChallengeTotalRewards.text = challengeDetailsData.totalSteps
        challengeDetailsData.startDate?.let { startDateValue ->
            privateChallengeStartDate.text = STUtils.getDate(
                startDateValue, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        }
        challengeDetailsData.endDate?.let { endDateValue ->
            privateChallengeEndDate.text = STUtils.getDate(
                endDateValue, "yyyy-MM-dd", "dd-MMM-yyyy"
            )
        }
        challengeDetailsData.joinCode?.let {
            if (it == "") {
                joinCodeLayout.gone()
            } else {
                joinCodeLayout.visible()
                challengeCodeText.text = it
            }
        } ?: kotlin.run {
            joinCodeLayout.gone()
        }
        challengeDetailsData.joined?.let { isChallengeJoined ->
            if (!isChallengeStarted && isChallengeJoined) {
                adminSteps.gone()
            } else {
                adminSteps.visible()
            }
        }
        challengeDetailsData.admin?.let {
            if (it.userId == STPreference.getUserId(activityContext)) {
                joinCodeLayout.visible()
            } else {
                joinCodeLayout.gone()
            }
            it.picture?.let { picture ->
                adminProfileImage.load(activityContext, picture)
                adminImageManRunning.gone()
                adminProfileImage.visible()
            } ?: kotlin.run {
                adminImageManRunning.visible()
                adminProfileImage.gone()
            }
            adminMemberName.text = it.name
            it.totalSteps?.let { totalAdminSteps ->
                adminSteps.text =
                    "${
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(totalAdminSteps.toDouble()).setScale(
                                    0,
                                    RoundingMode.HALF_EVEN
                                )
                            )/*NumberFormat.getNumberInstance(Locale.US)
                        .format(totalAdminSteps.toInt())*/
                    } ${
                        getString(
                            R.string.steps
                        )
                    }"
            }
        } ?: kotlin.run {
            adminLayout.gone()
        }
        if (isChallengeStarted) {
            privateChallengeStartDateLayout.gone()
            privateChallengeEndDateLayout.visible()
        } else {
            privateChallengeStartDateLayout.visible()
            privateChallengeEndDateLayout.gone()
        }
        challengeDetailsData.isCompleted?.let { isChallengeCompleted ->
            if (isChallengeCompleted) {
                isCompleted = true
                privateChallengeStartDateLayout.visible()
                privateChallengeEndDateLayout.visible()
                privateChallengeWinnerLayout.visible()
                winnerCheerLayout.gone()
                challengeDetailsData.challengeUsers?.let { challengeUsers ->
                    challengeUsers[0].let { winnerTeam ->
                        winnerTeamData = winnerTeam
                        winnerTeam.name?.let {
                            userWinnerName.text = it
                        }
                        winnerTeam.picture?.let {
                            userImageWinner.load(activityContext, it)
                            userImageManRunningWinner.gone()
                            userImageWinner.visible()
                        } ?: kotlin.run {
                            userImageManRunningWinner.visible()
                            userImageWinner.gone()
                        }
                        winnerTeam.totalSteps?.let {
                            if (it == "0") {
                                privateChallengeWinnerLayout.gone()
                            } else {
                                privateChallengeWinnerLayout.visible()
                            }
                            winnerSteps.text = NumberFormat.getNumberInstance(Locale.US)
                                .format(
                                    BigDecimal(it.toDouble()).setScale(
                                        0,
                                        RoundingMode.HALF_EVEN
                                    )
                                )
                            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
                        } ?: kotlin.run {
                            privateChallengeWinnerLayout.gone()
                        }
                        winnerTeam.cheered?.let { isCheered ->
                            if (!isCheered) {
                                isWinnerCheered = false
                                winnerTeam.cheerReceived?.let { cheerReceived ->
                                    if (cheerReceived == "0") {
                                        winnerCheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                                        winnerCheerCount.text =
                                            activityContext.resources.getString(R.string.cheer_count_label)
                                    } else {
                                        winnerCheers.setImageResource(R.drawable.cheer_with_count_disabled)
                                        winnerCheerCount.text = cheerReceived
                                    }
                                    winnerTeamCheerReceived = cheerReceived
                                }
                                winnerCheerCount.textColor(
                                    STUtils.getColor(
                                        activityContext,
                                        R.color.edit_text_bg_stroke_color
                                    )
                                )
                            } else {
                                isWinnerCheered = true
                                winnerCheers.setImageResource(R.drawable.cheer_with_count_enabled)
                                winnerCheerCount.text = winnerTeam.cheerReceived
                                winnerCheerCount.textColor(
                                    STUtils.getColor(
                                        activityContext,
                                        R.color.button_bg_enabled_color
                                    )
                                )
                            }
                        }
                    }
                }
            } else {
                isCompleted = false
            }
        }
        if (challengeDetailsData.joined!!) {
            if (isChallengeStarted) {
                if (challengeDetailsData.participants?.toInt()!! > 0) {
//                    leaderBoardLayout.visible()
                    challengeDetailsData.challengeUsers?.let {
                        leaderBoardLayout.visible()
                    } ?: kotlin.run {
                        leaderBoardLayout.gone()
                    }
                }
            } else {
                challengeDetailsData.admin?.let {
                    if (it.userId == STPreference.getUserId(activityContext)) {
                        challengeDetailsData.challengeUsers?.let {
                            leaderBoardLayout.visible()
                        } ?: kotlin.run {
                            leaderBoardLayout.gone()
                        }
                    } else {
                        leaderBoardLayout.gone()
                    }
                } ?: kotlin.run {
                    leaderBoardLayout.gone()
                }
            }
            if (isChallengeStarted &&
                challengeDetailsData.theme == STConstants.CHALLENGE_THEME_1
            ) {
                if (STConstants.UPDATE_MY_CHALLENGE_LIST) {
                    testCallback?.invoke()
                }
            }
            if (isUpcomingChallenge) {
                challengeDetailsData.admin?.let {
                    if (it.userId != STPreference.getUserId(activityContext)) {
                        if (activityContext is STContainerActivity) {
                            (activityContext as STContainerActivity)
                                .manageMoreMenuPrivateChallengeVisibility(
                                    challengeDetailsData.joined!!,
                                    challengeDetailsData.isCompleted!!
                                )
                        }
                    }
                } ?: kotlin.run {
                    if (activityContext is STContainerActivity) {
                        (activityContext as STContainerActivity)
                            .manageMoreMenuPrivateChallengeVisibility(
                                challengeDetailsData.joined!!,
                                challengeDetailsData.isCompleted!!
                            )
                    }
                }
            }
            joinNow.gone()
            challengeDetailsData.isCompleted?.let {
                if (it) {
                    isCompleted = true
                    joinNow.visible()
                    joinNow.text = activityContext.getString(R.string.join_another_challenge)
                } else {
                    isCompleted = false
                    joinNow.gone()
                }
            } ?: kotlin.run {
                joinNow.gone()
            }
        } else {
            challengeDetailsData.admin?.let {
                if (it.userId == STPreference.getUserId(activityContext)) {
                    leaderBoardLayout.visible()
                } else {
                    leaderBoardLayout.gone()
                }
            } ?: kotlin.run {
                leaderBoardLayout.gone()
            }
            joinNow.visible()
        }
        challengeDetailsData.privateChallengeLink?.let {
            privateChallengeLink = it
        }
    }

    private fun onMvpParticipantClick(mvpParticipant: ArrayList<STMVPTeamList>) {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.mvp_participant))
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_MVP_PARTICIPANT
            )
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengesId)
            container.putExtra(STConstants.MVP_PARTICIPANT, mvpParticipant)
            container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
            container.putExtra(STConstants.IS_CHALLENGE_COMPLETED, isCompleted)
            startActivity(container)
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

    private fun setParticipantAdapter(
        participantTeamsList: List<STParticipantTeams>,
        challengeDetailsData: STChallengesListData
    ) {
        participantTeams = participantTeamsList
        participantTeams?.let {
            noTeamListData.gone()
            teamList.setVerticalLayoutManager()
            teamList.setVerticalItemDecoration(
                activityContext.resources.getDimension(R.dimen.margin_small).toInt(),
                0
            )
            leaderBoardListPC2Adapter = STLeaderBoardListPC2Adapter(activityContext)
            leaderBoardListPC2Adapter?.setLeaderBoardLists(participantTeams as ArrayList<STParticipantTeams>)
            leaderBoardListPC2Adapter?.isChallengeStarted(isChallengeStarted)
            leaderBoardListPC2Adapter?.setType(challengeDetailsData.type)
            if (challengeDetailsData.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                leaderBoardListPC2Adapter?.showParticipants(challengeDetailsData.showParticipants)
            }
            teamList.adapter = leaderBoardListPC2Adapter
        } ?: kotlin.run {
            noTeamListData.visible()
        }
    }

    private fun setLeaderBoardAdapter(leaderBoardItemsListData: List<STLeaderBoardChallengeUsers>) {
        leaderBoardItemsList = leaderBoardItemsListData
        leaderBoardItemsList?.let {
            noLeaderBoardData.gone()
            leaderBoardList.layoutManager =
                LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
            leaderBoardListAdapter =
                STLeaderBoardListAdapter(
                    activityContext
                )
            leaderBoardListAdapter!!.setClickListener(object :
                STLeaderBoardListAdapter.OnItemClickListener {
                override fun onItemClick(leaderBoardUser: STLeaderBoardChallengeUsers?) {
                    if (challengeDetails?.type == STConstants.STEPPI_CHALLENGE_3) {
                        if (leaderBoardUser?.userId != null && leaderBoardUser.userId == STPreference.getUserId(
                                activityContext
                            )
                        ) {
                            val container = Intent(activityContext, STContainerActivity::class.java)
                            container.putExtra(
                                STConstants.FRAGMENT_NAME,
                                activityContext.resources.getString(R.string.daily_steps)
                            )
                            container.putExtra(
                                STConstants.FRAGMENT_ID,
                                STConstants.FRAGMENT_ID_DAILY_STEP
                            )
                            container.putExtra(STConstants.RANK, challengeDetails?.myUser?.rank)
                            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeDetails?.id)
                            container.putExtra(
                                STConstants.CHALLENGES_END_DATE,
                                challengeDetails?.endDate
                            )
                            container.putExtra(
                                STConstants.CHALLENGES_START_DATE,
                                challengeDetails?.startDate
                            )
                            container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
                            container.putExtra(STConstants.CHALLENGES_TYPE, challengeDetails?.type)
                            container.putExtra(
                                STConstants.IS_CHALLENGE_COMPLETED,
                                challengeDetails?.isCompleted
                            )
                            startActivity(container)
                        }
                    }
                }

                override fun onCheerClick(
                    leaderBoardUser: STLeaderBoardChallengeUsers?,
                    position: Int
                ) {
                    cheerClickPosition = position
                    invokeIntent(
                        STChallengesIntent.CheerChallengeUser(
                            challengesId!!,
                            leaderBoardUser?.id!!
                        )
                    )
                    myUserData?.let {
                        if (it.rank!!.toInt() == position.plus(1)) {
                            it.cheerReceived = (it.cheerReceived!!.toInt() + 1).toString()
                            it.cheered = true
                            setMyUserLayout(myUserData)
                        }
                    }
//                }
                }

            })
            leaderBoardList.adapter = leaderBoardListAdapter
            leaderBoardListAdapter?.setLeaderboardLists(leaderBoardItemsList as ArrayList<STLeaderBoardChallengeUsers>)
            leaderBoardListAdapter?.isChallengeStarted(isChallengeStarted)
            leaderBoardListAdapter?.isChallengeCompleted(isCompleted)
            leaderBoardListAdapter?.setPC3Data(
                challengeDetails?.type == STConstants.STEPPI_CHALLENGE_3,
                challengeDetails?.challengeDays
            )
            leaderBoardListAdapter?.isPrivate(challengeDetails?.isPrivate ?: false)
            challengeDetails?.participants?.let {
                if (it.toInt() <= 10) {
                    viewAllList.gone()
                } else {
                    viewAllList.visible()
                }
            } ?: kotlin.run {
                viewAllList.gone()
            }
        } ?: kotlin.run {
//            noLeaderBoardData.visible()
        }
    }

    @OnClick(R.id.myUserLayout)
    fun myUserLayout() {
        if (challengeDetails?.type == STConstants.STEPPI_CHALLENGE_3) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(
                STConstants.FRAGMENT_NAME,
                activityContext.resources.getString(R.string.daily_steps)
            )
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_DAILY_STEP
            )
            container.putExtra(STConstants.RANK, challengeDetails?.myUser?.rank)
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeDetails?.id)
            container.putExtra(
                STConstants.CHALLENGES_END_DATE,
                challengeDetails?.endDate
            )
            container.putExtra(
                STConstants.CHALLENGES_START_DATE,
                challengeDetails?.startDate
            )
            container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
            container.putExtra(
                STConstants.IS_CHALLENGE_COMPLETED,
                challengeDetails?.isCompleted
            )
            startActivity(container)
        }
    }

    private fun initMyChallengesList() {
//        participants.layoutManager =
//            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
//        participantAdapter = STParticipantsListAdapter(activityContext)
//        participants.adapter = participantAdapter
//        participantAdapter?.setClickListener(object :
//            STParticipantsListAdapter.OnItemClickListener {
//            override fun onItemClick() {
////                val container = Intent(activityContext, STContainerActivity::class.java)
////                container.putExtra(STConstants.FRAGMENT_NAME, "Nike")
////                container.putExtra(
////                    STConstants.FRAGMENT_ID,
////                    STConstants.FRAGMENT_ID_REWARDS_DETAILS
////                )
////                startActivity(container)
//            }
//        })
    }

//    @OnClick(R.id.challengeStatus)
//    fun challengeStatus() {
//        val container = Intent(activityContext, STContainerActivity::class.java)
//        container.putExtra(STConstants.FRAGMENT_NAME, "Challenge Status")
//        container.putExtra(
//            STConstants.FRAGMENT_ID,
//            STConstants.FRAGMENT_ID_CHALLENGE_STATUS
//        )
//        startActivity(container)
//    }

    @OnClick(R.id.joinNow)
    fun joinNow() {
        if (challengeIsPrivate!! && !challengeDetails?.joined!! && !isUpcomingChallenge) {
            val privateChallengeText: String? = if (challengeDetails?.isCompleted!!) {
                getString(R.string.challenge_started_text).replace(
                    "####",
                    getString(R.string.finished)
                )
            } else {
                getString(R.string.challenge_started_text).replace(
                    "####",
                    getString(R.string.started)
                )
            }
            showToast(privateChallengeText)
        } else if (challengeDetails?.type == STConstants.STEPPI_CHALLENGE_2 && !challengeDetails?.joined!!) {
            if (isCompleted!!) {
                activityContext.finish()
            } else joinDFCChallengeDialog()

        } else if (challengeDetails?.type == STConstants.DUBAI_FITNESS_CHALLENGE && !challengeDetails?.joined!!) {
            if (isCompleted!!) {
                activityContext.finish()
            } else joinDFCChallengeDialog()

        } else {                                                                    //challengeDetails?.type == STConstants.STEPPI_CHALLENGE_1
            if (challengeCompletedLayout.visibility == View.VISIBLE) {
//            joinAnotherChallengeDialog()
                activityContext.finish()
            } else {
                if (joinNow.text == activityContext.getString(R.string.join_another_challenge)) {
                    activityContext.finish()
                } else {
                    challengeDetails?.let { challengeDetailsData ->
                        if (challengeDetailsData.joined!!) leaveChallengeDialog()
                        else {
                            if (challengeDetails?.publicJoinCode!!) joinPublicWithCodeChallengeDialog()
                            else joinChallengeDialog()
                        }
                    }
                }
            }
        }
        //joinChallengeDialog()
        //joinAnotherChallengeDialog()
        //openTeamDetail()
        //leaveChallengeDialog()
    }

    private fun joinDFCChallengeDialog() {
        dfcDialog = showJoinDFCChallengeDialog {
            clickToAgree()
            dialogOK {
                if (isPleaseAgree()) {
                    joinLeaveChallenge(getChallengeJoinCode()!!)
//                    dismissDialog()
                }
            }
        }
        dfcDialog?.setSize(activityContext)
        dfcDialog?.show()
    }


    /*



    todo: join public challenge (target,goal)  */

    private fun joinPublicWithCodeChallengeDialog() {
        publicJoinDialog = showJoinDFCChallengeDialog {
            clickToAgree()
            dialogOK {
                if (isPleaseAgree()) {
                    joinPublicChallenge(getChallengeJoinCode()!!)
//                    dismissDialog()
                }
            }
        }
        publicJoinDialog?.setSize(activityContext)
        publicJoinDialog?.show()
    }

    private fun joinPublicChallenge(challengeJoinCode: String) {
        challengeDetails?.let {
            //Public Chanllenge with code
            Log.d("TAG", "joinPublicChallenge: ")
            initRetrofit(challengeJoinCode)

        }
    }

    private fun initRetrofit(challengeJoinCode: String) {
        if (STUtils.isInternetOn(activityContext)) {         //network On

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL.plus(STAPIConstants.API_VERSION))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            requestDidStart()
            CoroutineScope(Dispatchers.IO).launch {
                checkJoinCode(retrofit, challengeJoinCode)
            }

        } else showToast(resources.getString(R.string.no_internet))  //no network
    }

    private suspend fun checkJoinCode(retrofit: Retrofit, challengeJoinCode: String) {

        challengeDetails?.id?.let { id ->
            STPreference.getAccessToken(activityContext)?.let {
                val stApiServicesClass = retrofit.create(STAPIServices::class.java)
                val call: Call<STChallengeDetailsResponse> =
                    stApiServicesClass.joinPublicChallenge(it, id, challengeJoinCode)

                withContext(Dispatchers.IO) {
                    joinPublicChallengeCodeApi(call)
                }
            }
        }
    }

    private fun joinPublicChallengeCodeApi(call: Call<STChallengeDetailsResponse>) {

        call.enqueue(object : Callback<STChallengeDetailsResponse> {

            override fun onResponse(
                call: Call<STChallengeDetailsResponse>,
                response: Response<STChallengeDetailsResponse>
            ) {

                if (response.isSuccessful) {
                    dismissPublicChallengeDialog()

//                        response.body()?.data.let {
//                            challengeDetails = it
//                        }

                    CoroutineScope(Dispatchers.Main).launch {
                        performUiUpdate(response)
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        requestDidFinish()
                        dismissPublicChallengeDialog()
                        showToast("Invalid Code" + response.message())
                    }
                }
            }

            override fun onFailure(call: Call<STChallengeDetailsResponse>, t: Throwable) {
                CoroutineScope(Dispatchers.Main).launch {
                    requestDidFinish()
                    dismissPublicChallengeDialog()
                    showToast("Joining Failed" + t.message)
                }
            }
        })
    }

    private fun performUiUpdate(response: Response<STChallengeDetailsResponse>) {

        STConstants.UPDATE_MY_CHALLENGE_LIST = true
        STConstants.UPDATE_STEPPI_CHALLENGE_LIST = true
        requestDidFinish()

        response.body()?.let {
            val challengeResponse: STChallengeDetailsResponse = it

            challengeResponse?.data?.let {
                if (!it.joined!! && (it.type == STConstants.STEPPI_CHALLENGE_2 || it.type == STConstants.STEPPI_CHALLENGE_3)) {
                    activityContext.finish()
                } else {
                    if (challengeDetails?.type == STConstants.DUBAI_FITNESS_CHALLENGE) {
                        dfcDialog?.let { dfcDialog_ ->
                            if (dfcDialog_.isShowing)
                                dfcDialog_.dismiss()
                        }
                    }
                    setChallengeDetailsData(challengeResponse)
                }
            }
            challengeResponse?.let {
                trackJoinChallenge(it)
            }
        }
    }

    private fun dismissPublicChallengeDialog() {
        publicJoinDialog?.let {
            if (it.isShowing)
                it.dismiss()
        }
    }


    //todo


    private fun goToChallengeJoinPage(challengeDetails: STChallengesListData) {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, "")
            container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_CHALLENGE_DETAILS)
            container.putExtra(STConstants.CHALLENGES_DATA, challengeDetails)
            container.putExtra(STConstants.CHALLENGES_TYPE, challengeDetails.challengeType)
            container.putExtra(STConstants.CHALLENGES_ISPRIVATE, challengeDetails.isPrivate)
            challengeDetails.joinCode?.let {
                container.putExtra(
                    STConstants.PRIVATE_CHALLENGE_JOIN_CODE,
                    it
                )
            }
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeDetails.id)
            startActivity(container)
            activityContext.finish()
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
                    joinLeaveChallenge()
                    dismissDialog()
                }
            }
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun joinLeaveChallenge(challengeJoinCode: String? = "") {
        challengeDetails?.let {
            if (it.isPrivate!!) {
                it.id?.let { id ->
                    invokeIntent(
                        STChallengesIntent.JoinLeavePrivateChallenge(
                            if (it.joined!!) STConstants.CHALLENGE_OPERATION_LEAVE
                            else STConstants.CHALLENGE_OPERATION_JOIN,
                            id,
                            it.joinCode!!
                        )
                    )
                }
            } else {
                if (it.type == STConstants.DUBAI_FITNESS_CHALLENGE && !it.joined!!) {
                    it.id?.let { id ->
                        if (challengeJoinCode == "") {
                            showToast(getString(R.string.dfc_challenge_code_validation))
                            return
                        }
                        invokeIntent(
                            STChallengesIntent.JoinLeaveDFCChallenge(
                                if (it.joined!!) STConstants.CHALLENGE_OPERATION_LEAVE
                                else STConstants.CHALLENGE_OPERATION_JOIN, id, challengeJoinCode!!
                            )
                        )
                    }
                } else {                                            //in public challenge case both team and target/goal challenge with no join code

                    it.id?.let { id ->
                        invokeIntent(
                            STChallengesIntent.JoinLeaveChallenge(
                                if (it.joined!!) STConstants.CHALLENGE_OPERATION_LEAVE
                                else STConstants.CHALLENGE_OPERATION_JOIN,
                                id
                            )
                        )
                    }


                }
            }
        }
    }

//    private fun joinAnotherChallengeDialog() {
//        val successDialog: Dialog = showJoinAnotherChallengeDialog {
//            dialogJoinChallenge {
//                activityContext.finish()
//                dismissDialog()
//            }
//
//            val challengeTitleString = SpannableStringBuilder()
//            if (!challengeJoined) {
//                challengeTitleString.append(resources.getString(R.string.reach_the_goal_not_joined_1))
//            } else {
//                challengeTitleString.append(resources.getString(R.string.reach_the_goal_joined_1))
//            }
//            challengeTitleString.color(
//                STUtils.getColor(
//                    activityContext,
//                    R.color.button_bg_enabled_color
//                )
//            ) {
//                append(
//                    resources.getString(
//                        R.string.reach_the_goal_not_joined_2,
//                        STUtils.formatNumber(totalStepsValue.toInt())
//                    )
//                )
//            }
//                .append(resources.getString(R.string.reach_the_goal_not_joined_3))
//
//
//            val challengeCompletedDescriptionString = SpannableStringBuilder()
//                .bold { append(resources.getString(R.string.challenge_complete_description_1)) }
//                //.append(resources.getString(R.string.challenge_complete_description_2))
//                .color(
//                    STUtils.getColor(
//                        activityContext,
//                        R.color.button_bg_enabled_color
//                    )
//                ) { append(STPreference.getName(activityContext)!! + "!") }
//                .append(resources.getString(R.string.challenge_complete_description_3))
//                .bold {
//                    append(
//                        resources.getString(
//                            R.string.challenge_complete_description_4,
//                            challengeDetails?.challengeType!!
//                        )
//                    )
//                }
//                .append(resources.getString(R.string.challenge_complete_description_5))
//
//            setChallengeStatus(challengeJoined)
//            setTitle(challengeTitleString)
//            setDescription(challengeCompletedDescriptionString)
//        }
//        successDialog.setSize(activityContext)
//        successDialog.show()
//    }

    fun leaveChallengeDialog() {
        val successDialog: Dialog = leaveChallengeDialog {
            closeClick { dismissDialog() }
            confirmClick {
                joinLeaveChallenge()
                dismissDialog()
            }
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    @OnClick(R.id.viewAllList)
    fun viewAllClick() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.leader_board))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_VIEW_ALL_LEADER_BOARD)
        container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        container.putExtra(STConstants.IS_CHALLENGE_COMPLETED, isCompleted)
        container.putExtra(STConstants.CHALLENGES_ISPRIVATE, challengeDetails?.isPrivate)
        container.putExtra(STConstants.CHALLENGES_DATA_ID, challengesId)
        container.putExtra(STConstants.CHALLENGES_TYPE, challengeDetails?.type)
        container.putExtra(STConstants.CHALLENGES_DATA, challengeDetails)
        startActivity(container)
    }

    @OnClick(R.id.pc2ViewAllList)
    fun pc2ViewAllList() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.leader_board))
        container.putExtra(
            STConstants.FRAGMENT_ID,
            STConstants.FRAGMENT_ID_VIEW_ALL_LEADER_BOARD_PC4
        )
        container.putExtra(STConstants.IS_CHALLENGE_STARTED, isChallengeStarted)
        container.putExtra(STConstants.CHALLENGES_DATA_ID, challengesId)
        container.putExtra(STConstants.CHALLENGES_DATA, challengeDetails)
        startActivity(container)
    }
}
