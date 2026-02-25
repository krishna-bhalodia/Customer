package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Preview(
    name = "CommonChipRow - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonChipRowPreview() {
    B2CTheme {
        val categories = listOf("All", "Men", "Women", "Kids", "Offers")
        var selected by remember { mutableStateOf("Men") }

        Column(
            modifier = Modifier
                .background(White)
                .padding(vertical = Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            // With Search Option
            CommonChipRow(
                categories = categories,
                selectedCategory = selected,
                onCategorySelected = { selected = it },
                isSearchOption = true,
                onSearchClick = {}
            )

            // Without Search Option
            CommonChipRow(
                categories = categories,
                selectedCategory = selected,
                onCategorySelected = { selected = it }
            )
        }
    }
}

@Composable
fun <T> CommonChipRow(
    modifier: Modifier = Modifier,
    categories: List<T>,
    selectedCategory: T?,
    onCategorySelected: (T) -> Unit,
    categoryToString: (T) -> String = { it.toString() },
    isSearchOption: Boolean = false,
    onSearchClick: () -> Unit = {},
    chipHeight: Dp = 32.dp, // Adjusted to align better with standard tap targets
) {
    val chipShape = Shapes.large

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.small),
        contentPadding = PaddingValues(horizontal = Spacing.large)
    ) {
        if (isSearchOption) {
            item {
                Box(
                    modifier = Modifier
                        .height(chipHeight)
                        .clip(chipShape)
                        .border(1.dp, BorderLight, chipShape)
                        .clickable { onSearchClick() }
                        .padding(horizontal = Spacing.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        tint = TextBlack,
                        modifier = Modifier.size(Spacing.medium)
                    )
                }
            }
        }
        items(categories) { category ->
            val isSelected = selectedCategory == category
            val bgColor = if (isSelected) TextBlack else Color.Transparent
            val textColor = if (isSelected) White else TextBlack
            val borderColor = if (isSelected) Color.Transparent else BorderLight

            Box(
                modifier = Modifier
                    .height(chipHeight)
                    .clip(chipShape)
                    .background(bgColor)
                    .border(1.dp, borderColor, chipShape)
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = Spacing.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = categoryToString(category),
                    color = textColor,
                    style = AppTypography.labelSmall // Using theme style instead of hardcoded 14.sp
                )
            }
        }
    }
}