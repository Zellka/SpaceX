package com.example.spacex.app

import android.app.Application
import com.example.spacex.di.AppComponent
import com.example.spacex.di.AppModule
import com.example.spacex.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}