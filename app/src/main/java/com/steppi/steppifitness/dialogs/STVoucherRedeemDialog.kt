package com.steppi.steppifitness.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.category.RewardDetails
import com.steppi.steppifitness.network.response.category.STStoresRewardsListData
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.gone
import com.steppi.steppifitness.utils.loadImage
import com.steppi.steppifitness.utils.visible
import com.steppi.steppifitness.views.OtpView

class STVoucherRedeemDialog(
    private val context: Context, merchantListData: STMerchantData?,
    merchantRewardsData: STStoresRewardsListData?,
    rewardDetail: RewardDetails?
) : STBaseDialogHelper() {
    var pinData: String? = ""

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_voucher_redeem, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private val dialogConfirm: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogConfirm)
    }

    private val closeDialog: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.dialogCancel)
    }

    private val pin: OtpView by lazy {
        dialogView.findViewById<OtpView>(R.id.pin)
    }

    private val expireDate: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.expireDate)
    }

    private val site: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.site)
    }

    private val inStoreLayout: LinearLayout by lazy {
        dialogView.findViewById<LinearLayout>(R.id.inStoreLayout)
    }

    private val digitalLayout: LinearLayout by lazy {
        dialogView.findViewById<LinearLayout>(R.id.digitalLayout)
    }

    init {
        merchantListData?.logo?.let {
            dialogView.findViewById<ImageView>(R.id.logo).loadImage(context, it)
        }
        dialogView.findViewById<TextView>(R.id.merchant_name).text = merchantListData?.name
        rewardDetail?.requiredSteps?.let {
            dialogView.findViewById<TextView>(R.id.stepsCount).text =
                STUtils.formatNumber(it.toInt()).plus(context.getString(R.string.redeem_steps))
        }
        if (rewardDetail?.estimateSaving.toString().contains("AED")) {
            dialogView.findViewById<TextView>(R.id.estimateSaving).text =
                rewardDetail?.estimateSaving.toString()
                    .replace("AED", "", true).trim()
        } else {
            dialogView.findViewById<TextView>(R.id.estimateSaving).text =
                rewardDetail?.estimateSaving.toString()
        }
        dialogView.findViewById<TextView>(R.id.redeemedCount).text =
            rewardDetail?.redeemed.plus(" " + context.getString(R.string.redeemed))
        dialogView.findViewById<TextView>(R.id.rewardTitle).text = rewardDetail?.name
        /*expireDate.text = context.getString(R.string.expire)
            .plus(STUtils.formatServerDate(rewardDetail!!.expireOn))*/

        dialogView.findViewById<TextView>(R.id.site).text = merchantListData!!.site

        if (rewardDetail?.rewardType == STConstants.IN_STORE) {
            digitalLayout.gone()
            //site.visibility = GONE
            inStoreLayout.visibility = VISIBLE
            dialogConfirm.background = STUtils.getDrawable(context, R.drawable.button_bg_normal)
            dialogConfirm.isEnabled = false
        } else {
            digitalLayout.visible()
            //site.visibility = VISIBLE
            inStoreLayout.visibility = GONE
            dialogConfirm.isEnabled = true
        }

        rewardDetail?.expireOn?.let {
            expireDate.visible()
            expireDate.text = context.getString(R.string.expire)
                .plus(STUtils.formatServerDate(it))
        } ?: kotlin.run {
            expireDate.gone()
        }

        pin.callback = object : OtpView.CallBack {
            override fun onComplete(otp: String) {
                pinData = otp
                dialogConfirm.background =
                    STUtils.getDrawable(context, R.drawable.button_bg_enabled)
                dialogConfirm.isEnabled = true
            }

            override fun onRemoved(otp: String) {
                pinData = otp
                dialogConfirm.background = STUtils.getDrawable(context, R.drawable.button_bg_normal)
                dialogConfirm.isEnabled = false
            }
        }
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun setMessage(
        message: String?,
        callback: () -> Unit
    ) {
    }

    fun getPin() = pinData

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
