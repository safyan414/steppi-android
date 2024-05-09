package com.steppi.steppifitness.adapter.challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STLoadingViewHolder
import com.steppi.steppifitness.adapter.STPaginationBaseAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STTeamMember
import com.steppi.steppifitness.utils.*
import com.steppi.steppifitness.views.STGothamRoundedLightTextView
import kotlinx.android.synthetic.main.list_item_leader_board_list.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STMyTeamLeaderBoardListAdapter(private val activityContext: Context) :
    STPaginationBaseAdapter(), Filterable {
    private var onClickItem: OnItemClickListener? = null
    private var challengeStarted: Boolean? = true
    private var challengeCompleted: Boolean? = true
    private var challengeDays: Int = 0
    private var isDFCChallenge: Boolean = false
    private var mDataSetList: ArrayList<STTeamMember> = ArrayList()
    private var oDFCListDataValue: STGothamRoundedLightTextView? = null

    interface OnItemClickListener {
        fun onItemClick(userId: String?)
        fun onCheerClick(teamMember: STTeamMember, position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> LeaderBoardViewHolder(
                inflater.inflate(R.layout.list_item_leader_board_list, parent, false)
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return mDataSetList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            STConstants.ITEM -> {
                val myTeamLeaderBoardTeam = mDataSetList[position]
                myTeamLeaderBoardTeam.let { teamMember ->
                    setPositionLabel(
                        teamMember,
                        holder,
                        position,
                        mDataSet as ArrayList<STTeamMember>
                    )
                    holder.itemView.userName.text = teamMember.name
                    if (!isDFCChallenge) {
                        teamMember.totalSteps?.let {
                            holder.itemView.steps.text = NumberFormat.getNumberInstance(Locale.US)
                                .format(
                                    BigDecimal(it.toDouble()).setScale(
                                        0,
                                        RoundingMode.HALF_EVEN
                                    )
                                )
                        }
                        holder.itemView.stepsLabelMyUser.visible()
                    } else {
                        holder.itemView.steps.text =
                            teamMember.achievedDailyTargets.toString() + "/" + challengeDays
                        holder.itemView.stepsLabelMyUser.gone()
                    }
                    teamMember.picture?.let {
                        holder.itemView.userImageManRunning.gone()
                        holder.itemView.userImage.visible()
                        holder.itemView.userImage.load(
                            activityContext, it
                        ) {
                            holder.itemView.userImageManRunning.visible()
                            holder.itemView.userImage.gone()
                        }
                    } ?: kotlin.run {
                        holder.itemView.userImageManRunning.visible()
                        holder.itemView.userImage.gone()
                    }
                    holder.itemView.setOnClickListener {
                        onClickItem?.onItemClick(teamMember.userId)
                    }
                    holder.itemView.cheers.setOnClickListener {
                        val teamMemberUser =
                            mDataSetList[holder.adapterPosition]
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
//                    if (isDFCChallenge) {
//                        holder.itemView.stepsMinutes.visible()
//                        var dailyStepsValue = activityContext.resources.getString(R.string.zero)
//                        var activeMinutesValue = activityContext.resources.getString(R.string.zero)
//                        teamMember.steps?.let { stepsData ->
//                            stepsData.steps?.let { dailySteps ->
//                                dailyStepsValue = dailySteps
//                            } ?: kotlin.run {
//                                dailyStepsValue =
//                                    activityContext.resources.getString(R.string.zero)
//                            }
//                            stepsData.activeMinutes?.let { dailyActiveMinutes ->
//                                activeMinutesValue = dailyActiveMinutes
//                            } ?: kotlin.run {
//                                activeMinutesValue =
//                                    activityContext.resources.getString(R.string.zero)
//                            }
//                            holder.itemView.stepsMinutes.text =
//                                "${STUtils.formatNumber(dailyStepsValue.toInt())}" +
//                                        " ${activityContext.resources.getString(R.string.steps)} | " +
//                                        "${STUtils.formatNumber(activeMinutesValue.toInt())} " +
//                                        "${activityContext.resources.getString(R.string.minutes_label)}"
//                        }
//                    } else {
//                        holder.itemView.stepsMinutes.gone()
//                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    private fun setPositionLabel(
        teamMember: STTeamMember,
        holder: RecyclerView.ViewHolder,
        rankPosition: Int,
        mDataSet: ArrayList<STTeamMember>
    ) {
        var position = rankPosition
        if (isDFCChallenge) {
            for (i in mDataSet.indices) {
                if (teamMember.name == mDataSet[i].name) {
                    position = i
                    break
                }
            }
        } else {
            position = rankPosition
        }
        if (position <= 2) {
            if (teamMember.totalSteps == "0") {
                if (!challengeStarted!! || challengeCompleted!!) {
                    holder.itemView.cheerLayout.gone()
                }
                viewPositionNormalZero(holder.itemView, position)
            } else {
                if (!challengeStarted!!) {
                    holder.itemView.cheerLayout.gone()
                    viewPositionNormal(holder.itemView, position)
                } else {
                    if (challengeCompleted!!) {
                        holder.itemView.cheerLayout.gone()
                    }
                    holder.itemView.position.gone()
                    holder.itemView.rankingImage.visible()
                    holder.itemView.mainBg.isSelected = true
                    when (position) {
                        0 -> {
                            holder.itemView.rankingImage.setImageResource(R.drawable.win_label_one)
                        }
                        1 -> {
                            holder.itemView.rankingImage.setImageResource(R.drawable.win_label_two)
                        }
                        2 -> {
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
            }
            viewPositionNormal(holder.itemView, position)
        }
    }

    private fun viewPositionNormalZero(itemView: View, position: Int) {
        itemView.position.visible()
        itemView.rankingImage.gone()
        itemView.position.text = (position + 1).toString()
    }

    private fun viewPositionNormal(itemView: View, position: Int) {
        itemView.mainBg.isSelected = position <= 2
        itemView.position.visible()
        itemView.rankingImage.gone()
        itemView.position.text = (position + 1).toString()
    }

    fun setValues(participantTeamList: List<STTeamMember>?) {
        participantTeamList?.let {
            mDataSet.addAll(it)
            mDataSetList = mDataSet as ArrayList<STTeamMember>
            notifyDataSetChanged()
        }
    }

    fun isChallengeStarted(challengeStartedValue: Boolean) {
        challengeStarted = challengeStartedValue
    }

    fun isChallengeCompleted(challengeCompletedValue: Boolean) {
        challengeCompleted = challengeCompletedValue
    }

    fun isDFCChallengeData(challengeDays: Int?, isDFCChallengeValue: Boolean) {
        if (challengeDays != null) {
            this.challengeDays = challengeDays
        }
        this.isDFCChallenge = isDFCChallengeValue
    }

    class LeaderBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                mDataSetList = if (charSearch.isEmpty()) {
                    mDataSet as ArrayList<STTeamMember>
                } else {
                    val resultList = ArrayList<STTeamMember>()
                    for (row in mDataSet as ArrayList<STTeamMember>) {
                        if (row.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = mDataSetList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mDataSetList = results?.values as ArrayList<STTeamMember>
                if (mDataSetList.isEmpty()) {
                    oDFCListDataValue?.visible()
                } else {
                    oDFCListDataValue?.gone()
                }
                notifyDataSetChanged()
            }
        }
    }

    fun noData(noDFCListData: STGothamRoundedLightTextView?) {
        oDFCListDataValue = noDFCListData
    }
}
