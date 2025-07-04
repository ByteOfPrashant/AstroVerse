package com.byteofprashant.data.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

object ErrorHandler {
    fun handleError(throwable: Throwable): String {
        return when (throwable) {
            is ClientRequestException -> "Invalid request. Please check the input data and try again."
            is ServerResponseException -> "Server error occurred. Please try again later or check if the service is available."
            is IOException -> "Network issue detected. Ensure your device is connected to the internet."
            else -> "Something went wrong. Please try again or contact support if the issue persists."
        }
    }
}