package com.example.quizzy.ui.screen.home.composbales

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R
import com.example.quizzy.domain.model.QuizStreakItem
import com.example.quizzy.domain.model.WeeklyOverviewModel

@Composable
fun WeeklyOverviewCard(
    weeklyOverview: WeeklyOverviewModel,
    modifier: Modifier = Modifier
) {
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
        SectionHeader(title = "Quiz Streak", imageRes = R.drawable.ic_question_card)

        QuizStreak(streakItems = weeklyOverview.quizStreak)

        SectionHeader(title = "Accuracy", imageRes = R.drawable.ic_target)

        Text(
            text = weeklyOverview.overallAccuracy.label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(4.dp))
        AnimatedProgressBar(percentage = weeklyOverview.overallAccuracy.percentage)
        Spacer(modifier = Modifier.height(8.dp))


        SectionHeader(title = "Performance by Topic", imageRes = R.drawable.ic_bar_graph)

        weeklyOverview.performanceByTopic.forEach { item ->
            PerformanceTopic(
                topic = item.topic,
                performance = if (item.trend == "up") Performance.UP else Performance.DOWN
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
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
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(44.dp)
            )
        }

        CustomHorizontalDivider()

    }
}



@Composable
private fun QuizStreak(streakItems: List<QuizStreakItem>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        streakItems.forEach { item ->
            StreakCircle(
                day = item.day,
                completed = item.status == "done"
            )
        }
    }
}




