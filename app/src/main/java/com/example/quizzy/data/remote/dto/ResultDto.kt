package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("student") var student: StudentDto? = StudentDto(),
    @SerialName("todaySummary") var todaySummary: TodaySummary? = TodaySummary(),
    @SerialName("weeklyOverview") var weeklyOverview: WeeklyOverview? = WeeklyOverview()
)