package com.example.quizzy.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzy.ui.screen.login.states.LoginState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(schoolId: String, studentId: String) {
        val school = schoolId.trim()
        val student = studentId.trim()

        if (school.isEmpty() || student.isEmpty()) {
            _loginState.value = LoginState.Error("Please enter School ID and Student ID")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            runCatching {
                firebaseAuth.signInAnonymously().await()
            }.onSuccess { result ->
                val user = result.user
                if (user != null) {
                    _loginState.value = LoginState.Success(
                        schoolId = school,
                        studentId = student,
                        uid = user.uid
                    )
                } else {
                    _loginState.value = LoginState.Error("Login failed: user is null")
                }
            }.onFailure { e ->
                _loginState.value = LoginState.Error("Login failed: ${e.message}")
            }
        }
    }

    fun reset() {
        _loginState.value = LoginState.Idle
    }
}

