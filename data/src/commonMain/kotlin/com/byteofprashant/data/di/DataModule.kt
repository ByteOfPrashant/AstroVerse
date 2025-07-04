package com.byteofprashant.data.di

import com.byteofprashant.data.remote.ApiService
import com.byteofprashant.data.remote.KtorClient
import com.byteofprashant.data.repository.ApodRepositoryImpl
import com.byteofprashant.domain.repository.ApodRepository
import org.koin.dsl.module

object DataModule {

    fun provideModule() = module {
        single { KtorClient.httpClient }
        factory<ApiService> { ApiService(get()) }
        single<ApodRepository> { ApodRepositoryImpl(get()) }
    }
}