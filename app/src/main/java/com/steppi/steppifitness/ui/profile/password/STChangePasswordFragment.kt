package com.steppi.steppifitness.ui.profile.password

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.network.request.password.STChangePasswordRequest
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.mvi.STProfileController
import com.steppi.steppifitness.ui.profile.mvi.STProfileIntent
import com.steppi.steppifitness.ui.profile.mvi.STProfileState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.update_mobile_password_success_dialog.*

class STChangePasswordFragment :
    STBaseViewModelFragment<STProfileIntent, STProfileState, STProfileController>(
        STProfileController::class.java
    ) {
    private var isConfirmAnimated = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_change_password
    }

    override fun initViews() {
        passwordTextInputLayout.setPasswordTransformation(userPassword)
        passwordOldTextInputLayout.setPasswordTransformation(userOldPassword)
        userPassword.textChangeObserver {
            validateAllData()
        }
        userOldPassword.textChangeObserver {
            validateAllData()
        }
    }

    private fun validateAllData() {
        if (STUtils.getValueFromView(userPassword).isNullOrEmpty() || STUtils.getValueFromView(
                userOldPassword
            ).isNullOrEmpty()
        ) {
            confirmButton.background =
                STUtils.getDrawable(activityContext, R.drawable.button_bg_normal)
            confirmButton.setTextColor(STUtils.getColor(activityContext, R.color.white))
            STUtils.setLightFont(activityContext, confirmButton)
            if (isConfirmAnimated) {
                confirmButton.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isConfirmAnimated = false
            }
        } else {
            confirmButton.background =
                STUtils.getDrawable(activityContext, R.drawable.button_bg_enabled)
            confirmButton.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
            STUtils.setBoldFont(activityContext, confirmButton)
            if (!isConfirmAnimated) {
                confirmButton.animateBounce(duration = 500, fromZoomLevel = 1f, toZoomLevel = 1.05f)
                    .start()
                isConfirmAnimated = true
            }
        }
    }

    override fun onViewModelReady() {
    }

    override fun processState(state: STProfileState) {
        when (state) {
            is STProfileState.Loading -> requestDidStart()
            is STProfileState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
                manageError(state.errorData?.statusCode)
            }
            is STProfileState.ChangePassword -> {
                requestDidFinish()
                showProfileSuccessDialog()
            }
            is STProfileState.LogoutUser -> {
                requestDidFinish()
                logout()
            }
        }
    }

    @OnClick(R.id.confirmButton)
    fun confirmPassword() {
        if (null == STUtils.getValueFromView(userOldPassword)) {
            return
        }
        if (null == STUtils.getValueFromView(userPassword)) {
            return
        }
        val changePasswordRequest = STChangePasswordRequest()
        changePasswordRequest.oldPassword = STUtils.getValueFromView(userOldPassword)
        changePasswordRequest.newPassword = STUtils.getValueFromView(userPassword)
        invokeIntent(STProfileIntent.ChangePassword(changePasswordRequest))
    }

    private fun showProfileSuccessDialog() {
        val updatePasswordDialog = Dialog(activityContext)
        updatePasswordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        updatePasswordDialog.setContentView(R.layout.update_mobile_password_success_dialog)
        updatePasswordDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        updatePasswordDialog.setCancelable(false)
        val wm =
            activityContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager // for activity use context instead of getActivity()
        val display = wm.defaultDisplay // getting the screen size of device
        val size = Point()
        display.getSize(size)
        val width = size.x - 20  // Set your heights
        val height = size.y - 80 // set your widths

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(updatePasswordDialog.window?.attributes)

        lp.width = width
        lp.height = height
        updatePasswordDialog.window?.attributes = lp
        updatePasswordDialog.show()
        updatePasswordDialog.findViewById<ImageView>(R.id.successTick)
            .setImageResource(R.drawable.password_change_icon)
        updatePasswordDialog.successOk.visible()
        updatePasswordDialog.successOk.setOnClickListener {
            updatePasswordDialog.dismiss()
            activityContext.finish()
            //invokeIntent(STProfileIntent.UserLogout)
        }
        STUtils.setValueToView(
            updatePasswordDialog.findViewById(R.id.successMessage),
            activityContext.resources.getString(R.string.password_updated_message)
        )
        Handler().postDelayed({
            updatePasswordDialog.dismiss()
            activityContext.finish()
            //invokeIntent(STProfileIntent.UserLogout)
        }, 3000)
    }
}
