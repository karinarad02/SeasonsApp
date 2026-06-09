package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.model.WeatherForecast
import com.example.weatherapp.data.remote.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface WeatherRepository {
    fun getCurrentWeather(city: String): Flow<CurrentWeather>
    fun getForecast(city: String): Flow<WeatherForecast>
}

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val apiKey: String
) : WeatherRepository {
    override fun getCurrentWeather(city: String): Flow<CurrentWeather> = flow {
        emit(api.getCurrentWeather(city, apiKey))
    }

    override fun getForecast(city: String): Flow<WeatherForecast> = flow {
        emit(api.getForecast(city, apiKey))
    }
}
