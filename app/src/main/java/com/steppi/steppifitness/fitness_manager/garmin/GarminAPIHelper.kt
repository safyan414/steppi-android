package com.steppi.steppifitness.fitness_manager.garmin

import com.github.scribejava.core.builder.api.DefaultApi10a
import com.github.scribejava.core.model.Token

class GarminAPIHelper : DefaultApi10a() {

    companion object{
        fun getInstance() : GarminAPIHelper{
            return GarminAPIHelper()
        }
    }

    override fun getAuthorizationBaseUrl(): String {
        return "https://connect.garmin.com/oauthConfirm"
    }

    override fun getRequestTokenEndpoint(): String {
        return "https://connectapi.garmin.com/oauth-service/oauth/request_token"
    }

    override fun getAccessTokenEndpoint(): String {
        return "https://connectapi.garmin.com/oauth-service/oauth/access_token"
    }
}
