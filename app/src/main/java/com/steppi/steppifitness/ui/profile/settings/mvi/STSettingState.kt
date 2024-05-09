package com.steppi.steppifitness.ui.profile.settings.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.request.biometric.STBiometricEnableResponse
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.notification.STNotificationListResponse
import com.steppi.steppifitness.network.response.user.STChangeDefaultLanguageResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STSettingState : MviViewState {
    object Loading : STSettingState()
    data class Error(val errorData: STErrorData?) : STSettingState()
    data class GetNotificationList(val notificationList: STNotificationListResponse?) :
        STSettingState()

    data class MarkAllRead(val allRead: STBaseResponse?) : STSettingState()
    data class EnableFingerprint(val fingerprintResponse: STBiometricEnableResponse) :
        STSettingState()

    data class ChangeDefaultLanguage(val changeDefaultLanguageResponse: STChangeDefaultLanguageResponse) : STSettingState()
}
