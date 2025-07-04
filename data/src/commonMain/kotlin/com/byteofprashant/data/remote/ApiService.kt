package com.byteofprashant.data.remote

import com.byteofprashant.data.model.ApodResponse
import com.byteofprashant.domain.model.Apod
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom

class ApiService(val client: HttpClient) {

    suspend fun getApod(date: String?): Apod {
        val repose: ApodResponse = client.get {
            url.takeFrom("https://api.nasa.gov/planetary/apod")
            parameter("api_key", "key") // Replace with your key
            date?.let { parameter("date", it) }
        }.body()

        return Apod(
            repose.title,
            repose.explanation,
            repose.url,
            date = repose.date,
            media_type = repose.media_type
        )
        
    }
}