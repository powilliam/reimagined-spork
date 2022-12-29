package com.powilliam.reimaginedspork.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun ApplicationNavHost(
    windowSizeClass: WindowSizeClass,
    controller: NavHostController = rememberNavController()
) {
    NavHost(navController = controller, startDestination = "") {}
}