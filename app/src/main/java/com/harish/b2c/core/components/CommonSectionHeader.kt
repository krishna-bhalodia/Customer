package com.harish.b2c.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.TextBlack

@Composable
fun CommonSectionHeader(
    title: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = TextBlack,
            style = AppTypography.titleLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        if (actionText != null && onActionClick != null) {
            Text(
                text = actionText,
                color = BrandRed,
                style = AppTypography.titleLarge.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}