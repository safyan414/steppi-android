package com.steppi.steppifitness.ui.challenges

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.challenge.STLeaderBoardListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.challenges.data.STLeaderBoardChallengeUsers
import com.steppi.steppifitness.network.response.challenges.data.STLeaderBoardMyUser
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_view_all_leaderboard.*
import kotlinx.android.synthetic.main.list_item_leader_board_list.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STViewAllLeaderBoardFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var challengeId: String? = null
    private var leaderBoardListAdapter: STLeaderBoardListAdapter? = null
    private var leaderBoardItemsList: List<STLeaderBoardChallengeUsers>? = null
    private var myUserData: STLeaderBoardMyUser? = null
    private var cheerClickPosition: Int? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var isChallengeStarted: Boolean = false
    private var isChallengeCompleted: Boolean = false
    private var challengeIsPrivate: Boolean? = false
    private var challengeType: String? = null
    private var challengeDetails: STChallengesListData? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_all_leaderboard
    }

    override fun initViews() {
        initData()
    }

    private fun initData() {
        challengeId = arguments?.getString(STConstants.CHALLENGES_DATA_ID)
        arguments?.getBoolean(STConstants.IS_CHALLENGE_STARTED, false)?.let {
            isChallengeStarted = it
        }
        arguments?.getBoolean(STConstants.IS_CHALLENGE_COMPLETED, false)?.let {
            isChallengeCompleted = it
        }
        arguments?.getBoolean(STConstants.CHALLENGES_ISPRIVATE, false)?.let {
            challengeIsPrivate = it
        }
        arguments?.getString(STConstants.CHALLENGES_TYPE)?.let {
            challengeType = it
        }
        challengeDetails =
            arguments?.getSerializable(STConstants.CHALLENGES_DATA) as? STChallengesListData
        initPullToRefresh()
        initLeaderBoardAdapter()
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

    override fun onViewModelReady() {
        getLeaderboardList()
    }

    override fun onRefresh() {
        leaderBoardListAdapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
        pageLayoutMain.invisible()
        invokeIntent(STChallengesIntent.GetViewAllLeaderboardData(challengeId!!, offset))
    }

    private fun getLeaderboardList() {
        invokeIntent(STChallengesIntent.GetViewAllLeaderboardData(challengeId!!, offset))
    }

    override fun processState(state: STChallengesState) {
        when (state) {
            is STChallengesState.Loading -> {
                requestDidStart()
            }
            is STChallengesState.Error -> {
                requestDidFinish()
                pullTolRefresh?.isRefreshing = false
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
                activityContext.finish()
            }
            is STChallengesState.GetViewAllLeaderboardData -> {
                requestDidFinish()
                pullTolRefresh?.isRefreshing = false
                pageLayoutMain.visible()
                state.leaderboardResponse?.let {
                    listTotal = it.total!!
                    it.data?.let { leaderboardList ->
                        leaderBoardItemsList = leaderboardList.challengeUser
                        myUserData = leaderboardList.myUser
                        setMyUserLayout(myUserData)
                        setLeaderBoardList()
                    }
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
        }
    }

    private fun initLeaderBoardAdapter() {
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)

        leaderBoardList.layoutManager = layoutManager
        leaderBoardListAdapter = STLeaderBoardListAdapter(activityContext)
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
                /*if (!participantTeam!!.cheered!! &&
                    !participantTeam.userId.equals(STPreference.getUserId(activityContext))
                ) {*/
                cheerClickPosition = position
                invokeIntent(
                    STChallengesIntent.CheerChallengeUser(
                        challengeId!!,
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
                //}
            }

        })
        leaderBoardList.adapter = leaderBoardListAdapter
        addScrollListener(layoutManager)
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        leaderBoardList.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                getLeaderboardList()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }


    private fun setLeaderBoardList() {
        leaderBoardList.visible()
        leaderBoardItemsList?.let {
            if (offset > 0) {
                leaderBoardListAdapter!!.removeLoadingFooter()
                isLoading = false
            }

            leaderBoardListAdapter?.setLeaderboardLists(it as ArrayList<STLeaderBoardChallengeUsers>)
            leaderBoardListAdapter?.isChallengeStarted(isChallengeStarted)
            leaderBoardListAdapter?.isChallengeCompleted(isChallengeCompleted)
            leaderBoardListAdapter?.isPrivate(challengeIsPrivate ?: false)
            leaderBoardListAdapter?.setPC3Data(
                challengeDetails?.type == STConstants.STEPPI_CHALLENGE_3,
                challengeDetails?.challengeDays
            )

            if (listTotal > leaderBoardListAdapter!!.getListSize()) leaderBoardListAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = leaderBoardListAdapter!!.getListSize() - 1
        }
    }

    @OnClick(R.id.cheers)
    fun onCheerClick() {
        myUserData?.let {
            if (!it.cheered!!) {
                invokeIntent(
                    STChallengesIntent.CheerChallengeUser(
                        challengeId!!,
                        it.id!!
                    )
                )
                it.cheerReceived = (it.cheerReceived!!.toInt() + 1).toString()
                it.cheered = true
                setMyUserLayout(it)
                leaderBoardItemsList?.let { leaderboardList ->
                    if (leaderboardList.size >= it.rank!!.toInt()) {
                        val mData = leaderboardList[it.rank.toInt() - 1]
                        mData.cheerReceived = (mData.cheerReceived!!.toInt() + 1).toString()
                        mData.cheered = true
                        leaderBoardListAdapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    @OnClick(R.id.myUserLayout)
    fun myUserLayout() {
        if (challengeType == STConstants.STEPPI_CHALLENGE_3) {
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
            container.putExtra(STConstants.CHALLENGES_DATA_ID, challengeId)
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

    private fun setMyUserLayout(myUser: STLeaderBoardMyUser?) {
        myUser?.let {
            userName.text = it.name
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
                    steps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(totalSteps.toDouble()).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
                    /*NumberFormat.getNumberInstance(Locale.US).format(totalSteps.toInt())*/
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
            }
            /*it.cheerReceived?.let { cheerReceived ->
                if (cheerReceived.isNotEmpty() && cheerReceived != "0") {
                    cheerCount.visible()
                    cheers.setImageResource(R.drawable.cheer_with_count_disabled)
                    cheerCount.text = it.cheerReceived
                } else {
                    cheerCount.gone()
                }
            }*/

            it.cheered?.let { myUserCheered ->
                if (!myUserCheered) {
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
            it.rank?.let { myUserRank ->
                if (myUserRank.toInt() > 10) {
                    myUserLayout.visible()
                } else {
                    myUserLayout.gone()
                }
            } ?: kotlin.run {
                myUserLayout.gone()
            }
        } ?: kotlin.run {
            myUserLayout.gone()
        }
    }
}
