package com.harish.b2c.presentation.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.core.components.AccountMenuItem
import com.harish.b2c.core.components.CommonBottomBar
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.core.components.QuickActionCard
import com.harish.b2c.ui.theme.*
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.utils.glassShadow
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.presentation.products.LogoutBottomSheet

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    GradientBackground {
        AccountScreen(navController = NavController(LocalContext.current))
    }
}

@Composable
fun AccountScreen(navController: NavController) {
    var showLogoutSheet by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // ✅ Stack AppBar and Profile Image to allow overlapping
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                CommonAppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .align(Alignment.TopCenter), // Keep bar at top
                    onBackClick = { navController.popBackStack() },
                    trailingContent = {
                        Row(
                            modifier = Modifier
                                .background(LoyaltyBg, Shapes.medium)
                                .border(2.dp, LoyaltyBorder, Shapes.medium)
                                .padding(horizontal = Spacing.small, vertical = 6.dp)
                                .clickable { navController.navigate(NavigationScreen.LoyaltyPointsScreen.route) },
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
                )

                // ✅ Profile Image Box
                Box(
                    modifier = Modifier
                        .size(124.dp)
                        // This offset moves it "up" into the AppBar area
                        .offset(y = 40.dp)
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
            Spacer(modifier = Modifier.height(48.dp))
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.large),
                horizontalArrangement = Arrangement.spacedBy(19.dp)
            ) {
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Your Orders",
                    icon = { Icon(painter = painterResource(R.drawable.cart), null, tint = OrderIconOrange) },
                    bgColor = OrderIconOrange.copy(alpha = 0.2f),
                    borderColor = OrderIconOrange,
                    onClick = { navController.navigate(NavigationScreen.OrdersScreen.route) }
                )
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = "Need Help?",
                    icon = { Icon(painter = painterResource(R.drawable.notepad), null, tint = BrandPurple) },
                    bgColor = BrandPurple.copy(alpha = 0.2f),
                    borderColor = BrandPurple,
                    onClick = { /* Navigate */ }
                )
            }

            Spacer(modifier = Modifier.height(Spacing.large))

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
                            top = Spacing.large
                        ),
                    verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                ) {
                    AccountMenuItem(title = "Personal Info", icon = { Icon(painter = painterResource(R.drawable.man), null, tint = MenuOrange) }, iconBgColor = MenuOrange, onClick = {navController.navigate(NavigationScreen.ProfileScreen.route) })
                    AccountMenuItem(title = "Delivery Address", icon = { Icon(painter = painterResource(R.drawable.location), null, tint = MenuPurple) }, iconBgColor = MenuPurple, onClick = {navController.navigate(NavigationScreen.SavedAddressesScreen.route) })
                    AccountMenuItem(title = "Payment Methods", icon = { Icon(painter = painterResource(R.drawable.payment_card), null, tint = MenuBlue) }, iconBgColor = MenuBlue, onClick = { navController.navigate(NavigationScreen.PaymentMethodScreen.route)})
                    AccountMenuItem(title = "Notifications", icon = { Icon(painter = painterResource(R.drawable.notify), null, tint = MenuRed) }, iconBgColor = MenuRed, onClick = { navController.navigate(NavigationScreen.NotificationsScreen.route)})
                    AccountMenuItem(title = "Terms of use", icon = { Icon(painter = painterResource(R.drawable.file), null, tint = MenuGreen) }, iconBgColor = MenuGreen, onClick = { })
                    AccountMenuItem(title = "Privacy policy", icon = { Icon(painter = painterResource(R.drawable.shield), null, tint = MenuOrange) }, iconBgColor = MenuOrange, onClick = { })
                    AccountMenuItem(title = "About Us", icon = { Icon(painter = painterResource(R.drawable.info), null, tint = MenuPurple) }, iconBgColor = MenuPurple, onClick = { })
                    AccountMenuItem(title = "Logout", icon = { Icon(painter = painterResource(R.drawable.logout), null, tint = BrandRed) }, iconBgColor = BrandRed, titleColor = BrandRed, onClick = { showLogoutSheet = true })
                }
            }
        }
        if (showLogoutSheet) {
            LogoutBottomSheet(
                onDismiss = { showLogoutSheet = false },
                onConfirmLogout = {
                    showLogoutSheet = false
                    // navController.navigate(NavigationScreen.LoginScreen.route) { popUpTo(0) }
                }
            )
        }
    }
