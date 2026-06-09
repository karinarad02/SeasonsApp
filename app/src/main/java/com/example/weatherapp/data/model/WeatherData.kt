package com.example.weatherapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    val main: MainData,
    val weather: List<WeatherDescription>,
    val wind: WindData,
    val name: String
)

@Serializable
data class WeatherForecast(
    val list: List<ForecastItem>
)

@Serializable
data class ForecastItem(
    val dt: Long,
    val main: MainData,
    val weather: List<WeatherDescription>,
    @SerialName("dt_txt") val dtTxt: String
)

@Serializable
data class MainData(
    val temp: Double,
    val humidity: Int,
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double
)

@Serializable
data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class WindData(
    val speed: Double
)
