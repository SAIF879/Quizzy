package com.example.quizzy.ui.screen.home.composbales

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quizzy.R


@Composable
fun StreakCircle(day: String, completed: Boolean) {
    val contentColor = if (completed) Color.White else Color.Gray

    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (completed) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Completed",
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
        } else {
            // Dashed circular border
            Canvas(modifier = Modifier.matchParentSize()) {
                drawCircle(
                    color = Color.LightGray,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(6.dp.toPx(), 4.dp.toPx())
                        )
                    )
                )
            }

            Text(
                text = day,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
