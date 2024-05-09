package com.steppi.steppifitness.app

object STConstants {
    // Instagram Key details
    val INSTAGRAM_CLIENT_ID = "9af33f221b1f43919a2b647074fd83a7"
    val INSTAGRAM_CLIENT_SECRET = "e055cf8af3ee4909bc8267f2f98a81dd"
    val INSTAGRAM_CALLBACK_URL = "http://www.mobiiworld.com"
    const val TERMS_URL = "/privacy-policy"  //"http://www.mobiiworld.com"
    const val CHALLENGE_TERMS_CONDITION_URL = "/challenge-terms-and-condition"
    const val LEARN_MORE_URL = "/learn-more"
    const val NOT_SHOWING_ACTIVE_MINUTES = "/devices-data-policy"
    const val SHARE_APP_URL = "/share-app-page"
    const val CORPORATE_CHALLENGE_RULES = "/corporate-challenge-rules"
    const val STEPPI_CHALLENGE_RULES = "/steppi-challenge-rules"
    const val PC2_RULES = "/team-challenge-rules"
    const val PC3_RULES = "/daily-goal-challenge-rules"
    const val PRIVATE_CHALLENGE_RULES = "/private-challenge-rules"
    const val TOUGH_MUDDER_CHALLENGE_RULES = "/tough-mudder-challenge-rules"
    const val DFC_CHALLENGE_RULES = "/dubai-fitness-challenge-rules"

    const val ENGLISH_CODE = "en"
    const val ARABIC_CODE = "ar"
    const val DEVICE_TYPE = "android"
    const val PLATFORM = "Android"

    const val ARG_POSITION = "position"
    const val FEATURED_ARG_POSITION = "featured_arg_position"
    const val IS_NOTIFICATION = "IS_NOTIFICATION"
    const val IS_DEEP_LINKING_NOTIFICATION = "IS_DEEP_LINKING_NOTIFICATION"

    const val NOTIFICATION_TYPE = "type"
    const val NOTIFICATION_CHALLENGE_ID = "challengeId"
    const val NOTIFICATION_CHALLENGE_TYPE = "challengeType"
    const val NOTIFICATION_REWARD_ID = "rewardId"
    const val NOTIFICATION_REWARD_TYPE = "rewardType"
    const val SELECTED_PRIVATE_CHALLENGE = "selectedPrivateChallenge"
    const val SELECTED_THEME = "selectedTheme"
    const val SELECTED_TEAM = "selectedTeam"
    const val SELECTED_TEAM_POS = "selectedTeamPos"


    const val CHALLENGES_ISPRIVATE_NOTIFICATION = "isPrivate"
    const val PRIVATE_CHALLENGE_JOIN_CODE_NOTIFICATION = "challenge_join_code"

    const val PASSWORD_MINIMUM_LENGTH = 6

    const val FRAGMENT_NAME = "FRAGMENT_NAME"
    const val FRAGMENT_ID = "FRAGMENT_ID"

    const val FRAGMENT_ID_DISTANCE = 100
    const val FRAGMENT_ID_MINUTES = 101
    const val FRAGMENT_ID_CALORIES = 102
    const val FRAGMENT_ID_REWARDS_DETAILS = 103
    const val FRAGMENT_ID_REWARDS_REDEEM = 104
    const val FRAGMENT_ID_EDIT_PROFILE = 105
    const val FRAGMENT_ID_VIEW_CORPORATE = 106
    const val FRAGMENT_ID_REDEEM_DETAILS = 108
    const val FRAGMENT_ID_CHALLENGE_DETAILS = 110
    const val FRAGMENT_ID_NOTIFICATION = 113
    const val FRAGMENT_ID_EDIT_MOBILE = 114
    const val FRAGMENT_ID_VALIDATE_OTP = 115
    const val FRAGMENT_ID_CHANGE_PASSWORD = 116
    const val FRAGMENT_ID_CHALLENGE_STATUS = 117
    const val FRAGMENT_ID_TEAM_DETAILS = 118
    const val FRAGMENT_ID_LOCATION_SELECTION = 119
    const val FRAGMENT_ID_STEPS = 120
    const val FRAGMENT_ID_BIOMETRIC_LOGIN = 121
    const val FRAGMENT_ID_UNIT_CONVERSION = 122
    const val FRAGMENT_ID_SETTINGS = 123
    const val FRAGMENT_ID_SEARCH = 124
    const val FRAGMENT_ID_VIEW_ALL_LEADER_BOARD = 125
    const val FRAGMENT_CHANGE_LANGUAGE = 126
    const val FRAGMENT_ID_ADD_PRIVATE_CHALLENGE = 127
    const val FRAGMENT_ID_CREATE_PRIVATE_CHALLENGE = 128
    const val FRAGMENT_ID_PRIVATE_CHALLENGE_CHOOSE_THEME = 129
    const val FRAGMENT_ID_PRIVATE_CHALLENGE_NOT_FOUND = 130
    const val FRAGMENT_ID_MY_TEAM_LEADERBOARD = 131
    const val FRAGMENT_ID_MVP_PARTICIPANT = 132
    const val FRAGMENT_ID_DAILY_STEP = 133
    const val FRAGMENT_ID_WHATS_NEW = 134
    const val FRAGMENT_ID_MY_TEAM_LEADER_BOARD_DFC = 135
    const val FRAGMENT_ID_VIEW_ALL_LEADER_BOARD_PC4 = 136

    const val USER_DATA = "USER_DATA"
    const val NEED_MOBILE_CODE = "NEED_MOBILE_CODE"
    const val COUNTRY_DATA = "MOBILE_CODE"
    const val SELECTED_COUNTRY_DATA = "SELECTED_COUNTRY_DATA"
    const val IS_FROM_LOGIN_REGISTER = "IS_FROM_LOGIN_REGISTER"
    const val WEB_HEADER = "WEB_HEADER"
    const val WEB_URL = "WEB_URL"
    const val REWARD_TYPE = "REWARD_TYPE"
    const val REWARD_SELECTED_POSITION = "REWARD_SELECTED_POSITION"
    const val CATEGORY_LIST = "CATEGORY_LIST"
    const val CATEGORY = "CATEGORY"
    const val REFERRAL_CODE = "REFERRAL_CODE"
    const val MERCHANT_LIST = "MERCHANT_LIST"
    const val SELECTED_MERCHANT_STORE = "SELECTED_MERCHANT_STORE"
    const val SELECTED_REWARD = "SELECTED_REWARD"

    //challenges
    const val CHALLENGES_DATA = "CHALLENGES_DATA"
    const val PRIVATE_CHALLENGE_DETAILS_DATA = "PRIVATE_CHALLENGE_DETAILS_DATA"
    const val IS_NEW_MESSAGE = "isNewMessage"
    const val CHALLENGES_DATA_ID = "CHALLENGES_DATA_ID"
    const val CHALLENGES_TYPE = "CHALLENGES_TYPE"
    const val CHOOSE_DEVICE_CONNECTION_DATA = "CHOOSE_DEVICE_CONNECTION_DATA"
    const val SEARCH_CATEGORY_LIST = "SEARCH_CATEGORY_LIST"
    const val CHALLENGES_DATA_LIST = "CHALLENGES_DATA_LIST"
    const val PRIVATE_CHALLENGE_DATA = "PRIVATE_CHALLENGE_DATA"
    const val CHALLENGES_ISPRIVATE = "CHALLENGES_ISPRIVATE"
    const val PRIVATE_CHALLENGE_JOIN_CODE = "PRIVATE_CHALLENGE_JOIN_CODE"
    const val IS_CHALLENGE_STARTED = "IS_CHALLENGE_STARTED"
    const val IS_CHALLENGE_COMPLETED = "IS_CHALLENGE_COMPLETED"
    const val RANK = "RANK"
    const val CHALLENGES_END_DATE = "CHALLENGES_END_DATE"
    const val CHALLENGES_START_DATE = "CHALLENGES_START_DATE"
    const val WHATS_NEW_SCREEN_LIST_DATA = "WHATS_NEW_SCREENLIST_DATA"

    const val DIGITAL_REWARD_REDEEM_DATA = "DIGITAL_REWARD_REDEEM_DATA"
    var BROADCAST_NOTIFICATION_UPDATE = "BROADCAST_NOTIFICATION_UPDATE"

    const val REWARD_ID = "REWARD_ID"
    const val STORE_ID = "STORE_ID"
    const val CHALLENGE_NOT_STARTED = "CHALLENGE_NOT_STARTED"
    const val CHALLENGE_STARTED = "CHALLENGE_STARTED"
    const val CHALLENGE_COMPLETED = "CHALLENGE_COMPLETED"
    const val CHALLENGE_NOT_ENDED = "CHALLENGE_NOT_ENDED"
    const val STEPPI_CHALLENGE_LIST = "STEPPI_CHALLENGE_LIST"
    const val MY_CHALLENGE_LIST = "MY_CHALLENGE_LIST"
    const val CHALLENGE_TODAY = "CHALLENGE_TODAY"

    const val MALE = 1
    const val FEMALE = 2
    const val OTHER = 3

    const val REQUEST_CODE_COUNTRY = 1001
    const val REQUEST_CODE_NATIONALITY = 1002
    const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1111
    const val FITBIT_PERMISSIONS_REQUEST_CODE = 1112
    const val REQUEST_CODE_STORE_LOCATION = 1113
    const val REQUEST_CODE_DEVICE_CONNECTION = 1114
    const val REQUEST_CODE_DEVICE_CONNECTION_SUCCESS = 1115
    const val REQUEST_CODE_WHATS_NEW = 1117
    const val GARMIN_PERMISSIONS_REQUEST_CODE = 1118
    const val HUAWEI_HEALTH_PERMISSIONS_REQUEST_CODE = 1119

    const val MAKE_PERMISSION_REQUEST_CODE = 1


    var PROFILE_UPDATE = false
    var IS_PRIVATE_CHALLENGE_CREATED = false
//    var CHALLENGE_ANIMATION_UPDATE = false

    const val GOOGLE_FIT = 1
    const val SAMSUNG_HEALTH = 6
    const val FIT_BIT = 2
    const val GARMIN = 3
    const val XIAOMI = 5

    const val ACTIVE_LEVEL_OTP = 0
    const val ACTIVE_LEVEL_DEVICE_SELECTION = 1
    const val ACTIVE_LEVEL_REGISTERED = 2

    const val IN_STORE = 1
    const val DIGITAL = 2

    const val REWARD_TYPE_IN_STORE = "inStore"
    const val REWARD_TYPE_DIGITAL = "digital"

    const val CHALLENGE_OPERATION_JOIN = "join"
    const val CHALLENGE_OPERATION_LEAVE = "leave"

    // View Types for pagination
    const val ITEM = 0
    const val LOADING = 1

    const val VISIBLE_THRESHOLD = 3

    var UPDATE_MY_CHALLENGE_LIST = false
    var UPDATE_STEPPI_CHALLENGE_LIST = false

    // challenge type

    const val STEPPI_CHALLENGE = "STEPPI"
    const val CORPORATE_CHALLENGE = "CORPORATE"


    var MARK_ALL_NOTIFICATION_READ = false
    var NOTIFICATION_COUNT = 0


    const val PARTICIPANT_TEAM = "PARTICIPANT_TEAM"
    const val PARTICIPANT_TEAM_ID = "PARTICIPANT_TEAM_ID"
    const val PARTICIPANT_TEAMS_DATA = "PARTICIPANT_TEAMS_DATA"
    const val PARTICIPANT_TEAM_RANK_POSITION = "PARTICIPANT_TEAM_RANK_POSITION"
    const val MVP_PARTICIPANT = "MVP_PARTICIPANT"
    const val CHALLENGE_ID = "CHALLENGE_ID"
    const val TEAM_ID = "TEAM_ID"
    const val TEAM_MEMBER_LIST = "TEAM_MEMBER_LIST"
    const val IMAGE_FILE = "imageFile"
    const val REGISTER_DATA = "registerData"
    const val FACEBOOK_IMAGE_URL = "facebookImageUrl"
    const val FITBIT_TOKEN_EXPIRED_OR_INVALID = "FITBIT_TOKEN_EXPIRED_OR_INVALID"
    const val ANALYTICS_TYPE = "ANALYTICS_TYPE"
    const val ANALYTICS_DATA = "ANALYTICS_DATA"
    const val ANALYTICS_TYPE_DISTANCE = "analytics_distance"
    const val ANALYTICS_TYPE_CALORIES = "analytics_calories"
    const val ANALYTICS_TYPE_STEPS = "analytics_steps"
    const val ANALYTICS_TYPE_ACTIVEMINUTES = "minutes_values"
    const val FROM_MY_PROFILE = "fromMyProfile"
    const val DEVICE_SELECTED = "DEVICE_SELECTED"
    const val LANGUAGE_CHANGE = "LANGUAGE_CHANGE"

    const val ZERO = 0
    const val SEVEN = 7
    const val FIVE = 5
    const val SIX = 6

    const val FITBIT_PACKAGE_NAME = "com.fitbit.FitbitMobile"
    const val GOOGLE_FIT_PACKAGE_NAME = "com.google.android.apps.fitness"
    const val GARMIN_PACKAGE_NAME = "com.garmin.android.apps.connectmobile"
    const val HUAWEI_HEALTH_PACKAGE_NAME = "com.huawei.health"

    const val HUAWEI_HEALTH_MIN_VERCODE = 1000152513 //"10.1.2.513"

    const val HUAWEI_DEVICE_ID = "1"
    const val HUAWEI_DEVICE_NAME = "Huawei Health"
    const val HUAWEI_DEVICE_CODE = "HUAWEI_HEALTH"


    const val CHALLENGE_THEME_NONE = "NONE"
    const val CHALLENGE_THEME_1 = "THEME_1"
    const val CHALLENGE_THEME_2 = "THEME_2"

    const val CHALLENGE_STATUS_COMPLETED = "Completed"
    const val CHALLENGE_STATUS_ONGOING = "Ongoing"
    const val CHALLENGE_STATUS_UPCOMING = "Upcoming"
    const val CHALLENGE_STATUS_FAILED = "Failed"

    const val STEPPI_CHALLENGE_1 = "STEPPI_CHALLENGE_1"
    const val STEPPI_CHALLENGE_2 = "STEPPI_CHALLENGE_2"
    const val STEPPI_CHALLENGE_3 = "STEPPI_CHALLENGE_3"
    const val TOUGH_MUDDER_CHALLENGE = "TOUGH_MUDDER_CHALLENGE"
    const val DUBAI_FITNESS_CHALLENGE = "DUBAI_FITNESS_CHALLENGE"

    const val PC1 = "Group"
    const val PC2 = "Teams"
    const val PC3 = "Daily Goal"
    const val PRIVATE = "Private"
    const val THUGH_MUDDER = "Team Angel Wolf"

    var IS_LANGUAGE_CHANGED = false

    // My Challenges API Type End Points
    const val API_END_POINT_UPCOMING = "upcoming"
    const val API_END_POINT_ONGOING = "ongoing"
    const val API_END_POINT_CORPORATE = "corporate"
    const val API_END_POINT_COMPLETED = "completed"

    // Steppi Challenges API Type End Points
    const val API_END_POINT_ALL = "all"
    const val API_END_POINT_PC_1 = "pc1"
    const val API_END_POINT_PC_2 = "pc2"
    const val API_END_POINT_PC_3 = "pc3"
    const val API_END_POINT_DFC = "pc4"

    const val FIT_BIT_USER_ID_LABEL = "user_id"
    const val GOOGLE_FIT_USER_ID_LABEL = "accountId"

    const val TOUGH_MUDDER_CHALLENGE_NOT_JOINED = 0
    const val TOUGH_MUDDER_CHALLENGE_JOINED_NOTSTARTED = 1
    const val TOUGH_MUDDER_CHALLENGE_JOINED_STARTED = 2
    const val TOUGH_MUDDER_CHALLENGE_FINISHED = 3
    const val TOUGH_MUDDER_CHALLENGE_FAILED = 4

    const val TOUGH_MUDDER_CHALLENGE_TARGET_STEPS = "targetSteps"
    const val TOUGH_MUDDER_CHALLENGE_TARGET_DISTANCE = "targetDistance"
    const val TOUGH_MUDDER_CHALLENGE_TARGET_CALORIES = "targetCalories"
    const val TOUGH_MUDDER_CHALLENGE_TARGET_ACTIVE_MINUTES = "targetActiveMinutes"

    var IS_DFC_CHALLENGE_ENABLED = false
    var IS_SYNC_INITIALLY = true

    const val HEADER_KEY_LANGUAGE = "lang"
}
