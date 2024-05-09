package com.steppi.steppifitness.ui.onboarding

import android.animation.Animator
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.ui.base.STBaseFragment
import com.steppi.steppifitness.ui.login.STLoginActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.onboarding_one.*
import kotlinx.android.synthetic.main.onboarding_three.*
import kotlinx.android.synthetic.main.onboarding_two.*

class STOnboardingPagerFragment : STBaseFragment() {
    private var selectedLanguage: String? = null
    private var position = 0
    private var animator: Animator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(STConstants.ARG_POSITION)?.let {
            position = it
        }
    }

    override fun initViews() {
        setListeners()
    }

    private fun setListeners() {
        if (position == 2) {
            start.setOnClickListener {
                setLanguage(STPreference.getLanguageCode(activityContext)!!)
                selectedLanguage?.let {
                    startActivity(Intent(activityContext, STLoginActivity::class.java))
                    activityContext.finish()
                }
//                startActivity(
//                    Intent(
//                        activityContext,
//                        STLanguageSelectionActivity::class.java
//                    )
//                )
//                activityContext.finish()
            }

            //                startActivity(
//                    Intent(
//                        activityContext,
//                        STLanguageSelectionActivity::class.java
//                    ).apply {
//                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    })
//                activityContext.overridePendingTransition(
//                    R.anim.slide_in_right,
//                    R.anim.slide_out_left
//                )
        }
    }

    private fun setLanguage(selectedLanguage: String) {
        this.selectedLanguage = selectedLanguage
        STPreference.saveLanguageCode(activityContext, selectedLanguage)
    }

    override fun getLayoutId(): Int {
        return when (position) {
            0 -> R.layout.onboarding_one
            1 -> R.layout.onboarding_two
            2 -> R.layout.onboarding_three
            else -> R.layout.onboarding_one
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onResume() {
        super.onResume()
        if (isMenuVisible) {
            startAnimation()
        }
    }

    private fun startAnimation() {
        when (position) {
            0 -> startScreen1Animation()
            1 -> startScreen2Animation()
            2 -> startScreen3Animation()
        }
    }

    private fun startScreen1Animation() {
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_ON_BOARDING_PAGE_1,
                "", ""
            )
        indicator1Screen1.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        stepsProgress.max = 13500
        stepsProgress.secondaryProgress = 13500
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            stepsContainer.rotationY = 180F
            centerText.rotationY = 180F
            title.rotationY = 180F
            description.rotationY = 180F
            indicatorManScreen1.rotationY = 180F
        }
        animator = animateSequentially(
            arrayListOf(
//                stepsProgress.animateRotate(duration = 800),
                stepsContainer.animateZoomIn(duration = 300),
                AnimatorSet().apply {
                    playTogether(
                        stepsProgress.animateProgress(8100, duration = 1800),
                        stepsTodayCount.animateCount(8100, duration = 1800)
                    )
                },
                centerText.animateWidth(duration = 400),
                stepImage.animateZoomIn(duration = 300),
                AnimatorSet().apply {
                    playTogether(
                        title.animateTranslationHorizontal(duration = 600, fromX = 500f, toX = 0f),
                        description.animateTranslationHorizontal(
                            duration = 600,
                            fromX = 500f,
                            toX = 0f,
                            startDelay = 300
                        )
                    )
                },
                lineContainerScreen1.animateZoomIn(duration = 300),
                indicatorManScreen1.animateTranslationHorizontal(
                    duration = 500,
                    fromX = 0f,
                    toX = 2 * indicator1Screen1.measuredWidth.toFloat()
                )
            )
        )
    }

    private fun startScreen2Animation() {
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_ON_BOARDING_PAGE_2,
                "", ""
            )
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            imageTop.rotationY = 180F
            titleSave.rotationY = 180F
            descriptionSave.rotationY = 180F
            indicatorManScreen2.rotationY = 180F
        }
        animator = animateSequentially(
            arrayListOf(
                imageTop.animateZoomIn(duration = 300),
                imageSave.animateZoomIn(duration = 300),
                AnimatorSet().apply {
                    playTogether(
                        titleSave.animateTranslationHorizontal(
                            duration = 600,
                            fromX = 500f,
                            toX = 0f
                        ),
                        descriptionSave.animateTranslationHorizontal(
                            duration = 600,
                            fromX = 500f,
                            toX = 0f,
                            startDelay = 300
                        )
                    )
                },
                lineContainerScreen2.animateZoomIn(duration = 300),
                indicatorManScreen2.animateTranslationHorizontal(
                    duration = 500,
                    fromX = 0f,
                    toX = 4 * indicator2Screen2.measuredWidth.toFloat()
                )
            )
        )
    }

    private fun startScreen3Animation() {
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEvent(
                STFireBaseAnalyticsConstants.EVENT_ON_BOARDING_PAGE_3,
                "", ""
            )
        if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
            boardingThreeBgLayout.rotationY = 180F
            titleSmile.rotationY = 180F
            descriptionSmile.rotationY = 180F
            start.rotationY = 180F
        }
        animator = animateSequentially(
            arrayListOf(
                boardingThreeBgLayout.animateZoomIn(duration = 300),
                imageSmile.animateZoomIn(duration = 300),
                AnimatorSet().apply {
                    playTogether(
                        titleSmile.animateTranslationHorizontal(
                            duration = 400,
                            fromX = 500f,
                            toX = 0f
                        ),
                        descriptionSmile.animateTranslationHorizontal(
                            duration = 400,
                            fromX = 500f,
                            toX = 0f,
                            startDelay = 200
                        )
                    )
                },
                start.animateZoomIn(duration = 300)
            )
        )
    }

    override fun onPause() {
        hideOnBoardingContents()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        hideOnBoardingContents()
    }

    private fun hideOnBoardingContents() {
        animator?.cancel()
        animator = null
        when (position) {
            0 -> hideOnBoarding1()
            1 -> hideOnBoarding2()
            2 -> hideOnBoarding3()
        }
    }

    private fun hideOnBoarding1() {
        stepsProgress.visibility = View.INVISIBLE
        stepsContainer.visibility = View.INVISIBLE
        centerText.visibility = View.INVISIBLE
        stepImage.visibility = View.INVISIBLE
        title.visibility = View.INVISIBLE
        description.visibility = View.INVISIBLE
        lineContainerScreen1.visibility = View.INVISIBLE
        indicatorManScreen1.visibility = View.INVISIBLE
        indicatorManScreen1.translationX = 0f
    }

    private fun hideOnBoarding2() {
        imageTop.visibility = View.INVISIBLE
        imageSave.visibility = View.INVISIBLE
        titleSave.visibility = View.INVISIBLE
        descriptionSave.visibility = View.INVISIBLE
        lineContainerScreen2.visibility = View.INVISIBLE
        indicatorManScreen2.visibility = View.INVISIBLE
        indicatorManScreen2.translationX = 0f
    }

    private fun hideOnBoarding3() {
        imageSmile.invisible()
        boardingThreeBgLayout.invisible()
        titleSmile.visibility = View.INVISIBLE
        descriptionSmile.visibility = View.INVISIBLE
        start.visibility = View.INVISIBLE
    }

}
