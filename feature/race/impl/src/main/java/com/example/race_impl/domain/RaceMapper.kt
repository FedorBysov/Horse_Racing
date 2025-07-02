package com.example.race_impl.domain

import com.example.network_api.model.HorseDto
import com.example.network_api.model.RaceUpdateDto
import com.example.race_api.model.Horse
import com.example.race_impl.domain.entity.RaceResultDO
import com.example.race_impl.domain.entity.RaceUpdateDO
import com.example.storage_api.entity.HorseResultEntity
import com.example.storage_api.entity.RaceEntity


fun List<Horse>.toRaceResultDomainList(): List<RaceResultDO> {
    return this.mapNotNull { horse ->
        horse.finishPosition?.let { position ->
            RaceResultDO(
                name = horse.name,
                position = position
            )
        }
    }
}

fun List<RaceResultDO>.toDataBaseObject(): Pair<RaceEntity, List<HorseResultEntity>> {

    val race = RaceEntity()
    return race to this.map { resultRace ->
        HorseResultEntity(
            raceId = 0, // ID будет установлен после вставки RaceEntity
            horseName = resultRace.name,
            finishPosition = resultRace.position
        )
    }
}

fun RaceUpdateDto.toDomainObject(): RaceUpdateDO = RaceUpdateDO(
    horseId = this.horseId,
    progress = this.progress
)

fun List<HorseDto>.toDomainObject(): List<Horse> = this.map { horse ->
    Horse(
        name = horse.name,
        progress = 0f
    )
}





