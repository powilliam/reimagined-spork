package com.powilliam.reimaginedspork

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.powilliam.reimaginedspork.navigation.ApplicationNavHost
import com.powilliam.reimaginedspork.theming.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var vibrator: Vibrator

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            ApplicationTheme({ this }) {
                ApplicationNavHost(windowSizeClass, onVibrate = ::onVibrate)
            }
        }
    }

    private fun onVibrate(interval: Long) {
        val effect = VibrationEffect.createOneShot(interval / 2, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
    }
}