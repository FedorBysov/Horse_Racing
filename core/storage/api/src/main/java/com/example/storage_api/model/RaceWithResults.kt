package com.example.storage_api.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.storage_api.entity.HorseResultEntity
import com.example.storage_api.entity.RaceEntity

data class RaceWithResults(
    @Embedded
    val race: RaceEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "raceId"
    )
    val results: List<HorseResultEntity>
) 