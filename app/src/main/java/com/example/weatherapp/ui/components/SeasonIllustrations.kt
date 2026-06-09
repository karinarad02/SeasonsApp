package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Season

@Composable
fun SeasonIllustration(season: Season, modifier: Modifier = Modifier) {
    val drawableRes = when (season) {
        Season.Spring -> R.drawable.spring
        Season.Summer -> R.drawable.summer
        Season.Rainy -> R.drawable.rainy
        Season.Autumn -> R.drawable.autumn
        Season.Winter -> R.drawable.winter
    }

    val alignment = when (season) {
        Season.Spring, Season.Summer, Season.Autumn -> Alignment.Center
        Season.Rainy, Season.Winter -> Alignment.CenterEnd
    }

    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = alignment
    )
}
