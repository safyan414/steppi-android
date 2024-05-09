package com.steppi.steppifitness.utils

import android.os.Handler
import android.text.method.PasswordTransformationMethod
import android.view.View

/**
 * Created by stavros.diolatzis on 18/1/2017.
 */

class CustomPasswordTransformationMethod(maskChar: Char, timeBeforeMask: Int) :
    PasswordTransformationMethod() {

    private var maskChar = '\u25A0'
    private var timeBeforeMask = 1000

    private lateinit var passwordCharSequence: PasswordCharSequence
    private lateinit var view: View
    private var handler: Handler? = null
    private lateinit var runnable: Runnable

    init {
        this.maskChar = maskChar
        this.timeBeforeMask = timeBeforeMask
    }

    fun setMaskChar(maskChar: Char) {
        this.maskChar = maskChar
    }

    fun setTimeBeforeMask(timeBeforeMask: Int) {
        this.timeBeforeMask = timeBeforeMask
    }

    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        passwordCharSequence = PasswordCharSequence(source)
        handler = Handler()
        this.view = view
        return passwordCharSequence
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        super.onTextChanged(s, start, before, count)
        if (before <= 0) {
            passwordCharSequence.showLast = false

            if (this::runnable.isInitialized)
                handler?.removeCallbacks(runnable)

            runnable = Runnable {
                passwordCharSequence.showLast = false
                view.requestLayout()
            }

            handler?.postDelayed(runnable, timeBeforeMask.toLong())
        } else {

            if (this::runnable.isInitialized)
                handler?.removeCallbacks(runnable)

            passwordCharSequence.showLast = false
            view.requestLayout()
        }
    }

    inner class PasswordCharSequence(private val mSource: CharSequence) : CharSequence {
        override val length: Int
            get() = mSource.length

        override fun get(index: Int): Char {
            //This is the check which makes sure the last character is shown
            return if (index != mSource.length - 1)
                maskChar
            else if (showLast)
                mSource[index]
            else
                maskChar
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return mSource.subSequence(startIndex, endIndex) // Return default
        }

        internal var showLast = false
    }
}
