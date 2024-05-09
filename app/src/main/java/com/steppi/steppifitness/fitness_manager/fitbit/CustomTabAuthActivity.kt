package com.steppi.steppifitness.fitness_manager.fitbit

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import com.steppi.steppifitness.BuildConfig

class CustomTabAuthActivity : AppCompatActivity() {
    private var isCustomTabsIntent: Boolean = false
    var mCustomTabsClient: CustomTabsClient? = null
    var mCustomTabsSession: CustomTabsSession? = null
    var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    var mCustomTabsIntent: CustomTabsIntent? = null
    private val authorizeUrl = "https://www.fitbit.com/oauth2/authorize"

    private var loginUrl: String? = null
    private var CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome"
    private val redirectUri = "steppi://callback"
    private val responseType =
        "token" // 'code' = authorization code grant flow | 'token' = implicit grant flow (currently only supports implicit)
    private val expiresIn = "31536000" // 86400-1day | 604800-1week | 2592000-30days | 31536000-1yr
    private val scopes =
        "activity%20" + "heartrate%20" + "location%20" /*+ "nutrition%20" */ + "profile%20" + "settings%20" +/* "sleep%20" +*/"social%20" /*+*//*"weight"*/
    private val prompt = "login"
    private lateinit var serviceName: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginUrl = authorizeUrl + "?" +
                //"client_id=" + resources.getString(R.string.client_id) + "&" +
                "client_id=" + BuildConfig.CLIENT_ID + "&" +
                "response_type=" + responseType + "&" +
                "scope=" + scopes + "&" +
                "redirect_uri=" + redirectUri + "&" +
                "expires_in=" + expiresIn + "&" +
                "prompt=" + prompt
        onStartAuthCustomTab()
    }

    private fun onStartAuthCustomTab() {
        mCustomTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(
                name: ComponentName?,
                customTabsClient: CustomTabsClient?
            ) {
                serviceName = name!!
                mCustomTabsClient = customTabsClient
                mCustomTabsClient?.warmup(0L)
                mCustomTabsSession = mCustomTabsClient?.newSession(null)
                mCustomTabsIntent?.intent?.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                mCustomTabsIntent?.intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mCustomTabsIntent?.launchUrl(this@CustomTabAuthActivity, Uri.parse(loginUrl))
                isCustomTabsIntent = true
            }

            override fun onServiceDisconnected(name: ComponentName) {
                mCustomTabsClient = null
                if (isCustomTabsIntent) {
                    isCustomTabsIntent = false
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                }
            }
        }

        CustomTabsClient.bindCustomTabsService(
            this,
            CUSTOM_TAB_PACKAGE_NAME,
            mCustomTabsServiceConnection
        )

        mCustomTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
            .setShowTitle(true)
            .setToolbarColor(Color.parseColor("#EA4335"))
            .build()
    }

    override fun onResume() {
        super.onResume()
//        Log.e("AuthTest", "onResume")
        if (isCustomTabsIntent) {
            mCustomTabsServiceConnection?.onServiceDisconnected(serviceName)
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        mCustomTabsServiceConnection?.onServiceDisconnected(serviceName)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mCustomTabsServiceConnection?.onServiceDisconnected(serviceName)
//    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
//        Log.e("AuthTest", "onNewIntent")
        val uri = intent?.data
        val host = uri?.host

        val callbackUri = Uri.parse(redirectUri)
        val callbackHost = callbackUri.host

        if (host == callbackHost) {
            try {
                val data = Intent()
                data.putExtra("data", uri.toString())
                setResult(Activity.RESULT_OK, data)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        } else {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
