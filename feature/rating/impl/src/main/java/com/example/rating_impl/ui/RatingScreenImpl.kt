package com.example.rating_impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rating_api.RatingScreen
import javax.inject.Inject

class RatingScreenImpl @Inject constructor() : RatingScreen {
    @Composable
    override fun RatingContent(modifier: Modifier) {
        Surface(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
} 