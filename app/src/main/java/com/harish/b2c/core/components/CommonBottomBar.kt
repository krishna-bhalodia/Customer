package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Signika
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.appSpacing
import com.harish.b2c.ui.theme.regularBody

// ----------------------
// PREVIEW WRAPPER VERSION
// ----------------------

@Composable
private fun PreviewBottomBar(selectedIndex: Int) {
    CommonBottomBar(
        selectedIndex = selectedIndex,
        onItemSelected = {},
        onFabClick = {}
    )
}

// ---------- ALL STATES PREVIEW ----------

@Preview(showBackground = true, widthDp = 375, heightDp = 140)
@Composable
fun BottomBar_Preview_Selected_0() {
    PreviewBottomBar(selectedIndex = 0)
}

@Preview(showBackground = true, widthDp = 375, heightDp = 140)
@Composable
fun BottomBar_Preview_Selected_1() {
    PreviewBottomBar(selectedIndex = 1)
}

@Preview(showBackground = true, widthDp = 375, heightDp = 140)
@Composable
fun BottomBar_Preview_Selected_2() {
    PreviewBottomBar(selectedIndex = 2)
}

@Preview(showBackground = true, widthDp = 375, heightDp = 140)
@Composable
fun BottomBar_Preview_Selected_3() {
    PreviewBottomBar(selectedIndex = 3)
}

@Composable
fun CommonBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.appSpacing

    // The entire menu block is 120dp tall according to the CSS.
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        // 1. Bottom White Curved Bar
        // Starts 26dp from the top (120 - 26 = 94dp height)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(94.dp)
                .align(Alignment.BottomCenter)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    ambientColor = Color.Black.copy(alpha = 0.08f),
                    spotColor = Color.Black.copy(alpha = 0.08f)
                ),
            color = White,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    // Leave 88dp padding on the right to make room for the right-aligned FAB
                    .padding(start = 24.dp, end = 88.dp)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                BottomBarItem(
                    icon = R.drawable.store_front, // Use your correct home icon here
                    label = "Home",
                    isSelected = selectedIndex == 0,
                    onClick = { onItemSelected(0) }
                )

                BottomBarItem(
                    icon = R.drawable.products,
                    label = "Products",
                    isSelected = selectedIndex == 1,
                    onClick = { onItemSelected(1) }
                )

                BottomBarItem(
                    icon = R.drawable.promotions,
                    label = "Promotions",
                    isSelected = selectedIndex == 2,
                    onClick = { onItemSelected(2) }
                )

                BottomBarItem(
                    icon = R.drawable.person,
                    label = "Account",
                    isSelected = selectedIndex == 3,
                    onClick = { onItemSelected(3) }
                )
            }
        }

        // 2. FAB (Positioned at TopEnd)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 2.dp, end = 24.dp) // Maps exactly to left: 80.8% and top: 2px
        ) {
            // Main FAB Circle (48x48)
            Box(
                modifier = Modifier
                    .padding(top = 4.dp, end = 4.dp) // Padding to accommodate the badge overlap
                    .size(48.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.16f)
                    )
                    .background(BrandRed, CircleShape) // Solid red base
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                White.copy(alpha = 0.4f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onFabClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.cart),
                    contentDescription = "Cart",
                    tint = White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // 3. FAB Badge (The black "0" button from CSS)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .defaultMinSize(minWidth = 18.dp, minHeight = 18.dp)
                    .background(TextBlack, RoundedCornerShape(16.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0", // Map to dynamic state when you wire up logic
                    fontFamily = Signika,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = White,
                    lineHeight = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val spacing = MaterialTheme.appSpacing
    val inactiveIconColor = Color(0xFF7C858C)

    val iconColor = if (isSelected) BrandRed else inactiveIconColor
    val textColor = if (isSelected) BrandRed else TextBlack

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.extraSmall),
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.regularBody.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
        )
    }
}