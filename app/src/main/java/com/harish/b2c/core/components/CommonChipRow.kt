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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R

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
    // Base colors from Figma
    val textBlack = Color(0xFF030304)
    val chipShape = RoundedCornerShape(16.dp)

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        // Adding 24.dp horizontal padding so the row starts from "left: 24px"
        // as per your Figma Frame 1171275080, but allows smooth scrolling off-screen.
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        if (isSearchOption) {
            item {
                Box(
                    modifier = Modifier
                        .height(29.dp)
                        .clip(chipShape)
                        .border(
                            width = 1.dp,
                            color = textBlack.copy(alpha = 0.1f), // rgba(3, 3, 4, 0.1)
                            shape = chipShape
                        )
                        .clickable { onSearchClick() }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
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

            // Conditional styling based on selection state
            val bgColor = if (isSelected) textBlack else Color.Transparent
            val textColor = if (isSelected) Color.White else textBlack
            val borderColor = if (isSelected) Color.Transparent else textBlack.copy(alpha = 0.1f)

            Box(
                modifier = Modifier
                    .height(29.dp)
                    .clip(chipShape)
                    .background(bgColor)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = chipShape
                    )
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = categoryToString(category),
                    color = textColor,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)) // font-weight: 400
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Figma Chip Row Spec")
@Composable
private fun PreviewCommonChipRow() {
    val categories = listOf("All", "CSD", "Candy", "Lolly Pop", "Gum", "Energy Drink", "Juice", "Water")
    var selectedCategory by remember { mutableStateOf(categories[0]) } // "All" selected by default

    Box(modifier = Modifier.padding(vertical = 24.dp)) {
        CommonChipRow(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            isSearchOption = false
        )
    }
}