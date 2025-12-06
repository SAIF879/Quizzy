package com.example.quizzy.ui.screen.home.composbales

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin


@Preview()
@Composable
fun PositiveSineWave(
    modifier: Modifier = Modifier,
    waveColor: Color = MaterialTheme.colorScheme.primary,
    amplitude: Float = 200f, // height of wave
    frequency: Float = 1f // number of waves
) {
    Canvas(modifier = modifier.height(200.dp).fillMaxWidth()) {
        val width = size.width
        val height = size.height
        val centerY = height / 2

        val points = mutableListOf<Offset>()

        val step = 1f
        for (x in 0..width.toInt()) {
            val angle = (x / width) * 2 * PI * frequency
            val y = sin(angle).toFloat()
            if (y >= 0) { // only positive sine
                points.add(Offset(x.toFloat(), centerY - y * amplitude))
            }
        }

        // Draw the sine line
        for (i in 0 until points.size - 1) {
            drawLine(
                color = waveColor,
                start = points[i],
                end = points[i + 1],
                strokeWidth = 4f
            )
        }
    }
}




