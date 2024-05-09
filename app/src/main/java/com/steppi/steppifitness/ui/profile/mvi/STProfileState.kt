package com.steppi.steppifitness.ui.profile.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.country.STCountryResponse
import com.steppi.steppifitness.network.response.user.*
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STProfileState : MviViewState {
    object Loading : STProfileState()

    data class Error(val errorData: STErrorData?) : STProfileState()
    data class GetProfile(val userDataResponse: STUserDataResponse) : STProfileState()
    data class UpdateProfile(val userDataResponse: STUserDataResponse) : STProfileState()
    data class GetCountries(val countryResponse: STCountryResponse) : STProfileState()
    data class UpdateProfilePic(val updatePicResponse: STProfilePicResponse) : STProfileState()
    data class UpdateMobile(val updateMobileResponse: STUpdateMobileResponse) : STProfileState()
    data class ValidateOtp(val validateOtpResponse: STBaseResponse) : STProfileState()
    data class ChangePassword(val changePasswordResponse: STBaseResponse) : STProfileState()
    data class ResendOtp(val resendOtpResponse: STResendOtpResponse) : STProfileState()
    data class GetRedeemedRewardList(val redeemedRewardsResponse: STRedeemedRewardsResponse) :
        STProfileState()

    data class LogoutUser(val userLogoutResponse: STBaseResponse) : STProfileState()
}
