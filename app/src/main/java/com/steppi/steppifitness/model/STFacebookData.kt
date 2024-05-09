package com.steppi.steppifitness.model

import java.io.Serializable

data class STFacebookData(
    val id: String,
    val name: String,
    val email: String,
    val picture: Picture?,
    val first_name: String,
    val last_name: String
) : Serializable

data class Picture(
    val data: PictureData?
) : Serializable

data class PictureData(
    val height: Int,
    val is_silhouette: Boolean,
    val url: String?,
    val width: Int
) : Serializable
