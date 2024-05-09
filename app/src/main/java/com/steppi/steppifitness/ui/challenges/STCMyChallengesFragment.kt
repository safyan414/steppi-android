package com.steppi.steppifitness.ui.challenges

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STSteppiChallengesListAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.STSteppiChallengeListResponse
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesController
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesIntent
import com.steppi.steppifitness.ui.challenges.mvi.STChallengesState
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_my_chalenges.*
import kotlinx.android.synthetic.main.my_challenge_status_layout.*

class STCMyChallengesFragment :
    STBaseViewModelFragment<STChallengesIntent, STChallengesState, STChallengesController>(
        STChallengesController::class.java
    ), SwipeRefreshLayout.OnRefreshListener {
    private var myChallengeListAdapter: STSteppiChallengesListAdapter? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var isRefreshList = true

    private var joinChallengeData: STChallengesListData? = null
    private var joinChallengeItemPosition: Int? = null
    private var isOnGoing: Boolean? = true
    private var isUpComing: Boolean? = false
    private var isCorporate: Boolean? = false
    private var isCompleted: Boolean? = false
    var listPosition: Int? = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_chalenges
    }

    override fun initViews() {
        initMyChallengesList()
    }

    override fun onViewModelReady() {
        shimmer_view_container.startShimmerAnimation()
        shimmer_view_container.visible()
        when {
            isOnGoing!! -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_ONGOING
                )
            )
            isUpComing!! -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_UPCOMING
                )
            )
            isCompleted!! -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_COMPLETED
                )
            )
            else -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_CORPORATE
                )
            )
        }
        //        invokeIntent(STChallengesIntent.GetMyChallenges(offset))
    }

    override fun onRefresh() {
        clearAdapter()
        when {
            isOnGoing!! -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_ONGOING
                )
            )
            isUpComing!! -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_UPCOMING
                )
            )
            isCompleted!! -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_COMPLETED
                )
            )
            else -> invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_CORPORATE
                )
            )
        }
//        invokeIntent(STChallengesIntent.GetMyChallenges(offset))
    }

    override fun processState(state: STChallengesState) {
        when (state) {
//            is STChallengesState.Loading -> requestDidStart()
            is STChallengesState.Error -> {
//                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STChallengesState.GetMyChallenges -> {
//                requestDidFinish()
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.gone()
                listTotal = state.myChallengeListResponse?.total!!
                setMyChallengeList(state.myChallengeListResponse)
            }

            is STChallengesState.GetMyChallengesByType -> {
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.gone()
                listTotal = state.myChallengeListResponse?.total!!
                setMyChallengeList(state.myChallengeListResponse)
            }

            is STChallengesState.RemoveChallenge -> {
                state.deleteResponse.data?.archived?.let {
                    if (it) {
                        myChallengeListAdapter?.removeItem(listPosition!!)
                    }
                }
            }
        }
    }

    private fun initMyChallengesList() {
        challengeListPullRefresh.setOnRefreshListener(this)
        challengeListPullRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
        val layoutManager =
            LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false)
        list_challenges.layoutManager = layoutManager
        myChallengeListAdapter = STSteppiChallengesListAdapter(activityContext)
        list_challenges.adapter = myChallengeListAdapter
        myChallengeListAdapter?.setClickListener(object :
            STSteppiChallengesListAdapter.OnItemClickListener {

            override fun onDeleteClick(challengesData: STChallengesListData?, position: Int) {
                listPosition = position
                val dialog: Dialog = showRemoveChallengeDialog {
                    closeClick { dismissDialog() }
                    confirmClick {
                        challengesData?.id?.let {
                            invokeIntent(
                                STChallengesIntent.RemoveChallenge(
                                    challengesData.id!!,
                                    position
                                )
                            )
                        }

                        dismissDialog()
                    }
                }
                dialog.setSize(activityContext)
                dialog.show()
            }

            override fun onItemClick(challengesData: STChallengesListData?) {
                val container = Intent(activityContext, STContainerActivity::class.java)
                container.putExtra(
                    STConstants.FRAGMENT_NAME,
                    ""
                )
                container.putExtra(
                    STConstants.FRAGMENT_ID,
                    STConstants.FRAGMENT_ID_CHALLENGE_DETAILS
                )
                container.putExtra(STConstants.CHALLENGES_DATA, challengesData)
                container.putExtra(STConstants.CHALLENGES_TYPE, challengesData?.challengeType)
                container.putExtra(STConstants.CHALLENGES_ISPRIVATE, challengesData?.isPrivate)
                challengesData?.joinCode?.let {
                    container.putExtra(STConstants.PRIVATE_CHALLENGE_JOIN_CODE, it)
                }
                container.putExtra(STConstants.CHALLENGES_DATA_ID, challengesData?.id)
                startActivity(container)
            }

            override fun onJoinClick(challengesData: STChallengesListData?, position: Int) {
                joinChallengeData = challengesData
                joinChallengeItemPosition = position
                if (joinChallengeData?.status == "Completed") {
                    joinAnotherChallengeDialog(joinChallengeData)
                } else {
                    joinChallengeDialog()
                }
            }
        })
        addScrollListener(layoutManager)
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        list_challenges.addOnScrollListener(object : EndlessScrollListener(
            layoutManager, STConstants.VISIBLE_THRESHOLD
        ) {
            override fun loadMoreItems() {
                isLoading = true
                onViewModelReady()
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading

        })
    }

    @SuppressLint("ResourceType")
    private fun setMyChallengeList(steppiChallengeListResponse: STSteppiChallengeListResponse?) {
        challengeListPullRefresh?.isRefreshing = false
        steppiChallengeListResponse?.data?.let { challengeListData ->
            if (challengeListData.isNullOrEmpty()) {
                noData.visible()
                list_challenges.gone()
            } else {
                noData.gone()
                list_challenges.visible()
                list_challenges.bringToFront()
            }

            steppiChallengeListResponse?.corporate?.let { it ->

                it.tabTitle?.let {
                    corporateChallenge.text = it
                }

                corporateLogo.gone()
                corporateImage.visible()
                it.tabLogo?.let { imageUrl ->
                    corporateLogo.load(activityContext, imageUrl)
                    corporateLogo.visible()
                    corporateImage.invisible()
                    backgrounViewCorporate.setBackgroundColor(Color.TRANSPARENT)
                }?: kotlin.run {
                    corporateLogo.gone()
                    corporateImage.visible()
                }
            }

            if (offset > 0) {
                myChallengeListAdapter!!.removeLoadingFooter()
                isLoading = false
            }
            myChallengeListAdapter?.setChallengeListData(
                isRefreshList,
                challengeListData as ArrayList<STChallengesListData>,
                STConstants.MY_CHALLENGE_LIST
            )
            if (listTotal > myChallengeListAdapter!!.getListSize()) myChallengeListAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = myChallengeListAdapter!!.getListSize() - 1
        } ?: kotlin.run {
            noData.visible()
            list_challenges.gone()
        }
        isRefreshList = false
    }

    override fun onResume() {
        super.onResume()
        if (STConstants.UPDATE_MY_CHALLENGE_LIST) {
            isRefreshList = true
            offset = 0
            isLastPage = false
            STConstants.UPDATE_MY_CHALLENGE_LIST = false
//            invokeIntent(STChallengesIntent.GetMyChallenges(offset))
            when {
                isOnGoing!! -> invokeIntent(
                    STChallengesIntent.GetMyChallengesByType(
                        offset,
                        STConstants.API_END_POINT_ONGOING
                    )
                )
                isUpComing!! -> invokeIntent(
                    STChallengesIntent.GetMyChallengesByType(
                        offset,
                        STConstants.API_END_POINT_UPCOMING
                    )
                )
                isCompleted!! -> invokeIntent(
                    STChallengesIntent.GetMyChallengesByType(
                        offset,
                        STConstants.API_END_POINT_COMPLETED
                    )
                )
                else -> invokeIntent(
                    STChallengesIntent.GetMyChallengesByType(
                        offset,
                        STConstants.API_END_POINT_CORPORATE
                    )
                )
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
                    joinLeaveChallenge()
                    dismissDialog()
                }
            }
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun joinAnotherChallengeDialog(challengesData: STChallengesListData?) {
        val successDialog: Dialog = showJoinAnotherChallengeDialog {
            dialogJoinChallenge {
                dismissDialog()
            }

            val challengeTitleString = SpannableStringBuilder()
            if (!challengesData?.joined!!) {
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
                        STUtils.formatNumber(challengesData.targetSteps?.toInt())
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
                            challengesData.challengeType!!
                        )
                    )
                }
                .append(resources.getString(R.string.challenge_complete_description_5))
            setChallengeStatus(challengesData.joined!!)
            setTitle(challengeTitleString)
            setDescription(challengeCompletedDescriptionString)
        }
        successDialog.setSize(activityContext)
        successDialog.show()
    }

    private fun joinLeaveChallenge() {
        joinChallengeData?.let {
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

    @OnClick(R.id.addPrivateChallengeButton)
    fun addPrivateChallengeButtonClick() {
        if (STUtils.singleClick()) {
            val container = Intent(activityContext, STContainerActivity::class.java)
            container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.create_challenge))
            container.putExtra(
                STConstants.FRAGMENT_ID,
                STConstants.FRAGMENT_ID_ADD_PRIVATE_CHALLENGE
            )
            startActivity(container)
        }
    }

    @OnClick(R.id.onGoingCell)
    fun onGoingCellClick() {
        isUpComing = false
        isCorporate = false
        isCompleted = false
        onGoingCell.setBackgroundResource(R.drawable.button_bg_enabled_small)
        onGoingImage.setImageResource(R.drawable.my_challenge_ongoing_enabled)
        upComingCell.setBackgroundResource(R.color.transparent)
        upComingImage.setImageResource(R.drawable.my_challenge_upcoming_disabled)
        corporateCell.setBackgroundResource(R.color.transparent)
      //  corporateLogo.setImageResource(R.drawable.my_challenge_corporate_disabled)
        completedCell.setBackgroundResource(R.color.transparent)
        completedImage.setImageResource(R.drawable.my_challenge_completed_disabled)
        if (!isOnGoing!!) {
            isOnGoing = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_ONGOING
                )
            )
        }
    }

    @OnClick(R.id.upComingCell)
    fun upComingCellClick() {
        isOnGoing = false
        isCorporate = false
        isCompleted = false
        onGoingCell.setBackgroundResource(R.color.transparent)
        onGoingImage.setImageResource(R.drawable.my_challenge_ongoing_disabled)
        upComingCell.setBackgroundResource(R.drawable.button_bg_enabled_small)
        upComingImage.setImageResource(R.drawable.my_challenge_upcoming_enabled)
        corporateCell.setBackgroundResource(R.color.transparent)
       // corporateLogo.setImageResource(R.drawable.my_challenge_corporate_disabled)
        completedCell.setBackgroundResource(R.color.transparent)
        completedImage.setImageResource(R.drawable.my_challenge_completed_disabled)
        if (!isUpComing!!) {
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            isUpComing = true
            clearAdapter()
            invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_UPCOMING
                )
            )
        }
    }

    @OnClick(R.id.corporateCell)
    fun corporateCellClick() {
        isOnGoing = false
        isUpComing = false
        isCompleted = false
        onGoingCell.setBackgroundResource(R.color.transparent)
        onGoingImage.setImageResource(R.drawable.my_challenge_ongoing_disabled)
        upComingCell.setBackgroundResource(R.color.transparent)
        upComingImage.setImageResource(R.drawable.my_challenge_upcoming_disabled)
        corporateCell.setBackgroundResource(R.drawable.button_bg_enabled_small)
     //   corporateImage.setImageResource(R.drawable.my_challenge_corporate_enabled)
        completedCell.setBackgroundResource(R.color.transparent)
        completedImage.setImageResource(R.drawable.my_challenge_completed_disabled)
        if (!isCorporate!!) {
            isCorporate = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_CORPORATE
                )
            )
        }

    }

    @OnClick(R.id.completedCell)
    fun completedCellClick() {
        isOnGoing = false
        isUpComing = false
        isCorporate = false
        onGoingCell.setBackgroundResource(R.color.transparent)
        onGoingImage.setImageResource(R.drawable.my_challenge_ongoing_disabled)
        upComingCell.setBackgroundResource(R.color.transparent)
        upComingImage.setImageResource(R.drawable.my_challenge_upcoming_disabled)
        corporateCell.setBackgroundResource(R.color.transparent)
     //   corporateImage.setImageResource(R.drawable.my_challenge_corporate_disabled)
        completedCell.setBackgroundResource(R.drawable.button_bg_enabled_small)
        completedImage.setImageResource(R.drawable.my_challenge_completed_enabled)
        if (!isCompleted!!) {
            isCompleted = true
            clearAdapter()
            shimmer_view_container.startShimmerAnimation()
            shimmer_view_container.visible()
            invokeIntent(
                STChallengesIntent.GetMyChallengesByType(
                    offset,
                    STConstants.API_END_POINT_COMPLETED
                )
            )
        }
    }

    private fun clearAdapter() {
        myChallengeListAdapter?.clear()
        isLoading = false
        isLastPage = false
        listTotal = 0
        offset = 0
    }

//    @OnClick(R.id.create)
//    fun createNow() {
//        val container = Intent(activityContext, STContainerActivity::class.java)
//        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.private_challenge))
//        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_CHALLENGE_CREATE)
//        startActivity(container)
//    }
//
//    @OnClick(R.id.join)
//    fun joinNow() {
//        val container = Intent(activityContext, STContainerActivity::class.java)
//        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.join_challenge))
//        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_JOIN_CHALLENGE)
//        startActivity(container)
//    }
}
