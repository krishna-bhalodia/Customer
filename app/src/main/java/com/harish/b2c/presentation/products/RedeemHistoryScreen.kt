package com.harish.b2c.presentation.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonRedeemHistoryCard
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.Spacing

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RedeemHistoryScreenPreview() {
    GradientBackground {
    RedeemHistoryScreen(navController = NavController(LocalContext.current))}
}

@Composable
fun RedeemHistoryScreen(
    navController: NavController
) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Redeem History",
                    onBackClick = { navController.popBackStack()  },
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = Spacing.mediumLarge)
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = Spacing.large, vertical = Spacing.medium),
                verticalArrangement = Arrangement.spacedBy(10.dp) // 10px gap matching Figma CSS
            ) {
                // Item 1: Earned points (+250)
                item {
                    CommonRedeemHistoryCard(
                        title = "Order Placed", // Matching Figma placeholder copy
                        description = "On spent amount of AED 16860.00",
                        points = 250,
                        time = "6:30 pm"
                    )
                }

                // Item 2: Redeemed points (-2500)
                item {
                    CommonRedeemHistoryCard(
                        title = "Redeem points",
                        description = "Through honeymoon package free Tickets",
                        points = -2500,
                        time = "6:30 pm"
                    )
                }

                // Item 3: Earned points (+100)
                item {
                    CommonRedeemHistoryCard(
                        title = "Order Placed",
                        description = "On spent amount of AED 5000.00",
                        points = 100,
                        time = "6:30 pm"
                    )
                }

                // Item 4: Redeemed points (-3000)
                item {
                    CommonRedeemHistoryCard(
                        title = "Redeem points",
                        description = "Through all Oner Products 3 Carton free",
                        points = -3000,
                        time = "6:30 pm"
                    )
                }
            }
        }
    }
