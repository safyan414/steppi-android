package com.steppi.steppifitness.ui.country.mvi

import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.country.STCountryResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STCountryState : MviViewState {
    object Loading : STCountryState()

    data class Error(val errorData: STErrorData?) : STCountryState()
    data class GetCountries(val countryResponse: STCountryResponse) : STCountryState()
    data class SearchCountryState(val countryList: List<STCountryData>?) : STCountryState()
}