package com.example.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.model.Season

fun Season.toOrderNumber(): String = when(this) {
    Season.Summer -> "1"
    Season.Rainy -> "2"
    Season.Autumn -> "3"
    Season.Winter -> "4"
}

fun Season.toIllustrationIcon(): ImageVector = when(this) {
    Season.Summer -> Icons.Default.WbSunny
    Season.Rainy -> Icons.Default.Umbrella
    Season.Autumn -> Icons.Default.Eco
    Season.Winter -> Icons.Default.AcUnit
}

@Composable
fun SeasonSelector(
    selectedSeason: Season,
    onSeasonSelected: (Season) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White.copy(alpha = 0.4f),
        shape = CircleShape,
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Sort seasons to match image order if needed, 
            // but here we just iterate. Image shows Summer, Rainy, Autumn, Winter? 
            // Actually it's based on order.
            Season.values().forEach { season ->
                SeasonPill(
                    season = season,
                    isSelected = season == selectedSeason,
                    onClick = { onSeasonSelected(season) }
                )
            }
        }
    }
}

@Composable
fun SeasonPill(
    season: Season,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val icon = when (season) {
        Season.Autumn -> Icons.Default.Park
        Season.Summer -> Icons.Default.WbSunny
        Season.Winter -> Icons.Default.AcUnit
        Season.Rainy -> Icons.Default.WaterDrop
    }

    if (isSelected) {
        val pillColor = if (season == Season.Rainy || season == Season.Winter) Color(0xFF0F172A) else MaterialTheme.colorScheme.onSurface
        Surface(
            color = pillColor,
            shape = CircleShape,
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = season.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    } else {
        val iconColor = if (MaterialTheme.colorScheme.onBackground == Color.White) Color.White.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = season.name,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun LocationHeader(
    name: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = location,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}
