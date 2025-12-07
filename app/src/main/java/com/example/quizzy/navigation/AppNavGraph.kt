package com.example.quizzy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizzy.ui.screen.dashboard.DashboardScreen
import com.example.quizzy.ui.screen.login.LoginScreen
import com.example.quizzy.ui.screen.notification.NotificationScreen

@Composable
fun AppNavGraph(startDestination: String = AppRoute.Login.route) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppRoute.Login.route) {
//            LoginCheckScreen(
//                onLoginSuccess = {
//                    navController.navigate(AppRoute.Home.route) {
//                        popUpTo(AppRoute.Login.route) { inclusive = true }
//                    }
//                }
//            )
            LoginScreen()
        }

        composable(AppRoute.Dashboard.route) {
            DashboardScreen(
                onNotificationsClick = {
                    navController.navigate(AppRoute.Notifications.route)
                }
            )
        }

        composable(AppRoute.Notifications.route) {
            NotificationScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
