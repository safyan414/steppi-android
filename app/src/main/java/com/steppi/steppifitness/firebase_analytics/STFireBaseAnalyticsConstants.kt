package com.steppi.steppifitness.firebase_analytics

object STFireBaseAnalyticsConstants {
    //    Event Names
    const val EVENT_SIGN_UP_FORM_VISIT = "signup_form_visit"
    const val EVENT_SIGN_UP_FORM_SUBMISSION = "signup_form_submission"
    const val EVENT_SIGN_UP_OTP_SCREEN = "signup_otp_screen"
    const val EVENT_SIGN_UP_OTP_SUBMISSION = "signup_otp_submission"
    const val EVENT_SIGN_UP_COMPLETION = "signup_completion"
    const val EVENT_ON_BOARDING_PAGE_1 = "onboarding_page_1"
    const val EVENT_ON_BOARDING_PAGE_2 = "onboarding_page_2"
    const val EVENT_ON_BOARDING_PAGE_3 = "onboarding_page_3"
    const val EVENT_SIGN_IN_WITH_EMAIL = "sign_in_with_email"
    const val EVENT_SIGN_IN_WITH_FACEBOOK = "sign_in_with_facebook"
    const val EVENT_IN_STORE_VENDOR_DETAIL_VISIT = "instore_vendor_detail_visit"
    const val EVENT_IN_STORE_VENDOR_REWARD_VISIT = "instore_vendor_reward_visit"
    const val EVENT_IN_STORE_VENDOR_REWARD_REDEMPTION = "instore_vendor_reward_redemption"
    const val EVENT_DIGITAL_VENDOR_DETAIL_VISIT = "digital_vendor_detail_visit"
    const val EVENT_DIGITAL_VENDOR_REWARD_VISIT = "digital_vendor_reward_visit"
    const val EVENT_DIGITAL_VENDOR_REWARD_REDEMPTION = "digital_vendor_reward_redemption"
    const val EVENT_PUBLIC_CHALLENGE_DETAIL_VISIT = "public_challenge_detail_visit"
    const val EVENT_PUBLIC_CHALLENGE_JOIN = "public_challenge_join"
    const val EVENT_CORPORATE_CHALLENGE_DETAIL_VISIT = "corporate_challenge_detail_visit"
    const val EVENT_CORPORATE_CHALLENGE_LEAVE = "corporate_challenge_leave"
    const val EVENT_DEVICE_SELECTION = "device_selection"
    //    Event Parameters
    const val EVENT_PARAMETER_VENDOR_NAME = "vendor_name"
    const val EVENT_PARAMETER_VENDOR_ID = "vendor_id"
    const val EVENT_PARAMETER_REWARD_NAME = "reward_name"
    const val EVENT_PARAMETER_REWARD_ID = "reward_id"
    const val EVENT_PARAMETER_CHALLENGE_NAME = "challenge_name"
    const val EVENT_PARAMETER_CHALLENGE_ID = "challenge_id"
    const val EVENT_PARAMETER_CORPORATE_CHALLENGE_ID = "corporate_id"
    const val EVENT_PARAMETER_CORPORATE_NAME = "corporate_name"
    const val EVENT_PARAMETER_HEALTH_DEVICE_NAME = "health_device"
    const val EVENT_PARAMETER_HEALTH_DEVICE_PLATFORM = "platform"
}
