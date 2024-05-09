package com.steppi.steppifitness.fitness_manager.fitbit

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException

class STApiError constructor(error: Throwable) {
    var message = "An error occurred"

    init {
        if (error is HttpException) {
            val errorJsonString = error.response()?.errorBody()?.string()
            this.message = errorJsonString!!
            val rootJsonObject = JSONObject(errorJsonString)
            val isSucess = rootJsonObject.getString("success")
            if (isSucess == "false") {
                val errors = rootJsonObject.getString("errors")
                val mJsonArray = JSONArray(errors)
                for (i in 0 until mJsonArray.length()) {
                    val sObject = mJsonArray.get(i).toString()
                    val mItemObject = JSONObject(sObject)
                    this.message = mItemObject.getString("errorType")
                }
            }
        } else {
            this.message = error.message ?: this.message
        }
    }
}
