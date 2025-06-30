package com.example.network_api

import com.example.network_api.model.RaceHistoryDto
import kotlinx.coroutines.flow.Flow

interface HistoryApi {
    /**
     * Возвращает Flow списка предыдущих гонок.
     * Каждый элемент — одна гонка с 5 лошадями и отмеченным победителем.
     */
    fun getRaceHistory(): Flow<List<RaceHistoryDto>>
} 