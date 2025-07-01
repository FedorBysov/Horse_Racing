package com.example.race_impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.race_api.model.Horse
import com.example.race_impl.domain.interactors.RaceUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RaceViewModel @Inject constructor(
    private val raceUseCase: RaceUseCase
) : ViewModel() {

    private val _horses = MutableStateFlow<List<Horse>>(List(5) { index ->
        Horse(
            name = "Лошадь ${index + 1}",
            progress = 0f,
            finishPosition = null
        )
    })
    val horses: StateFlow<List<Horse>> = _horses.asStateFlow()

    private val _isRaceStarted = MutableStateFlow(false)
    val isRaceStarted: StateFlow<Boolean> = _isRaceStarted.asStateFlow()

    private var finishedCount = 0

    private var raceJob: Job? = null

    private var isResultSaved = false


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
        resetRace()
        Log.d("RaceViewModel1", "Starting race")
        raceJob = viewModelScope.launch {
            _isRaceStarted.value = true
            finishedCount = 0
            isResultSaved = false
            raceUseCase.watchRaceUseCase().collect { update ->
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
                    _isRaceStarted.value = false
                    Log.d("RaceViewModel", "Calling saveRaceResults()")
                    Log.d(
                        "RaceViewModel",
                        "Saving horses: " + currentHorses.joinToString { "${it.name}:${it.finishPosition}" })
                    viewModelScope.launch {
                        raceUseCase.saveRaceResults(currentHorses.toList())
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