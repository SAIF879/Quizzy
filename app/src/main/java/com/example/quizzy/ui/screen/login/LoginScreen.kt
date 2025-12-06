package com.example.quizzy.ui.screen.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // Log input and state changes
    LaunchedEffect(schoolId, studentId, loginState) {
        Log.d("LoginScreen", "SchoolID: $schoolId, StudentID: $studentId, State: $loginState")
    }

    // Navigate on success
    if (loginState is LoginState.Success) {
        LaunchedEffect(Unit) {
            Log.d("LoginScreen", "Login successful, navigating...")
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // dark background
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // School ID
        OutlinedTextField(
            value = schoolId,
            onValueChange = {
                viewModel.schoolId.value = it
                Log.d("LoginScreen", "SchoolID updated: $it")
            },
            label = { Text("School ID", color = Color.LightGray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = loginState !is LoginState.Loading,
            singleLine = true,
        )

        // Student ID
        OutlinedTextField(
            value = studentId,
            onValueChange = {
                viewModel.studentId.value = it
                Log.d("LoginScreen", "StudentID updated: $it")
            },
            label = { Text("Student ID", color = Color.LightGray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            enabled = loginState !is LoginState.Loading,
            singleLine = true,
        )

        // Error Message
        if (loginState is LoginState.Error) {
            Text(
                text = (loginState as LoginState.Error).message,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        // Login Button
        Button(
            onClick = {
                Log.d("LoginScreen", "Login button clicked with SchoolID=$schoolId and StudentID=$studentId")
                viewModel.login()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = loginState !is LoginState.Loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black
            )
        ) {
            if (loginState is LoginState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp,
                    color = Color.Black
                )
            } else {
                Text("Login")
            }
        }
    }
}
