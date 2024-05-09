package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R

class STDeviceNotConnectedDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_device_not_connected, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogConnectDevice: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogConnectDevice)
    }

    private val dialogCancelDevice: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogCancelDevice)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setMessage(message: String?, callback: () -> Unit) {
    }

    fun confirmClick(func: (() -> Unit)? = null) =
        with(dialogConnectDevice) {
            setClickListener(func)
        }

    fun closeClick(func: (() -> Unit)? = null) =
        with(dialogCancelDevice) {
            setClickListener(func)
        }

    private fun View.setClickListener(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
        }
}
