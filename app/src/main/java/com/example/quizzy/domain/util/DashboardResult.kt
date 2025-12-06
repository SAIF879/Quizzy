package com.example.quizzy.domain.util

import com.example.quizzy.domain.model.Result

sealed class DashboardResult {
    data class Success(val result: Result) : DashboardResult()
    data class Error(val message: String) : DashboardResult()
}
