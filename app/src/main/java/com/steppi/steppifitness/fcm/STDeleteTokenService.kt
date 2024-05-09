package com.steppi.steppifitness.fcm

import android.app.IntentService
import android.content.Intent
import com.steppi.steppifitness.utils.STPreference
import java.io.IOException

// [Modified By HMSConvertor] import com.google.firebase.iid.FirebaseInstanceId
import org.xms.f.iid.ExtensionInstanceId

class STDeleteTokenService : IntentService(TAG) {

    override fun onHandleIntent(intent: Intent?) {
        try {
            // FirebaseInstanceId.getInstance().deleteInstanceId()
            // [Modified By HMSConvertor]
            ExtensionInstanceId.getInstance(this).deleteInstanceId()
            STPreference.saveDeviceId(applicationContext, null)

            // FirebaseInstanceId.getInstance().token
            // [Modified By HMSConvertor]
            ExtensionInstanceId.getInstance(this).token
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        val TAG = STDeleteTokenService::class.java.simpleName
    }
}
