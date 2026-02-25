package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.harish.b2c.ui.theme.*

enum class ActionIconPosition {
    Leading, Trailing
}

@Composable
fun CommonActionInput(
    value: String,
    placeholder: String,
    icon: ImageVector,
    iconPosition: ActionIconPosition = ActionIconPosition.Trailing,
    options: List<String> = emptyList(), // ✅ Dropdown options
    onOptionSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Standard component height
                .background(White20, Shapes.large)
                .border(1.dp, BorderLight, Shapes.large)
                .clickable {
                    if (iconPosition == ActionIconPosition.Trailing && options.isNotEmpty()) {
                        expanded = true
                    }
                }
                .padding(horizontal = Spacing.mediumLarge),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (iconPosition == ActionIconPosition.Leading) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TextBlack,
                    modifier = Modifier.size(Spacing.mediumLarge)
                )
                Spacer(modifier = Modifier.width(Spacing.medium))
            }

            Text(
                text = value.ifEmpty { placeholder },
                style = AppTypography.bodyLarge, // Removed hardcoded 18.sp
                color = if (value.isEmpty()) TextGrey else TextBlack,
                modifier = Modifier.weight(1f)
            )

            if (iconPosition == ActionIconPosition.Trailing) {
                Spacer(modifier = Modifier.width(Spacing.medium))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TextBlack,
                    modifier = Modifier.size(Spacing.mediumLarge)
                )
            }
        }

        // ✅ Dropdown Menu
        if (iconPosition == ActionIconPosition.Trailing) {
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