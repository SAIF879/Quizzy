package com.example.quizzy.di

import com.example.quizzy.ui.screen.dashboard.DashboardViewModel
import com.example.quizzy.ui.screen.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
}
