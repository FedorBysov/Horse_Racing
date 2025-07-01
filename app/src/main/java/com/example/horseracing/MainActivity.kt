package com.example.horseracing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.horseracing.di.AppComponent
import com.example.horseracing.ui.theme.HorseRacingTheme
import com.example.navigation_impl.AppNavigation
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as HorseRacingApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            HorseRacingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        modifier = Modifier.fillMaxSize(),
                        features = appComponent.featureApis()
                    )
                }
            }
        }
    }
}
