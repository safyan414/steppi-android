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
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_leaderboard_pc2.view.*

class STLeaderBoardListPC2Adapter(private val activityContext: Context) :
    STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var challengeStarted: Boolean? = true
    private var challengeType: String? = ""
    var isShowParticipants: Boolean? = true
    var isFromViewAllList: Boolean? = false

    interface OnItemClickListener {
        fun onItemClick(participantTeam: STLeaderBoardChallengeUsers?)
        fun onCheerClick(participantTeam: STLeaderBoardChallengeUsers?, position: Int)
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
            STConstants.ITEM -> LeaderBoardViewHolder(
                inflater.inflate(R.layout.list_item_leaderboard_pc2, parent, false)
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
                val leaderBoardTeam = mDataSet[position] as STParticipantTeams
                leaderBoardTeam.let { participantTeams ->
                    if (position <= 2) {
                        if (!isFromViewAllList!!) {
                            if (challengeType == STConstants.DUBAI_FITNESS_CHALLENGE) {
                                if (participantTeams.engagementRate == 0.0) {
                                    viewPositionNormal(holder.itemView, position)
                                } else {
                                    setRankIcon(holder, position)
                                }
                            } else {
                                if (participantTeams.totalScore == "0") {
                                    viewPositionNormal(holder.itemView, position)
                                } else {
                                    setRankIcon(holder, position)
                                }
                            }
                        } else {
                            viewPositionNormal(holder.itemView, position)
                        }
                    } else {
                        viewPositionNormal(holder.itemView, position)
                    }
                    holder.itemView.teamName.text = participantTeams.name
                    participantTeams.participants?.let { participants ->
                        holder.itemView.pcTwoTeamMembers.text = participants
                    } ?: kotlin.run {
                        holder.itemView.pcTwoTeamMembers.text = "0"
                    }
                    if (challengeType == STConstants.DUBAI_FITNESS_CHALLENGE) {
                        participantTeams.engagementRate?.let { totalScore ->
                            holder.itemView.totalScore.text =
                                STUtils.formatNumber(totalScore.toInt())
                        } ?: kotlin.run {
                            holder.itemView.totalScore.text =
                                activityContext.getString(R.string.zero)
                        }
                        if (!isShowParticipants!!) {
                            holder.itemView.pcTwoTeamMembersLayout.gone()
                        } else {
                            holder.itemView.pcTwoTeamMembersLayout.visible()
                        }
                    } else {
                        participantTeams.totalScore?.let { totalScore ->
                            holder.itemView.totalScore.text =
                                STUtils.formatNumber(totalScore.toInt())
                        } ?: kotlin.run {
                            holder.itemView.totalScore.text =
                                activityContext.getString(R.string.zero)
                        }
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    private fun setRankIcon(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (!challengeStarted!!) {
            viewPositionNormal(holder.itemView, position)
        } else {
            holder.itemView.position.gone()
            holder.itemView.rankingImage.visible()
            when (position) {
                0 -> {
                    holder.itemView.myTeamCellLayout.setBackgroundResource(R.drawable.rounded_theme_yellow_stroke_small)
                    holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                }
                1 -> {
                    holder.itemView.myTeamCellLayout.setBackgroundResource(R.drawable.rounded_theme_orange_stroke_small)
                    holder.itemView.rankingImage.setImageResource(R.drawable.win_label_two)
                }
                2 -> {
                    holder.itemView.myTeamCellLayout.setBackgroundResource(R.drawable.rounded_theme_violet_stroke_small)
                    holder.itemView.rankingImage.setImageResource(R.drawable.win_label_three)
                }
            }
        }
    }

    private fun viewPositionNormal(itemView: View, position: Int) {
        itemView.position.visible()
        itemView.rankingImage.gone()
        itemView.position.text = (position + 1).toString()
        itemView.myTeamCellLayout.setBackgroundResource(R.drawable.rounded_theme_stroke_small)
    }

    fun setLeaderBoardLists(merchantList: ArrayList<STParticipantTeams>) {
        merchantList.let {
            mDataSet.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun isChallengeStarted(challengeStartedValue: Boolean) {
        challengeStarted = challengeStartedValue
    }

    fun setType(type: String?) {
        challengeType = type
    }

    fun showParticipants(showParticipants: Boolean?) {
        isShowParticipants = showParticipants
    }

    fun setIsViewAllList(isFromViewAll: Boolean) {
        isFromViewAllList = isFromViewAll
    }

    class LeaderBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
