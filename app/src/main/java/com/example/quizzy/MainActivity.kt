package com.example.quizzy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quizzy.navigation.AppNavGraph
import com.example.quizzy.ui.screen.home.DashboardScreen
import com.example.quizzy.ui.screen.login.LoginCheckScreen
import com.example.quizzy.ui.screen.login.LoginScreen
import com.example.quizzy.ui.screen.notification.NotificationScreen
import com.example.quizzy.ui.theme.QuizzyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizzyTheme {
                AppNavGraph()
            }
        }
    }
}
