package com.example.quizzy.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedVideoDto(

    @SerialName("title") var title: String? = null,
    @SerialName("actionText") var actionText: String? = null

)