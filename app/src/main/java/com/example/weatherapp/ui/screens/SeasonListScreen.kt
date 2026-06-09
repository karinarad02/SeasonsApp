package com.example.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.data.model.Season
import com.example.weatherapp.data.model.SeasonalDestination
import com.example.weatherapp.data.repository.DestinationRepository
import com.example.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

import com.example.weatherapp.ui.components.SeasonIllustration

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize

import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.alpha

@Composable
fun SeasonListScreen(
    repository: DestinationRepository,
    onNavigateToDetail: (String) -> Unit,
    deviceLocation: String? = null,
    modifier: Modifier = Modifier,
) {
    val destinations by repository.getDestinations().collectAsState(initial = emptyList())
    
    if (destinations.isEmpty()) return

    val pagerState = rememberPagerState { destinations.size }
    val currentDestination = destinations[pagerState.currentPage]
    val scope = rememberCoroutineScope()
    
    val configuration = LocalConfiguration.current
    val isWideScreen = configuration.screenWidthDp > 600

    WeatherAppTheme(season = currentDestination.season) {
        Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                val destination = destinations[page]
                SeasonPage(
                    destination = destination,
                    isWideScreen = isWideScreen,
                    deviceLocation = deviceLocation
                )
            }

            // Top Header removed as per request ("Ngagidig" removed)
            
            // Bottom Selectors
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 0.dp), // Removed navigationBarsPadding to let Surface touch bottom
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SeasonSelector(
                    selectedSeason = currentDestination.season,
                    onSeasonSelected = { season ->
                        val index = destinations.indexOfFirst { it.season == season }
                        if (index != -1) {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    },
                )
                
                if (!isWideScreen) {
                    Spacer(modifier = Modifier.height(8.dp)) // Reduced from 24.dp to lower the bar
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp) // Slightly taller to account for nav bar area
                            .pointerInput(currentDestination.id) {
                                var hasNavigated = false
                                detectVerticalDragGestures(
                                    onDragStart = { hasNavigated = false },
                                    onVerticalDrag = { _, dragAmount ->
                                        if (!hasNavigated && dragAmount < -10f) {
                                            hasNavigated = true
                                            onNavigateToDetail(currentDestination.id)
                                        }
                                    }
                                )
                            }
                            .clickable { onNavigateToDetail(currentDestination.id) },
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        color = Color.White
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .navigationBarsPadding(), // Padding inside to keep content above nav bar
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = Color(0xFFF1F5F9),
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp),
                                        tint = Color(0xFF1E293B)
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            Text(
                                text = "Swipe Up for Details",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color(0xFF64748B),
                                    letterSpacing = 0.5.sp
                                ),
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DesktopMenu() {
    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
        listOf("Home", "Categories", "Our Story", "Membership", "Login").forEach { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (item == "Home") FontWeight.Bold else FontWeight.Medium,
                color = if (item == "Login") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.clickable { /* Handle click */ }
            )
        }
    }
}

@Composable
fun SeasonPage(
    destination: SeasonalDestination,
    isWideScreen: Boolean,
    deviceLocation: String? = null,
    modifier: Modifier = Modifier,
) {
    val locationText = deviceLocation ?: destination.location

    Box(modifier = modifier.fillMaxSize()) {
        // Gradient Background
        val brush = when (destination.season) {
            Season.Spring -> Brush.verticalGradient(listOf(Color(0xFFF0FDF4), Color(0xFFDCFCE7)))
            Season.Summer -> Brush.verticalGradient(listOf(Color(0xFFE0F2FE), Color(0xFFF0FDF4)))
            Season.Autumn -> Brush.verticalGradient(listOf(Color(0xFFFEF3C7), Color(0xFFFFF7ED)))
            Season.Winter -> Brush.verticalGradient(listOf(Color(0xFFF3E8FF), Color(0xFFE9D5FF)))
            Season.Rainy -> Brush.verticalGradient(listOf(Color(0xFF334155), Color(0xFF0F172A)))
        }

        val textColor = if (destination.season == Season.Rainy) Color.White else Color(0xFF1E3A8A)
        val alphaColor = textColor.copy(alpha = 0.5f)
        val lineAlphaColor = textColor.copy(alpha = 0.2f)

        Box(modifier = Modifier.fillMaxSize().background(brush))

        // Large Background Illustration
        SeasonIllustration(
            season = destination.season,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, bottom = 120.dp, start = 20.dp, end = 20.dp) // More padding = more zoomed out
                .scale(1.2f)
        )

        // Text Content
        val horizontalPadding = if (isWideScreen) 120.dp else 40.dp
        val topPadding = if (isWideScreen) 180.dp else 120.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = topPadding)
                .padding(horizontal = horizontalPadding),
        ) {
            // Day Number and line
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = destination.season.toOrderNumber(),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = if (isWideScreen) 80.sp else 90.sp,
                        lineHeight = 90.sp
                    ),
                    fontWeight = FontWeight.Black,
                    color = textColor, 
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(1.dp)
                        .background(lineAlphaColor)
                )
            }
            
            // Season Name
            Text(
                text = destination.name,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = if (isWideScreen) 60.sp else 64.sp,
                    lineHeight = 64.sp
                ),
                fontWeight = FontWeight.Bold,
                color = textColor, 
            )
            
            Spacer(modifier = Modifier.height(4.dp))

            // Location with Marker
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = alphaColor,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = locationText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = alphaColor,
                )
            }
        }
    }
}
