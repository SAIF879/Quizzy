package com.example.quizzy.navigation

sealed class AppRoute(val route: String) {
    data object Login : AppRoute("login")
    data object Dashboard : AppRoute("home")
    data object Notifications : AppRoute("notifications")
}