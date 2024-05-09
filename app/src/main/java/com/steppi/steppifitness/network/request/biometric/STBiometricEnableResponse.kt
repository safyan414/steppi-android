package com.steppi.steppifitness.network.request.biometric

import com.squareup.moshi.Json
import java.io.Serializable

class STBiometricEnableResponse : Serializable {
    @Json(name = "message")
    var message: String? = null
    @Json(name = "data")
    var data: FingerPrintData? = null
}

class FingerPrintData : Serializable {
    @Json(name = "fingerprint")
    var fingerprint: String? = null
}
