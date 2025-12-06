package com.example.quizzy.navigation

sealed class AppRoute(val route: String) {
    data object Login : AppRoute("login")
    data object Home : AppRoute("home")
    data object Notifications : AppRoute("notifications")
}