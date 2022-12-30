package com.powilliam.reimaginedspork.metronome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExposureNeg1
import androidx.compose.material.icons.rounded.ExposurePlus1
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.reimaginedspork.composables.LaunchedInterval
import com.powilliam.reimaginedspork.theming.ApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetronomeScreen(
    modifier: Modifier = Modifier,
    uiState: MetronomeUiState = MetronomeUiState(),
    onMutateBeatsPerMinute: (Float) -> Unit = {},
    onToggleIsTicking: () -> Unit = {},
    onVibrate: (Long) -> Unit = {}
) {
    if (uiState.isTicking) {
        LaunchedInterval(
            durationInMilliseconds = uiState.intervalBetweenBeatsInMilliseconds.toLong()
        ) {
            onVibrate(uiState.intervalBetweenBeatsInMilliseconds.toLong())
        }
    }

    Scaffold { paddingValues ->
        Box {
            Column(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .draggable(
                        state = rememberDraggableState { delta ->
                            onMutateBeatsPerMinute(delta / 6)
                        },
                        orientation = Orientation.Vertical,
                        startDragImmediately = false,
                        reverseDirection = true,
                        enabled = !uiState.isTicking
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${uiState.beatsPerMinute}",
                    style = MaterialTheme.typography.displayLarge
                )
                Text(text = "BPM", style = MaterialTheme.typography.bodyLarge)
            }

            Card(
                modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(50)
            ) {
                Row(
                    modifier
                        .animateContentSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(visible = !uiState.isTicking,
                        enter = slideInHorizontally { it } + fadeIn(),
                        exit = slideOutHorizontally { it } + fadeOut()) {
                        IconButton(enabled = !uiState.isTicking, onClick = {
                            onMutateBeatsPerMinute(-1.0F)
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.ExposureNeg1, contentDescription = null
                            )
                        }
                    }

                    IconButton(onClick = onToggleIsTicking) {
                        val imageVector = if (uiState.isTicking) {
                            Icons.Rounded.Pause
                        } else {
                            Icons.Rounded.PlayArrow
                        }

                        Icon(
                            imageVector = imageVector,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    AnimatedVisibility(visible = !uiState.isTicking,
                        enter = slideInHorizontally { -it } + fadeIn(),
                        exit = slideOutHorizontally { -it } + fadeOut()) {
                        IconButton(enabled = !uiState.isTicking, onClick = {
                            onMutateBeatsPerMinute(1.0F)
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.ExposurePlus1, contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun MetronomeScreenPreview() {
    val context = LocalContext.current

    ApplicationTheme({ context }) {
        MetronomeScreen()
    }
}