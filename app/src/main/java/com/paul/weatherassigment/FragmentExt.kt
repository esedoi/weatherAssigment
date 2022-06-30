package com.paul.weatherassigment

import androidx.fragment.app.Fragment
import com.paul.weatherassigment.factory.ViewModelFactory


    fun Fragment.getVmFactory(): ViewModelFactory {
        val repository = (requireContext().applicationContext as WeatherApplication).repository
        return ViewModelFactory(repository)
    }
