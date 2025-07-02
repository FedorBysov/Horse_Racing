package com.example.race_impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.race_api.model.Horse
import com.example.race_impl.domain.interactors.RaceInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class RaceViewModel @Inject constructor(
    private val raceInteractor: RaceInteractor
) : ViewModel() {

    //TODO() Внедрить кол-во из интернета
    private val _horses = MutableStateFlow<List<Horse>>(emptyList())
    open val horses: StateFlow<List<Horse>> = _horses.asStateFlow()

    private val _isRaceStarted = MutableStateFlow(false)
    open val isRaceStarted: StateFlow<Boolean> = _isRaceStarted.asStateFlow()

    private var finishedCount = 0

    private var raceJob: Job? = null

    private var isResultSaved = false

    init {
        viewModelScope.launch {
            val horsesFromApi = raceInteractor.getHorseListUseCase()
            _horses.value = horsesFromApi.map { dto ->
                Horse(
                    name = dto.name,
                    progress = 0f,
                    finishPosition = null
                )
            }
        }
    }


    fun resetRace() {
        finishedCount = 0
        isResultSaved = false
        raceJob?.cancel()
        _isRaceStarted.value = false
        _horses.value = _horses.value.map { horse ->
            horse.copy(progress = 0f, finishPosition = null)
        }
    }

    fun startRace() {
        Log.d("RaceViewModel", "startRace() called")
        if (_isRaceStarted.value) return
        raceJob = viewModelScope.launch {
            _isRaceStarted.value = true
            finishedCount = 0
            isResultSaved = false

            val horsesFromApi = raceInteractor.getHorseListUseCase()
            _horses.value = horsesFromApi.map { dto ->
                Horse(
                    name = dto.name,
                    progress = 0f,
                    finishPosition = null
                )
            }
            raceInteractor.watchRaceUseCase().collect { update ->
                val currentHorses = _horses.value.toMutableList()
                val index = update.horseId
                val horse = currentHorses.getOrNull(index) ?: return@collect

                if (horse.finishPosition != null) {
                    Log.d("RaceViewModel1", "Skipping horse ${horse.name} – already finished")
                    return@collect
                }

                val newProgress = update.progress.coerceIn(0f, 1f)
                var newFinishPosition = horse.finishPosition

                if (newProgress >= 1f && newFinishPosition == null) {
                    finishedCount++
                    newFinishPosition = finishedCount
                }

                Log.d("RaceViewModel", "Updating horse ${horse.name} progress=$newProgress pos=$newFinishPosition")
                currentHorses[index] = horse.copy(
                    progress = newProgress,
                    finishPosition = newFinishPosition
                )
                _horses.value = currentHorses.toList()

                if (finishedCount == currentHorses.size && !isResultSaved && currentHorses.all { it.finishPosition != null }) {
                    // Принудительно завершаем лошадей без finishPosition
                    currentHorses.forEachIndexed { idx, horse ->
                        if (horse.finishPosition == null) {
                            finishedCount++
                            currentHorses[idx] = horse.copy(finishPosition = finishedCount)
                            Log.d("RaceViewModel", "Принудительно финишировала ${horse.name}")
                        }
                    }
                    isResultSaved = true
                    Log.d("RaceViewModel", "Calling saveRaceResults()")
                    Log.d(
                        "RaceViewModel",
                        "Saving horses: " + currentHorses.joinToString { "${it.name}:${it.finishPosition}" })
                    viewModelScope.launch {
                        raceInteractor.saveRaceResultsUseCase(currentHorses.toList())
                    }
                    raceJob?.cancel()
                    return@collect
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        raceJob?.cancel()
        raceJob = null
    }
}