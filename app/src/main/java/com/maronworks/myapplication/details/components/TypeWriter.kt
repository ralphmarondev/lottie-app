package com.maronworks.myapplication.details.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun TypeWriter(
    text: String,
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        for (i in 1 until text.length) {
            displayedText = text.substring(0, i)
            delay(100)
        }
    }

    Text(text = displayedText)
}