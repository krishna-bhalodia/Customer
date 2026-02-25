package com.harish.b2c.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White

@Composable
fun HomeScreen(navController: NavController) {

    var searchQuery by remember { mutableStateOf("") }
    var bottomNavIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            CommonBottomBar(
                selectedIndex = bottomNavIndex,
                onItemSelected = { index ->
                    when (index) {
                        0 -> {  navController.navigate(NavigationScreen.HomeScreen.route) }
                        1 -> {
                            navController.navigate(NavigationScreen.ProductsScreen.route)
                        } // Calls the lambda defined in AppNavHost
                        2 -> {  navController.navigate(NavigationScreen.CheckoutScreen.route) }
                        3 -> { /* Navigate to Account/Profile */ }
                    }
                },
                onFabClick = { }, modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(Spacing.extraLarge)) // Status bar padding equivalent

            // --- 1. Top Header Area ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)) {
                    Text(
                        text = "Hi Nicholas,",
                        color = TextBlack, // Centralized color
                        style = AppTypography.headlineLarge.copy(
                            fontSize = 24.sp
                        ) // Uses Signika Bold from Type.kt
                    )
                    Text(
                        text = "Good Morning!",
                        color = BrandRed, // Centralized Brand color
                        style = AppTypography.titleMedium.copy(
                            fontSize = 14.sp
                        ) // Uses Signika Medium from Type.kt
                    )
                }

                // Notification Bell with Theme-based Glassmorphism
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .shadow(
                            elevation = Spacing.medium,
                            shape = CircleShape,
                            spotColor = BrandRed.copy(alpha = 0.1f)
                        )
                        .background(White.copy(alpha = 0.3f), CircleShape)
                        .border(1.dp, White.copy(alpha = 0.6f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.notification),
                        contentDescription = "Notifications",
                        tint = TextBlack,
                        modifier = Modifier.size(20.dp)
                    )
                    // Notification Indicator
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 10.dp)
                            .size(8.dp)
                            .background(BrandRed, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // --- 2. Search Bar ---
            Box(modifier = Modifier.padding(horizontal = Spacing.large)) {
                CommonSearchBar(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = "Search...",
                    trailingIcon = null
                )
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // --- 3. Delivery Address ---
            Column(
                modifier = Modifier.padding(horizontal = Spacing.large),
                verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
            ) {
                Text(
                    text = "Delivery to",
                    color = DisabledGray, // Used centralized gray
                    style = AppTypography.labelSmall.copy(fontSize = 12.sp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                ) {
                    Text(
                        text = "S12, Kira Rd, Kampala Capital City",
                        color = TextBlack,
                        style = AppTypography.bodyLarge.copy(fontSize = 14.sp)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Change Address",
                        tint = TextBlack,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // --- 4. Banners ---
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Spacing.mediumSmall),
                contentPadding = PaddingValues(horizontal = Spacing.large)
            ) {
                items(4) {
                    Box(
                        modifier = Modifier
                            .width(327.dp)
                            .height(150.dp)
                            .clip(Shapes.large) // Uses AppShapes from Shape.kt
                            .background(TextBlack.copy(alpha = 0.1f))
                    ) {
                        Text(
                            "Banner Image Placeholder",
                            style = AppTypography.bodyLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // Banner Indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.medium),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 12.dp, height = 6.dp)
                        .background(TextBlack, CircleShape)
                )
                repeat(3) {
                    Spacer(modifier = Modifier.width(Spacing.extraSmall))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(TextBlack.copy(alpha = 0.2f), CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // --- 5. Loyalty Points Statistics Card ---
            CommonLoyaltyCard(
                points = 957,
                onRedeemClick = { /* Handle Redeem Action */ },
                modifier = Modifier.padding(horizontal = Spacing.large)
            )

            Spacer(modifier = Modifier.height(Spacing.large))

            // --- 6. Recent Orders ---
            CommonSectionHeader(
                title = "Recent Order",
                actionText = "View All",
                onActionClick = { /* Handle View All Action */ },
                modifier = Modifier.padding(horizontal = Spacing.large)
            )

            Spacer(modifier = Modifier.height(Spacing.mediumSmall))

            // Order Card List
            LazyRow(
                contentPadding = PaddingValues(horizontal = Spacing.large),
                horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                items(2) {
                    CommonRecentOrderCard(
                        orderId = "#MHO00300010",
                        date = "Placed on 17 Oct, 2025",
                        amount = "1240.00",
                        status = "Order delivered",
                        modifier = Modifier.width(327.dp)
                    )
                }
            }

            // Extra padding for scrollability above bottom bar
            Spacer(modifier = Modifier.height(Spacing.huge))
        }
    }
}