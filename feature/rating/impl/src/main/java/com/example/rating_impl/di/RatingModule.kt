package com.example.rating_impl.di

import com.example.navigation_api.FeatureApi
import com.example.rating_api.RatingFeatureApi
import com.example.rating_api.RatingScreen
import com.example.rating_impl.ui.RatingFeatureImpl
import com.example.rating_impl.ui.RatingScreenImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
abstract class RatingModule {
    @Binds
    @Singleton
    abstract fun provideRatingScreen(impl: RatingScreenImpl): RatingScreen

    @Binds
    @Singleton
    abstract fun provideRatingFeature(impl: RatingFeatureImpl): RatingFeatureApi

    @Binds
    @IntoSet
    abstract fun bindFeatureApi(impl: RatingFeatureImpl): FeatureApi
} 