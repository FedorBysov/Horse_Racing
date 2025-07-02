package com.example.network_api

import com.example.network_api.model.RaceUpdateDto
import kotlinx.coroutines.flow.Flow

interface RaceApi {
    /**
     * Запускает сессию гонки и возвращает Flow<RaceUpdateDto>.
     * Гонка идёт ровно 10 секунд, обновления эмитятся примерно каждые 100 мс.
     */
    fun watchRace(): Flow<RaceUpdateDto>
} 