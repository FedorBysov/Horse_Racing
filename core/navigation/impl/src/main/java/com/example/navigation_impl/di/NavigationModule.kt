package com.example.navigation_impl.di

import com.example.navigation_api.FeatureApi
import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class NavigationModule {
    @Multibinds
    abstract fun provideFeatures(): Set<FeatureApi>
} 