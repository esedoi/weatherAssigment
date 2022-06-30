package com.paul.weatherassigment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class WeatherData(
    val records: Records,
    val result: Result,
    val success: String
):Parcelable

@Parcelize
data class Records(
    val datasetDescription: String,
    val location: @RawValue List<Location>
):Parcelable

@Parcelize
data class Result(
    val fields: @RawValue List<Field>,
    val resource_id: String
):Parcelable

@Parcelize
data class Location(
    val locationName: String,
    val weatherElement:  @RawValue List<WeatherElement>
):Parcelable

@Parcelize
data class WeatherElement(
    val elementName: String,
    val time: @RawValue List<Time>
):Parcelable

@Parcelize
data class Time(
    val endTime: String,
    val parameter: Parameter,
    val startTime: String
):Parcelable

@Parcelize
data class Parameter(
    val parameterName: String,
    val parameterUnit: String
):Parcelable

@Parcelize
data class Field(
    val id: String,
    val type: String
):Parcelable