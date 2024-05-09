package com.steppi.steppifitness.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.steppi.steppifitness.R

/**
 * Created by mobiiworld on 9/20/17.
 */

class OtpView : LinearLayout {
    var mOtpViews = ArrayList<EditText>()
    var mCurrentFocussedView: EditText? = null
    var separatorWidth = 5.0f
    var textColorHighlighted = Color.BLACK
    var textColorNormal = Color.BLACK
    var backgroundNormal = Color.BLACK
    var backgroundFilled = Color.BLACK
    var backgroundShape = 0
    var viewCount = 0
    var callback: CallBack? = null
    private var isTextAdded: Boolean? = false

    interface CallBack {
        fun onComplete(otp: String)
        fun onRemoved(otp: String)
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
//        Log.e("OtpTest", "Otp init")
        val styles = context.obtainStyledAttributes(attrs, R.styleable.OtpView)
        separatorWidth = styles.getDimension(R.styleable.OtpView_separatorWidth, 5f)
        backgroundFilled = styles.getColor(R.styleable.OtpView_backgroundFilled, Color.WHITE)
        backgroundNormal = styles.getColor(R.styleable.OtpView_backgroundNormal, Color.WHITE)
        textColorHighlighted =
            styles.getColor(R.styleable.OtpView_textColorHighlighted, Color.BLACK)
        backgroundShape = styles.getResourceId(R.styleable.OtpView_backgroundShape, 0)
        textColorNormal = styles.getColor(R.styleable.OtpView_textColorNormal, Color.BLACK)
        viewCount = styles.getInteger(R.styleable.OtpView_itemCount, 1)
        styles.recycle()

        removeAllViews()
        weightSum = 4f


        for (i in 0 until viewCount) {
            val layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
            layoutParams.weight = 1f
            when (i) {
                0 -> layoutParams.marginEnd = (separatorWidth / 2).toInt()
                viewCount - 1 -> layoutParams.marginStart = (separatorWidth / 2).toInt()
                else -> {
                    layoutParams.marginEnd = (separatorWidth / 2).toInt()
                    layoutParams.marginStart = (separatorWidth / 2).toInt()
                }
            }
            val editText = EditText(context)
            editText.id = i
            editText.imeOptions = EditorInfo.IME_ACTION_NEXT
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            try {
                // https://github.com/android/platform_frameworks_base/blob/kitkat-release/core/java/android/widget/TextView.java#L562-564
                val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                f.isAccessible = true
                f.set(editText, R.drawable.cursor)
            } catch (ignored: Exception) {
            }

            editText.maxLines = 1
            editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
            editText.gravity = Gravity.CENTER
            editText.layoutParams = layoutParams
            val background = ContextCompat.getDrawable(context, backgroundShape) as LayerDrawable
            (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable).setColor(
                backgroundNormal
            )
            (background.findDrawableByLayerId(R.id.topLayer) as GradientDrawable).setColor(
                backgroundNormal
            )
            isTextAdded = false
            (background.findDrawableByLayerId(R.id.bg_main) as GradientDrawable).setColor(
                backgroundNormal
            )
            editText.background = background
            setListners(editText)
            mOtpViews.add(editText)
            addView(editText)
        }
        mOtpViews[0].requestFocus()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListners(editText: EditText) {
        editText.setOnTouchListener { v, event ->
            editText.text.clear()
            isTextAdded = false
            val background = editText?.background as LayerDrawable
            (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable).setColor(
                backgroundFilled
            )
            (background.findDrawableByLayerId(R.id.topLayer) as GradientDrawable).setColor(
                backgroundNormal
            )
            (background.findDrawableByLayerId(R.id.bg_main) as GradientDrawable).setColor(
                backgroundNormal
            )
            editText?.setTextColor(textColorNormal)
            if (editText?.id ?: 0 != 0) {
                mOtpViews[(editText?.id ?: 0) - 1].requestFocus()
            } else {
                editText?.let {
                    val iim =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    iim.hideSoftInputFromWindow(it.windowToken, 0)
                }
            }
            false
        }
        editText.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            val background = editText?.background as LayerDrawable
            if (hasFocus) {
                (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable)
                    .setColor(backgroundFilled)
                mCurrentFocussedView = v as EditText
                if (mCurrentFocussedView!!.length() > 0) {
                    mCurrentFocussedView?.setSelection(0, mCurrentFocussedView!!.length())
                }
            } else {
                if (!isTextAdded!!) {
                    if (editText.text.isNotEmpty()) {
                        (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable)
                            .setColor(backgroundFilled)
                    } else {
                        (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable)
                            .setColor(backgroundNormal)
                    }
                } else {
                    (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable)
                        .setColor(backgroundFilled)
                }
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length ?: 0 > 0) {
                    isTextAdded = true
                    val background = mCurrentFocussedView?.background as LayerDrawable
                    (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable).setColor(
                        backgroundFilled
                    )
                    (background.findDrawableByLayerId(R.id.bg_main) as GradientDrawable).setColor(
                        backgroundFilled
                    )
                    (background.findDrawableByLayerId(R.id.topLayer) as GradientDrawable).setColor(
                        backgroundFilled
                    )
                    mCurrentFocussedView?.setTextColor(textColorHighlighted)
                    if (mCurrentFocussedView?.id ?: 0 != mOtpViews.size - 1) {
                        mOtpViews[(mCurrentFocussedView?.id ?: 0) + 1].requestFocus()
                    } else {
                        mCurrentFocussedView?.let {
                            val iim =
                                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            iim.hideSoftInputFromWindow(it.windowToken, 0)
                        }
                    }
                } else {
                    isTextAdded = false
                    val background = mCurrentFocussedView?.background as LayerDrawable
                    (background.findDrawableByLayerId(R.id.topLayerBorder1) as GradientDrawable).setColor(
                        backgroundNormal
                    )
                    (background.findDrawableByLayerId(R.id.topLayer) as GradientDrawable).setColor(
                        backgroundNormal
                    )
                    (background.findDrawableByLayerId(R.id.bg_main) as GradientDrawable).setColor(
                        backgroundNormal
                    )
                    mCurrentFocussedView?.setTextColor(textColorNormal)

                    if (mCurrentFocussedView?.id ?: 0 != 0) {
                        mOtpViews[(mCurrentFocussedView?.id ?: 0) - 1].requestFocus()
                    } else {
                        mCurrentFocussedView?.let {
                            val iim =
                                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            iim.hideSoftInputFromWindow(it.windowToken, 0)
                        }
                    }
                }
                checkOtpCompleted()
            }
        })
    }

    private fun checkOtpCompleted() {
        var otp = ""
        mOtpViews.forEach {
            if (it.text.trim().isNotEmpty()) {
                otp += it.text.trim()
            }
        }

        if (otp.length == 4) {
            callback?.onComplete(otp)
        } else {
            callback?.onRemoved(otp)
        }
    }
}
