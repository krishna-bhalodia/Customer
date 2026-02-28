package com.harish.b2c.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.mediumTitle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.R

@Preview(showBackground = true)
@Composable
fun UpdatedAppBarPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        // Example 1: Standard with Title only
        CommonAppBar(title = "Settings", onBackClick = {})

        // Example 2: Title + Trailing Icon (e.g., Edit)
        CommonAppBar(
            title = "Profile",
            onBackClick = {},
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        // Example 3: No Title + Trailing Text (e.g., Skip button)
        CommonAppBar(
            onBackClick = {},
            trailingContent = {
                Text(
                    text = "Skip",
                    color = BrandRed,
                    modifier = Modifier.clickable { /* action */ }
                )
            }
        )
    }
}

@Composable
fun CommonAppBar(
    modifier: Modifier = Modifier,
    title: String? = null, // Made optional
    iconContainer: Dp = 40.dp,
    componentHeight: Dp = 56.dp,
    onBackClick: () -> Unit,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null // Added trailing slot
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(componentHeight)
            .padding(horizontal = Spacing.large),
        contentAlignment = Alignment.CenterStart
    ) {
        // 1. Back Button (Leading)
        Box(
            modifier = Modifier
                .size(iconContainer)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = TextBlack.copy(alpha = 0.08f),
                    shape = CircleShape
                )
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = TextBlack,
                modifier = Modifier.size(Spacing.mediumLarge)
            )
        }

        // 2. Title (Optional & Centered)
        title?.let {
            Text(
                text = it,
                color = TextBlack,
                style = AppTypography.mediumTitle.copy(fontSize = 20.sp),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        // 3. Trailing Content (Optional)
        trailingContent?.let {
            Box(
                modifier = Modifier.align(Alignment.CenterEnd),
                content = it
            )
        }
    }
}