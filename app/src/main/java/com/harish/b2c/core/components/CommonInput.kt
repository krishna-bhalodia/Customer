package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.appShapes
import com.harish.b2c.ui.theme.appSpacing
import com.harish.b2c.ui.theme.regularBody

@Composable
fun CommonInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    componentHeight: Dp = 56.dp,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val spacing = MaterialTheme.appSpacing
    val shape = MaterialTheme.appShapes.large // 16.dp rounded corners

    val textStyle = MaterialTheme.typography.regularBody.copy(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = TextBlack
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(componentHeight)
            .background(White.copy(alpha = 0.3f), shape)
            .border(1.dp, TextBlack.copy(alpha = 0.16f), shape)
            .padding(horizontal = spacing.mediumLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(spacing.medium))
        }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            cursorBrush = SolidColor(TextBlack),
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle.copy(color = TextBlack.copy(alpha = 0.3f))
                        )
                    }
                    innerTextField()
                }
            }
        )

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(spacing.medium))
            trailingIcon()
        }
    }
}