package com.example.rating_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface RatingScreen {
    @Composable
    fun RatingContent(modifier: Modifier = Modifier)
} 