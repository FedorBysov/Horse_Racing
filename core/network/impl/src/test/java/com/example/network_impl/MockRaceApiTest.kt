package com.example.network_impl

import app.cash.turbine.test
import com.example.network_api.model.RaceUpdateDto
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class MockRaceApiTest {
    private val mockRaceApi = MockRaceApi()

    @Test
    fun `watchRace emits updates for approximately 10 seconds and one horse reaches finish`() = runTest {
        mockRaceApi.watchRace().test {
            val startTime = System.currentTimeMillis()
            val updates = mutableListOf<RaceUpdateDto>()

            while (!awaitComplete()) {
                updates.add(awaitItem())
            }

            val duration = System.currentTimeMillis() - startTime
            
            // Проверяем длительность гонки (10 секунд +/- 500мс)
            assertTrue(duration in 9500..10500, "Race duration was $duration ms")

            // Получаем последние обновления для каждой лошади
            val finalStates = updates.groupBy { it.horseId }
                .mapValues { (_, updates) -> updates.last().progress }

            // Проверяем, что ровно одна лошадь достигла финиша
            assertEquals(1, finalStates.values.count { it == 1f },
                "Expected exactly one horse to reach finish line")

            // Проверяем, что все остальные лошади не достигли финиша
            assertTrue(finalStates.values.count { it < 1f } == 4,
                "Expected 4 horses to not reach finish line")
        }
    }
} 