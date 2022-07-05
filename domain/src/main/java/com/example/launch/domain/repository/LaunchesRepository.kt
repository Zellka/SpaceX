package com.example.launch.domain.repository

import com.example.launch.domain.models.Crew
import com.example.launch.domain.models.LaunchResponse
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {
    fun getLaunches(): Flow<LaunchResponse>
    fun getCrew(): Flow<List<Crew>>
}