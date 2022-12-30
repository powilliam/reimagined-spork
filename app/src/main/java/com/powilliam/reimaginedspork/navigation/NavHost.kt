package com.powilliam.reimaginedspork.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.powilliam.reimaginedspork.metronome.MetronomeScreen
import com.powilliam.reimaginedspork.metronome.MetronomeViewModel

@Composable
fun ApplicationNavHost(
    windowSizeClass: WindowSizeClass,
    controller: NavHostController = rememberNavController(),
    onVibrate: (Long) -> Unit,
) {
    NavHost(navController = controller, startDestination = Destination.Metronome.route) {
        composable(Destination.Metronome.route, Destination.Metronome.arguments) {
            val metronomeViewModel = hiltViewModel<MetronomeViewModel>()
            val uiState by metronomeViewModel.uiState.collectAsState()

            MetronomeScreen(
                uiState = uiState,
                onMutateBeatsPerMinute = metronomeViewModel::onMutateBeatsPerMinute,
                onToggleIsTicking = metronomeViewModel::onToggleIsTicking,
                onVibrate = onVibrate
            )
        }
    }
}