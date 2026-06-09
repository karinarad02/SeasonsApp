package com.example.weatherapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object SeasonList : NavKey

@Serializable
data class DestinationDetail(val destinationId: String) : NavKey
