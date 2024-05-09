package com.steppi.steppifitness.ui.onboarding

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.STOnBoardingPagerAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.STBaseActivity
import com.steppi.steppifitness.ui.login.STLoginActivity
import com.steppi.steppifitness.utils.FadeOutTransformation
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.onPageSelected
import kotlinx.android.synthetic.main.activity_on_boarding.*

class STOnBoardingActivity : STBaseActivity() {
    private var selectedLanguage: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_on_boarding
    }

    override fun initViews() {
        setViewPager()
    }

    private fun setViewPager() {
        viewPager.setPageTransformer(true, FadeOutTransformation())
        viewPager.adapter = STOnBoardingPagerAdapter(supportFragmentManager)
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            viewPager.rotationY = 180F
        }
        viewPager.onPageSelected {
            if (it == 2) {
                skip.visibility = GONE
            } else {
                skip.visibility = VISIBLE
            }
        }
    }

    @OnClick(R.id.skip)
    fun skip() {
        setLanguage(STPreference.getLanguageCode(activityContext)!!)
        selectedLanguage?.let {
            startActivity(Intent(applicationContext, STLoginActivity::class.java))
            finish()
        }
//        startActivity(Intent(this, STLanguageSelectionActivity::class.java))
//        finish()
    }
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    private fun setLanguage(selectedLanguage: String) {
        this.selectedLanguage = selectedLanguage
        STPreference.saveLanguageCode(activityContext, selectedLanguage)
    }
}
