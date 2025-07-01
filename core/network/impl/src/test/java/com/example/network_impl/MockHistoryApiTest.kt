//package com.example.network_impl
//
//import app.cash.turbine.test
//import kotlinx.coroutines.test.runTest
//import org.junit.Test
//import kotlin.test.assertEquals
//import kotlin.test.assertTrue
//
//class MockHistoryApiTest {
//    private val mockHistoryApi = MockHistoryApi()
//
//    @Test
//    fun `getRaceHistory returns valid history list`() = runTest {
//        mockHistoryApi.getRaceHistory().test {
//            val historyList = awaitItem()
//
//            // Проверяем, что список не пустой
//            assertTrue(historyList.isNotEmpty(), "History list should not be empty")
//
//            historyList.forEach { raceHistory ->
//                // Проверяем, что в каждой гонке ровно 5 лошадей
//                assertEquals(5, raceHistory.horses.size,
//                    "Each race should have exactly 5 horses")
//
//                // Проверяем, что победитель есть в списке лошадей
//                assertTrue(raceHistory.horses.any { it.id == raceHistory.winnerHorseId },
//                    "Winner horse should be in the race participants")
//
//                // Проверяем корректность id лошадей (1-5)
//                assertTrue(raceHistory.horses.all { it.id in 1..5 },
//                    "Horse IDs should be in range 1..5")
//            }
//
//            // Проверяем сортировку по времени (от новых к старым)
//            assertTrue(historyList.zipWithNext { a, b -> a.timestamp > b.timestamp }.all { it },
//                "Races should be sorted by timestamp descending")
//
//            awaitComplete()
//        }
//    }
//}