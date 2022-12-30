package com.powilliam.reimaginedspork.metronome

data class MetronomeUiState(val beatsPerMinute: Int = 60, val isTicking: Boolean = false) {
    val intervalBetweenBeatsInMilliseconds = 1.0 / beatsPerMinute * 60 * 1000
}
