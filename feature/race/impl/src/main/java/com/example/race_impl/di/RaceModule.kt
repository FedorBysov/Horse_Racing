package com.example.race_impl.di

import com.example.navigation_api.FeatureApi
import com.example.race_api.RaceFeatureApi
import com.example.race_api.RaceScreen
import com.example.race_impl.data.RaceRepositoryImpl
import com.example.race_impl.domain.RaceRepository
import com.example.race_impl.domain.interactors.RaceInteractorImpl
import com.example.race_impl.domain.interactors.RaceInteractor
import com.example.race_impl.RaceFeatureImpl
import com.example.race_impl.presentation.RaceScreenImpl
import com.example.utils.di.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class RaceModule {

    @Binds
    @ApplicationScope
    abstract fun bindsRaceFeature(impl: RaceFeatureImpl): RaceFeatureApi

    @Binds
    @IntoSet
    abstract fun bindFeatureApi(impl: RaceFeatureImpl): FeatureApi

    @Binds
    @ApplicationScope
    abstract fun bindRaceRepository(impl: RaceRepositoryImpl): RaceRepository

    @Binds
    @ApplicationScope
    abstract fun bindRaceUseCase(impl: RaceInteractorImpl): RaceInteractor

    @Binds
    @ApplicationScope
    abstract fun bindRaceScreen(impl: RaceScreenImpl): RaceScreen
} 