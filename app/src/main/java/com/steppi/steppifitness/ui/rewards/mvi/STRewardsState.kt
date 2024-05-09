package com.steppi.steppifitness.ui.rewards.mvi

import com.steppi.steppifitness.model.STErrorData
import com.steppi.steppifitness.network.response.category.*
import com.steppi.steppifitness.ui.base.mvi.MviViewState

sealed class STRewardsState : MviViewState {
    object Loading : STRewardsState()
    data class Error(val errorData: STErrorData?) : STRewardsState()
    data class GetMerchantList(val merchantList: STMerchantListResponse) : STRewardsState()
    data class GetStoresRewardList(val storesRewardsList: STStoresRewardsListResponse) : STRewardsState()
    data class GetMerchantStoresList(val merchantStoresList: STMerchantStoresListResponse) : STRewardsState()

    data class GetInStoreRewardDetails(val rewardDetails: STRewardDetailsResponse) : STRewardsState()
    data class GetDigitalRewardDetails(val rewardDetails: STRewardDetailsResponse) : STRewardsState()

    data class RedeemReward(val redeemRewardResponse: STRedeemRewardResponse) : STRewardsState()
    data class MerchantAddRemoveFavorite(val merchantAddRemoveResponse: STMerchantAddRemoveResponse) : STRewardsState()

    data class SearchStoreLocationState(val storeList: List<STMerchantStoresListData>?) : STRewardsState()
}
