package com.steppi.steppifitness.ui.profile.corporate.mvi

import com.steppi.steppifitness.network.request.corporate.STJoinCorporateUserRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STCorporateIntent : MviIntent {

    object GetCorporateUser : STCorporateIntent()
    object LeaveCorporation : STCorporateIntent()
    object ResendCorporateEmail : STCorporateIntent()
    object TimerIntent : STCorporateIntent()

    data class JoinCorporation(val joinCorporationRequest: STJoinCorporateUserRequest) :
        STCorporateIntent()
}
