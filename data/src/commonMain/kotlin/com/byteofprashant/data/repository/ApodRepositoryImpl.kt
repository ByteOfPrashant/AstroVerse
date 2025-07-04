package com.byteofprashant.data.repository

import com.byteofprashant.data.remote.ApiService
import com.byteofprashant.data.remote.safeApiCall
import com.byteofprashant.domain.model.Apod
import com.byteofprashant.domain.repository.ApodRepository

class ApodRepositoryImpl(val apiService: ApiService) : ApodRepository {
    override suspend fun getApod(date: String?): Result<Apod> {
        return safeApiCall {
            apiService.getApod(date)
        }
    }
}