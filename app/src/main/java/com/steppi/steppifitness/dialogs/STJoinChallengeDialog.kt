package com.steppi.steppifitness.dialogs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.common.STWebViewActivity
import com.steppi.steppifitness.utils.STUtils

class STJoinChallengeDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_join_challenge, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogClickToAgree: CheckBox by lazy {
        dialogView.findViewById<CheckBox>(R.id.checkboxClickToAgree)
    }

    private val dialogOK: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogOK)
    }

    private val clickCheckboxLayout: LinearLayout by lazy {
        dialogView.findViewById<LinearLayout>(R.id.clickCheckbox)
    }

    private val termsAndCondition: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.termAndConditions)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setMessage(
        message: String?,
        callback: () -> Unit
    ) {
    }

    fun clickToAgree(func: (() -> Unit)? = null) =
        with(dialogClickToAgree) {
            setOnCheckedChangeListener(func)
        }

    fun clickCheckboxLayout(func: (() -> Unit)? = null) =
        with(clickCheckboxLayout) {
            setChangeListener(func)
        }

    fun dialogOK(func: (() -> Unit)? = null) =
        with(dialogOK) {
            setClickListener(func)
        }

    fun onTCClick() =
        with(termsAndCondition) {
            setClickListener {
                val termsIntent = Intent(context, STWebViewActivity::class.java)
                termsIntent.putExtra(
                    STConstants.WEB_URL,
                    BuildConfig.API_URL + STConstants.CHALLENGE_TERMS_CONDITION_URL
                )
                termsIntent.putExtra(
                    STConstants.WEB_HEADER,
                    context.resources.getString(R.string.terms_condition)
                )
                context.startActivity(termsIntent)
            }
        }

    fun isPleaseAgree(): Boolean = dialogClickToAgree.isChecked

    private fun View.setClickListener(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
        }

    private fun CheckBox.setOnCheckedChangeListener(func: (() -> Unit)?) =
        setOnCheckedChangeListener { _, _ ->
            func?.invoke()
            if (!dialogClickToAgree.isChecked) {
                dialogOK.background = STUtils.getDrawable(context, R.drawable.button_bg_normal)
                dialogOK.setTextColor(STUtils.getColor(context, R.color.indicator_normal))
                clickCheckboxLayout.background =
                    STUtils.getDrawable(context, R.drawable.button_bg_normal)
            } else {
                dialogOK.background = STUtils.getDrawable(context, R.drawable.button_bg_enabled)
                dialogOK.setTextColor(STUtils.getColor(context, R.color.theme_color))
                clickCheckboxLayout.background =
                    STUtils.getDrawable(context, R.drawable.button_bg_enabled)
            }
        }

    private fun View.setChangeListener(func: (() -> Unit)?) =
        setOnClickListener {
            if (!dialogClickToAgree.isChecked) {
                dialogClickToAgree.isChecked = true
                dialogOK.background = STUtils.getDrawable(context, R.drawable.button_bg_enabled)
                dialogOK.setTextColor(STUtils.getColor(context, R.color.theme_color))
                clickCheckboxLayout.background =
                    STUtils.getDrawable(context, R.drawable.button_bg_enabled)
            } else {
                dialogClickToAgree.isChecked = false
                dialogOK.background = STUtils.getDrawable(context, R.drawable.button_bg_normal)
                dialogOK.setTextColor(STUtils.getColor(context, R.color.indicator_normal))
                clickCheckboxLayout.background =
                    STUtils.getDrawable(context, R.drawable.button_bg_normal)
            }
        }
}
