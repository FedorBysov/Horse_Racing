package com.example.race_impl.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.race_api.RaceScreen
import javax.inject.Inject

class RaceScreenImpl @Inject constructor(
    private val viewModel: RaceViewModel
) : RaceScreen {
    
    @Composable
    override fun RaceContent() {
        val horses by viewModel.horses.collectAsState()
        val isRaceStarted by viewModel.isRaceStarted.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Лошадиные скачки",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            horses.forEach { horse ->
                HorseRaceTrack(
                    horseName = horse.name,
                    progress = horse.progress,
                    finishPosition = horse.finishPosition
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.startRace() },
                    enabled = !isRaceStarted
                ) {
                    Text("Старт")
                }

                Button(
                    onClick = { viewModel.resetRace() },
//                    enabled = isRaceStarted
                ) {
                    Text("Сброс")
                }
            }
        }
    }
}

