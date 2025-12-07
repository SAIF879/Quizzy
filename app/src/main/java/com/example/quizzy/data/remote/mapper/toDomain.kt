package com.example.quizzy.data.remote.mapper

import com.example.quizzy.data.remote.dto.*
import com.example.quizzy.domain.model.*

fun StudentDto.toDomain(): Student = Student(
    name = this.name.orEmpty(),
    classes = this.classes.orEmpty(),
    availabilityStatus = this.availability?.status.orEmpty(),
    quizAttempts = this.quiz?.attempts ?: 0,
    accuracyCurrent = this.accuracy?.current.orEmpty()
)

fun TodaySummary.toDomain(): TodaySummaryModel = TodaySummaryModel(
    mood = this.mood.orEmpty(),
    description = this.description.orEmpty(),
    recommendedVideoTitle = this.recommendedVideo?.title.orEmpty(),
    recommendedVideoActionText = this.recommendedVideo?.actionText.orEmpty(),
    characterImage = this.characterImage.orEmpty()
)

fun PerformanceByTopicDto.toDomain(): PerformanceByTopic = PerformanceByTopic(
    topic = this.topic.orEmpty(),
    trend = this.trend.orEmpty()
)

fun QuizStreakItemDto.toDomain(): QuizStreakItem = QuizStreakItem(
    day = this.day.orEmpty(),
    status = this.status.orEmpty()
)

fun WeeklyOverview.toDomain(): WeeklyOverviewModel = WeeklyOverviewModel(
    quizStreak = this.quizStreak.map { it.toDomain() },
    overallAccuracy = OverallAccuracy(
        percentage = this.overallAccuracy?.percentage ?: 0,
        label = this.overallAccuracy?.label.orEmpty()
    ),
    performanceByTopic = this.performanceByTopic.map { it.toDomain() }
)


fun ResultDto.toDomain(): Result = Result(
    student = this.student?.toDomain() ?: Student("", "", "", 0, ""),
    todaySummary = this.todaySummary?.toDomain() ?: TodaySummaryModel("", "", "", "", ""),
    weeklyOverview = this.weeklyOverview?.toDomain() ?: WeeklyOverviewModel(
        quizStreak = emptyList(),
        overallAccuracy = OverallAccuracy(percentage = 0, label = ""),
        performanceByTopic = emptyList()
    )
)
