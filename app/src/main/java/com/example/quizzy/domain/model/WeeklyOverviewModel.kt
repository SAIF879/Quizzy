package com.example.quizzy.domain.model

data class WeeklyOverviewModel(
    val quizStreak: List<Int>,
    val overallAccuracyPercentage: Int,
    val overallAccuracyLabel: String,
    val performanceByTopic: List<PerformanceByTopic>
)