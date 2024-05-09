package com.steppi.steppifitness.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.steppi.steppifitness.cache.STPCache

class STPxBoldTextView : AppCompatTextView {

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
                    .containsKey("Raleway-Bold")
            ) {
                val tf = Typeface.createFromAsset(
                    context!!.assets,
                    "fonts/Raleway-Bold.ttf"
                )
                STPCache.sharedGCache.setFontCache("Raleway-Bold", tf)
            }
            setTypeface(
                STPCache.sharedGCache.getFontCache()["Raleway-Bold"],
                Typeface.NORMAL
            )
            includeFontPadding = false
        }
    }
}
