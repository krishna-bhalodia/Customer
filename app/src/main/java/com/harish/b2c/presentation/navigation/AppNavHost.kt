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
import com.harish.b2c.presentation.MainContainerScreen
import com.harish.b2c.presentation.account.AccountScreen
import com.harish.b2c.presentation.account.AddNewAddressScreen
import com.harish.b2c.presentation.checkout.CheckoutScreen
import com.harish.b2c.presentation.checkout.OrderSuccessScreen
import com.harish.b2c.presentation.home.HomeScreen
import com.harish.b2c.presentation.splash.SplashScreen
import com.harish.b2c.presentation.login.LoginScreen
import com.harish.b2c.presentation.login.OtpScreen
import com.harish.b2c.presentation.loyalty.LoyaltyPointsScreen
import com.harish.b2c.presentation.onboarding.OnboardingScreen
import com.harish.b2c.presentation.products.NotificationsScreen
import com.harish.b2c.presentation.products.OrderDetailsScreen
import com.harish.b2c.presentation.products.OrdersScreen
import com.harish.b2c.presentation.products.PaymentMethodScreen
import com.harish.b2c.presentation.products.ProductsScreen
import com.harish.b2c.presentation.products.ProfileScreen
import com.harish.b2c.presentation.products.RaiseComplaintFormScreen
import com.harish.b2c.presentation.products.RaiseComplaintScreen
import com.harish.b2c.presentation.products.RedeemHistoryScreen
import com.harish.b2c.presentation.products.SavedAddressesScreen
import com.harish.b2c.presentation.promotions.PromotionsScreen
import com.harish.b2c.presentation.signUp.DeliveryAddressScreen
import com.harish.b2c.presentation.splash.SplashViewModel


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
        navController = navController, startDestination = NavigationScreen.SplashScreen.route,
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
        composable(NavigationScreen.SplashScreen.route) {
            val vm: SplashViewModel = hiltViewModel()
            SplashScreen(navController, vm)
        }
        composable(NavigationScreen.OnBoardScreen.route) {
            OnboardingScreen(navController)
        }
        composable(NavigationScreen.Login.route) {
            //  val vm: LoginViewModel = hiltViewModel()
            LoginScreen(navController = navController, isSignUp = false)
        }
        composable(NavigationScreen.SignUp.route) {
            //  val vm: LoginViewModel = hiltViewModel()
            LoginScreen(navController = navController, isSignUp = true)
        }
        composable(NavigationScreen.Otp.route) {
            //  val vm: LoginViewModel = hiltViewModel()
            OtpScreen(navController, isSignUp = false)
        }
        composable(NavigationScreen.OtpforSignUp.route) {
            //  val vm: LoginViewModel = hiltViewModel()
            OtpScreen(navController, isSignUp = true)
        }
        composable(NavigationScreen.DeliveryAddress.route) {
            //  val vm: LoginViewModel = hiltViewModel()
            DeliveryAddressScreen(navController)
        }
        // --- Bottom Bar Screens ---
        composable(NavigationScreen.MainContainer.route) {
            MainContainerScreen(rootNavController = navController)
        }
        composable(NavigationScreen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(NavigationScreen.ProductsScreen.route) {
            ProductsScreen(navController)
        }
        composable(NavigationScreen.PromotionsScreen.route) {
            PromotionsScreen(navController)
        }
        composable(NavigationScreen.AccountScreen.route) {
            AccountScreen(navController)
        }

        // --- Other Screens ---
        composable(NavigationScreen.CheckoutScreen.route) {
            CheckoutScreen(navController)
        }
        composable(NavigationScreen.SuccessScreen.route) {
            OrderSuccessScreen(navController)
        }
        composable(NavigationScreen.OrdersScreen.route) {
            OrdersScreen(navController)
        }
        composable(NavigationScreen.OrderDetailsScreen.route) {
            OrderDetailsScreen(navController)
        }
        composable(NavigationScreen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(NavigationScreen.SavedAddressesScreen.route) {
            SavedAddressesScreen(navController)
        }
        composable(NavigationScreen.PaymentMethodScreen.route) {
            PaymentMethodScreen(navController)
        }
        composable(NavigationScreen.NotificationsScreen.route) {
            NotificationsScreen(navController)
        }
        composable(NavigationScreen.LoyaltyPointsScreen.route) {
            LoyaltyPointsScreen(navController)
        }
        composable(NavigationScreen.RaiseComplaint.route) {
            RaiseComplaintScreen(navController)
        }
        composable(NavigationScreen.RaiseComplaintForm.route) {
            RaiseComplaintFormScreen(navController)
        }
        composable(NavigationScreen.RedeemHistory.route) {
            RedeemHistoryScreen(navController)
        }
        composable(NavigationScreen.AddNewAddressScreen.route) {
            AddNewAddressScreen(navController)
        }
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