package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
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
            val scope = rememberCoroutineScope()
            var deviceLocationName by remember { mutableStateOf<String?>(null) }

            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                if (permissions.values.all { it }) {
                    scope.launch {
                        deviceLocationName = locationProvider.getCurrentLocationName()
                    }
                }
            }

            LaunchedEffect(Unit) {
                val hasFineLocation = context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED
                val hasCoarseLocation = context.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED
                
                if (hasFineLocation || hasCoarseLocation) {
                    deviceLocationName = locationProvider.getCurrentLocationName()
                } else {
                    permissionLauncher.launch(
                        arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
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
