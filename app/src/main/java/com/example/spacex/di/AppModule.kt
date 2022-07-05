package com.example.spacex.di

import android.content.Context
import com.example.launch.domain.usecase.GetCrewUseCase
import com.example.launch.domain.usecase.GetLaunchesUseCase
import com.example.spacex.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideViewModelFactory(
        getLaunchesUseCase: GetLaunchesUseCase,
        getCrewUseCase: GetCrewUseCase
    ): ViewModelFactory {
        return ViewModelFactory(
            getLaunchesUseCase,
            getCrewUseCase
        )
    }
}