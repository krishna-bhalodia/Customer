package com.harish.b2c.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.harish.b2c.R

sealed class NavigationScreen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {

    data object Login : NavigationScreen("login_screen")
    data object HomeScreen : NavigationScreen("home_screen")
    data object ProfileScreen : NavigationScreen("profile_screen")
}
