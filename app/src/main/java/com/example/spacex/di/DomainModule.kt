package com.example.spacex.di

import com.example.launch.domain.repository.LaunchesRepository
import com.example.launch.domain.usecase.GetCrewUseCase
import com.example.launch.domain.usecase.GetLaunchesUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class DomainModule {

    @Provides
    fun provideGetLaunchesUseCase(repository: LaunchesRepository): GetLaunchesUseCase {
        return GetLaunchesUseCase(repository, Dispatchers.IO)
    }

    @Provides
    fun provideGetCrewUseCase(repository: LaunchesRepository): GetCrewUseCase {
        return GetCrewUseCase(repository, Dispatchers.IO)
    }
}