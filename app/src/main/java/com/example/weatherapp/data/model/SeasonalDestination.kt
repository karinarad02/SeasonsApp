package com.example.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class Season {
    Summer, Rainy, Autumn, Winter, Spring
}

@Serializable
data class Attraction(
    val name: String,
    val iconName: String // e.g., "Ski", "Festival", "Hiking"
)

@Serializable
data class VisitorStats(
    val currentYear: String,
    val lastYear: String
)

@Serializable
data class SeasonalDestination(
    val id: String,
    val season: Season,
    val name: String,
    val location: String,
    val illustrationUrl: String,
    val temperature: String,
    val weatherSummary: String,
    val distance: String,
    val description: String,
    val attractions: List<Attraction>,
    val stats: VisitorStats
)
