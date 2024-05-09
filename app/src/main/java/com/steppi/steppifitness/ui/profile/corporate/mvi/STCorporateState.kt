package com.steppi.steppifitness.ui.profile.corporate.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.corporate.STCorporateUserDataResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STCorporateState : MviViewState {

    object Loading : STCorporateState()
    object TimerState : STCorporateState()

    data class Error(val errorData: STErrorData?) : STCorporateState()
    data class GetCorporateUser(val getCorporateUserResponse: STCorporateUserDataResponse) : STCorporateState()
    data class JoinCorporation(val joinCorporationResponse: STCorporateUserDataResponse) : STCorporateState()
    data class LeaveCorporation(val leaveCorporationResponse: STBaseResponse) : STCorporateState()
    data class ResendCorporateEmail(val resendCorporateEmail: STBaseResponse) : STCorporateState()


}