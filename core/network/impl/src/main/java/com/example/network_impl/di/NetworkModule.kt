package com.example.network_impl.di

import com.example.network_api.HistoryApi
import com.example.network_api.RaceApi
import com.example.network_impl.MockHistoryApi
import com.example.network_impl.MockRaceApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideRaceApi(): RaceApi = MockRaceApi()

    @Provides
    @Singleton
    fun provideHistoryApi(): HistoryApi = MockHistoryApi()
} 