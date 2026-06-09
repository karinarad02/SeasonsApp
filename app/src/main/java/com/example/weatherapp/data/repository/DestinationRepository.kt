package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface DestinationRepository {
    fun getDestinations(): Flow<List<SeasonalDestination>>
    fun getDestinationById(id: String): Flow<SeasonalDestination?>
}

class MockDestinationRepository : DestinationRepository {
    private val destinations = listOf(
        SeasonalDestination(
            id = "summer_1",
            season = Season.Summer,
            name = "Asalem - Khalkhal",
            location = "Gilan, Iran",
            illustrationUrl = "https://example.com/summer.png",
            temperature = "28°C",
            weatherSummary = "Summer",
            distance = "535 km",
            description = "Enjoy the beauty of summer with various attractions available here, as well as various complete facilities available at affordable prices for all tourists from all over the world.",
            attractions = listOf(
                Attraction("Ski", "Ski"),
                Attraction("Festival", "Festival"),
                Attraction("Hiking", "Hiking")
            ),
            stats = VisitorStats("4.100", "3.765")
        ),
        SeasonalDestination(
            id = "rainy_1",
            season = Season.Rainy,
            name = "Asalem - Khalkhal",
            location = "Gilan, Iran",
            illustrationUrl = "https://example.com/rainy.png",
            temperature = "15°C",
            weatherSummary = "Rainy",
            distance = "435 km",
            description = "The rainy season brings a mysterious mist and fresh scent of earth to the forests of northern Iran.",
            attractions = listOf(
                Attraction("Mist", "Mist"),
                Attraction("Jungle", "Jungle"),
                Attraction("Cottage", "Cottage")
            ),
            stats = VisitorStats("0.8M", "0.7M")
        ),
        SeasonalDestination(
            id = "autumn_1",
            season = Season.Autumn,
            name = "Asalem - Khalkhal",
            location = "Gilan, Iran",
            illustrationUrl = "https://example.com/autumn.png",
            temperature = "18°C",
            weatherSummary = "Autumn",
            distance = "435 km",
            description = "The Asalem to Khalkhal road is one of the most beautiful dream roads in Iran, which connects the city of Asalem in Gilan province to Khalkhal in Ardabil province.",
            attractions = listOf(
                Attraction("Forest", "Forest"),
                Attraction("Hiking", "Hiking"),
                Attraction("Photography", "Photography")
            ),
            stats = VisitorStats("1.2M", "0.9M")
        ),
        SeasonalDestination(
            id = "winter_1",
            season = Season.Winter,
            name = "Asalem - Khalkhal",
            location = "Gilan, Iran",
            illustrationUrl = "https://example.com/winter.png",
            temperature = "-5°C",
            weatherSummary = "Winter",
            distance = "435 km",
            description = "Experience the snowy wonderland. The road becomes a white paradise, perfect for those who love winter sceneries.",
            attractions = listOf(
                Attraction("Ski", "Ski"),
                Attraction("Snowball", "Snowball"),
                Attraction("Hot Tea", "Hot Tea")
            ),
            stats = VisitorStats("0.5M", "0.4M")
        ),
        SeasonalDestination(
            id = "spring_1",
            season = Season.Spring,
            name = "Asalem - Khalkhal",
            location = "Gilan, Iran",
            illustrationUrl = "https://example.com/spring.png",
            temperature = "22°C",
            weatherSummary = "Spring",
            distance = "535 km",
            description = "Witness the rebirth of nature in spring. The meadows are filled with wildflowers and the air is filled with the sweet scent of blossoms.",
            attractions = listOf(
                Attraction("Festival", "Festival"),
                Attraction("Hiking", "Hiking"),
                Attraction("Photography", "Photography")
            ),
            stats = VisitorStats("3.500", "3.200")
        )
    )

    override fun getDestinations(): Flow<List<SeasonalDestination>> = flowOf(destinations)

    override fun getDestinationById(id: String): Flow<SeasonalDestination?> = flowOf(
        destinations.find { it.id == id }
    )
}
