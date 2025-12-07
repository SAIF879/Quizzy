package com.example.quizzy.ui.screen.dashboard.states

import com.example.quizzy.domain.model.Result


sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val data: Result) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}
