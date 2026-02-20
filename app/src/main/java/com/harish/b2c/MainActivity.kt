package com.harish.b2c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.navigation.NavHostController
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.AppNavHost
import com.harish.b2c.presentation.navigation.Navigator
import com.harish.b2c.ui.theme.B2CTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            B2CTheme() {
                GradientBackground {
                    AppNavHost(
                        navigator = Navigator()
                    )
                }
            }
        }
    }
}