package com.paul.weatherassigment.homefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.weatherassigment.data.MyResult
import com.paul.weatherassigment.data.WeatherData
import com.paul.weatherassigment.data.source.WeatherRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private var _weather = MutableLiveData<WeatherData>()
    val weather: LiveData<WeatherData>
        get() = _weather


    fun getWeather(authorization: String, locationName: List<String>, elementName: List<String>) {

        viewModelScope.launch {

            when (val result =
                weatherRepository.getWeather(authorization, locationName, elementName)) {
                is MyResult.Success -> {
                    _weather.value = result.data ?: return@launch

                }
                is MyResult.Error -> {
                    Log.d("homeViewModel", "result = ${result.exception}")
                }
            }
        }

    }

}