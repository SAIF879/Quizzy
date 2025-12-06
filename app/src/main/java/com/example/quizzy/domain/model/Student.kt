package com.example.quizzy.domain.model

data class Student(
    val name: String,
    val classes: String,
    val availabilityStatus: String,
    val quizAttempts: Int,
    val accuracyCurrent: String
)
