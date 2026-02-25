package com.harish.b2c.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Composable
fun CommonRewardCard(
    title: String,
    coinCost: String,
    imageRes: Int,
    backgroundColor: Color,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Handling Disabled state styling directly mapped from CSS (opacity 0.5 and greyscale image)
    val cardAlpha = if (isEnabled) 1f else 0.5f
    val imageColorFilter = if (isEnabled) null else ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, Shapes.medium)
            .clip(Shapes.medium)
            .clickable(enabled = isEnabled) { onClick() }
            .padding(Spacing.medium)
            .alpha(cardAlpha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Product Image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            colorFilter = imageColorFilter,
            modifier = Modifier
                .size(62.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(Spacing.small))

        // Title
        Text(
            text = title,
            style = AppTypography.bodyLarge.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 15.sp,
                letterSpacing = (-0.3).sp
            ),
            color = TextBlack,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.extraSmall))

        // Coins Cost
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "for $coinCost coins",
                style = AppTypography.bodyLarge.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = TextGrey
            )
            Image(
                painter = painterResource(id = R.drawable.coin), // Use your existing coin vector
                contentDescription = "Coins",
                modifier = Modifier.size(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(Spacing.small))

        // View Button
        Box(
            modifier = Modifier
                .background(TextBlack, RoundedCornerShape(44.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "View",
                fontFamily = Signika,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = White
            )
        }
    }
}