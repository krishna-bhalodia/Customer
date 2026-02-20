package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R

@Preview(
    name = "Phone Input - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonPhoneNumberInputPreview() {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 1️⃣ Empty State (Placeholder visible)
        CommonPhoneNumberInput(
            value = "",
            onValueChange = {}
        )

        // 2️⃣ Filled State
        CommonPhoneNumberInput(
            value = "9876543210",
            onValueChange = {}
        )

        // 3️⃣ Different Country Code
        CommonPhoneNumberInput(
            value = "1234567890",
            onValueChange = {},
            countryCode = "+1"
        )
    }
}

@Composable
fun CommonPhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    countryCode: String = "+91",
    placeholderText: String = "000 000 0000"
) {
    // Base colors from Figma: #030304
    val textBlack = Color(0xFF030304)
    val borderColor = textBlack.copy(alpha = 0.6f)    // rgba(3, 3, 4, 0.6)
    val placeholderColor = textBlack.copy(alpha = 0.3f) // opacity: 0.3

    // Shared typography: Signika, 400 (Regular), 18px, line-height 22px
    val textStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = textBlack
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            // border: 1px solid rgba(3, 3, 4, 0.6); border-radius: 16px;
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            // padding: 20px; (Applying horizontal padding, vertical is handled by alignment)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Country Code Label
        Text(
            text = countryCode,
            style = textStyle
        )

        // gap: 16px;
        Spacer(modifier = Modifier.width(16.dp))

        // Phone Number Input Area
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                // Optional: Filter to only allow digits and restrict length to 10
                if (newValue.all { it.isDigit() } && newValue.length <= 10) {
                    onValueChange(newValue)
                }
            },
            textStyle = textStyle,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            cursorBrush = SolidColor(textBlack),
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    // Show placeholder when empty
                    if (value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = textStyle.copy(color = placeholderColor)
                        )
                    }
                    // The actual input field
                    innerTextField()
                }
            }
        )
    }
}