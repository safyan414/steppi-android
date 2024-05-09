package com.steppi.steppifitness.ui.country

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnTextChanged
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.country.STCountryAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STCountryData
import com.steppi.steppifitness.network.response.country.STCountryResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.country.mvi.STCountryController
import com.steppi.steppifitness.ui.country.mvi.STCountryIntent
import com.steppi.steppifitness.ui.country.mvi.STCountryState
import com.steppi.steppifitness.utils.STDividerItemDecoration
import com.steppi.steppifitness.utils.showToast
import kotlinx.android.synthetic.main.activity_country_list.*

class STCountrySelectionActivity :
    STBaseViewModelActivity<STCountryIntent, STCountryState, STCountryController>(
        STCountryController::class.java
    ) {
    private var needShowDialCode: Boolean = false
    private var selectedCountry: STCountryData? = null
    private var countryAdapter: STCountryAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_country_list
    }

    override fun onViewModelReady() {
        invokeIntent(STCountryIntent.GetCountries)
    }

    override fun processState(state: STCountryState) {
        when (state) {
            is STCountryState.Loading -> requestDidStart()
            is STCountryState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
            }
            is STCountryState.GetCountries -> {
                requestDidFinish()
                setCountryData(state.countryResponse)
            }
            is STCountryState.SearchCountryState -> {
                countryAdapter?.setCountryList(state.countryList as ArrayList<STCountryData>)
            }
        }
    }

    private fun setCountryData(countryResponse: STCountryResponse) {
        countryResponse.data?.let {
            if (it.isNotEmpty()) {
                countryAdapter = STCountryAdapter(
                    activityContext,
                    it as ArrayList<STCountryData>,
                    needShowDialCode,
                    selectedCountry
                ) { country ->
                    intent.putExtra(STConstants.COUNTRY_DATA, country as STCountryData)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                countryList.adapter = countryAdapter
            }
        }
    }

    private fun getIntentData() {
        needShowDialCode = intent.getBooleanExtra(STConstants.NEED_MOBILE_CODE, false)
        intent.getSerializableExtra(STConstants.SELECTED_COUNTRY_DATA)?.let {
            selectedCountry = it as STCountryData
        }
    }

    override fun initViews() {
        getIntentData()
        setHeaderName(activityContext.resources.getString(R.string.country))
        countryList.layoutManager = LinearLayoutManager(activityContext)
        countryList.addItemDecoration(STDividerItemDecoration(activityContext))
    }

    @OnTextChanged(R.id.countrySearch)
    fun search(text: CharSequence) {
        invokeIntent(
            STCountryIntent.SearchCountryIntent(
                text.toString(),
                countryAdapter?.getCountryList()
            )
        )
    }
}
