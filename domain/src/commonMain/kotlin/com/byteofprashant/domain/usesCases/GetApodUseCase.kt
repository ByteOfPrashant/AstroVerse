package com.byteofprashant.domain.usesCases

import com.byteofprashant.domain.model.Apod
import com.byteofprashant.domain.repository.ApodRepository

class GetApodUseCase(private val repository: ApodRepository) {

    suspend operator fun invoke(date: String?): Result<Apod> = repository.getApod(date)
}