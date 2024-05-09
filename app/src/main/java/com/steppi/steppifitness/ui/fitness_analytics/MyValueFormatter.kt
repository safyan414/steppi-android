package com.steppi.steppifitness.ui.fitness_analytics

import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.YAxisValueFormatter
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

open class MyValueFormatter(fromDistance: Boolean?) : YAxisValueFormatter {
    private val mFormat: DecimalFormat? = null
    override fun getFormattedValue(value: Float, yAxis: YAxis?): String {
        return BigDecimal(value.toDouble()).setScale(
            4,
            RoundingMode.HALF_EVEN
        ).toString()
//        return mFormat?.format(value)!!
    }
}
