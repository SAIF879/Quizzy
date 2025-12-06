package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OverallAccuracyDto(

    @SerialName("percentage") var percentage: Int? = null,
    @SerialName("label") var label: String? = null

)