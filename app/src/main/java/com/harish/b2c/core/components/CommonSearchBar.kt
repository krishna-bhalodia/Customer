package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.regularBody

@Preview(showBackground = true, widthDp = 375)
@Composable
fun SearchBar_Empty_Preview() {
    var text by remember { mutableStateOf("") }
    CommonSearchBar(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true, widthDp = 375)
@Composable
fun SearchBar_WithText_Preview() {
    var text by remember { mutableStateOf("Milk") }
    CommonSearchBar(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = false, widthDp = 375)
@Composable
fun SearchBar_WithTrailingIcon_Preview() {
    var text by remember { mutableStateOf("") }
    CommonSearchBar(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.padding(16.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Filter",
                tint = Color.Black
            )
        }
    )
}

@Composable
fun CommonSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search...",
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null
) {

    // CSS specified font: Signika 16px
    val textStyle = AppTypography.regularBody.copy(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = TextBlack
    )

    // Outer Row to align the search bar and the external trailing icon
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Inner Row: The actual Search Input box
        Row(
            modifier = Modifier
                .weight(1f) // Takes up all remaining space so the icon can sit next to it
                .height(48.dp) // Height specified in CSS
                // Replicating: box-shadow: 0px 8px 24px rgba(234, 10, 42, 0.08);
                .shadow(
                    elevation = 8.dp,
                    shape = Shapes.large ,
                    spotColor = BrandRed.copy(alpha = 0.08f),
                    ambientColor = BrandRed.copy(alpha = 0.08f)
                )
                // Replicating: background: rgba(255, 255, 255, 0.3);
                .background(White.copy(alpha = 0.3f), Shapes.large )
                // Replicating: border: 1px solid rgba(255, 255, 255, 0.6);
                .border(1.dp, White.copy(alpha = 0.6f), Shapes.large )
                .padding(horizontal = Spacing.medium), // 16.dp padding from CSS
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Leading Icon
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = TextBlack,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(Spacing.small)) // 8.dp gap from CSS

            // Search Text Input
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = textStyle,
                singleLine = true,
                cursorBrush = SolidColor(TextBlack),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearch() }),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                // Replicating: Label opacity: 0.4
                                style = textStyle.copy(color = TextBlack.copy(alpha = 0.4f))
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        // Optional Trailing Icon (OUTSIDE the search box)
        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(Spacing.medium)) // 16.dp gap between search bar and icon
            Box(
                modifier = Modifier
                    .size(24.dp) // Fixed size for the clickable area
                    .clickable { /* Add your filter/icon click logic here */ },
                contentAlignment = Alignment.Center
            ) {
                trailingIcon()
            }
        }
    }
}