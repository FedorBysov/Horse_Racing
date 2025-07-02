package com.example.network_impl.di

import com.example.network_api.HistoryApi
import com.example.network_api.RaceApi
import com.example.network_impl.MockRaceApi
import com.example.utils.di.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {
    @[Provides ApplicationScope]
    fun provideRaceApi(): RaceApi = MockRaceApi()


} 