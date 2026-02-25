package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.*
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun OrderDetailsScreenPreview() {
    GradientBackground {   // Replace with your actual app theme name if different
        OrderDetailsScreen(
            onBackClick = {}
        )
    }
}

@Composable
fun OrderDetailsScreen(
    onBackClick: () -> Unit
) {

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(88.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 24.dp)
                            .size(40.dp)
                            .border(1.dp, TextBlack.copy(alpha = 0.08f), CircleShape)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextBlack
                        )
                    }

                    Text(
                        text = "Order Details",
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 32.dp),
                        style = AppTypography.titleLarge.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextBlack
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Translucent Order Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White.copy(alpha = 0.5f))
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Order #MHO00300010", fontFamily = Signika, fontSize = 12.sp, color = TextBlack)
                    Text(text = "Placed on 17 Oct, 2025", fontFamily = Signika, fontSize = 12.sp, color = TextGrey)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Order For Container
                Box(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .background(White, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "Order for Rahman, 7894561230", fontFamily = Signika, fontSize = 14.sp, color = TextBlack)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Main White Bottom Sheet Container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(24.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Status Row
                        item {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(SuccessGreen, CircleShape)
                                        .border(1.dp, SuccessGreen.copy(alpha = 0.1f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = White, modifier = Modifier.size(10.dp))
                                }
                                Text("Delivered on 18 Oct", fontFamily = Signika, fontSize = 14.sp, color = TextBlack)
                            }
                        }

                        // Order Items List
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                    .background(White, RoundedCornerShape(16.dp))
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                CommonOrderItemCard(
                                    title = "SM Tang Orange Tub (6x2kg)",
                                    subtitle = "1 CSE • 1 PCS",
                                    price = "30.00",
                                    imageUrl = R.drawable.sm_tang,
                                    sku = "HFG0000136 • Juice",
                                    quantity = 100
                                )
                                HorizontalDivider(color = TextBlack.copy(alpha = 0.1f))
                                CommonOrderItemCard(
                                    title = "Orchid Valley Guava Delight",
                                    subtitle = "1 CSE • 24 PCS",
                                    price = "360.00",
                                    imageUrl = R.drawable.orchid,
                                    sku = "HFG0000136 • Juice",
                                    quantity = 10
                                )
                                HorizontalDivider(color = TextBlack.copy(alpha = 0.1f))
                                CommonOrderItemCard(
                                    title = "Predator Energy Gold, 250ml",
                                    subtitle = "5 CSE • 120 PCS",
                                    price = "2400.00",
                                    imageUrl = R.drawable.predator,
                                    sku = "HFG0000136 • Energy Drink",
                                    quantity = 5
                                )
                            }
                        }

                        // Promotional Items List
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                    .background(White, RoundedCornerShape(16.dp))
                            ) {
                                // Blue Header
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(PromoBlueBg, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.check),
                                        contentDescription = "Promo",
                                        modifier = Modifier.size(Spacing.extraLarge), // 32.dp
                                        tint = Color.Unspecified
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Text(
                                            text = "Applied 10% OFF on 100 PCS",
                                            style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                            color = PromoBlue
                                        )
                                        Text(
                                            text = "of SM Tang Orange Tub (6x2kg)",
                                            style = AppTypography.bodyLarge.copy(fontSize = 14.sp),
                                            color = TextGrey
                                        )
                                    }
                                }
                                // Free Item Body
                                Box(modifier = Modifier.padding(16.dp)) {
                                    CommonOrderItemCard(
                                        title = "SM Tang Orange Tub (6x2kg)",
                                        subtitle = "1 CSE • 1 PCS",
                                        price = "00.00", // Free
                                        imageUrl = R.drawable.sm_tang,
                                        sku = "HFG0000136 • Juice",
                                        quantity = 10
                                    )
                                }
                            }
                        }

                        // Summaries & Support
                        item { CommonInvoiceSummaryCard(itemsTotal = "15360.00", vat = "1500.00", discount = "0.00", grandTotal = "16860.00") }
                        item { CommonOrderInfoCard(orderId = "#MHO00300010", paymentMethod = "Via MTN Mobile Money", deliveryAddress = "S12, Kira Rd, Kampala Capital City, Central Region, 2121", orderDate = "Placed on 17 Oct, 2025") }
                        item { CommonSupportCard() }
                        item{
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }

}