package com.example.race_impl.di

import com.example.navigation_api.FeatureApi
import com.example.race_api.RaceFeatureApi
import com.example.race_impl.presentation.RaceFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
abstract class RaceModule {
    @Binds
    @Singleton
    abstract fun provideRaceFeature(impl: RaceFeatureImpl): RaceFeatureApi

    @Binds
    @IntoSet
    abstract fun bindFeatureApi(impl: RaceFeatureImpl): FeatureApi
} 