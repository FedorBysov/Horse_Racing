package com.example.race_impl.domain.interactors

import com.example.race_api.model.Horse
import com.example.race_impl.domain.entity.RaceUpdateDO
import kotlinx.coroutines.flow.Flow

interface RaceInteractor {

    fun watchRaceUseCase(): Flow<RaceUpdateDO>

    suspend fun saveRaceResultsUseCase(horses: List<Horse>)

    suspend fun getHorseListUseCase(): List<Horse>
}