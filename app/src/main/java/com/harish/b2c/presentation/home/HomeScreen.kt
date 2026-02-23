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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    val spacing = Spacing // Accessing centralized spacing from Spacing.kt
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
            // Replaced hardcoded status bar spacer with theme spacing
            Spacer(modifier = Modifier.height(spacing.extraLarge))

            // --- 1. Top Header Area ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)) {
                    Text(
                        text = "Hi Nicholas,",
                        color = TextBlack, // Centralized color
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 24.sp
                        ) // Uses Signika Bold from Type.kt
                    )
                    Text(
                        text = "Good Morning!",
                        color = BrandRed, // Centralized Brand color
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp
                        ) // Uses Signika Medium from Type.kt
                    )
                }

                // Notification Bell with Theme-based Glassmorphism
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .shadow(
                            elevation = spacing.medium,
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

            Spacer(modifier = Modifier.height(spacing.large))

            // --- 2. Search Bar ---
            Box(modifier = Modifier.padding(horizontal = spacing.large)) {
                CommonSearchBar(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = "Search...",
                    trailingIcon = null
                )
            }

            Spacer(modifier = Modifier.height(spacing.large))

            // --- 3. Delivery Address ---
            Column(
                modifier = Modifier.padding(horizontal = spacing.large),
                verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)
            ) {
                Text(
                    text = "Delivery to",
                    color = DisabledGray, // Used centralized gray
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.extraSmall)
                ) {
                    Text(
                        text = "S12, Kira Rd, Kampala Capital City",
                        color = TextBlack,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Change Address",
                        tint = TextBlack,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.large))

            // --- 4. Banners ---
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.mediumSmall),
                contentPadding = PaddingValues(horizontal = spacing.large)
            ) {
                items(4) {
                    Box(
                        modifier = Modifier
                            .width(327.dp)
                            .height(150.dp)
                            .clip(MaterialTheme.appShapes.large) // Uses AppShapes from Shape.kt
                            .background(TextBlack.copy(alpha = 0.1f))
                    ) {
                        Text(
                            "Banner Image Placeholder",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // Banner Indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing.medium),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 12.dp, height = 6.dp)
                        .background(TextBlack, CircleShape)
                )
                repeat(3) {
                    Spacer(modifier = Modifier.width(spacing.extraSmall))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(TextBlack.copy(alpha = 0.2f), CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.large))

            // --- 5. Loyalty Points Statistics Card ---
            CommonLoyaltyCard(
                points = 957,
                onRedeemClick = { /* Handle Redeem Action */ },
                modifier = Modifier.padding(horizontal = spacing.large)
            )

            Spacer(modifier = Modifier.height(spacing.large))

            // --- 6. Recent Orders ---
            CommonSectionHeader(
                title = "Recent Order",
                actionText = "View All",
                onActionClick = { /* Handle View All Action */ },
                modifier = Modifier.padding(horizontal = spacing.large)
            )

            Spacer(modifier = Modifier.height(spacing.mediumSmall))

            // Order Card List
            LazyRow(
                contentPadding = PaddingValues(horizontal = spacing.large),
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                items(2) {
                    CommonRecentOrderCard(
                        orderId = "#MHO00300010",
                        date = "Placed on 17 Oct, 2025",
                        amount = "1240.00",
                        status = "Order delivered"
                    )
                }
            }

            // Extra padding for scrollability above bottom bar
            Spacer(modifier = Modifier.height(spacing.huge))
        }
    }
}