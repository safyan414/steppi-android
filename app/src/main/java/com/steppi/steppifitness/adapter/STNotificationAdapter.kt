package com.steppi.steppifitness.adapter

import android.content.Context
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.notification.Challenge
import com.steppi.steppifitness.network.response.notification.NotificationData
import com.steppi.steppifitness.utils.STUtils
import kotlinx.android.synthetic.main.list_item_notification.view.*
import java.util.*
import kotlin.collections.ArrayList

class STNotificationAdapter(private val activityContext: Context) : STPaginationBaseAdapter() {
    private var onClickItem: OnClickItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> DeviceViewHolder(
                inflater.inflate(R.layout.list_item_notification, parent, false)
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            STConstants.ITEM -> {
                val notificationData = getItem(position) as NotificationData
                notificationData.let { data ->
                    data.title?.let { title ->
                        holder.itemView.notificationTitle.text = title
                    } ?: kotlin.run {
                        holder.itemView.notificationTitle.text =
                            STUtils.getNotificationTitle(activityContext, data.type)
                    }
                    holder.itemView.notificationText.text = data.text
                    data.createdAt?.let {
                        val createdDate = STUtils.getSyncedDateFromString(it)
                        val createdDay = Calendar.getInstance()
                        createdDate?.let { createdDateStamp ->
                            createdDay.time = createdDateStamp
                            holder.itemView.notificationTime.text =
                                STUtils.getMyPrettyDate(
                                    activityContext,
                                    createdDay.timeInMillis
                                )//STUtils.formatServerDate(data.createdAt)
                        }
                    }
                    if (data.isRead!!) {
                        holder.itemView.notificationLayout.background =
                            ContextCompat.getDrawable(activityContext, R.drawable.edit_profile_bg)
                    } else {
                        holder.itemView.notificationLayout.background =
                            ContextCompat.getDrawable(activityContext, R.drawable.edit_text_bg)
                    }
                    holder.itemView.setOnClickListener {
                        data.isInteractive?.let { isInteractive ->
                            if (isInteractive) {
                                when {
                                    data.reward != null ->
                                        data.reward.id?.let {
                                            onClickItem!!.clickRewardsDetails(it)
                                        }
                                    data.challenge != null -> {
                                        data.challenge.let { challenge ->

                                            var isNewMessage = false
                                            data.type?.let {
                                                if (it.equals("NEW_TEAM_DISCUSSION_MESSAGE") ||
                                                    it.equals("REPLIED_TO_YOUR_TEAM_DISCUSSION_MESSAGE")
                                                ) {
                                                    isNewMessage = true
                                                }
                                            }

                                            onClickItem!!.clickChallengesDetails(
                                                challenge,
                                                isNewMessage
                                            )
                                        }
                                    }

//                                    data.toughMudderSubChallenge != null -> {
//                                        data.toughMudderSubChallenge.let { toughMudderSubChallenge ->
//                                            onClickItem!!.clickToughMudderChallengeDetails(
//                                                toughMudderSubChallenge
//                                            )
//                                        }
//                                    }
                                    else -> {
                                    }
                                }
//                                when (data.type) {
//                                    STUtils.NotificationType.NEW_REWARD_AVAILABLE.name,
//                                    STUtils.NotificationType.REWARD_CUSTOM.name -> {
//                                        data.reward!!.id?.let {
//                                            onClickItem!!.clickRewardsDetails(it)
//                                        }
//                                    }
//                                    STUtils.NotificationType.NEW_PUBLIC_CHALLENGE_AVAILABLE.name,
//                                    STUtils.NotificationType.CORPORATE_CHALLENGE_ABOUT_TO_START.name,
//                                    STUtils.NotificationType.CHALLENGE_TARGET_STEPS_ACHIEVED.name,
//                                    STUtils.NotificationType.CORPORATE_CHALLENGE_COMPLETED.name,
//                                    STUtils.NotificationType.CORPORATE_CHALLENGE_STARTING_SOON.name -> {
//                                        data.challenge.let { challenge ->
//                                            onClickItem!!.clickChallengesDetails(challenge)
//                                        }
//                                    }
//                                }
                            }
                        }
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setNotificationData(isRefresh: Boolean, challengesListData: ArrayList<NotificationData>) {
        //this.challengesListData = challengesListData
        if (isRefresh) {
            mDataSet = ArrayList()
        }
        mDataSet.addAll(challengesListData)
        notifyDataSetChanged()
    }

    fun setOnClickItem(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    interface OnClickItem {
        fun clickRewardsDetails(rewardsId: String)
        fun clickChallengesDetails(challenge: Challenge?, isNewMessage: Boolean)

//        fun clickToughMudderChallengeDetails(challenge: ToughMudderSubChallenge?)
    }
}
