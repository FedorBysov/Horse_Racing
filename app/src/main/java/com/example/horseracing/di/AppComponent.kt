package com.example.horseracing.di

import android.content.Context
import com.example.horseracing.MainActivity
import com.example.navigation_api.FeatureApi
import com.example.navigation_impl.di.NavigationModule
import com.example.network_impl.di.NetworkModule
import com.example.race_impl.di.RaceModule
import com.example.rating_impl.di.RatingModule
import com.example.storage_impl.di.RatingStorageModule
import com.example.utils.di.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(modules = [
    NetworkModule::class,
    NavigationModule::class,
    RaceModule::class,
    RatingModule::class,
    RatingStorageModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun featureApis(): Set<@JvmSuppressWildcards FeatureApi>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
} 