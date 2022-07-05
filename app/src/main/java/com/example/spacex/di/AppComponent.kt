package com.example.spacex.di

import com.example.spacex.presentation.view.DetailFragment
import com.example.spacex.presentation.view.LaunchesFragment
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun inject(launchesFragment: LaunchesFragment)

    fun inject(detailFragment: DetailFragment)
}