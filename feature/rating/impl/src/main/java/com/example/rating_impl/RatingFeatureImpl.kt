package com.example.rating_impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.rating_api.RatingFeatureApi
import com.example.rating_api.RatingRoutes
import com.example.rating_api.RatingScreen
import javax.inject.Inject

class RatingFeatureImpl @Inject constructor(
    private val ratingScreen: RatingScreen
) : RatingFeatureApi {
    override val ratingRoute = RatingRoutes.baseRoute
    
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(ratingRoute) {
            ratingScreen.RatingContent()
        }
    }
} 