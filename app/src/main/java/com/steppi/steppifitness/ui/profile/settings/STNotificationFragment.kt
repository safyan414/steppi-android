package com.steppi.steppifitness.ui.profile.settings

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STNotificationAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.app.STConstants.IS_NEW_MESSAGE
import com.steppi.steppifitness.network.response.challenges.data.STChallengesListData
import com.steppi.steppifitness.network.response.notification.Challenge
import com.steppi.steppifitness.network.response.notification.NotificationData
import com.steppi.steppifitness.network.response.notification.STNotificationListResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingController
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingIntent
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_notification.*

class STNotificationFragment :
    STBaseViewModelFragment<STSettingIntent, STSettingState, STSettingController>(
        STSettingController::class.java
    ), PaginationAdapterCallback, SwipeRefreshLayout.OnRefreshListener,
    STNotificationAdapter.OnClickItem {
    private var notificationAdapter: STNotificationAdapter? = null
    private var isLoading = false
    private var isLastPage = false
    private var listTotal = 0
    private var offset = 0
    private var isRefreshList = true

    override fun getLayoutId(): Int {
        return R.layout.fragment_notification
    }

    override fun initViews() {
        //registerNotificationCountReceiver()
        initNotificationAdapter()
    }

    override fun processState(state: STSettingState) {
        when (state) {
            is STSettingState.Loading -> requestDidStart()
            is STSettingState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STSettingState.GetNotificationList -> {
                requestDidFinish()
                if (offset == 0)
                    invokeIntent(STSettingIntent.MarkAllRead)
                listTotal = state.notificationList?.total!!
                setMyChallengeList(state.notificationList)
            }
            is STSettingState.MarkAllRead -> {
                requestDidFinish()
                STConstants.MARK_ALL_NOTIFICATION_READ = true
            }
        }
    }

    override fun onViewModelReady() {
        invokeIntent(STSettingIntent.GetNotificationList(offset))
    }

    override fun retryPageLoad() {
        invokeIntent(STSettingIntent.GetNotificationList(offset))
    }

    private fun initNotificationAdapter() {
        notificationListPullRefresh.setOnRefreshListener(this)
        notificationListPullRefresh.setColorSchemeResources(
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color,
            R.color.button_bg_enabled_color
        )
        val layoutManager = LinearLayoutManager(activityContext)
        notificationList.layoutManager = layoutManager
        notificationList.addItemDecoration(
            STVerticalSpaceItemDecoration(
                STUtils.getDimen(
                    activityContext,
                    R.dimen.margin_medium
                )
            )
        )
        notificationAdapter = STNotificationAdapter(activityContext)
        notificationList.adapter = notificationAdapter
        notificationAdapter!!.setOnClickItem(this)
        addScrollListener(layoutManager)
    }

    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        notificationList.addOnScrollListener(object : EndlessScrollListener(
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

    private fun setMyChallengeList(notificationListResponse: STNotificationListResponse?) {
        notificationListPullRefresh?.isRefreshing = false
        notificationListResponse?.data?.let { notificationListData ->
            if (notificationListData.isNullOrEmpty()) {
                noData.visible()
            }
            if (offset > 0) {
                notificationAdapter!!.removeLoadingFooter()
                isLoading = false
            }
            notificationAdapter?.setNotificationData(
                isRefreshList,
                notificationListData as ArrayList<NotificationData>
            )
            if (listTotal > notificationAdapter!!.getListSize()) notificationAdapter!!.addLoadingFooter()
            else isLastPage = true

            offset = notificationAdapter!!.getListSize() - 1
        }
        isRefreshList = false
    }

    private fun refreshList() {
        notificationAdapter?.clear()
        isRefreshList = true
        offset = 0
        isLoading = false
        isLastPage = false
        invokeIntent(STSettingIntent.GetNotificationList(offset))
    }

    override fun onRefresh() {
        refreshList()
    }

    /*private fun registerNotificationCountReceiver() {
        LocalBroadcastManager.getInstance(activityContext)
            .registerReceiver(
                broadCastReceiver,
                IntentFilter(STConstants.BROADCAST_NOTIFICATION_UPDATE)
            )
    }

    private fun unregisterNotificationCountReceiver() {
        LocalBroadcastManager.getInstance(activityContext).unregisterReceiver(broadCastReceiver)
    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                STConstants.BROADCAST_NOTIFICATION_UPDATE -> {
                    // refresh list or add notification object on 0 index
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //unregisterNotificationCountReceiver()
    }*/
    override fun clickRewardsDetails(rewardsId: String) {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, getString(R.string.details))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_REWARDS_REDEEM)
        container.putExtra(STConstants.NOTIFICATION_REWARD_ID, rewardsId)
        //container.putExtra(STConstants.NOTIFICATION_REWARD_TYPE, rewardType)
        container.putExtra(STConstants.IS_NOTIFICATION, true)
        startActivity(container)
    }

    override fun clickChallengesDetails(challenge: Challenge?, isNewMessage: Boolean) {
        val challengesData: STChallengesListData? = STChallengesListData()
        challengesData?.id = challenge?.id
        challengesData?.challengeType = challenge?.challengeType
        challengesData?.type = challenge?.type
        challengesData?.theme = challenge?.theme
        challengesData?.name = challenge?.name
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, ""/*getString(R.string.challenge_details)*/)
        container.putExtra(
            STConstants.FRAGMENT_ID,
            STConstants.FRAGMENT_ID_CHALLENGE_DETAILS
        )
        container.putExtra(STConstants.CHALLENGES_TYPE, challenge?.challengeType)
        container.putExtra(STConstants.CHALLENGES_DATA_ID, challenge?.id)
        container.putExtra(STConstants.CHALLENGES_DATA, challengesData)
        container.putExtra(IS_NEW_MESSAGE, isNewMessage)

        startActivity(container)
    }


//    override fun clickToughMudderChallengeDetails(toughMudderSubChallenge: ToughMudderSubChallenge?) {
//        val challengesData: STChallengesListData? = STChallengesListData()
//        challengesData?.id = toughMudderSubChallenge?.id
//        toughMudderSubChallenge?.challengeType?.let {
//            challengesData?.challengeType = it
//        } ?: kotlin.run {
//            challengesData?.challengeType = STConstants.STEPPI_CHALLENGE
//        }
//        toughMudderSubChallenge?.type?.let {
//            challengesData?.type = it
//        } ?: kotlin.run {
//            challengesData?.type = STConstants.TOUGH_MUDDER_CHALLENGE
//        }
//        challengesData?.theme = toughMudderSubChallenge?.theme
//        challengesData?.name = toughMudderSubChallenge?.name
//        val container = Intent(activityContext, STContainerActivity::class.java)
//        container.putExtra(STConstants.FRAGMENT_NAME, ""/*getString(R.string.challenge_details)*/)
//        container.putExtra(
//            STConstants.FRAGMENT_ID,
//            STConstants.FRAGMENT_ID_CHALLENGE_DETAILS
//        )
//        toughMudderSubChallenge?.challengeType?.let {
//            container.putExtra(STConstants.CHALLENGES_TYPE, it)
//        } ?: kotlin.run {
//            container.putExtra(STConstants.CHALLENGES_TYPE, STConstants.STEPPI_CHALLENGE)
//        }
//        container.putExtra(STConstants.CHALLENGES_DATA_ID, toughMudderSubChallenge?.id)
//        container.putExtra(STConstants.CHALLENGES_DATA, challengesData)
//        startActivity(container)
//    }
}
