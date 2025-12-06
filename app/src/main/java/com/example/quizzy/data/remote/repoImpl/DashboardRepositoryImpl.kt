package com.example.quizzy.data.remote.repoImpl

import com.example.quizzy.data.remote.api.ApiService
import com.example.quizzy.data.remote.mapper.toDomain
import com.example.quizzy.data.remote.util.safeApiCall
import com.example.quizzy.domain.repo.DashboardRepository
import com.example.quizzy.domain.util.DashboardResult

// data/repository/DashboardRepositoryImpl.kt
class DashboardRepositoryImpl(
    private val apiService: ApiService
) : DashboardRepository {

    override suspend fun getDashboard(): DashboardResult {
        return safeApiCall(
            apiCall = { apiService.getDashboardData() },
            onSuccess = { dto -> DashboardResult.Success(dto.toDomain()) },
            onError = { message -> DashboardResult.Error(message) }
        )
    }
}
