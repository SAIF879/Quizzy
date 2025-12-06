package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeeklyOverview(

    @SerialName("quizStreak") var quizStreak: ArrayList<QuizDto> = arrayListOf(),
    @SerialName("overallAccuracy") var overallAccuracy: OverallAccuracyDto? = OverallAccuracyDto(),
    @SerialName("performanceByTopic") var performanceByTopic: ArrayList<PerformanceByTopicDto> = arrayListOf()

)