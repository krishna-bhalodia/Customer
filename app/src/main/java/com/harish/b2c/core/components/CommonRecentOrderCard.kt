package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.appShapes
import com.harish.b2c.ui.theme.regularBody
import androidx.compose.ui.tooling.preview.Preview


// ------------------------------
// DEFAULT PREVIEW
// ------------------------------
@Preview(showBackground = true, widthDp = 375)
@Composable
fun RecentOrderCard_Preview_Default() {
    MaterialTheme {
        CommonRecentOrderCard(
            orderId = "#MHO00300010",
            date = "Placed on 17 Oct, 2025",
            amount = "1240.00",
            status = "Order delivered"
        )
    }
}

// ------------------------------
// LONG TEXT PREVIEW
// ------------------------------
@Preview(showBackground = true, widthDp = 375)
@Composable
fun RecentOrderCard_Preview_LongText() {
    MaterialTheme {
        CommonRecentOrderCard(
            orderId = "#MHO003000109999999",
            date = "Placed on 17 October, 2025 at 10:30 AM",
            amount = "9999.00",
            status = "Order delivered successfully"
        )
    }
}

// ------------------------------
// DIFFERENT STATUS PREVIEW
// ------------------------------
@Preview(showBackground = true, widthDp = 375)
@Composable
fun RecentOrderCard_Preview_Pending() {
    MaterialTheme {
        CommonRecentOrderCard(
            orderId = "#MHO00300011",
            date = "Placed on 20 Oct, 2025",
            amount = "340.00",
            status = "Order processing"
        )
    }
}
@Composable
fun CommonRecentOrderCard(
    orderId: String,
    date: String,
    amount: String,
    status: String,
    modifier: Modifier = Modifier
) {
    val greyText = Color(0xFF7C858C)
    val successGreen = Color(0xFF52B96C)
    val orangeIconColor = Color(0xFFFC9E15)
    val orangeIconBg = orangeIconColor.copy(alpha = 0.2f)

    // Main Container (Column)
    Column(
        modifier = modifier
            .width(327.dp)
            .background(White, MaterialTheme.appShapes.large)
            .border(1.dp, TextBlack.copy(alpha = 0.1f), MaterialTheme.appShapes.large)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Top Row: Icon, Order Info, and Price/Arrow
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Side: Icon and Text Info
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Cart Icon Box
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(orangeIconBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart, // Replace with your custom SVG if needed
                        contentDescription = "Order",
                        tint = orangeIconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Text Column
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = orderId,
                        color = TextBlack,
                        style = MaterialTheme.typography.regularBody.copy(
                            fontSize = 14.sp
                        )
                    )
                    Text(
                        text = date,
                        color = greyText,
                        style = MaterialTheme.typography.regularBody.copy(
                            fontSize = 12.sp
                        )
                    )
                }
            }

            // Right Side: Amount and Next Arrow
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = "AED",
                        color = greyText,
                        style = MaterialTheme.typography.regularBody.copy(
                            fontSize = 12.sp
                        ),
                        lineHeight = 14.sp
                    )

                    Text(
                        text = amount, // pass only numeric part if possible
                        color = TextBlack,
                        style = MaterialTheme.typography.regularBody.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        lineHeight = 16.sp
                    )
                }

                // Arrow Box
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(TextBlack.copy(alpha = 0.08f), CircleShape)
                        .border(1.dp, White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "View Details",
                        tint = TextBlack,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // Bottom Row: Status Indicator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = status,
                color = TextBlack,
                style = MaterialTheme.typography.regularBody.copy(
                    fontSize = 12.sp
                )
            )

            // Custom Status Checkmark Circle
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .shadow(
                        elevation = 24.dp,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.08f)
                    )
                    .background(successGreen, CircleShape)
                    .border(1.dp, successGreen.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Status",
                    tint = White,
                    modifier = Modifier.size(10.dp)
                )
            }
        }
    }
}