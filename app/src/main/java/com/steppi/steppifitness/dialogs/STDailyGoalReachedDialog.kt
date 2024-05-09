package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.steppi.steppifitness.R

class STDailyGoalReachedDialog(private val context: Context) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_daily_goal_reached, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogConfirm: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogUpdateConfirm)
    }

    private val closeDialog: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogUpdateCancel)
    }

    private val sparklesView: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.sparkles)
    }

    private val glowView: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.glow)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setAnimation(activityContext: AppCompatActivity) {
        val animation = AlphaAnimation(1.0f, 0.2f)
        animation.duration = 500
        animation.startOffset = 500
        animation.fillBefore = true
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
//        AnimatorSet().apply {
//            playTogether(
//
//            )
//        }
//        val animRotate =
//            AnimationUtils.loadAnimation(activityContext, R.anim.rotate_clockwise)
//        sparklesView.startAnimation(animRotate)
        sparklesView.startAnimation(animation)
        glowView.startAnimation(animation)
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
