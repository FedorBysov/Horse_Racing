package com.example.horseracing.di

import com.example.horseracing.MainActivity
import com.example.network_impl.di.NetworkModule
import com.example.race_impl.di.RaceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    RaceModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
} 