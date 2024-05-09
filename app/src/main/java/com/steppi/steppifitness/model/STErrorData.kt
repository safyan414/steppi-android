package com.steppi.steppifitness.model

import java.io.Serializable

class STErrorData : Serializable {
    var message: String? = null
    var errorCode: Int = 0
    var statusCode: Int = 0
}
