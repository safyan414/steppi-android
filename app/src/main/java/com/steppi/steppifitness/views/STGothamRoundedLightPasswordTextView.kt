package com.steppi.steppifitness.views

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import com.steppi.steppifitness.R
import com.steppi.steppifitness.cache.STPCache
import com.steppi.steppifitness.utils.AnimateDrawableScale
import java.util.*

class STGothamRoundedLightPasswordTextView : AppCompatTextView {
    private var activeImage = 0
    private var inactiveImage = 0
    private var isActiveImageSet = false
    private var isInactiveImageSet = false
    private var animateDrawableActive: AnimateDrawableScale? = null
    private var animateDrawableInActive: AnimateDrawableScale? = null

    constructor(context: Context) : super(context) {
        setCustomFont()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setCustomFont()
        readAttribute(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setCustomFont()
        readAttribute(attrs)
    }

    private fun readAttribute(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.STGothamRoundedLightEditText)
        activeImage = ta.getResourceId(R.styleable.STGothamRoundedLightEditText_activeImage, 0)
        inactiveImage = ta.getResourceId(R.styleable.STGothamRoundedLightEditText_inActiveImage, 0)
        ta.recycle()
        if (activeImage != 0 && inactiveImage != 0) {
            animateDrawableActive = AnimateDrawableScale(
                resources,
                ContextCompat.getDrawable(
                    context,
                    activeImage
                )!!
            )
            animateDrawableInActive = AnimateDrawableScale(
                resources,
                ContextCompat.getDrawable(
                    context,
                    inactiveImage
                )!!
            )
        }
    }

    fun setDrawableAndAnimate(image: Drawable?, scaleFrom: Float = 1f, scaleTo: Float = 1f) {
        if (image != null) {
            animateDrawableActive = AnimateDrawableScale(
                resources,
                image
            )
            if (Build.VERSION.SDK_INT >= 17) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                    animateDrawableActive,
                    compoundDrawablesRelative[1],
                    compoundDrawablesRelative[2],
                    compoundDrawablesRelative[3]
                )
            } else {
                setCompoundDrawablesWithIntrinsicBounds(
                    compoundDrawables[0],
                    compoundDrawables[1],
                    animateDrawableActive,
                    compoundDrawables[3]
                )
            }
            animateDrawableActive?.scale(scaleFrom = scaleFrom, scaleTo = scaleTo)
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        setActiveInactiveImage(text.toString())
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }

    private fun setActiveInactiveImage(text: String) {
//        setTextColor(BDUtils.getColor(context, R.color.colorPrimary))
        if (activeImage != 0 && inactiveImage != 0) {
            if (text.isNotEmpty()) {
                if (!isActiveImageSet) {
                    isActiveImageSet = true
                    isInactiveImageSet = false
                    if (Build.VERSION.SDK_INT >= 17) {
                        setCompoundDrawablesRelativeWithIntrinsicBounds(
                            animateDrawableActive,
                            compoundDrawablesRelative[1],
                            compoundDrawablesRelative[2],
                            compoundDrawablesRelative[3]
                        )
                    } else {
                        setCompoundDrawablesWithIntrinsicBounds(
                            compoundDrawables[0],
                            compoundDrawables[1],
                            animateDrawableActive,
                            compoundDrawables[3]
                        )
                    }
                    animateDrawableActive?.scale()
                }
            } else {
                if (!isInactiveImageSet) {
                    isActiveImageSet = false
                    isInactiveImageSet = true
                    if (Build.VERSION.SDK_INT >= 17) {
                        setCompoundDrawablesRelativeWithIntrinsicBounds(
                            animateDrawableInActive,
                            compoundDrawablesRelative[1],
                            compoundDrawablesRelative[2],
                            compoundDrawablesRelative[3]
                        )
                    } else {
                        setCompoundDrawablesWithIntrinsicBounds(
                            compoundDrawables[0],
                            compoundDrawables[1],
                            animateDrawableInActive,
                            compoundDrawables[3]
                        )
                    }
                    animateDrawableInActive?.scale()
                }
            }
        }
    }

    private fun setCustomFont() {
        // textDirection = View.TEXT_DIRECTION_LOCALE
        val isLeftToRight =
            TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR
        if (isLeftToRight)
            this.gravity = (Gravity.CENTER or Gravity.START)
        else
            this.gravity = (Gravity.CENTER or Gravity.END)
        synchronized(STPCache.sharedGCache.getFontCache()) {
            if (!STPCache.sharedGCache.getFontCache()
                    .containsKey("GothamRounded-Light")
            ) {
                val tf = Typeface.createFromAsset(
                    context!!.assets,
                    "fonts/GothamRounded-Light.otf"
                )
                STPCache.sharedGCache.setFontCache("GothamRounded-Light", tf)
            }
            setTypeface(
                STPCache.sharedGCache.getFontCache()["GothamRounded-Light"],
                Typeface.NORMAL
            )
            includeFontPadding = false
        }
    }
}
