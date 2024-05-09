package com.steppi.steppifitness.fitness_manager.fitbit

import android.util.Base64
import android.util.Log
import com.steppi.steppifitness.network.STRetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.ExecutionException

class FitbitApi {
    companion object {
        private var urlString: String? = null // string to pass in url
        private var accessToken: String? = null // string to pass in access token
        private var requestMethod: String? = null // string to pass in GET or POST
        private var authHeader: String? = null // string to pass in authorization header first word
        private var isRevoke: Boolean? =
            false // boolean to check if action is revoking access token
        private var clientId: String? = null
        private var clientSecret: String? = null

        // this method retrieves data from api and returns resulting json string
        fun getData(url: String, aToken: String): String? {
            urlString = url
            accessToken = aToken
            requestMethod = "GET"
            authHeader = "Bearer"
            isRevoke = false
            urlString = urlString!!.replace("https://api.fitbit.com/", "")
            return try {
                //return RetrieveDataFromApi().execute().get()
                //return getFitBitData().blockingGet()
                getFitBitData().toString()
            } catch (e: InterruptedException) {
                Log.e("ERROR", e.message, e)
                null
            } catch (e: ExecutionException) {
                Log.e("ERROR", e.message, e)
                null
            }
        }

        // this method retrieves data from api and returns resulting json string
        fun getDataNew(url: String, aToken: String): Single<String>? {
            urlString = url
            accessToken = aToken
            requestMethod = "GET"
            authHeader = "Bearer"
            isRevoke = false
            return try {
                //return RetrieveDataFromApi().execute().get()
                getFitBitData()
            } catch (e: InterruptedException) {
                Log.e("ERROR", e.message, e)
                null
            } catch (e: ExecutionException) {
                Log.e("ERROR", e.message, e)
                null
            }
        }

        // this method extracts information from json string and stores them in a json object
        fun convertStringToJson(jsonString: String): JSONObject? {
            return try {
                JSONObject(jsonString)
            } catch (e: JSONException) {
                Log.e("ERROR", e.message, e)
                null
            }
        }

        // this method revokes current access token
        fun revokeToken(aToken: String, id: String, secret: String) {
            urlString = "oauth2/revoke"//"https://api.fitbit.com/oauth2/revoke"
            accessToken = aToken
            requestMethod = "POST"
            authHeader = "Basic"
            isRevoke = true
            clientId = id
            clientSecret = secret
            try {
                getFitBitData()
                // RetrieveDataFromApi().execute().get()
            } catch (e: InterruptedException) {
                Log.e("ERROR", e.message, e)
            } catch (e: ExecutionException) {
                Log.e("ERROR", e.message, e)
            }
        }

        private fun getFitBitData(): Single<String> {
            if (isRevoke!!) {
                return STRetrofitClient.createFitbitClient()
                    .revokeFitbitToken(
                        urlString!!,
                        "$authHeader " + Base64.encodeToString(
                            "$clientId:$clientSecret".toByteArray(charset("UTF-8")), Base64.DEFAULT
                        ), accessToken!!
                    )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map { it.string() }
            } else {
                return STRetrofitClient.createFitbitClient()
                    .getFitbitData(urlString!!, "$authHeader $accessToken")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.string().toString() }
            }
            // return data
        }
    }
}
