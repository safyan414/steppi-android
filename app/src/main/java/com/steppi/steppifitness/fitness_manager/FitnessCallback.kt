package com.steppi.steppifitness.fitness_manager

import com.steppi.steppifitness.model.STFitnessDataModel

interface FitnessCallback {
    fun onSuccess(stepCount: Int, calorieCount: Float, distance: Float)
    fun onError(error: String)
    fun onStepCountReceived(stepCount: Int, userInput: Int = 0)
    fun onCalorieReceived(calorie: Float, userInput: Float = 0f)
    fun onDistanceReceived(distance: Float, userInput: Float = 0f)
    fun dailySummary(dataMap: HashMap<String, STFitnessDataModel>)
}
