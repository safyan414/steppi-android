package com.steppi.steppifitness.adapter.device

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.utils.STUtils
import kotlinx.android.synthetic.main.list_item_device.view.*

class STDeviceAdapter(
    private val activityContext: Context,
    private val deviceList: ArrayList<STDeviceData>?,
    private var deviceSelected: STDeviceData?,
    val listItemOnClick: (STDeviceData) -> Unit,
    val infoClick: (STDeviceData) -> Unit
) : RecyclerView.Adapter<STDeviceAdapter.DeviceViewHolder>() {
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_device, parent, false)

        return DeviceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return deviceList?.size ?: 0
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        deviceList?.let {
            val device = it[position]
            STUtils.setValueToView(holder.itemView.deviceName, device.name)
            /*if (selectedPosition == position) {
                holder.itemView.selectDevice.visible()
                holder.itemView.selectDevice.animateCheckedChange(true).start()
                holder.itemView.deviceName.setTextColor(
                    STUtils.getColor(
                        activityContext,
                        R.color.button_bg_enabled_color
                    )
                )
                STUtils.setBoldFont(activityContext, holder.itemView.deviceName)
            } else {
                holder.itemView.selectDevice.invisible()
                holder.itemView.deviceName.setTextColor(
                    STUtils.getColor(
                        activityContext,
                        R.color.text_white
                    )
                )
                STUtils.setBookFont(activityContext, holder.itemView.deviceName)
            }*/
            holder.itemView.setOnClickListener {
                deviceSelected = null
                selectedPosition = position
                listItemOnClick(device)
//                notifyDataSetChanged()
            }
//            holder.itemView.infoBtn.setOnClickListener {
//                infoClick(device)
//            }
            /*deviceSelected?.let { deviceSelected ->
                if (deviceSelected.id == device.id) {
                    holder.itemView.selectDevice.visible()
                    holder.itemView.selectDevice.animateCheckedChange(true).start()
                    holder.itemView.deviceName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.button_bg_enabled_color
                        )
                    )
                    STUtils.setBoldFont(activityContext, holder.itemView.deviceName)
                } else {
                    holder.itemView.selectDevice.invisible()
                    holder.itemView.deviceName.setTextColor(
                        STUtils.getColor(
                            activityContext,
                            R.color.text_white
                        )
                    )
                    STUtils.setBookFont(activityContext, holder.itemView.deviceName)
                }
            }*/
            when (device.deviceCode) {
                STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                    if (selectedPosition == position || deviceSelected != null && deviceSelected!!.deviceCode?.equals(
                            device.deviceCode
                        )!!
                    ) {
                        holder.itemView.deviceImage.setImageResource(R.drawable.googlefit_selected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info_blk)
                        selectedLayout(holder)
                    } else {
                        holder.itemView.deviceImage.setImageResource(R.drawable.googlefit_unselected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info)
                        unselectedLayout(holder)
                    }
                }
                STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> {
                    if (selectedPosition == position || deviceSelected != null && deviceSelected!!.deviceCode?.equals(
                            device.deviceCode
                        )!!
                    ) {
                        holder.itemView.deviceImage.setImageResource(R.drawable.huawei_health_selected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info_blk)
                        selectedLayout(holder)
                    } else {
                        holder.itemView.deviceImage.setImageResource(R.drawable.huawei_health_unselected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info)
                        unselectedLayout(holder)
                    }
                }
                STUtils.EnumFitnessDevice.SAMSUNG_HEALTH.name -> {
                    if (selectedPosition == position || deviceSelected != null && deviceSelected!!.deviceCode?.equals(
                            device.deviceCode
                        )!!
                    ) {
                        holder.itemView.deviceImage.setImageResource(R.drawable.samsung_health_selected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info_blk)
                        selectedLayout(holder)
                    } else {
                        holder.itemView.deviceImage.setImageResource(R.drawable.samsung_health_unselected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info)
                        unselectedLayout(holder)
                    }
                }
                STUtils.EnumFitnessDevice.FITBIT.name -> {
                    if (selectedPosition == position || deviceSelected != null && deviceSelected!!.deviceCode?.equals(
                            device.deviceCode
                        )!!
                    ) {
                        holder.itemView.deviceImage.setImageResource(R.drawable.fitbit_selected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info_blk)
                        selectedLayout(holder)
                    } else {
                        holder.itemView.deviceImage.setImageResource(R.drawable.fitbit_unselected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info)
                        unselectedLayout(holder)
                    }
                }
                STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> {
                    if (selectedPosition == position || deviceSelected != null && deviceSelected!!.deviceCode?.equals(
                            device.deviceCode
                        )!!
                    ) {
                        holder.itemView.deviceImage.setImageResource(R.drawable.garmin_selected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info_blk)
                        selectedLayout(holder)
                    } else {
                        holder.itemView.deviceImage.setImageResource(R.drawable.garmin_unselected)
                        holder.itemView.infoBtn.setImageResource(R.drawable.ic_info)
                        unselectedLayout(holder)
                    }
                }
            }
        }
    }

    private fun selectedLayout(holder: DeviceViewHolder) {
        holder.itemView.deviceName.setTextColor(
            STUtils.getColor(
                activityContext,
                R.color.theme_color
            )
        )
        STUtils.setBoldFont(activityContext, holder.itemView.deviceName)
        holder.itemView.deviceLayout.background =
            STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
    }

    private fun unselectedLayout(holder: DeviceViewHolder) {
        holder.itemView.deviceName.setTextColor(
            STUtils.getColor(
                activityContext,
                R.color.text_white
            )
        )
        STUtils.setBookFont(activityContext, holder.itemView.deviceName)
        holder.itemView.deviceLayout.background =
            STUtils.getDrawable(activityContext, R.drawable.button_bg_device)
    }

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
