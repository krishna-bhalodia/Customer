package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.utils.glassShadow
import com.harish.b2c.ui.theme.*

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

@Preview(showBackground = true, backgroundColor = 0xFF000000, widthDp = 375, heightDp = 140)
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
    val barShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // ✅ Material 3 NavigationBar
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
                .align(Alignment.BottomCenter)
                // 1. Apply Shadow FIRST so it can draw outside the shape
                .glassShadow(
                    color = Color(0xFF000000),
                    alpha = 0.08f,
                    shape = barShape,
                    shadowRadius = 24.dp,
                    offsetY = -8.dp
                )
                // 2. Apply Clip SECOND so the background color respects the corners
                .clip(barShape),
            containerColor = White,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(0, 0, 0, 0) // Manual padding control
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 88.dp), // Keeping space for the FAB
                  //  .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarNavigationItem(R.drawable.home, "Home", selectedIndex == 0) { onItemSelected(0) }
                BottomBarNavigationItem(R.drawable.products, "Products", selectedIndex == 1) { onItemSelected(1) }
                BottomBarNavigationItem(R.drawable.promotions, "Promotions", selectedIndex == 2) { onItemSelected(2) }
                BottomBarNavigationItem(R.drawable.person, "Account", selectedIndex == 3) { onItemSelected(3) }
            }
        }

        // ✅ Floating FAB (Remains custom to keep the exact offset/badge UI)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = (-20).dp) // Adjusted slightly for M3 alignment
                .padding(end = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .shadow(16.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.16f))
                    .background(BrandRed, CircleShape)
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

            // Badge
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 4.dp, y = (-4).dp)
                    .defaultMinSize(minWidth = 18.dp, minHeight = 18.dp),
                color = TextBlack,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "0",
                    fontSize = 11.sp,
                    color = White,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun BottomBarNavigationItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconColor = if (isSelected) BrandRed else TextGrey
    val textColor = if (isSelected) BrandRed else TextBlack

    // We use a Column inside the NavigationBar's Row scope to keep your custom spacing
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(
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
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = textColor,
            fontSize = 12.sp,
            style = AppTypography.bodyLarge.copy(lineHeight = 12.sp)
        )
    }
}