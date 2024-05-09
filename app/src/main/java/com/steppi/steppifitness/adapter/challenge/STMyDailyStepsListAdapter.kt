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
import com.steppi.steppifitness.network.response.challenges.ChallengeUserSteps
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.visible
import kotlinx.android.synthetic.main.list_item_daily_steps.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class STMyDailyStepsListAdapter(private val activityContext: Context) : STPaginationBaseAdapter() {
    private var onClickItem: OnItemClickListener? = null
    private var showStar: Boolean = false

    interface OnItemClickListener {
        fun onItemClick()
        fun onCheerClick(position: Int)
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.onClickItem = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            STConstants.ITEM -> LeaderBoardViewHolder(
                inflater.inflate(R.layout.list_item_daily_steps, parent, false)
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
                val stepDataValue = mDataSet[position] as ChallengeUserSteps
                holder.itemView.dayList.text =
                    "${activityContext.getString(R.string.day_label)} ${(mDataSet.size - (position))}"
                stepDataValue.let { stepData ->
                    holder.itemView.dailySteps.text = NumberFormat.getNumberInstance(Locale.US)
                        .format(
                            BigDecimal(stepData.steps?.toDouble()!!).setScale(
                                0,
                                RoundingMode.HALF_EVEN
                            )
                        )/*NumberFormat.getNumberInstance(Locale.US)
                        .format(stepData.steps?.toInt())*/
                    holder.itemView.dateMonth.text = STUtils.getDate(
                        stepData.date, "yyyy-MM-dd", "dd-MMM-yyyy"
                    )/*MMM dd*/
                    holder.itemView.dateDay.text = STUtils.getDate(
                        stepData.date, "yyyy-MM-dd", "EEE"
                    )
                    if (showStar) {
                        if (stepDataValue.stared) {
                            holder.itemView.isFeatured.visible()
                        } else {
                            holder.itemView.isFeatured.gone()
                        }
                    } else {
                        holder.itemView.isFeatured.gone()
                    }
                }
            }
            STConstants.LOADING -> {
                (holder as STLoadingViewHolder).bindViews(retryPageLoad, errorMsg, null)
            }
        }
    }

    fun setData(stepsList: List<ChallengeUserSteps>, showStar: Boolean = false) {
        this.showStar = showStar
        stepsList.let {
            val list = it.sortedByDescending { it.date }
            mDataSet.addAll(list)

            notifyDataSetChanged()
        }
    }

    class LeaderBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
