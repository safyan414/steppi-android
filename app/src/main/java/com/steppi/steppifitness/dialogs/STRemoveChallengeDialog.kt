package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R

class STRemoveChallengeDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_remove_challenge, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogConfirm: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogConfirm)
    }

    private val closeDialog: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogCancel)
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
