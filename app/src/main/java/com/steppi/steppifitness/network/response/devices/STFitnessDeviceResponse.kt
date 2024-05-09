package com.steppi.steppifitness.network.response.devices

import com.squareup.moshi.Json
import com.steppi.steppifitness.model.STDeviceData
import com.steppi.steppifitness.network.response.STBaseResponse
import java.io.Serializable

class STFitnessDeviceResponse(
    @Json(name = "data")
    var data: List<STDeviceData>? = null
) : STBaseResponse()
