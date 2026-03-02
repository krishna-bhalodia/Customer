package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Composable
fun AppInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingActionText: String? = null,
    onTrailingActionClick: () -> Unit = {},
    prefixText: String? = null,
    isMultiline: Boolean = false,
    isEditable: Boolean = true,
    isDark: Boolean = false,
    // New parameters for Dropdown functionality
    options: List<String> = emptyList(),
    onOptionSelected: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    componentHeight: Dp = if (isMultiline) 200.dp else 56.dp,
    shape: Shape = Shapes.large
) {
    // State for the dropdown menu
    var expanded by remember { mutableStateOf(false) }
    val isDropdown = options.isNotEmpty()

    val textStyle = AppTypography.regularBody.copy(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = TextBlack
    )

    val containerColor = if (!isDark) White.copy(alpha = 0.3f) else TextBlack.copy(alpha = 0.05f)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (label != null) {
            Text(
                text = label,
                fontFamily = Signika,
                fontSize = 18.sp,
                color = TextBlack
            )
        }

        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(componentHeight)
                    .background(containerColor, shape)
                    .border(1.dp, TextBlack.copy(alpha = 0.16f), shape)
                    .then(
                        if (isDropdown) Modifier.clickable { expanded = true }
                        else Modifier
                    )
                    .padding(horizontal = Spacing.mediumLarge, vertical = if (isMultiline) 16.dp else 0.dp),
                verticalAlignment = if (isMultiline) Alignment.Top else Alignment.CenterVertically
            ) {
                leadingIcon?.let {
                    it()
                    Spacer(modifier = Modifier.width(Spacing.medium))
                }

                if (prefixText != null) {
                    Text(text = prefixText, style = textStyle)
                    Spacer(modifier = Modifier.width(Spacing.medium))
                }

                if (isDropdown) {
                    // Dropdown Display Logic
                    Text(
                        text = value.ifEmpty { placeholder },
                        style = textStyle,
                        color = if (value.isEmpty()) TextBlack.copy(alpha = 0.3f) else TextBlack,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    // Standard TextField Logic
                    BasicTextField(
                        value = value,
                        onValueChange = {
                            if (keyboardOptions.keyboardType == KeyboardType.Number) {
                                if (it.all { char -> char.isDigit() } && it.length <= 10) onValueChange(it)
                            } else {
                                onValueChange(it)
                            }
                        },
                        enabled = isEditable,
                        textStyle = textStyle,
                        modifier = Modifier.weight(1f).then(if (isMultiline) Modifier.fillMaxHeight() else Modifier),
                        singleLine = !isMultiline,
                        keyboardOptions = keyboardOptions,
                        cursorBrush = SolidColor(TextBlack),
                        decorationBox = { innerTextField ->
                            Box(contentAlignment = if (isMultiline) Alignment.TopStart else Alignment.CenterStart) {
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

                if (trailingActionText != null) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = trailingActionText,
                        fontFamily = Signika,
                        fontSize = 18.sp,
                        color = BrandRed,
                        modifier = Modifier.clickable { onTrailingActionClick() }
                    )
                }

                trailingIcon?.let {
                    Spacer(modifier = Modifier.width(Spacing.medium))
                    it()
                }
            }

            // Dropdown Menu implementation
            if (isDropdown) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(White)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option,
                                    style = AppTypography.bodyLarge,
                                    color = TextBlack
                                )
                            },
                            onClick = {
                                onOptionSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun ConsolidatedComponentsPreview() {
    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // --- INPUT USAGES ---
        Text("Inputs", fontWeight = Bold)

        // 1. Standard Input
        AppInput(value = "", onValueChange = {}, placeholder = "Search products...")

        // 2. Profile Style Input (Label + Leading Icon + Action)
        AppInput(
            label = "Full Name",
            value = "Harish",
            onValueChange = {},
            placeholder = "Enter name",
            leadingIcon = { Icon(painterResource(R.drawable.person), null) },
            trailingActionText = "Edit"
        )

        // 3. Phone Number Input
        AppInput(
            value = "",
            onValueChange = {},
            placeholder = "000 000 0000",
            prefixText = "+91",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // 4. Multiline Input
        AppInput(
            value = "",
            onValueChange = {},
            placeholder = "Describe your issue...",
            isMultiline = true
        )

    }
}