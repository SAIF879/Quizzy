package com.example.quizzy.data.remote.mapper

import com.example.quizzy.data.remote.dto.PerformanceByTopicDto
import com.example.quizzy.data.remote.dto.ResultDto
import com.example.quizzy.data.remote.dto.StudentDto
import com.example.quizzy.data.remote.dto.TodaySummary
import com.example.quizzy.data.remote.dto.WeeklyOverview
import com.example.quizzy.domain.model.PerformanceByTopic
import com.example.quizzy.domain.model.Student
import com.example.quizzy.domain.model.TodaySummaryModel
import com.example.quizzy.domain.model.WeeklyOverviewModel

// StudentDto -> Student
fun StudentDto.toDomain(): Student = Student(
    name = this.name.orEmpty(),
    classes = this.classes.orEmpty(),
    availabilityStatus = this.availability?.status.orEmpty(),
    quizAttempts = this.quiz?.attempts ?: 0,
    accuracyCurrent = this.accuracy?.current.orEmpty()
)

// TodaySummary -> TodaySummaryModel
fun TodaySummary.toDomain(): TodaySummaryModel = TodaySummaryModel(
    mood = this.mood.orEmpty(),
    description = this.description.orEmpty(),
    recommendedVideoTitle = this.recommendedVideo?.title.orEmpty(),
    recommendedVideoActionText = this.recommendedVideo?.actionText.orEmpty(),
    characterImage = this.characterImage.orEmpty()
)

// PerformanceByTopicDto -> PerformanceByTopic
fun PerformanceByTopicDto.toDomain(): PerformanceByTopic = PerformanceByTopic(
    topic = this.topic.orEmpty(),
    trend = this.trend.orEmpty()
)

// WeeklyOverview -> WeeklyOverviewModel
fun WeeklyOverview.toDomain(): WeeklyOverviewModel = WeeklyOverviewModel(
    quizStreak = this.quizStreak.map { it.attempts ?: 0 },
    overallAccuracyPercentage = this.overallAccuracy?.percentage ?: 0,
    overallAccuracyLabel = this.overallAccuracy?.label.orEmpty(),
    performanceByTopic = this.performanceByTopic.map { it.toDomain() }
)

// ResultDto -> Result
fun ResultDto.toDomain(): com.example.quizzy.domain.model.Result =
    com.example.quizzy.domain.model.Result(
        student = this.student?.toDomain() ?: Student("", "", "", 0, ""),
        todaySummary = this.todaySummary?.toDomain() ?: TodaySummaryModel("", "", "", "", ""),
        weeklyOverview = this.weeklyOverview?.toDomain() ?: WeeklyOverviewModel(
            emptyList(),
            0,
            "",
            emptyList()
        )
    )
