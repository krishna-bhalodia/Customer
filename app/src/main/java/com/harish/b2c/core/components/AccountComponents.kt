package com.harish.b2c.core.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White

@Composable
fun AccountMenuItem(
    title: String,
    icon: @Composable () -> Unit,
    iconBgColor: Color,
    titleColor: Color = TextBlack,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Icon Container with 8% opacity background
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(iconBgColor.copy(alpha = 0.08f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        // Title
        Text(
            text = title,
            style = AppTypography.bodyLarge.copy(fontSize = 18.sp),
            color = titleColor,
            modifier = Modifier.weight(1f)
        )

        // Chevron
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = TextBlack,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun QuickActionCard(
    title: String,
    icon: @Composable () -> Unit,
    bgColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() },
        color = White.copy(alpha = 0.5f),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, White.copy(alpha = 0.5f)),
        shadowElevation = 0.dp // To match the soft drop shadow from Figma, consider using your GlassShadow utility here if needed
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(bgColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = AppTypography.bodyLarge.copy(fontSize = 16.sp),
                color = TextBlack
            )
        }
    }
}