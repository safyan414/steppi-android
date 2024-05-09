package com.steppi.steppifitness.ui.rewards.mvi

import com.steppi.steppifitness.network.request.reward.STRedeemRewardRequest
import com.steppi.steppifitness.network.response.category.STMerchantStoresListData
import com.steppi.steppifitness.ui.base.mvi.MviIntent

sealed class STRewardsIntent : MviIntent {
    data class GetMerchantList(
        val categoryId: String,
        val latitude: String,
        val longitude: String,
        val offset: Int = 0
    ) : STRewardsIntent()

    data class GetSearchMerchantList(
        val categoryId: String,
        val latitude: String,
        val longitude: String,
        /*val offset: Int = 0,*/
        val query: String = ""
    ) : STRewardsIntent()

    data class GetStoresRewardList(
        val merchantId: String,
        val storeId: String?,
        val offset: Int = 0
    ) : STRewardsIntent()

    data class GetMerchantsStoresList(
        val merchantId: String,
        val latitude: String,
        val longitude: String,
        val rewardId: String = "",
        val query: String = ""
    ) : STRewardsIntent()

    data class GetInStoreRewardDetails(val rewardId: String, val storeId: String) :
        STRewardsIntent()

    data class GetDigitalRewardDetails(val rewardId: String) : STRewardsIntent()
    data class MerchantAddRemoveFavorite(val merchantId: String) : STRewardsIntent()

    data class RedeemReward(val rewardId: String, val redeemRewardRequest: STRedeemRewardRequest) :
        STRewardsIntent()

    data class SearchStoreLocationIntent(
        val searchKey: String,
        val countryList: List<STMerchantStoresListData>?
    ) :
        STRewardsIntent()
}
