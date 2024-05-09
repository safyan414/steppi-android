package com.steppi.steppifitness.fitness_manager.hihealth

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hihealth.error.HiHealthError
import com.huawei.hihealthkit.HiHealthDataQuery
import com.huawei.hihealthkit.HiHealthDataQueryOption
import com.huawei.hihealthkit.auth.HiHealthAuth
import com.huawei.hihealthkit.auth.HiHealthOpenPermissionType
import com.huawei.hihealthkit.data.HiHealthPointData
import com.huawei.hihealthkit.data.store.HiHealthDataStore
import com.huawei.hihealthkit.data.type.HiHealthPointType
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.app.STConstants.HUAWEI_HEALTH_PERMISSIONS_REQUEST_CODE
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.model.STFitnessDataModel
import com.steppi.steppifitness.utils.STPreference
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

class HuaweiHealthDataSource(
    private val activity: WeakReference<AppCompatActivity>,
    private val stepCountCallback: FitnessCallback,
    private var startTime: Long,
    private var endTime: Long
) {
    companion object {
        const val TAG = "HuaweiHealthDataSource"
        const val PERMISSION_ALLOWED = 1
    }

    private var queriedSteps = false
    private var steps = 0
    private var queriedCalories = false
    private var calories = 0f
    private var queriedDistance = false
    private var distance = 0f
    private var queriedMinutes = false
    private var minutes = 0f

    private val dataMap = HashMap<String, STFitnessDataModel>()

    private val readPermissions = intArrayOf(
        HiHealthOpenPermissionType.HEALTH_OPEN_PERMISSION_TYPE_READ_DATA_POINT_STEP_SUM,
        HiHealthOpenPermissionType.HEALTH_OPEN_PERMISSION_TYPE_READ_DATA_POINT_CALORIES_SUM,
        HiHealthOpenPermissionType.HEALTH_OPEN_PERMISSION_TYPE_READ_DATA_POINT_DISTANCE_SUM,
        HiHealthOpenPermissionType.HEALTH_OPEN_PERMISSION_TYPE_READ_DATA_POINT_INTENSITY
    )

    init {
        if (isHuaweiHealthAppOutdated()) {
            Log.w(TAG, "HAUWEI health app is outdated")
            Toast.makeText(activity.get(), R.string.huawei_health_outdated, Toast.LENGTH_SHORT)
                .show()
            // throw Exception("Huawei health app is outdated")
        }
        checkForUserPermission()
    }

    /**
     * check for Huawei health app version if compatible with Huawei Health SDK used in STEPPI ( [HiHealthAuth], [HiHealthDataStore], ...)
     * @return Boolean true if the installed apps version is compatible with new HiHealth SDK, false otherwise
     */
    private fun isHuaweiHealthAppOutdated(): Boolean {

        val pinfo = try {
            activity.get()!!.packageManager.getPackageInfo(
                STConstants.HUAWEI_HEALTH_PACKAGE_NAME,
                0
            )
        } catch (e: Exception) {
            //Huawei Health is not installed
            return true
        }

        //getVersionCode is Deprecated, instead use getLongVersionCode().
        val verCode: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            pinfo.longVersionCode
        } else {
            pinfo.versionCode.toLong()
        }


        return verCode < STConstants.HUAWEI_HEALTH_MIN_VERCODE
    }

    /**
     * check if the user already gave us permissions to query steps, calories and distance,
     * otherwise redirect user to Huawei Health app to ask him for authorization
     * @param queryData Boolean, if true will query data (steps...) if we have permission, otherwise just get permission
     */
    fun checkForUserPermission(queryData: Boolean = true) {
        HiHealthAuth.getDataAuthStatusEx(
            activity.get(), null, readPermissions
        ) { resultCode, resultMsg, writeList, readList ->
            Log.i(TAG, "HiHealth resultCode:$resultCode")
            Log.i(TAG, "HiHealth resultMsg:$resultMsg")
            if (resultCode == HiHealthError.SUCCESS) {
                for (permission in readList) {
                    //if we are missing at least one permission, ask for authorization
                    if (permission != PERMISSION_ALLOWED) {
                        askUserForAuthorization()
                        return@getDataAuthStatusEx
                    }
                }
                havePermission()
                //we already have the all the permissions
                if (queryData)
                    queryHealthData()
            } else {
                //failed

            }
        }

    }

    /**
     * save fake google id, to avoid "no device connected" error
     */
    private fun havePermission() {
        if (STPreference.getGoogleFitUserId(activity.get()!!).isNullOrEmpty())
            STPreference.saveGoogleFitUserId(
                activity.get()!!,
                "HUAWEI_HEALTH_" + STPreference.getUserId(activity.get() as Activity)
            )
    }

    /**
     * redirect user to Huawei Health app, to authorize us (grant permission) to query steps,distance and calories
     * @param next: @param next Function0<Unit>?: optional function that will be invoked when user give all permissions
     */
    private fun askUserForAuthorization() {
        HiHealthAuth.requestAuthorization(
            activity.get(), null, readPermissions
        ) { resultCode: Int, obj: Any ->
            Log.d(TAG, "HiHealth onResult:$resultCode")
            if (resultCode == HiHealthError.SUCCESS) {
                Log.d(TAG, "HiHealth success resultContent:$obj ")
                //huawei health auth page shown successfully
                //this does not mean STEPPI have permission yet
            } else {
                //failed to redirect user to auth screen
                Log.d(TAG, "failed to show huawei health auth screen $obj")
            }

        }
    }

    /**
     * send queries to Huawei Health to get steps, calories and distance of the period that start from [startTime] to [endTime]
     */
    private fun queryHealthData() {
        //query steps
        queryHealthInfo(HiHealthPointType.DATA_POINT_STEP_SUM,
            { data ->
                queriedSteps = true
                if (data.isNotEmpty()) {//147
                    //each item in [data] is a day
                    for (day in data) {
                        addSummaryForDay(day.endTime, day.value.toFloat(), 0f, 0f, 0L)
                    }
                    val lastDay = data.last()
                    steps = lastDay.value
                }
                sendOnSuccess()
                runOnUiThread { stepCountCallback.onStepCountReceived(steps) }
            },
            { error ->
                runOnUiThread {
                    stepCountCallback.onError(
                        error
                    )
                }
            }
        )

        //query calories
        queryHealthInfo(HiHealthPointType.DATA_POINT_CALORIES_SUM,
            { data ->
                queriedCalories = true
                if (data.isNotEmpty()) { //Data received in Calories, must decided by 1000, to convert it to kcal

                    //each item in [data] is a day
                    for (day in data) {
                        addSummaryForDay(day.endTime, 0f, day.value.toFloat() / 1000, 0f, 0L)
                    }

                    val lastDay = data.last()
                    calories = lastDay.value.toFloat() / 1000

                }

                sendOnSuccess()
                runOnUiThread { stepCountCallback.onCalorieReceived(calories) }
            },
            { error ->
                runOnUiThread {
                    stepCountCallback.onError(error)
                }
            }
        )

        //query distance
        queryHealthInfo(HiHealthPointType.DATA_POINT_DISTANCE_SUM,
            { data ->
                queriedDistance = true//0,1 km, 100m
                if (data.isNotEmpty()) {
                    //each item in [data] is a day
                    for (day in data) {
                        addSummaryForDay(day.endTime, 0f, 0f, day.value.toFloat(), 0L)
                    }
                    val lastDay = data.last()
                    distance = lastDay.value.toFloat()
                }
                sendOnSuccess()
                runOnUiThread { stepCountCallback.onDistanceReceived(distance) }
            },
            { error ->
                runOnUiThread {
                    stepCountCallback.onError(error)
                }
            }
        )

        //query active minutes
        queryHealthInfo(HiHealthPointType.DATA_POINT_EXERCISE_INTENSITY,
            { data ->
                queriedMinutes = true
                if (data.isNotEmpty()) {
                    //each item in [data] is a day
                    for (day in data) {
                        addSummaryForDay(day.endTime, 0f, 0f, 0f, day.value.toLong())
                    }
                    val lastDay = data.last()
                    minutes = lastDay.value.toFloat()
                }
                sendOnSuccess()
                runOnUiThread { }
            },
            { error ->
                runOnUiThread {
                    stepCountCallback.onError(error)
                }
            }
        )
        //HiHealthPointType.DATA_POINT_EXERCISE_INTENSITY
    }

    /**
     * send a query to huawei health, called in [queryHealthData]
     * @param infoType Int, the information we want, ex: for steps pass [HiHealthPointType.DATA_POINT_STEP_SUM]
     * @param onInfo Function1<[@kotlin.ParameterName] Any, Unit>, callback when  receiving data
     * @param onError Function1<[@kotlin.ParameterName] String, Unit>, callback when the query fail
     */
    private fun queryHealthInfo(
        infoType: Int,
        onInfo: (data: ArrayList<HiHealthPointData>) -> (Unit),
        onError: (error: String) -> (Unit),
        recursiveCall: Boolean = false
    ) {
        val timeout = 0
        val hiHealthDataQuery =
            HiHealthDataQuery(infoType, startTime, endTime, HiHealthDataQueryOption())

        HiHealthDataStore.execQuery(
            activity.get(),
            hiHealthDataQuery,
            timeout
        ) { resultCode: Int, data: Any ->
            Log.i(TAG, "query info resultCode: $resultCode")

            if (resultCode == HiHealthError.SUCCESS) {
                //received data successfully
                val data = data as ArrayList<HiHealthPointData>
                onInfo(data)
                havePermission()
            } else {
                Log.d(TAG, "error $data")
                if (resultCode == HiHealthError.ERR_PERMISSION_EXCEPTION) {
                    //steppi missing permission to query data from huawei health
                    //ask user to authorize ( give permissions ) again

                    //prevent infinite loop
                    if (!recursiveCall) {
                        //request permission then try querying info again
                        askUserForAuthorization()
                    } else {
                        //we already tried to ask user for permisssion and he still refused
                        //avoid asking again
                        Log.d(TAG, "user insist in refusing to share data.")
                        error("STEPPI can't read your Huawei Health data")
                    }
                }
            }

        }

        /**
         *
         */
    }

    /**
     * invoke onSuccess callback only when get all data (steps, calories, distance)
     * invoke dailySummary callback when get all data
     * called every time we receive an information, called in [queryHealthData]
     */
    private fun sendOnSuccess() {
        if (queriedSteps && queriedCalories && queriedDistance && queriedMinutes) {
            runOnUiThread {
                stepCountCallback.onSuccess(steps, calories, distance)
                stepCountCallback.dailySummary(dataMap)
            }
        }
    }

    /**
     * add data to dataMap for specific day represent by [dayEndTime]
     * called in [queryHealthData]
     */
    private fun addSummaryForDay(
        dayEndTime: Long,
        steps: Float,
        calories: Float,
        distance: Float,
        minutes: Long
    ) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dayEndTime
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.time)
        var dataModel = dataMap[date]
        if (null == dataModel) {
            dataModel = STFitnessDataModel()
        }
        dataModel.steps += steps
        dataModel.callorie += calories
        dataModel.distance += distance
        dataModel.activeMinutes += minutes
        dataMap[date] = dataModel
    }

    /**
     * run a function on the [activity]'s thread
     * @param run Function0<Unit>, the function to invoke
     * @return Unit?: returns nothing
     */
    private fun runOnUiThread(run: () -> (Unit)) = activity.get()?.runOnUiThread(run)

    private fun sendDataToActivity() = activity.get()?.runOnUiThread {
        stepCountCallback.onSuccess(steps, calories, distance)
        stepCountCallback.onStepCountReceived(steps)
        stepCountCallback.onCalorieReceived(calories)
        stepCountCallback.onDistanceReceived(distance)
        stepCountCallback.dailySummary(dataMap)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == HUAWEI_HEALTH_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "result ok")
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e(TAG, "result cancelled")
            }
        } else {
            Log.e(TAG, "requestCode NOT request_oauth")
        }
    }
}
