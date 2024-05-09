package com.steppi.steppifitness.fitness_manager.googlefit

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_DELTA
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.tasks.Task
import com.steppi.steppifitness.app.STConstants.GOOGLE_FIT_PERMISSIONS_REQUEST_CODE
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.model.STFitnessDataModel
import com.steppi.steppifitness.utils.STPreference
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class GoogleFitDataSource(
    private val activity: WeakReference<AppCompatActivity>,
    private val stepCountCallback: FitnessCallback,
    private var startTime: Long,
    private var endTime: Long
) {
    private var fitnessOptions: FitnessOptions? = null
    private var totalSteps = 0
    private var totalStepsUserInput = 0
    private var totalCalorie = 0f
    private var totalCalorieUserInput = 0f
    private var totalDistance = 0f
    private var totalDistanceUserInput = 0f
    private val dataMap = HashMap<String, STFitnessDataModel>()
    private var gso: GoogleSignInOptions? = null
    private var signInClient: GoogleSignInClient? = null

    init {
        fitnessOptions = FitnessOptions.builder()
            .addDataType(TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_SPEED_SUMMARY, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_MOVE_MINUTES, FitnessOptions.ACCESS_READ)
            .build()
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .addExtension(fitnessOptions)
            .build()
        signInClient = GoogleSignIn.getClient(activity.get()!!, gso!!)
        checkForUserPermission()
    }

    private fun checkForUserPermission() {
        if (!GoogleSignIn.hasPermissions(
                GoogleSignIn.getLastSignedInAccount(activity.get()),
                fitnessOptions!!
            )
        ) {
            activity.get()?.startActivityForResult(
                signInClient!!.signInIntent,
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE
            )
        } else {
            getCustomDataStore(startTime, endTime)
            val account = GoogleSignIn.getLastSignedInAccount(activity.get())
        }
    }

//    private fun checkForUserPermission() {
//        if (!GoogleSignIn.hasPermissions(
//                GoogleSignIn.getLastSignedInAccount(activity.get()),
//                fitnessOptions!!
//            )
//        ) {
//            GoogleSignIn.requestPermissions(
//                activity.get()!!, // your activity
//                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
//                GoogleSignIn.getLastSignedInAccount(activity.get()),
//                fitnessOptions!!
//            )
//        } else {
//            getCustomDataStore(startTime, endTime)
//        }
//    }

    private fun getCustomDataStore(startTime: Long, endTime: Long) {
        Thread(Runnable {
            totalSteps = 0
            totalCalorie = 0f
            totalDistance = 0f
            totalStepsUserInput = 0
            totalCalorieUserInput = 0f
            totalDistanceUserInput = 0f

            val ESTIMATED_STEP_DELTAS = DataSource.Builder()
                .setDataType(TYPE_STEP_COUNT_DELTA)
                .setType(DataSource.TYPE_DERIVED)
                .setStreamName("estimated_steps")
                .setAppPackageName("com.google.android.gms")
                .build()
            val readRequest = DataReadRequest.Builder()
                .aggregate(ESTIMATED_STEP_DELTAS, DataType.AGGREGATE_STEP_COUNT_DELTA)
                .aggregate(DataType.TYPE_DISTANCE_DELTA, DataType.AGGREGATE_DISTANCE_DELTA)
                .aggregate(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED)
                .aggregate(DataType.TYPE_MOVE_MINUTES, DataType.AGGREGATE_MOVE_MINUTES)
                //.bucketByActivitySegment(1, TimeUnit.MILLISECONDS)
                .bucketByTime(1, TimeUnit.DAYS)
                .enableServerQueries()
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build()
            //.aggregate(DataType.TYPE_ACTIVITY_SEGMENT, DataType.AGGREGATE_ACTIVITY_SUMMARY)
            //.bucketByActivitySegment(1, TimeUnit.MILLISECONDS)
            //.bucketByTime(1, TimeUnit.DAYS)

            dataMap.clear()
            Fitness.getHistoryClient(
                activity.get()!!,
//        GoogleSignIn.getLastSignedInAccount(activity.get()!!)!!
                GoogleSignIn.getAccountForExtension(activity.get()!!, fitnessOptions!!)
            ).readData(readRequest)
                .addOnSuccessListener { dataSet ->
                    //Used for aggregated data
                    when {
                        dataSet.buckets?.size!! > 0 -> {
//                            Log.i("GoogleFit", "Get Buckets")
                            for (bucket in dataSet.buckets!!) {
                                val dataSets = bucket.dataSets
                                for (ds in dataSets) {
//                                    Log.i("GoogleFit", "dataSets $ds")
                                    showDataSet(ds)
                                }
                            }
                        }

                        dataSet.dataSets?.size!! > 0 -> {
//                            Log.i("GoogleFit", "Get DataSet")
                            for (ds in dataSet.dataSets!!) {
                                showDataSet(ds)
                            }
                        }
                        else -> Log.i("GoogleFit", "No history found for this user")
                    }//Used for non-aggregated data
                    //calculateDistance()
                    Handler(Looper.getMainLooper()).post {
                        stepCountCallback.onSuccess(totalSteps, totalCalorie, totalDistance)
                        stepCountCallback.onStepCountReceived(totalSteps, totalStepsUserInput)
                        stepCountCallback.onCalorieReceived(totalCalorie, totalCalorieUserInput)
                        stepCountCallback.onDistanceReceived(totalDistance, totalDistanceUserInput)
                        stepCountCallback.dailySummary(dataMap)
                    }
                }
                .addOnFailureListener { e ->
                    run {
                        Handler(Looper.getMainLooper()).post {
                            stepCountCallback.onError(e.message!!)
                            Log.e(
                                "GoogleFitError=> ",
                                "There was a problem getting the step count.",
                                e
                            )
                        }
                    }
                }
        }).start()
    }

    private fun showDataSet(dataSet: DataSet) {
        var steps: Int
        var stepsUser: Int
        var calorie: Float
        var calorieUser: Float
        var distance: Float
        var distanceUser: Float
        var activeMinutes: Int
        val calendar = Calendar.getInstance()
        for (dp in dataSet.dataPoints) {
            for (field in dp.dataType.fields) {
                calendar.timeInMillis = dp.getStartTime(TimeUnit.MILLISECONDS)
//                val date = STUtils.getFormattedDate(calendar, "yyyy-MM-dd")
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.time)
                var dataModel = dataMap[date]
                if (null == dataModel) {
                    dataModel = STFitnessDataModel()
                }
                dataModel.date = date
                Log.e("Google fit DataType", "Data types: ${dataSet.dataType.name}")
                when (dataSet.dataType.name) {
                    "com.google.step_count.delta" -> {
                        steps = dp.getValue(field).asInt()
                        dataModel.steps =
                            (dataModel.steps) + steps
                        stepsUser = 0
                        if (dp.originalDataSource.streamName == "user_input") {
                            stepsUser = dp.getValue(field).asInt()
                            dataModel.stepsUserInput =
                                (dataModel.stepsUserInput) + stepsUser
                        } else {
                        }
                        //                        Log.d(
                        //                            "TimeCheckMap",
                        //                            "date $date steps: ${steps}"
                        //                        )
                        addSteps(steps, stepsUser)
                    }
                    "com.google.calories.expended" -> {
                        calorie = dp.getValue(field).asFloat()
                        dataModel.callorie =
                            (dataModel.callorie) + calorie
                        calorieUser = 0f
                        if (dp.originalDataSource.streamName.contains("user_input")) {
                            calorieUser = dp.getValue(field).asFloat()
                            dataModel.callorieUserInput =
                                (dataModel.callorieUserInput) + calorieUser
                        }
                        //                        Log.d(
                        //                            "TimeCheckMap",
                        //                            "date $date calorie: ${calorie}"
                        //                        )
                        addCalorie(calorie, calorieUser)
                    }
                    "com.google.distance.delta" -> {
                        distance = dp.getValue(field).asFloat()
                        dataModel.distance =
                            (dataModel.distance) + distance
                        distanceUser = 0f
                        if (dp.originalDataSource.streamName.contains("user_input")) {
                            distanceUser = dp.getValue(field).asFloat()
                            dataModel.distanceUserInput =
                                (dataModel.distanceUserInput) + distanceUser
                        }
                        //                        Log.d(
                        //                            "TimeCheckMap",
                        //                            "date $date distance: ${distance}"
                        //                        )
                        addDistance(distance, distanceUser)
                    }
                    "com.google.active_minutes" -> {
                        activeMinutes = dp.getValue(field).asInt()
                        dataModel.activeMinutes =
                            (dataModel.activeMinutes) + activeMinutes
                    }
                }
                dataMap[date] = dataModel
            }
        }
    }

    private fun addSteps(step: Int, stepsUser: Int) {
        totalSteps += step
        totalStepsUserInput += stepsUser
    }

    private fun addCalorie(totalCalorie: Float, calorieUser: Float) {
        this.totalCalorie += totalCalorie
        this.totalCalorieUserInput += calorieUser
    }

    private fun addDistance(totalDistance: Float, distanceUser: Float) {
        this.totalDistance += totalDistance
        this.totalDistanceUserInput += distanceUser
    }

    fun connect(startTime: Long, endTime: Long) {
        this.startTime = startTime
        this.endTime = endTime
        checkForUserPermission()
    }

    fun onStart(startTime: Long, endTime: Long) {
        connect(startTime, endTime)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                onStart(startTime, endTime)
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
                //getCustomDataStore(startTime, endTime)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("GoogleFit", "RESULT_CANCELED")
            }
        } else {
            Log.e("GoogleFit", "requestCode NOT request_oauth")
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
//            Log.e(
//                "email",
//                "email : ${account?.email/*GoogleSignIn.getLastSignedInAccount(activity.get()!!)!!.email*/}"
//            )
//            Log.e(
//                "account id",
//                "account id : ${account?.id/*GoogleSignIn.getLastSignedInAccount(activity.get()!!)!!.id*/}"
//            )
            account?.id?.let { accountId ->
                STPreference.saveGoogleFitUserId(activity.get()!!, accountId)
            }
        } catch (e: ApiException) { // The ApiException status code indicates the detailed failure reason.
// Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
//            updateUI(null)
        }
    }
}
