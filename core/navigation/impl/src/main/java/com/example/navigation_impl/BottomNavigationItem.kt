package com.example.navigation_impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.race_api.RaceRoutes
import com.example.rating_api.RatingRoutes


sealed class BottomNavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Race : BottomNavigationItem(
        route = RaceRoutes.baseRoute,
        label = "Гонки",
        icon = Icons.Default.DirectionsRun
    )

    data object Rating : BottomNavigationItem(
        route = RatingRoutes.baseRoute,
        label = "История",
        icon = Icons.Default.BarChart
    )
}