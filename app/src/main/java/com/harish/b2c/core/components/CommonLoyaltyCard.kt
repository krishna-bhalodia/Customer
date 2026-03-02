package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Composable
fun CommonLoyaltyCard(
    points: Int,
    onRedeemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = Spacing // Centralized Spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LoyaltyBg, Shapes.large) // Use AppShapes
            .border(2.dp, LoyaltyBorder, Shapes.large)
            .padding(spacing.medium), // 16.dp
        verticalArrangement = Arrangement.spacedBy(spacing.medium)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Icon Container
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = "Loyalty Points Icon",
                    tint = Color.Unspecified // Keep original coin colors
                )
            }

            Spacer(modifier = Modifier.width(spacing.medium))

            // Points Text
            Column {
                Text(
                    text = points.toString(),
                    style = AppTypography.headlineLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ), // Signika Bold
                    color = DarkGrey
                )
                Text(
                    text = "Loyalty Points Earned",
                    style = AppTypography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ), // Signika Regular
                    color = TextGrey // Centralized secondary text color
                )
            }
        }

        // Action Button
        Button(
            onClick = onRedeemClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = White,
                contentColor = TextBlack
            ),
            shape = Shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp), // Standardized height
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Redeem Now",
                style = AppTypography.titleMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "View Details",
                tint = TextBlack,
                modifier = Modifier.size(16.dp) // 16x16 inner frame size
            )

        }
    }
}