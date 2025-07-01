package com.example.storage_api.dao

import androidx.room.*
import com.example.storage_api.entity.HorseResultEntity
import com.example.storage_api.entity.RaceEntity
import com.example.storage_api.model.RaceWithResults
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingDao {

    @Transaction
    @Query("SELECT * FROM races ORDER BY timestamp DESC")
    fun observeAllRaces(): Flow<List<RaceWithResults>>

    @Insert
    suspend fun insertRace(race: RaceEntity): Long

    @Insert
    suspend fun insertHorseResults(results: List<HorseResultEntity>)

    @Transaction
    suspend fun insertRaceWithResults(race: RaceEntity, results: List<HorseResultEntity>) {
        val raceId = insertRace(race)
        insertHorseResults(results.map { it.copy(raceId = raceId) })
    }

    @Query("DELETE FROM races")
    suspend fun deleteAllRaces()
} 