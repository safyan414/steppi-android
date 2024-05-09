package com.steppi.steppifitness.ui.home.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.category.STCategoryResponse
import com.steppi.steppifitness.network.response.category.STMerchantListResponse
import com.steppi.steppifitness.network.response.home.STFeaturedRewardsResponse
import com.steppi.steppifitness.network.response.home.STSyncFitnessDataResponse
import com.steppi.steppifitness.network.response.home.STWhatsNewLatestOnBoardingScreensResponse
import com.steppi.steppifitness.network.response.notification.STUnreadNotificationCountResponse
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STHomeState : MviViewState {
    object Loading : STHomeState()

    data class Error(val errorData: STErrorData?) : STHomeState()
    data class GetCategory(val categoryResponse: STCategoryResponse) : STHomeState()
    data class GetMerchantList(val merchantList: STMerchantListResponse) : STHomeState()
    data class GetHomeScreenFeaturedRewards(val featuredRewardsResponse: STFeaturedRewardsResponse) :
        STHomeState()

    data class SyncFitnessData(val syncFitnessDataResponse: STSyncFitnessDataResponse) :
        STHomeState()

    data class GetUnreadNotificationCount(val unreadNotificationCountResponse: STUnreadNotificationCountResponse) :
        STHomeState()

    data class DeviceTokenUpdateResponse(val deviceTokenResponse: STBaseResponse) : STHomeState()

    data class MarkHomeNotificationRead(val allRead: STBaseResponse?) : STHomeState()

    data class WhatsNewOnBoardingScreen(val whatsNewLatestOnBoardingScreensResponse: STWhatsNewLatestOnBoardingScreensResponse?) :
        STHomeState()

    data class WhatsNewVersionUpdate(val deviceTokenResponse: STBaseResponse) : STHomeState()
}
