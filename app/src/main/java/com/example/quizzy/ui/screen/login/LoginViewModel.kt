package com.example.quizzy.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzy.ui.screen.login.states.LoginState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    var schoolId = MutableStateFlow("")
    var studentId = MutableStateFlow("")

    fun login() {
        val school = schoolId.value.trim()
        val student = studentId.value.trim()

        if (school.isEmpty() || student.isEmpty()) {
            _loginState.value = LoginState.Error("Please enter School ID and Student ID")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            runCatching {
                withContext(Dispatchers.IO) {
                    firebaseAuth.signInAnonymously().await()
                }
            }.onSuccess { result ->
                val user = result.user

                if (user != null) {
                    Log.d("LoginVM", "School ID: $school")
                    Log.d("LoginVM", "Student ID: $student")
                    Log.d("LoginVM", "Firebase UID: ${user.uid}")

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

