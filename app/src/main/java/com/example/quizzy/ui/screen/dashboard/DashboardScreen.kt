package com.example.quizzy.ui.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R
import com.example.quizzy.ui.screen.dashboard.composbales.DashboardErrorState
import com.example.quizzy.ui.screen.dashboard.composbales.DashboardTopAppBar
import com.example.quizzy.ui.screen.dashboard.composbales.ShimmerLoadingScreen
import com.example.quizzy.ui.screen.dashboard.composbales.SingleStatCard
import com.example.quizzy.ui.screen.dashboard.composbales.WatchCard
import com.example.quizzy.ui.screen.dashboard.composbales.WeeklyOverviewCard
import com.example.quizzy.ui.screen.dashboard.states.DashboardUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    onNotificationsClick: () -> Unit = {}
) {
    val viewModel: DashboardViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    when (state) {
        is DashboardUiState.Loading -> {
            ShimmerLoadingScreen()
        }

        is DashboardUiState.Error -> {
            val message = (state as DashboardUiState.Error).message
            DashboardErrorState(message)
        }

        is DashboardUiState.Success -> {
          val result = (state as DashboardUiState.Success).data
            val student = result.student
            val todaySummary = result.todaySummary
            val weeklyOverview = result.weeklyOverview
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    DashboardTopAppBar(
                        userName = student.name,
                        className = student.classes,
                        onNotificationClick = {onNotificationsClick.invoke()}
                    )
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            SingleStatCard(
                                title = "Availability",
                                value = student.availabilityStatus,
                                iconRes = R.drawable.ic_person,
                                color = Color(0xFF22C55D),
                                modifier = Modifier.weight(1f)
                            )
                            SingleStatCard(
                                title = "Quiz",
                                value = "${student.quizAttempts} Attempt",
                                iconRes = R.drawable.ic_quiz,
                                color = Color(0xFFFE9C3B),
                                modifier = Modifier.weight(1f)
                            )
                            SingleStatCard(
                                title = "Accuracy",
                                value = student.accuracyCurrent,
                                iconRes = R.drawable.ic_accuracy,
                                color = Color(0xFFFF4F4F),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    item {
                        Text(
                            text = "Today’s Summary",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                        item {
                            WatchCard(
                                mood = todaySummary.mood,
                                description = todaySummary.description,
                                actionText = todaySummary.recommendedVideoActionText,
                            )
                    }
                    item {
                        Text(
                            text = "Weekly Overview",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        WeeklyOverviewCard(
                            weeklyOverview = weeklyOverview,
                        )
                    }

                }
            }
        }
    }
}

