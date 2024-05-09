package com.steppi.steppifitness.ui.profile.settings

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.core.content.ContextCompat
import butterknife.OnClick
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.mvi.STBaseViewModelFragment
import com.steppi.steppifitness.ui.common.STContainerActivity
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingController
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingIntent
import com.steppi.steppifitness.ui.profile.settings.mvi.STSettingState
import com.steppi.steppifitness.utils.*
import kotlinx.android.synthetic.main.fragment_settings.*

class STSettingsFragment :
    STBaseViewModelFragment<STSettingIntent, STSettingState, STSettingController>(
        STSettingController::class.java
    ) {
    override fun processState(state: STSettingState) {
        when (state) {
            is STSettingState.Loading -> requestDidStart()
            is STSettingState.Error -> requestDidFinish()
            is STSettingState.EnableFingerprint -> {
                requestDidFinish()
                state.fingerprintResponse.data?.let {
                    STPreference.setBioMetricToken(activityContext, it.fingerprint)
                    showToast(getString(R.string.fingerprint_auth_enabled_label))
                    if (STPreference.getLanguageCode(activityContext) == STConstants.ARABIC_CODE) {
                        viewBiometric.setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(activityContext, R.drawable.tick_country),
                            null,
                            null,
                            null
                        )
                    } else {
                        viewBiometric.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            ContextCompat.getDrawable(activityContext, R.drawable.tick_country),
                            null
                        )
                    }
                }
            }
        }
    }

    override fun onViewModelReady() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_settings
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initViews() {
        val biometricManager = BiometricManager.from(activityContext)
        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
            biometricLogin?.visible()
        } else {
            biometricLogin?.gone()
        }
        setBioMetricIcon()
    }

    private fun setBioMetricIcon() {
        STPreference.getBioMetricToken(activityContext)?.let {
            viewBiometric.setRtlControlForDrawables(R.drawable.tick_country)
        } ?: kotlin.run {
            viewBiometric.setRtlControlForDrawables(R.drawable.tick_normal)
        }
    }

    @OnClick(R.id.biometricLogin)
    fun biometricLoginEnable() {
        STPreference.getBioMetricToken(activityContext)?.let {
            viewBiometric.setRtlControlForDrawables(R.drawable.tick_normal)
            STPreference.setBioMetricToken(activityContext, null)
            showToast(getString(R.string.fingerprint_auth_disabled_label))
        } ?: kotlin.run {
            invokeIntent(STSettingIntent.EnableFingerprint)
        }
    }

    @OnClick(R.id.unitConversion)
    fun unitConversion() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.unit_conversion))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_UNIT_CONVERSION)
        startActivity(container)
    }

    @OnClick(R.id.languageLayout)
    fun changeLanguage() {
        val container = Intent(activityContext, STContainerActivity::class.java)
        container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.language))
        container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_CHANGE_LANGUAGE)
        startActivity(container)
    }

    @Override
    override fun onResume() {
        super.onResume()
        if (STConstants.IS_LANGUAGE_CHANGED) {
            activityContext.finish()
//            val container = Intent(activityContext, STContainerActivity::class.java)
//            container.putExtra(STConstants.FRAGMENT_NAME, resources.getString(R.string.settings))
//            container.putExtra(STConstants.FRAGMENT_ID, STConstants.FRAGMENT_ID_SETTINGS)
//            startActivity(container)
//            activityContext.finish()
        }
    }

}
