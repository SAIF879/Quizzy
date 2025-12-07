package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailabilityDto(
    @SerialName("status") var status: String? = null
)