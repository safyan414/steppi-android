package com.steppi.steppifitness.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_leader_board_list.view.*

class STMemberListAdapter(
    private val activityContext: Context,
    private val teamMembers: ArrayList<STTeamMember>,
    private val challengeStarted: Boolean?,
    private var challengeCompleted: Boolean?

) : RecyclerView.Adapter<STMemberListAdapter.MemberViewHolder>() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = -1
    private var filteredList: ArrayList<STTeamMember>? = null
    var isSearchedData: Boolean? = false

    init {
        filteredList = ArrayList()
        filteredList?.addAll(teamMembers)
    }

    interface OnItemClickListener {
        fun onItemClick(userId: String?)
        fun onCheerClick(teamMember: STTeamMember, position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_leader_board_list, parent, false)
        return MemberViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filteredList?.size ?: 0
    }

    fun getMembersList(): ArrayList<STTeamMember> {
        return teamMembers
    }

    fun setMembersList(teamMembersList: ArrayList<STTeamMember>) {
        this.filteredList?.clear()
        this.filteredList?.addAll(teamMembersList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val participantTeam = filteredList?.get(position)
//        participantTeam?.let { member ->
//            member.user?.let { user ->
//                holder.itemView.memberName.text = user.name
//                holder.itemView.profile.load(activityContext, user?.picture)
//                if (challengeStarted!!) {
//                    holder.itemView.layout_step_count.visible()
//                    if (!user?.steps.isNullOrEmpty()) {
//                        user.steps?.let { steps ->
//                            holder.itemView.steps.text =
//                                "${STUtils.formatNumber(steps.toInt())}" +
//                                        "${activityContext.getString(R.string.redeem_steps)}"
//                        }
//                    } else {
//                        holder.itemView.steps.text =
//                            activityContext.getString(R.string.zero) + activityContext.getString(R.string.redeem_steps)
//                    }
//                } else {
//                    holder.itemView.layout_step_count.gone()
//                }
//            }
//        }

        /*holder.itemView.setOnClickListener {
            onClickItem?.onItemClick()
        }*/
//        val myTeamLeaderBoardTeam = mDataSet[position] as STTeamMember

        participantTeam?.let { teamMember ->
            holder.itemView.mainBg.isSelected = false
            holder.itemView.position.visible()
            if (position <= 2) {

                if ((teamMember.totalSteps == "0" && teamMember?.type == "step")|| (teamMember.totalActiveMinutes == "0" && teamMember?.type == "active_minutes")|| (teamMember.totalCalories == "0" && teamMember?.type == "calories_burned")|| (teamMember.totalDistance == "0" && teamMember?.type == "distance_covered")) {

                    if (!challengeStarted!! || challengeCompleted!!) {
                        holder.itemView.cheerLayout.gone()
                        holder.itemView.position.gone()
                        holder.itemView.rankingImage.visible()

                        when (position) {
                            0 -> {
                                holder.itemView.mainBg.isSelected = true
                                holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                            }
                            1 -> {
                                holder.itemView.mainBg.isSelected = true
                                holder.itemView.rankingImage.setImageResource(R.drawable.win_label_two)
                            }
                            2 -> {
                                holder.itemView.mainBg.isSelected = true
                                holder.itemView.rankingImage.setImageResource(R.drawable.win_label_three)
                            }
                            else -> {
                                viewPositionNormal(holder.itemView, position)
                            }
                        }
                      //  holder.itemView.rankingImage.gone()
                    } else {
                        viewPositionNormal(holder.itemView, position)
                    }
                } else {
                    if (!challengeStarted!!) {
                        holder.itemView.cheerLayout.gone()
                        holder.itemView.position.gone()
                        holder.itemView.rankingImage.gone()
//                        viewPositionNormal(holder.itemView, position)
                    } else {
                        if (challengeCompleted!!) {
                            holder.itemView.cheerLayout.gone()
                        }
                        holder.itemView.position.gone()
                        holder.itemView.rankingImage.visible()
                        holder.itemView.mainBg.isSelected = false
                        if (isSearchedData == true) {
                            holder.itemView.mainBg.isSelected = false
                        }

                        when (position) {
                            0 -> {
                                holder.itemView.mainBg.isSelected = true
                                holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                            }
                            1 -> {
                                holder.itemView.mainBg.isSelected = true
                                holder.itemView.rankingImage.setImageResource(R.drawable.win_label_two)
                            }
                            2 -> {
                                holder.itemView.mainBg.isSelected = true
                                holder.itemView.rankingImage.setImageResource(R.drawable.win_label_three)
                            }
                            else -> {
                                viewPositionNormal(holder.itemView, position)
                            }
                        }
                    }
                }
            } else {
                if (!challengeStarted!! || challengeCompleted!!) {
                    holder.itemView.cheerLayout.gone()
                  //  holder.itemView.position.gone()
                    holder.itemView.position.text = (position + 1).toString()
                    holder.itemView.rankingImage.gone()
                } else {
                    viewPositionNormal(holder.itemView, position)
                }
            }
            holder.itemView.userName.text = teamMember.name ?: teamMember.user?.name
         //   holder.itemView.steps.text = STUtils.formatNumber(teamMember.totalSteps?.toInt())

            if (teamMember.type == "step") {
                holder.itemView.steps.text = teamMember.totalSteps
                holder.itemView.stepsLabelMyUser.text = "Steps"
            } else if (teamMember.type == "active_minutes"){
                holder.itemView.steps.text = teamMember.totalActiveMinutes
                holder.itemView.stepsLabelMyUser.text = "Mins"

            } else if (teamMember.type == "distance_covered") {
                holder.itemView.steps.text = teamMember.totalDistance
                holder.itemView.stepsLabelMyUser.text = "KM"

            } else if (teamMember.type == "calories_burned") {
                holder.itemView.steps.text = teamMember.totalCalories
                holder.itemView.stepsLabelMyUser.text = "Cals"
            }

            if (isSearchedData == true) {
                holder.itemView.mainBg.isSelected = false
                holder.itemView.position.gone()
                holder.itemView.rankingImage.gone()
                holder.itemView.pcTwoAnimationLeaderBoardPosition.gone()
            }

            teamMember.picture?.let {
                holder.itemView.userImageManRunning.gone()
                holder.itemView.userImage.visible()
                holder.itemView.userImage.load(activityContext, it) {
                    holder.itemView.userImageManRunning.visible()
                    holder.itemView.userImage.gone()
                }
            } ?: kotlin.run {
                teamMember.user?.picture?.let {
                    holder.itemView.userImageManRunning.gone()
                    holder.itemView.userImage.visible()
                    holder.itemView.userImage.load(activityContext, it) {
                        holder.itemView.userImageManRunning.visible()
                        holder.itemView.userImage.gone()
                    }
                } ?: kotlin.run {
                    holder.itemView.userImageManRunning.visible()
                    holder.itemView.userImage.gone()
                }
            }
            holder.itemView.setOnClickListener {
                onClickItem?.onItemClick(teamMember.userId)
            }
            holder.itemView.cheers.setOnClickListener {
                val teamMemberUser =
                    filteredList?.get(holder.adapterPosition) as STTeamMember
                if (!teamMemberUser.cheered!!) {
                    holder.itemView.cheerCount.text =
                        (teamMemberUser.cheerReceived!!.toInt() + 1).toString()
                    teamMemberUser.cheered = true
                    holder.itemView.cheerCount.textColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                    holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_enabled)
                    onClickItem?.onCheerClick(teamMemberUser, holder.adapterPosition)
                }
            }
            teamMember.cheered?.let {
                if (!it) {
                    teamMember.cheerReceived?.let { cheerReceived ->
                        if (cheerReceived == "0") {
                            holder.itemView.cheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                            holder.itemView.cheerCount.text =
                                activityContext.resources.getString(R.string.cheer_count_label)
                        } else {
                            holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_disabled)
                            holder.itemView.cheerCount.text = teamMember.cheerReceived
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
                    holder.itemView.cheerCount.text = teamMember.cheerReceived
                    holder.itemView.cheerCount.textColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                }
            }
        }
    }

    private fun viewPositionNormal(itemView: View, position: Int) {

        itemView.mainBg.isSelected = position <= 2

        if (isSearchedData == true) {
            itemView.mainBg.isSelected = false
        }
        itemView.position.visible()
        itemView.rankingImage.gone()
        itemView.position.text = (position + 1).toString()
    }

    fun isChallengeCompleted(challengeCompletedValue: Boolean) {
        challengeCompleted = challengeCompletedValue
    }

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
