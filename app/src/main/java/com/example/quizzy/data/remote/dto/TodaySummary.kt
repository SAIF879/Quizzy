package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodaySummary(

    @SerialName("mood") var mood: String? = null,
    @SerialName("description") var description: String? = null,
    @SerialName("recommendedVideo") var recommendedVideo: RecommendedVideoDto? = RecommendedVideoDto(),
    @SerialName("characterImage") var characterImage: String? = null

)