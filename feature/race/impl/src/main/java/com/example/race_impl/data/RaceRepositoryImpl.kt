package com.example.race_impl.data

import com.example.network_api.RaceApi
import com.example.race_api.model.Horse
import com.example.race_impl.domain.RaceRepository
import com.example.race_impl.domain.entity.RaceUpdateDO
import com.example.race_impl.domain.toDataBaseObject
import com.example.race_impl.domain.toDomainObject
import com.example.race_impl.domain.toRaceResultDomainList
import com.example.storage_api.dao.RatingDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RaceRepositoryImpl @Inject constructor(
    private val raceApi: RaceApi,
    private val ratingDao: RatingDao
) : RaceRepository {

    override fun watchRace(): Flow<RaceUpdateDO> = raceApi.watchRace().map {
        it.toDomainObject()
    }

    override suspend fun saveRaceResults(horses: List<Horse>) {
        val raceResults = horses.toRaceResultDomainList()
        val (raceEntity, horseResults) = raceResults.toDataBaseObject()
        ratingDao.insertRaceWithResults(raceEntity, horseResults)
    }

    override suspend fun getHorseList(): List<Horse> {
        return raceApi.getHorseList().toDomainObject()
    }
}