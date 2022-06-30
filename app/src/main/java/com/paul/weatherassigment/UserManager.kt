package com.paul.weatherassigment

import android.content.Context
import android.content.SharedPreferences

class UserManager {

    companion object {

        private lateinit var settings: SharedPreferences

        fun readData(greet: String): Boolean {
            settings = WeatherApplication.instance.getSharedPreferences(
                "DATA",
                Context.MODE_PRIVATE
            )

            return settings.getBoolean(greet, true)
        }

        fun saveData(isFirstTime: Boolean) {
            settings = WeatherApplication.instance.getSharedPreferences(
                "DATA",
                Context.MODE_PRIVATE
            )
            settings.edit()
                .putBoolean("greet", isFirstTime)
                .apply()
        }
    }
}