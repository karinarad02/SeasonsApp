package com.example.weatherapp.ui.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.entryProvider
import com.example.weatherapp.data.repository.DestinationRepository
import com.example.weatherapp.ui.screens.DestinationDetailScreen
import com.example.weatherapp.ui.screens.SeasonListScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveWeatherApp(
    repository: DestinationRepository,
    deviceLocation: String? = null
) {
    // Correct usage of rememberNavBackStack with a vararg of elements
    val backStack = rememberNavBackStack(SeasonList)
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>()

    NavDisplay(
        backStack = backStack,
        onBack = { 
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        sceneStrategies = listOf(listDetailStrategy),
        entryProvider = entryProvider {
            entry<SeasonList>(
                metadata = ListDetailSceneStrategy.listPane(
                    detailPlaceholder = {
                        Text("Select a season to view details")
                    },
                ),
            ) {
                SeasonListScreen(
                    repository = repository,
                    deviceLocation = deviceLocation,
                    onNavigateToDetail = { id ->
                        backStack.add(DestinationDetail(id))
                    },
                )
            }
            entry<DestinationDetail>(
                metadata = ListDetailSceneStrategy.detailPane(),
            ) { key ->
                DestinationDetailScreen(
                    destinationId = key.destinationId,
                    repository = repository,
                    onBack = { 
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    },
                )
            }
        },
    )
}
