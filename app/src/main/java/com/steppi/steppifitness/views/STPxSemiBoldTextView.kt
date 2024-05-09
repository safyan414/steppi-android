package com.steppi.steppifitness.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.steppi.steppifitness.cache.STPCache

class STPxSemiBoldTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        setCustomFont()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setCustomFont()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setCustomFont()
    }

    private fun setCustomFont() {
        textDirection = View.TEXT_DIRECTION_LOCALE
        synchronized(STPCache.sharedGCache.getFontCache()) {
            if (!STPCache.sharedGCache.getFontCache()
                    .containsKey("Raleway-SemiBold")
            ) {
                val tf = Typeface.createFromAsset(
                    context!!.assets,
                    "fonts/Raleway-SemiBold.ttf"
                )
                STPCache.sharedGCache.setFontCache("Raleway-SemiBold", tf)
            }
            setTypeface(
                STPCache.sharedGCache.getFontCache()["Raleway-SemiBold"],
                Typeface.NORMAL
            )
            includeFontPadding = false
        }
    }
}
