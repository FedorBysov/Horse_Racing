package com.example.rating_impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rating_api.RatingScreen
import javax.inject.Inject

class RatingScreenImpl @Inject constructor(
    private val viewModel: RatingViewModel
) : RatingScreen {
    @Composable
    override fun RatingContent() {

        val raceHistory by viewModel.raceHistory.collectAsState()

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(raceHistory) { race ->
                        CardInfoItem(raceWithResults = race)
                    }
                }

                Button(
                    onClick = { viewModel.deleteHistory() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Очистить историю")
                }
            }
        }
    }
}