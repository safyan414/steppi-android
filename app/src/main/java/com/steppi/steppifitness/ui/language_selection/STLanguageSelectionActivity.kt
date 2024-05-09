package com.steppi.steppifitness.ui.language_selection

import android.content.Intent
import android.view.View
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.STBaseActivity
import com.steppi.steppifitness.ui.onboarding.STOnBoardingActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_language_selection.*

class STLanguageSelectionActivity : STBaseActivity() {
    private var selectedLanguage: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_language_selection
    }

    override fun initViews() {
        if (STPreference.getLanguageCode(activityContext)!! == STConstants.ARABIC_CODE) {
            btnContinue.text = getString(R.string.continue_arabic)
        } else {
            btnContinue.text = getString(R.string.continue_string)
        }
        if (tickArabic.visibility == View.INVISIBLE && tickEnglish.visibility == View.INVISIBLE) {
            btnContinue.text = getString(R.string.continue_string)
        }
    }

    @OnClick(R.id.btnContinue)
    fun goNext() {
        selectedLanguage?.let {
            startActivity(Intent(applicationContext, STOnBoardingActivity::class.java))//
            finish()
        } ?: kotlin.run {
            showToast(getString(R.string.please_select_language))
        }
    }

    @OnClick(R.id.langArabic)
    fun selectArabic() {
        tickArabic.visible()
        tickEnglish.invisible()
        langEnglish.background =
            STUtils.getDrawable(activityContext, R.drawable.button_selector_normal)
        langArabic.background =
            STUtils.getDrawable(activityContext, R.drawable.button_lang_bg_enabled_red)
        btnContinue.background =
            STUtils.getDrawable(activityContext, R.drawable.button_selector_enabled)
        arabic.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        english.setTextColor(STUtils.getColor(activityContext, R.color.white))
        btnContinue.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        btnContinue.text = getString(R.string.continue_arabic)
        setLanguage(STConstants.ARABIC_CODE)
        logoAr.bringToFront()
        logoAr.animateZoom()
        logoEn.animateZoomReset()
        tickArabic.animateCheckedChange(true).start()
    }

    @OnClick(R.id.langEnglish)
    fun selectEnglish() {
        tickArabic.invisible()
        tickEnglish.visible()
        langEnglish.background =
            STUtils.getDrawable(activityContext, R.drawable.button_lang_bg_enabled)
        langArabic.background =
            STUtils.getDrawable(activityContext, R.drawable.button_selector_normal)
        btnContinue.background =
            STUtils.getDrawable(activityContext, R.drawable.button_selector_enabled)
        btnContinue.text = getString(R.string.continue_string)
        arabic.setTextColor(STUtils.getColor(activityContext, R.color.white))
        english.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        btnContinue.setTextColor(STUtils.getColor(activityContext, R.color.theme_color))
        setLanguage(STConstants.ENGLISH_CODE)
        logoEn.bringToFront()
        logoEn.animateZoom()
        logoAr.animateZoomReset()
        tickEnglish.animateCheckedChange(true).start()
    }

    private fun setLanguage(selectedLanguage: String) {
        this.selectedLanguage = selectedLanguage
        STPreference.saveLanguageCode(activityContext, selectedLanguage)
    }

}
