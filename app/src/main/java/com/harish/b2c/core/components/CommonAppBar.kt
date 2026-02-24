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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.Spacing

@Preview(
    name = "CommonAppBar - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonAppBarPreview() {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Default
            CommonAppBar(
                title = "Login",
                onBackClick = {}
            )

            // Long Title
            CommonAppBar(
                title = "Personal Information",
                onBackClick = {}
            )

            // Very Long Title
            CommonAppBar(
                title = "Verify Your Mobile Number",
                onBackClick = {}
            )
        }

}

@Composable
fun CommonAppBar(
    title: String,
    modifier: Modifier = Modifier,
    iconContainer: Dp = 40.dp,
    componentHeight: Dp = 56.dp,
    onBackClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(componentHeight)
            .padding(horizontal = Spacing.large)
    ) {
        // Back Button
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
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

        // Title
        Text(
            text = title,
            color = TextBlack,
            style = AppTypography.mediumTitle.copy(fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}