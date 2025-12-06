package com.example.quizzy.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizzy.ui.screen.login.states.LoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val loginState by viewModel.loginState.collectAsState()
    val schoolId by viewModel.schoolId.collectAsState()
    val studentId by viewModel.studentId.collectAsState()

    // Navigate on success
    if (loginState is LoginState.Success) {
        LaunchedEffect(Unit) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // School ID
        OutlinedTextField(
            value = schoolId,
            onValueChange = { viewModel.schoolId.value = it },
            label = { Text("School ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = loginState !is LoginState.Loading,
            singleLine = true
        )

        // Student ID
        OutlinedTextField(
            value = studentId,
            onValueChange = { viewModel.studentId.value = it },
            label = { Text("Student ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            enabled = loginState !is LoginState.Loading,
            singleLine = true
        )

        // Error Message
        if (loginState is LoginState.Error) {
            Text(
                text = (loginState as LoginState.Error).message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        // Login Button
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = loginState !is LoginState.Loading
        ) {
            if (loginState is LoginState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Login")
            }
        }
    }
}
