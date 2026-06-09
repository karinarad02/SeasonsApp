package com.example.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.data.model.Attraction
import com.example.weatherapp.data.model.Season
import com.example.weatherapp.data.model.SeasonalDestination
import com.example.weatherapp.data.repository.DestinationRepository
import com.example.weatherapp.ui.theme.WeatherAppTheme

import com.example.weatherapp.ui.components.SeasonIllustration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationDetailScreen(
    destinationId: String,
    repository: DestinationRepository,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val destination by repository.getDestinationById(destinationId).collectAsState(initial = null)

    destination?.let { dest ->
        WeatherAppTheme(season = dest.season) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Details", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.titleMedium) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                    )
                },
                containerColor = MaterialTheme.colorScheme.background,
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                ) {
                    // Header Illustration Area
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .background(MaterialTheme.colorScheme.surface),
                    ) {
                        // Background Illustration
                        SeasonIllustration(
                            season = dest.season,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Season Number and Name
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .align(Alignment.TopStart),
                        ) {
                            Text(
                                text = dest.season.name,
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp),
                                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = dest.name + ", Iran",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                )
                            }
                        }
                        
                        // Icon Overlay
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = dest.season.toIllustrationIcon(),
                                contentDescription = null,
                                modifier = Modifier.size(120.dp),
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "${dest.season.name} Description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = dest.description,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Seasonal activities",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 8.dp),
                    ) {
                        items(dest.attractions) { attraction ->
                            AttractionCard(attraction)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun AttractionCard(attraction: Attraction) {
    Card(
        modifier = Modifier.width(100.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = attraction.toIcon(),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = attraction.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

fun Attraction.toIcon(): ImageVector = when(this.name) {
    "Ski" -> Icons.Default.AcUnit
    "Festival" -> Icons.Default.Celebration
    "Hiking" -> Icons.Default.DirectionsWalk
    "Forest" -> Icons.Default.Park
    "Camping" -> Icons.Default.Cabin
    "Photography" -> Icons.Default.PhotoCamera
    "Horse Riding" -> Icons.Default.BedroomBaby
    "Snowball" -> Icons.Default.AcUnit
    "Hot Tea" -> Icons.Default.Coffee
    "Mist" -> Icons.Default.Cloud
    "Jungle" -> Icons.Default.Terrain
    "Cottage" -> Icons.Default.Home
    else -> Icons.Default.Star
}
