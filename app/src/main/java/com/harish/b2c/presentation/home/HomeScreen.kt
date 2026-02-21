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
import androidx.compose.material.icons.filled.Notifications
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
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.appShapes
import com.harish.b2c.ui.theme.appSpacing
import com.harish.b2c.ui.theme.regularBody

@Composable
fun HomeScreen() {
    val spacing = MaterialTheme.appSpacing
    var searchQuery by remember { mutableStateOf("") }
    var bottomNavIndex by remember { mutableIntStateOf(0) }

    // Colors derived from CSS used in this top-level screen
    val greyText = Color(0xFF7C858C)

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            CommonBottomBar(
                selectedIndex = bottomNavIndex,
                onItemSelected = { bottomNavIndex = it },
                onFabClick = { /* Handle FAB click */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(spacing.extraLarge)) // Status bar padding equivalent

            // --- 1. Top Header Area ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Hi Nicholas,",
                        color = TextBlack,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = "Good Morning!",
                        color = BrandRed,
                        style = MaterialTheme.typography.regularBody.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                // Notification Glassmorphism Bell
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .shadow(16.dp, CircleShape, spotColor = BrandRed.copy(alpha = 0.08f))
                        .background(White.copy(alpha = 0.3f), CircleShape)
                        .border(1.dp, White.copy(alpha = 0.6f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(R.drawable.notification),
                        contentDescription = "Notifications",
                        tint = TextBlack,
                        modifier = Modifier.size(20.dp)
                    )
                    // Red Dot
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
                    trailingIcon = null // Optional filter icon if needed
                )
            }

            Spacer(modifier = Modifier.height(spacing.large))

            // --- 3. Delivery Address ---
            Column(
                modifier = Modifier.padding(horizontal = spacing.large),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Delivery to",
                    color = greyText,
                    style = MaterialTheme.typography.regularBody.copy(fontSize = 12.sp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "S12, Kira Rd, Kampala Capital City",
                        color = TextBlack,
                        style = MaterialTheme.typography.regularBody.copy(fontSize = 14.sp)
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
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = spacing.large)
            ) {
                items(4) { // 4 Banners as per original implementation
                    Box(
                        modifier = Modifier
                            .width(327.dp)
                            .height(150.dp)
                            .clip(MaterialTheme.appShapes.large)
                            .background(TextBlack.copy(alpha = 0.1f)) // Placeholder for image
                    ) {
                        // Replace with AsyncImage or Image for "banner"
                        Text(
                            "Banner Image Placeholder",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // Banner Indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 12.dp, height = 6.dp)
                        .background(TextBlack, CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(TextBlack.copy(alpha = 0.2f), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(TextBlack.copy(alpha = 0.2f), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(TextBlack.copy(alpha = 0.2f), CircleShape)
                )
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

            Spacer(modifier = Modifier.height(12.dp))

            // Order Card List
            LazyRow(
                contentPadding = PaddingValues(horizontal = spacing.large),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
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

            Spacer(modifier = Modifier.height(40.dp)) // Extra padding so bottom bar doesn't cover content
        }
    }
}