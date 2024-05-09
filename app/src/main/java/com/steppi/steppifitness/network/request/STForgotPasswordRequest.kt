package com.steppi.steppifitness.network.request

import com.squareup.moshi.Json

class STForgotPasswordRequest {
  @Json(name ="phoneNumber")
  var phoneNumber: String? = null
}
