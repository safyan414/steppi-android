package com.steppi.steppifitness.utils

import android.content.Context
import android.content.SharedPreferences
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import java.sql.Timestamp

object STPreference {
    private const val PREFERENCE_KEY = "Preference"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    @Synchronized
    fun setFacebookAccessToken(context: Context, facebook_access_token: String) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("FACEBOOK_ACCESS_TOKEN", facebook_access_token).apply()
    }

    fun getFacebookAccessToken(context: Context): String? {
        val preferences = getSharedPreferences(context)
        return preferences.getString("FACEBOOK_ACCESS_TOKEN", "")
    }

    @Synchronized
    fun setName(context: Context, fName: String?) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("NAME", fName).apply()
    }

    fun getName(context: Context): String? {
        val preferences = getSharedPreferences(context)
        return preferences.getString("NAME", null)
    }

    @Synchronized
    fun setUserId(context: Context, fName: String?) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("USER_ID", fName).apply()
    }

    fun getUserId(context: Context): String? {
        val preferences = getSharedPreferences(context)
        return preferences.getString("USER_ID", null)
    }

    @Synchronized
    fun setUserGender(context: Context, gender: Int?) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putInt("USER_GENDER", gender!!).apply()
    }

    fun getUserGender(context: Context): Int? {
        val preferences = getSharedPreferences(context)
        return preferences.getInt("USER_GENDER", 0)
    }

    @Synchronized
    fun setEmail(context: Context, email: String?) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("EMAIL", email).apply()
    }

    fun getEmail(context: Context): String? {
        val preferences = getSharedPreferences(context)
        return preferences.getString("EMAIL", "")
    }

    @Synchronized
    fun setCorporateEmail(context: Context, email: String?) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("CORPORATE_EMAIL", email).apply()
    }

    fun getCorporateEmail(context: Context): String? {
        val preferences = getSharedPreferences(context)
        return preferences.getString("CORPORATE_EMAIL", "")
    }

    @Synchronized
    fun setProfilePic(context: Context, profile_pic: String?) {
        val preferences = getSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString("PROFILE_PIC", profile_pic).apply()
    }

    fun getProfilePic(context: Context): String? {
        val preferences = getSharedPreferences(context)
        return preferences.getString("PROFILE_PIC", null)
    }

    @Synchronized
    fun saveLanguageCode(context: Context, languageCode: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("LANGUAGE_CODE", languageCode).apply()
    }


    fun getLanguageCode(context: Context): String? {
        return getSharedPreferences(context).getString("LANGUAGE_CODE", STConstants.ENGLISH_CODE)
            ?: STConstants.ENGLISH_CODE
    }

    @Synchronized
    fun saveDeviceId(context: Context, deviceId: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("DEVICE_ID", deviceId).apply()
    }


    fun getDeviceId(context: Context): String? {
        return getSharedPreferences(context).getString("DEVICE_ID", null)
    }

    @Synchronized
    fun saveRegToken(context: Context, userToken: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("REG_TOKEN", userToken).apply()
    }


    fun getRegToken(context: Context): String? {
        return getSharedPreferences(context).getString("REG_TOKEN", null)
    }

    @Synchronized
    fun saveAccessToken(context: Context, userToken: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("ACCESS_TOKEN", userToken).apply()
    }


    fun getAccessToken(context: Context): String? {
        return getSharedPreferences(context).getString("ACCESS_TOKEN", null)
    }

    @Synchronized
    fun saveFitnessDevice(context: Context, deviceId: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("FITNESS_DEVICE", deviceId).apply()
    }

    fun getFitnessDevice(context: Context): String? {
        return getSharedPreferences(context).getString("FITNESS_DEVICE", null)
    }

    @Synchronized
    fun saveFitnessDeviceName(context: Context, deviceName: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("FITNESS_DEVICE_NAME", deviceName).apply()
    }

    fun getFitnessDeviceName(context: Context): String? {
        return getSharedPreferences(context).getString("FITNESS_DEVICE_NAME", null)
    }

    @Synchronized
    fun saveFitnessDeviceID(context: Context, deviceId: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("FITNESS_DEVICE_ID", deviceId).apply()
    }

    fun getFitnessDeviceID(context: Context): String? {
        return getSharedPreferences(context).getString("FITNESS_DEVICE_ID", null)
    }

    @Synchronized
    fun saveVerifyToken(context: Context, verToken: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("VERIFY_TOKEN", verToken).apply()
    }


    fun getVerifyToken(context: Context): String? {
        return getSharedPreferences(context).getString("VERIFY_TOKEN", null)
    }

    @Synchronized
    fun setUserLevel(context: Context, userLevel: Int) {
        val editor = getSharedPreferences(context).edit()
        editor.putInt("USER_LEVEL", userLevel).apply()
    }

    fun getUserLevel(context: Context): Int {
        return getSharedPreferences(context).getInt("USER_LEVEL", -1)
    }

    @Synchronized
    fun saveGarminToken(context: Context, token: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("GARMIN_ACCESS_TOKEN", token).apply()
    }

    fun getGarminToken(context: Context): String? {
        return getSharedPreferences(context).getString("GARMIN_ACCESS_TOKEN", null)
    }

    @Synchronized
    fun saveGarminTokenSecret(context: Context, token: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("GARMIN_ACCESS_TOKEN_SECRET", token).apply()
    }

    fun getGarminTokenSecret(context: Context): String? {
        return getSharedPreferences(context).getString("GARMIN_ACCESS_TOKEN_SECRET", null)
    }

    // saving fitbit token.
    @Synchronized
    fun saveFitbitToken(context: Context, token: String?) {
        val editor = getSharedPreferences(context).edit()
        if (null != token) {
            editor.putString(
                "FITBIT_AUTH_TOKEN", Encryptor.encrypt(
                    Timestamp(
                        context.applicationContext.packageManager.getPackageInfo(
                            context.applicationContext.packageName,
                            0
                        ).firstInstallTime
                    ).toString(), token
                )
            ).apply()
        } else {
            editor.putString("FITBIT_AUTH_TOKEN", token).apply()
        }
    }

    fun getFitbitiToken(context: Context): String? {
        val token = getSharedPreferences(context).getString("FITBIT_AUTH_TOKEN", null)
        if (null != token) {
            return Encryptor.decrypt(
                Timestamp(
                    context.applicationContext.packageManager.getPackageInfo(
                        context.applicationContext.packageName,
                        0
                    ).firstInstallTime
                ).toString(), token
            )
        }
        return null
    }

    // saving fitbit user_id.
    @Synchronized
    fun saveFitBitUserId(context: Context, userId: String?) {
        val editor = getSharedPreferences(context).edit()
        if (null != userId) {
            editor.putString(
                "FIT_BIT_USER_ID", Encryptor.encrypt(
                    Timestamp(
                        context.applicationContext.packageManager.getPackageInfo(
                            context.applicationContext.packageName,
                            0
                        ).firstInstallTime
                    ).toString(), userId
                )
            ).apply()
        } else {
            editor.putString("FIT_BIT_USER_ID", userId).apply()
        }
    }

    fun getFitBitUserId(context: Context): String? {
        val userId = getSharedPreferences(context).getString("FIT_BIT_USER_ID", null)
        if (null != userId) {
            return Encryptor.decrypt(
                Timestamp(
                    context.applicationContext.packageManager.getPackageInfo(
                        context.applicationContext.packageName,
                        0
                    ).firstInstallTime
                ).toString(), userId
            )
        }
        return null
    }

    @Synchronized
    fun saveGarminUserId(context: Context, userId: String?) {
        val editor = getSharedPreferences(context).edit()
        if (null != userId) {
            editor.putString(
                "GARMIN_USER_ID", Encryptor.encrypt(
                    Timestamp(
                        context.applicationContext.packageManager.getPackageInfo(
                            context.applicationContext.packageName,
                            0
                        ).firstInstallTime
                    ).toString(), userId
                )
            ).apply()
        } else {
            editor.putString("GARMIN_USER_ID", userId).apply()
        }
    }

    fun getGarminUserId(context: Context): String? {
        val userId = getSharedPreferences(context).getString("GARMIN_USER_ID", null)
        if (null != userId) {
            return Encryptor.decrypt(
                Timestamp(
                    context.applicationContext.packageManager.getPackageInfo(
                        context.applicationContext.packageName,
                        0
                    ).firstInstallTime
                ).toString(), userId
            )
        }
        return null
    }

    // saving google fit user_id.
    @Synchronized
    fun saveGoogleFitUserId(context: Context, userId: String?) {
        val editor = getSharedPreferences(context).edit()
        if (null != userId) {
            editor.putString(
                "GOOGLE_FIT_USER_ID", Encryptor.encrypt(
                    Timestamp(
                        context.applicationContext.packageManager.getPackageInfo(
                            context.applicationContext.packageName,
                            0
                        ).firstInstallTime
                    ).toString(), userId
                )
            ).apply()
        } else {
            editor.putString("GOOGLE_FIT_USER_ID", userId).apply()
        }
    }

    // saving huawei health user_id.
    val HUAWEI_HEALTH_USER_ID = "HUAWEI_HEALTH_USER_ID"
    @Synchronized
    fun saveHuaweiHealthUserId(context: Context, userId: String?) {
        //todo:
        val editor = getSharedPreferences(context).edit()
        if (null != userId) {
            editor.putString(
                HUAWEI_HEALTH_USER_ID  , Encryptor.encrypt(
                    Timestamp(
                        context.applicationContext.packageManager.getPackageInfo(
                            context.applicationContext.packageName,
                            0
                        ).firstInstallTime
                    ).toString(), userId
                )
            ).apply()
        } else {
            editor.putString(HUAWEI_HEALTH_USER_ID, userId).apply()
        }
    }

    fun getGoogleFitUserId(context: Context): String? {
        val userId = getSharedPreferences(context).getString("GOOGLE_FIT_USER_ID", null)
        if (null != userId) {
            return Encryptor.decrypt(
                Timestamp(
                    context.applicationContext.packageManager.getPackageInfo(
                        context.applicationContext.packageName,
                        0
                    ).firstInstallTime
                ).toString(), userId
            )
        }
        return null
    }

    @Synchronized
    fun saveUnitSelected(context: Context, unitSelected: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("UNIT_SELECTED", unitSelected).apply()
    }

    fun getUnitSelected(context: Context): String? {
        return getSharedPreferences(context).getString(
            "UNIT_SELECTED",
            context.getString(R.string.kilometer)
        )
    }

    @Synchronized
    fun setBioMetricEnabled(context: Context, isBioMetricEnabled: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean("BIOMETRIC_ENABLED", isBioMetricEnabled).apply()
    }

    fun isBioMetricEnabled(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(
            "BIOMETRIC_ENABLED",
            false
        )
    }

    @Synchronized
    fun setBioMetricToken(context: Context, bioMetricToken: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString("BIOMETRIC_TOKEN", bioMetricToken).apply()
    }

    fun getBioMetricToken(context: Context): String? {
        return getSharedPreferences(context).getString(
            "BIOMETRIC_TOKEN",
            null
        )
    }

    @Synchronized
    fun setFirstTimeInstalled(context: Context, isFirstTimeInstalled: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean("FIRST_TIME_INSTALLED", isFirstTimeInstalled).apply()
    }

    fun isFirstTimeInstalled(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean("FIRST_TIME_INSTALLED", true)
    }
}
