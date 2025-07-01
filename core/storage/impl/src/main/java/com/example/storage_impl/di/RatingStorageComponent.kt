package com.example.storage_impl.di

import androidx.room.processor.Context
import com.example.storage_api.dao.RatingDao
import com.example.utils.di.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [RatingStorageComponent::class])
interface RatingStorageComponent {

    fun ratingDao(): RatingDao

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): RatingStorageComponent
    }
}