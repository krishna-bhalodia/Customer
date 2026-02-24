package com.harish.b2c.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.regularBody

@Composable
fun CommonOtpInput(
    otpText: String,
    onOtpTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    componentHeight: Dp = 56.dp,
    otpCount: Int = 5
) {
    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount && it.all { char -> char.isDigit() }) {
                onOtpTextChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(otpCount) { index ->
                    val char = when {
                        index < otpText.length -> otpText[index].toString()
                        else -> ""
                    }
                    val borderAlpha = if (index < otpText.length || index == otpText.length) 0.6f else 0.16f

                    Box(
                        modifier = Modifier
                            .width(componentHeight)
                            .height(componentHeight)
                            .border(
                                width = 1.dp,
                                color = TextBlack.copy(alpha = borderAlpha),
                                shape = Shapes.large
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = AppTypography.regularBody.copy(
                                fontSize = 18.sp,
                                lineHeight = 22.sp,
                                color = TextBlack
                            )
                        )
                    }
                }
            }
        }
    )
}