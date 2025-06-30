package com.example.network_api.model

data class RaceUpdateDto(
    val horseId: Int,
    val progress: Float // от 0f до 1f
) 