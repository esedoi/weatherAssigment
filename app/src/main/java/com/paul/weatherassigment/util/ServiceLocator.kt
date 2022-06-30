package com.paul.weatherassigment.util

import android.content.Context
import com.paul.weatherassigment.data.source.DefaultWeatherRepository
import com.paul.weatherassigment.data.source.WeatherRepository
import com.paul.weatherassigment.data.source.remote.WeatherRemoteDataSource

object ServiceLocator {

    var repository: WeatherRepository? = null

    fun provideRepository(context: Context): WeatherRepository {
        synchronized(this) {
            return repository
                ?: repository
                ?: createWeatherRepository(context)
        }
    }

    private fun createWeatherRepository(context: Context): WeatherRepository {
        return DefaultWeatherRepository(
            WeatherRemoteDataSource
        )
    }
}