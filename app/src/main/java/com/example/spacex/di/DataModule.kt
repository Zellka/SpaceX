package com.example.spacex.di

import com.example.launch.data.api.ApiService
import com.example.launch.data.repository.LaunchesRepositoryImpl
import com.example.launch.domain.repository.LaunchesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideLaunchesRepository(api: ApiService): LaunchesRepository {
        return LaunchesRepositoryImpl(api)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        val BASE_URL = "https://api.spacexdata.com/v4/"
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}