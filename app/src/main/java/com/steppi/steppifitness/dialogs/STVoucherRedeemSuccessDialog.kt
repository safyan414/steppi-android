package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R
import com.steppi.steppifitness.network.response.category.RedeemRewardData
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.loadImage

class STVoucherRedeemSuccessDialog(
    private val context: Context, merchantListData: STMerchantData?,
    redeemRewardResponse: RedeemRewardData?
) : STBaseDialogHelper() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_voucher_redeem_successfully, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val closeDialog: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogOK)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    init {
        dialogView.findViewById<ImageView>(R.id.logo).loadImage(context, merchantListData?.logo)
        dialogView.findViewById<TextView>(R.id.merchant_name).text = merchantListData?.name
        redeemRewardResponse?.reward?.requiredSteps?.let {
            dialogView.findViewById<TextView>(R.id.stepsCount).text =
                STUtils.formatNumber(it.toInt())
                    .plus(context.resources.getString(R.string.redeem_steps))
        }
        if (redeemRewardResponse?.reward?.estimateSaving.toString().contains("AED")) {
            dialogView.findViewById<TextView>(R.id.estimateSaving).text =
                redeemRewardResponse?.reward?.estimateSaving.toString()
                    .replace("AED", "", true).trim()
        } else {
            dialogView.findViewById<TextView>(R.id.estimateSaving).text =
                redeemRewardResponse?.reward?.estimateSaving.toString()
        }
        dialogView.findViewById<TextView>(R.id.rewardTitle).text =
            redeemRewardResponse?.reward?.name
        dialogView.findViewById<TextView>(R.id.redemptionCode).text =
            redeemRewardResponse?.redemptionCode
    }

    fun setMessage(
        message: String?,
        callback: () -> Unit
    ) {
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
