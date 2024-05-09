package com.steppi.steppifitness.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.steppi.steppifitness.cache.STPCache

class STGothamRoundedBookEditText : AppCompatEditText {

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
                    .containsKey("GothamRounded-Book")
            ) {
                val tf = Typeface.createFromAsset(
                    context!!.assets,
                    "fonts/GothamRounded-Book.otf"
                )
                STPCache.sharedGCache.setFontCache("GothamRounded-Book", tf)
            }
            setTypeface(
                STPCache.sharedGCache.getFontCache()["GothamRounded-Book"],
                Typeface.NORMAL
            )
            includeFontPadding = false
        }
    }
}
