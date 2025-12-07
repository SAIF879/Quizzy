package com.example.quizzy.ui.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R
import com.example.quizzy.ui.screen.login.composbales.StyledTextField
import com.example.quizzy.ui.screen.login.states.LoginUiState
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    var schoolId by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }

    // Navigate when login succeeds
    LaunchedEffect(state.user) {
        if (state.user != null) {
            delay(800)
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top decorative section
        WelcomeSection()

        // Bottom login card
        AnimatedVisibility(visible = state.user == null) {
            LoginCard(
                schoolId = schoolId,
                studentId = studentId,
                onSchoolIdChange = { schoolId = it },
                onStudentIdChange = { studentId = it },
                uiState = state,
                onLoginClick = { viewModel.login(schoolId, studentId) }
            )
        }

        // Signing in UI
        if (state.user != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Signing you in...",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(16.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun WelcomeSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(600.dp)
                .padding(top = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
            )

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 150.dp, y = 120.dp)
                    .background(Color(0xFFFFDF92), CircleShape)
            )

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = (-150).dp, y = 80.dp)
                    .background(Color(0XFFDFF8FB), CircleShape)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_pie),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (-200).dp, y = 150.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_pw),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .offset(x = (600.dp * 0.1f), y = (-250).dp)
            )
        }

        WelcomeText("Welcome to\nQuizzy!")
    }
}

@Composable
fun WelcomeText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 48.sp
    )
}

@Composable
fun LoginCard(
    schoolId: String,
    studentId: String,
    onSchoolIdChange: (String) -> Unit,
    onStudentIdChange: (String) -> Unit,
    uiState: LoginUiState,
    onLoginClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Let’s Get you Signed in",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Spacer(Modifier.height(16.dp))

            StyledTextField(
                value = schoolId,
                onValueChange = onSchoolIdChange,
                hint = "School ID"
            )

            Spacer(Modifier.height(12.dp))

            StyledTextField(
                value = studentId,
                onValueChange = onStudentIdChange,
                hint = "Student ID"
            )

            Spacer(Modifier.height(24.dp))

            // Error message
            uiState.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
            }

            // Sign In button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !uiState.isLoading,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                } else {
                    Text("Sign in", fontSize = 16.sp)
                }
            }
        }
    }
}
