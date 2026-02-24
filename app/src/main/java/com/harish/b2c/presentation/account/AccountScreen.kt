package com.harish.b2c.presentation.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.core.components.AccountMenuItem
import com.harish.b2c.core.components.CommonBottomBar
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.core.components.QuickActionCard
import com.harish.b2c.ui.theme.*
import com.harish.b2c.R
@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    GradientBackground {
        AccountScreen()
    }
}
@Composable
fun AccountScreen(
) {
    Scaffold(
        containerColor = Color.Transparent, // Let GradientBackground show through if added later

        bottomBar = {
            CommonBottomBar(
                selectedIndex = 1, // 1 representing the 'Products' tab
                onItemSelected = { index ->
                    // Handle Navigation based on index
                },
                onFabClick = {
                    // Handle FAB (e.g., Cart) click
                }, modifier = Modifier.navigationBarsPadding()
            )

        }

    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Top App Bar Area (Custom for this screen as per Figma)
            // Top Section with Overlapping Profile
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp).statusBarsPadding()
            ) {

                // Top Row (Back + Points)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back Button
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, TextBlack.copy(alpha = 0.08f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "back",
                            tint = TextBlack
                        )
                    }

                    // Loyalty Points Badge
                    Row(
                        modifier = Modifier
                            .background(LoyaltyBg, RoundedCornerShape(16.dp))
                            .border(2.dp, LoyaltyBorder, RoundedCornerShape(16.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Coin",
                            tint = OrderIconOrange,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "1080",
                            style = AppTypography.titleMedium.copy(fontSize = 15.sp),
                            color = DarkGrey
                        )
                    }
                }

                // Profile Image (Overlapping)
                Box(
                    modifier = Modifier
                        .size(124.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = 20.dp) // 👈 controls how much it overlaps
                        .background(White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.dark_man),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = TextBlack.copy(alpha = 0.7f)
                    )
                }
            }
            // Profile Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Jorden Smith",
                    style = AppTypography.headlineLarge.copy(fontSize = 24.sp),
                    color = TextBlack
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "+256 78 45 61230",
                    style = AppTypography.bodyLarge.copy(fontSize = 18.sp),
                    color = TextGrey
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            // Quick Actions Row (Your Orders / Need Help)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(19.dp)
            ) {
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Your Orders",
                    icon = { Icon(painter = painterResource(R.drawable.cart), null, tint = OrderIconOrange) },
                    bgColor = OrderIconOrange.copy(alpha = 0.2f),
                    borderColor = OrderIconOrange,
                    onClick = { /* Navigate to Orders */ }
                )
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Need Help?",
                    icon = { Icon(painter = painterResource(R.drawable.notepad), null, tint = BrandPurple) },
                    bgColor = BrandPurple.copy(alpha = 0.2f),
                    borderColor = BrandPurple,
                    onClick = { /* Navigate to Support */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Menu Sheet (White Background with Top Rounded Corners)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    // Use weight or minHeight if you want it to stretch down exactly,
                    // but wrapContentHeight allows it to scale with items.
                    .wrapContentHeight(),
                color = White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 24.dp,
                            bottom = 120.dp
                        ), // Extra bottom padding for Bottom Nav visibility
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AccountMenuItem(
                        title = "Personal Info",
                        icon = { Icon(painter = painterResource(R.drawable.man), null, tint = Color(0xFFFB9400)) },
                        iconBgColor = Color(0xFFFB9400),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Delivery Address",
                        icon = { Icon(painter = painterResource(R.drawable.location), null, tint = Color(0xFF6949FF)) },
                        iconBgColor = Color(0xFF6949FF),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Payment Methods",
                        icon = { Icon(painter = painterResource(R.drawable.payment_card), null, tint = Color(0xFF246BFD)) },
                        iconBgColor = Color(0xFF246BFD),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Notifications",
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.notify),
                                null,
                                tint = Color(0xFFFF5A5F)
                            )
                        },
                        iconBgColor = Color(0xFFFF5A5F),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Terms of use",
                        icon = { Icon(painter = painterResource(R.drawable.file), null, tint = Color(0xFF12D18E)) },
                        iconBgColor = Color(0xFF12D18E),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Privacy policy",
                        icon = { Icon(painter = painterResource(R.drawable.shield), null, tint = Color(0xFFFB9400)) },
                        iconBgColor = Color(0xFFFB9400),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "About Us",
                        icon = { Icon(painter = painterResource(R.drawable.info), null, tint = Color(0xFF6949FF)) },
                        iconBgColor = Color(0xFF6949FF),
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Logout",
                        icon = { Icon(painter = painterResource(R.drawable.logout), null, tint = BrandRed) },
                        iconBgColor = BrandRed,
                        titleColor = BrandRed,
                        onClick = { /* Handle Logout */ }
                    )
                }
            }
        }
    }
}