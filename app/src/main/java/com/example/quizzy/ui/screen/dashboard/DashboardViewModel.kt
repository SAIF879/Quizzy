package com.example.quizzy.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzy.domain.usecase.GetDashboardUseCase
import com.example.quizzy.domain.util.DashboardResult
import com.example.quizzy.ui.screen.dashboard.states.DashboardUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DashboardViewModel(
    private val getDashboardUseCase: GetDashboardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val state: StateFlow<DashboardUiState> = _state

    init {
        fetchDashboard()
    }

    private fun fetchDashboard() {
        viewModelScope.launch {
            _state.value = DashboardUiState.Loading
            val result = withContext(Dispatchers.IO) {
                getDashboardUseCase()
            }
            _state.value = when (result) {
                is DashboardResult.Success -> DashboardUiState.Success(result.result)
                is DashboardResult.Error -> DashboardUiState.Error(result.message)
            }
        }
    }

}
