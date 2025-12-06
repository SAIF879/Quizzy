package com.example.quizzy.ui.screen.home.composbales

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R // Make sure to import your R file

@Composable
fun WeeklyOverviewCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- Quiz Streak Section ---
        SectionHeader(title = "Quiz Streak", imageRes = R.drawable.ic_quiz)
        QuizStreak()
        Divider(color = Color.LightGray)

        // --- Accuracy Section ---
        SectionHeader(title = "Accuracy", imageRes = R.drawable.ic_accuracy)
        Text(text = "68% correct", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { 0.68f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.Red,
            trackColor = Color.Red.copy(alpha = 0.2f),
            strokeCap = StrokeCap.Round
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray)

        // --- Performance by Topic Section ---
        SectionHeader(title = "Performance by Topic", imageRes = R.drawable.ic_bar_graph)
        PerformanceTopic("Newton's Laws Of Motion", Performance.UP)
        PerformanceTopic("Sources Of Energy", Performance.UP)
        PerformanceTopic("Light Reflection And Refraction", Performance.DOWN)
        Spacer(modifier = Modifier.height(16.dp))

        // --- More Details Button ---
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "More Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun SectionHeader(title: String, imageRes: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        // Replace with your actual drawable
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun QuizStreak() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StreakCircle(day = "M", completed = true)
        StreakCircle(day = "T", completed = true)
        StreakCircle(day = "W", completed = true)
        StreakCircle(day = "T", completed = true)
        StreakCircle(day = "F", completed = false)
        StreakCircle(day = "S", completed = false)
        StreakCircle(day = "S", completed = false)
    }
}

@Composable
private fun StreakCircle(day: String, completed: Boolean) {
    val backgroundColor = if (completed) Color(0xFF4CAF50) else Color.Transparent
    val contentColor = if (completed) Color.White else Color.Gray
    val border = if (completed) null else Modifier.border(1.dp, Color.LightGray, CircleShape)

    Box(
        modifier = Modifier
            .size(32.dp)
            .then(border ?: Modifier)
            .background(backgroundColor, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (completed) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Completed",
                tint = contentColor,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(text = day, color = contentColor, fontWeight = FontWeight.Bold)
        }
    }
}

enum class Performance { UP, DOWN }

@Composable
private fun PerformanceTopic(topic: String, performance: Performance) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = topic, fontSize = 16.sp, color = Color.Gray)
        val (icon, color) = when (performance) {
            Performance.UP -> Icons.Default.KeyboardArrowUp to Color(0xFF4CAF50) // Green
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

@Preview(showBackground = true)
@Composable
private fun WeeklyOverviewCardPreview() {
    WeeklyOverviewCard(modifier = Modifier.padding(16.dp))
}