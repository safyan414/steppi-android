package com.steppi.steppifitness.fitness_manager.samsung_health

import android.util.Log
import com.samsung.android.sdk.healthdata.*
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadResult
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.model.STFitnessDataModel
import com.steppi.steppifitness.utils.STUtils
import java.util.*

class StepCountReporter(private val mStore: HealthDataStore) {
    /*private var mStepCountObserver: StepCountObserver? = null
    private var mDistanceObserver: StepCountObserver? = null
    private var mCalorieObserver: StepCountObserver? = null*/
    private var stepCountCallback: FitnessCallback? = null
    internal var APP_TAG = "StepCountReporter"
    var startTime: Long = 0
    var endTime: Long = 0
    private val dataMap = HashMap<String, STFitnessDataModel>()
    private val startTimeOfToday: Long
        get() {
            val today = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            today.set(Calendar.DAY_OF_MONTH, 27)
            today.set(Calendar.HOUR_OF_DAY, 10)
            today.set(Calendar.MINUTE, 10)
            today.set(Calendar.SECOND, 0)
            today.set(Calendar.MILLISECOND, 0)
            return today.timeInMillis
        }

    private val mListener = HealthResultHolder.ResultListener<ReadResult> { result ->
        var count = 0
        var callorie = 0f
        var distance = 0f

        result.use { result ->
            dataMap.clear()
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            for (data in result) {
                count = count.plus(data.getInt(HealthConstants.StepCount.COUNT))
                callorie = callorie.plus(data.getFloat(HealthConstants.StepCount.CALORIE))
                distance = distance.plus(data.getFloat(HealthConstants.StepCount.DISTANCE))
                calendar.timeInMillis = data.getLong(HealthConstants.StepCount.UPDATE_TIME)
                val date = STUtils.getFormattedDate(calendar, "yyyy-MM-dd")
                var dataModel = dataMap[date]
                if (null == dataModel) {
                    dataModel = STFitnessDataModel()
                }
                dataModel.date = date
                dataModel.steps =
                    (dataModel.steps) + data.getInt(HealthConstants.StepCount.COUNT)
                dataModel.callorie =
                    (dataModel.callorie) + data.getFloat(HealthConstants.StepCount.CALORIE)
                dataModel.distance =
                    (dataModel.distance) + data.getFloat(HealthConstants.StepCount.DISTANCE)
                dataMap[date] = dataModel
            }
        }
        if (stepCountCallback != null) {
            stepCountCallback!!.onSuccess(count, callorie, distance)
            stepCountCallback!!.onStepCountReceived(count)
            stepCountCallback!!.onCalorieReceived(callorie)
            stepCountCallback!!.onDistanceReceived(distance)
            stepCountCallback!!.dailySummary(dataMap)
        }
        /*if (mStepCountObserver != null) {
            mStepCountObserver!!.onChanged(count)
        }
        if (mDistanceObserver != null) {
            mDistanceObserver!!.onChanged(distance.roundToInt())
        }
        if (mCalorieObserver != null) {
            mCalorieObserver!!.onChanged(floor(callorie).roundToInt())
        }*/
    }

    private val mObserver = object : HealthDataObserver(null) {
        // Update the step count when a change event is received
        override fun onChange(dataTypeName: String) {
            Log.d(APP_TAG, "Observer receives a data changed event")
            readStepCount()
        }
    }
    /*fun start(
        listener: StepCountObserver,
        listenerDistance: StepCountObserver,
        listenerCalorie: StepCountObserver,
        fitnessCallback: FitnessCallback,
        startTime: Long,
        endTime: Long
    ) {
        mStepCountObserver = listener
        mDistanceObserver = listenerDistance
        mCalorieObserver = listenerCalorie
        stepCountCallback = fitnessCallback
        HealthDataObserver.addObserver(
            mStore,
            HealthConstants.StepCount.HEALTH_DATA_TYPE,
            mObserver
        )

        this.startTime = toUTC(startTime)
        this.endTime = toUTC(endTime)
        readStepCount()
    }*/

    fun start(fitnessCallback: FitnessCallback, startTime: Long, endTime: Long) {
        stepCountCallback = fitnessCallback
        HealthDataObserver.addObserver(
            mStore,
            HealthConstants.StepCount.HEALTH_DATA_TYPE,
            mObserver
        )
        this.startTime = toUTC(startTime)
        this.endTime = toUTC(endTime)
        readStepCount()
    }

    fun toUTC(timestamp: Long): Long {
        val cal = Calendar.getInstance()
        val offset = cal.timeZone.getOffset(timestamp)
        return timestamp + offset
    }

    // Read the today's step count on demand
    private fun readStepCount() {
        val resolver = HealthDataResolver(mStore, null)
        val request = ReadRequest.Builder()
            .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
            .setProperties(
                arrayOf(
                    HealthConstants.StepCount.COUNT,
                    HealthConstants.StepCount.DISTANCE,
                    HealthConstants.StepCount.CALORIE,
                    HealthConstants.StepCount.CREATE_TIME,
                    HealthConstants.StepCount.UPDATE_TIME
                )
            )
            .setLocalTimeRange(
                HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                startTime, endTime
            )
            .build()
        try {
            resolver.read(request).setResultListener(mListener)
        } catch (e: Exception) {
            Log.e(APP_TAG, "Getting step count fails.", e)
            if (stepCountCallback != null) {
                stepCountCallback!!.onError("SamsungHealthKit Getting step count fails. $e")
            }
        }
    }

    /*interface StepCountObserver {
        fun onChanged(count: Int)

        fun onError(msg: String) {}
    }*/
}
