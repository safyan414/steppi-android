package com.steppi.steppifitness.ui.country.mvi

import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STCountryIntent : MviIntent {
    object GetCountries : STCountryIntent()

    data class SearchCountryIntent(val searchKey: String, val countryList: List<STCountryData>?) :
        STCountryIntent()

}