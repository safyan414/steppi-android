package com.steppi.steppifitness.fitness_manager.garmin

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

class GarminCustomTabAuthActivity : AppCompatActivity() {
    private var isCustomTabsIntent: Boolean = false
    var mCustomTabsClient: CustomTabsClient? = null
    var mCustomTabsSession: CustomTabsSession? = null
    var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    var mCustomTabsIntent: CustomTabsIntent? = null

    private var loginUrl: String? = null
    private var CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome"
    private val redirectUri = "steppi://garmin_callback"
    private val responseType =
        "token" // 'code' = authorization code grant flow | 'token' = implicit grant flow (currently only supports implicit)
    private val expiresIn = "86400" // 86400-1day | 604800-1week | 2592000-30days | 31536000-1yr
    private val scopes =
        "activity%20" + "heartrate%20" + "location%20" /*+ "nutrition%20" */ + "profile%20" + "settings%20" +/* "sleep%20" +*/"social%20" /*+*//*"weight"*/
    private val prompt = "login"
    lateinit var serviceName: ComponentName
    companion object {
        lateinit var loginUrlGlobal: String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getString("authUrl")?.let {
            loginUrl = it
            loginUrlGlobal = it
        }
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
                loginUrl?.let {
                    mCustomTabsIntent?.launchUrl(this@GarminCustomTabAuthActivity, Uri.parse(
                        it))
                }

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
            .setToolbarColor(Color.parseColor("#0090CB"))
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
