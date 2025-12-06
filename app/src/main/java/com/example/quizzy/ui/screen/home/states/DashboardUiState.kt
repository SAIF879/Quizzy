package com.example.quizzy.ui.screen.home.states

import com.example.quizzy.domain.model.Result


sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val data: Result) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}
