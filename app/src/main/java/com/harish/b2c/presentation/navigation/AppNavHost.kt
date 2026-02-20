package com.harish.b2c.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harish.b2c.presentation.login.LoginScreen
import com.harish.b2c.presentation.login.OtpScreen
import com.harish.b2c.presentation.onboarding.DeliveryAddressScreen


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    navigator: Navigator
) {
    LaunchedEffect(Unit) {
        navigator.commands.collect { command ->
            when (command) {
                is NavigationCommand.Navigate -> {
                    navController.navigate(command.route)
                }

                is NavigationCommand.NavigateAndClear -> {
                    navController.navigate(command.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }

    NavHost(
        navController = navController, startDestination = NavigationScreen.Login.route,
        // Forward: New screen slides in from right
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it })
        },
        // Forward: Old screen slides out to left
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it })
        },
        // Backward: New screen slides in from left
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it })
        },
        // Backward: Old screen slides out to right
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it })
        }) {

        composable(NavigationScreen.Login.route) {
          //  val vm: LoginViewModel = hiltViewModel()
            LoginScreen(navController)
        }
        composable(NavigationScreen.Otp.route) {
          //  val vm: LoginViewModel = hiltViewModel()
            OtpScreen(navController)
        }
        composable(NavigationScreen.DeliveryAddress.route) {
          //  val vm: LoginViewModel = hiltViewModel()
            DeliveryAddressScreen(navController)
        }
//        composable(NavigationScreen.HomeScreen.route) {
//            val vm: HomeViewModel = hiltViewModel()
//            HomeScreen(navController, vm)
//        }
//        composable(NavigationScreen.ProfileScreen.route) {
//            val vm: ProfileViewModel = hiltViewModel()
//            MyProfileScreen(navController, vm)
//        }
    }
}
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController, graphRoute: String
): T {
    val parentEntry = remember(this) {
        navController.getBackStackEntry(graphRoute)
    }
    return hiltViewModel(parentEntry)
}