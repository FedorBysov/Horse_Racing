package com.example.race_impl.domain

import com.example.network_api.model.RaceUpdateDto
import com.example.race_api.model.Horse
import com.example.race_impl.domain.entity.RaceUpdateDO
import kotlinx.coroutines.flow.Flow

interface RaceRepository {

    fun watchRace(): Flow<RaceUpdateDO>

    suspend fun saveRaceResults(horses: List<Horse>)
}
