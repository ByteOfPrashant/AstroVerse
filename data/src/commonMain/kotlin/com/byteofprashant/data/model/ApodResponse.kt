package com.byteofprashant.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApodResponse(
    val title: String,
    val explanation: String,
    val url: String,
    val date: String,
    val media_type: String // "image" or "video"
)