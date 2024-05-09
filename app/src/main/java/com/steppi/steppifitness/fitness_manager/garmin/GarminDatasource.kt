package com.steppi.steppifitness.fitness_manager.garmin

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.*
import com.github.scribejava.core.oauth.OAuth10aService
import com.google.gson.Gson
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.utils.STPreference
import com.steppi.steppifitness.utils.STUtils
import org.json.JSONObject
import java.lang.ref.WeakReference

class GarminDatasource(
    private val activity: WeakReference<AppCompatActivity>,
    private val stepCountCallback: FitnessCallback,
    private var startTime: Long,
    private var endTime: Long
) {
    var service: OAuth10aService
    var progressDialog: Dialog
    var requestToken: OAuth1RequestToken? = null

    init {
        service = ServiceBuilder(BuildConfig.CONSUMER_KEY)
            .apiSecret(BuildConfig.CONSUMER_SECRET)
            .callback("steppi://garmin_callback")
            .build(GarminAPIHelper.getInstance());
        progressDialog = STUtils.getProgressDialog(activity.get() as Context)
        if (getAccess() == "NULL") {
            removeAccess()
//            try {
//                service.requestToken?.let {
            GetRequestToken().execute()
//                } ?: kotlin.run {
//                    stepCountCallback.onError("Garmin call Cancelled")
//                }
//            } catch (ex: NetworkOnMainThreadException) {
////                stepCountCallback.onError("Garmin call Cancelled")
//            }
        } else {
            val accessToken = Gson().fromJson(getAccess(), OAuth1AccessToken::class.java)
            GetGarminUserId().execute(accessToken)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == STConstants.GARMIN_PERMISSIONS_REQUEST_CODE) {
                val garminData = data!!.getStringExtra("data")
                garminData?.let {
                    val uri = Uri.parse(it)
                    val oauth_verifier = uri.getQueryParameter("oauth_verifier")
                    GetAccessToken().execute(requestToken, oauth_verifier)
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == STConstants.GARMIN_PERMISSIONS_REQUEST_CODE) {
                stepCountCallback.onError("Garmin call Cancelled ")
            }
        } else {
            activity.get()?.finish()
        }
    }

    inner class GetAccessToken : AsyncTask<Any, Void, OAuth1AccessToken>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Any?): OAuth1AccessToken? {
            val requestToken =
                service.getAccessToken(params[0] as OAuth1RequestToken, params[1] as String)
            return requestToken
        }

        override fun onPostExecute(result: OAuth1AccessToken?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            val token = result
            token?.let {
                setAccessToken(it)
                GetGarminUserId().execute(token)
            }
        }
    }

    private fun setAccessToken(token: OAuth1AccessToken) {
        try {
//            Gson().toJson(token)
            STPreference.saveGarminToken(activity.get()!!, token.token)
            STPreference.saveGarminTokenSecret(activity.get()!!, token.tokenSecret)
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("ERROR", e.message, e)
        }
    }

    private fun getAccess(): String {
        return if (STPreference.getGarminToken(activity.get() as Context) == null) {
            "NULL"
        } else STPreference.getGarminToken(activity.get()!!) ?: "NULL"
    }

    private fun removeAccess() {
        STPreference.saveGarminToken(activity.get()!!, null)
    }

    private fun setUserId(userId: String) {
        try {
            STPreference.saveGarminUserId(activity.get()!!, userId)
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("ERROR", e.message, e)
        }
    }

    inner class GetRequestToken : AsyncTask<Void, Void, OAuth1RequestToken>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Void): OAuth1RequestToken {
            val requestToken = service.requestToken
            return requestToken
        }

        override fun onPostExecute(result: OAuth1RequestToken) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            requestToken = result
            requestToken?.let {
                val authUrl = service.getAuthorizationUrl(it)
                val intent = Intent(activity.get(), GarminCustomTabAuthActivity::class.java)
                intent.putExtra("authUrl", authUrl)
                activity.get()?.startActivityForResult(
                    intent,
                    STConstants.GARMIN_PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }

//    inner class GetGarminData : AsyncTask<OAuth1AccessToken, Void, Void?>() {
//
//
//        override fun doInBackground(vararg params: OAuth1AccessToken?): Void? {
//            val startTimeInSec = startTime / 1000
//            val endTimeInSec = endTime / 1000
//            val request = OAuthRequest(
//                Verb.GET,
//                "https://healthapi.garmin.com/wellness-api/rest/dailies?uploadStartTimeInSeconds=$startTimeInSec&uploadEndTimeInSeconds=$endTimeInSec"
//            )
//            service.signRequest(params[0], request)
//            service.execute(request, object : OAuthAsyncRequestCallback<Response> {
//                override fun onCompleted(response: Response?) {
//                    Log.e("response", response?.body)
//                    try {
//                        val dataArray = JSONArray(response?.body)
//                        for (i in 0..dataArray.length())
//                        {
//                            val obj: JSONObject = dataArray[i] as JSONObject
//                            val dataModel = STFitnessDataModel()
//                            dataModel.steps = obj.optInt("steps").toFloat()
//                            dataModel.callorie = obj.optInt("activeKilocalories").toFloat()
//                            dataModel.distance = obj.optDouble("distanceInMeters").toFloat()
//
//                            val date = obj.optString("calendarDate")
//                            dataMap[date] = dataModel
//
//                        }
//
//
////                        stepCountCallback.onStepCountReceived(dataModel.steps.toInt())
////                        stepCountCallback.onCalorieReceived(dataModel.callorie)
////                        stepCountCallback.onDistanceReceived(dataModel.distance)
////                        stepCountCallback.onSuccess(
////                            dataModel.steps.toInt(),
////                            dataModel.callorie,
////                            dataModel.distance
////                        )
//                        stepCountCallback.dailySummary(dataMap)
//
//                    } catch (e: Exception) {
//                        stepCountCallback.onError(e.message?:"")
//                    }
//
//
//                }
//
//                override fun onThrowable(t: Throwable?) {
//                    Log.e("Error", "api error " + t?.message)
//                    stepCountCallback.onError(t?.message ?: "")
//
//                }
//
//            })
//            return null
//        }
//
//        override fun onPostExecute(result: Void?) {
//            super.onPostExecute(result)
//            progressDialog.dismiss()
//        }
//
//    }

    inner class GetGarminUserId : AsyncTask<OAuth1AccessToken, Void, Void?>() {
        lateinit var accessToken: OAuth1AccessToken

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.show()
        }

        override fun doInBackground(vararg params: OAuth1AccessToken?): Void? {
            accessToken = params[0]!!
            val request = OAuthRequest(
                Verb.GET,
                "https://healthapi.garmin.com/wellness-api/rest/user/id"
            )
            service.signRequest(params[0], request)
            service.execute(request, object : OAuthAsyncRequestCallback<Response> {
                override fun onCompleted(response: Response?) {
                    if (response != null && response.code == 200) {
                        val userId = JSONObject(response.body).getString("userId")
                        setUserId(userId)
                        stepCountCallback.dailySummary(HashMap())
                    }
                }

                override fun onThrowable(t: Throwable?) {
                    t?.printStackTrace()
                }
            })
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
//            GetGarminData().execute(accessToken)
        }
    }
}
