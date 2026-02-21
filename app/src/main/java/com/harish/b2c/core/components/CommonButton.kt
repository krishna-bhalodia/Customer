package com.harish.b2c.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.DisabledGray
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.appShapes
import com.harish.b2c.ui.theme.appSpacing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "CommonButton - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonButtonPreview() {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Filled Enabled
            CommonButton(
                text = "Filled Enabled",
                onClick = {}
            )

            // Filled Disabled
            CommonButton(
                text = "Filled Disabled",
                isEnabled = false,
                onClick = {}
            )

            // Outlined Enabled
            CommonButton(
                text = "Outlined Enabled",
                isOutlined = true,
                onClick = {}
            )

            // Outlined Disabled
            CommonButton(
                text = "Outlined Disabled",
                isOutlined = true,
                isEnabled = false,
                onClick = {}
            )

            // Leading Icon
            CommonButton(
                text = "Leading Icon",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                },
                onClick = {}
            )

            // Trailing Icon
            CommonButton(
                text = "Trailing Icon",
                trailingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                },
                onClick = {}
            )

            // Both Icons
            CommonButton(
                text = "Both Icons",
                leadingIcon = {
                    Icon(Icons.Default.Favorite, null)
                },
                trailingIcon = {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
                },
                onClick = {}
            )

            // Custom Size
            CommonButton(
                text = "Large Button",
                height = 72.dp,
                fontSize = 20.sp,
                onClick = {}
            )
        }
    }

val componentHeight: Dp = 56.dp
@Composable
fun CommonButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isOutlined: Boolean = false,
    height: Dp? = null,
    fontSize: TextUnit = 18.sp,
    iconSpacing: Dp? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    val spacing = MaterialTheme.appSpacing
    val actualHeight = height ?: componentHeight
    val actualIconSpacing = iconSpacing ?: spacing.mediumSmall

    val mainColor = if (isEnabled) BrandRed else DisabledGray
    val contentColor = if (isOutlined) mainColor else White
    val buttonShape = MaterialTheme.appShapes.large // Uses 16.dp from Shape.kt

    var buttonModifier = modifier
        .fillMaxWidth()
        .height(actualHeight)

    if (isOutlined) {
        buttonModifier = buttonModifier.border(BorderStroke(1.dp, mainColor), buttonShape)
    } else {
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(White.copy(alpha = 0.4f), White.copy(alpha = 0.0f))
        )
        buttonModifier = buttonModifier
            .background(mainColor, buttonShape)
            .background(gradientBrush, buttonShape)
    }

    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = contentColor,
            disabledContentColor = contentColor
        ),
        contentPadding = PaddingValues(),
        modifier = buttonModifier
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(actualIconSpacing))
                }

                Text(
                    text = text,
                    color = contentColor,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = fontSize,
                        letterSpacing = 0.2.sp
                    )
                )

                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(actualIconSpacing))
                    trailingIcon()
                }
            }
        }
    }
}