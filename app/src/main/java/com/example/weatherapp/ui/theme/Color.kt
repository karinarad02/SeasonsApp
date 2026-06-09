package com.example.weatherapp.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

interface SeasonPalette {
    val Primary: Color
    val Background: Color
    val OnBackground: Color
    val Surface: Color
}

// Seasonal Colors (Extracted from designs)
object SeasonalColors {
    object Autumn : SeasonPalette {
        override val Primary = Color(0xFFE67E22)
        override val Background = Color(0xFFFFF7ED)
        override val OnBackground = Color(0xFF431407)
        override val Surface = Color(0xFFFFEDD5)
    }
    object Summer : SeasonPalette {
        override val Primary = Color(0xFF1E3A8A) // Dark Navy for Primary in Summer
        override val Background = Color(0xFFF0FDF4)
        override val OnBackground = Color(0xFF1E3A8A) // Dark Navy
        override val Surface = Color(0xFFDCFCE7)
    }
    object Winter : SeasonPalette {
        override val Primary = Color(0xFF2980B9)
        override val Background = Color(0xFFF0F9FF)
        override val OnBackground = Color(0xFF0C4A6E)
        override val Surface = Color(0xFFE0F2FE)
    }
    object Rainy : SeasonPalette {
        override val Primary = Color(0xFF1E293B)
        override val Background = Color(0xFF0F172A)
        override val OnBackground = Color(0xFFFFFFFF) // White text for Rainy as per image
        override val Surface = Color(0xFF1E293B)
    }
}
