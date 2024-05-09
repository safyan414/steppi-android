package com.steppi.steppifitness.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.*
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.Exception
import kotlin.math.roundToInt

fun View.animateZoom(scale: Float = 1.2f, duration: Long = 200) {
    animate().scaleX(scale).scaleY(scale).setDuration(duration).start()
}

fun View.animateZoomReset(scale: Float = 1f, duration: Long = 200) {
    animate().scaleX(scale).scaleY(scale).setDuration(duration).start()
}

fun View.animateRotate(
    angle: Float = 360f,
    startDelay: Long = 0,
    duration: Long = 600,
    repeatCount: Int = 0
): Animator {
    clearAnimation()
    if (rotation == angle)
        rotation = 0f
    val rotationAnim = ObjectAnimator.ofFloat(this, "rotation", angle)
    rotationAnim.startDelay = startDelay
    rotationAnim.duration = duration
    rotationAnim.repeatCount = repeatCount
    rotationAnim.interpolator = AccelerateInterpolator()
    rotationAnim.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return rotationAnim
}

fun View.animateZoomIn(
    fromZoomLevel: Float = 0f,
    toZoomLevel: Float = 1f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    scaleX = 0f
    scaleY = 0f
    val scaleXAnim = ObjectAnimator.ofFloat(this, "scaleX", fromZoomLevel, toZoomLevel)
    val scaleYAnim = ObjectAnimator.ofFloat(this, "scaleY", fromZoomLevel, toZoomLevel)
    val alphaAnim = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
    val animatorSet = AnimatorSet()
    animatorSet.startDelay = startDelay
    animatorSet.duration = duration
    animatorSet.interpolator = AccelerateInterpolator()
    animatorSet.playTogether(scaleXAnim, scaleYAnim, alphaAnim)
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet

}

fun View.animateWidth(
    zoomLevel: Float = 1f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    scaleX = 0f
    val scaleXAnim = ObjectAnimator.ofFloat(this, "scaleX", 0f, zoomLevel).apply {
        interpolator = AccelerateDecelerateInterpolator()
        setDuration(duration)
    }
    val alphaAnim = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f).apply {
        interpolator = AccelerateDecelerateInterpolator()
        setDuration(duration)
    }

    val animatorSet = AnimatorSet()
    animatorSet.startDelay = startDelay
    animatorSet.playTogether(scaleXAnim, alphaAnim)
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet
}

fun View.animateTranslationHorizontal(
    fromX: Float = 0f,
    toX: Float = 0f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    val translate = ObjectAnimator.ofFloat(this, "translationX", fromX, toX)
    translate.duration = duration
    translate.startDelay = startDelay
    translate.interpolator = DecelerateInterpolator()
    translate.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return translate
}


fun View.animateBounce(
    fromZoomLevel: Float = 0f,
    toZoomLevel: Float = 1f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    scaleX = fromZoomLevel
    scaleY = fromZoomLevel
    val scaleXAnim =
        ObjectAnimator.ofFloat(this, "scaleX", fromZoomLevel, toZoomLevel, fromZoomLevel)
    val scaleYAnim =
        ObjectAnimator.ofFloat(this, "scaleY", fromZoomLevel, toZoomLevel, fromZoomLevel)
    val alphaAnim = ObjectAnimator.ofFloat(this, "alpha", 1f, 1f)
    val animatorSet = AnimatorSet()
    animatorSet.startDelay = startDelay
    animatorSet.duration = duration
    animatorSet.interpolator = BounceInterpolator()
    animatorSet.playTogether(scaleXAnim, scaleYAnim, alphaAnim)
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet

}


fun animateSequentially(
    animators: List<Animator?>,
    startDelay: Long = 0,
    animationEnd: (() -> Unit)? = null
): Animator? {
    try {
        val animatorSet = AnimatorSet()
        animatorSet.startDelay = startDelay
        animatorSet.playSequentially(animators)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                animationEnd?.invoke()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
        animatorSet.start()
        return animatorSet
    } catch (e: Exception) {
    }
    return null
}

fun animateTogether(
    animators: List<Animator?>,
    startDelay: Long = 0,
    animationEnd: (() -> Unit)? = null
): Animator? {
    try {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animators)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                animationEnd?.invoke()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
        animatorSet.startDelay = startDelay
        animatorSet.start()
        return animatorSet
    } catch (e: Exception) {
    }
    return null
}

fun View.shake(degrees: Float = 10f, duration: Long = 100): Animator {

    return ObjectAnimator.ofFloat(this, "rotation", 0f, 0f).apply {
        setDuration(duration)
        setFloatValues(0f, degrees, degrees, 0f, 0f, -degrees, -degrees, 0f)
    }
}

fun View.animateTranslateHorizontalAndZoom(
    fromZoomLevel: Float = 0f,
    toZoomLevel: Float = 1f,
    fromX: Float = 100f,
    toX: Float = 0f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    scaleX = fromZoomLevel
    scaleY = fromZoomLevel
    val scaleXAnim = ObjectAnimator.ofFloat(this, "scaleX", fromZoomLevel, toZoomLevel)
    val translateXAnim = ObjectAnimator.ofFloat(this, "translationX", fromX, toX)
    val scaleYAnim = ObjectAnimator.ofFloat(this, "scaleY", fromZoomLevel, toZoomLevel)
    val alphaAnim = ObjectAnimator.ofFloat(this, "alpha", 1f, 1f)
    val animatorSet = AnimatorSet()
    animatorSet.startDelay = startDelay
    animatorSet.duration = duration
    animatorSet.interpolator = LinearInterpolator()
    animatorSet.playTogether(scaleXAnim, scaleYAnim, alphaAnim, translateXAnim)
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet

}

fun View.animateVerticalBounce(
    fromY: Float = 0f,
    toY: Float = 1f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    val bounceVertical = ObjectAnimator.ofFloat(this, "translationY", fromY, toY)
    bounceVertical.startDelay = startDelay
    bounceVertical.duration = duration
    bounceVertical.interpolator = BounceInterpolator()
    bounceVertical.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return bounceVertical

}

fun View.animateTranslationVertical(
    fromY: Float = 0f,
    toY: Float = 0f,
    fromAlpha: Float = 1f,
    toAlpha: Float = 1f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    val translate = ObjectAnimator.ofFloat(this, "translationY", fromY, toY)
    val alpha = ObjectAnimator.ofFloat(this, "alpha", fromAlpha, toAlpha)
    val animatorSet = AnimatorSet()
    animatorSet.playTogether(translate, alpha)
    animatorSet.duration = duration
    animatorSet.startDelay = startDelay
    animatorSet.interpolator = DecelerateInterpolator()
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet
}

fun View.leaderBoardAnimationVertical(
    fromY: Float = 0f,
    toY: Float = 0f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    val translate = ObjectAnimator.ofFloat(this, "translationY", fromY, toY)
    translate.duration = duration
    translate.startDelay = startDelay
    translate.interpolator = DecelerateInterpolator()
    translate.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return translate
}

fun View.leaderBoardAnimationHorizontal(
    fromX: Float = 0f,
    toX: Float = 0f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    val translate = ObjectAnimator.ofFloat(this, "translationX", fromX, toX)
    translate.duration = duration
    translate.startDelay = startDelay
    translate.interpolator = DecelerateInterpolator()
    translate.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return translate
}

fun View.animateEnableDisable(): Animator {
    return animateBounce(
        duration = 500,
        fromZoomLevel = 1f,
        toZoomLevel = 1.2f
    )
}

fun View.animateCheckedChange(isChecked: Boolean): Animator {
    return if (isChecked) animateRotate(
        angle = 360f,
        duration = 300
    ) else animateRotate(angle = -360f, duration = 300)
}

fun View.animateFallDown(
    fromX: Float = 0f,
    toX: Float = 0f,
    fromY: Float = 0f,
    toY: Float = 0f,
    toScale: Float = 1f,
    rotation: Float = 0f,
    duration: Long = 500,
    startDelay: Long = 0
): Animator {
    val translateX = ObjectAnimator.ofFloat(this, "translationX", fromX, toX)
    val translateY = ObjectAnimator.ofFloat(this, "translationY", fromY, toY)
    val rotation = ObjectAnimator.ofFloat(this, "rotation", rotation)
    val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f, toScale)
    val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f, toScale)
    val animatorSet = AnimatorSet()
    animatorSet.playTogether(translateX, translateY, rotation, scaleX, scaleY)
    animatorSet.duration = duration
    animatorSet.startDelay = startDelay
    animatorSet.interpolator = DecelerateInterpolator()
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet
}

fun View.animateResetFallDown(duration: Long = 100, startDelay: Long = 0): Animator {
    val translateX = ObjectAnimator.ofFloat(this, "translationX", translationX, 0f)
    val translateY = ObjectAnimator.ofFloat(this, "translationY", translationY, 0f)
    val rotation = ObjectAnimator.ofFloat(this, "rotation", 0f)
    val scaleX = ObjectAnimator.ofFloat(this, "scaleX", scaleX, 1f)
    val scaleY = ObjectAnimator.ofFloat(this, "scaleY", scaleY, 1f)
    val animatorSet = AnimatorSet()
    animatorSet.playTogether(translateX, translateY, rotation, scaleX, scaleY)
    animatorSet.duration = duration
    animatorSet.startDelay = startDelay
    animatorSet.interpolator = DecelerateInterpolator()
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet
}

fun View.animateRotateInfinite(duration: Long = 300, startDelay: Long = 0): Animator {
    clearAnimation()
    rotation = 0f
    val rotationAnim = ObjectAnimator.ofFloat(this, "rotation", 360f)
    rotationAnim.repeatCount = Animation.INFINITE
    val animatorSet = AnimatorSet()
    animatorSet.play(rotationAnim)
    animatorSet.startDelay = startDelay
    animatorSet.duration = duration
    animatorSet.interpolator = LinearInterpolator()
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            visibility = View.VISIBLE
        }
    })
    return animatorSet
}

fun ProgressBar.animateProgress(
    progressValue: Int,
    duration: Long = 600,
    startDelay: Long = 0
): Animator {
    visible()
    val valueAnimator = ValueAnimator.ofFloat(0.toFloat(), progressValue.toFloat())
    valueAnimator.duration = duration
    valueAnimator.startDelay = startDelay
    valueAnimator.interpolator = DecelerateInterpolator()
    valueAnimator.addUpdateListener {
        progress = (it.animatedValue as Float).roundToInt()
    }
    return valueAnimator
}

fun TextView.animateCount(
    progressValue: Int,
    duration: Long = 600,
    startDelay: Long = 0
): Animator {
    visible()
    val valueAnimator = ValueAnimator.ofFloat(0.toFloat(), progressValue.toFloat())
    valueAnimator.duration = duration
    valueAnimator.startDelay = startDelay
    valueAnimator.interpolator = DecelerateInterpolator()
    valueAnimator.addUpdateListener {
        text = STUtils.formatNumber((it.animatedValue as Float).roundToInt())
    }
    return valueAnimator
}
