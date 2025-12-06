package com.example.quizzy.ui.screen.login.states

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(
        val schoolId: String,
        val studentId: String,
        val uid: String
    ) : LoginState()

    data class Error(val message: String) : LoginState()
}

