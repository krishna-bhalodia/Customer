package com.harish.b2c.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.regularBody

@Composable
fun CommonPhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    countryCode: String = "+91",
    componentHeight: Dp = 56.dp,
    placeholderText: String = "000 000 0000"
) {
    val borderColor = TextBlack.copy(alpha = 0.6f)
    val placeholderColor = TextBlack.copy(alpha = 0.3f)

    val textStyle = AppTypography.regularBody.copy(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = TextBlack
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(componentHeight)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = Shapes.large
            )
            .padding(horizontal = Spacing.mediumLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = countryCode,
            style = textStyle
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } && newValue.length <= 10) {
                    onValueChange(newValue)
                }
            },
            textStyle = textStyle,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            cursorBrush = SolidColor(TextBlack),
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = textStyle.copy(color = placeholderColor)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}