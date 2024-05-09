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
import com.steppi.steppifitness.network.response.challenges.data.STMVPTeamList
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_mvp_participant.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STMvpParticipantListAdapter(private val activityContext: Context) :
    STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var challengeStarted: Boolean? = true
    private var isChallengeCompleted: Boolean? = false

    interface OnItemClickListener {
        fun onItemClick(mvpTeamList: STMVPTeamList?)

        fun onCheerClick(mvpTeamList: STMVPTeamList, position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> LeaderBoardViewHolder(
                inflater.inflate(R.layout.list_item_mvp_participant, parent, false)
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
                val mvpTeamListData = mDataSet[position] as STMVPTeamList
                mvpTeamListData.let { mvpTeamList ->
                    holder.itemView.mvpTeamName.text = mvpTeamList.teamName
                    mvpTeamList.user?.let { userData ->
                        holder.itemView.mvpUserName.text = userData.name
                        userData.steps?.let {
                            holder.itemView.mvpUserSteps.text =NumberFormat.getNumberInstance(Locale.US)
                                .format(
                                    BigDecimal(it.toDouble()).setScale(
                                        0,
                                        RoundingMode.HALF_EVEN
                                    )
                                )
                            /*NumberFormat.getNumberInstance(Locale.US).format(it.toInt())*/
                        }
                        userData.picture?.let {
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
                    mvpTeamList.cheered?.let {
                        if (!it) {
                            mvpTeamList.cheerReceived?.let { cheerReceived ->
                                if (cheerReceived == "0") {
                                    holder.itemView.cheers.setImageResource(R.drawable.cheer_zero_count_disabled)
                                    holder.itemView.cheerCount.text =
                                        activityContext.resources.getString(R.string.cheer_count_label)
                                } else {
                                    holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_disabled)
                                    holder.itemView.cheerCount.text = mvpTeamList.cheerReceived
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
                            holder.itemView.cheerCount.text = mvpTeamList.cheerReceived
                            holder.itemView.cheerCount.textColor(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            )
                        }
                    }
                    if (!challengeStarted!! || isChallengeCompleted!!) {
                        holder.itemView.cheerLayout.gone()
                    }
                    holder.itemView.cheers.setOnClickListener {
                        val mvpTeamListValue =
                            mDataSet[holder.adapterPosition] as STMVPTeamList
                        if (!mvpTeamListValue.cheered!!) {
                            holder.itemView.cheerCount.text =
                                (mvpTeamListValue.cheerReceived!!.toInt() + 1).toString()
                            mvpTeamListValue.cheered = true
                            holder.itemView.cheerCount.textColor(
                                STUtils.getColor(
                                    activityContext,
                                    R.color.button_bg_enabled_color
                                )
                            )
                            holder.itemView.cheers.setImageResource(R.drawable.cheer_with_count_enabled)
                            onClickItem?.onCheerClick(mvpTeamListValue, holder.adapterPosition)
                        }
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    fun setData(mvps: ArrayList<STMVPTeamList>) {
        mvps.let {
            mDataSet.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun isChallengeStarted(challengeStartedValue: Boolean) {
        challengeStarted = challengeStartedValue
    }

    fun isChallengeCompleted(isCompleted: Boolean?) {
        isChallengeCompleted = isCompleted
    }

    class LeaderBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
