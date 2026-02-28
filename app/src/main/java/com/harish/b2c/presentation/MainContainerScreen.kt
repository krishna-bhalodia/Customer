package com.harish.b2c.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.harish.b2c.core.components.CommonBottomBar
import com.harish.b2c.presentation.account.AccountScreen
import com.harish.b2c.presentation.home.HomeScreen
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.presentation.products.ProductsScreen
import com.harish.b2c.presentation.promotions.PromotionsScreen


@Composable
fun MainContainerScreen(
    rootNavController: NavHostController, // To navigate to screens OUTSIDE the bottom bar (like Checkout)
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            CommonBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index },
                onFabClick = {
                    // Navigate to Checkout via the root controller
                    rootNavController.navigate(NavigationScreen.CheckoutScreen.route)
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedIndex) {
                0 -> HomeScreen(rootNavController)
                1 -> ProductsScreen(rootNavController)
                2 -> PromotionsScreen(rootNavController)
                3 -> AccountScreen(rootNavController)
            }
        }
    }
}