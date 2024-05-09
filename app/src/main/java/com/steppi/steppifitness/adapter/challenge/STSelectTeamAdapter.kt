package com.steppi.steppifitness.adapter.challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STLoadingViewHolder
import com.steppi.steppifitness.adapter.STPaginationBaseAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STChallengeTeamData
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_team.view.*

class STSelectTeamAdapter(selectedPos: Int) :
    STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = selectedPos

    interface OnItemClickListener {
        fun onItemClick(team: STChallengeTeamData, position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> TeamViewHolder(
                inflater.inflate(R.layout.list_item_team, parent, false)
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
                val team = mDataSet[position] as STChallengeTeamData
                STUtils.setValueToView(holder.itemView.teamName, team.name)
                if (selectedPosition == position) {
                    holder.itemView.selectedTeam.visible()
                } else {
                    holder.itemView.selectedTeam.gone()
                }
                holder.itemView.setOnClickListener {
                    selectedPosition = holder.adapterPosition
                    notifyDataSetChanged()
                    onClickItem?.onItemClick(team, selectedPosition)
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    fun setTeamData(teams: ArrayList<STChallengeTeamData>?) {
        teams?.let { teamsList ->
            val sortedList = teamsList.sortedBy { it.name }
            mDataSet.addAll(sortedList)
            notifyDataSetChanged()
        }
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
