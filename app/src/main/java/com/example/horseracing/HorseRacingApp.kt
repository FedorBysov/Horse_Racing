package com.example.horseracing

import android.app.Application
import com.example.horseracing.di.AppComponent
import com.example.horseracing.di.DaggerAppComponent

class HorseRacingApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
} 