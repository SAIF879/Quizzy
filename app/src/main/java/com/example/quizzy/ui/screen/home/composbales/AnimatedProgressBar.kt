package com.example.quizzy.ui.screen.home.composbales

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedProgressBar(percentage: Int) {

    // Convert to 0f–1f range
    val targetProgress = (percentage / 100f).coerceIn(0f, 1f)

    var startAnimation by remember { mutableStateOf(false) }

    // Re-animate whenever percentage changes
    LaunchedEffect(percentage) {
        startAnimation = true
    }

    val animatedProgress by animateFloatAsState(
        targetValue = if (startAnimation) targetProgress else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        color =Color(0XFFFF6776),
        trackColor = Color.Red.copy(alpha = 0.2f),
        strokeCap = StrokeCap.Round,
        gapSize = (-8).dp,
        drawStopIndicator = {}
    )
}
