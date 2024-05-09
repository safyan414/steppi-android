package com.steppi.steppifitness.network

import com.steppi.steppifitness.model.STUser
import com.steppi.steppifitness.network.request.STBaseRequest
import com.steppi.steppifitness.network.request.STForgotPasswordRequest
import com.steppi.steppifitness.network.request.STToughMudderChallengeRequest
import com.steppi.steppifitness.network.request.biometric.STBiometricEnableResponse
import com.steppi.steppifitness.network.request.corporate.STAddEditTeamDiscussionMessageRequest
import com.steppi.steppifitness.network.request.corporate.STJoinCorporateUserRequest
import com.steppi.steppifitness.network.request.home.STSyncFitnessDataRequest
import com.steppi.steppifitness.network.request.home.STWhatsNewVersionUpdateRequest
import com.steppi.steppifitness.network.request.login.STLoginRequest
import com.steppi.steppifitness.network.request.login.STRegisterRequest
import com.steppi.steppifitness.network.request.login.STSocialAccountLoginRequest
import com.steppi.steppifitness.network.request.otp.STValidateOtpRequest
import com.steppi.steppifitness.network.request.password.STChangePasswordRequest
import com.steppi.steppifitness.network.request.reward.STRedeemRewardRequest
import com.steppi.steppifitness.network.response.STBaseResponse
import com.steppi.steppifitness.network.response.category.*
import com.steppi.steppifitness.network.response.challenges.*
import com.steppi.steppifitness.network.response.challenges.data.STLeaderboardResponse
import com.steppi.steppifitness.network.response.corporate.STCorporateUserDataResponse
import com.steppi.steppifitness.network.response.country.STCountryResponse
import com.steppi.steppifitness.network.response.devices.STFitnessDeviceResponse
import com.steppi.steppifitness.network.response.devices.STSelectedFitnessDeviceResponse
import com.steppi.steppifitness.network.response.home.STFeaturedRewardsResponse
import com.steppi.steppifitness.network.response.home.STSyncFitnessDataResponse
import com.steppi.steppifitness.network.response.home.STWhatsNewLatestOnBoardingScreensResponse
import com.steppi.steppifitness.network.response.login.STSocialRegisterResponse
import com.steppi.steppifitness.network.response.notification.STNotificationListResponse
import com.steppi.steppifitness.network.response.notification.STUnreadNotificationCountResponse
import com.steppi.steppifitness.network.response.steps_analytics.STStepsAnalyticsResponse
import com.steppi.steppifitness.network.response.user.*
import com.steppi.steppifitness.network.response.version.STVersionCheckResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface STAPIServices {
    @GET(STAPIConstants.GET_COUNTRIES)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun countries(): Observable<STCountryResponse>

    @GET(STAPIConstants.GET_FITNESS_DEVICES)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getFitnessDevices(
        @Query("deviceType") deviceType: String,
        @Header("includeGarmin") includeGarmin: Boolean
    ): Observable<STFitnessDeviceResponse>

    @POST(STAPIConstants.LOGIN)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun login(@Body loginRequest: STLoginRequest): Observable<STUserDataResponse>

    @POST(STAPIConstants.FB_ACCOUNT_LOGIN)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun fbLogin(@Body socialAccountLoginRequest: STSocialAccountLoginRequest): Observable<STSocialRegisterResponse>

    @POST(STAPIConstants.INSTA_ACCOUNT_LOGIN)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun instaLogin(@Body socialAccountLoginRequest: STSocialAccountLoginRequest): Observable<STSocialRegisterResponse>

    /*@Multipart
    @POST(STAPIConstants.REGISTER)
    fun register(@Part file: MultipartBody.Part?, @PartMap map: HashMap<String, RequestBody>): Observable<STUserDataResponse>*/

    @POST(STAPIConstants.REGISTER)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun register(@Body registerRequest: STRegisterRequest): Observable<STUserDataResponse>

    @GET(STAPIConstants.FITNESS_DEVICE_SELECTION)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun addFitnessDevice(
        @Path("deviceId") deviceId: String?,
        @Header("accountId") accountId: String?,
        @Header("access_token") access_token: String?,
        @Header("access_token_secret") access_token_secret: String?
    ): Observable<STSelectedFitnessDeviceResponse>

    @POST(STAPIConstants.FORGOT_PASSWORD)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun forgotPassword(@Body forgotPasswordRequest: STForgotPasswordRequest): Observable<STBaseResponse>

    /*@POST(STAPIConstants.VALIDATE_OTP)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun validateOtp(@Body validateOtpRequest: STValidateOtpRequest): Observable<STUserDataResponse>*/

    @Multipart
    @POST(STAPIConstants.VALIDATE_OTP)
    fun validateOtp(
        @Part file: MultipartBody.Part?,
        @PartMap map: HashMap<String, RequestBody>
    ): Observable<STUserDataResponse>

    @POST(STAPIConstants.RESEND_OTP)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun resendOtp(@Body resendOtpRequest: STBaseRequest): Observable<STResendOtpResponse>

    @GET(STAPIConstants.GET_PROFILE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getProfile(): Observable<STUserDataResponse>

    @PUT(STAPIConstants.UPDATE_PROFILE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun updateProfile(
        @Path("id") id: String?, @Body updateProfileRequest: STUser
    ): Observable<STUserDataResponse>

    @Multipart
    @POST(STAPIConstants.UPDATE_PROFILE_PIC)
    fun updateProfilePic(@Part file: MultipartBody.Part?): Observable<STProfilePicResponse>

    @GET(STAPIConstants.GET_REFRESH_TOKEN)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun refereshToken(): Observable<STBaseResponse>

    @POST(STAPIConstants.UPDATE_MOBILE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun updateMobile(@Body updateMobileRequest: STUser): Observable<STUpdateMobileResponse>

    @POST(STAPIConstants.VERIFY_PHONE_NUMBER_CHANGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun verifyPhoneNumberChange(@Body verifyMobileRequest: STValidateOtpRequest): Observable<STBaseResponse>

    @POST(STAPIConstants.PHONE_NUMBER_CHANGE_RESEND_OTP)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun phoneNumberChangeResendOtp(@Body verifyMobileRequest: STValidateOtpRequest): Observable<STResendOtpResponse>

    @POST(STAPIConstants.CHANGE_PASSWORD)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun changePassword(@Body changePasswordRequest: STChangePasswordRequest): Observable<STBaseResponse>

    // corporate
    @GET(STAPIConstants.GET_CORPORATE_USER)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getCorporateUser(): Observable<STCorporateUserDataResponse>

    @DELETE(STAPIConstants.LEAVE_CORPORATION)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun leaveCorporation(): Observable<STBaseResponse>

    @POST(STAPIConstants.JOIN_CORPORATION)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinCorporation(@Body leaveCorporationRequest: STJoinCorporateUserRequest): Observable<STCorporateUserDataResponse>

    @GET(STAPIConstants.RESEND_CORPORATE_EMAIL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun resendCorporateEmail(): Observable<STBaseResponse>

    // get fitbit data from server
    @GET
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getFitbitData(
        @Url url: String,
        @Header("Authorization") authorization: String
    ): Single<ResponseBody>

    @POST
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun revokeFitbitToken(
        @Url url: String, @Header("Authorization") authorization: String,
        @Header("token") revokeToken: String
    ): Single<ResponseBody>

    @GET(STAPIConstants.GET_CATEGORY)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getCategory(): Observable<STCategoryResponse>

    @GET(STAPIConstants.GET_MERCHANTS_LIST)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getMerchantsList(
        @Path("categoryId") deviceId: String?,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("offset") offset: String = "0"
    ): Observable<STMerchantListResponse>

    @GET(STAPIConstants.SEARCH)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getSearchMerchantsList(
        /*@Path("categoryId") deviceId: String?,*/
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("offset") offset: String = "0",
        @Query("query") query: String = ""
    ): Call<STMerchantListResponse>

    @GET(STAPIConstants.MERCHANTS_STORE_LISTING)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getMerchantStoreList(
        @Path("merchantId") merchantId: String?,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("query") query: String = ""
    ): Observable<STMerchantStoresListResponse>

    @GET(STAPIConstants.MERCHANTS_STORE_LISTING_FOR_REWARD)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getMerchantStoreListForReward(
        @Path("merchantId") merchantId: String?,
        @Path("rewardId") rewardId: String?,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("query") query: String = ""
    ): Observable<STMerchantStoresListResponse>

    @GET(STAPIConstants.MERCHANT_ADD_REMOVE_FAVORITE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun merchantAddRemoveFavorite(
        @Path("merchantId") merchantId: String?
    ): Observable<STMerchantAddRemoveResponse>

    @GET(STAPIConstants.STORES_REWARD_LISTING)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getStoresRewardList(
        @Path("merchantId") merchantId: String?,
        @Path("storeId") storeId: String?,
        @Query("offset") offset: String = "0"
    ): Observable<STStoresRewardsListResponse>

    @GET(STAPIConstants.DIGITAL_REWARD_LISTING)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getDigitalRewardList(
        @Path("merchantId") merchantId: String?,
        @Query("offset") offset: String = "0"
    ): Observable<STStoresRewardsListResponse>

    @GET(STAPIConstants.IN_STORE_REWARD_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getInStoreRewardDetails(
        @Path("rewardId") rewardId: String?,
        @Path("storeId") storeId: String?
    ): Observable<STRewardDetailsResponse>

    @GET(STAPIConstants.DIGITAL_REWARD_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getDigitalRewardDetails(@Path("rewardId") rewardId: String?): Observable<STRewardDetailsResponse>

    @POST(STAPIConstants.REDEEM_REWARD)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun redeemReward(
        @Path("rewardId") rewardId: String?,
        @Body redeemRewardRequest: STRedeemRewardRequest
    ): Observable<STRedeemRewardResponse>

    @GET(STAPIConstants.REDEEMED_REWARDS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getRedeemedRewards(
        @Path("rewardType") rewardType: String?,
        @Query("offset") offset: String = "0"
    ): Observable<STRedeemedRewardsResponse>

    @GET(STAPIConstants.HOME_SCREEN_FEATURED_REWARDS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getHomeScreenFeaturedRewards(): Observable<STFeaturedRewardsResponse>

    @POST(STAPIConstants.SYNC_FITNESS_DATA)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun syncFitnessData(
        @Body syncFitnessDataRequest: STSyncFitnessDataRequest
    ): Observable<STSyncFitnessDataResponse>

    @GET(STAPIConstants.STEPPI_CHALLENGE_LISTS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getSteppiChallengeList(
        @Path("listType") type: String,
        @Query("offset") offset: String = "0"
    ): Observable<STSteppiChallengeListResponse>

    @GET(STAPIConstants.MY_CHALLENGES)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getMyChallenges(@Query("offset") offset: String = "0"): Observable<STSteppiChallengeListResponse>

    @GET(STAPIConstants.CHALLENGE_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getChallengeDetails(
        @Path("challengeId") challengeId: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.CORPORATE_CHALLENGE_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getCorporateChallengeDetails(
        @Path("challengeId") challengeId: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.CHALLENGE_TEAM_MEMBERS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getChallengeTeamMembers(
        @Path("challengeId") challengeId: String?,
        @Path("teamId") teamId: String?,
        @Path("offset") offset: Int?
    ): Observable<STChallengeTeamMemberAndStatsResponse>

    @GET(STAPIConstants.CHALLENGE_SEARCHED_TEAM_MEMBERS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getChallengeSearchedTeamMembers(
        @Path("challengeId") challengeId: String?,
        @Path("teamId") teamId: String?,
        @Path("name") name: String?
    ): Observable<STChallengeTeamMemberResponse>

    @GET(STAPIConstants.JOIN_LEAVE_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinLeaveChallenge(
        @Path("operationType") operationType: String?,
        @Path("challengeId") challengeId: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.GET_NOTIFICATIONS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getNotificationList(
        @Query("offset") offset: String = "0"
    ): Observable<STNotificationListResponse>

    @GET(STAPIConstants.MARK_ALL_READ)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun mallAllRead(): Observable<STBaseResponse>

    @GET(STAPIConstants.GET_UNREAD_NOTIFICATIONS_COUNT)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getUnreadNotificationCount(): Observable<STUnreadNotificationCountResponse>

    @GET(STAPIConstants.GET_TEAM_DISCUSSION_MESSAGES)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getTeamDiscussionMessages(
        @Path("challengeId") challengeId: String?,
        @Path("teamId") teamId: String?,
        @Query("offset") offset: String = "0"
    ): Observable<STTeamDiscussionMessageListResponse>

    @POST(STAPIConstants.ADD_TEAM_DISCUSSION_MESSAGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun addTeamDiscussionMessage(
        @Path("challengeId") challengeId: String?,
        @Path("teamId") teamId: String?,
        @Body addDiscussionMessage: STAddEditTeamDiscussionMessageRequest
    ): Observable<STAddTeamDiscussionMessageResponse>

    @PUT(STAPIConstants.EDIT_TEAM_DISCUSSION_MESSAGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun editTeamDiscussionMessage(
        @Path("challengeId") challengeId: String?,
        @Path("teamId") teamId: String?,
        @Path("messageId") messageId: String?,
        @Body editDiscussionMessage: STAddEditTeamDiscussionMessageRequest
    ): Observable<STAddTeamDiscussionMessageResponse>

    @DELETE(STAPIConstants.USER_LOGOUT)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun userLogout(): Observable<STBaseResponse>

    @GET(STAPIConstants.VERSION_CHECK)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun versionCheck(
        /*@Header("appVersion") appVersion: String?,
        @Header("deviceType") deviceType: String?*/
    ): Observable<STVersionCheckResponse>

    @POST(STAPIConstants.DEVICE_TOKEN_UPDATE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun deviceTokenUpdate(@Body deviceTokenRequest: STBaseRequest): Observable<STBaseResponse>

    @GET(STAPIConstants.GET_ANALYTICS_DATA)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getAnalyticsData(
        @Query("date") date: String
    ): Observable<STStepsAnalyticsResponse>

    @GET(STAPIConstants.ENABLE_FINGERPRINT_LOGIN)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getUserEnableFingerprint(): Observable<STBiometricEnableResponse>

    @GET(STAPIConstants.CHALLENGE_LEADERBOARD)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getChallengeLeaderboard(
        @Path("challengeId") challengeId: String?,
        @Query("offset") offset: String = "0"
    ): Observable<STLeaderboardResponse>

    @GET(STAPIConstants.CHEER_CHALLENGE_USER)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun cheerChallengeUser(
        @Path("challengeId") challengeId: String?,
        @Path("challengeUserId") challengeUserId: String?
    ): Observable<STBaseResponse>

    @GET(STAPIConstants.GET_PRIVATE_CHALLENGE_TEMPLATES_LIST)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getPrivateChallengeTemplatesList(
        @Query("offset") offset: String = "0"
    ): Observable<STPrivateChallengeResponse>

    @GET(STAPIConstants.FIND_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun findChallenge(
        @Path("joinCode") joinCode: String
    ): Observable<STChallengeDetailsResponse>

    @Multipart
    @POST(STAPIConstants.CREATE_PRIVATE_CHALLENGE)
    fun createPrivateChallenge(
        @Path("templateId") templateId: String,
        @Part("currentDate") currentDate: RequestBody,
        @Part("theme") theme: RequestBody
    ): Observable<STChallengeDetailsResponse?>

    @GET(STAPIConstants.PRIVATE_CHALLENGE_JOIN)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinLeavePrivateChallenge(
        @Path("operationType") operationType: String?,
        @Path("challengeId") challengeId: String?,
        @Path("joinCode") joinCode: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.MY_CHALLENGES_BY_TYPE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getMyChallengesByType(
        @Path("listType") listType: String,
        @Query("offset") offset: String = "0"
    ): Observable<STSteppiChallengeListResponse>

    @GET(STAPIConstants.PRIVATE_CHALLENGE_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getPrivateChallengeDetails(
        @Path("challengeId") challengeId: String?,
        @Path("joinCode") joinCode: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.GET_CHALLENGE_TEAMS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getChallengeTeams(
        @Path("challengeId") challengeId: String?,
        @Query("offset") offset: String = "0",
        @Query("query") query: String
    ): Call<STChallengeTeamResponse>

    @GET(STAPIConstants.JOIN_TEAM_PUBLIC_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinTeamPublicChallenge(
        @Path("challengeId") challengeId: String,
        @Path("challengeTeamId") challengeTeamId: String,
        @Path("publicJoinCode") publicJoinCode: String
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.JOIN_PUBLIC_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinPublicChallenge(
        @Header("token") token: String,
        @Path("challengeId") challengeId: String,
        @Path("publicJoinCode") publicJoinCode: String
    ): Call<STChallengeDetailsResponse>

    @GET(STAPIConstants.GET_DAILY_STEP)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getDailyStepsOfUser(
        @Path("challengeId") challengeId: String/*, @Query("offset") offset: String = "0"*/
    ): Observable<STDailyStepsResponse>

    @GET(STAPIConstants.GET_TEAM_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getTeamDetails(
        @Path("teamId") teamId: String
    ): Observable<STTeamDetailsResponse>

    @GET(STAPIConstants.GET_TEAM_LEADER_BOARD_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getTeamLeaderBoardDetails(
        @Path("challengeId") challengeId: String,
        @Path("teamId") teamId: String, @Query("offset") offset: String = "0"
    ): Observable<STTeamLeaderBoardDetailsResponse>

    @GET(STAPIConstants.GET_TEAM_LEADER_BOARD_ANIMATION)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getTeamLeaderBoardAnimation(
        @Path("challengeId") challengeId: String, @Query("offset") offset: String = "0"
    ): Observable<STTeamLeaderBoardAnimationResponse>

    @DELETE(STAPIConstants.REMOVE_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun removeCompletedChallenge(
        @Path("challengeId") challengeId: String
    ): Observable<STRemoveChallengeResponse>

    @GET(STAPIConstants.MARK_HOME_NOTIFICATION_READ)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun mallHomeNotificationRead(): Observable<STBaseResponse>

    @GET(STAPIConstants.GET_WHATS_NEW_ON_BOARDING_SCREENS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun whatsNewOnBoardingScreen(@Query("version") version: String = "0"): Observable<STWhatsNewLatestOnBoardingScreensResponse>

    @POST(STAPIConstants.GET_WHATS_NEW_LAST_VERSION_UPDATE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun whatsNewVersionUpdate(
        @Body whatsNewVersionUpdateRequest: STWhatsNewVersionUpdateRequest
    ): Observable<STBaseResponse>

    @GET(STAPIConstants.TOUGH_MUDDER_CHALLENGE_LIST)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun toughMudderChallengeList(): Observable<STSteppiChallengeListResponse>

    @GET(STAPIConstants.JOIN_TOUGH_MUDDER_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinToughMudderChallenge(
        @Path("operationType") operationType: String?,
        @Path("id") id: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.TOUGH_MUDDER_CHALLENGE_DETAILS)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getToughMudderChallengeDetails(
        @Path("id") id: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.START_TOUGH_MUDDER_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun startToughMudderChallenge(
        @Path("id") id: String?
    ): Observable<STChallengeDetailsResponse>

    @POST(STAPIConstants.COMPLETE_TOUGH_MUDDER_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun completeToughMudderChallenge(
        @Body toughMudderChallengeRequest: STToughMudderChallengeRequest
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.JOIN_LEAVE_DFC_CHALLENGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun joinLeaveDfcChallenge(
        @Path("operationType") operationType: String?,
        @Path("challengeId") challengeId: String?,
        @Path("joinCode") joinCode: String?
    ): Observable<STChallengeDetailsResponse>

    @GET(STAPIConstants.CHANGE_DEFAULT_LANGUAGE)
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun changeDefaultLanguage(): Observable<STChangeDefaultLanguageResponse>
}
