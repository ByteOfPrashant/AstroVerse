package com.byteofprashant.domain.repository

import com.byteofprashant.domain.model.Apod

interface ApodRepository {
    suspend fun getApod(date: String? = null): Result<Apod>
}