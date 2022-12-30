package com.powilliam.reimaginedspork.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object Metronome : Destination("metronome")
}
