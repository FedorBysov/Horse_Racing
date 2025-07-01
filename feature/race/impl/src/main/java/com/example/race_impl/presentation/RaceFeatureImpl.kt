package com.example.race_impl.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.race_api.RaceFeatureApi
import com.example.race_api.RaceScreen
import javax.inject.Inject

class RaceFeatureImpl @Inject constructor(
    private val raceScreen: RaceScreen
) : RaceFeatureApi {
    override val raceRoute = "race"
    
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(raceRoute) {
            raceScreen.RaceContent()
        }
    }
} 