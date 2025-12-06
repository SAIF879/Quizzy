package com.example.quizzy.domain.repo

import com.example.quizzy.domain.util.DashboardResult

interface DashboardRepository {
    suspend fun getDashboard(): DashboardResult
}