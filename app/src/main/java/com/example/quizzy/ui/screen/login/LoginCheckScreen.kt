package com.example.quizzy.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R
import com.example.quizzy.ui.screen.login.states.LoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginCheckScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val loginState by viewModel.loginState.collectAsState()
    var schoolId by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }

    // Navigate on success
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Top illustrative part
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_man),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .offset(x = (-80).dp, y = 20.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.image_man),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = 60.dp, y = (-20).dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.image_man),
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .offset(x = 30.dp, y = 80.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Welcome to\nQuizzy!",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 48.sp
            )
        }

        // Bottom sheet for login form
        LoginSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            schoolId = schoolId,
            onSchoolIdChange = { schoolId = it },
            studentId = studentId,
            onStudentIdChange = { studentId = it },
            loginState = loginState,
            onLoginClick = {
                viewModel.schoolId.value = schoolId
                viewModel.studentId.value = studentId
                viewModel.login()
            }
        )
    }
}

@Composable
private fun LoginSheet(
    modifier: Modifier = Modifier,
    schoolId: String,
    onSchoolIdChange: (String) -> Unit,
    studentId: String,
    onStudentIdChange: (String) -> Unit,
    loginState: LoginState,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind { // Custom top shape
                val path = Path().apply {
                    moveTo(0f, 40f)
                    quadraticBezierTo(size.width / 2, 0f, size.width, 40f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(path, color = Color.White)
            }
            .padding(top = 60.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's Get you Signed in",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // School ID Field
        OutlinedTextField(
            value = schoolId,
            onValueChange = onSchoolIdChange,
            label = { Text("School ID") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
            ),
            singleLine = true,
            enabled = loginState !is LoginState.Loading
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Student ID Field
        OutlinedTextField(
            value = studentId,
            onValueChange = onStudentIdChange,
            label = { Text("Student ID") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
            ),
            singleLine = true,
            enabled = loginState !is LoginState.Loading
        )

        // Error Message
        if (loginState is LoginState.Error) {
            Text(
                text = loginState.message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign in Button
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

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun LoginCheckScreenPreview() {
    LoginCheckScreen()
}
