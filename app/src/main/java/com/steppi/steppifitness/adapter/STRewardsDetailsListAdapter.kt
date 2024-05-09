package com.steppi.steppifitness.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.category.STStoresRewardsListData
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import kotlinx.android.synthetic.main.list_item_reward_details_list.view.*

class STRewardsDetailsListAdapter : STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = -1

    //private var merchantRewardsList: ArrayList<STStoresRewardsListData>? = null
    private var redeemRewardListener: RedeemReward? = null

    interface OnItemClickListener {
        fun onItemClick(merchantRewardsData: STStoresRewardsListData?)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    fun setRedeemRewardListener(listener: RedeemReward) {
        this.redeemRewardListener = listener
    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reward_details_list, parent, false)

        return RewardsViewHolder(itemView)
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> RewardsViewHolder(
                inflater.inflate(R.layout.list_item_reward_details_list, parent, false)
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    /*override fun getItemCount(): Int {
        return merchantRewardsList?.size ?: 0
    }*/
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val merchantRewardsData = merchantRewardsList?.get(position)
        when (getItemViewType(position)) {
            STConstants.ITEM -> {
                val merchantRewardsData = getItem(position) as STStoresRewardsListData
                holder.itemView.setOnClickListener {
                    onClickItem?.onItemClick(getItem(holder.adapterPosition) as STStoresRewardsListData)
                }
                merchantRewardsData.description?.let {
                    if (it == "") {
                        holder.itemView.rewardDetails.gone()
                    } else {
                        holder.itemView.rewardDetails.text = it
                    }
                } ?: kotlin.run {
                    holder.itemView.rewardDetails.gone()
                }
                holder.itemView.rewardTitle.text = merchantRewardsData.name
                merchantRewardsData.requiredSteps?.let {
                    holder.itemView.requiredSteps.text = STUtils.formatNumber(it.toInt())
                }
                if (merchantRewardsData.estimateSaving.toString().contains("AED")) {
                    holder.itemView.estimateSaving.text =
                        merchantRewardsData.estimateSaving.toString()
                            .replace("AED", "", true).trim()
                } else {
                    holder.itemView.estimateSaving.text =
                        merchantRewardsData.estimateSaving.toString()
                }
                holder.itemView.redeemReward.setOnClickListener {
                    redeemRewardListener?.redeemReward(merchantRewardsData)
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    fun setMerchantRewardsLists(
        isRefresh: Boolean,
        merchantRewardsList: ArrayList<STStoresRewardsListData>
    ) {
        //this.merchantRewardsList = merchantRewardsList
        if (isRefresh)
            mDataSet = ArrayList()

        mDataSet.addAll(merchantRewardsList)
        notifyDataSetChanged()
    }

    class RewardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface RedeemReward {
        fun redeemReward(rewardData: STStoresRewardsListData?)
    }
}
