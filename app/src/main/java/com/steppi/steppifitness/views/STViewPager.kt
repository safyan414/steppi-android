package com.steppi.steppifitness.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class STViewPager : FrameLayout {
    private lateinit var viewPagerOutter: ViewPager
    private lateinit var viewPagerInner: ViewPager
    private var disableOuterViewPager = false
    private var pageSelectedListener: PageSelectedListener? = null
    var currentItem = 0
    private var indicator: Indicator? = null

    interface PageSelectedListener {
        fun onPageSelected(position: Int)
    }

    constructor(context: Context) : super(context) {
        initView()
        setListeners()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        setListeners()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        setListeners()
    }

    private fun initView() {
        val layoutParamsOuter =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        viewPagerOutter = ViewPager(context)
        viewPagerOutter.id = View.generateViewId()
        addView(viewPagerOutter, layoutParamsOuter)
        val layoutParamsInner =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        viewPagerInner = ViewPager(context)
        viewPagerInner.id = View.generateViewId()
        addView(viewPagerInner, layoutParamsInner)
    }

    fun setBackgroundViewPagerAdapter(adapter: PagerAdapter) {
        viewPagerOutter.adapter = adapter
    }

    fun setBackgroundTranformation(transformation: ViewPager.PageTransformer) {
        viewPagerOutter.setPageTransformer(true, transformation)
    }

    fun setInnerViewPagerAdapter(adapter: PagerAdapter) {
        viewPagerInner.adapter = adapter
        initIndicator(adapter.count)
    }

    fun disableOutSideView(disable: Boolean = false) {
        disableOuterViewPager = disable
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        viewPagerOutter.setOnTouchListener(OnTouchListener { p0, p1 ->
            if (disableOuterViewPager) {
                true
            } else {
                viewPagerInner.onTouchEvent(p1)
                false
            }
        })

        viewPagerInner.setOnTouchListener(OnTouchListener { p0, p1 ->
            viewPagerOutter.onTouchEvent(p1)
            false
        })

        viewPagerInner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                pageSelectedListener?.onPageSelected(position)
                currentItem = position
                indicator?.changeIndicator(position)
            }
        })
    }

    fun setInnerViewPagerLayoutParams(layoutParams: LayoutParams) {
        viewPagerInner.layoutParams = layoutParams
        postInvalidate()
    }

    fun setInnerViewPagerBackground(backgroundRes: Int) {
        viewPagerInner.setBackgroundResource(backgroundRes)
        postInvalidate()
    }

    fun setPageSelectedListener(pageSelectedListener: PageSelectedListener?) {
        this.pageSelectedListener = pageSelectedListener
    }

    fun setIndicator(selectedImageRes: Int, normalImageRes: Int) {
        val layoutParamsIndicator =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        indicator = Indicator(context)
        indicator?.selectedImageRes = selectedImageRes
        indicator?.normalImageRes = normalImageRes
        addView(indicator, layoutParamsIndicator)
    }

    private fun initIndicator(count: Int) {
        val indicatorLayoutParam = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        indicatorLayoutParam.bottomMargin =
            (viewPagerInner.layoutParams as LayoutParams).bottomMargin + 50
        indicatorLayoutParam.marginStart = (viewPagerInner.layoutParams as LayoutParams).marginStart
        indicatorLayoutParam.marginEnd = (viewPagerInner.layoutParams as LayoutParams).marginEnd
        indicatorLayoutParam.gravity = Gravity.BOTTOM
        indicator?.layoutParams = indicatorLayoutParam
        indicator?.init(count)
    }

    class Indicator : LinearLayout {
        var selectedImageRes = 0
        var normalImageRes = 0
        var indicatorHeight = 50
        var prevPosition = 0

        constructor(context: Context) : super(context)

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

        constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        )

        fun init(count: Int) {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams.height = indicatorHeight
            removeAllViews()
            for (i in 0 until count) {
                val imageView = ImageView(context)
                val layoutParam = LayoutParams(indicatorHeight, indicatorHeight)
                layoutParam.marginStart = 15
                layoutParam.marginEnd = 15
                if (i == 0) {
                    imageView.setBackgroundResource(selectedImageRes)
                    layoutParam.width = indicatorHeight
                    layoutParam.height = indicatorHeight
                } else {
                    imageView.setBackgroundResource(normalImageRes)
                    layoutParam.width = indicatorHeight / 3
                    layoutParam.height = indicatorHeight / 3
                }
                addView(imageView, layoutParam)
            }
            postInvalidate()
        }

        fun changeIndicator(position: Int) {
            val prevView = getChildAt(prevPosition)
            prevView?.setBackgroundResource(normalImageRes)
            prevView?.layoutParams?.width = indicatorHeight / 3
            prevView?.layoutParams?.height = indicatorHeight / 3

            val currView = getChildAt(position)
            currView?.setBackgroundResource(selectedImageRes)
            currView?.layoutParams?.width = indicatorHeight
            currView?.layoutParams?.height = indicatorHeight
            prevPosition = position
        }
    }
}
