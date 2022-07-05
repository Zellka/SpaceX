package com.example.launch.data.api

import com.example.launch.domain.models.Crew
import com.example.launch.domain.models.LaunchResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("launches/query")
    suspend fun getLaunches(@Body query: Request): LaunchResponse

    @GET("crew")
    suspend fun getCrew(): List<Crew>
}