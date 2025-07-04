package com.byteofprashant.astroverse.di

import com.byteofprashant.astroverse.ui.HomeApodScreen.ApodViewModel
import org.koin.dsl.module

object AppModule {

    fun provideModule() = module {
        single { ApodViewModel(get()) }

    }
}