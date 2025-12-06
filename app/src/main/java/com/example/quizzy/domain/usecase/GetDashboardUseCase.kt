package com.example.quizzy.domain.usecase


import com.example.quizzy.domain.util.DashboardResult


interface GetDashboardUseCase {
    suspend operator fun invoke(): DashboardResult
}

