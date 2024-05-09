package com.steppi.steppifitness.ui.profile.mvi

import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.request.otp.STValidateOtpRequest
import com.steppi.steppifitness.network.request.password.STChangePasswordRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent
import okhttp3.MultipartBody

sealed class STProfileIntent : MviIntent {

    object GetProfile : STProfileIntent()
    object GetCountries : STProfileIntent()
    object UserLogout : STProfileIntent()

    data class UpdateProfile(val updateProfileRequest: STUser) : STProfileIntent()
    data class UpdateProfilePic(val picture: MultipartBody.Part?) : STProfileIntent()
    data class UpdateMobile(val updateMobileRequest: STUser) : STProfileIntent()
    data class ValidateOtp(val validateOtpRequest: STValidateOtpRequest) : STProfileIntent()
    data class ChangePassword(val changePasswordRequest: STChangePasswordRequest) :
        STProfileIntent()

    data class ResendOtp(val resendOtpRequest: STValidateOtpRequest) : STProfileIntent()
    data class GetRedeemedRewardsList(val rewardType: String, val pageOffset: Int = 0) :
        STProfileIntent()
}
