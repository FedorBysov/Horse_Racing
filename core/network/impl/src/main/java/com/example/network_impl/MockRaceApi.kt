package com.example.network_impl

import com.example.network_api.RaceApi
import com.example.network_api.model.HorseDto
import com.example.network_api.model.RaceUpdateDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/**
 * Мок-реализация API гонок, которая симулирует гонку между 5 лошадьми.
 *
 * Особенности реализации:
 * - Гонка продолжается, пока все лошади не достигнут финиша
 * - Обновления приходят каждые 100мс
 * - Прогресс каждой лошади увеличивается случайно
 * - Для каждой лошади сохраняется порядковый номер прихода к финишу
 */
class MockRaceApi @Inject constructor() : RaceApi {
    /**
     * Список из 5 лошадей, которые участвуют в гонке.
     * Каждая лошадь имеет уникальный id (1-5) и имя.
     */
    private val horses = List(5) { index ->
        HorseDto(id = index, name = "Лошадь $index")
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun watchRace(): Flow<RaceUpdateDto> = flow {
        // Карта для хранения текущего прогресса каждой лошади
        val horseProgress = horses.associate { it.id to 0f }.toMutableMap()

        // Карта для хранения места каждой лошади на финише (0 - не финишировала)
        val finishPositions = horses.associate { it.id to 0 }.toMutableMap()

        // Счетчик финишировавших лошадей
        var finishedCount = 0

        // Константы для управления временем гонки
        val updateInterval = 100L // Интервал обновлений - 100мс

        // 1. Эмитим начальное состояние - все лошади на старте
        horses.forEach { horse ->
            emit(RaceUpdateDto(horse.id, 0f))
        }

        // 2. Основной цикл гонки - продолжаем, пока все не финишируют
        while (finishedCount < horses.size) {
            horses.forEach { horse ->
                // Пропускаем лошадей, которые уже финишировали
                if (finishPositions[horse.id] != 0) return@forEach

                val currentProgress = horseProgress[horse.id] ?: 0f

                // Генерируем случайное увеличение прогресса (0-2% за шаг)
                val progressStep = Random.nextFloat() * 0.02f
                // Обновляем прогресс
                val newProgress = (currentProgress + progressStep).coerceAtMost(1f)
                // Проверяем, достигла ли лошадь финиша

                horseProgress[horse.id] = newProgress
                emit(RaceUpdateDto(horse.id, newProgress))
                if (newProgress >= 1f) {
                    finishedCount++
                    finishPositions[horse.id] = finishedCount
                    horseProgress[horse.id] = 1f
                    emit(RaceUpdateDto(horse.id, 1f))
                }

                //delay(updateInterval)
            }

            // Ждём 100мс перед следующим обновлением
            delay(updateInterval)
        }

        // 3. Финальное обновление - показываем все места
        delay(200)
        horses.forEach { horse ->
            val progress = horseProgress[horse.id] ?: 0f
            if (progress < 1f) {
                horseProgress[horse.id] = 1f
                emit(RaceUpdateDto(horse.id, 1f))
            }
            if (finishPositions[horse.id] == 0) {
                finishPositions[horse.id] = ++finishedCount
                println("Принудительно финишировала ${horse.name}")
            }
        }

        // Для отладки выводим места в лог
        horses.forEach { horse ->
            println("Лошадь ${horse.name} пришла ${finishPositions[horse.id]}-й")
        }
    }.flowOn(Dispatchers.Default) // Выполняем всю работу в фоновом потоке
    .shareIn(scope, SharingStarted.WhileSubscribed(replayExpirationMillis = 0), replay = 0)
}