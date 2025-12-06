package com.example.quizzy.ui.screen.home.composbales

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quizzy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopAppBar(
    userName: String,
    className: String,
    onNotificationClick: () -> Unit = {},
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Hello $userName!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = className,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        },
        actions = {
            IconButton(
                onClick = onNotificationClick,
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        modifier = modifier
    )
}
