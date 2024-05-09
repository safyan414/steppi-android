package com.steppi.steppifitness.ui.register.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.country.STCountryResponse
import com.steppi.steppifitness.network.response.user.STUserDataResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STRegisterState : MviViewState {

    object Loading : STRegisterState()
    data class Error(val errorData: STErrorData?) : STRegisterState()
    data class Register(val userResponse: STUserDataResponse) :
        STRegisterState()

    data class GetCountries(val countryResponse: STCountryResponse) : STRegisterState()
}