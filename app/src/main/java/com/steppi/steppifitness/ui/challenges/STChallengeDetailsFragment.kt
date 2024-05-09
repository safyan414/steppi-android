package com.steppi.steppifitness.ui.challenges

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STImageTopCornerCurvedViewPager
import com.steppi.steppifitness.adapter.STParticipantTeamsListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.app.STConstants.IS_NEW_MESSAGE
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.common.STWebViewActivity
import com.steppi.steppifitness.utils.*
import com.steppi.steppifitness.views.STGothamRoundedBookTextView
import kotlinx.android.synthetic.main.fragment_my_challenge_details.*
import kotlinx.android.synthetic.main.layout_challenge_winner.*
import kotlinx.coroutines.*
import vimeoextractor.OnVimeoExtractionListener
import vimeoextractor.VimeoExtractor
import vimeoextractor.VimeoVideo
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class STChallengeDetailsFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), View.OnClickListener {
    private var challengesId: String? = null
    private var challengeDetails: STChallengesListData? = null

    //    private var achievedStepsValue: Long = 0
    private var totalStepsValue: Long = 0 
    private var challengeJoined = false
    private var participantTeamsListAdapter: STParticipantTeamsListAdapter? = null
    private var isChallengeStarted: Boolean = false
    var uri = ""
    var challengeStatus = -1

    private var isNewMessage = false 

    lateinit var scope: CoroutineScope // could also use an other scope such as viewModelScope if available
    var job: Job? = null

    private lateinit var challengeDetailsStartTime: String
    private lateinit var challengeDetailsStartDate: String
    private lateinit var challengeDetailsEndTime: String
    private lateinit var challengeDetailsEndDate: String

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_challenge_details
    }

    override fun initViews() {
        initData()

        playButton_Detail.setOnClickListener {
            Log.e("abc", "testing")
            if (challengeStatus != STConstants.TOUGH_MUDDER_CHALLENGE_NOT_JOINED) {
                showPlayer()

            } else {
                showToast("You need to join the challenge to watch the video.")
            }
        }
    }

    private fun initData() {
        challengesId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)


        arguments?.getBoolean(STConstants.IS_NEW_MESSAGE)?.let {
            isNewMessage = it
        }

        scope = CoroutineScope(Dispatchers.Default)

        tvDoneOtherChallenges.setOnClickListener(this)
    }

    override fun onViewModelReady() {
        challengesId?.let {
            invokeIntent(STChallengesIntent.GetCorporateChallengeDetails(it))
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
            is STChallengesState.GetCorporateChallengeDetails -> {
                requestDidFinish()
                setChallengeDetailsData(state.challengeDetailsResponse)
            }
            is STChallengesState.JoinLeaveChallenge -> {
                STConstants.UPDATE_MY_CHALLENGE_LIST = true
                STConstants.UPDATE_STEPPI_CHALLENGE_LIST = true
                requestDidFinish()
                state.challengeDetailsResponse?.data?.let {
                    if (!it.joined!!) {
                        activityContext.finish()
                    } else {
                        setChallengeDetailsData(state.challengeDetailsResponse)
                    }
                }
                state.challengeDetailsResponse?.let {
                    trackJoinChallenge(it)
                }
            }
        }
    }


    var myTeam: STParticipantTeams? = null

    private fun showPlayer() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(uri), "video/*")
        startActivity(intent)
    }

    private fun setChallengeDetailsData(challengeDetailsResponse: STChallengeDetailsResponse?) {
        challengeDetails = challengeDetailsResponse?.data
        challengeDetails?.let { challengeDetailsData ->

            initViewPager()
            pager.visible()
            next.visible()
            challengeDetailsData.urlVideo?.let {

                if (it.isNotEmpty()) {
                    getVideo(it)
                }
            }

            challengeDetailsMainLayout.visible()
            infoLayout.visible()
            if (activityContext is STContainerActivity) {
                challengeDetailsData.name?.let {
                    (activityContext as STContainerActivity).setHeader(it)
                }
            }
            challengeDetailsData.name?.let {
                challengeName.text = it
            }
            challengeDetailsData.description?.let {
                if (it == "") {
                    descriptionTitleLabel.gone()
                    challengeDescription.gone()
                } else {
                    descriptionTitleLabel.visible()
                    challengeDescription.visible()
                    challengeDescription.text = it
                }
            } ?: kotlin.run {
                descriptionTitleLabel.gone()
                challengeDescription.gone()
            }

            challengeDetailsData.challengeStatus?.let {
                challengeStatus = it
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

                        enableCountdownTimerUpdates(challengeDetailsData, true)
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_ONGOING, true) -> {
                        challengeStatusText.text = activityContext.getString(R.string.ongoing)
                        challengeStatusText.setTextColor(
                            STUtils.getColor(
                                activityContext,
                                R.color.button_bg_enabled_color
                            )
                        )

                        enableCountdownTimerUpdates(challengeDetailsData, false)
                    }
                    it.equals(STConstants.CHALLENGE_STATUS_COMPLETED, true) -> {
                        challengeStatusText.text = activityContext.getString(R.string.done)
                        challengeStatusText.setTextColor(
                            STUtils.getColor(activityContext, R.color.button_bg_enabled_color)
                        )
                    }
                }

                challengeDetailsData.isCompleted?.let { isCompleted ->
                    if (isCompleted) {
                        enableTimerDone(challengeDetailsData)
//                        joinNow.visible()
//                        joinNow.text = activityContext.getString(R.string.join_another_challenge)
                    } else {
                        joinNow.gone()
                    }
                }
            } ?: kotlin.run {
                challengeStatusText.gone()
            }


            challengeDetailsData.corporation?.logo?.let {
                logo.load(activityContext, it)
            }

            challengeDetailsData.startDate?.let {
                startDate.text = STUtils.getDate(it, "yyyy-MM-dd", "dd-MMM-yyyy")
            }

            challengeDetailsData.endDate?.let {
                endDate.text = STUtils.getDate(it, "yyyy-MM-dd", "dd-MMM-yyyy")
            }

            challengeDetailsData.startDate?.let { startDate ->
                val currentDate = STUtils.getFormattedDate(
                    Calendar.getInstance(),
                    "yyyy-MM-dd"
                )
                when (STUtils.compareDate(startDate, currentDate)) {
                    STConstants.CHALLENGE_NOT_STARTED -> {
                        Log.d("CHALLENGE_NOT_STARTED", "CHALLENGE_NOT_STARTED")
                        isChallengeStarted = false
                    }
                    STConstants.CHALLENGE_STARTED -> {
                        isChallengeStarted = true
                    }
                }
            }

            challengeDetailsData.voucherDescription?.let {
                if (it == "") {
                    giftCell.gone()
                } else {
                    gift.text = it
                }
            } ?: kotlin.run {
                giftCell.gone()
            }


            challengeDetailsData.corporateChallengeType?.let { challengeType ->
                //participantLayout.visible()
                corporateChallengeTypeValueTextView.text = "Steps"
                if (challengeType == "step") {
                    corporateChallengeTypeValueTextView.text = "Steps"
                } else if (challengeType == "active_minutes") {
                    corporateChallengeTypeValueTextView.text = "Active Minutes"
                } else if (challengeType == "distance_covered") {
                    corporateChallengeTypeValueTextView.text = "Distance Covered"
                } else if (challengeType == "calories_burned") {
                    corporateChallengeTypeValueTextView.text = "Calories Burned"
                }
            } ?: kotlin.run {
                // participantLayout.gone()
                corporateChallengeTypeValueTextView.text = ""
            }



            challengeDetailsData.teams?.let { teamsCount ->
                participantLayout.visible()
                when (teamsCount) {
                    "0" -> {
                        participant.text = teamsCount
                    }
                    "1" -> {
                        participant.text =
                            "$teamsCount ${activityContext.getString(R.string.teams_value_sub_one)}"
                    }
                    else -> {
                        participant.text =
                            "$teamsCount ${activityContext.getString(R.string.teams_value_sub)}"
                    }
                }
            } ?: kotlin.run {
                participantLayout.gone()
            }
            myTeam = challengeDetailsData.participantTeams?.firstOrNull { it.myTeam }
            val pos: Int = challengeDetailsData.participantTeams?.indexOf(myTeam) ?: -1
            myTeam?.let {
                myTeamLayoutCorporate.visible()
                teamName.text = it.name
                totalScore.text = if (!it.totalScore.isNullOrEmpty()) {
//                    it.totalScore
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(it.totalScore).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                } else {
                    "0"
                }
                dailyAverage.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.dailyAverage).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )

                if (isChallengeStarted) {
                    if (pos < 3 && myTeam?.teamSteps != "0") {
                        rankingImageLayout.visible()
                        position.gone()
                        when (pos) {
                            0 -> {
                                myTeamLayoutCorporate.setBackgroundResource(R.drawable.rounded_theme_yellow_stroke_small)
                                rankingImage.setImageResource(R.drawable.win_label_one)
                            }
                            1 -> {
                                myTeamLayoutCorporate.setBackgroundResource(R.drawable.rounded_theme_orange_stroke_small)
                                rankingImage.setImageResource(R.drawable.win_label_two)
                            }
                            2 -> {
                                myTeamLayoutCorporate.setBackgroundResource(R.drawable.rounded_theme_violet_stroke_small)
                                rankingImage.setImageResource(R.drawable.win_label_three)
                            }
                            else -> {
                                myTeamLayoutCorporate.setBackgroundResource(R.drawable.rounded_theme_green_storke_small)
                                rankingImageLayout.gone()
                                position.visible()
                                position.text = (pos + 1).toString()
                            }
                        }

                    } else {
                        myTeamLayoutCorporate.setBackgroundResource(R.drawable.rounded_theme_green_storke_small)
                        rankingImageLayout.gone()
                        position.visible()
                        position.text = (pos + 1).toString()
                    }
                } else {
                    myTeamLayoutCorporate.setBackgroundResource(R.drawable.rounded_theme_green_storke_small)
                    rankingImageLayout.gone()
                    position.visible()
                    position.text = (pos + 1).toString()
                }
            } ?: kotlin.run {
                participantTeamsLayout.gone()
            }

            if (isNewMessage)
                openTeamDetail(myTeam)

            myTeamLayoutCorporate.setOnClickListener {
                openTeamDetail(myTeam)
            }

            challengeDetailsData.participantTeams?.let { teams ->
//                val newTeams = ArrayList<STParticipantTeams>()
//                teams.forEach {
//                    if (!it.myTeam) {
//                        newTeams.add(it)
//                    }
//                }
                myTeam?.let {
                    participantTeamsLayout.visible()
                } ?: kotlin.run {
                    participantTeamsLayout.gone()
                }
                setParticipantAdapter(
                    teams,
                    pos
                )
            }

            challengeDetailsData.isCompleted?.let { isCompleted ->
                if (isCompleted && challengeDetailsData.winnerTeam != null) {
                    winnerLayout_pc_two.visible()
                    pcTwoWinnerTeamName.text = challengeDetailsData.winnerTeam!!.name
                    pcTwoWinnerTeamName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                    pcTwoWinnerTeamMembers.text =
                        STUtils.formatNumber(
                            (challengeDetailsData.winnerTeam!!.totalScore ?: "0").toInt()
                        )
                    pcTwoWinnerTeamTotalScore.text =
                        STUtils.formatNumber(
                            (challengeDetailsData.winnerTeam!!.dailyAverage ?: "0").toInt()
                        )
                    membersLabel.text = activityContext.getString(R.string.total_score)
                    totalScoreLabel.text = activityContext.getString(R.string.daily_average)
                    membersLabel.setTextColor(STUtils.getColor(activityContext, R.color.light_blue))
                    totalScoreLabel.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.light_blue
                        )
                    )
                } else {
                    winnerLayout_pc_two.gone()
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
    }


    /*



    todo: countdown timer */

    private fun enableCountdownTimerUpdates(challengeDetailsData: STChallengesListData, isUpComing: Boolean) {
        startUpdates(challengeDetailsData, isUpComing)
    }

    fun startUpdates(challengeDetailsData: STChallengesListData, isUpComing: Boolean) {
        stopUpdates()
        job = scope.launch {
            while (true) {
                CoroutineScope(Dispatchers.Main).launch {
                    enableCountdownTimer(challengeDetailsData, isUpComing)                          // the function that should be ran every second
                    Log.e("timer", "startUpdates: ")
                }
                delay(1000)
            }
        }
    }

    private fun enableCountdownTimer(challengeDetailsData: STChallengesListData, isUpComing: Boolean) {
        if (isUpComing) {
            setTimerImageDrawable(ivTimer, R.drawable.timer_will_start)
            setTimerImageDrawable(ivTimerBg, R.drawable.timer_will_start_bg)
            tvTimerHeading.text = getString(R.string.corporate_timer_will_head)

            challengeDetailsData.startTime?.let {
                challengeDetailsStartTime = it
            }
            getTimeDifferenceValues(challengeDetailsStartTime)

            challengeDetailsData.startDate?.let {
                challengeDetailsStartDate = it
            }
            setSubDate(challengeDetailsStartDate, getString(R.string.corporate_timer_will_sub))

        } else {            //OnGoing
            setTimerImageDrawable(ivTimer, R.drawable.timer_ongoing)
            setTimerImageDrawable(ivTimerBg, R.drawable.timer_ongoing_bg)
            tvTimerHeading.text = getString(R.string.corporate_timer_ongoing_head)

            challengeDetailsData.endTime?.let {
                challengeDetailsEndTime = it
            }
            getTimeDifferenceValues(challengeDetailsEndTime)

            challengeDetailsData.endDate?.let {
                challengeDetailsEndDate = it
            }
            setSubDate(challengeDetailsEndDate, getString(R.string.corporate_timer_ongoing_sub))
        }

        tvDoneOtherChallenges.visibility = View.GONE
        clTimeLayout.visibility = View.VISIBLE
    }

    private fun getTimeDifferenceValues(date: String) {

        //convert timestamp to date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val inputDate = inputFormat.parse(date)
        val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val formattedDateString = outputFormat.format(inputDate)
        val formattedDate = outputFormat.parse(formattedDateString)

        //Get Current Date
        val dateFormat: DateFormat =
            SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val currentTime: String = dateFormat.format(Date())
        val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val currentDate = df.parse(currentTime) 


        //milliseconds
        var difference: Long = 0
        if (currentDate != null) {

            //current date will be less
            difference = formattedDate.time - currentDate.time

            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24

            val elapsedDays = difference / daysInMilli
            if (elapsedDays > 0) {
                difference %= daysInMilli
                tvTimerDays.visibility = View.VISIBLE
                setCountdownTimerValues(
                    tvTimerDays,
                    elapsedDays,
                    getString(R.string.corporate_timer_will_days)
                )
            } else tvTimerDays.visibility = View.GONE


            val elapsedHours = difference / hoursInMilli
            if (elapsedHours > 0) {
                difference %= hoursInMilli
                tvTimerHours.visibility = View.VISIBLE
                setCountdownTimerValues(
                    tvTimerHours,
                    elapsedHours,
                    getString(R.string.corporate_timer_will_hours)
                )
            } else tvTimerHours.visibility = View.GONE


            val elapsedMinutes = difference / minutesInMilli
            if (elapsedMinutes > 0) {
                difference %= minutesInMilli
                tvTimerMinutes.visibility = View.VISIBLE
                setCountdownTimerValues(
                    tvTimerMinutes,
                    elapsedMinutes,
                    getString(R.string.corporate_timer_will_minutes)
                )
            } else tvTimerMinutes.visibility = View.GONE
        }
    }

    private fun setCountdownTimerValues(tvTimer: STGothamRoundedBookTextView?, timerValue: Long, endingString: String) {

        val ssTimerValue = SpannableString(timerValue.toString())
        ssTimerValue.setSpan(                         // set color
            ForegroundColorSpan(
                ContextCompat.getColor(
                    activityContext,
                    R.color.button_bg_enabled_color
                )
            ), 0, ssTimerValue.length, 0          //run span upto text.length
        )
        ssTimerValue.setSpan(                         // set bold
            StyleSpan(Typeface.BOLD), 0, ssTimerValue.length, 0          //run span upto text.length
        )

        val span2 = SpannableString(" $endingString")
        span2.setSpan(
            RelativeSizeSpan(0.8f),
            0,
            span2.length,
            SPAN_INCLUSIVE_INCLUSIVE
        )
        val finalText: CharSequence = TextUtils.concat(
            ssTimerValue, "", span2
        )

        tvTimer?.text = finalText
    }

    private fun setTimerImageDrawable(iv: ImageView?, timerDrawable: Int) {
        iv?.setImageDrawable(
            ContextCompat.getDrawable(
                activityContext,
                timerDrawable
            )
        )
    }

    private fun setSubDate(
        date: String, startingString: String
    ) {

        val dateStr = STUtils.getDate(date, "yyyy-MM-dd", "dd MMMM")

        val ssDate = SpannableString(dateStr)
        ssDate.setSpan(                         // set color
            ForegroundColorSpan(
                ContextCompat.getColor(
                    activityContext,
                    R.color.button_lang_bg_enabled_color
                )
            ), 0, dateStr.length, 0          //run span upto text.length
        )

        val span1 = SpannableString(startingString)
        val span2 = SpannableString(getString(R.string.corporate_timer_will_gst))
        span2.setSpan(
            RelativeSizeSpan(0.8f),
            0,
            getString(R.string.corporate_timer_will_gst).length,
            SPAN_INCLUSIVE_INCLUSIVE
        )                   // set size, size will be 0.8 times smaller than original

        //to make different span strings together, string.format not work here
        val finalText: CharSequence = TextUtils.concat(
            span1, " ", ssDate, " ", span2
        )

        tvTimerDateSub.text = finalText
    }

    private fun enableTimerDone(challengeDetailsData: STChallengesListData) {

        tvTimerDays.visibility = View.GONE
        tvTimerHours.visibility = View.GONE
        tvTimerMinutes.visibility = View.GONE
        tvDoneOtherChallenges.visibility = View.VISIBLE

        setTimerImageDrawable(ivTimer, R.drawable.timer_done)
        setTimerImageDrawable(ivTimerBg, R.drawable.timer_done_bg)

        tvTimerHeading.text = getString(R.string.corporate_timer_done_head)

        challengeDetailsData.endDate?.let {
            setSubDate(it, getString(R.string.corporate_timer_done_sub))
        }

        clTimeLayout.visibility = View.VISIBLE
    }

    //todo: stop updates

    fun stopUpdates() {
        Log.e("timer", "stopUpdates: ")
        job?.cancel()
        job = null
    }

    override fun onPause() {
        super.onPause()
        stopUpdates()
    }

    override fun onStop() {
        super.onStop()
        stopUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopUpdates()
    }

    /*




    todo leave challenge*/
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

//    @SuppressLint("SetTextI18n")
//    private fun setChallengeDetailsData(challengeDetailsResponse: STChallengeDetailsResponse?) {
//        challengeDetails = challengeDetailsResponse?.data
//        challengeDetails?.let { challengeDetailsData ->
//            initViewPager()
//            challengeDetailsMainLayout.visibility = View.VISIBLE
//            //challengeType.text = challengeDetailsData.challengeType
//            challengeDetailsData.name?.let {
//                challengeName.text = it
//            }
//
//            challengeDetailsData.startDate?.let { startDate ->
//                val currentDate = STUtils.getFormattedDate(
//                    Calendar.getInstance(),
//                    "yyyy-MM-dd"
//                )
//                when (STUtils.compareDate(startDate, currentDate)) {
//                    STConstants.CHALLENGE_NOT_STARTED -> {
//                        Log.d("CHALLENGE_NOT_STARTED", "CHALLENGE_NOT_STARTED")
//                        isChallengeStarted = false
//                    }
//                    STConstants.CHALLENGE_STARTED -> {
//                        isChallengeStarted = true
//                    }
//                }
//            }
//            if (activityContext is STContainerActivity) {
//                challengeDetailsData.challengeType?.let {
//                    (activityContext as STContainerActivity).setHeader(it)
//                }
//            }
//            challengeDetailsData.challengeType?.let { challengeDetailsChallengeType ->
//                if (challengeDetailsChallengeType.equals(
//                        STConstants.STEPPI_CHALLENGE,
//                        ignoreCase = true
//                    )
//                ) {
//                    challengeDescription.text = challengeDetailsData.description
//                    if (challengeDetailsData.logo.isNullOrEmpty()) {
//                        logo.setBackgroundResource(R.drawable.steppi_icon)
//                    } else {
//                        logo.load(activityContext, challengeDetailsData.logo)
//                    }
//                    challengeType.text = challengeDetailsData.challengeType
//                    challengeDetailsData.targetSteps?.let {
//                        val stepsValue =
//                            NumberFormat.getNumberInstance(Locale.US).format(it.toInt())
//                        val stepsValueFontUpdate = SpannableStringBuilder(stepsValue)
//                        val stepsString =
//                            SpannableStringBuilder(activityContext.getString(R.string.steps))
//                        stepsValueFontUpdate.setSpan(
//                            STGothamRoundedBoldTypefaceSpan(activityContext, ""),
//                            0,
//                            stepsValue.length,
//                            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
//                        )
//                        stepsString.setSpan(
//                            STGothamRoundedMediumTypefaceSpan(activityContext, ""),
//                            0,
//                            4,
//                            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
//                        )
//                        val stepsText = SpannableStringBuilder()
//                        stepsText.bold { append(stepsValueFontUpdate) }
//                            .append(activityContext.getString(R.string.space)).append(stepsString)
//                        steps.text = stepsText
//                        steps.isSelected = true
//                    }
//                    totalStepsValue = challengeDetailsData.targetSteps?.toLong()!!
//                    infoLayoutCorporate.gone()
//                    challengeDetailsData.participants?.let {
//                        participantCount.text =
//                            NumberFormat.getNumberInstance(Locale.US).format(it.toInt())
//                    }
//                    startDate.text = STUtils.getDate(
//                        challengeDetailsData.startDate,
//                        "yyyy-MM-dd", "dd-MMM-yyyy"
//                    )
//                    challengeDetailsData.states?.let { statesData ->
//                        stepsTillNowLayout.visible()
//                        statesData.achievedSteps?.let {
//                            achievedSteps.text =
//                                NumberFormat.getNumberInstance(Locale.US).format(it)
//                            achievedStepsValue = it
//                        } ?: kotlin.run {
//                            achievedSteps.text = getString(R.string.zero)
//                            achievedStepsValue = 0
//                        }
//                        statesData.achievedSteps.toString()
//                        statesData.averageDailySteps?.let {
//                            averageDailySteps.text =
//                                NumberFormat.getNumberInstance(Locale.US).format(it)
//                        } ?: kotlin.run {
//                            averageDailySteps.text = getString(R.string.zero)
//                        }
//                        statesData.perUserAverageDailySteps?.let {
//                            perUserAverageDailySteps.text =
//                                NumberFormat.getNumberInstance(Locale.US)
//                                    .format(it)
//                        } ?: kotlin.run {
//                            perUserAverageDailySteps.text = getString(R.string.zero)
//                        }
//                    }
//                    //totalStepsValue = challengeDetailsData.targetSteps?.toLong()!!
//                    val percentageValue: Int? =
//                        if (((achievedStepsValue * 100.00) / totalStepsValue) < 100 &&
//                            ((achievedStepsValue * 100.00) / totalStepsValue) > 99
//                        ) {
//                            99
//                        } else {
//                            if (totalStepsValue > 0) {
//                                ((achievedStepsValue * 100.00) / totalStepsValue).roundToInt()
//                            } else {
//                                0
//                            }
//                        }
//                    progressBar1.progress = percentageValue!!
//                    progressPercentage.text = "$percentageValue%"
//                    //steps.text = challengeDetailsData.targetSteps
//                    //challengeDetails?.let { challengeDetail ->
//                    if (isChallengeStarted) {
//                        stepsTillNowLayout.visible()
//                        progressBarLayout.visible()
//                        challengeDetailsData.userStates?.let { myContributionData ->
//                            myContributionLayout.visible()
//                            myContributionData.totalSteps?.let {
//                                totalSteps.text =
//                                    NumberFormat.getNumberInstance(Locale.US).format(it)
//                            }
//                            myContributionData.averageSteps?.let {
//                                averageSteps.text =
//                                    NumberFormat.getNumberInstance(Locale.US).format(it)
//                            }
//                            profileImage.load(
//                                activityContext,
//                                STPreference.getProfilePic(activityContext)
//                            )
//
//                        } ?: kotlin.run {
//                            joinNow.visible()
//                        }
//                    } else {
//                        stepsTillNowLayout()
//                        progressBarLayout.gone()
//                    }
//
//                    if (challengeDetailsData.joined!!) {
//                        challengeJoined = true
//                        if (achievedStepsValue >= totalStepsValue && achievedStepsValue.toInt() != 0) {
//                            labelGoal.gone()
//                            progressPercentage.text = getString(R.string.hundreds_percentage)
//                            challengeOngoingLayout.gone()
//                            challengeCompletedLayout.visible()
//
//                            val challengeTitleString = SpannableStringBuilder()
//                                .append(resources.getString(R.string.reach_the_goal_1))
//                                .color(
//                                    STUtils.getColor(
//                                        activityContext,
//                                        R.color.button_bg_enabled_color
//                                    )
//                                ) {
//                                    append(
//                                        resources.getString(
//                                            R.string.reach_the_goal_2,
//                                            STUtils.formatNumber(totalStepsValue.toInt())
//                                        )
//                                    )
//                                }
//                                .append(resources.getString(R.string.reach_the_goal_3))
//                            completedTitleText.text = challengeTitleString
//
//                            val challengeCompletedDescriptionString = SpannableStringBuilder()
//                                .bold { append(resources.getString(R.string.challenge_complete_description_1)) }
//                                //.append(resources.getString(R.string.challenge_complete_description_2))
//                                .color(
//                                    STUtils.getColor(
//                                        activityContext,
//                                        R.color.button_bg_enabled_color
//                                    )
//                                ) { append(STPreference.getName(activityContext)!! + "!") }
//                                .append(resources.getString(R.string.challenge_complete_description_3))
//                                .bold {
//                                    append(
//                                        resources.getString(
//                                            R.string.challenge_complete_description_4,
//                                            challengeDetailsData.challengeType!!
//                                        )
//                                    )
//                                }
//                                .append(resources.getString(R.string.challenge_complete_description_5))
//
//                            challengeCompletedDescription.text = challengeCompletedDescriptionString
//                            joinNow.visible()
//                            joinNow.text =
//                                activityContext.resources.getString(R.string.join_another_challenge)
//                        } else {
//                            joinNow.gone()
//                            joinedStatus.visible()
////                        stepsTillNowLayout.visible()
////                        progressBarLayout.visible()
////                        myContributionLayout.visible()
//                        }
//                    } else {
//                        challengeJoined = false
//                        joinNow.visible()
//                        if (achievedStepsValue >= totalStepsValue && achievedStepsValue.toInt() != 0) {
//                            labelGoal.gone()
//                            progressPercentage.text = getString(R.string.hundreds_percentage)
//                            challengeOngoingLayout.gone()
//                            challengeCompletedLayout.visible()
//
//                            val challengeTitleString = SpannableStringBuilder()
//                                .append(resources.getString(R.string.reach_the_goal_not_joined_1))
//                                .color(
//                                    STUtils.getColor(
//                                        activityContext,
//                                        R.color.button_bg_enabled_color
//                                    )
//                                ) {
//                                    append(
//                                        resources.getString(
//                                            R.string.reach_the_goal_not_joined_2,
//                                            STUtils.formatNumber(totalStepsValue.toInt())
//                                        )
//                                    )
//                                }
//                                .append(resources.getString(R.string.reach_the_goal_not_joined_3))
//                            completedTitleText.text = challengeTitleString
//
//                            challengeCompletedDescription.gone()
//                            joinNow.text =
//                                activityContext.resources.getString(R.string.join_another_challenge)
//                        }
//                    }
//                    val eventNameList: HashMap<String, String> = HashMap()
//                    challengeDetailsData.name?.let { challengeName ->
//                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_NAME] =
//                            challengeName
//                    }
//                    challengeDetailsData.id?.let { challengeId ->
//                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_ID] =
//                            challengeId
//                    }
//                    if (!BuildConfig.DEBUG)
//                        STFireBaseAnalytics.trackEventValue(
//                            STFireBaseAnalyticsConstants.EVENT_PUBLIC_CHALLENGE_DETAIL_VISIT,
//                            eventNameList
//                        )
//                } else if (challengeDetailsChallengeType.equals(
//                        STConstants.CORPORATE_CHALLENGE,
//                        ignoreCase = true
//                    )
//                ) {
//                    challengeDescription.gone()
//                    corporateChallengeDescription.visible()
//                    corporateChallengeDescription.text = challengeDetailsData.description
//                    rules.visible()
//                    rules.setOnClickListener {
//                        val learnMoreIntent = Intent(activityContext, STWebViewActivity::class.java)
//                        learnMoreIntent.putExtra(
//                            STConstants.WEB_URL,
//                            BuildConfig.API_URL + STConstants.CORPORATE_CHALLENGE_RULES
//                        )
//                        learnMoreIntent.putExtra(
//                            STConstants.WEB_HEADER,
//                            activityContext.resources.getString(R.string.rules)
//                        )
//                        startActivity(learnMoreIntent)
//                    }
//                    // update it properly
//                    corporateStartDate.text = STUtils.getDate(
//                        challengeDetailsData.startDate,
//                        "yyyy-MM-dd", "dd-MMM-yyyy"
//                    )
//
//                    corporateEndDate.text = STUtils.getDate(
//                        challengeDetailsData.endDate,
//                        "yyyy-MM-dd", "dd-MMM-yyyy"
//                    )
//
//                    //startDateLabel.gone()
//                    if (challengeDetailsData.isCompleted!!) {
//                        //startDateCorporate.text = getString(R.string.done)
//                        challengeDetailsData.winnerTeam?.let {
//                            winnerLayout.visible()
//                            winnerTeamName.text = it.name
//                        }
//                    }
//
//                    challengeDetailsData.status?.let { challengeStatus ->
//                        joinedStatusCorporate.text = challengeStatus.substring(
//                            0,
//                            1
//                        ).toUpperCase() + challengeStatus.substring(
//                            1
//                        ).toLowerCase()
//                    } ?: kotlin.run {
//                        when {
//                            challengeDetailsData.isCompleted!! -> {
//                                joinedStatusCorporate.text = getString(R.string.done)
//                            }
//                            isChallengeStarted -> {
//                                joinedStatusCorporate.text = getString(R.string.ongoing)
//                            }
//                        }
//                    }
//
//                    /*setCorporateChallengeDate(
//                        challengeDetailsData.startDate,
//                        challengeDetailsData.endDate,
//                        challengeDetailsData.isCompleted
//                    )*/
//
//                    challengeDetailsData.voucherDescription?.let { voucherDescriptionText ->
//                        if (voucherDescriptionText == "") {
//                            corporateRewardName.gone()
//                        } else {
//                            corporateRewardName.text = voucherDescriptionText
//                        }
//                    } ?: kotlin.run {
//                        corporateRewardName.gone()
//                    }
//
//                    challengeDetailsData.corporation?.let { corporationData ->
//                        logo.load(activityContext, corporationData.logo)
//                        challengeType.text = corporationData.name
//                        if (activityContext is STContainerActivity) {
//                            (activityContext as STContainerActivity).setHeader(corporationData.name)
//                        }
//                    }
//                    challengeDetailsData.corporateChallengeCap?.let {
//                        //                    steps.text = NumberFormat.getNumberInstance(Locale.US).format(it.toInt())
//                        val stepsValue =
//                            NumberFormat.getNumberInstance(Locale.US).format(it.toInt())
//                        val stepsValueFontUpdate = SpannableStringBuilder(stepsValue)
//                        val stepsString =
//                            SpannableStringBuilder(activityContext.getString(R.string.steps))
//                        stepsValueFontUpdate.setSpan(
//                            STGothamRoundedBoldTypefaceSpan(activityContext, ""),
//                            0,
//                            stepsValue.length,
//                            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
//                        )
//                        stepsString.setSpan(
//                            STGothamRoundedMediumTypefaceSpan(activityContext, ""),
//                            0,
//                            4,
//                            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
//                        )
//                        val stepsText = SpannableStringBuilder()
//                        stepsText.bold { append(stepsValueFontUpdate) }
//                            .append(activityContext.getString(R.string.space)).append(stepsString)
//                        steps.text = stepsText
//                        steps.isSelected = true
//                    }
//                    labelGoal.text = activityContext.resources.getText(R.string.target)
//
//                    totalStepsValue = challengeDetailsData.corporateChallengeCap?.toLong()!!
//                    joinNow.gone()
//                    infoLayoutCorporate.visible()
//                    challengeStatus.gone()
//
//                    challengeDetailsData.corporation?.let {
//                        if (activityContext is STContainerActivity) {
//                            (activityContext as STContainerActivity)
//                                .manageMoreMenuVisibility(
//                                    challengeDetailsData.joined!!,
//                                    challengeDetailsData.isCompleted!!
//                                )
//                        }
//                    }
//
//                    challengeDetailsData.participantTeams?.let {
//                        for (i in it.indices) {
//                            if (it[i].myTeam) {
//                                val participantTeam = it[i]
//                                myTeamLayoutCorporate.setOnClickListener {
//                                    openTeamDetail(participantTeam)
//                                }
//                                val myTeam = it[i]
//                                myTeamLayoutCorporate.visible()
//                                position.text = (i + 1).toString()
//                                teamName.text = myTeam.name
//                                myTeam.dailyAverage?.let { myTeamDailyAverage ->
//                                    dailyAverage.text =
//                                        STUtils.formatNumber(myTeamDailyAverage.toInt())
//                                } ?: kotlin.run {
//                                    dailyAverage.text = getString(R.string.zero)
//                                }
//                                myTeam.totalScore?.let { myTeamTotalScore ->
//                                    totalScore.text = STUtils.formatNumber(myTeamTotalScore.toInt())
//                                } ?: kotlin.run {
//                                    totalScore.text = getString(R.string.zero)
//                                }
//                            }
//                        }
//                        setParticipantAdapter(
//                            it,
//                            challengeDetailsData.isCompleted!!,
//                            challengeDetailsData.winnerTeam
//                        )
//                        infoLayoutCorporate.visible()
//                        participantTeamsLayout.visible()
//                        joinNow.gone()
//                        infoLayout.gone()
//                    } ?: kotlin.run {
//                        myTeamLayoutCorporate.gone()
//                        infoLayoutCorporate.gone()
//                        participantTeamsLayout.gone()
//                    }
//                    val eventNameList: HashMap<String, String> = HashMap()
//                    challengeDetailsData.name?.let { challengeName ->
//                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_NAME] =
//                            challengeName
//                    }
//                    challengeDetailsData.corporation?.let { challengeCorporation ->
//                        challengeCorporation.name?.let { corporateName ->
//                            eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CORPORATE_NAME] =
//                                corporateName
//                        }
//                    }
//                    challengeDetailsData.id?.let { challengeId ->
//                        eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CHALLENGE_ID] =
//                            challengeId
//                    }
//                    challengeDetailsData.corporation?.let { challengeCorporation ->
//                        challengeCorporation.id?.let { corporateId ->
//                            eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_CORPORATE_CHALLENGE_ID] =
//                                corporateId
//                        }
//                    }
//                    if (!BuildConfig.DEBUG)
//                        STFireBaseAnalytics.trackEventValue(
//                            STFireBaseAnalyticsConstants.EVENT_CORPORATE_CHALLENGE_DETAIL_VISIT,
//                            eventNameList
//                        )
//                }
//            }
//        }
//    }

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


    fun getVideo(url: String) {
        pager.visible()
        playButton_Detail.visible()

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

    /*


    todo: onCLick */
    override fun onClick(v: View?) {
        if (v == tvDoneOtherChallenges)
            joinNow()
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


//    private fun setMyTeamsAdapter(participantTeamList: List<STParticipantTeams>) {
//        myTeams.layoutManager =
//            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
//        myTeamsListAdapter =
//            STMyTeamsListAdapter(
//                activityContext,
//                participantTeamList
//            )
//        myTeams.adapter = myTeamsListAdapter
//        myTeams.setVerticalItemDecoration(
//            space = STUtils.getDimen(activityContext, R.dimen.margin_normal),
//            initialPadding = STUtils.getDimen(activityContext, R.dimen.margin_normal)
//        )
//    }

    private fun setParticipantAdapter(
        participantTeamList: List<STParticipantTeams>,
        myTeamIndex: Int
    ) {
        participantTeams.layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
        participantTeams.setVerticalItemDecoration(
            STUtils.getDimen(
                activityContext,
                R.dimen.margin_small_xx
            ), 0
        )
        participantTeamsListAdapter =
            STParticipantTeamsListAdapter(
                participantTeamList,
                isChallengeStarted,
                myTeamIndex
            )
        participantTeams.adapter = participantTeamsListAdapter
        participantTeamsListAdapter?.setClickListener(object :
            STParticipantTeamsListAdapter.OnItemClickListener {
            override fun onItemClick(participantTeam: STParticipantTeams?) {
                openTeamDetail(participantTeam)
            }
        })
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

    @OnClick(R.id.rulesLayout)
    fun rulesLayoutClick() {
        val learnMoreIntent = Intent(activityContext, STWebViewActivity::class.java)
        learnMoreIntent.putExtra(
            STConstants.WEB_URL,
            BuildConfig.API_URL + STConstants.CORPORATE_CHALLENGE_RULES
        )
        learnMoreIntent.putExtra(
            STConstants.WEB_HEADER,
            activityContext.resources.getString(R.string.rules)
        )
        startActivity(learnMoreIntent)
    }


    @OnClick(R.id.joinNow)
    fun joinNow() {
        if (challengeDetails?.isCompleted == null || challengeDetails?.isCompleted!!) {
            /*joinAnotherChallengeDialog()*/
            activityContext.finish()
        } else {
            challengeDetails?.let { challengeDetailsData ->
                if (challengeDetailsData.joined!!) leaveChallengeDialog()
                else joinChallengeDialog()
            }
        }
        //joinChallengeDialog()
        //joinAnotherChallengeDialog()
        //openTeamDetail()
        //leaveChallengeDialog()
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

    private fun joinLeaveChallenge() {
        challengeDetails?.let {
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

    private fun joinAnotherChallengeDialog() {
        val successDialog: Dialog = showJoinAnotherChallengeDialog {
            dialogJoinChallenge {
                activityContext.finish()
                dismissDialog()
            }

            val challengeTitleString = SpannableStringBuilder()
            if (!challengeJoined) {
                challengeTitleString.append(resources.getString(R.string.reach_the_goal_not_joined_1))
            } else {
                challengeTitleString.append(resources.getString(R.string.reach_the_goal_joined_1))
            }
            challengeTitleString.color(
                STUtils.getColor(
                    activityContext,
                    R.color.button_bg_enabled_color
                )
            ) {
                append(
                    resources.getString(
                        R.string.reach_the_goal_not_joined_2,
                        STUtils.formatNumber(totalStepsValue.toInt())
                    )
                )
            }
                .append(resources.getString(R.string.reach_the_goal_not_joined_3))


            val challengeCompletedDescriptionString = SpannableStringBuilder()
                .bold { append(resources.getString(R.string.challenge_complete_description_1)) }
                //.append(resources.getString(R.string.challenge_complete_description_2))
                .color(
                    STUtils.getColor(
                        activityContext,
                        R.color.button_bg_enabled_color
                    )
                ) { append(STPreference.getName(activityContext)!! + "!") }
                .append(resources.getString(R.string.challenge_complete_description_3))
                .bold {
                    append(
                        resources.getString(
                            R.string.challenge_complete_description_4,
                            challengeDetails?.challengeType!!
                        )
                    )
                }
                .append(resources.getString(R.string.challenge_complete_description_5))

            setChallengeStatus(challengeJoined)
            setTitle(challengeTitleString)
            setDescription(challengeCompletedDescriptionString)
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun openTeamDetail(participantTeam: STParticipantTeams?) {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, participantTeam!!.name ?: "")
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_TEAM_DETAILS)
        container.putExtra(STConstants.CHALLENGE_ID, challengesId)
        container.putExtra(STConstants.CHALLENGE_STARTED, isChallengeStarted)
        container.putExtra(STConstants.CHALLENGE_COMPLETED, challengeDetails?.isCompleted)
        if (isNewMessage)
            container.putExtra(IS_NEW_MESSAGE, isNewMessage)
//        for (i in 1..300) {
//            participantTeam.teamMembers?.add(participantTeam.teamMembers.get(0));
////            println(i)
//        }
        participantTeam.teamMembers?.clear()
        container.putExtra(STConstants.PARTICIPANT_TEAM, participantTeam)
        startActivity(container)
    }

}
