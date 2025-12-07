package com.example.quizzy.ui.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.quizzy.ui.screen.login.states.LoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val loginState by viewModel.loginState.collectAsState()
    var schoolId by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }

    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            kotlinx.coroutines.delay(800)
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

        // Bottom login card
        AnimatedVisibility(
            visible = loginState !is LoginState.Success,
            modifier = Modifier.fillMaxWidth()
        ) {
            LoginCard(
                schoolId = schoolId,
                studentId = studentId,
                onSchoolIdChange = { schoolId = it },
                onStudentIdChange = { studentId = it },
                loginState = loginState,
                onLoginClick = {
                    viewModel.login(schoolId, studentId)
                }
            )
        }

        if (loginState is LoginState.Success) {
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
    loginState: LoginState,
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
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
                if (loginState is LoginState.Error) {
                    Text(
                        text = loginState.message,
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
                    enabled = loginState !is LoginState.Loading,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    if (loginState is LoginState.Loading) {
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
}
