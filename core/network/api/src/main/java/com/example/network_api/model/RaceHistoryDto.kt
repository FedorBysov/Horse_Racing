package com.example.network_api.model

data class RaceHistoryDto(
    val raceId: Int,
    val horses: List<HorseDto>,      // всегда 5 лошадей
    val winnerHorseId: Int,          // id победителя
    val timestamp: Long              // millis epoch
) 