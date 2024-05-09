package com.steppi.steppifitness.adapter.challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STLoadingViewHolder
import com.steppi.steppifitness.adapter.STPaginationBaseAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STLeaderBoardChallengeUsers
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_leader_board_list.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STLeaderBoardListAdapter(private val activityContext: Context) : STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var challengeDays: Int = 0
    private var pc3WinnersPositions: ArrayList<Int>? = null

    interface OnItemClickListener {
        fun onItemClick(leaderBoardUser: STLeaderBoardChallengeUsers?)

        fun onCheerClick(leaderBoardUser: STLeaderBoardChallengeUsers?, position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_leader_board_list, parent, false)
        return RewardsViewHolder(itemView)*/
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> LeaderboardViewHolder(
                inflater.inflate(R.layout.list_item_leader_board_list, parent, false)
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
                val leaderBoardTeam = mDataSet[position] as STLeaderBoardChallengeUsers
                leaderBoardTeam.let { leaderBoardUsers ->
                    if (position <= 2) {
                        if (leaderBoardUsers.totalSteps == "0") {
                            if (!challengeStarted!! || isChallengeCompleted!!) {
                                holder.itemView.cheerLayout.gone()
                            }
                            viewPositionNormalZeroSteps(holder.itemView, position)
                        } else {
                            if (!challengeStarted!!) {
                                holder.itemView.cheerLayout.gone()
                                viewPositionNormal(holder.itemView, position)
                            } else {
                                if (isChallengeCompleted!!) {
                                    holder.itemView.cheerLayout.gone()
                                }
                                if (isPrivate) {
                                    if (position == 0) {
                                        holder.itemView.position.gone()
                                        holder.itemView.rankingImage.visible()
                                        holder.itemView.mainBg.isSelected = true
                                        holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                                    } else {
                                        holder.itemView.mainBg.isSelected = false
                                        viewPositionNormal(holder.itemView, position)
                                    }
                                } else if (isPc3) {
                                    pc3WinnersPositions?.let { pc3WinnersPositions ->
                                        if (pc3WinnersPositions.contains(position)) {
                                            holder.itemView.position.gone()
                                            holder.itemView.rankingImage.visible()
                                            holder.itemView.mainBg.isSelected = true
                                            holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                                        } else {
                                            holder.itemView.mainBg.isSelected = false
                                            viewPositionNormal(holder.itemView, position)
                                        }
                                    } ?: kotlin.run {
                                        holder.itemView.mainBg.isSelected = false
                                        viewPositionNormal(holder.itemView, position)
                                    }
                                } else {
                                    viewPositionSetting(holder.itemView, position)
                                }
                            }
                        }
                    } else {
                        if (!challengeStarted!! || isChallengeCompleted!!) {
                            holder.itemView.cheerLayout.gone()
                        }
                        if (isPc3) {
                            pc3WinnersPositions?.let { pc3WinnersPositions ->
                                if (pc3WinnersPositions.contains(position)) {
                                    holder.itemView.position.gone()
                                    holder.itemView.rankingImage.visible()
                                    holder.itemView.mainBg.isSelected = true
                                    holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                                } else {
                                    holder.itemView.mainBg.isSelected = false
                                    viewPositionNormal(holder.itemView, position)
                                }
                            }
                        } else {
                            viewPositionNormal(holder.itemView, position)
                        }
                    }
                    leaderBoardUsers.cheered?.let {
                        if (!it) {
                            leaderBoardUsers.cheerReceived?.let { cheerReceived ->
                                if (cheerReceived == "0") {
                                    holder.itemView.cheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                                    holder.itemView.cheerCount.text =
                                        activityContext.resources.getString(R.string.cheer_count_label)
                                } else {
                                    holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_disabled)
                                    holder.itemView.cheerCount.text = leaderBoardUsers.cheerReceived
                                }
                            }
                            holder.itemView.cheerCount.textColor(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.edit_text_bg_stroke_color
                                )
                            )
                        } else {
                            holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_enabled)
                            holder.itemView.cheerCount.text = leaderBoardUsers.cheerReceived
                            holder.itemView.cheerCount.textColor(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            )
                        }
                    }
                    holder.itemView.userName.text = leaderBoardUsers.name
                    if (!isPc3) {
                        leaderBoardUsers.totalSteps?.let {
                            holder.itemView.steps.text = NumberFormat.getNumberInstance(Locale.US)
                                .format(
                                    BigDecimal(it.toDouble()).setScale(
                                        0,
                                        RoundingMode.HALF_EVEN
                                    )
                                )
                            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
                            holder.itemView.stepsLabelMyUser.visible()
                        }
                    } else {
                        holder.itemView.steps.text =
                            leaderBoardUsers.achievedDailyTargets.toString() + "/" + challengeDays
                        holder.itemView.stepsLabelMyUser.gone()
                        if (leaderBoardUsers.userId == STPreference.getUserId(activityContext)) {
                            holder.itemView.next.visible()
                        } else {
                            holder.itemView.next.gone()
                        }

                        holder.itemView.next.setOnClickListener {
                            onClickItem?.onItemClick(leaderBoardUsers)
                        }
                    }
                    leaderBoardUsers.picture?.let {
                        holder.itemView.userImageManRunning.gone()
                        holder.itemView.userImage.visible()
                        holder.itemView.userImage.load(activityContext, it) {
                            holder.itemView.userImageManRunning.visible()
                            holder.itemView.userImage.gone()
                        }
                    } ?: kotlin.run {
                        holder.itemView.userImageManRunning.visible()
                        holder.itemView.userImage.gone()
//                        holder.itemView.userImageManRunning.setImageResource(R.drawable.woman_running)
                    }
                    holder.itemView.setOnClickListener {
                        onClickItem?.onItemClick(leaderBoardUsers)
                    }
                    holder.itemView.cheers.setOnClickListener {
                        val leaderBoardUser =
                            mDataSet[holder.adapterPosition] as STLeaderBoardChallengeUsers
                        if (!leaderBoardUser.cheered!!) {
                            holder.itemView.cheerCount.text =
                                (leaderBoardUser.cheerReceived!!.toInt() + 1).toString()
                            leaderBoardUser.cheered = true
                            holder.itemView.cheerCount.textColor(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            )
                            holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_enabled)
                            onClickItem?.onCheerClick(leaderBoardUser, holder.adapterPosition)
                        }
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    private fun viewPositionNormal(itemView: View, position: Int) {
        itemView.position.visible()
        itemView.rankingImage.gone()
        pc3WinnersPositions?.let { pc3WinnersPositions ->
            if (isPc3 && pc3WinnersPositions.isNotEmpty()) {
                itemView.mainBg.isSelected = pc3WinnersPositions.contains(position)
                itemView.position.text = (position + 2 - pc3WinnersPositions.size).toString()
            } else {
                if (!isPrivate) {
                    itemView.mainBg.isSelected = position <= 2
                } else {
                    itemView.mainBg.isSelected = position == 0
                }
                itemView.position.text = (position + 1).toString()
            }
        } ?: kotlin.run {
            if (!isPrivate) {
                itemView.mainBg.isSelected = position <= 2
            } else {
                itemView.mainBg.isSelected = position == 0
            }
            itemView.position.text = (position + 1).toString()
        }
    }

    private fun viewPositionNormalZeroSteps(itemView: View, position: Int) {
        itemView.position.visible()
        itemView.rankingImage.gone()
        itemView.position.text = (position + 1).toString()
    }

    private fun viewPositionSetting(itemView: View, position: Int) {
        itemView.position.gone()
        itemView.rankingImage.visible()
        itemView.mainBg.isSelected = true
        when (position) {
            0 -> {
                itemView.rankingImage.setImageResource(R.drawable.win_label_one)
            }
            1 -> {
                itemView.rankingImage.setImageResource(R.drawable.win_label_two)
            }
            2 -> {
                itemView.rankingImage.setImageResource(R.drawable.win_label_three)
            }
        }
    }

    fun setLeaderboardLists(merchantList: ArrayList<STLeaderBoardChallengeUsers>?) {
        merchantList?.let {
            mDataSet.addAll(it)
            notifyDataSetChanged()
        }
    }

    private var challengeStarted: Boolean? = true
    fun isChallengeStarted(challengeStartedValue: Boolean) {
        challengeStarted = challengeStartedValue
    }

    private var isPrivate: Boolean = false
    fun isPrivate(private: Boolean = false) {
        isPrivate = private
    }

    private var isPc3: Boolean = false
    fun setPC3Data(isPc3: Boolean = false, challengeDays: Int?) {
        this.isPc3 = isPc3
        if (challengeDays != null) {
            this.challengeDays = challengeDays
        }
        pc3WinnersPositions = getMaxIndices(mDataSet)
    }

    private var isChallengeCompleted: Boolean? = false
    fun isChallengeCompleted(isCompleted: Boolean?) {
        isChallengeCompleted = isCompleted
    }

    private fun getMaxIndices(values: ArrayList<Any>): ArrayList<Int> {
        val max = values.maxBy {
            (it as STLeaderBoardChallengeUsers).achievedDailyTargets ?: 0
        }

        val maxIndices = ArrayList<Int>()
        for (i in values.indices) {
            if ((values[i] as STLeaderBoardChallengeUsers).achievedDailyTargets != 0) {
                if ((values[i] as STLeaderBoardChallengeUsers).achievedDailyTargets == (max as STLeaderBoardChallengeUsers).achievedDailyTargets) {
                    maxIndices.add(Integer.valueOf(i))
                }
            }
        }
        return maxIndices
    }

    class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
