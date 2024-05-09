package com.steppi.steppifitness.adapter.redeemed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STLoadingViewHolder
import com.steppi.steppifitness.adapter.STPaginationBaseAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.user.RedeemedRewards
import com.steppi.steppifitness.utils.PaginationAdapterCallback
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.STUtils.replaceArabicNumbers
import com.steppi.steppifitness.utils.loadImage
import kotlinx.android.synthetic.main.list_item_in_store_redeemed.view.*

class STRedeemedInStoreAdapter(
    private val activityContext: Context,
    private val mCallback: PaginationAdapterCallback? = null
) : STPaginationBaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> RedeemedInStoreViewHolder(
                inflater.inflate(R.layout.list_item_in_store_redeemed, parent, false)
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
                val redeemedRewardsData = getItem(position) as RedeemedRewards
                redeemedRewardsData.let { redeemedRewards ->
                    redeemedRewards.reward?.let { rewardData ->
                        rewardData.merchant?.let {
                            holder.itemView.itemLogo.loadImage(activityContext, it.logo)
                        }
                        holder.itemView.itemName.text = rewardData.name
                        rewardData.description?.let {
                            if (it == "") {
                                holder.itemView.itemDescriptionTitle.visibility = View.GONE
                                holder.itemView.itemDescription.visibility = View.GONE
                            } else {
                                holder.itemView.itemDescriptionTitle.text = it
                                holder.itemView.itemDescription.text = it
                            }
                        } ?: kotlin.run {
                            holder.itemView.itemDescriptionTitle.visibility = View.GONE
                            holder.itemView.itemDescription.visibility = View.GONE
                        }
                        rewardData.requiredSteps?.let {
                            holder.itemView.stepsToRedeem.text = STUtils.formatNumber(it.toInt())
                        }
                        rewardData.redeemed?.let {
                            holder.itemView.redeemedSteps.text = STUtils.formatNumber(it.toInt())
                        }
                        if (rewardData.estimateSaving.toString().contains("AED")) {
                            holder.itemView.estimateSaving.text =
                                "${STUtils.formatNumber(rewardData.estimateSaving)}"
                                    .replace("AED", "", true).trim()
                        } else {
                            holder.itemView.estimateSaving.text =
                                "${STUtils.formatNumber(rewardData.estimateSaving)}"
                        }
                        /*rewardData.estimateSaving?.let { rewardsEstimateSaving ->
                            if (rewardData.estimateSaving.toString().contains("AED")) {
                                holder.itemView.aedSaving.text =
                                    "${STUtils.formatNumber(rewardData.estimateSaving)}${activityContext.resources.getString(
                                        R.string.aed_saving
                                    )}"
                            } else {
                                holder.itemView.aedSaving.text =
                                    "${STUtils.formatNumber(rewardData.estimateSaving)}${activityContext.resources.getString(
                                        R.string.aed
                                    )}${activityContext.resources.getString(
                                        R.string.aed_saving
                                    )}"
                            }
                        }*/
                    }
                    holder.itemView.redemptionCode.text = redeemedRewards.redemptionCode?.replaceArabicNumbers()
                    holder.itemView.redeemedIn.text =
                        STUtils.formatServerDate(redeemedRewards.createdAt)
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, mCallback)
            }
        }
    }

    fun setRedeemedData(redeemedRewardsList: ArrayList<RedeemedRewards>) {
        mDataSet.addAll(redeemedRewardsList)
        notifyDataSetChanged()
    }

    class RedeemedInStoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
