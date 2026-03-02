package com.harish.b2c.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
    selectedCountryCode: String, // NEW: Pass the selected code
    onCountryCodeChange: (String) -> Unit, // NEW: Callback for when user selects a code
    modifier: Modifier = Modifier,
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

    // State to control dropdown visibility
    var expanded by remember { mutableStateOf(false) }
    // List of supported country codes
    val countryCodes = listOf("+91", "+1", "+44", "+61", "+971")

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
        // Dropdown Container
        Box {
            Row(
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(end = Spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCountryCode,
                    style = textStyle
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Country Code",
                    tint = TextBlack
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countryCodes.forEach { code ->
                    DropdownMenuItem(
                        text = { Text(text = code, style = textStyle) },
                        onClick = {
                            onCountryCodeChange(code)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        // Phone Number Input
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