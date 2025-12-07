package com.example.quizzy.ui.screen.notification
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R
import com.example.quizzy.ui.screen.notification.composables.NotificationItem
import com.example.quizzy.ui.screen.notification.composables.SettingsItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                      Text(
                          "Notifications & Settings",
                          fontWeight = FontWeight.SemiBold
                      )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.left_nav_icons),
                            modifier = Modifier.size(28.dp),
                            contentDescription = "back_press icon",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Notifications",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            item {
                NotificationItem(
                    title = "Missed quiz in physics in yesterday",
                    timestamp = "2 hours ago",
                    indicatorColor = Color(0xFFFFA726)
                )
            }
            item {
                NotificationItem(
                    title = "Badge earned",
                    timestamp = "8 hours ago",
                    indicatorColor = Color(0xFFAB47BC)
                )
            }
            item {
                NotificationItem(
                    title = "Teacher Note",
                    timestamp = "1 day ago",
                    indicatorColor = Color(0xFF66BB6A)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Settings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                SettingsItem(
                    iconRes = R.drawable.ic_switch_child,
                    title = "Switch Child",
                    subtitle = "Change active child profile",
                    onClick = {  },
                )
            }
            item {
                SettingsItem(
                    iconRes = R.drawable.ic_language,
                    title = "Language",
                    subtitle = "English",
                    onClick = {  }
                )
            }
            item {
                SettingsItem(
                    iconRes = R.drawable.ic_log_out,
                    title = "Logout",
                    subtitle = "Sign out of your account",
                    onClick = { },
                    contentColor = Color(0xFFFF6776)
                )
            }
        }
    }
}

