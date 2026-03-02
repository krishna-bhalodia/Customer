package com.harish.b2c.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonItemCard
import com.harish.b2c.core.components.CommonLoyaltyCard
import com.harish.b2c.core.components.CommonSearchBar
import com.harish.b2c.core.components.CommonSectionHeader
import com.harish.b2c.core.utils.glassShadow
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BorderLight
import com.harish.b2c.ui.theme.BorderWhite
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.DisabledGray
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White20
import com.harish.b2c.ui.theme.darkShadow

@Composable
fun HomeScreen(navController: NavController) {

    var searchQuery by remember { mutableStateOf("") }
    var bottomNavIndex by remember { mutableIntStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(Spacing.extraLarge))

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
                    color = TextBlack,
                    style = AppTypography.headlineLarge // Removed hardcoded 24.sp
                )
                Text(
                    text = "Good Morning!",
                    color = BrandRed,
                    style = AppTypography.titleMedium // Removed hardcoded 14.sp
                )
            }

            // Notification Bell


            // 2. The Notification Indicator (Half inside, half outside)
            BadgedBox(
                badge = {
                    Badge(containerColor = BrandRed,modifier = Modifier.size(8.dp))
                    // Optional: If you need to nudge the badge further outward/inward,

                }
            ) {
                // IconButton natively handles the circular ripple and centers the Icon
                IconButton(
                    onClick = { navController.navigate(NavigationScreen.NotificationsScreen.route) },
                    modifier = Modifier
                        .size(Spacing.huge) // Sizing the content makes the badge anchor correctly
                        .glassShadow(
                            color = darkShadow,
                            alpha = 0.08f,
                            shape = CircleShape,
                            shadowRadius = 16.dp,
                            offsetY = 0.dp
                        )
                        .background(White20, CircleShape)
                        .border(1.dp, BorderWhite, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.notification),
                        contentDescription = "Notifications",
                        tint = TextBlack,
                        modifier = Modifier.size(Spacing.mediumLarge)
                    )
                }
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
                color = DisabledGray,
                style = AppTypography.labelSmall
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
            ) {
                Text(
                    text = "S12, Kira Rd, Kampala Capital City",
                    color = TextBlack,
                    style = AppTypography.bodyLarge
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Change Address",
                    tint = TextBlack,
                    modifier = Modifier.size(Spacing.mediumLarge)
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
                        .width(327.dp) // Specific UI design width
                        .height(150.dp)
                        .clip(Shapes.large)
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
                    .size(width = Spacing.mediumSmall, height = 6.dp)
                    .background(TextBlack, CircleShape)
            )
            repeat(3) {
                Spacer(modifier = Modifier.width(Spacing.extraSmall))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(BorderLight, CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        CommonLoyaltyCard(
            points = 957,
            onRedeemClick = { navController.navigate(NavigationScreen.LoyaltyPointsScreen.route) },
            modifier = Modifier.padding(horizontal = Spacing.large)
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        CommonSectionHeader(
            title = "Recent Order",
            actionText = "View All",
            onActionClick = { navController.navigate(NavigationScreen.OrdersScreen.route) },
            modifier = Modifier.padding(horizontal = Spacing.large)
        )

        Spacer(modifier = Modifier.height(Spacing.mediumSmall))

        LazyRow(
            contentPadding = PaddingValues(horizontal = Spacing.large),
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            items(2) {
                CommonItemCard(
                    title = "#MHO00300010",
                    subtitle = "Placed on 17 Oct, 2025",
                    price = "1240.00",
                    statusText = "Order Delivered",
                    showTrailingArrow = true,
                    imageUrl = R.drawable.cart,
                    modifier = Modifier.width(327.dp),
                    onClick = { navController.navigate(NavigationScreen.OrderDetailsScreen.route) }
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.huge))
    }
}
