package com.paul.weatherassigment

import android.app.Application
import com.paul.weatherassigment.data.source.WeatherRepository
import com.paul.weatherassigment.util.ServiceLocator
import kotlin.properties.Delegates

class WeatherApplication : Application() {

    val repository: WeatherRepository
        get() = ServiceLocator.provideRepository(this)

    companion object {
        var instance: WeatherApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}