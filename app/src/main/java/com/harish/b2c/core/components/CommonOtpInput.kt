package com.harish.b2c.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "OTP Input - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonOtpInputPreview() {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // 1️⃣ Empty
        CommonOtpInput(
            otpText = "",
            onOtpTextChange = {}
        )

        // 2️⃣ Partially Filled
        CommonOtpInput(
            otpText = "12",
            onOtpTextChange = {}
        )

        // 3️⃣ Fully Filled
        CommonOtpInput(
            otpText = "12345",
            onOtpTextChange = {}
        )
    }
}

@Composable
fun CommonOtpInput(
    otpText: String,
    onOtpTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    otpCount: Int = 5
) {
    val textBlack = Color(0xFF030304)

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
                horizontalArrangement = Arrangement.SpaceBetween // Distributes the 5 boxes evenly
            ) {
                repeat(otpCount) { index ->
                    val char = when {
                        index < otpText.length -> otpText[index].toString()
                        else -> ""
                    }

                    // Active/filled state border opacity: 0.6, Empty state opacity: 0.16
                    val borderAlpha = if (index < otpText.length || index == otpText.length) 0.6f else 0.16f

                    Box(
                        modifier = Modifier
                            .width(56.dp)
                            .height(56.dp)
                            .border(
                                width = 1.dp,
                                color = textBlack.copy(alpha = borderAlpha),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            color = textBlack,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }
    )
}