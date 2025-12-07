package com.example.quizzy.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzy.ui.screen.login.states.LoginUiState
import com.example.quizzy.ui.screen.login.states.LoginUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun login(schoolId: String, studentId: String) {
        val school = schoolId.trim()
        val student = studentId.trim()
        if (school.isEmpty() || student.isEmpty()) {
            _state.value = LoginUiState(errorMessage = "Please enter School ID and Student ID")
            return
        }

        viewModelScope.launch {
            _state.value = LoginUiState(isLoading = true)
            runCatching { firebaseAuth.signInAnonymously().await() }
                .onSuccess { result ->
                    val user = result.user
                    if (user != null) {
                        _state.value = LoginUiState(user = LoginUser(school, student, user.uid))
                    } else {
                        _state.value = LoginUiState(errorMessage = "Login failed: user is null")
                    }
                }
                .onFailure { e ->
                    _state.value = LoginUiState(errorMessage = "Login failed: ${e.message}")
                }
        }
    }

}

