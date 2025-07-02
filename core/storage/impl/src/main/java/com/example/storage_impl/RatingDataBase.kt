package com.example.storage_impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.storage_api.dao.RatingDao
import com.example.storage_api.entity.HorseResultEntity
import com.example.storage_api.entity.RaceEntity

@Database(
    entities = [RaceEntity::class, HorseResultEntity::class],
    version = 3,
    exportSchema = false
)
abstract class RatingDataBase : RoomDatabase(){

    abstract fun ratingDao(): RatingDao

    companion object{
        private const val DB_NAME = "RatingDataBase"
        private var INSTANCE: RatingDataBase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): RatingDataBase{
            INSTANCE?.let { return it }

            synchronized(LOCK){
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = RatingDataBase::class.java,
                    name = DB_NAME
                )
                    .fallbackToDestructiveMigration(false)
                    .build()

                INSTANCE = database

                return database
            }
        }
    }
}