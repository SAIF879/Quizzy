package com.example.quizzy.domain.model

data class TodaySummaryModel(
    val mood: String,
    val description: String,
    val recommendedVideoTitle: String,
    val recommendedVideoActionText: String,
    val characterImage: String
)