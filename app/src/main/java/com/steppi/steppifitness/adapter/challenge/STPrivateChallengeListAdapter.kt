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
import com.steppi.steppifitness.network.response.challenges.data.STCreatePrivateChallengeData
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.list_item_private_challenge.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STPrivateChallengeListAdapter(private val activityContext: Context) :
    STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var selectedPosition = -1

    interface OnItemClickListener {
        fun onItemClick(challengeCreate: STCreatePrivateChallengeData?)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> ChallengeViewHolder(
                inflater.inflate(R.layout.list_item_private_challenge, parent, false)
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
                val privateChallenge = mDataSet[position] as STCreatePrivateChallengeData
//                val borderDrawable = holder.itemView.itemContainer.background as GradientDrawable
//                val drawable = holder.itemView.noOf.background as GradientDrawable
                when {
                    position % 3 == 0 -> {
                        holder.itemView.itemContainer.setBackgroundResource(R.drawable.rounded_theme_lightblue_stroke_small)
                        holder.itemView.noOf.setBackgroundResource(R.drawable.bg_blue_light)
                    }
                    position % 3 == 1 -> {
                        holder.itemView.itemContainer.setBackgroundResource(R.drawable.rounded_theme_orange_stroke_small)
                        holder.itemView.noOf.setBackgroundResource(R.drawable.button_bg_orange_small)

                    }
                    else -> {
                        holder.itemView.itemContainer.setBackgroundResource(R.drawable.rounded_theme_red_stroke_small)
                        holder.itemView.noOf.setBackgroundResource(R.drawable.button_bg_red_small)
                    }
                }
                holder.itemView.selectedImage.setOnClickListener {
                }
                STUtils.setValueToView(holder.itemView.noOf, privateChallenge.noOf.toString())
                STUtils.setValueToView(
                    holder.itemView.type,
                    privateChallenge.type.toString()
                        .plus(activityContext.getString(R.string.days_challenge_label))
                )
                STUtils.setValueToView(
                    holder.itemView.challengeDesc,
                    privateChallenge.description.toString()
                )
                STUtils.setValueToView(
                    holder.itemView.challengeCost,
                    activityContext.getString(R.string.cost_label) + NumberFormat.getNumberInstance(
                        Locale.US
                    ).format(
                        BigDecimal(privateChallenge.costSteps?.toDouble()!!).setScale(
                            0,
                            RoundingMode.HALF_EVEN
                        )
                    )
                            /*NumberFormat.getNumberInstance(Locale.US).format(privateChallenge.costSteps)*/
                            + activityContext.getString(R.string.steps_per_participant)
                )
                if (privateChallenge.images != null && privateChallenge.images!!.isNotEmpty()) {
                    holder.itemView.challengeImage.load(
                        activityContext,
                        privateChallenge.images?.get(0),
                        activityContext.resources.getDimension(R.dimen.margin_small).toInt()
                    )
                }
                holder.itemView.selectedImage.setOnClickListener {
                    selectedPosition = holder.adapterPosition
                    onClickItem?.onItemClick(mDataSet[holder.adapterPosition] as STCreatePrivateChallengeData)
                    notifyDataSetChanged()
                }
                holder.itemView.setOnClickListener {
                    selectedPosition = holder.adapterPosition
                    onClickItem?.onItemClick(mDataSet[holder.adapterPosition] as STCreatePrivateChallengeData)
                    notifyDataSetChanged()
                }
                holder.itemView.selectedImage.isChecked = selectedPosition == holder.adapterPosition
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    fun setPrivateChallengeData(challengeCreates: ArrayList<STCreatePrivateChallengeData>?) {
        challengeCreates?.let {
            mDataSet.addAll(it)
            notifyDataSetChanged()
        }
    }

    class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
