package com.steppi.steppifitness.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.steps_analytics.StepsAnalyticsData
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.STUtils
import kotlinx.android.synthetic.main.list_item_activity.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class STActivityDetailsAdapter(
    private val activityContext: Context,
    private val analyticsType: String?
) : RecyclerView.Adapter<STActivityDetailsAdapter.ActivityViewHolder>() {
    private var stepsArrayCount: ArrayList<StepsAnalyticsData>? = null
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_activity, parent, false)
        return ActivityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stepsArrayCount?.size ?: 0
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val stepsAnalyticsData = stepsArrayCount?.get(position)
        stepsAnalyticsData?.let { analyticsData ->
            holder.itemView.analyticsDay.text =
                "${STUtils.getDate(analyticsData.date, "yyyy-MM-dd", "EEEE")},"
            holder.itemView.analyticsDate.text =
                STUtils.getDate(analyticsData.date, "yyyy-MM-dd", "d MMMM")
            when (analyticsType) {
                STConstants.ANALYTICS_TYPE_DISTANCE -> {
                    STPreference.getUnitSelected(activityContext)?.let { unitSelected ->
                        if (unitSelected == activityContext.getString(R.string.kilometer)) {
                            holder.itemView.analyticsValue?.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                    .format(
                                        BigDecimal(analyticsData.distance!!.toDouble() / 1000).setScale(
                                            4,
                                            RoundingMode.HALF_EVEN
                                        )
                                    ) + " " + activityContext.getString(R.string.distance_label_km)
                        } else {
                            holder.itemView.analyticsValue?.text =
                                NumberFormat.getNumberInstance(Locale.US)
                                    .format(
                                        BigDecimal(STUtils.getMiles(analyticsData.distance!!.toDouble())).setScale(
                                            4,
                                            RoundingMode.HALF_EVEN
                                        )
                                    ) + " " + activityContext.getString(R.string.distance_label_mile)
                        }
                    }
                }
                STConstants.ANALYTICS_TYPE_CALORIES -> {
                    holder.itemView.analyticsValue.text =
                        "${NumberFormat.getNumberInstance(Locale.US)
                            .format(
                                BigDecimal(analyticsData.calories?.toDouble()!!).setScale(
                                    4,
                                    RoundingMode.HALF_EVEN
                                )
                            )} ${activityContext.getString(
                            R.string.calorie_label
                        )}"
                }
                STConstants.ANALYTICS_TYPE_STEPS -> {
                    holder.itemView.analyticsValue.text =
                        "${NumberFormat.getNumberInstance(Locale.US)
                            .format(analyticsData.steps?.toInt())} ${activityContext.getString(R.string.steps)}"
                }

                STConstants.ANALYTICS_TYPE_ACTIVEMINUTES -> {
                    holder.itemView.analyticsValue.text = "${NumberFormat.getNumberInstance(Locale.US).format(analyticsData.activeMinutes?.toInt())} ${activityContext.getString(R.string.active_minutes)}"
                }
                else -> {
                }
            }
        }
    }

    fun setStepsAnalyticsList(stepsArrayCountList: ArrayList<StepsAnalyticsData>) {
        stepsArrayCount = stepsArrayCountList
        stepsArrayCount?.reverse()
        notifyDataSetChanged()
    }

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
