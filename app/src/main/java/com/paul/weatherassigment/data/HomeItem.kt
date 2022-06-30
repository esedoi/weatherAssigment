package com.paul.weatherassigment.data

sealed class HomeItem {


    data class WeatherText(val time: Time) : HomeItem() {

    }
    data class Pic(val pic:String) : HomeItem() {

    }
}