package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

enum class NotificationType {
    DELIVERY, SUCCESS, PROMO
}

@Composable
fun CommonNotificationCard(
    title: String,
    description: String,
    time: String,
    isUnread: Boolean,
    type: NotificationType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Unread is solid white, read is 40% white
    val bgColor = if (isUnread) White else White.copy(alpha = 0.4f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(bgColor, Shapes.medium)
            .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.medium)
            .clip(Shapes.medium)
            .clickable { onClick() }
            .padding(horizontal = Spacing.medium, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Texts Column
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            Text(
                text = title,
                style = AppTypography.titleMedium.copy(fontSize = 14.sp),
                color = TextBlack
            )
            Text(
                text = description,
                style = AppTypography.bodyLarge.copy(fontSize = 12.sp, lineHeight = 16.sp),
                color = TextGrey
            )
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        // Time & Icon Column
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(Spacing.mediumSmall)
        ) {
            Text(
                text = time,
                style = AppTypography.bodyLarge.copy(fontSize = 12.sp),
                color = TextGrey
            )

            // Resolve Icon Background and Resource based on Type
            val (iconBgColor, iconRes, tintColor) = when (type) {
                NotificationType.DELIVERY -> Triple(WarningOrange, R.drawable.cart, White)
                NotificationType.SUCCESS -> Triple(SuccessGreen, R.drawable.truck, White)
                NotificationType.PROMO -> Triple(Color(0xFFFFE3A2), R.drawable.coin, Color.Unspecified)
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(iconBgColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = tintColor,
                    modifier = Modifier.size(Spacing.mediumLarge)
                )
            }
        }
    }
}