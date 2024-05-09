package com.steppi.steppifitness.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.network.response.challenges.data.STParticipantTeams
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.list_item_my_teams_list.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STParticipantTeamsListAdapter(
    private val participantTeamList: List<STParticipantTeams>,
    private val challengeStarted: Boolean,
    private val myTeamIndex: Int
) : RecyclerView.Adapter<STParticipantTeamsListAdapter.RewardsViewHolder>() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick(participantTeam: STParticipantTeams?)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_my_teams_list, parent, false)

        return RewardsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return participantTeamList.size
    }

    override fun onBindViewHolder(holder: RewardsViewHolder, position: Int) {
        val participantTeam = participantTeamList[position]
        participantTeam.let { team ->
            //            holder.itemView.arrowNext.gone()
//            holder.itemView.userImage.gone()
            if (position <= 2) {
                if (team.teamSteps == "0") {
                    viewPositionNormal(holder.itemView, position)
                } else {
                    if (!challengeStarted) {
                        viewPositionNormal(holder.itemView, position)
                    } else {
                        holder.itemView.position.gone()
                        holder.itemView.rankingImageLayout.visible()
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
            } else {
                viewPositionNormal(holder.itemView, position)
            }


//            holder.itemView.position.text = (position + 1).toString()
            holder.itemView.teamName.text = team.name
            if (!team.totalScore.isNullOrEmpty()) {
                holder.itemView.totalScore.text =
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(team.totalScore).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
            } else {
                holder.itemView.totalScore.text = "0"
            }
            if (!team.dailyAverage.isNullOrEmpty()) {
                holder.itemView.dailyAverage.text =
                    NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(team.dailyAverage).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )
            } else {
                holder.itemView.dailyAverage.text = "0"
            }
        }
    }

    private fun viewPositionNormal(itemView: View, position: Int) {
        itemView.position.visible()
        itemView.rankingImageLayout.gone()
//        if (position >= myTeamIndex)
//            itemView.position.text = (position + 2).toString()
//        else
        itemView.position.text = (position + 1).toString()
        Log.d("position", itemView.position.text as String)
    }

    class RewardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
