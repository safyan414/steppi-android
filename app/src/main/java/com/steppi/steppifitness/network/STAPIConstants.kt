package com.steppi.steppifitness.network

interface STAPIConstants {
    companion object {
        const val IS_INCLUDE_GARMIN = true
        const val SUCCESS_CODE = 200

        const val USER_NOT_FOUND = 2000
        const val USER_NOT_VERIFIED = 3000

        const val STATUS_CODE_SESSION = 401
        const val STATUS_CODE_TOKEN_EXPIRED = 4010
        const val STATUS_CODE_SESSION_EXPIRED = 4011
        const val STATUS_CODE_INVALID_SESSION = 4012
        const val STATUS_CODE_MALFORMED_REQUEST = 4013
        const val STATUS_CODE_USER_UNAVIALABLE = 4014

        const val API_VERSION = "/api/v1/"
        const val GET_COUNTRIES = "public/list-countries"
        const val GET_FITNESS_DEVICES = "public/list-fitness-devices"
        const val LOGIN = "auth/login"
        const val FB_ACCOUNT_LOGIN = "auth/social-fb-account"
        const val INSTA_ACCOUNT_LOGIN = "auth/social-in-account"
        const val REGISTER = "auth/register"
        const val FORGOT_PASSWORD = "auth/forgot-password"
        const val VALIDATE_OTP = "auth/verify-account"
        const val RESEND_OTP = "auth/resend-otp"
        const val GET_PROFILE = "user"
        const val UPDATE_PROFILE = "user/{id}"
        const val UPDATE_PROFILE_PIC = "user/update/profile-pic"
        const val GET_REFRESH_TOKEN = "auth/refresh-access-token"
        const val UPDATE_MOBILE = "user/change-phone-number"
        const val VERIFY_PHONE_NUMBER_CHANGE = "user/verify-phone-number-change"
        const val PHONE_NUMBER_CHANGE_RESEND_OTP = "user/phone-number-change-resend-otp"
        const val CHANGE_PASSWORD = "user/change-password"
        const val FITNESS_DEVICE_SELECTION = "user/fitness-device-selection/{deviceId}"
        const val USER_LOGOUT = "auth/log-out-user"

        // corporate
        const val LEAVE_CORPORATION = "corporate-user/leave-corporation"
        const val GET_CORPORATE_USER = "corporate-user"
        const val JOIN_CORPORATION = "corporate-user/join-corporation"
        const val RESEND_CORPORATE_EMAIL = "corporate-user/resend-corporate-verification"

        //Category
        const val GET_CATEGORY = "public/list-categories"
        const val GET_MERCHANTS_LIST = "category/list/merchants/{categoryId}"
        const val SEARCH = "category/list/merchants"

        // STORE
        const val MERCHANTS_STORE_LISTING = "merchant/stores/{merchantId}"
        const val MERCHANTS_STORE_LISTING_FOR_REWARD = "merchant/stores/{merchantId}/{rewardId}"
        const val MERCHANT_ADD_REMOVE_FAVORITE = "merchant/add-remove-favorite/{merchantId}"
        const val STORES_REWARD_LISTING = "store/rewards/{merchantId}/{storeId}"
        const val DIGITAL_REWARD_LISTING = "store/rewards/{merchantId}"

        // reward
        const val IN_STORE_REWARD_DETAILS = "reward/{rewardId}/{storeId}"
        const val DIGITAL_REWARD_DETAILS = "reward/{rewardId}"
        const val REDEEM_REWARD = "reward/redeem/{rewardId}"
        const val REDEEMED_REWARDS = "reward/list/redeemed-rewards/{rewardType}"

        // home screen
        const val HOME_SCREEN_FEATURED_REWARDS = "user/home-screen-data"
        const val SYNC_FITNESS_DATA = "health/sync-now"
        const val GET_WHATS_NEW_ON_BOARDING_SCREENS = "user/get-latest-onboarding-screens"
        const val GET_WHATS_NEW_LAST_VERSION_UPDATE = "user/update-last-used-version"

        // Challenges
        const val STEPPI_CHALLENGE_LISTS = "challenge/list/{listType}"
        const val MY_CHALLENGES = "challenge/my-challenges"
//        const val CORPORATE_CHALLENGE_DETAILS = "challenge/details/{challengeId}"                 //todo wrong naming here
//        const val CHALLENGE_DETAILS = "challenge/details-corporate-challenge/{challengeId}"

        const val CHALLENGE_DETAILS = "challenge/details/{challengeId}"
        const val CORPORATE_CHALLENGE_DETAILS = "challenge/details-corporate-challenge/{challengeId}"

        const val CHALLENGE_TEAM_MEMBERS = "challenge/team-members/{challengeId}/{teamId}/{offset}/1"
        const val CHALLENGE_SEARCHED_TEAM_MEMBERS = "challenge/search-team-members/{challengeId}/{teamId}/{name}"
        const val JOIN_LEAVE_CHALLENGE = "challenge/{operationType}/{challengeId}"
        const val CHALLENGE_LEADERBOARD = "challenge/leaderboard/{challengeId}"
        const val CHEER_CHALLENGE_USER = "challenge/cheer/{challengeId}/{challengeUserId}"
        const val GET_PRIVATE_CHALLENGE_TEMPLATES_LIST = "challenge/get-private-challenge-templates"
        const val FIND_CHALLENGE = "challenge/get-private-challenge/{joinCode}"
        const val CREATE_PRIVATE_CHALLENGE = "challenge/create-private-challenge/{templateId}"
        const val MY_CHALLENGES_BY_TYPE = "challenge/my-challenges/{listType}"
        const val PRIVATE_CHALLENGE_JOIN = "challenge/{operationType}/{challengeId}/{joinCode}"
        const val GET_CHALLENGE_TEAMS = "challenge/get-challenge-teams/{challengeId}"

        const val JOIN_PUBLIC_CHALLENGE = "challenge/join/{challengeId}/{publicJoinCode}"                  //public challenge
        const val JOIN_TEAM_PUBLIC_CHALLENGE = "challenge/join/{challengeId}/{challengeTeamId}/{publicJoinCode}"                  //team challenge
        const val GET_DAILY_STEP = "challenge/my-challenge-steps/{challengeId}"
        const val GET_TEAM_DETAILS = "challenge/get-challenge-team-details/{teamId}"
        const val GET_TEAM_LEADER_BOARD_DETAILS = "challenge/leaderboard/{challengeId}/{teamId}"
        const val GET_TEAM_LEADER_BOARD_ANIMATION = "challenge/team-leaderboard/{challengeId}"
        const val GET_PARTICIPANT_LEADER_BOARD_ANIMATION = "challenge/leaderboard/{challengeId}"
        const val REMOVE_CHALLENGE = "challenge/archive-challenge-now/{challengeId}"

        // Notification
        const val GET_NOTIFICATIONS = "user-notification/get-notifications"
        const val GET_UNREAD_NOTIFICATIONS_COUNT = "user-notification/get-unread-notification-count"
        const val MARK_ALL_READ = "user-notification/mark-all-read"
        const val MARK_HOME_NOTIFICATION_READ = "user-notification/mark-home-notification-read"

        // team detail
        const val GET_TEAM_DISCUSSION_MESSAGES =
            "challenge/get-team-discussion-messages/{challengeId}/{teamId}"
        const val ADD_TEAM_DISCUSSION_MESSAGE =
            "challenge/add-team-discussion-messages/{challengeId}/{teamId}"
        const val EDIT_TEAM_DISCUSSION_MESSAGE =
            "challenge/update-team-discussion-messages/{challengeId}/{teamId}/{messageId}"

        const val VERSION_CHECK = "auth/check-app-update-requirement"
        const val DEVICE_TOKEN_UPDATE = "user/device-token-update"
        const val GET_ANALYTICS_DATA = "health/get-analytics-data"

        //Finger Print
        const val ENABLE_FINGERPRINT_LOGIN = "user/enable-fingerprint"
        const val PRIVATE_CHALLENGE_DETAILS = "challenge/details/{challengeId}/{joinCode}"
        const val TOUGH_MUDDER_CHALLENGE_LIST = "tough-mudder-challenge/upcoming/list"
        const val JOIN_TOUGH_MUDDER_CHALLENGE = "tough-mudder-challenge/{operationType}/{id}"
        const val TOUGH_MUDDER_CHALLENGE_DETAILS = "tough-mudder-challenge/details/{id}"
        const val START_TOUGH_MUDDER_CHALLENGE = "tough-mudder-challenge/start/{id}"
        const val COMPLETE_TOUGH_MUDDER_CHALLENGE = "tough-mudder-challenge/complete/survey"
        const val JOIN_LEAVE_DFC_CHALLENGE = "challenge/{operationType}/{challengeId}/{joinCode}"

        const val CHANGE_DEFAULT_LANGUAGE = "user/change-default-language"
    }
}
