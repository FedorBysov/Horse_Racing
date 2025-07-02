package com.example.rating_impl.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storage_api.model.RaceWithResults

@Composable
fun CardInfoItem(raceWithResults: RaceWithResults) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            val formattedDate = java.text.SimpleDateFormat("dd.MM.yyyy HH:mm")
                .format(java.util.Date(raceWithResults.race.timestamp))

            Text(
                text = "Заезд: $formattedDate",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(Modifier.padding(vertical = 8.dp))

            raceWithResults.results.sortedBy { it.finishPosition }.forEach { result ->
                Text(
                    text = "Место: ${result.finishPosition}. Имя: ${result.horseName}",
                    fontSize = 14.sp
                )
            }
        }
    }
}

