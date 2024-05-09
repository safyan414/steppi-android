package com.steppi.steppifitness.cache

import android.graphics.Bitmap
import android.graphics.Typeface
import java.util.*

class STPCache {
    fun getFontCache(): Hashtable<String, Typeface> {
        return fontCache
    }

    fun setFontCache(font: String, fontName: Typeface) {
        fontCache[font] = fontName
    }

    companion object {
        var leafLet: Bitmap? = null
        val sharedGCache: STPCache
            get() {
                if (_cache == null) {
                    _cache = STPCache()
                }
                return _cache!!
            }
        private var _cache: STPCache? = null
        private val fontCache = Hashtable<String, Typeface>()
    }
}
