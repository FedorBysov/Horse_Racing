package com.example.storage_api.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "horse_results",
    foreignKeys = [
        ForeignKey(
            entity = RaceEntity::class,
            parentColumns = ["id"],
            childColumns = ["raceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("raceId")],
    primaryKeys = ["raceId", "horseName"]
)
data class HorseResultEntity(
    val raceId: Long,
    val horseName: String,
    val finishPosition: Int
) 