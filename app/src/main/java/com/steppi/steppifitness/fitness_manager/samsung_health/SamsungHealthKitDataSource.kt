package com.steppi.steppifitness.fitness_manager.samsung_health

import android.app.AlertDialog
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult
import com.samsung.android.sdk.healthdata.HealthConstants
import com.samsung.android.sdk.healthdata.HealthDataStore
import com.samsung.android.sdk.healthdata.HealthPermissionManager
import com.steppi.steppifitness.R
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import java.lang.ref.WeakReference

class SamsungHealthKitDataSource(
    private val activity: WeakReference<AppCompatActivity>,
    private val stepCountCallback: FitnessCallback,
    private var startTime: Long,
    private var endTime: Long
) {
    private var mStore: HealthDataStore? = null
    private var mReporter: StepCountReporter? = null
    internal var APP_TAG = "HealthKitDataSource"
    fun init() {
        // Create a HealthDataStore instance and set its listener
        mStore = HealthDataStore(activity.get(), mConnectionListener)
        // Request the connection to the health data store
        mStore?.connectService()
    }

    private val mConnectionListener: HealthDataStore.ConnectionListener =
        object : HealthDataStore.ConnectionListener {
            override fun onConnected() {
                Log.d(APP_TAG, "Health data service is connected.")
                mReporter = StepCountReporter(mStore!!)
                if (isPermissionAcquired()) {
                    mReporter?.start(
                        /* mStepCountObserver,
                         mDistanceObserver,
                         mCalorieObserver,*/
                        stepCountCallback,
                        startTime,
                        endTime
                    )
                } else {
                    requestPermission()
                }
            }

            override fun onConnectionFailed(error: HealthConnectionErrorResult) {
                Log.d(APP_TAG, "Health data service is not available.")
                showConnectionFailureDialog(error)
            }

            override fun onDisconnected() {
                Log.d(APP_TAG, "Health data service is disconnected.")
                if (activity.get()?.isFinishing != true) {
                    mStore?.connectService()
                }
            }
        }

    private fun isPermissionAcquired(): Boolean {
        val permKey = HealthPermissionManager.PermissionKey(
            HealthConstants.StepCount.HEALTH_DATA_TYPE,
            HealthPermissionManager.PermissionType.READ
        )
        val pmsManager = HealthPermissionManager(mStore)
        try {
            // Check whether the permissions that this application needs are acquired
            val resultMap = pmsManager.isPermissionAcquired(setOf(permKey))
            return resultMap[permKey]!!
        } catch (e: Exception) {
            Log.e(APP_TAG, "Permission request fails.", e)
        }
        return false
    }

    private fun requestPermission() {
        val permKey = HealthPermissionManager.PermissionKey(
            HealthConstants.StepCount.HEALTH_DATA_TYPE,
            HealthPermissionManager.PermissionType.READ
        )
        val pmsManager = HealthPermissionManager(mStore)
        try {
            // Show user permission UI for allowing user to change options
            pmsManager.requestPermissions(setOf(permKey), activity.get())
                .setResultListener { result ->
                    Log.d(APP_TAG, "Permission callback is received.")
                    val resultMap = result.resultMap

                    if (resultMap.containsValue(java.lang.Boolean.FALSE)) {
                        stepCountCallback.onStepCountReceived(0)
                        showPermissionAlarmDialog()
                    } else {
                        // Get the current step count and display it
                        mReporter?.start(
                            /*mStepCountObserver,
                            mDistanceObserver,
                            mCalorieObserver,*/
                            stepCountCallback,
                            startTime,
                            endTime
                        )
                    }
                }
        } catch (e: Exception) {
            Log.e(APP_TAG, "Permission setting fails.", e)
        }
    }

/*    private val mStepCountObserver = object : StepCountReporter.StepCountObserver {
        override fun onChanged(count: Int) {
            stepCountCallback.onStepCountReceived(count)
        }

        override fun onError(msg: String) {
            stepCountCallback.onError(msg)
        }

    }
    private val mDistanceObserver = object : StepCountReporter.StepCountObserver {
        override fun onChanged(count: Int) {
            stepCountCallback.onDistanceReceived(count.toFloat())
        }
    }
    private val mCalorieObserver = object : StepCountReporter.StepCountObserver {
        override fun onChanged(count: Int) {
            stepCountCallback.onCalorieReceived(count.toFloat())
        }
    }*/

    private fun showPermissionAlarmDialog() {
        if (activity.get()?.isFinishing == true) {
            return
        }

        val alert = AlertDialog.Builder(activity.get())
        alert.setTitle("Notice")
            .setMessage("All permissions should be acquired")
            .setPositiveButton("Ok", null)
            .show()
    }

    private fun showConnectionFailureDialog(error: HealthConnectionErrorResult) {
        if (activity.get()?.isFinishing == true) {
            return
        }
        val alert = AlertDialog.Builder(activity.get())

        if (error.hasResolution()) {
            when (error.errorCode) {
                HealthConnectionErrorResult.PLATFORM_NOT_INSTALLED -> alert.setMessage(R.string.msg_req_install)
                HealthConnectionErrorResult.OLD_VERSION_PLATFORM -> alert.setMessage(R.string.msg_req_upgrade)
                HealthConnectionErrorResult.PLATFORM_DISABLED -> alert.setMessage(R.string.msg_req_enable)
                HealthConnectionErrorResult.USER_AGREEMENT_NEEDED -> alert.setMessage(R.string.msg_req_agree)
                else -> alert.setMessage(R.string.msg_req_available)
            }
        } else {
            alert.setMessage(R.string.msg_conn_not_available)
        }
        alert.setPositiveButton(R.string.ok) { dialog, id ->
            if (error.hasResolution()) {
                error.resolve(activity.get())
            }
        }
        if (error.hasResolution()) {
            alert.setNegativeButton(R.string.cancel, null)
        }
        alert.show()
    }

    fun onDestroy() {
        mStore?.disconnectService()
    }

    fun getStepCount(startTime: Long, endTime: Long) {
        mReporter?.start(
            /*     mStepCountObserver,
                 mDistanceObserver,
                 mCalorieObserver,*/
            stepCountCallback,
            startTime,
            endTime
        )
    }
}
