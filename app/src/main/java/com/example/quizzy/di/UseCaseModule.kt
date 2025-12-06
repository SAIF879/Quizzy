package com.example.quizzy.di

import com.example.quizzy.domain.usecase.GetDashboardUseCase
import com.example.quizzy.domain.usecase.GetDashboardUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetDashboardUseCase> { GetDashboardUseCaseImpl(get()) }
}