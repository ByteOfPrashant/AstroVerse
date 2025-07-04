package com.byteofprashant.domain.di

import com.byteofprashant.domain.usesCases.GetApodUseCase
import org.koin.dsl.module

object DomainModule {

    fun provideModule() = module {
        factory { GetApodUseCase(get()) }
    }
}