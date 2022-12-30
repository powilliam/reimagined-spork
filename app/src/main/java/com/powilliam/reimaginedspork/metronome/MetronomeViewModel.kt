package com.powilliam.reimaginedspork.metronome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MetronomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MetronomeUiState())
    val uiState: StateFlow<MetronomeUiState> = _uiState

    fun onMutateBeatsPerMinute(delta: Float) = viewModelScope.launch {
        _uiState.update { state ->
            val beatsPerMinute = state.beatsPerMinute + delta.roundToInt()
            val newValue = if (beatsPerMinute !in 40..240) state.beatsPerMinute else beatsPerMinute

            state.copy(beatsPerMinute = newValue)
        }
    }

    fun onToggleIsTicking() = viewModelScope.launch {
        _uiState.update { state ->
            state.copy(isTicking = !state.isTicking)
        }
    }
}