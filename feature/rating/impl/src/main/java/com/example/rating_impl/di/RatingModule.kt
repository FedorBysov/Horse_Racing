package com.example.rating_impl.di

import com.example.navigation_api.FeatureApi
import com.example.rating_api.RatingFeatureApi
import com.example.rating_api.RatingScreen
import com.example.rating_impl.presentation.RatingFeatureImpl
import com.example.rating_impl.presentation.RatingScreenImpl
import com.example.utils.di.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
abstract class RatingModule {
    @Binds
    @ApplicationScope
    abstract fun provideRatingScreen(impl: RatingScreenImpl): RatingScreen

    @Binds
    @ApplicationScope
    abstract fun provideRatingFeature(impl: RatingFeatureImpl): RatingFeatureApi

    @Binds
    @IntoSet
    abstract fun bindFeatureApi(impl: RatingFeatureImpl): FeatureApi
} 