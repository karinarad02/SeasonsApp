package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): CurrentWeather

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherForecast

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}
