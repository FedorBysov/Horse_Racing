package com.example.navigation_impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.race_api.RaceRoutes
import com.example.rating_api.RatingRoutes
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun AppNavigation(
    modifier: Modifier
) {
    val navController = rememberNavController()
    val navItems = BottomNavigationFactory.getBottomNavItems()
    var selectedRoute by rememberSaveable { mutableStateOf(navItems.first().route) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = selectedRoute == item.route,
                        onClick = {
                            selectedRoute = item.route
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        label = { Text(item.label) },
                        icon = { Icon(item.icon, contentDescription = item.label) }
                    )
                }
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = RaceRoutes.HOME
            ) {

                composable(RaceRoutes.baseRoute) {
                }

                composable(route = RatingRoutes.baseRoute) {
                }


            }
        }
    }
}