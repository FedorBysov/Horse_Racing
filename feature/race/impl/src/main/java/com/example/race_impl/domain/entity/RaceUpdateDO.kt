package com.example.race_impl.domain.entity

data class RaceUpdateDO(
    val horseId: Int,
    val progress: Float // от 0f до 1f
)