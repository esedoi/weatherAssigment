package com.paul.weatherassigment.network

import androidx.viewbinding.BuildConfig
import com.paul.weatherassigment.data.MyResult
import com.paul.weatherassigment.data.WeatherData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://opendata.cwb.gov.tw/api/"


private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = when (true) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    )
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface WeatherApiService {

    @GET("v1/rest/datastore/F-C0032-001")
    suspend fun getWeather(
        @Query("Authorization") authorization: String,
        @Query("locationName") locationName: List<String>,
        @Query("elementName") elementName: List<String>,
    ): WeatherData

}


object WeatherApi {
    val retrofitService: WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }
}
