package com.powilliam.reimaginedspork.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LaunchedInterval(
    durationInMilliseconds: Long,
    onTick: suspend CoroutineScope.() -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(durationInMilliseconds) {
        val job = coroutineScope.launch {
            while (true) {
                onTick()
                delay(durationInMilliseconds)
            }
        }

        onDispose {
            job.cancel()
        }
    }
}