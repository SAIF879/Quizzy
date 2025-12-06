package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PerformanceByTopicDto(

    @SerialName("topic") var topic: String? = null,
    @SerialName("trend") var trend: String? = null

)