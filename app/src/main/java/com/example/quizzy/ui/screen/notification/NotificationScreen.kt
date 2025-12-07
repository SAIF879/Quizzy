package com.example.quizzy.ui.screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R


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
                            contentDescription = "back_press icon"
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

@Composable
fun NotificationItem(
    title: String,
    timestamp: String,
    indicatorColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 2.dp,
                bottomEnd = 2.dp))
            .background(indicatorColor.copy(alpha = 0.1f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(indicatorColor, shape = RoundedCornerShape(2.dp))
        )
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = timestamp,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SettingsItem(
    iconRes: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    contentColor: Color = Color.Black
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = contentColor,
            modifier = Modifier.size(28.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_6")
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen()
}

