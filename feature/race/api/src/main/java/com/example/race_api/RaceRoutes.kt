package com.example.race_api

import com.example.navigation_api.FeatureRoutes

object RaceRoutes:FeatureRoutes {

    private const val BASE_ROUTE = "race"
    override val baseRoute: String = BASE_ROUTE

    /**
     * Маршрут главного экрана.
     * Это простой маршрут без параметров, используемый как
     * стартовый экран приложения.
     */
    const val HOME = BASE_ROUTE

}