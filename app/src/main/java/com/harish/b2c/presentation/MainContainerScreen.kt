package com.harish.b2c.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    // 1. Initialize a list to track tab history
    val navigationHistory = rememberSaveable(
        saver = listSaver<SnapshotStateList<Int>, Int>(
            save = { it.toList() },
            restore = { mutableStateListOf<Int>().apply { addAll(it) } }
        )
    ) { mutableStateListOf(0) }

    // Get the current active tab from the end of the list
    val selectedIndex = navigationHistory.lastOrNull() ?: 0

    // 2. Intercept back button if there is more than one item in history
    BackHandler(enabled = navigationHistory.size > 1) {
        navigationHistory.removeAt(navigationHistory.size - 1)
    }
    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            CommonBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index -> if (selectedIndex != index) {
                    // If user clicks a tab, add it to history.
                    // Optional: remove previous instances of this tab to keep history clean
                    navigationHistory.remove(index)
                    navigationHistory.add(index)
                } },
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