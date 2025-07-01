package com.example.race_impl.ui

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.network_api.RaceApi
import com.example.race_api.RaceFeatureApi
import javax.inject.Inject

class RaceFeatureImpl @Inject constructor(
    private val raceApi: RaceApi
) : RaceFeatureApi {
    override val raceRoute = "race"
    
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(raceRoute) {
            RaceContent(
                raceApi = raceApi,
                modifier = modifier
            )
        }
    }
} 