package com.example.rating_impl.domain.Interactors

import com.example.storage_api.model.RaceWithResults
import kotlinx.coroutines.flow.Flow

interface RatingInteractor {

    fun getRaceHistoryUseCase(): Flow<List<RaceWithResults>>

    suspend fun clearRaceHistoryUseCase()

}