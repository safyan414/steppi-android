package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R

class STUpdateAppDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_update_app, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogConfirm: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogUpdateConfirm)
    }

    private val closeDialog: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogUpdateCancel)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setMessage(message: String?, callback: () -> Unit) {
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
