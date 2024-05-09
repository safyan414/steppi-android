package com.steppi.steppifitness.ui.challenges.leaderboard

import android.animation.*
import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STChallengeDetailsResponse
import com.steppi.steppifitness.network.response.challenges.STMyTeam
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STLeaderBoardChallengeUsers
import com.steppi.steppifitness.network.response.challenges.data.STLeaderBoardMyUser
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.dfc_leader_board_sprite_list.*
import kotlinx.android.synthetic.main.fragment_dfc_challenge_leaderboard.*
import kotlinx.android.synthetic.main.leader_board_item_cell_dfc.view.*
import kotlinx.android.synthetic.main.list_item_leader_board_list.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STDFCChallengeLeaderBoardFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ) {
    private var starAnimator: ValueAnimator? = null
    private var cloudAnimator: ValueAnimator? = null
    val anim = AnimatorSet()
    private var challengesId: String? = null
    private var challengeDetailsDataList: STChallengeDetailsResponse? = null
    private var isChallengeStarted: Boolean = false
    private var myUserData: STLeaderBoardMyUser? = null
    private var isCompleted: Boolean? = false
    private var challengeDetails: STChallengesListData? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_dfc_challenge_leaderboard
    }

    override fun initViews() {
        initData()
        astronautsAnimation()
    }

    private fun astronautsAnimation() {
        val myAnim =
            AnimationUtils.loadAnimation(activityContext, R.anim.bounce)
        val myAnim2 =
            AnimationUtils.loadAnimation(activityContext, R.anim.bounce2)
        val myAnim3 =
            AnimationUtils.loadAnimation(activityContext, R.anim.bounce3)
        val animRotate =
            AnimationUtils.loadAnimation(activityContext, R.anim.rotate_clockwise)
        text_asset_first_position.startAnimation(myAnim)
        text_asset_icon_second_position.startAnimation(myAnim2)
        text_asset_icon_third_position.startAnimation(myAnim3)
        text_asset_icon_fourth_position.startAnimation(myAnim)
        text_asset_icon_fifth_position.startAnimation(myAnim2)
        text_asset_icon_sixth_position.startAnimation(myAnim3)
        text_asset_icon_seven_position.startAnimation(myAnim)
        text_asset_icon_eight_position.startAnimation(myAnim2)
        text_asset_icon_nine_position.startAnimation(myAnim3)
        text_asset_icon_tenth_position.startAnimation(myAnim)
    }

    private fun initData() {
        challengesId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        challengeDetailsDataList =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA_LIST) as? STChallengeDetailsResponse
    }

    override fun onViewModelReady() {
        challengeDetailsDataList?.let {
            setLeaderBoardData(it)
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
            is STChallengesState.GetViewAllLeaderboardData -> {
                requestDidFinish()
                state.leaderboardResponse?.data?.let { leaderBoardList ->
                    setLeaderBoardDataList(
                        challengeDetails!!,
                        leaderBoardList.challengeUser as ArrayList<STLeaderBoardChallengeUsers>
                    )
                    animateFromBottom()
                    isCompleted =
                        challengeDetails!!.status == STConstants.CHALLENGE_STATUS_COMPLETED
                    myUserData = leaderBoardList.myUser
                    setMyUserLayout(myUserData)
                }
            }
            is STChallengesState.GetTeamLeaderBoardAnimation -> {
                requestDidFinish()
                state.teamLeaderBoardAnimationResponse?.let {
                    it.data?.let {
                        isCompleted =
                            challengeDetails!!.status == STConstants.CHALLENGE_STATUS_COMPLETED
                        it.teams?.let { teams ->
                            setLeaderBoardTeamDataList(
                                challengeDetails!!, teams as ArrayList<STParticipantTeams>
                            )
                            animateFromBottom()
                        }
                        it.myTeam?.let { myTeam ->
                            setMyUserLayoutForTeam(myTeam)
                        }
                    }
                }
            }
            else -> {
            }
        }
    }

    override fun onDestroy() {
        cloudAnimator?.cancel()
        starAnimator?.cancel()
        super.onDestroy()
    }

    override fun onPause() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            cloudAnimator?.pause()
            starAnimator?.pause()
        }
        super.onPause()
    }

    override fun onResume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            cloudAnimator?.resume()
            starAnimator?.resume()
        }
        super.onResume()
    }

    private fun setLeaderBoardData(challengeDetailsResponse: STChallengeDetailsResponse) {
        challengeDetailsResponse.data?.let { challengeDetailsData ->
            challengeDetails = challengeDetailsData
            challengeDetailsData.startDate?.let { startDate ->
                val currentDate = STUtils.getFormattedDate(
                    Calendar.getInstance(),
                    "yyyy-MM-dd"
                )
                when (STUtils.compareDate(startDate, currentDate)) {
                    STConstants.CHALLENGE_NOT_STARTED -> {
                        isChallengeStarted = false
                    }
                    STConstants.CHALLENGE_STARTED -> {
                        isChallengeStarted = true
                    }
                }
            }
            if (challengeDetailsData.challengeType == STConstants.CORPORATE_CHALLENGE) {
                if (challengeDetailsData.theme != STConstants.CHALLENGE_THEME_NONE)
                    invokeIntent(
                        STChallengesIntent.GetTeamLeaderBoardAnimation(
                            challengeDetailsData.id!!,
                            0
                        )
                    )
            } else {
                when (challengeDetailsData.type) {
                    STConstants.STEPPI_CHALLENGE_2 -> {
                        if (challengeDetailsData.theme != STConstants.CHALLENGE_THEME_NONE)
                            invokeIntent(
                                STChallengesIntent.GetTeamLeaderBoardAnimation(
                                    challengeDetailsData.id!!,
                                    0
                                )
                            )
                    }
                    STConstants.DUBAI_FITNESS_CHALLENGE -> {
                        if (challengeDetailsData.theme != STConstants.CHALLENGE_THEME_NONE)
                            invokeIntent(
                                STChallengesIntent.GetTeamLeaderBoardAnimation(
                                    challengeDetailsData.id!!,
                                    0
                                )
                            )
                    }
                    else -> {
                        if (challengeDetailsData.theme != STConstants.CHALLENGE_THEME_NONE)
                            invokeIntent(
                                STChallengesIntent.GetViewAllLeaderboardData(
                                    challengeDetailsData.id!!,
                                    0
                                )
                            )
                    }
                }
            }
        }
    }

    private fun setMyUserLayoutForTeam(myTeamData: STMyTeam) {
        myUserLayout.setPadding(20, 0, 20, 0)
        myTeamData.let { myTeam ->
            userName.text = myTeam.name
            myTeam.engagementRate?.let {
                steps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )/*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
                stepsLabelMyUser.text = activityContext.getString(R.string.points_label)
                stepsLabelMyUser.setTextColor(STUtils.getColor(activityContext, R.color.light_blue))
            }
            userImage.gone()
            position.gone()
            cheerLayout.gone()
            pcTwoAnimationLeaderBoardPosition.visible()
            myTeam.rank?.let {
                pcTwoAnimationLeaderBoardPosition.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(it.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
                /*NumberFormat.getNumberInstance(Locale.US).format(it)*/
            }
            stepsRemainingLayout.gone()
//            myTeam.next?.let { stepsToNextLevel ->
//                if (stepsToNextLevel == "0") {
//                    stepsRemainingLayout.gone()
//                } else {
//                    stepsRemainingLayout.visible()
//                    stepsRemaining.text = NumberFormat.getNumberInstance(Locale.US)
//                        .format(
//                            BigDecimal(stepsToNextLevel.toDouble()).setScale(
//                                0,
//                                RoundingMode.HALF_EVEN
//                            )
//                        )
//                    /*NumberFormat.getNumberInstance(Locale.US)
//                        .format(stepsToNextLevel.toInt())*/
//                    centerText.text = activityContext.getString(R.string.score_to_reach_next_rank)
//                }
//            } ?: kotlin.run {
//                stepsRemainingLayout.gone()
//            }
            myTeam.rank?.let {
                if (it > 10) {
                    myUserLayout.visible()
                } else {
                    myUserLayout.gone()
                }
            } ?: kotlin.run {
                myUserLayout.gone()
            }
            if (!isChallengeStarted || isCompleted!!) {
//                myUserLayout.gone()
                stepsRemainingLayout.gone()
            }
        }
    }

    private fun setMyUserLayout(myUser: STLeaderBoardMyUser?) {
        myUserLayout.setPadding(20, 0, 20, 0)
        myUser?.let {
            userName.text = it.name
            it.totalSteps?.let { totalStepsValue ->
                steps.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(totalStepsValue.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
                /*NumberFormat.getNumberInstance(Locale.US).format(totalStepsValue.toInt())*/
            }
            it.picture?.let { pictureUrl ->
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
            position.text = it.rank.toString()
            it.rank?.let { rank ->
                position.text = NumberFormat.getNumberInstance(Locale.US)
                    .format(
                        BigDecimal(rank.toDouble()).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )/*NumberFormat.getNumberInstance(Locale.US).format(rank.toInt())*/
            }
            it.next?.let { stepsToNextLevel ->
                if (stepsToNextLevel == "0") {
                    stepsRemainingLayout.gone()
                } else {
                    stepsRemainingLayout.visible()
                    stepsRemaining.text = NumberFormat.getNumberInstance(Locale.US)
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
            it.cheered?.let { cheeredUser ->
                if (!cheeredUser) {
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

            it.rank?.let { rank ->
                if (rank.toInt() > 10) {
                    myUserLayout.visible()
                } else {
                    myUserLayout.gone()
                }
            } ?: kotlin.run {
                myUserLayout.gone()
            }

            if (!isChallengeStarted || isCompleted!!) {
                cheerLayout.gone()
                stepsRemainingLayout.gone()
//                myUserLayout.gone()
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun setLeaderBoardDataList(
        challengeDetailsData: STChallengesListData,
        challengeUsers: ArrayList<STLeaderBoardChallengeUsers>
    ) {
        for (usersListPosition in challengeUsers.indices) {
            val leaderBoardItemView: View = activityContext.layoutInflater
                .inflate(R.layout.leader_board_item_cell_dfc, null)
            when (usersListPosition) {
                9 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText10Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.visible()
                        sprint6.visible()
                        sprint7.visible()
                        sprint8.visible()
                        sprint9.visible()
                        sprint10.visible()
                    }
                }
                8 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText9Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.visible()
                        sprint6.visible()
                        sprint7.visible()
                        sprint8.visible()
                        sprint9.visible()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                7 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText8Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.visible()
                        sprint6.visible()
                        sprint7.visible()
                        sprint8.visible()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                6 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText7Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.visible()
                        sprint6.visible()
                        sprint7.visible()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                5 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText6Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.visible()
                        sprint6.visible()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                4 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText5Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.visible()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                3 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText4Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint1.visible()
                        sprint2.visible()
                        sprint3.visible()
                        sprint4.visible()
                        sprint5.gone()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                2 -> {
//                    if (!isChallengeStarted || challengeDetailsData.isPrivate!!) {
//                        leaderBoardItemView.win_label.gone()
//                        leaderBoardItemView.textPosition.visible()
//                    } else {
//                        challengeUsers[usersListPosition].totalSteps?.let {
//                            if (it == "0") {
//                                leaderBoardItemView.win_label.gone()
//                                leaderBoardItemView.textPosition.visible()
//                            } else {
//                                leaderBoardItemView.win_label.visible()
//                                leaderBoardItemView.textPosition.gone()
//                                leaderBoardItemView.win_label.setImageResource(R.drawable.win_label_three)
//                            }
//                        } ?: kotlin.run {
//                            leaderBoardItemView.win_label.gone()
//                            leaderBoardItemView.textPosition.visible()
//                        }
//                    }
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText3Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint4TextBg.invisible()
                        sprint5.gone()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                1 -> {
//                    if (!isChallengeStarted || challengeDetailsData.isPrivate!!) {
//                        leaderBoardItemView.win_label.gone()
//                        leaderBoardItemView.textPosition.visible()
//                    } else {
//                        challengeUsers[usersListPosition].totalSteps?.let {
//                            if (it == "0") {
//                                leaderBoardItemView.win_label.gone()
//                                leaderBoardItemView.textPosition.visible()
//                            } else {
//                                leaderBoardItemView.win_label.visible()
//                                leaderBoardItemView.textPosition.gone()
//                                leaderBoardItemView.win_label.setImageResource(R.drawable.win_label_two)
//                            }
//                        } ?: kotlin.run {
//                            leaderBoardItemView.win_label.gone()
//                            leaderBoardItemView.textPosition.visible()
//                        }
//                    }
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText2Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint3TextBg.invisible()
                        sprint4TextBg.invisible()
                        sprint5.gone()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                0 -> {
//                    if (!isChallengeStarted) {
//                        leaderBoardItemView.win_label.gone()
//                        leaderBoardItemView.textPosition.visible()
//                    } else {
//                        challengeUsers[usersListPosition].totalSteps?.let {
//                            if (it == "0") {
//                                leaderBoardItemView.win_label.gone()
//                                leaderBoardItemView.textPosition.visible()
//                            } else {
//                                leaderBoardItemView.win_label.visible()
//                                leaderBoardItemView.textPosition.gone()
//                                leaderBoardItemView.win_label.setImageResource(R.drawable.win_label_one)
//                            }
//                        } ?: kotlin.run {
//                            leaderBoardItemView.win_label.gone()
//                            leaderBoardItemView.textPosition.visible()
//                        }
//                    }
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text =
                        challengeUsers[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(challengeUsers[usersListPosition].totalSteps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(challengeUsers[usersListPosition].totalSteps?.toInt())*/
                    sprintText1Layout.addView(leaderBoardItemView)
                    if (challengeUsers.size == usersListPosition + 1) {
                        sprint2TextBg.invisible()
                        sprint3TextBg.invisible()
                        sprint4TextBg.invisible()
                        sprint5.gone()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
            }
        }
    }

    private fun setLeaderBoardTeamDataList(
        challengeDetailsData: STChallengesListData, teams: ArrayList<STParticipantTeams>
    ) {
        for (usersListPosition in teams.indices) {
            val leaderBoardItemView: View = activityContext.layoutInflater
                .inflate(R.layout.leader_board_item_cell_dfc, null)
            leaderBoardItemView.animStepsLabel.text = getString(R.string.points_label)
            when (usersListPosition) {
                9 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText10Layout.addView(leaderBoardItemView)
                    if (isCompleted!!) {
                        sprint4TextBg.invisible()
                        sprint5TextBg.invisible()
                        sprint5.visible()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                    } else {
                        if (teams.size >= usersListPosition + 1) {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5.visible()
                            sprint6.visible()
                            sprint7.visible()
                            sprint8.visible()
                            sprint9.visible()
                            sprint10.visible()
                        }
                    }
                }
                8 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText9Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        if (isCompleted!!) {
                            sprint4TextBg.invisible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        } else {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5.visible()
                            sprint6.visible()
                            sprint7.visible()
                            sprint8.visible()
                            sprint9.visible()
                            sprint10.gone()
                            sprint11.gone()
                        }
                    }
                }
                7 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText8Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        if (isCompleted!!) {
                            sprint4TextBg.invisible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        } else {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5.visible()
                            sprint6.visible()
                            sprint7.visible()
                            sprint8.visible()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        }
                    }
                }
                6 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText7Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        if (isCompleted!!) {
                            sprint4TextBg.invisible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        } else {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5.visible()
                            sprint6.visible()
                            sprint7.visible()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        }
                    }
                }
                5 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText6Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        if (isCompleted!!) {
                            sprint4TextBg.invisible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        } else {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5.visible()
                            sprint6.visible()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        }
                    }
                }
                4 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText5Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        if (isCompleted!!) {
                            sprint4TextBg.invisible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        } else {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        }
                    }
                }
                3 -> {
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText4Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        if (isCompleted!!) {
                            sprint4TextBg.invisible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        } else {
                            sprint1.visible()
                            sprint2.visible()
                            sprint3.visible()
                            sprint4.visible()
                            sprint5TextBg.invisible()
                            sprint5.visible()
                            sprint6.gone()
                            sprint7.gone()
                            sprint8.gone()
                            sprint9.gone()
                            sprint10.gone()
                            sprint11.gone()
                        }
                    }
                }
                2 -> {
//                    if (!isChallengeStarted || challengeDetailsData.isPrivate!!) {
//                        leaderBoardItemView.win_label.gone()
//                        leaderBoardItemView.textPosition.visible()
//                    } else {
//                        teams[usersListPosition].engagementRate?.let {
//                            if (it == 0.0) {
//                                leaderBoardItemView.win_label.gone()
//                                leaderBoardItemView.textPosition.visible()
//                            } else {
//                                leaderBoardItemView.win_label.visible()
//                                leaderBoardItemView.textPosition.gone()
//                                leaderBoardItemView.win_label.setImageResource(R.drawable.win_label_three)
//                            }
//                        } ?: kotlin.run {
//                            leaderBoardItemView.win_label.gone()
//                            leaderBoardItemView.textPosition.visible()
//                        }
//                    }
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText3Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        sprint4TextBg.invisible()
                        sprint5TextBg.invisible()
                        sprint5.visible()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                1 -> {
//                    if (!isChallengeStarted || challengeDetailsData.isPrivate!!) {
//                        leaderBoardItemView.win_label.gone()
//                        leaderBoardItemView.textPosition.visible()
//                    } else {
//                        teams[usersListPosition].engagementRate?.let {
//                            if (it == 0.0) {
//                                leaderBoardItemView.win_label.gone()
//                                leaderBoardItemView.textPosition.visible()
//                            } else {
//                                leaderBoardItemView.win_label.visible()
//                                leaderBoardItemView.textPosition.gone()
//                                leaderBoardItemView.win_label.setImageResource(R.drawable.win_label_two)
//                            }
//                        } ?: kotlin.run {
//                            leaderBoardItemView.win_label.gone()
//                            leaderBoardItemView.textPosition.visible()
//                        }
//                    }
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/

                    sprintText2Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        sprint3TextBg.invisible()
                        sprint4TextBg.invisible()
                        sprint5TextBg.invisible()
                        sprint5.visible()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
                0 -> {
//                    if (!isChallengeStarted) {
//                        leaderBoardItemView.win_label.gone()
//                        leaderBoardItemView.textPosition.visible()
//                    } else {
//                        teams[usersListPosition].engagementRate?.let {
//                            if (it == 0.0) {
//                                leaderBoardItemView.win_label.gone()
//                                leaderBoardItemView.textPosition.visible()
//                            } else {
//                                leaderBoardItemView.win_label.visible()
//                                leaderBoardItemView.textPosition.gone()
//                                leaderBoardItemView.win_label.setImageResource(R.drawable.win_label_one)
//                            }
//                        } ?: kotlin.run {
//                            leaderBoardItemView.win_label.gone()
//                            leaderBoardItemView.textPosition.visible()
//                        }
//                    }
                    leaderBoardItemView.textPosition.text = (usersListPosition + 1).toString()
                    leaderBoardItemView.leaderBoardUserName.text = teams[usersListPosition].name
                    leaderBoardItemView.leaderBoardUserName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.white
                        )
                    )
                    leaderBoardItemView.userSteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(teams[usersListPosition].engagementRate ?: 0.0).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US)
                        .format(teams[usersListPosition].totalScore?.toInt())*/
                    sprintText1Layout.addView(leaderBoardItemView)
                    if (teams.size == usersListPosition + 1) {
                        sprint2TextBg.invisible()
                        sprint3TextBg.invisible()
                        sprint4TextBg.invisible()
                        sprint5TextLayout.invisible()
                        sprint5.visible()
                        sprint6.gone()
                        sprint7.gone()
                        sprint8.gone()
                        sprint9.gone()
                        sprint10.gone()
                        sprint11.gone()
                    }
                }
            }
        }
    }


    private fun animateFromBottom() {
        anim.playTogether(
            sprint1TextLayout.leaderBoardAnimationVertical(
                startDelay = 0,
                fromY = 1000f,
                duration = 2000
            ),
            sprint2TextLayout.leaderBoardAnimationVertical(
                startDelay = 500,
                fromY = 1000f,
                duration = 2000
            ),
            sprint3TextLayout.leaderBoardAnimationVertical(
                startDelay = 1000,
                fromY = 1000f,
                duration = 2000
            ),
            sprint4TextLayout.leaderBoardAnimationVertical(
                startDelay = 1500,
                fromY = 1000f,
                duration = 2000
            ),
            sprint5TextLayout.leaderBoardAnimationVertical(
                startDelay = 2000,
                fromY = 1000f,
                duration = 2000
            ),
            sprint6TextLayout.leaderBoardAnimationVertical(
                startDelay = 2500,
                fromY = 1000f,
                duration = 2000
            ),
            sprint7TextLayout.leaderBoardAnimationVertical(
                startDelay = 3000,
                fromY = 1000f,
                duration = 2000
            ),
            sprint8TextLayout.leaderBoardAnimationVertical(
                startDelay = 3500,
                fromY = 1000f,
                duration = 2000
            ),
            sprint9TextLayout.leaderBoardAnimationVertical(
                startDelay = 4000,
                fromY = 1000f,
                duration = 2000
            ),
            sprint10TextLayout.leaderBoardAnimationVertical(
                startDelay = 4500,
                fromY = 1000f,
                duration = 2000
            )/*,
            endImage2.leaderBoardAnimationHorizontal(
                startDelay = 0,
                duration = 2000,
                fromX = 500f,
                toX = 0f
            ),
            startImage4.leaderBoardAnimationHorizontal(
                startDelay = 0,
                duration = 2000,
                fromX = -500f,
                toX = 0f
            )*/
        )
        anim.start()
    }

//    private fun <T> chopped(
//        list: List<T>,
//        L: Int
//    ): List<List<T>>? {
//        val parts: MutableList<List<T>> =
//            java.util.ArrayList()
//        val N = list.size
//        var i = 0
//        while (i < N) {
//            parts.add(
//                java.util.ArrayList(
//                    list.subList(i, Math.min(N, i + L))
//                )
//            )
//            i += L
//        }
//        return parts
//    }
}
