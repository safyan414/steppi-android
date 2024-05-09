package com.steppi.steppifitness.ui.profile.settings.mvi

import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STSettingIntent : MviIntent {
    object MarkAllRead : STSettingIntent()
    object EnableFingerprint : STSettingIntent()
    data class GetNotificationList(val offset: Int = 0) : STSettingIntent()
    object ChangeDefaultLanguage : STSettingIntent()
}
