package com.example.storage_impl.di

import android.content.Context
import com.example.storage_api.dao.RatingDao
import com.example.storage_impl.RatingDataBase
import com.example.utils.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object RatingStorageModule {

    @[Provides ApplicationScope]
    fun provideRatingDataBase(context:Context): RatingDataBase = RatingDataBase.getInstance(context)

    @[Provides ApplicationScope]
    fun provideRatingDao(dataBase: RatingDataBase): RatingDao = dataBase.ratingDao()
}