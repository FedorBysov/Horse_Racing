package com.example.race_impl.di

import com.example.race_api.RaceScreen
import com.example.race_impl.ui.RaceScreenImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RaceModule {
    
    @Binds
    @Singleton
    abstract fun provideRaceScreen(impl: RaceScreenImpl): RaceScreen
} 