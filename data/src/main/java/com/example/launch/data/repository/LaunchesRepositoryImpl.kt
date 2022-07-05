package com.example.launch.data.repository

import com.example.launch.data.api.*
import com.example.launch.domain.models.Crew
import com.example.launch.domain.models.LaunchResponse
import com.example.launch.domain.repository.LaunchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LaunchesRepositoryImpl(private val apiService: ApiService) : LaunchesRepository {

    override fun getLaunches(): Flow<LaunchResponse> {
        val request = Request(
            query = Query(date_utc = DateUTC("2021-01-01T00:00:00.000Z")),
            options = Option(limit = 100, sort = SortDate(date_utc = "desc"))
        )
        return flow {
            emit(apiService.getLaunches(request))
        }.flowOn(Dispatchers.IO)
    }


    override fun getCrew(): Flow<List<Crew>> {
        return flow {
            emit(apiService.getCrew())
        }.flowOn(Dispatchers.IO)
    }
}