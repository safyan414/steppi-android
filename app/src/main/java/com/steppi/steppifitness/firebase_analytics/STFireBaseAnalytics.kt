package com.steppi.steppifitness.firebase_analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle

// [Modified By HMSConvertor] import com.google.firebase.analytics.FirebaseAnalytics
import org.xms.f.analytics.ExtensionAnalytics

object STFireBaseAnalytics {
    // [Modified By HMSConvertor]     private var mmInstance: FirebaseAnalytics? = null
    private var mmInstance: ExtensionAnalytics? = null

    // [Modified By HMSConvertor]     val instance: FirebaseAnalytics
    val instance: ExtensionAnalytics
        @Synchronized get() {
            checkNotNull(mmInstance) { "Call initialize() before getInstance()" }
            // [Modified By HMSConvertor]   return mmInstance as FirebaseAnalytics
            return mmInstance as ExtensionAnalytics
        }

    @Synchronized
    fun initialize(context: Context) {
        // [Modified By HMSConvertor]         check(mmInstance == null) { "Extra call to initialize analytics trackers" }
        check(mmInstance ?.isSameAs( null ) ?: ( null == null )) { "Extra call to initialize analytics trackers" }
        // [Modified By HMSConvertor]         mmInstance = FirebaseAnalytics.getInstance(context)
        mmInstance = ExtensionAnalytics.getInstance(context)
        mmInstance!!.setAnalyticsCollectionEnabled(true)
        mmInstance!!.setMinimumSessionDuration(10000)
        mmInstance!!.setSessionTimeoutDuration(500)
    }

    fun trackEvent(userAction: String, eventName: String, event: String) {
        val bundle = Bundle()
        bundle.putString(eventName, event)
        mmInstance!!.logEvent(userAction, bundle)
    }

    fun trackScreen(activityContext: Activity, screenName: String) {
        mmInstance!!.setCurrentScreen(activityContext, screenName, null)
    }

    fun trackEventValue(userAction: String, eventNameList: HashMap<String, String>) {
        val bundle = Bundle()
        for (i in eventNameList) {
            bundle.putString(i.key, i.value)
        }
        mmInstance!!.logEvent(userAction, bundle)
    }
}
