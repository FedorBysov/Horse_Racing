package com.example.race_api.model

/**
 * Модель данных лошади для гонки
 *
 * @property name Имя лошади
 * @property progress Текущий прогресс движения (от 0f до 1f)
 * @property finishPosition Позиция на финише (null если гонка не завершена)
 */
data class Horse(
    val name: String,
    val progress: Float,
    val finishPosition: Int? = null
) 