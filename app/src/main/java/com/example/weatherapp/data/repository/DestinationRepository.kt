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
            name = "Summer",
            location = "Sunny Region",
            illustrationUrl = "https://example.com/summer.png",
            temperature = "28°C",
            weatherSummary = "Sunny",
            distance = "535 km",
            description = "Embrace the warmth of summer with long sunny days and clear skies. It's the perfect time for outdoor adventures, coastal escapes, and vibrant festivals under the sun.",
            attractions = listOf(
                Attraction("Camping", "Camping"),
                Attraction("Hiking", "Hiking"),
                Attraction("Photography", "Photography"),
                Attraction("Swimming", "Swimming"),
                Attraction("Picnic", "Picnic"),
                Attraction("Surfing", "Surfing"),
                Attraction("Beach Volley", "Volley"),
                Attraction("Sunset View", "Sunset"),
                Attraction("BBQ Night", "BBQ"),
                Attraction("Kayaking", "Kayaking")
            ),
            stats = VisitorStats("4.100", "3.765")
        ),
        SeasonalDestination(
            id = "rainy_1",
            season = Season.Rainy,
            name = "Rainy",
            location = "Cloudy Region",
            illustrationUrl = "https://example.com/rainy.png",
            temperature = "15°C",
            weatherSummary = "Rainy",
            distance = "435 km",
            description = "Experience the soothing rhythm of the rainy season. Refreshing showers bring life to the landscape, creating a cozy atmosphere perfect for relaxation and enjoying the fresh scent of rain.",
            attractions = listOf(
                Attraction("Mist Walk", "Mist"),
                Attraction("Hot Tea", "Coffee"),
                Attraction("Reading", "MenuBook"),
                Attraction("Forest Rain", "Thunderstorm"),
                Attraction("Cottage Stay", "Home"),
                Attraction("Puddle Jump", "Water"),
                Attraction("Rainy Drive", "Drive"),
                Attraction("Indoor Games", "Games"),
                Attraction("Meditation", "SelfImprovement"),
                Attraction("Music", "MusicNote")
            ),
            stats = VisitorStats("0.8M", "0.7M")
        ),
        SeasonalDestination(
            id = "autumn_1",
            season = Season.Autumn,
            name = "Autumn",
            location = "Highlands",
            illustrationUrl = "https://example.com/autumn.png",
            temperature = "18°C",
            weatherSummary = "Cool",
            distance = "435 km",
            description = "Witness the stunning transformation of nature as leaves turn to brilliant shades of gold and crimson. The crisp air and harvest season offer a peaceful time for reflection and scenic walks.",
            attractions = listOf(
                Attraction("Photography", "PhotoCamera"),
                Attraction("Leaf Peeping", "Eco"),
                Attraction("Hiking", "DirectionsWalk"),
                Attraction("Nature Walk", "Landscape"),
                Attraction("Harvest", "Agriculture"),
                Attraction("Cider Tasting", "WineBar"),
                Attraction("Pumpkin Patch", "OutdoorGrill"),
                Attraction("Bonfire", "Whatshot"),
                Attraction("Stargazing", "Stars"),
                Attraction("Collecting", "Park")
            ),
            stats = VisitorStats("1.2M", "0.9M")
        ),
        SeasonalDestination(
            id = "winter_1",
            season = Season.Winter,
            name = "Winter",
            location = "Alpine Region",
            illustrationUrl = "https://example.com/winter.png",
            temperature = "-5°C",
            weatherSummary = "Snowy",
            distance = "435 km",
            description = "Step into a serene winter wonderland. From snow-covered peaks to cozy fireside evenings, winter brings a unique charm with its quiet beauty and thrilling cold-weather activities.",
            attractions = listOf(
                Attraction("Skiing", "AcUnit"),
                Attraction("Snowball", "AcUnit"),
                Attraction("Hot Tea", "Coffee"),
                Attraction("Ice Skating", "IceSkating"),
                Attraction("Sightseeing", "Visibility"),
                Attraction("Sledding", "DownhillSkiing"),
                Attraction("Aurora", "BrightnessLow"),
                Attraction("Snowshoeing", "DirectionsWalk"),
                Attraction("Hot Springs", "HotTub"),
                Attraction("Snowman", "Toys")
            ),
            stats = VisitorStats("0.5M", "0.4M")
        ),
        SeasonalDestination(
            id = "spring_1",
            season = Season.Spring,
            name = "Spring",
            location = "Floral Valley",
            illustrationUrl = "https://example.com/spring.png",
            temperature = "22°C",
            weatherSummary = "Mild",
            distance = "535 km",
            description = "Celebrate the arrival of spring as the world blooms with new life. Vibrant flowers, mild temperatures, and singing birds mark a season of renewal and fresh beginnings.",
            attractions = listOf(
                Attraction("Flower Fest", "FilterVintage"),
                Attraction("Hiking", "DirectionsWalk"),
                Attraction("Bird Watching", "FlutterDash"),
                Attraction("Gardening", "Yard"),
                Attraction("Biking", "DirectionsBike"),
                Attraction("Outdoor Yoga", "SelfImprovement"),
                Attraction("Kite Flying", "Air"),
                Attraction("Visit Farm", "Agriculture"),
                Attraction("Picnic", "LocalPizza"),
                Attraction("Painting", "Brush")
            ),
            stats = VisitorStats("3.500", "3.200")
        )
    )

    override fun getDestinations(): Flow<List<SeasonalDestination>> = flowOf(destinations)

    override fun getDestinationById(id: String): Flow<SeasonalDestination?> = flowOf(
        destinations.find { it.id == id }
    )
}
