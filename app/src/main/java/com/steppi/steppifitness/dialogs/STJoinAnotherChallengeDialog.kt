package com.steppi.steppifitness.dialogs

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R
import com.steppi.steppifitness.utils.STUtils
import android.text.Spannable
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.spanWith

class STJoinAnotherChallengeDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_join_another_challenge, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogJoinChallenge: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogJoinChallenge)
    }
    private val titleText: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.titleText)
    }
    private val descriptionText: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.descriptionText)
    }
    private var challengeJoined = false

    init {
        val titleText = dialogView.findViewById<TextView>(R.id.titleText)
        val descriptionText = dialogView.findViewById<TextView>(R.id.descriptionText)

        val stepCountText = "100,000 step"
        val mySpannedText = SpannableString("Congratulation! we’ve reach our $stepCountText goal")
        mySpannedText.spanWith(stepCountText) {
            what = ForegroundColorSpan(STUtils.getColor(context, R.color.button_bg_enabled_color))
            flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        }
        titleText.text = mySpannedText

        val boldText1 = "Great job!"
        val boldText2 = "Steppi family"
        val nameText = "Mazen"

        val boldText1Spannable = SpannableString(boldText1)
        val boldSpan = StyleSpan(Typeface.BOLD)
        boldText1Spannable.setSpan(boldSpan, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val boldText2Spannable = SpannableString(boldText2)
        boldText2Spannable.setSpan(boldSpan, 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val spannedText =
            SpannableString("$boldText1Spannable Thank you $nameText your contribution is much appreciated and serve a great cause be proud of your achievement, on behalf of $boldText2Spannable WE THANK YOU")
        spannedText.spanWith(nameText) {
            what = ForegroundColorSpan(STUtils.getColor(context, R.color.button_bg_enabled_color))
            flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        }
        descriptionText.text = spannedText
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setMessage(
        message: String?,
        callback: () -> Unit
    ) {
    }

    fun setChallengeStatus(
        isChallengeJoined: Boolean
    ) {
        this.challengeJoined = isChallengeJoined
        if (!challengeJoined) {
            descriptionText.gone()
        }
    }

    fun setTitle(
        title: SpannableStringBuilder
    ) {
        titleText.text = title
    }

    fun setDescription(description: SpannableStringBuilder) {
        descriptionText.text = description
    }

    fun dialogJoinChallenge(func: (() -> Unit)? = null) =
        with(dialogJoinChallenge) {
            setClickListener(func)
        }

    private fun View.setClickListener(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
        }
}
