package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.visible

class STOkayCancelDialog(private val context: Context) : STBaseDialogHelper() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_ok_cancel, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)
    private val messageView: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.message)
    }
    private val cancelButton: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.cancelButton)
    }
    private val steppiLogo: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.steppiLogo)
    }
    private val okayBtn: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.okayBtn)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setMessage(message: String?) {
        STUtils.setValueToView(messageView, message)
    }

    fun setOkayText(text: String?) {
        STUtils.setValueToView(okayBtn, text)
    }

    fun setCancelText(text: String?) {
        STUtils.setValueToView(cancelButton, text)
    }

    fun isLogoVisible(visible: Boolean = true) {
        if (visible) {
            steppiLogo.visible()
        } else {
            steppiLogo.gone()
        }
    }

    fun isLogout() {
        steppiLogo.visible()
        steppiLogo.setImageResource(R.drawable.leave_icon)
    }

    fun isCancelVisible(visible: Boolean = true) {
        if (visible) {
            cancelButton.visible()
        } else {
            cancelButton.gone()
        }
    }

    fun cancelClickListener(func: (() -> Unit)? = null) =
        with(cancelButton) {
            setClickListener(func)
        }

    fun okayClickListener(func: (() -> Unit)? = null) =
        with(okayBtn) {
            setClickListener(func)
        }

    private fun View.setClickListener(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()

        }
}
