package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.regularBody

@Composable
fun CommonMultilineInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    componentHeight: Dp = 200.dp
) {
    val textStyle = AppTypography.regularBody.copy(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = TextBlack
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(componentHeight)
            .background(White.copy(alpha = 0.3f), Shapes.large)
            .border(1.dp, TextBlack.copy(alpha = 0.16f), Shapes.large)
            .padding(16.dp) // Uniform padding for top-left start
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            cursorBrush = SolidColor(TextBlack),
            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                // Align to TopStart instead of CenterStart
                Box(contentAlignment = Alignment.TopStart) {
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
    }
}