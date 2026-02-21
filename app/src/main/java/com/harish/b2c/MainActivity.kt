package com.harish.b2c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.core.event.UiEventChannel
import com.harish.b2c.main.MainViewModel
import com.harish.b2c.presentation.navigation.AppNavHost
import com.harish.b2c.presentation.navigation.Navigator
import com.harish.b2c.ui.theme.B2CTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var uiEventChannel: UiEventChannel
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            B2CTheme() {
                GradientBackground {
                    val navController = rememberNavController()

                    AppNavHost(
                        navController = navController,
                        navigator = navigator,
                    )
                }
            }
        }
    }
}