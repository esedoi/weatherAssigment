package com.paul.weatherassigment.data.source

import com.paul.weatherassigment.data.MyResult
import com.paul.weatherassigment.data.WeatherData

interface WeatherRepository {
    suspend fun getWeather(authorization:String, locationName: List<String>, elementName: List<String>): MyResult<WeatherData>
}