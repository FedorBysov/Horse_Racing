package com.example.race_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.race_api.RaceScreen
import com.example.race_api.model.Horse
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

            HorseList(horses)

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
                ) {
                    Text("Сброс")
                }
            }
        }
    }
}

@Composable
fun HorseList(horses: List<Horse>) {
    Column {
        horses.forEach { horse ->
            HorseRaceTrack(
                horseName = horse.name,
                progress = horse.progress,
                finishPosition = horse.finishPosition
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun PreviewHorseList() {
    HorseList(
        listOf(
            Horse("Лошадь 1", 0.8f, 1),
            Horse("Лошадь 2", 0.6f, 2),
            Horse("Лошадь 3", 0.4f, null),
            Horse("Лошадь 4", 0.2f, null),
            Horse("Лошадь 5", 1f, 3),
        )
    )
}

