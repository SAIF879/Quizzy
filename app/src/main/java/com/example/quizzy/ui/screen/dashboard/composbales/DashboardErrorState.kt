package com.example.quizzy.ui.screen.dashboard.composbales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DashboardErrorState(
    message: String,
    onRetry: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF2F2))
            .padding(16.dp ,),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                tint = Color(0xFFFF4F4F),
                modifier = Modifier.size(64.dp)
            )
        }

        item {
            Text(
                text = "Oops! Something went wrong",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB91C1C),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color(0xFFB91C1C),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        item {
            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Retry")
            }
        }

        items(1) {
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}
