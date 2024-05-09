package com.steppi.steppifitness.ui.home.mvi

import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.network.request.home.STSyncFitnessDataRequest
import com.steppi.steppifitness.network.request.home.STWhatsNewVersionUpdateRequest
import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STHomeIntent : MviIntent {
    object GetCategory : STHomeIntent()
    data class GetMerchantList(
        val categoryId: String,
        val latitude: String,
        val longitude: String
    ) : STHomeIntent()

    object GetHomeScreenFeaturedRewards : STHomeIntent()
    data class GetFitnessData(val syncFitnessDataRequest: STSyncFitnessDataRequest) : STHomeIntent()
    object GetUnreadNotificationCount : STHomeIntent()
    data class DeviceTokenUpdate(val deviceTokenUpdate: STBaseRequest) : STHomeIntent()
    object MarkHomeNotificationRead : STHomeIntent()
    data class WhatsNewOnBoardingScreen(val version: String) : STHomeIntent()
    data class WhatsNewVersionUpdate(val whatsNewVersionUpdateRequest: STWhatsNewVersionUpdateRequest) :
        STHomeIntent()
}
