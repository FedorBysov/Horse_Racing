package com.example.rating_impl.data

import com.example.rating_impl.domain.RatingRepository
import com.example.storage_api.dao.RatingDao
import com.example.storage_api.model.RaceWithResults
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val ratingDao: RatingDao
) : RatingRepository{
    override fun getRaceHistory(): Flow<List<RaceWithResults>> = ratingDao.observeAllRaces()

    override suspend fun clearRaceHistory() = ratingDao.deleteAllRaces()
}