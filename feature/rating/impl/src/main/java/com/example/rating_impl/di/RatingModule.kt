package com.example.rating_impl.di

import com.example.navigation_api.FeatureApi
import com.example.rating_api.RatingFeatureApi
import com.example.rating_api.RatingScreen
import com.example.rating_impl.RatingFeatureImpl
import com.example.rating_impl.data.RatingRepositoryImpl
import com.example.rating_impl.domain.Interactors.RatingInteractorImpl
import com.example.rating_impl.domain.Interactors.RatingUseCase
import com.example.rating_impl.domain.RatingRepository
import com.example.rating_impl.presentation.RatingScreenImpl
import com.example.utils.di.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class RatingModule {
    @Binds
    @ApplicationScope
    abstract fun bindsRatingScreen(impl: RatingScreenImpl): RatingScreen

    @Binds
    @ApplicationScope
    abstract fun bindsRatingFeature(impl: RatingFeatureImpl): RatingFeatureApi

    @Binds
    @IntoSet
    abstract fun bindFeatureApi(impl: RatingFeatureImpl): FeatureApi


    @Binds
    @ApplicationScope
    abstract fun bindRatingRepository(impl: RatingRepositoryImpl): RatingRepository

    @Binds
    @ApplicationScope
    abstract fun bindRatingUseCase(impl: RatingInteractorImpl): RatingUseCase
} 