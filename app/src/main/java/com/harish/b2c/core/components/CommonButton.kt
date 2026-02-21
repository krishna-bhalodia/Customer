package com.harish.b2c.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R

@Preview(
    name = "Common Button - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonButtonAllStatesPreview() {

    androidx.compose.foundation.layout.Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 1️⃣ Default Enabled
        CommonButton(
            text = "Enabled",
            onClick = {}
        )

        // 2️⃣ Disabled
        CommonButton(
            text = "Disabled",
            isEnabled = false,
            onClick = {}
        )

        // 3️⃣ Leading Icon
        CommonButton(
            text = "Leading Icon",
            leadingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            onClick = {}
        )

        // 4️⃣ Trailing Icon
        CommonButton(
            text = "Trailing Icon",
            trailingIcon = {
                androidx.compose.material.icons.Icons.Default.ArrowForward.let {
                    androidx.compose.material3.Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            onClick = {}
        )

        // 5️⃣ Both Icons
        CommonButton(
            text = "Both Icons",
            leadingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            trailingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            onClick = {}
        )

        // 6️⃣ Custom Height & Font Size
        CommonButton(
            text = "Large Button",
            height = 70.dp,
            fontSize = 20.sp,
            onClick = {}
        )
        CommonButton(
            text = "Add New Address",
            isOutlined = true,       // Enables transparent background and border
            height = 46.dp,          // Smaller 46px height
            fontSize = 16.sp,        // Smaller 16px font
            leadingIcon = {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            },
            onClick = { /* Handle Click */ }
        )
    }
}
@Composable
fun CommonButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isOutlined: Boolean = false,     // NEW: Toggle for outlined state
    height: Dp = 57.dp,              // Default to 57px height
    fontSize: TextUnit = 18.sp,      // Default to 18px font
    iconSpacing: Dp = 10.dp,         // Default gap: 10px
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    // Base colors from Figma
    val activeColor = Color(0xFFEA0A2A)
    val disabledColor = Color(0xFF91989D)
    val mainColor = if (isEnabled) activeColor else disabledColor

    // Text and Icon color changes based on outlined state
    val contentColor = if (isOutlined) mainColor else Color.White

    val buttonShape = RoundedCornerShape(16.dp)

    // Build the dynamic modifier based on the state
    var buttonModifier = modifier
        .fillMaxWidth()
        .height(height)

    if (isOutlined) {
        // Outlined state: Transparent background with a border
        buttonModifier = buttonModifier.border(
            border = BorderStroke(1.dp, mainColor),
            shape = buttonShape
        )
    } else {
        // Filled state: Solid color + Gradient overlay
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.4f),
                Color.White.copy(alpha = 0.0f)
            )
        )
        buttonModifier = buttonModifier
            .background(color = mainColor, shape = buttonShape)
            .background(brush = gradientBrush, shape = buttonShape)
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
        contentPadding = PaddingValues(), // Removed default padding so Row can fill sizes
        modifier = buttonModifier
    ) {
        // Providing LocalContentColor ensures any standard Material Icon passed in
        // the slots will automatically tint to Red (outlined) or White (filled)
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            // Flex layout: row, center, center
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading Icon
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(iconSpacing))
                }

                Text(
                    text = text,
                    color = contentColor,
                    fontSize = fontSize,
                    fontFamily = FontFamily(Font(R.font.signika_semibold, FontWeight.SemiBold)), // font-weight: 600
                    letterSpacing = 0.2.sp
                )

                // Trailing Icon
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(iconSpacing))
                    trailingIcon()
                }
            }
        }
    }
}