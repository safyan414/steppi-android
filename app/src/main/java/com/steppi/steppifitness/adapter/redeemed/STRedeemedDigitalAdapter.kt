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
import com.steppi.steppifitness.utils.loadImage
import kotlinx.android.synthetic.main.layout_after_redeem.view.*
import kotlinx.android.synthetic.main.list_item_digital_redeemed.view.*

class STRedeemedDigitalAdapter(
    private val activityContext: Context,
    private val mCallback: PaginationAdapterCallback? = null
) : STPaginationBaseAdapter() {
    private var copyReferralCode: CopyReferralCode? = null

    fun setCopyReferralCodeListener(listener: CopyReferralCode) {
        this.copyReferralCode = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> RedeemedDigitalViewHolder(
                inflater.inflate(R.layout.list_item_digital_redeemed, parent, false)
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // val redeemedRewardsData = redeemedRewardsList?.get(position)
        when (getItemViewType(position)) {
            STConstants.ITEM -> {
                val redeemedRewardsData = getItem(position) as RedeemedRewards
                redeemedRewardsData.let { redeemedRewards ->
                    redeemedRewards.reward?.let { rewardData ->
                        rewardData.merchant?.let {
                            holder.itemView.itemLogo.loadImage(activityContext, it.logo)
                        }
                        rewardData.merchant?.let { merchant ->
                            holder.itemView.merchantName.text = merchant.name
                        }
                        holder.itemView.rewardsTitle.text = rewardData.name
                        rewardData.description?.let {
                            if (it == "") {
                                holder.itemView.rewardsDescription.visibility = View.GONE
                            } else {
                                holder.itemView.rewardsDescription.text = it
                            }
                        } ?: kotlin.run {
                            holder.itemView.rewardsDescription.visibility = View.GONE
                        }
                        rewardData.requiredSteps?.let {
                            holder.itemView.stepsToRedeem.text = STUtils.formatNumber(it.toInt())
                        }
                        rewardData.redeemed?.let {
                            holder.itemView.redeemedSteps.text = STUtils.formatNumber(it.toInt())
                        }
                        if (rewardData.estimateSaving.toString().contains("AED")) {
                            holder.itemView.estimateSaving.text =
                                rewardData.estimateSaving.toString()
                                    .replace("AED", "", true).trim()
                        } else {
                            holder.itemView.estimateSaving.text =
                                rewardData.estimateSaving.toString()
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
                    redeemedRewards.createdAt?.let {
                        holder.itemView.redeemedDate.text =
                            STUtils.formatServerDate(it)
                    }
                    redeemedRewards.reward?.let {
                        it.expireOn?.let { expiresOn ->
                            holder.itemView.expireDate.text =
                                STUtils.formatServerDate(expiresOn)
                        }
                    }
                    redeemedRewards.redeemCode?.let {
                        holder.itemView.referralCodeText.text = it
                    }
                    holder.itemView.copyReferralCode.setOnClickListener {
                        val data = getItem(holder.adapterPosition) as RedeemedRewards
                        copyReferralCode!!.copyCode(data.redeemCode)
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, mCallback)
            }
        }
    }
    /*  override fun onBindViewHold(itemView: Any?, position: Int) {
      }
      override fun onCreateVH(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      }*/

    fun setRedeemedData(redeemedRewardsList: ArrayList<RedeemedRewards>) {
        mDataSet.addAll(redeemedRewardsList)
        notifyDataSetChanged()
    }

    class RedeemedDigitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface CopyReferralCode {
        fun copyCode(referralCode: String?)
    }
    /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */
    /* fun add(r: RedeemedRewards?) {
         redeemedRewardsList!!.add(r!!)
         notifyItemInserted(redeemedRewardsList!!.size - 1)
     }

     fun remove(r: RedeemedRewards?) {
         val position: Int = redeemedRewardsList!!.indexOf(r)
         if (position > -1) {
             redeemedRewardsList!!.removeAt(position)
             notifyItemRemoved(position)
         }
     }

     fun clear() {
         isLoadingAdded = false
         while (itemCount > 0) {
             remove(getItem(0))
         }
     }

     fun isEmpty(): Boolean {
         return itemCount == 0
     }

     fun addLoadingFooter() {
         isLoadingAdded = true
         add(RedeemedRewards())
     }

     fun removeLoadingFooter() {
         isLoadingAdded = false
         val position: Int = redeemedRewardsList!!.size - 1
         val result: RedeemedRewards? = getItem(position)
         if (result != null) {
             redeemedRewardsList?.removeAt(position)
             notifyItemRemoved(position)
         }
     }

     fun getItem(position: Int): RedeemedRewards? {
         return redeemedRewardsList?.get(position)
     }

     fun getListSize(): Int {
         return redeemedRewardsList!!.size
     }

     */
    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     *//*
    fun showRetry(show: Boolean, @Nullable errorMsg: String?) {
        retryPageLoad = show
        notifyItemChanged(redeemedRewardsList!!.size - 1)
        if (errorMsg != null) this.errorMsg = errorMsg
    }*/
}
