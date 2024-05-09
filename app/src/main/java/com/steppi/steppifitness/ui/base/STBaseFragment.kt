package com.steppi.steppifitness.ui.base

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.steppi.steppifitness.dialogs.*
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.response.category.RedeemRewardData
import com.steppi.steppifitness.network.response.category.RewardDetails
import com.steppi.steppifitness.network.response.category.STStoresRewardsListData
import com.steppi.steppifitness.network.response.category.data.STMerchantData
import com.steppi.steppifitness.ui.language_selection.STLanguageSelectionActivity
import com.steppi.steppifitness.ui.login.STLoginActivity
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.STUtils
import com.steppi.steppifitness.utils.startActivityFinishAll

abstract class STBaseFragment : Fragment() {
    lateinit var unBinder: Unbinder
    lateinit var activityContext: AppCompatActivity
    private var progressDialogue: Dialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unBinder = ButterKnife.bind(this, view)
        initViews()
    }

    override fun onDestroyView() {
        unBinder.unbind()
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    fun requestDidStart() {
        /**
         * Starting the progressing indicator
         */
        if (progressDialogue != null) {
            if (progressDialogue!!.isShowing) {
            }
        } else {
            try {
                progressDialogue = STUtils.showProgressDialog(activityContext)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun logout() {
        logoutFromFitnessDevice()
        STPreference.setProfilePic(activityContext, null)
        STPreference.setUserId(activityContext, null)
        STPreference.setUserGender(activityContext, -1)
        STPreference.setName(activityContext, null)
        STPreference.setEmail(activityContext, null)
        STPreference.setCorporateEmail(activityContext, null)
        STPreference.saveAccessToken(activityContext, null)
        STPreference.saveRegToken(activityContext, null)
        STPreference.saveFitnessDevice(activityContext, null)
        STPreference.saveUnitSelected(activityContext, null)
        STPreference.saveFitnessDeviceID(activityContext, null)
        STPreference.saveFitnessDeviceName(activityContext, null)
        STPreference.setUserLevel(activityContext, -1)//STConstants.ACTIVE_LEVEL_OTP
        startActivity(Intent(activityContext, STLanguageSelectionActivity::class.java)) // STLoginActivity
        ActivityCompat.finishAffinity(activityContext)
    }

    private fun logoutFromFitnessDevice() {
        STUtils.logoutFromFitBit(activityContext)
        STUtils.logoutFromGoogleFit(activityContext)
        STUtils.logoutFromGarmin(activityContext)
    }

    fun requestDidFinish() {
        /**
         * Finishing the progressing indicator
         */

        if (progressDialogue != null) {
            if (progressDialogue!!.isShowing) {
                try {

                    progressDialogue!!.dismiss()
                    progressDialogue = null

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun changeFragment(containerId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(containerId, fragment, fragment.javaClass.simpleName).commit()
    }

    fun manageError(statusCode: Int?) {
        Log.e("Error Status Code", "Error Status Code : $statusCode")
        when (statusCode) {
            STAPIConstants.STATUS_CODE_SESSION_EXPIRED,
            STAPIConstants.STATUS_CODE_INVALID_SESSION,
            STAPIConstants.STATUS_CODE_MALFORMED_REQUEST -> logout()
        }
    }

    inline fun showVoucherRedeemDialog(
        merchantListData: STMerchantData?,
        merchantRewardsData: STStoresRewardsListData?,
        rewardDetail: RewardDetails?,
        func: STVoucherRedeemDialog.() -> Unit
    ): AlertDialog =
        STVoucherRedeemDialog(
            activityContext,
            merchantListData,
            merchantRewardsData,
            rewardDetail
        ).apply {
            func()
        }.create()

    inline fun showVoucherRedeemSuccessDialog(
        merchantListData: STMerchantData?,
        redeemRewardResponse: RedeemRewardData?,
        func: STVoucherRedeemSuccessDialog.() -> Unit
    ): AlertDialog =
        STVoucherRedeemSuccessDialog(
            activityContext,
            merchantListData,
            redeemRewardResponse
        ).apply {
            func()
        }.create()

    inline fun showRemoveChallengeDialog(func: STRemoveChallengeDialog.() -> Unit): AlertDialog =
        STRemoveChallengeDialog(activityContext).apply {
            func()
        }.create()

    inline fun showOkayCancelDialog(func: STOkayCancelDialog.() -> Unit): AlertDialog =
        STOkayCancelDialog(activityContext).apply {
            func()
        }.create()

    inline fun showJoinDFCChallengeDialog(func: STJoinDFCChallengeDialog.() -> Unit): AlertDialog =
        STJoinDFCChallengeDialog(activityContext).apply {
            func()
        }.create()

    inline fun showJoinChallengeDialog(func: STJoinChallengeDialog.() -> Unit): AlertDialog =
        STJoinChallengeDialog(activityContext).apply {
            func()
        }.create()

    inline fun showJoinAnotherChallengeDialog(func: STJoinAnotherChallengeDialog.() -> Unit): AlertDialog =
        STJoinAnotherChallengeDialog(activityContext).apply {
            func()
        }.create()

    inline fun leaveChallengeDialog(func: STLeaveChallengeDialog.() -> Unit): AlertDialog =
        STLeaveChallengeDialog(activityContext).apply {
            func()
        }.create()

    inline fun dailyGoalReachedDialog(func: STDailyGoalReachedDialog.() -> Unit): AlertDialog =
        STDailyGoalReachedDialog(activityContext).apply {
            func()
        }.create()

    inline fun deviceNotConnectedDialog(func: STDeviceNotConnectedDialog.() -> Unit): AlertDialog =
        STDeviceNotConnectedDialog(activityContext).apply {
            func()
        }.create()

    abstract fun getLayoutId(): Int
    abstract fun initViews()
}
