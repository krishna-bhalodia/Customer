package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BorderLight
import com.harish.b2c.ui.theme.OrderIconBg
import com.harish.b2c.ui.theme.OrderIconOrange
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.White20

@Composable
fun CommonRecentOrderCard(
    orderId: String,
    date: String,
    amount: String,
    status: String,
    deliveryIcon: Int = R.drawable.green_check,
    modifier: Modifier = Modifier
) {
    val spacing = Spacing // Accessing theme spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
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
                        painter = painterResource(R.drawable.cart),
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
                        .background(
                            White20,
                            CircleShape
                        ) // Using White.copy(alpha=0.2f) from Color.kt
                        .border(1.dp, White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
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



            Icon(
                painterResource(deliveryIcon),
                contentDescription = "Status",
                tint = Color.Unspecified,
                modifier = Modifier.size(14.dp)
            )

        }
    }
}