package com.example.quizzy.domain.model

data class Result(
    val student: Student,
    val todaySummary: TodaySummaryModel,
    val weeklyOverview: WeeklyOverviewModel
)