package com.example.horseracing.di

import com.example.horseracing.MainActivity
import com.example.navigation_api.FeatureApi
import com.example.navigation_impl.di.NavigationModule
import com.example.network_impl.di.NetworkModule
import com.example.race_impl.di.RaceModule
import com.example.rating_impl.di.RatingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    NavigationModule::class,
    RaceModule::class,
    RatingModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun featureApis(): Set<@JvmSuppressWildcards FeatureApi>
} 