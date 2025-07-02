package com.example.network_impl

import com.example.network_api.HistoryApi
import com.example.network_api.model.HorseDto
import com.example.network_api.model.RaceHistoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

/**
 * Мок-реализация API истории гонок.
 * 
 * Особенности реализации:
 * - Возвращает фиксированный список из 10 прошедших гонок
 * - В каждой гонке участвуют те же 5 лошадей
 * - Гонки распределены по времени с интервалом в 1 минуту
 * - В каждой гонке случайно выбран победитель
 */
class MockHistoryApi @Inject constructor() : HistoryApi {
    /**
     * Список лошадей, идентичный списку в MockRaceApi.
     * Обеспечивает консистентность данных между API.
     */
    private val horses = List(5) { index ->
        HorseDto(id = index + 1, name = "Лошадь ${index + 1}")
    }

    override fun getRaceHistory(): Flow<List<RaceHistoryDto>> = flow {
        val currentTime = System.currentTimeMillis()
        
        // Генерируем список из 10 прошедших гонок
        val history = List(10) { index ->
            RaceHistoryDto(
                raceId = index + 1, // Уникальный ID гонки
                horses = horses,    // Все 5 лошадей участвовали в каждой гонке
                winnerHorseId = horses.random().id, // Случайный победитель
                // Временная метка: каждая предыдущая гонка на минуту раньше
                timestamp = currentTime - (index * 60_000L)
            )
        }
        
        // Эмитим весь список за один раз
        emit(history)
    }
} 