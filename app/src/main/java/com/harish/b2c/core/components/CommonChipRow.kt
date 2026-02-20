package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.core.utils.glassShadow
import com.harish.b2c.ui.theme.Gray900
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.White20
import com.harish.b2c.ui.theme.appShapes
import com.harish.b2c.ui.theme.appSpacing

@Composable
fun <T> CommonChipRow(
    modifier: Modifier = Modifier,
    categories: List<T>,
    selectedCategory: T?,
    onCategorySelected: (T) -> Unit,
    categoryToString: (T) -> String = { it.toString() },
    isSearchOption: Boolean = false,
    onSearchClick: () -> Unit = {},
) {
    val currentShapes = MaterialTheme.appShapes
    val currentSpacing = MaterialTheme.appSpacing
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(currentSpacing.medium)
    ) {
        if (isSearchOption) {
            item {
                val searchShape = currentShapes.medium

                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .glassShadow(
                            color = Color(0xFFEA0A2A),
                            alpha = 0.12f,
                            shape = searchShape,
                            shadowRadius = 16.dp,
                            offsetY = 4.dp
                        )
                        .clip(searchShape)
                        .background(White20)
                        .border(
                            width = 2.dp,
                            color = White.copy(alpha = 0.6f),
                            shape = searchShape
                        )
                        .clickable { onSearchClick() }
                        .padding(horizontal = 12.dp, vertical = 6.dp)) {
                    CommonSvgImage(
                        modifier = Modifier.size(16.dp),
                        assetName = "images/ic_search.svg",
                        contentDescription = "Search",
                    )
                }
            }
        }
        items(categories) { category ->
            val isSelected = selectedCategory == category

            // Define ONE shape for the category items
            val chipShape = currentShapes.extraLarge

            Box(
                modifier = Modifier
                    .glassShadow(
                        color = Color(0xFFEA0A2A),
                        alpha = 0.12f,
                        shape = chipShape,
                        shadowRadius = 16.dp,
                        offsetY = 4.dp
                    )
                    .clip(chipShape)
                    .background(
                        if (isSelected) Gray900 else White20
                    )
                    .border(
                        width = 2.dp,
                        color = if (isSelected) Gray900 else White.copy(alpha = 0.6f),
                        shape = chipShape
                    )
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 16.dp, vertical = 6.dp)) {
                Text(
                    text = categoryToString(category),
                    color = if (isSelected) White else Gray900
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5, name = "With Search Option")
@Composable
private fun PreviewCommonChipRowWithSearch() {
    val categories = listOf("Pizza", "Burger", "Sushi", "Salad", "Dessert")
    var selectedCategory by remember { mutableStateOf(categories[1]) }

    Box(modifier = Modifier.padding(vertical = 24.dp)) {
        CommonChipRow(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            isSearchOption = true
        )
    }
}