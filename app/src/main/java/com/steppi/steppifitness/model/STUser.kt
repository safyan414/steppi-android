package com.steppi.steppifitness.model

import com.squareup.moshi.Json
import java.io.Serializable

class STUser(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "phoneNumber")
    var phoneNumber: String? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "gender")
    var gender: Int? = null,
    @Json(name = "latitude")
    var latitude: Any? = null,
    @Json(name = "longitude")
    var longitude: Any? = null,
    @Json(name = "password")
    var password: String? = null,
    @Json(name = "facebookId")
    var facebookId: String? = null,
    @Json(name = "instagramId")
    var instagramId: String? = null,
    @Json(name = "dob")
    var dob: String? = null,
    @Json(name = "picture")
    var picture: String? = null,
    @Json(name = "corporateEmail")
    var corporateEmail: String? = null,
    @Json(name = "countryId")
    var countryId: String? = null,
    @Json(name = "activeLevel")
    var activeLevel: Int = 0,
    @Json(name = "isActive")
    var isActive: Boolean? = null,
    @Json(name = "isDeleted")
    var isDeleted: Boolean? = null,
    @Json(name = "referredBy")
    var referredBy: Any? = null,
    @Json(name = "usedReferral")
    var usedReferral: String? = null,
    @Json(name = "deviceId")
    var deviceId: String? = null,
    @Json(name = "totalSteps")
    var totalSteps: String? = null,
    @Json(name = "lifetimeSteps")
    var lifetimeSteps: String? = null,
    @Json(name = "totalSavings")
    var totalSavings: String? = null,
    @Json(name = "lastSyncStamp")
    var lastSyncStamp: String? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null,
    @Json(name = "corporateUser")
    var corporateUser: STCorporateUser? = null,
    @Json(name = "referralCode")
    var referralCode: ReferralCode? = null,
    @Json(name = "country")
    var country: STCountryData? = null,
    @Json(name = "device")
    var device: STDeviceData? = null
) : Serializable

class ReferralCode(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "userId")
    var userId: String? = null,
    @Json(name = "referralCode")
    var referralCode: String? = null,
    @Json(name = "createdAt")
    var createdAt: String? = null,
    @Json(name = "updatedAt")
    var updatedAt: String? = null
) : Serializable
