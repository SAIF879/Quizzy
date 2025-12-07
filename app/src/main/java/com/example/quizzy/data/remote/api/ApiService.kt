package com.example.quizzy.data.remote.api

import com.example.quizzy.data.remote.dto.ResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET(value = "v0/b/user-contacts-ade83.appspot.com/o/student_dashboard.json")
    suspend fun getDashboardData(
        @Query("alt") alt: String = "media",
        @Query("token") token: String = "0091b4c2-2ee2-4326-99cd-96d5312b34bd"
    ): Response<ResultDto>


}