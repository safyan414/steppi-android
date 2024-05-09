package com.steppi.steppifitness.ui.fitness_analytics;

import com.github.mikephil.charting.components.AxisBase;

interface IAxisValueFormatter {
    String getFormattedValue(float value, AxisBase axis);

    int getDecimalDigits();
}
