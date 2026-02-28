package com.harish.b2c.presentation.loyalty

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonRewardCard
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoyaltyPointsScreenPreview() {
    GradientBackground { LoyaltyPointsScreen(navController = NavController(LocalContext.current))}
}

@Composable
fun LoyaltyPointsScreen(
    navController: NavController
) {
    var showRedeemPopup by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()
        .blur(radius = if (showRedeemPopup) 12.dp else 0.dp) // Apply blur effect
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(640.dp)
        ) {

            val canvasWidth = size.width
            val ellipseWidth = canvasWidth * 2f
            val ellipseHeight = size.height * 1.1f

            // Calculate exact bounding boxes for our shapes
            val leftBound = (canvasWidth - ellipseWidth) / 2f
            val topBound = -ellipseHeight * 0.48f
            val rightBound = leftBound + ellipseWidth
            val bottomBound = topBound + ellipseHeight

            // 1️⃣ Beige Background Ellipse
            drawOval(
                color = Color(0xFFFFF5DC),
                topLeft = Offset(x = leftBound, y = topBound),
                size = Size(width = ellipseWidth, height = ellipseHeight)
            )

            // 2️⃣ Tapered Yellow Arc (Sharp ends, thick center)
            val centerThickness = 8.dp.toPx() // 🔥 Control center thickness here

            val outerPath = Path().apply {
                addOval(Rect(left = leftBound, top = topBound, right = rightBound, bottom = bottomBound))
            }

            val innerPath = Path().apply {
                addOval(Rect(
                    left = leftBound,
                    top = topBound + centerThickness,
                    right = rightBound,
                    bottom = bottomBound - centerThickness
                ))
            }

            val taperedLinePath = Path().apply {
                op(outerPath, innerPath, PathOperation.Difference)
            }

            drawPath(
                path = taperedLinePath,
                color = Color(0xFFFFC50B)
            )
        }

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Loyalty Points",
                    onBackClick = { navController.popBackStack() },
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = Spacing.mediumLarge)
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(Spacing.large))

                Image(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = null,
                    modifier = Modifier.size(79.dp)
                )

                Spacer(modifier = Modifier.height(Spacing.mediumSmall))

                Text(
                    text = "Total Loyalty Points",
                    style = AppTypography.bodyLarge,
                    color = TextBlack
                )

                Text(
                    text = "957",
                    style = AppTypography.headlineLarge.copy(fontSize = 24.sp),
                    color = DarkGrey
                )

                Spacer(modifier = Modifier.height(Spacing.large))

                Row(
                    modifier = Modifier
                        .clip(Shapes.large)
                        .background(White)
                        .clickable{navController.navigate(NavigationScreen.RedeemHistory.route)}
                        .padding(horizontal = Spacing.medium, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                ) {
                    Text(
                        text = "Redeem History",
                        style = AppTypography.bodyLarge.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextBlack
                    )

                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .border(1.dp, TextBlack, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = TextBlack,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Spacing.extrahuge))

                // Reward Items Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = Spacing.large, vertical = Spacing.small),
                    horizontalArrangement = Arrangement.spacedBy(19.dp),
                    verticalArrangement = Arrangement.spacedBy(19.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    // 1. Green Card
                    item {
                        CommonRewardCard(
                            title = "Honeymoon Package Free Tickets",
                            coinCost = "2500",
                            imageRes = R.drawable.sm_tang, // Sample Image
                            backgroundColor = Color(0xFFD9FFE3), // RewardGreen
                            onClick = { showRedeemPopup = true}
                        )
                    }

                    // 2. Purple Card
                    item {
                        CommonRewardCard(
                            title = "On All Oner Products 3 Carton free",
                            coinCost = "3000",
                            imageRes = R.drawable.oner_apple, // Sample Image
                            backgroundColor = Color(0xFFD7D7FF), // RewardPurple
                            onClick = { showRedeemPopup = true }
                        )
                    }

                    // 3. Orange Card
                    item {
                        CommonRewardCard(
                            title = "2 Days, 1 Night Free on Hotel Taj",
                            coinCost = "10000",
                            imageRes = R.drawable.orchid, // Sample Image
                            backgroundColor = Color(0xFFFFEACA), // RewardOrange
                            onClick = { showRedeemPopup = true }
                        )
                    }

                    // 4. Blue Card
                    item {
                        CommonRewardCard(
                            title = "2 Chocolate Carton Free of ICAM",
                            coinCost = "1500",
                            imageRes = R.drawable.predator, // Sample Image
                            backgroundColor = Color(0xFFCAE7FF), // RewardBlue
                            onClick = { showRedeemPopup = true }
                        )
                    }

                    // 5. Disabled / Grey Card
                    item {
                        CommonRewardCard(
                            title = "Honeymoon Package Free Tickets",
                            coinCost = "2500",
                            imageRes = R.drawable.sm_tang,
                            backgroundColor = Color(0xFFF0F0F0), // RewardDisabled
                            isEnabled = false
                        )
                    }

                    // 6. Disabled / Grey Card
                    item {
                        CommonRewardCard(
                            title = "On All Oner Products 3 Carton free",
                            coinCost = "3000",
                            imageRes = R.drawable.oner_apple,
                            backgroundColor = Color(0xFFF0F0F0), // RewardDisabled
                            isEnabled = false
                        )
                    }
                }
            }
        }
    }

    if (showRedeemPopup) {
        RedeemPointsPopup(
            onDismiss = { showRedeemPopup = false },
            onRedeem = {
                showRedeemPopup = false
                /* Add API call or navigation here later */
            }
        )
    }
}