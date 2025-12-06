package com.example.quizzy.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quizzy.ui.screen.home.states.DashboardUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(

) {
    // Collect state from ViewModel
    val viewModel: DashboardViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    // Handle state changes
    when (state) {
        is DashboardUiState.Loading -> {
            Log.d("DashboardScreen", "Loading dashboard data...")
        }
        is DashboardUiState.Success -> {
            val data = (state as DashboardUiState.Success).data
            Log.d("DashboardScreen", "Dashboard Success: $data")
        }
        is DashboardUiState.Error -> {
            val message = (state as DashboardUiState.Error).message
            Log.e("DashboardScreen", "Dashboard Error: $message")
        }
    }

    // Minimal UI placeholder
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Dashboard Screen (Logging only)")
    }
}
