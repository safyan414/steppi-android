package com.steppi.steppifitness.ui.profile.settings

import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.network.response.user.STChangeDefaultLanguageResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingController
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingIntent
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_change_language.*

class STChangeLanguageFragment :
    STBaseViewModelFragment<STSettingIntent, STSettingState, STSettingController>(
        STSettingController::class.java
    ) {
    override fun getLayoutId(): Int {
        return R.layout.fragment_change_language
    }

    override fun initViews() {
        setSelectionICon()
    }

    override fun onViewModelReady() {
    }

    override fun processState(state: STSettingState) {
        when (state) {
            is STSettingState.Loading -> requestDidStart()
            is STSettingState.Error -> {
                requestDidFinish()
                showToast(state.errorData?.message)
//                manageError(state.errorData?.statusCode)
                STConstants.IS_LANGUAGE_CHANGED = true
                activityContext.onBackPressed()
            }
            is STSettingState.ChangeDefaultLanguage -> {
                requestDidFinish()
                STConstants.IS_LANGUAGE_CHANGED = true
                activityContext.onBackPressed()
            }
            else -> {
                STConstants.IS_LANGUAGE_CHANGED = true
                activityContext.onBackPressed()
            }
        }
    }

    private fun setSelectionICon() {
        if (STPreference.getLanguageCode(activityContext)!! == STConstants.ENGLISH_CODE) {
            englishCheckIcon.visible()
            arabicCheckIcon.gone()
        } else {
            englishCheckIcon.gone()
            arabicCheckIcon.visible()
        }
    }

    @OnClick(R.id.arabicLayout)
    fun selectArabic() {
        if (STPreference.getLanguageCode(activityContext)!! == STConstants.ENGLISH_CODE) {
            setLanguage(STConstants.ARABIC_CODE)
            setSelectionICon()
            changeLanguageAPICall()
        }
    }

    @OnClick(R.id.englishLayout)
    fun selectEnglish() {
        if (STPreference.getLanguageCode(activityContext)!! == STConstants.ARABIC_CODE) {
            setLanguage(STConstants.ENGLISH_CODE)
            setSelectionICon()
            changeLanguageAPICall()
        }
    }

    private fun changeLanguageAPICall() {
        invokeIntent(STSettingIntent.ChangeDefaultLanguage)
    }

    private fun setLanguage(selectedLanguage: String) {
        STPreference.saveLanguageCode(activityContext, selectedLanguage)
    }
}
