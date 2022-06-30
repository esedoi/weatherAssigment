package com.paul.weatherassigment.data.source.remote

import com.paul.weatherassigment.data.MyResult
import com.paul.weatherassigment.data.WeatherData
import com.paul.weatherassigment.data.source.WeatherDataSource
import com.paul.weatherassigment.network.WeatherApi
import com.paul.weatherassigment.network.WeatherApiService

object WeatherRemoteDataSource: WeatherDataSource {
    override suspend fun getWeather(authorization:String, locationName: List<String>, elementName: List<String>): MyResult<WeatherData> {
        return try {
            val result = WeatherApi.retrofitService.getWeather(authorization,locationName, elementName )
                MyResult.Success(result)

        }catch (e:Exception){
            MyResult.Error(e)
        }
    }
}