package com.steppi.steppifitness.fitness_manager.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.app.STConstants.FITBIT_PERMISSIONS_REQUEST_CODE
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.model.STFitnessDataModel
import com.steppi.steppifitness.utils.STPreference
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function4
import io.reactivex.functions.Function5
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class FitBitDataSource(
    private val activity: WeakReference<AppCompatActivity>,
    private val stepCountCallback: FitnessCallback,
    private var startTime: Long,
    private var endTime: Long
) {
    private val disposable = CompositeDisposable()
    private val authorizeUrl = "https://www.fitbit.com/oauth2/authorize"
    private val responseType = "token"
    private val redirectUri = "steppi://callback"
    private val expiresIn = "31536000" // 86400-1day | 604800-1week | 2592000-30days | 31536000-1yr
    private val prompt = "login"
    private val scopes =
        "activity%20" + "heartrate%20" + "location%20" /*+ "nutrition%20" */ + "profile%20" + "settings%20" +/* "sleep%20" +*/"social%20" /*+*//*"weight"*/
    private var loginUrl: String? = null
    private val dataMap = HashMap<String, STFitnessDataModel>()

    init {
        loginUrl = authorizeUrl + "?" +
                //"client_id=" + activity.get()!!.resources.getString(R.string.client_id) + "&" +
                "client_id=" + BuildConfig.CLIENT_ID + "&" +
                "response_type=" + responseType + "&" +
                "scope=" + scopes + "&" +
                "redirect_uri=" + redirectUri + "&" +
                "expires_in=" + expiresIn + "&" +
                "prompt=" + prompt
        when {
            getAccess() == "NULL" -> {
                // no account stored or app has previously stored access key
                removeAccess()
                //onStartAuth()
                onStartAuthCustomTab()
            }
            FitbitApi.getData(
                "1/user/-/profile.json",
                getAccess()
            ).equals("java.io.IOException") -> {
                // login failed
                removeAccess()
                //onStartAuth()
                onStartAuthCustomTab()
            }
            else -> getFitBitData(startTime, endTime)
        }
    }

    private fun onStartAuthCustomTab() {
        activity.get()?.startActivityForResult(
            Intent(activity.get(), CustomTabAuthActivity::class.java),
            FITBIT_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun getAccess(): String {
        return try {
            if (STPreference.getFitbitiToken(activity.get() as Context) == null) {
                "NULL"
            } else STPreference.getFitbitiToken(activity.get()!!)
                ?: throw PackageManager.NameNotFoundException()
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("ERROR", e.message, e)
            "NULL"
        }
    }

    private fun setAccess(token: String) {
        try {
            STPreference.saveFitbitToken(activity.get()!!, token)
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("ERROR", e.message, e)
        }
    }

    private fun setUserId(userId: String) {
        try {
            STPreference.saveFitBitUserId(activity.get()!!, userId)
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("ERROR", e.message, e)
        }
    }

    private fun removeAccess() {
        STPreference.saveFitbitToken(activity.get()!!, null)
        STPreference.saveFitBitUserId(activity.get()!!, null)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FITBIT_PERMISSIONS_REQUEST_CODE) {
                val fitBitData = data!!.getStringExtra("data")
                fitBitData?.let {
                    val accessToken = getAccessTokenFromUrl(fitBitData)
                    val userId = getUserIdFromUrl(fitBitData)
                    Log.e("FitbitToken","token: $accessToken")
                    setAccess(accessToken)
                    setUserId(userId)
                }
                getFitBitData(startTime, endTime)
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == FITBIT_PERMISSIONS_REQUEST_CODE) {
                stepCountCallback.onError("Fitbit call Cancelled ")
            }
        } else {
            activity.get()?.finish()
        }
    }

    private fun getAccessTokenFromUrl(address: String): String {
        val addressParts =
            address.split("access_token=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return addressParts[1].split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
    }

    private fun getUserIdFromUrl(address: String): String {
        val addressParts =
            address.split("user_id=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return addressParts[1].split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
    }

    private fun logout() {
        // revoke access token and delete token on storage
        FitbitApi.revokeToken(
            getAccess(),
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET
        )
        removeAccess()
        // show toast to say user has logged out
        val context = activity.get()!!.applicationContext
        val duration = Toast.LENGTH_SHORT
        val toast =
            Toast.makeText(
                context,
                "",//activity.get()!!.resources.getString(R.string.logout_confirm),
                duration
            )
        toast.show()
    }

    fun getFitBitData(startTime: Long, endTime: Long) {
        // get calendar object of today - inputed number of days
        var totalSteps = 0
        val startDate = Calendar.getInstance()
        startDate.timeInMillis = startTime
        val dfStart = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val startDateFormatted = dfStart.format(startDate.time)

        val dfStartTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        val startTimeFormatted = dfStartTime.format(startDate.time)

        val endDate = Calendar.getInstance()
        endDate.timeInMillis = endTime
        val dfEnd = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val endDateFormatted = dfEnd.format(endDate.time)

        val dfEndTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        val endTimeFormatted = dfEndTime.format(endDate.time)

        //etFitnessDataByDateFromServer(startDateFormatted)
        dataMap.clear()
//        getStepsFromServer(startDateFormatted, endDateFormatted)
//        getDistanceFromServer(startDateFormatted, endDateFormatted)
//        getCalorieFromServer(startDateFormatted, endDateFormatted)
        getCombinedData(startDateFormatted, endDateFormatted)
    }

    private fun getFitnessDataByDateFromServer(startDateFormatted: String) {
        var totalSteps = 0
        var totalCalorie = 0f
        var totalDistance = 0f
        //val url = "1/user/-/activities/steps/date/$startDateFormatted/$endDateFormatted.json"
        val url = "1/user/-/activities/date/$startDateFormatted.json"
        val jsonString = FitbitApi.getDataNew(url, getAccess())

        jsonString?.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                var obj = FitbitApi.convertStringToJson(t)
                val objLen: Int

                try {
                    val stepsArray = obj!!.getJSONArray("activities")
                    objLen = stepsArray.length()

                    for (i in objLen - 1 downTo 0) {
                        obj = stepsArray.getJSONObject(i)

                        totalSteps += obj.getString("steps").toInt()
                        totalCalorie += obj.getString("calories").toFloat()
                        totalDistance += obj.getString("distance").toFloat()
                    }
                    stepCountCallback.onSuccess(totalSteps, totalCalorie, totalDistance)
                    stepCountCallback.onStepCountReceived(totalSteps)
                    stepCountCallback.onCalorieReceived(totalCalorie)
                    stepCountCallback.onDistanceReceived(totalDistance)
                } catch (e: JSONException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                } catch (e: NullPointerException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                }
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
//                Log.e("FitbitDataSource", "onError " + e.message)
                stepCountCallback.onError("Fitbit onError " + e.message)
            }
        })
    }

    private fun getStepsFromServer(startDateFormatted: String, endDateFormatted: String) {
        var totalSteps = 0
        //val url = "1/user/-/activities/steps/date/$startDateFormatted/$endDateFormatted.json"
        val url =
            "1/user/-/activities/tracker/steps/date/$startDateFormatted/$endDateFormatted.json"
        val jsonString = FitbitApi.getDataNew(url, getAccess())

        jsonString?.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                var obj = FitbitApi.convertStringToJson(t)
                val objLen: Int
                try {
                    //val stepsArray = stepsObj!!.getJSONArray("activities-steps")
                    val stepsArray = obj!!.getJSONArray("activities-tracker-steps")
                    objLen = stepsArray.length()

                    for (i in objLen - 1 downTo 0) {
                        obj = stepsArray.getJSONObject(i)

                        val date = obj!!.getString("dateTime")
                        var dataModel = dataMap[date]
                        if (null == dataModel) {
                            dataModel = STFitnessDataModel()
                        }
                        dataModel.date = date
                        dataModel.steps = dataModel.steps + obj.getString("value").toInt()
                        dataMap[date] = dataModel

                        totalSteps += obj.getString("value").toInt()
                    }
                    stepCountCallback.onStepCountReceived(totalSteps)
                    stepCountCallback.dailySummary(dataMap)
                } catch (e: JSONException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                } catch (e: NullPointerException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                }
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
//                Log.e("FitbitDataSource", "onError " + e.message)
                stepCountCallback.onError("Fitbit onError " + e.message)
            }
        })
    }

    private fun getCombinedData(startDateFormatted: String, endDateFormatted: String) {
        //val url = "1/user/-/activities/distance/date/$startDateFormatted/$endDateFormatted.json"
        //val url = "1/user/-/activities/calories/date/$startDateFormatted/$endDateFormatted.json"
        //val url = "1/user/-/activities/calories/date/$startDateFormatted/$endDateFormatted.json"
        val urlDistance =
            "1/user/-/activities/tracker/distance/date/$startDateFormatted/$endDateFormatted.json"
        val urlCalorie =
            "1/user/-/activities/tracker/calories/date/$startDateFormatted/$endDateFormatted.json"
        val urlSteps =
            "1/user/-/activities/tracker/steps/date/$startDateFormatted/$endDateFormatted.json"
  val urlActiveMinutes =
            "1/user/-/activities/tracker/minutesVeryActive/date/$startDateFormatted/$endDateFormatted.json"
        val urlFairlyActiveMinutes =
            "1/user/-/activities/tracker/minutesFairlyActive/date/$startDateFormatted/$endDateFormatted.json"

        val distanceObservable = FitbitApi.getDataNew(urlDistance, getAccess())
        val calorieObservable = FitbitApi.getDataNew(urlCalorie, getAccess())
        val stepsObservable = FitbitApi.getDataNew(urlSteps, getAccess())
        val historyObservable = FitbitApi.getDataNew(urlActiveMinutes, getAccess())
        val historyFarilyObservable = FitbitApi.getDataNew(urlFairlyActiveMinutes, getAccess())

        Single.zip(
            distanceObservable,
            calorieObservable,
            stepsObservable,
          historyObservable,
            historyFarilyObservable,
            Function5<String, String, String,String,String, HashMap<String, STFitnessDataModel>> { distance, calorie, steps, activeMinutes, abc ->
                parseResult(distance, calorie, steps, activeMinutes, abc)
            }).subscribe(object : SingleObserver<HashMap<String, STFitnessDataModel>> {
            override fun onSuccess(data: HashMap<String, STFitnessDataModel>) {
                stepCountCallback.dailySummary(data)
            }

            override fun onSubscribe(d: Disposable) {
                disposable.add(d)
            }

            override fun onError(e: Throwable) {
                var errorMessage: String? = when (e) {
                    is HttpException -> {
                        STApiError(e).message
                    }
                    is SocketTimeoutException -> {
                        e.message
                    }
                    is IOException -> {
                        e.message
                    }
                    else -> {
                        e.message
                    }
                }
                if (errorMessage == "expired_token" || errorMessage == "invalid_token") {
                    errorMessage = STConstants.FITBIT_TOKEN_EXPIRED_OR_INVALID
                    stepCountCallback.onError(errorMessage ?: "")
                } else {
                    stepCountCallback.onError(errorMessage ?: "")
                }
            }
        })
    }

    private fun parseResult(
        distanceResponse: String,
        calorieResponse: String,
        stepsResponse: String,
        activeMinutes: String,
        activeFarilyMinutes: String
    ): HashMap<String, STFitnessDataModel> {
        dataMap.clear()
        val objDistance = FitbitApi.convertStringToJson(distanceResponse)
        val objCalorie = FitbitApi.convertStringToJson(calorieResponse)
        val objSteps = FitbitApi.convertStringToJson(stepsResponse)
        val objActiveMinutes = FitbitApi.convertStringToJson(activeMinutes)
        val objFairlyActiveMinutes = FitbitApi.convertStringToJson(activeFarilyMinutes)
        Log.d("ActiveMinutes", "${objActiveMinutes}");

        var totalDistance = 0f
        var totalCalorie = 0f
        var totalSteps = 0f
        var totalMinutes = 0f
        var objLen: Int

        try {
            //val stepsArray = stepsObj!!.getJSONArray("activities-distance")
            val distanceArray = objDistance!!.getJSONArray("activities-tracker-distance")
            objLen = distanceArray.length()
            var obj: JSONObject
            for (i in objLen - 1 downTo 0) {
                obj = distanceArray.getJSONObject(i)
                val date = obj!!.getString("dateTime")
                var dataModel = dataMap[date]
                if (null == dataModel) {
                    dataModel = STFitnessDataModel()
                }
                dataModel.date = date
                dataModel.distance = dataModel.distance + obj.getString("value").toFloat() * 1000
                dataMap[date] = dataModel
                totalDistance += obj.getString("value").toFloat() * 1000
            }
        } catch (e: JSONException) {
            Log.e("ERROR", e.message, e)
        } catch (e: NullPointerException) {
            Log.e("ERROR", e.message, e)
        }
        try {
            //val stepsArray = stepsObj!!.getJSONArray("activities-distance")
            val calorieArray = objCalorie!!.getJSONArray("activities-tracker-calories")
            objLen = calorieArray.length()
            var obj: JSONObject
            for (i in objLen - 1 downTo 0) {
                obj = calorieArray.getJSONObject(i)
                val date = obj!!.getString("dateTime")
                var dataModel = dataMap[date]
                if (null == dataModel) {
                    dataModel = STFitnessDataModel()
                }
                dataModel.date = date
                dataModel.callorie = dataModel.callorie + obj.getString("value").toFloat()
                dataMap[date] = dataModel
                totalCalorie += obj.getString("value").toFloat()
            }
        } catch (e: JSONException) {
            Log.e("ERROR", e.message, e)
        } catch (e: NullPointerException) {
            Log.e("ERROR", e.message, e)
        }
        try {
            //val stepsArray = stepsObj!!.getJSONArray("activities-distance")
            val stepsArray = objSteps!!.getJSONArray("activities-tracker-steps")
            objLen = stepsArray.length()
            var obj: JSONObject
            for (i in objLen - 1 downTo 0) {
                obj = stepsArray.getJSONObject(i)
                val date = obj!!.getString("dateTime")
                var dataModel = dataMap[date]
                if (null == dataModel) {
                    dataModel = STFitnessDataModel()
                }
                dataModel.date = date
                dataModel.steps = dataModel.steps + obj.getString("value").toDouble().roundToInt()
                dataMap[date] = dataModel
                totalSteps += obj.getString("value").toFloat().roundToInt()
            }
        } catch (e: JSONException) {
            Log.e("ERROR", e.message, e)
        } catch (e: NullPointerException) {
            Log.e("ERROR", e.message, e)
        }
        var myDataModel: STFitnessDataModel?
      try {
            //val stepsArray = stepsObj!!.getJSONArray("activities-distance")
            val activeArray = objActiveMinutes!!.getJSONArray("activities-tracker-minutesVeryActive")
            objLen = activeArray.length()
            var obj: JSONObject

            for (i in objLen - 1 downTo 0) {
                obj = activeArray.getJSONObject(i)
                val date = obj!!.getString("dateTime")
                myDataModel = dataMap[date]
                if (null == myDataModel) {
                    myDataModel = STFitnessDataModel()
                }
                myDataModel?.date = date
                myDataModel?.activeMinutes = myDataModel.activeMinutes + obj.getString("value").toDouble().roundToInt()
                dataMap[date] = myDataModel
                totalMinutes += obj.getString("value").toFloat().roundToInt()
            }
        } catch (e: JSONException) {
            Log.e("ERROR", e.message, e)
        } catch (e: NullPointerException) {
            Log.e("ERROR", e.message, e)
        }

        try {
            //val stepsArray = stepsObj!!.getJSONArray("activities-distance")
            val activeArray = objFairlyActiveMinutes!!.getJSONArray("activities-tracker-minutesFairlyActive")
            objLen = activeArray.length()
            var obj: JSONObject
            for (i in objLen - 1 downTo 0) {
                obj = activeArray.getJSONObject(i)
                val date = obj!!.getString("dateTime")
                myDataModel = dataMap[date]
                if (null == myDataModel) {
                    myDataModel = STFitnessDataModel()
                }
                myDataModel.date = date
                myDataModel.activeMinutes = myDataModel.activeMinutes + obj.getString("value").toDouble().roundToInt()
                dataMap[date] = myDataModel
                totalMinutes += obj.getString("value").toFloat().roundToInt()
            }
        } catch (e: JSONException) {
            Log.e("ERROR", e.message, e)
        } catch (e: NullPointerException) {
            Log.e("ERROR", e.message, e)
        }
        return dataMap
    }

    private fun getDistanceFromServer(startDateFormatted: String, endDateFormatted: String) {
        var total = 0f
        //val url = "1/user/-/activities/distance/date/$startDateFormatted/$endDateFormatted.json"
        val url =
            "1/user/-/activities/tracker/distance/date/$startDateFormatted/$endDateFormatted.json"
        val jsonString = FitbitApi.getDataNew(url, getAccess())
        jsonString?.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                var obj = FitbitApi.convertStringToJson(t)
                val objLen: Int
                try {
                    //val stepsArray = stepsObj!!.getJSONArray("activities-distance")
                    val stepsArray = obj!!.getJSONArray("activities-tracker-distance")
                    objLen = stepsArray.length()

                    for (i in objLen - 1 downTo 0) {
                        obj = stepsArray.getJSONObject(i)
                        val date = obj!!.getString("dateTime")
                        var dataModel = dataMap[date]
                        if (null == dataModel) {
                            dataModel = STFitnessDataModel()
                        }
                        dataModel.date = date
                        dataModel.distance =
                            dataModel.distance + obj.getString("value").toFloat() * 1000
                        dataMap[date] = dataModel
                        total += obj.getString("value").toFloat()
                    }
                    stepCountCallback.onDistanceReceived(total)
                    stepCountCallback.dailySummary(dataMap)
                } catch (e: JSONException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                } catch (e: NullPointerException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                }
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
//                Log.e("FitbitDataSource", "onError " + e.message)
                stepCountCallback.onError("Fitbit onError " + e.message)
            }
        })
    }

    private fun getCalorieFromServer(startDateFormatted: String, endDateFormatted: String) {
        var total = 0f
        //val url = "1/user/-/activities/calories/date/$startDateFormatted/$endDateFormatted.json"
        val url =
            "1/user/-/activities/tracker/calories/date/$startDateFormatted/$endDateFormatted.json"
        val jsonString = FitbitApi.getDataNew(url, getAccess())

        jsonString?.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                var obj = FitbitApi.convertStringToJson(t)
                val objLen: Int
                try {
                    //val stepsArray = stepsObj!!.getJSONArray("activities-calories")
                    val stepsArray = obj!!.getJSONArray("activities-tracker-calories")
                    objLen = stepsArray.length()
                    for (i in objLen - 1 downTo 0) {
                        obj = stepsArray.getJSONObject(i)

                        val date = obj!!.getString("dateTime")
                        var dataModel = dataMap[date]
                        if (null == dataModel) {
                            dataModel = STFitnessDataModel()
                        }
                        dataModel.date = date
                        dataModel.callorie = dataModel.callorie + obj.getString("value").toInt()
                        dataMap[date] = dataModel
                        total += obj.getString("value").toInt()
                    }
                    stepCountCallback.onCalorieReceived(total)
                    stepCountCallback.dailySummary(dataMap)
                } catch (e: JSONException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                } catch (e: NullPointerException) {
                    Log.e("ERROR", e.message, e)
                    stepCountCallback.onError("Fitbit onError " + e.message)
                }
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.e("FitbitDataSource", "onError " + e.message)
                stepCountCallback.onError("Fitbit onError " + e.message)
            }
        })
    }

    fun onDestroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
