package com.example.rating_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rating_impl.domain.Interactors.RatingUseCase
import com.example.storage_api.model.RaceWithResults
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class RatingViewModel @Inject constructor(
    private val ratingUseCase: RatingUseCase
) : ViewModel() {

    private val _raceHistory: StateFlow<List<RaceWithResults>> =
        ratingUseCase.getRaceHistoryUseCase().stateIn(
            viewModelScope, SharingStarted.Eagerly, emptyList()
        )
    val raceHistory: StateFlow<List<RaceWithResults>> = _raceHistory

    fun deleteHistory() =
        viewModelScope.launch {
            ratingUseCase.clearRaceHistoryUseCase()
        }
}