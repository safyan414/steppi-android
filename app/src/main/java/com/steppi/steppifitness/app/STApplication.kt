package com.steppi.steppifitness.app

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent
import com.instabug.library.ui.onboarding.WelcomeMessage
import com.readystatesoftware.chuck.ChuckInterceptor
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.network.response.category.STCategoryResponse
import com.steppi.steppifitness.network.response.home.STHomeScreenData
import com.steppi.steppifitness.utils.STUtils
import com.survicate.surveys.Survicate
import okhttp3.OkHttpClient
import org.acra.ACRA
import org.acra.ReportField
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes
import org.xms.f.iid.ExtensionInstanceId
import org.xms.g.utils.GlobalEnvSetting


@ReportsCrashes(
    formKey = "",
    mailTo = "abhishek@mobiiworld.com,binesh@mobiiworldcorp.com,shahnawaz.mobiiworld@gmail.com",
    mode = ReportingInteractionMode.SILENT,
    customReportContent = [ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
        ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA,
        ReportField.STACK_TRACE, ReportField.AVAILABLE_MEM_SIZE, ReportField.TOTAL_MEM_SIZE,
        ReportField.LOGCAT],
    resToastText = R.string.crash_toast
)
class STApplication : MultiDexApplication() {

    companion object {
        var homeScreenData: STHomeScreenData? = null
        var categoryResponseData: STCategoryResponse? = null
        fun applicationContext(): Context {
            return this!!.applicationContext()
        }
    }

    override fun onCreate() {
        super.onCreate()
        org.xms.g.utils.GlobalEnvSetting.init(this, null)

        ACRA.init(this)
        MultiDex.install(this)
        initInstaBug()
        FirebaseInIt()
        initFirebaseAnalytics()
        Survicate.init(this)

    }

    private fun initFirebaseAnalytics() {
        STFireBaseAnalytics.initialize(this)
    }

    private fun FirebaseInIt() {

        if (GlobalEnvSetting.isHms()) {

            // Huawei Token
            object : Thread() {
                override fun run() {
                    try {
                        val appId = AGConnectServicesConfig.fromContext(applicationContext)
                            .getString("client/app_id")
                        val token =
                            HmsInstanceId.getInstance(applicationContext).getToken(appId, "HCM")
                        Log.i("STApplication", "Token: $token")
                    } catch (e: Exception) {
                        Log.i("STApplication", "getToken failed, $e")
                    }
                }
            }.start()

        } else {

            // Firebase Token
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("Firebase", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
//                Log.e("Firebase Token", "Firebase Token : $token")
                    // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
//                Log.d("Firebase", msg)
                })
        }
    }

    private fun initInstaBug() {
        if (BuildConfig.DEBUG) {
            Instabug.Builder(this, BuildConfig.INSTA_BUG_KEY)
                .setInvocationEvents(InstabugInvocationEvent.SHAKE)
                .build()
            Instabug.setWelcomeMessageState(WelcomeMessage.State.DISABLED)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        STUtils.setAppLanguage(this, STConstants.ENGLISH_CODE)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(STUtils.setAppLanguage(base, STConstants.ENGLISH_CODE))
    }
}
