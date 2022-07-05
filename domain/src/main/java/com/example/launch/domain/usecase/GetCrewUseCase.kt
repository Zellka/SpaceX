package com.example.launch.domain.usecase

import com.example.launch.domain.models.Crew
import com.example.launch.domain.repository.LaunchesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetCrewUseCase(
    private val repository: LaunchesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(): Flow<List<Crew>> {
        return repository.getCrew().flowOn(dispatcher)
    }
}