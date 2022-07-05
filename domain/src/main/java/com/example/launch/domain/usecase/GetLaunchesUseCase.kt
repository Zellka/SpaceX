package com.example.launch.domain.usecase

import com.example.launch.domain.models.LaunchResponse
import com.example.launch.domain.repository.LaunchesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetLaunchesUseCase(
    private val repository: LaunchesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(): Flow<LaunchResponse> {
        return repository.getLaunches().flowOn(dispatcher)
    }
}