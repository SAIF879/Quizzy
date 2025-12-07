package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizStreakItemDto(
    @SerialName("day") val day: String? = null,
    @SerialName("status") val status: String? = null
)