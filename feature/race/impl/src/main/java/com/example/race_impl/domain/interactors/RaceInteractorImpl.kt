package com.example.race_impl.domain.interactors

import com.example.race_api.model.Horse
import com.example.race_impl.domain.RaceRepository
import com.example.race_impl.domain.entity.RaceUpdateDO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RaceInteractorImpl @Inject constructor(
    private val raceRepository: RaceRepository
) : RaceInteractor {

    override fun watchRaceUseCase(): Flow<RaceUpdateDO> = raceRepository.watchRace()

    override suspend fun saveRaceResultsUseCase(horses: List<Horse>) =
        raceRepository.saveRaceResults(horses)

    override suspend fun getHorseListUseCase(): List<Horse> =
        raceRepository.getHorseList()
}