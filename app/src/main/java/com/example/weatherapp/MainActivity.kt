package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapp.ui.LocationProvider
import com.example.weatherapp.data.repository.MockDestinationRepository
import com.example.weatherapp.ui.navigation.AdaptiveWeatherApp
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val repository = remember { MockDestinationRepository() }
            val locationProvider = remember { LocationProvider(context) }
            var deviceLocationName by remember { mutableStateOf<String?>(null) }

            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                if (permissions.values.all { it }) {
                    // Permissions granted, fetch location
                }
            }

            LaunchedEffect(Unit) {
                permissionLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }

            LaunchedEffect(Unit) {
                val location = locationProvider.getCurrentLocationName()
                deviceLocationName = location
            }

            WeatherAppTheme {
                AdaptiveWeatherApp(
                    repository = repository,
                    deviceLocation = deviceLocationName
                )
            }
        }
    }
}
