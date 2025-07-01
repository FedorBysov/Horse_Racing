package com.example.race_impl.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.network_api.RaceApi
import com.example.race_api.RaceScreen
import com.example.race_api.model.Horse
import javax.inject.Inject

class RaceScreenImpl @Inject constructor(
    private val raceApi: RaceApi
) : RaceScreen {
    
    @Composable
    override fun RaceContent() {
        var isRaceStarted by remember { mutableStateOf(false) }
        val horses = remember { List(5) { index ->
            mutableStateOf(Horse(
                name = "Лошадь ${index + 1}",
                progress = 0f,
                finishPosition = null
            ))
        } }
        var finishedCount by remember { mutableStateOf(0) }
        
        LaunchedEffect(isRaceStarted) {
            if (isRaceStarted) {
                finishedCount = 0
                horses.forEach { horseState ->
                    horseState.value = horseState.value.copy(
                        progress = 0f,
                        finishPosition = null
                    )
                }
                
                raceApi.watchRace().collect { update ->
                    val horseState = horses[update.horseId - 1]
                    val currentHorse = horseState.value
                    
                    // Если лошадь достигла финиша и еще не имеет позиции
                    if (update.progress >= 1f && currentHorse.finishPosition == null) {
                        finishedCount++
                        horseState.value = currentHorse.copy(
                            progress = update.progress,
                            finishPosition = finishedCount
                        )
                    } else {
                        horseState.value = currentHorse.copy(
                            progress = update.progress
                        )
                    }
                }
            }
        }

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

            horses.forEach { horseState ->
                val horse = horseState.value
                HorseRaceTrack(
                    horseName = horse.name,
                    progress = horse.progress,
                    finishPosition = horse.finishPosition
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { isRaceStarted = true },
                    enabled = !isRaceStarted
                ) {
                    Text("Старт")
                }

                Button(
                    onClick = {
                        isRaceStarted = false
                        horses.forEach { horseState ->
                            horseState.value = horseState.value.copy(
                                progress = 0f,
                                finishPosition = null
                            )
                        }
                        finishedCount = 0
                    },
                    enabled = isRaceStarted
                ) {
                    Text("Играть снова")
                }
            }
        }
    }
}

@Composable
fun RaceContent(
    raceApi: RaceApi,
    modifier: Modifier
) {
    RaceScreenImpl(raceApi).RaceContent()
} 