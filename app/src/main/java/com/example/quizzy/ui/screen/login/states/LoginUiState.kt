package com.example.quizzy.ui.screen.login.states
data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val user: LoginUser? = null
)

data class LoginUser(
    val schoolId: String,
    val studentId: String,
)