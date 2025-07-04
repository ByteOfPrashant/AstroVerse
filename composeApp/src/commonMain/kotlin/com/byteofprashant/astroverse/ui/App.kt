package com.byteofprashant.astroverse.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.byteofprashant.astroverse.di.AppModule
import com.byteofprashant.astroverse.ui.HomeApodScreen.HomeApod
import com.byteofprashant.data.di.DataModule
import com.byteofprashant.domain.di.DomainModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(listOf(AppModule.provideModule(), DataModule.provideModule(), DomainModule.provideModule()))
    }) {
        MaterialTheme {
            HomeApod()
        }
    }
}                           