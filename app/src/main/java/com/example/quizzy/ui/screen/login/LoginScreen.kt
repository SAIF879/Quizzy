package com.example.quizzy.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
      Column(modifier = Modifier.fillMaxWidth() , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
          Image(
              painter = painterResource(id = R.drawable.welcome),
              contentDescription = null,
              modifier = Modifier
                  .size(400.dp)
                  .clip(CircleShape)
          )

          WelcomeText("Welcome to\nQuizzy!")
      }
        Column(modifier = Modifier.fillMaxWidth() ) {
            LoginCard(
                modifier = Modifier.fillMaxWidth(),
                schoolId,
                studentId
            )

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
    modifier: Modifier = Modifier,
    schoolId: MutableState<String>,
    studentId: MutableState<String>,
) {
    Card(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
            .wrapContentHeight()              // <<< IMPORTANT
            .widthIn(max = 420.dp),           // prevents huge card on large screens
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight(),         // <<< ensures no forced large height
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Let’s Get you Signed in",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            StyledTextField(
                value = schoolId.value,
                onValueChange = { schoolId.value = it },
                hint = "School ID"
            )

            StyledTextField(
                value = studentId.value,
                onValueChange = { studentId.value = it },
                hint = "Student ID"
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}
