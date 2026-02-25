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
import androidx.compose.material.icons.filled.Star
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    GradientBackground {
        AccountScreen()
    }
}

@Composable
fun AccountScreen() {
    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            CommonBottomBar(
                selectedIndex = 3,
                onItemSelected = { },
                onFabClick = { },
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.mediumLarge)
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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

                    Row(
                        modifier = Modifier
                            .background(LoyaltyBg, Shapes.medium)
                            .border(2.dp, LoyaltyBorder, Shapes.medium)
                            .padding(horizontal = Spacing.small, vertical = 6.dp),
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

                Box(
                    modifier = Modifier
                        .size(124.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = Spacing.mediumLarge)
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
                    onClick = { /* Navigate */ }
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
                            top = Spacing.large,
                            bottom = 120.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                ) {
                    AccountMenuItem(title = "Personal Info", icon = { Icon(painter = painterResource(R.drawable.man), null, tint = MenuOrange) }, iconBgColor = MenuOrange, onClick = { })
                    AccountMenuItem(title = "Delivery Address", icon = { Icon(painter = painterResource(R.drawable.location), null, tint = MenuPurple) }, iconBgColor = MenuPurple, onClick = { })
                    AccountMenuItem(title = "Payment Methods", icon = { Icon(painter = painterResource(R.drawable.payment_card), null, tint = MenuBlue) }, iconBgColor = MenuBlue, onClick = { })
                    AccountMenuItem(title = "Notifications", icon = { Icon(painter = painterResource(R.drawable.notify), null, tint = MenuRed) }, iconBgColor = MenuRed, onClick = { })
                    AccountMenuItem(title = "Terms of use", icon = { Icon(painter = painterResource(R.drawable.file), null, tint = MenuGreen) }, iconBgColor = MenuGreen, onClick = { })
                    AccountMenuItem(title = "Privacy policy", icon = { Icon(painter = painterResource(R.drawable.shield), null, tint = MenuOrange) }, iconBgColor = MenuOrange, onClick = { })
                    AccountMenuItem(title = "About Us", icon = { Icon(painter = painterResource(R.drawable.info), null, tint = MenuPurple) }, iconBgColor = MenuPurple, onClick = { })
                    AccountMenuItem(title = "Logout", icon = { Icon(painter = painterResource(R.drawable.logout), null, tint = BrandRed) }, iconBgColor = BrandRed, titleColor = BrandRed, onClick = { })
                }
            }
        }
    }
}