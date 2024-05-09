package com.steppi.steppifitness.ui.device_connection

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalytics
import com.steppi.steppifitness.firebase_analytics.STFireBaseAnalyticsConstants
import com.steppi.steppifitness.fitness_manager.FitnessCallback
import com.steppi.steppifitness.fitness_manager.fitbit.FitBitDataSource
import com.steppi.steppifitness.fitness_manager.garmin.GarminDatasource
import com.steppi.steppifitness.fitness_manager.googlefit.GoogleFitDataSource
import com.steppi.steppifitness.fitness_manager.hihealth.HuaweiHealthDataSource
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.model.STFitnessDataModel
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.device_connection.mvi.STDeviceController
import com.steppi.steppifitness.ui.device_connection.mvi.STDeviceIntent
import com.steppi.steppifitness.ui.device_connection.mvi.STDeviceState
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.choose_device_connection_activity.*
import kotlinx.android.synthetic.main.header_layout.*
import org.xms.g.utils.GlobalEnvSetting
import java.lang.ref.WeakReference
import java.util.*

class STDeviceConnectionActivity :
    STBaseViewModelActivity<STDeviceIntent, STDeviceState,
            STDeviceController>(STDeviceController::class.java), FitnessCallback {
    private var ifFromMyProfile: Boolean? = false
    private var deviceSelected: STDeviceData? = null
    private var selection: String? = null
    private var calenderFrom: Calendar = Calendar.getInstance()
    private var calenderTo: Calendar = Calendar.getInstance()
    private var googleFitDataSource: GoogleFitDataSource? = null
    private var fitBitDataSource: FitBitDataSource? = null
    private var garminDataSource: GarminDatasource? = null
    private var huaweiHealthDataSource: HuaweiHealthDataSource? = null
    private var requestHuaweiHealthAuthorization = false

    private var appPackageName: String? = null
//    private var samsungDataSource: SamsungHealthKitDataSource? = null

    override fun getLayoutId(): Int {
        return R.layout.choose_device_connection_activity
    }

    override fun initViews() {
        getIntentData()
        setViews()
    }

    private fun getIntentData() {
        if (intent.hasExtra(STConstants.FROM_MY_PROFILE)) {
            ifFromMyProfile = intent.getBooleanExtra(STConstants.FROM_MY_PROFILE, false)
            if (ifFromMyProfile!!) {
                skip.gone()
                header_back.visible()
            } else {
                skip.visible()
                header_back.gone()
            }
        }
        intent.getSerializableExtra(STConstants.DEVICE_SELECTED)?.let {
            deviceSelected = it as STDeviceData
            deviceSelected!!.name?.let { selectedDeviceName -> setHeaderName(selectedDeviceName) }
        }
        skip.visible()
    }

    private fun setViews() {
        deviceSelected?.let { deviceSelected ->
            when (deviceSelected.deviceCode) {
                STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                    deviceConnectionIcon.setImageResource(R.drawable.google_fit_logo)
                    deviceConnectionBg.setImageResource(R.drawable.fitness_selection_google_fit)
                    whatIs.text = resources.getString(R.string.what_is_google_fit)
                    what_is_description.text = resources.getString(R.string.google_fit_description)
                    installDescription.text =
                        resources.getString(R.string.google_fit_install_description)
                    connectDescription.text =
                        resources.getString(R.string.google_fit_connect_description)
                    needToInstall.text = getString(R.string.install_google_fit)
                }
                STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> {
                    deviceConnectionIcon.setImageResource(R.drawable.huawei_health_logo)
                    deviceConnectionBg.setImageResource(R.drawable.fitness_selection_huawei)
                    whatIs.text = resources.getString(R.string.what_is_huawei_health)
                    what_is_description.text =
                        resources.getString(R.string.huawei_health_description)
                    installDescription.text =
                        resources.getString(R.string.huawei_health_install_description)
                    connectDescription.text =
                        resources.getString(R.string.huawei_health_connect_description)
                    needToInstall.text = getString(R.string.install_huawei_health)
                }
                STUtils.EnumFitnessDevice.FITBIT.name -> {
                    deviceConnectionIcon.setImageResource(R.drawable.fitbit_logo)
                    deviceConnectionBg.setImageResource(R.drawable.fitness_selection_fitbit)
                    whatIs.text = resources.getString(R.string.what_is_fitbit)
                    what_is_description.text = resources.getString(R.string.fit_bit_description)
                    installDescription.text =
                        resources.getString(R.string.fit_bit_install_description)
                    connectDescription.text =
                        resources.getString(R.string.fit_bit_connect_description)
                    needToInstall.text = getString(R.string.install_fitbit)
                }
                STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> {
                    deviceConnectionIcon.setImageResource(R.drawable.fitbit_logo)
                    deviceConnectionBg.gone()
                    garminConnectionLayout.visible()
                    deviceConnectionBg.setImageResource(R.drawable.fitness_selection_fitbit)
                    whatIs.text = resources.getString(R.string.what_is_garmin)
                    what_is_description.text = resources.getString(R.string.garmin_description)
                    installDescription.text =
                        resources.getString(R.string.garmin_install_description)
                    connectDescription.text =
                        resources.getString(R.string.garmin_connect_description)
                    needToInstall.text = getString(R.string.install_garmin)
                }
            }
            STUtils.setImageSize(
                activityContext, deviceConnectionBg,
                STUtils.convertDpToPixel(activityContext, 5F).toInt()
            )
        }
    }

    @OnClick(R.id.skip)
    fun skip() {
        startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
    }

    @OnClick(R.id.buttonConnect)
    fun connect() {
        if (deviceSelected != null) {
            logoutFromPreviousFitnessDevice()
            STPreference.saveFitnessDevice(activityContext, deviceSelected?.deviceCode)
            calendarToday()
        } else {
            showToast(getString(R.string.validation_select_device))
        }
    }

    @OnClick(R.id.buttonInstall)
    fun install() {
        redirectToApp()
    }

    override fun onResume() {
        super.onResume()
        if (GlobalEnvSetting.isHms()) {
            huaweiHealthDataSource?.let {
                //when coming back from huawei health and user denied permissions, don't ask again for authorization
                if (requestHuaweiHealthAuthorization) {
                    //if STEPPI has permission [dailySummary] will be invoked to return result to previous activity
                    it.checkForUserPermission()
                    requestHuaweiHealthAuthorization = false
                }

            }
        }
    }

    private fun redirectToApp() {
        if (deviceSelected?.deviceCode == STUtils.EnumFitnessDevice.GOOGLE_FIT.name) {
            appPackageName = STConstants.GOOGLE_FIT_PACKAGE_NAME
        } else if (deviceSelected?.deviceCode == STUtils.EnumFitnessDevice.FITBIT.name) {
            appPackageName = STConstants.FITBIT_PACKAGE_NAME
        } else if (deviceSelected?.deviceCode == STUtils.EnumFitnessDevice.GARMIN_HEALTH.name) {
            appPackageName = STConstants.GARMIN_PACKAGE_NAME
        } else if (deviceSelected?.deviceCode == STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name) {
            appPackageName = STConstants.HUAWEI_HEALTH_PACKAGE_NAME
        }
        appPackageName?.let { appPackageName ->
            try {
                var uri = Uri.parse("market://details?id=$appPackageName")

                // In case of GMS Phone without App Gallery and Huawei Health
                // First it will download the App Gallery and then Huawei Health App
                if (!STUtils.isPackageInstalled(STConstants.HUAWEI_HEALTH_PACKAGE_NAME, packageManager) && deviceSelected?.deviceCode == STConstants.HUAWEI_DEVICE_CODE)
                    uri = Uri.parse("https://appgallery.huawei.com/#/app/C10414141?appId=C10414141&source=appshare&subsource=C10414141")

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        uri
                    )
                )
            } catch (except: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
    }

    private fun logoutFromPreviousFitnessDevice() {
        STPreference.saveFitnessDeviceID(activityContext, deviceSelected?.id)
        STPreference.saveFitnessDeviceName(activityContext, deviceSelected?.name)
        when (STPreference.getFitnessDevice(activityContext)) {
            STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                STUtils.logoutFromGoogleFit(activityContext)
            }
            STUtils.EnumFitnessDevice.FITBIT.name -> {
                STUtils.logoutFromFitBit(activityContext)
            }
            STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> {
                STUtils.logoutFromGarmin(activityContext)
            }
            STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> {
                STUtils.logoutFromHuaweiHealth(activityContext)
            }
            else -> {
                STUtils.logoutFromGoogleFit(activityContext)
                STUtils.logoutFromFitBit(activityContext)
                STUtils.logoutFromGarmin(activityContext)
                STUtils.logoutFromHuaweiHealth(activityContext)
            }
        }
    }

    override fun onViewModelReady() {
    }

    override fun processState(state: STDeviceState) {
        when (state) {
            is STDeviceState.Loading -> {
                requestDidStart()
            }
            is STDeviceState.Error -> {
                requestDidFinish()
                manageError(state.errorData?.statusCode)
                showToast(state.errorData?.message)
//                state.errorData?.statusCode?.let {
//                    if(it == 409) {
//
//                    }
//                }
                when (STPreference.getFitnessDevice(activityContext)) {
                    STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> {
                        STUtils.logoutFromGoogleFit(activityContext)
                        STPreference.saveFitnessDevice(activityContext, null)
                    }
                    STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> {
                        STUtils.logoutFromHuaweiHealth(activityContext)
                        STPreference.saveFitnessDevice(activityContext, null)
                    }
                    STUtils.EnumFitnessDevice.FITBIT.name -> {
                        STUtils.logoutFromGoogleFit(activityContext)
                        STPreference.saveFitnessDevice(activityContext, null)
                    }
                    STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> {
                        STUtils.logoutFromGarmin(activityContext)
                        STPreference.saveFitnessDevice(activityContext, null)
                    }
                    else -> {
                        STUtils.logoutFromGarmin(activityContext)
                        STUtils.logoutFromGoogleFit(activityContext)
                        STUtils.logoutFromFitBit(activityContext)
                        STUtils.logoutFromHuaweiHealth(activityContext)
                        STPreference.saveFitnessDevice(activityContext, null)
                    }
                }
            }
            is STDeviceState.SelectFitnessDevice -> {
                requestDidFinish()
                STPreference.setUserLevel(activityContext, STConstants.ACTIVE_LEVEL_REGISTERED)
//                state.deviceSelectionResponse.data?.let {
//                    STPreference.saveFitnessDevice(activityContext, it.deviceCode)
//                }
                callDeviceSelectionEventCompleted()
                if (ifFromMyProfile!!) {
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
                }
                STPreference.saveFitBitUserId(activityContext, null)
                STPreference.saveGoogleFitUserId(activityContext, null)
                STPreference.saveGarminUserId(activityContext, null)
                STPreference.saveHuaweiHealthUserId(activityContext, null)
            }
        }
    }

    private fun callDeviceSelectionEventCompleted() {
        val eventNameList: HashMap<String, String> = HashMap()
        deviceSelected?.let { selectedDevice ->
            selectedDevice.name?.let { selectedDeviceName ->
                eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_HEALTH_DEVICE_NAME] =
                    selectedDeviceName
            }
            eventNameList[STFireBaseAnalyticsConstants.EVENT_PARAMETER_HEALTH_DEVICE_PLATFORM] =
                STConstants.PLATFORM
        }
        if (!BuildConfig.DEBUG)
            STFireBaseAnalytics.trackEventValue(
                STFireBaseAnalyticsConstants.EVENT_DEVICE_SELECTION,
                eventNameList
            )
    }

    private fun calendarToday() {
        val cal = Calendar.getInstance()
        val endTime = cal.timeInMillis
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 1)
        val startTime = cal.timeInMillis

        calenderFrom.timeInMillis = startTime
        calenderTo.timeInMillis = endTime

        //selection = STPreference.getFitnessDevice(activityContext) selection = deviceSelected?.deviceCode

        selection = STPreference.getFitnessDevice(activityContext)
        initTrackingSdk(startTime, endTime)
    }

    private fun initTrackingSdk(startTime: Long, endTime: Long) {
        when (selection) {
            STUtils.EnumFitnessDevice.GOOGLE_FIT.name -> initGoogleFit(startTime, endTime)
            STUtils.EnumFitnessDevice.HUAWEI_HEALTH.name -> initHuaweiHealth(startTime, endTime)
            STUtils.EnumFitnessDevice.FITBIT.name -> initFitBit(startTime, endTime)
            STUtils.EnumFitnessDevice.GARMIN_HEALTH.name -> initGarmin(startTime, endTime)
//            STUtils.EnumFitnessDevice.SAMSUNG_HEALTH.name -> initSamsung(startTime, endTime)
        }
    }

    private fun initGoogleFit(startTime: Long, endTime: Long) {
        googleFitDataSource = GoogleFitDataSource(
            WeakReference(activityContext), this,
            startTime, endTime
        )
    }

    private fun initFitBit(startTime: Long, endTime: Long) {
        fitBitDataSource = FitBitDataSource(
            WeakReference(activityContext), this,
            startTime, endTime
        )
    }

    private fun initGarmin(startTime: Long, endTime: Long) {
        garminDataSource = GarminDatasource(
            WeakReference(activityContext), this,
            startTime, endTime
        )
    }

    private fun initHuaweiHealth(startTime: Long, endTime: Long) {
        if (STUtils.isPackageInstalled(STConstants.HUAWEI_HEALTH_PACKAGE_NAME, packageManager)) {
            try {
                requestHuaweiHealthAuthorization = true
                huaweiHealthDataSource = HuaweiHealthDataSource(
                    WeakReference(activityContext),
                    this,
                    startTime,
                    endTime
                )
            } catch (e: Exception) {
                showToast(getString(R.string.please_update_huawei_health_app))
                redirectToApp()
            }
        } else {
            showToast(getString(R.string.please_update_huawei_health_app))
            redirectToApp()
        }
    }

//    private fun initSamsung(startTime: Long, endTime: Long) {
//        samsungDataSource = SamsungHealthKitDataSource(
//            WeakReference(activityContext), this,
//            startTime,
//            endTime
//        )
//        samsungDataSource?.init()
//    }

    override fun onSuccess(stepCount: Int, calorieCount: Float, distance: Float) {
    }

    override fun onError(error: String) {
        showToast(error)
        logoutFromPreviousFitnessDevice()
    }

    override fun onStepCountReceived(stepCount: Int, userInput: Int) {
    }

    override fun onCalorieReceived(calorie: Float, userInput: Float) {
    }

    override fun onDistanceReceived(distance: Float, userInput: Float) {
    }

    override fun dailySummary(dataMap: HashMap<String, STFitnessDataModel>) {
        STPreference.getGoogleFitUserId(activityContext)?.let { googleFitUserId ->
            callFitnessSelectionAPIs(
                accountId = googleFitUserId,
                garminAccessToken = null,
                garminTokenSecret = null
            )
        }
        STPreference.getFitBitUserId(activityContext)?.let { fitBitUserId ->
            callFitnessSelectionAPIs(
                accountId = fitBitUserId,
                garminAccessToken = null,
                garminTokenSecret = null
            )
        }
        STPreference.getGarminUserId(activityContext)?.let { garminUserId ->
            STPreference.getGarminToken(activityContext)?.let { garminAccessToken ->
                STPreference.getGarminTokenSecret(activityContext)?.let { garminTokenSecret ->
                    callFitnessSelectionAPIs(
                        accountId = garminUserId,
                        garminAccessToken = garminAccessToken,
                        garminTokenSecret = garminTokenSecret
                    )
                }
            }
        }
        STConstants.IS_SYNC_INITIALLY = true
    }

    private fun callFitnessSelectionAPIs(
        accountId: String?,
        garminAccessToken: String?,
        garminTokenSecret: String?
    ) {
        invokeIntent(
            STDeviceIntent.SelectFitnessDevice(
                deviceSelected?.id,
                accountId,
                garminAccessToken,
                garminTokenSecret
            )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        googleFitDataSource?.onActivityResult(requestCode, resultCode, data)
        fitBitDataSource?.onActivityResult(requestCode, resultCode, data)
        garminDataSource?.onActivityResult(requestCode, resultCode, data)
        huaweiHealthDataSource?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

//    override fun initViews() {
//        setHeaderName(activityContext.resources.getString(R.string.choose_device))
//        animateLayout(headerContainer, mainContainer)
//        getIntentData()
//    }
//
//    private fun getIntentData() {
//        if (intent.hasExtra(STConstants.FROM_MY_PROFILE)) {
//            ifFromMyProfile = intent.getBooleanExtra(STConstants.FROM_MY_PROFILE, false)
//        }
//    }
//
//    @OnClick(R.id.next)
//    fun goToHome() {
//        if (ifFromMyProfile!!) {
//            setResult(Activity.RESULT_OK, intent)
//            finish()
//        } else {
//            startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
//        }
//    }
}
