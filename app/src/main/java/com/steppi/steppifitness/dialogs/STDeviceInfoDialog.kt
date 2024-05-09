package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.utils.STUtils

class STDeviceInfoDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_device_info, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogConfirm: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.infoOkBtn)
    }
    private val deviceName: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.deviceName)
    }
    private val deviceImage: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.deviceLogo)
    }
    private val deviceInfoDescription: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.deviceInfoDescription)
    }

    private val closeDialog: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogUpdateCancel)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setData(data: STDeviceData, callback: () -> Unit) {

        deviceName.text = data.name

        when (data.deviceCode) {

            STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                deviceImage.setImageResource(R.drawable.googlefit_unselected)
                deviceInfoDescription.text =
                    context.getString(R.string.google_fit_device_info_description)
            }STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> {
                //todo: add huawei image
                deviceImage.setImageResource(R.drawable.googlefit_unselected)
                deviceInfoDescription.text =
                    context.getString(R.string.huawei_health_device_info_description)
            }
            STUtils.EnumFitnessDevice.SAMSUNG_HEALTH.name -> {
                deviceImage.setImageResource(R.drawable.samsung_health_unselected)
                deviceInfoDescription.text =
                    context.getString(R.string.samsung_kit_device_info_description)
            }
            STUtils.EnumFitnessDevice.FITBIT.name -> {
                deviceImage.setImageResource(R.drawable.fitbit_unselected)
                deviceInfoDescription.text =
                    context.getString(R.string.fit_bit_device_info_description)
            }
        }
    }

    fun confirmClick(func: (() -> Unit)? = null) =
        with(dialogConfirm) {
            setClickListener(func)
        }

    fun closeClick(func: (() -> Unit)? = null) =
        with(closeDialog) {
            setClickListener(func)
        }

    private fun View.setClickListener(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
        }
}
