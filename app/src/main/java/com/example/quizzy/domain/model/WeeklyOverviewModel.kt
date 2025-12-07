package com.example.quizzy.domain.model

data class WeeklyOverviewModel(
    val quizStreak: List<QuizStreakItem>,
    val overallAccuracy: OverallAccuracy,
    val performanceByTopic: List<PerformanceByTopic>
)
