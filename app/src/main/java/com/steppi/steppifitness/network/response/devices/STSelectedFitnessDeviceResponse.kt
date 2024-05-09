package com.steppi.steppifitness.network.response.devices

import com.squareup.moshi.Json
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.network.response.STBaseResponse

class STSelectedFitnessDeviceResponse(
    @Json(name = "data")
    var data: STDeviceData? = null
) : STBaseResponse()
