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
    showSystemUi = true
)
@Composable
fun AccountScreenPreview() {
    GradientBackground {
        AccountScreen()
    }
}

@Composable
fun AccountScreen() {
    Scaffold(
        containerColor = Color.Transparent, // Let GradientBackground show through
        bottomBar = {
            CommonBottomBar(
                selectedIndex = 3, // 3 representing the 'Account' tab
                onItemSelected = { index ->
                    // Handle Navigation based on index
                },
                onFabClick = {
                    // Handle FAB (e.g., Cart) click
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Top Section with Overlapping Profile
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.mediumLarge)
                    .statusBarsPadding()
            ) {

                // Top Row (Back + Points)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back Button
                    Box(
                        modifier = Modifier
                            .size(Spacing.extraExtraLarge)
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
                            .background(LoyaltyBg, RoundedCornerShape(Spacing.medium))
                            .border(2.dp, LoyaltyBorder, RoundedCornerShape(Spacing.medium))
                            .padding(horizontal = 12.dp, vertical = 6.dp), // Kept specific inner component padding
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Coin",
                            tint = OrderIconOrange,
                            modifier = Modifier.size(Spacing.mediumLarge)
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
                        .size(124.dp) // Specific profile image wrapper size
                        .align(Alignment.BottomCenter)
                        .offset(y = Spacing.mediumLarge) // Controls overlap
                        .background(White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.dark_man),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp), // Specific icon size
                        tint = TextBlack.copy(alpha = 0.7f)
                    )
                }
            }

            // Profile Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(Spacing.medium))

                Text(
                    text = "Jorden Smith",
                    style = AppTypography.headlineLarge.copy(fontSize = 24.sp),
                    color = TextBlack
                )
                Spacer(modifier = Modifier.height(Spacing.extraSmall))
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
                    .padding(horizontal = Spacing.large),
                horizontalArrangement = Arrangement.spacedBy(19.dp) // Specific component gap
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

            Spacer(modifier = Modifier.height(Spacing.large))

            // Bottom Menu Sheet (White Background with Top Rounded Corners)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = White,
                shape = RoundedCornerShape(topStart = Spacing.large, topEnd = Spacing.large),
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = Spacing.large,
                            bottom = 120.dp // Extra bottom padding for Bottom Nav visibility
                        ),
                    verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                ) {
                    AccountMenuItem(
                        title = "Personal Info",
                        icon = { Icon(painter = painterResource(R.drawable.man), null, tint = MenuOrange) },
                        iconBgColor = MenuOrange,
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Delivery Address",
                        icon = { Icon(painter = painterResource(R.drawable.location), null, tint = MenuPurple) },
                        iconBgColor = MenuPurple,
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Payment Methods",
                        icon = { Icon(painter = painterResource(R.drawable.payment_card), null, tint = MenuBlue) },
                        iconBgColor = MenuBlue,
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Notifications",
                        icon = { Icon(painter = painterResource(R.drawable.notify), null, tint = MenuRed) },
                        iconBgColor = MenuRed,
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Terms of use",
                        icon = { Icon(painter = painterResource(R.drawable.file), null, tint = MenuGreen) },
                        iconBgColor = MenuGreen,
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "Privacy policy",
                        icon = { Icon(painter = painterResource(R.drawable.shield), null, tint = MenuOrange) },
                        iconBgColor = MenuOrange,
                        onClick = { /* Navigate */ }
                    )
                    AccountMenuItem(
                        title = "About Us",
                        icon = { Icon(painter = painterResource(R.drawable.info), null, tint = MenuPurple) },
                        iconBgColor = MenuPurple,
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