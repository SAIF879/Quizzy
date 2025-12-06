package com.example.quizzy.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quizzy.R
import com.example.quizzy.ui.screen.home.composbales.DashboardTopAppBar
import com.example.quizzy.ui.screen.home.composbales.SingleStatCard
import com.example.quizzy.ui.screen.home.states.DashboardUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen() {
    val viewModel: DashboardViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    when (state) {
        is DashboardUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DashboardUiState.Error -> {
            val message = (state as DashboardUiState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: $message", color = Color.Red)
            }
        }

        is DashboardUiState.Success -> {
            val student = (state as DashboardUiState.Success).data.student

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    DashboardTopAppBar(
                        userName = student.name,
                        className = student.classes,
                        onNotificationClick = {}
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState()) // Added scroll if needed
                ) {
                    // Stats Cards Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp) // Increased spacing
                    ) {
                        // Cards will automatically fill available space equally using weight
                        SingleStatCard(
                            title = "Availability",
                            value = student.availabilityStatus,
                            iconRes = R.drawable.ic_person,
                            color = Color(0xFF22C55D),
                            modifier = Modifier.weight(1f) // Apply weight here
                        )
                        SingleStatCard(
                            title = "Quiz",
                            value = "${student.quizAttempts} Attempt",
                            iconRes = R.drawable.ic_quiz,
                            color = Color(0xFFFE9C3B),
                            modifier = Modifier.weight(1f), // Apply weight here
                            valueColor = Color.Black
                        )
                        SingleStatCard(
                            title = "Accuracy",
                            value = student.accuracyCurrent,
                            iconRes = R.drawable.ic_accuracy,
                            color = Color(0xFFFF4F4F),
                            modifier = Modifier.weight(1f), // Apply weight here
                            valueColor = Color.Black
                        )
                    }

                    // Add more dashboard content here if needed
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun TopAppBar(){

}

@Composable
fun Frames(){

}

@Composable
fun FocusedCard(){

}

@Composable
fun QuizStreakBox(){

}