package com.steppi.steppifitness.utils

import androidx.viewpager.widget.ViewPager
import android.view.View
import kotlin.math.abs

class FadeOutTransformation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.translationX = -position * page.width
        page.alpha = 1 - abs(position)
    }
}
