package com.example.rating_impl.domain.Interactors

import com.example.rating_impl.domain.RatingRepository
import com.example.storage_api.model.RaceWithResults
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatingInteractorImpl @Inject constructor(
    private val ratingRepository: RatingRepository
): RatingUseCase{
    override fun getRaceHistoryUseCase(): Flow<List<RaceWithResults>> = ratingRepository.getRaceHistory()

    override suspend fun clearRaceHistoryUseCase() = ratingRepository.clearRaceHistory()

}