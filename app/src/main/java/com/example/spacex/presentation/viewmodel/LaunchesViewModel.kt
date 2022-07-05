package com.example.spacex.presentation.viewmodel

import androidx.lifecycle.*
import com.example.launch.domain.models.Crew
import com.example.launch.domain.models.LaunchResponse
import com.example.launch.domain.usecase.GetCrewUseCase
import com.example.launch.domain.usecase.GetLaunchesUseCase

class LaunchesViewModel(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val getCrewUseCase: GetCrewUseCase
) : ViewModel() {

    private var launchList: LiveData<LaunchResponse>
    private var crewList: LiveData<List<Crew>>

    init {
        launchList = getLaunchesUseCase.execute().asLiveData()
        crewList = getCrewUseCase.execute().asLiveData()
    }

    fun getLaunches() = launchList

    fun getCrew() = crewList
}

class ViewModelFactory(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val getCrewUseCase: GetCrewUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchesViewModel(
            getLaunchesUseCase,
            getCrewUseCase
        ) as T
    }
}