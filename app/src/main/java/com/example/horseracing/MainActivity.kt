package com.example.horseracing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.horseracing.ui.theme.HorseRacingTheme
import com.example.navigation_impl.AppNavigation
import com.example.race_api.RaceScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var raceScreen: RaceScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as HorseRacingApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            HorseRacingTheme {
                raceScreen.RaceContent()
                //AppNavigation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
