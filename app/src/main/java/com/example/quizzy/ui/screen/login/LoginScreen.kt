package com.example.quizzy.ui.screen.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R
import com.example.quizzy.ui.screen.login.composbales.StyledTextField
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val loginState by viewModel.loginState.collectAsState()
//    val schoolId by viewModel.schoolId.collectAsState()
//    val studentId by viewModel.studentId.collectAsState()

    val schoolId = remember { mutableStateOf("") }
    val studentId = remember { mutableStateOf("") }


    Column (  modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        ,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(600.dp)
                    .padding(top = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                // Main image
                Image(
                    painter = painterResource(id = R.drawable.welcome),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                )

                // Right dot
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = 150.dp, y = 120.dp)
                        .background(Color.Red, CircleShape)
                )

                // Left dot
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = (-150).dp, y = 80.dp)
                        .background(Color.Red, CircleShape)
                )
            }


            WelcomeText("Welcome to\nQuizzy!")
        }


        Column(modifier = Modifier.fillMaxWidth() ) {
            LoginCard(
                schoolId,
                studentId
            ){
            }
            Spacer(Modifier.size(30.dp))
        }
    }
}

@Composable
fun WelcomeText(text: String){
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
    schoolId: MutableState<String>,
    studentId: MutableState<String>,
    onSignIn: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
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
                    value = schoolId.value,
                    onValueChange = { schoolId.value = it },
                    hint = "School ID"
                )

                Spacer(Modifier.height(12.dp))

                StyledTextField(
                    value = studentId.value,
                    onValueChange = { studentId.value = it },
                    hint = "Student ID"
                )

                // Remove Spacer here
                Spacer(Modifier.height(40.dp)) // optional small padding inside card


            }
        }

    }
}

