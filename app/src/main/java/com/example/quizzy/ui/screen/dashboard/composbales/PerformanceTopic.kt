package com.example.quizzy.ui.screen.dashboard.composbales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Performance { UP, DOWN }

@Composable
fun PerformanceTopic(topic: String, performance: Performance) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = topic, fontSize = 18.sp, color = Color.Black)
        val (icon, color) = when (performance) {
            Performance.UP -> Icons.Default.KeyboardArrowUp to Color(0xFF4CAF50)
            Performance.DOWN -> Icons.Default.KeyboardArrowDown to Color.Red
        }
        Icon(
            imageVector = icon,
            contentDescription = "Performance Indicator",
            tint = color,
            modifier = Modifier.size(24.dp)
        )
    }
}
