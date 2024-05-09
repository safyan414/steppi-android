package com.steppi.steppifitness.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.challenges.data.STTeamDiscussionMessageListData
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.adapter_discussion_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class STDiscussionListAdapter(
    private val activityContext: Context
) : STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick()
        fun onEditMessage(position: Int, discussionData: STTeamDiscussionMessageListData)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscussionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_discussion_item, parent, false)

        return DiscussionViewHolder(itemView)
    }*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> DiscussionViewHolder(
                inflater.inflate(R.layout.adapter_discussion_item, parent, false)
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
                val discussionData = getItem(position) as STTeamDiscussionMessageListData
                discussionData.let {
                    holder.itemView.userName.text = it.user?.name
                    holder.itemView.discussion_content.text = it.message
                    holder.itemView.msgDate.text = it.updatedAt
                    it.createdAt?.let { createAt ->
                        val createdDate = STUtils.getSyncedDateFromString(createAt)
                        val createdDay = Calendar.getInstance()
                        createdDate?.let { createdDateStamp ->
                            createdDay.time = createdDateStamp
                            holder.itemView.msgDate.text =
                                STUtils.getMyPrettyDate(
                                    activityContext,
                                    createdDay.timeInMillis
                                )//STUtils.formatServerDate(data.createdAt)
                        }
                    }
                    holder.itemView.profile.load(activityContext, it.user?.picture)
                    holder.itemView.edit_message.setOnClickListener {
                        onClickItem?.onEditMessage(
                            holder.adapterPosition,
                            mDataSet[holder.adapterPosition] as STTeamDiscussionMessageListData
                        )
                    }
                    if (it.user?.id.equals(STPreference.getUserId(activityContext))) {
                        holder.itemView.edit_message.visible()
                    } else {
                        holder.itemView.edit_message.gone()
                    }
                }
                holder.itemView.setOnClickListener {
                    onClickItem?.onItemClick()
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    class DiscussionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setDiscussionListData(
        isRefresh: Boolean,
        DiscussionListData: ArrayList<STTeamDiscussionMessageListData>
    ) {
        if (isRefresh) {
            mDataSet = ArrayList()
        }
        mDataSet.addAll(DiscussionListData)
        notifyDataSetChanged()
    }
}
