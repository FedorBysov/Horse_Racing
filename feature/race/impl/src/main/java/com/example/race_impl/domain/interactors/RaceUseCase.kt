package com.example.race_impl.domain.interactors

import com.example.race_api.model.Horse
import com.example.race_impl.domain.entity.RaceUpdateDO
import kotlinx.coroutines.flow.Flow

interface RaceUseCase {

    fun watchRaceUseCase(): Flow<RaceUpdateDO>

    suspend fun saveRaceResults(horses: List<Horse>)

}