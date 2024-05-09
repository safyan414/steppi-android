package com.steppi.steppifitness.ui.device_connection

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import com.steppi.steppifitness.BuildConfig
import com.steppi.steppifitness.R
import com.steppi.steppifitness.adapter.device.STDeviceAdapter
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.network.STAPIConstants
import com.steppi.steppifitness.network.response.devices.STFitnessDeviceResponse
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelActivity
import com.steppi.steppifitness.ui.common.STWebViewActivity
import com.steppi.steppifitness.ui.device_connection.mvi.STDeviceController
import com.steppi.steppifitness.ui.device_connection.mvi.STDeviceIntent
import com.steppi.steppifitness.ui.device_connection.mvi.STDeviceState
import com.steppi.steppifitness.ui.home.STHomeActivity
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.activity_choose_devices.*
import kotlinx.android.synthetic.main.header_layout.*
import java.util.*

class STChooseDeviceActivity :
    STBaseViewModelActivity<STDeviceIntent, STDeviceState,
            STDeviceController>(STDeviceController::class.java) {
    private var deviceSelected: STDeviceData? = null
    private var ifFromMyProfile: Boolean? = false
    private var deviceAdapter: STDeviceAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_choose_devices
    }

    override fun initViews() {
        getIntentData()
        setHeaderName(activityContext.resources.getString(R.string.choose_device))
        mainContainer.invisible()
        headerContainer.invisible()
        ViewCompat.setNestedScrollingEnabled(deviceList, false)
        deviceList.layoutManager = LinearLayoutManager(activityContext)
        deviceList.addItemDecoration(
            STVerticalSpaceItemDecoration(
                STUtils.getDimen(
                    activityContext,
                    R.dimen.margin_medium
                )
            )
        )
    }

    private fun previouslySelectedDeviceDialog() {
        val deviceName = STPreference.getFitnessDeviceName(activityContext)
        if (!deviceName.isNullOrEmpty()) {
            showOkayCancelDialog {
                setMessage(
                    activityContext.getString(R.string.you_were_connected_with)
                        .replace("####", deviceName)
                )
                setOkayText(activityContext.resources.getString(R.string.ok))
                setCancelText(activityContext.resources.getString(R.string.cancel))
                isCancelVisible(false)
                okayClickListener {
                    dismissDialog()
                }
                cancelClickListener { dismissDialog() }
            }.show()
        }
    }

    private fun getIntentData() {
        if (intent.hasExtra(STConstants.FROM_MY_PROFILE)) {
            ifFromMyProfile = intent.getBooleanExtra(STConstants.FROM_MY_PROFILE, false)
            if (ifFromMyProfile!!) {
                skip.gone()
                header_back.visible()
            }
        }
        if (intent.hasExtra(STConstants.CHOOSE_DEVICE_CONNECTION_DATA)) {
            intent.getSerializableExtra(STConstants.CHOOSE_DEVICE_CONNECTION_DATA)?.let {

                // Once the Huawei Health Device is added in the backend api device list, this logic can be streamlined
                if ((it as STDeviceData).id == "1" && STPreference.getFitnessDevice(activityContext) == "HUAWEI_HEALTH") {
                    // Identify it as Huawei Health Device
                    it.apply {
                        name = STConstants.HUAWEI_DEVICE_NAME
                        deviceCode = STConstants.HUAWEI_DEVICE_CODE
                    }
                    deviceSelected = it
                } else {
                    deviceSelected = it
                }

                //skip.visibility = View.INVISIBLE
                //ifFromMyProfile = true
            }
        } else {
            skip.visible()
            header_back.gone()
        }
    }

    override fun processState(state: STDeviceState) {
        when (state) {
            is STDeviceState.Loading -> {
                requestDidStart()
            }
            is STDeviceState.Error -> {
                requestDidFinish()
                manageError(state.errorData?.statusCode)
            }
            is STDeviceState.GetFitnessDevices -> {
                requestDidFinish()
                setFitnessDevices(state.deviceResponse)
            }
        }
    }

    override fun onViewModelReady() {
        invokeIntent(STDeviceIntent.GetFitnessDevices(STAPIConstants.IS_INCLUDE_GARMIN))
    }

    private fun setFitnessDevices(deviceResponse: STFitnessDeviceResponse) {
        var deviceResponseData = deviceResponse.data as ArrayList<STDeviceData>

        // Add the Huawei Health Device in the existing device list
        // Once the Huawei Health Device is added in the backend api device list, this logic can be streamlined
//        deviceResponseData.add(composeHuaweiHealthDeviceItem())
//        Collections.swap(deviceResponseData, 1, 3)

        // Hide the Google Fit Device If device does not support Google Mobile Services (GMS)
        if (!STUtils.isGMSAvailable(activityContext)) {
            deviceResponseData.removeAt(0)
        }

        if (STAPIConstants.SUCCESS_CODE == deviceResponse.statusCode) {
            deviceAdapter = STDeviceAdapter(
                activityContext,
                deviceResponse.data as ArrayList<STDeviceData>,
                deviceSelected, listItemOnClick = { device ->
                    STPreference.saveFitnessDeviceID(activityContext, device.id)
                    STPreference.saveFitnessDeviceName(activityContext, device.name)
                    deviceSelected = device
                    if (deviceSelected != null) {
                        if (deviceSelected?.deviceCode != STPreference.getFitnessDevice(activityContext)) {
                            val intent =
                                Intent(activityContext, STDeviceConnectionActivity::class.java)
                            intent.putExtra(STConstants.FROM_MY_PROFILE, ifFromMyProfile!!)
                            intent.putExtra(STConstants.DEVICE_SELECTED, deviceSelected!!)
                            startActivityForResult(
                                intent,
                                STConstants.REQUEST_CODE_DEVICE_CONNECTION_SUCCESS
                            )
                        }
                    } else {
                        showToast(getString(R.string.validation_select_device))
                    }
                },
                infoClick = { device ->
                    showDeviceInfoDialog(device)
                }
            )
            deviceList.adapter = deviceAdapter
            animateLayout(headerContainer, mainContainer)
            previouslySelectedDeviceDialog()
        }
    }

    private fun composeHuaweiHealthDeviceItem(): STDeviceData {

        // Manually add the Huawei Health Device in the List. Later can be added in the device list in backend Api
        return STDeviceData(
            STConstants.HUAWEI_DEVICE_ID,
            STConstants.HUAWEI_DEVICE_NAME,
            STConstants.HUAWEI_DEVICE_CODE
        )
    }

    private fun showDeviceInfoDialog(device: STDeviceData) {
        val deviceInfoDialog: Dialog = deviceInfoDialog {
            setData(device) {}
            closeClick {
                dismissDialog()
                finish()
            }
            confirmClick {
                dismissDialog()
                try {
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        deviceInfoDialog.setSize(activityContext)
        deviceInfoDialog.setCancelable(false)
        deviceInfoDialog.show()
    }

    @OnClick(R.id.skip)
    fun skip() {
        startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
    }

    @OnClick(R.id.learnMore)
    fun learnMore() {
        val learnMoreIntent = Intent(activityContext, STWebViewActivity::class.java)
        learnMoreIntent.putExtra(
            STConstants.WEB_URL,
            BuildConfig.API_URL + STConstants.LEARN_MORE_URL
        )
        learnMoreIntent.putExtra(
            STConstants.WEB_HEADER,
            activityContext.resources.getString(R.string.learn_more)
        )
        startActivity(learnMoreIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            if (STConstants.REQUEST_CODE_DEVICE_CONNECTION_SUCCESS == requestCode) {
                intent.putExtra(
                    STConstants.CHOOSE_DEVICE_CONNECTION_DATA,
                    deviceSelected as STDeviceData
                )
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (ifFromMyProfile!!) super.onBackPressed()
        else startActivityFinishAll(Intent(activityContext, STHomeActivity::class.java))
    }
}
