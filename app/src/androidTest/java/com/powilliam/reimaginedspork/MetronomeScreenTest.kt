package com.powilliam.reimaginedspork

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.powilliam.reimaginedspork.metronome.MetronomeScreen
import com.powilliam.reimaginedspork.theming.ApplicationTheme
import org.junit.Rule
import org.junit.Test

class MetronomeScreenTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun main() {
        rule.setContent {
            ApplicationTheme({ rule.activity }) {
                MetronomeScreen()
            }
        }
    }
}