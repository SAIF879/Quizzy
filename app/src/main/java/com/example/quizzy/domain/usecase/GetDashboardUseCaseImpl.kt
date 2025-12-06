package com.example.quizzy.domain.usecase

import com.example.quizzy.domain.repo.DashboardRepository
import com.example.quizzy.domain.util.DashboardResult

class GetDashboardUseCaseImpl(
    private val repository: DashboardRepository
) : GetDashboardUseCase {

    override suspend operator fun invoke(): DashboardResult {
        return repository.getDashboard()
    }
}