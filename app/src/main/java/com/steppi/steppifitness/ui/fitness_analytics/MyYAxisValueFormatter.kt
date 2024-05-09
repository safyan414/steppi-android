package com.steppi.steppifitness.ui.fitness_analytics

import android.util.Log
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.YAxisValueFormatter
import java.text.DecimalFormat

class MyYAxisValueFormatter : /*IAxisValueFormatter, */YAxisValueFormatter {
    private var mFormat: DecimalFormat? = DecimalFormat("###,###,##0.0")
//    override fun getFormattedValue(
//        value: Float,
//        axis: AxisBase
//    ): String {
//        return mFormat!!.format(value.toDouble())
//    }
//
//    /** this is only needed if numbers are returned, else return 0  */
//    override fun getDecimalDigits(): Int {
//        return 9
//    }

//    init { // format values to 1 decimal digit
//        mFormat = DecimalFormat("###,###,##0.0")
//    }

    override fun getFormattedValue(
        value: Float,
        yAxis: YAxis?
    ): String { // "value" represents the position of the label on the axis (x or y)
        Log.e("Yaxis Value", "Value $value")
        return if (value > 0 && value <= 1) {
            mFormat!!.format(value.toDouble())
        } else {
            value.toInt().toString()
        }
//        return mFormat!!.format(value.toDouble())
    }
}
