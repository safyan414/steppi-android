package com.steppi.steppifitness.dialogs

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.textChangeObserver

class STJoinDFCChallengeDialog(private val context: Context) : STBaseDialogHelper() {
    var joinCode: String? = ""

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_join_dfc_challenge, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogOK: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogOK)
    }

    private val editTextChallengeCode: EditText by lazy {
        dialogView.findViewById<EditText>(R.id.challengeCode)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun clickToAgree() =
        with(editTextChallengeCode) {
            this.textChangeObserver {
                if (it.trim().isNotEmpty()) {
                    dialogOK.background =
                        STUtils.getDrawable(context, R.drawable.button_selector_enabled)
                    dialogOK.setTextColor(STUtils.getColor(context, R.color.theme_color))
                } else {
                    dialogOK.background =
                        STUtils.getDrawable(context, R.drawable.button_selector_normal)
                    dialogOK.setTextColor(STUtils.getColor(context, R.color.text_white))
                }
            }
            this.afterTextChanged {
                if (it.trim().isNotEmpty()) {
                    joinCode = it.trim()
                }
            }
        }

    fun isPleaseAgree(): Boolean = !STUtils.getValueFromView(editTextChallengeCode).isNullOrEmpty()

    fun getChallengeJoinCode() = joinCode

    fun dialogOK(func: (() -> Unit)? = null) =
        with(dialogOK) {
            setClickListener(func)
        }

    private fun View.setClickListener(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
        }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}
