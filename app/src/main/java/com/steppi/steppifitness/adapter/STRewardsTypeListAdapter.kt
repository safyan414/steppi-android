package com.steppi.steppifitness.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.load
import kotlinx.android.synthetic.main.list_item_rewards.view.*

class STRewardsTypeListAdapter(private val activityContext: Context) : STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = -1
    //private var merchantData: STMerchantData? = null
    //private var merchantList: ArrayList<STMerchantData>? = null

    interface OnItemClickListener {
        fun onItemClick(merchantData: STMerchantData?)
        fun onFavoriteClick(
            merchantData: STMerchantData?,
            position: Int,
            position1: Int
        )
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*  val itemView = LayoutInflater.from(parent.context)
              .inflate(R.layout.list_item_rewards, parent, false)
          return RewardsTypeListViewHolder(itemView)*/
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> RewardsTypeListViewHolder(
                inflater.inflate(R.layout.list_item_rewards, parent, false)
            )
            else -> STLoadingViewHolder(
                this,
                inflater.inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    /* override fun getItemCount(): Int {
         return merchantList?.size ?: 0
     }*/
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val merchantData = merchantList?.get(position)
        when (getItemViewType(position)) {
            STConstants.ITEM -> {
                val merchantData = getItem(position) as STMerchantData
                holder.itemView.setOnClickListener {
                    if (STUtils.singleClick()) {
                        onClickItem?.onItemClick(getItem(holder.adapterPosition) as STMerchantData)
                    }
                }
//                Log.e("position","position-$position " + merchantData?.name)
                holder.itemView.brandName.text = merchantData.name
                holder.itemView.description.text = merchantData.description
                merchantData.site?.let {
                    holder.itemView.webSite.text = it
                }
                holder.itemView.logo.load(activityContext, merchantData.logo)
                if (merchantData.images?.size!! > 0)
                    holder.itemView.itemImage.load(
                        activityContext,
                        merchantData.images[0]
                    )
                STUtils.setImageSize(
                    activityContext, holder.itemView.itemImage,
                    STUtils.convertDpToPixel(activityContext, 25F).toInt()
                )
                if (!merchantData.favorite!!) {
                    holder.itemView.featureIcon.setImageResource(R.drawable.is_not_featured)
                } else {
                    holder.itemView.featureIcon.setImageResource(R.drawable.is_featured)
                }
                holder.itemView.featureIcon.setOnClickListener {
                    if (STUtils.singleClick()) {
                        onClickItem?.onFavoriteClick(
                            getItem(holder.adapterPosition) as STMerchantData,
                            holder.adapterPosition, position
                        )
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    class RewardsTypeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setMerchantLists(merchantList: ArrayList<STMerchantData>?) {
        //this.merchantList = merchantList
        merchantList?.let {
            mDataSet.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun setFavouriteData(favorite: Boolean?, posi: Int?) {
//        selectedPosition = posi!!
        (getItem(posi!!) as STMerchantData).favorite = favorite
        //merchantData?.favorite = favorite
        notifyDataSetChanged()
    }
}
