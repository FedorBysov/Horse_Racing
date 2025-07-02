package com.example.rating_impl.domain

import com.example.storage_api.model.RaceWithResults
import kotlinx.coroutines.flow.Flow

interface RatingRepository {

    fun getRaceHistory(): Flow<List<RaceWithResults>>

    suspend fun clearRaceHistory()
}