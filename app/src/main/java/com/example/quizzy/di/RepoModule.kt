package com.example.quizzy.di

import com.example.quizzy.data.remote.repoImpl.DashboardRepositoryImpl
import com.example.quizzy.domain.repo.DashboardRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DashboardRepository> { DashboardRepositoryImpl(get()) }
}