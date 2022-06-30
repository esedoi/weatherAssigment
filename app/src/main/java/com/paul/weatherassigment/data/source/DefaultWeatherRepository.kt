package com.paul.weatherassigment.data.source

import com.paul.weatherassigment.data.MyResult
import com.paul.weatherassigment.data.WeatherData

class DefaultWeatherRepository (private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {
    override suspend fun getWeather(authorization:String, locationName: List<String>, elementName: List<String>): MyResult<WeatherData> {
        return weatherDataSource.getWeather(authorization, locationName, elementName)
    }
}