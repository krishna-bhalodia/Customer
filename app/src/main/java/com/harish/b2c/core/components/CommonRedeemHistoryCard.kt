package com.harish.b2c.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Composable
fun CommonRedeemHistoryCard(
    title: String,
    description: String,
    points: Int,
    time: String,
    modifier: Modifier = Modifier
) {
    val isPositive = points > 0
    val pointsText = if (isPositive) "+$points" else "$points"
    val pointsColor = if (isPositive) SuccessGreen else BrandRed

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White.copy(alpha = 0.4f), Shapes.medium)
            .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.medium)
            .padding(horizontal = Spacing.medium, vertical = 12.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left Column: Title and Description
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                style = AppTypography.titleMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = TextBlack
            )
            Text(
                text = description,
                style = AppTypography.bodyLarge.copy(
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                ),
                color = TextGrey
            )
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        // Right Column: Points and Time
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Points Badge Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = pointsText,
                    style = AppTypography.titleMedium.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = pointsColor
                )
                Image(
                    painter = painterResource(id = R.drawable.coin), // Utilizing existing coin icon
                    contentDescription = "Coins",
                    modifier = Modifier.size(16.dp)
                )
            }

            // Time
            Text(
                text = time,
                style = AppTypography.bodyLarge.copy(fontSize = 12.sp),
                color = TextGrey
            )
        }
    }
}