package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentDto(
    @SerialName("name") var name: String? = null,
    @SerialName("class") var classes: String? = null, // 'class' is a reserved keyword
    @SerialName("availability") var availability: AvailabilityDto? = AvailabilityDto(),
    @SerialName("quiz") var quiz: QuizDto? = QuizDto(),
    @SerialName("accuracy") var accuracy: AccuracyDto? = AccuracyDto()
)
