package com.steppi.steppifitness.model

import java.io.Serializable

class STFitnessDataModel : Serializable {
  var date: String? = null
  var timeMillis: Long = 0
  var distance: Float = 0f
  var callorie: Float = 0f
  var steps: Float = 0f
  var stepsUserInput: Float = 0f
  var distanceUserInput: Float = 0f
  var callorieUserInput: Float = 0f
  var activeMinutes: Long = 0L
}
