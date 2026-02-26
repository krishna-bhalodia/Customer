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

    data object SplashScreen : NavigationScreen("splash_screen")
    data object OnBoardScreen : NavigationScreen("onboard_screen")
    data object Login : NavigationScreen("login_screen")
    data object SignUp : NavigationScreen("sign_up_screen")
    data object Otp : NavigationScreen("otp_screen")
    data object OtpforSignUp : NavigationScreen("otp_for_sign_up_screen")
    data object DeliveryAddress : NavigationScreen("delivery_address_screen")
    data object HomeScreen : NavigationScreen("home_screen")
    data object ProductsScreen : NavigationScreen("products_screen")
    data object PromotionsScreen : NavigationScreen("promotions_screen")
    data object AccountScreen : NavigationScreen("account_screen")

    // Other Screens
    data object ProfileScreen : NavigationScreen("profile_screen")
    data object CheckoutScreen : NavigationScreen("checkout_screen")
    data object SuccessScreen : NavigationScreen("success_screen")
    data object OrdersScreen : NavigationScreen("orders_screen")
    data object OrderDetailsScreen : NavigationScreen("order_details_screen")
    data object SavedAddressesScreen : NavigationScreen("saved_addresses_screen")
    data object PaymentMethodScreen : NavigationScreen("payment_method_screen")
    data object NotificationsScreen : NavigationScreen("notifications_screen")
    data object LoyaltyPointsScreen : NavigationScreen("loyalty_points_screen")
    data object RaiseComplaint : NavigationScreen("raise_complaint_screen")
    data object RaiseComplaintForm : NavigationScreen("raise_complaint_form_screen")
    data object RedeemHistory : NavigationScreen("redeem_history_screen")
    data object AddNewAddressScreen : NavigationScreen("add_new_address_screen")
}
