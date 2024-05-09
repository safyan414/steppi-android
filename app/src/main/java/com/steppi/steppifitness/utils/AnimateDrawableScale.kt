package com.steppi.steppifitness.utils

import android.animation.ValueAnimator
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.animation.BounceInterpolator
import androidx.appcompat.graphics.drawable.DrawableWrapper

class AnimateDrawableScale(resources: Resources, drawable: Drawable) : DrawableWrapper(drawable) {
    private var mScale: Float = 0.toFloat()
    private var mBounds: Rect = Rect()
    private var mScaleAnimator: ValueAnimator? = null

    override fun draw(canvas: Canvas) {
        copyBounds(mBounds)
        canvas.save()
        canvas.scale(mScale, mScale, mBounds.centerX().toFloat(), mBounds.centerY().toFloat())
        super.draw(canvas)
        canvas.restore()
    }

    fun scale(duration: Long = 500, scaleFrom: Float = 1f, scaleTo: Float = 1.2f) {
        if (null != mScaleAnimator && mScaleAnimator!!.isStarted) {
            mScaleAnimator!!.end()
        } else if (null == mScaleAnimator) {
            mScaleAnimator = ValueAnimator.ofFloat(scaleFrom, scaleTo,scaleFrom)
        }
        mScaleAnimator!!.setFloatValues(scaleFrom, scaleTo,scaleFrom)
        mScaleAnimator!!.interpolator = BounceInterpolator()
        mScaleAnimator!!.addUpdateListener { valueAnimator ->
            mScale = valueAnimator.animatedValue as Float
            invalidateSelf()
        }
        mScaleAnimator!!.duration = duration
        mScaleAnimator!!.start()
    }
}