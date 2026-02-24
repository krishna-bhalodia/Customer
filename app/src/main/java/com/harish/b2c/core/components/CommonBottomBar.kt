package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.regularBody
import androidx.compose.ui.tooling.preview.Preview
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.Spacing

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

    // CSS specified total height is 120px.
    // The white rectangle starts at ~26px down (21.67%), and the FAB starts at 2px down.
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        // 1. White Background Container
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(94.dp) // 120dp total - 26dp offset
                .align(Alignment.BottomCenter)
                // Replicating: box-shadow: 0px -8px 24px rgba(0, 0, 0, 0.08);
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    ambientColor = Color.Black.copy(alpha = 0.08f),
                    spotColor = Color.Black.copy(alpha = 0.08f)
                ),
            color = White,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            // Row for the 4 standard navigation items
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    // Leave space on the right for the floating action button
                    .padding(end = 80.dp)
                    // Leave space at the bottom for the Native Home Indicator
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(
                    icon = Icons.Default.Home,
                    label = "Home",
                    isSelected = selectedIndex == 0,
                    onClick = { onItemSelected(0) }
                )
                BottomBarItem(
                    icon = Icons.Default.List, // Replace with your exact Products SVG
                    label = "Products",
                    isSelected = selectedIndex == 1,
                    onClick = { onItemSelected(1) }
                )
                BottomBarItem(
                    icon = Icons.Default.Star, // Replace with your exact Promotions SVG
                    label = "Promotions",
                    isSelected = selectedIndex == 2,
                    onClick = { onItemSelected(2) }
                )
                BottomBarItem(
                    icon = Icons.Default.Person, // Replace with your exact Account SVG
                    label = "Account",
                    isSelected = selectedIndex == 3,
                    onClick = { onItemSelected(3) }
                )
            }
        }

        // 2. Floating Action Button (Overlaps the top right)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                // CSS: left: 80.8% maps roughly to 24dp end padding on a standard 375 width screen
                .padding(end = Spacing.large, top = 2.dp)
                .size(48.dp)
                // Replicating: drop-shadow(0px 0px 16px rgba(0, 0, 0, 0.16))
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    spotColor = Color.Black.copy(alpha = 0.16f)
                )
                // Background combination: Linear gradient overlay + BrandRed solid base
                .background(BrandRed, CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            White.copy(alpha = 0.4f),
                            White.copy(alpha = 0.0f)
                        )
                    ),
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null, // Custom ripple or none as preferred
                    onClick = onFabClick
                ),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder for FAB Icon (e.g., Cart or Scan based on your app context)
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "FAB Action",
                tint = White,
                modifier = Modifier.size(Spacing.large)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val inactiveIconColor = Color(0xFF7C858C) // Hardcoded grey from your CSS

    val iconColor = if (isSelected) BrandRed else inactiveIconColor
    val textColor = if (isSelected) BrandRed else TextBlack

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall), // CSS: gap: 4px
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(Spacing.large) // CSS: width 24px, height 24px
        )

        Text(
            text = label,
            color = textColor,
            // Strictly uses regularBody but scales down to exactly 12sp per CSS
            style = AppTypography.regularBody.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
        )
    }
}