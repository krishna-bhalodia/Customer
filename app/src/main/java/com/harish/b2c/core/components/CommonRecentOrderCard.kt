package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.*

@Composable
fun CommonRecentOrderCard(
    orderId: String,
    date: String,
    amount: String,
    status: String,
    modifier: Modifier = Modifier
) {
    val spacing = Spacing // Accessing theme spacing

    Column(
        modifier = modifier
            .width(327.dp)
            .background(White, Shapes.large) // Use AppShapes
            .border(1.dp, BorderLight, Shapes.large)
            .padding(spacing.mediumSmall), // 12.dp from theme
        verticalArrangement = Arrangement.spacedBy(spacing.mediumSmall)
    ) {

        // Top Row: Icon, Order Info, and Price/Arrow
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Side: Icon and Text Info
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.mediumSmall)
            ) {
                // Cart Icon Box
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(OrderIconBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Order",
                        tint = OrderIconOrange,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Text Column
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)
                ) {
                    Text(
                        text = orderId,
                        color = TextBlack,
                        style = AppTypography.bodyLarge.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ) // Uses Signika Regular/Medium
                    )
                    Text(
                        text = date,
                        color = TextGrey,
                        style = AppTypography.labelSmall.copy(
                            fontSize = 12.sp
                        ) // Uses Signika Light
                    )
                }
            }

            // Right Side: Amount and Next Arrow
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.mediumSmall)
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = "AED",
                        color = TextGrey,
                        style = AppTypography.labelSmall.copy(fontSize = 12.sp)
                    )

                    Text(
                        text = amount,
                        color = TextBlack,
                        style = AppTypography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                // Arrow Box
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(White20, CircleShape) // Using White.copy(alpha=0.2f) from Color.kt
                        .border(1.dp, White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "View Details",
                        tint = TextBlack,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // Bottom Row: Status Indicator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.extraSmall)
        ) {
            Text(
                text = status,
                color = TextBlack,
                style = AppTypography.bodyLarge.copy(fontSize = 12.sp)
            )

            // Custom Status Checkmark Circle
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .shadow(
                        elevation = spacing.large,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.08f)
                    )
                    .background(SuccessGreen, CircleShape)
                    .border(1.dp, SuccessGreen.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Status",
                    tint = White,
                    modifier = Modifier.size(10.dp)
                )
            }
        }
    }
}