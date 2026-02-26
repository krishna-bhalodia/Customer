package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonInvoiceSummaryCard
import com.harish.b2c.core.components.CommonOrderInfoCard
import com.harish.b2c.core.components.CommonOrderItemCard
import com.harish.b2c.core.components.CommonSupportCard
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.PromoBlue
import com.harish.b2c.ui.theme.PromoBlueBg
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.SuccessGreen
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey
import com.harish.b2c.ui.theme.White

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderDetailsScreenPreview() {
    GradientBackground {
        OrderDetailsScreen(navController = NavController(LocalContext.current))
    }
}

@Composable
fun OrderDetailsScreen(
    navController: NavController
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Order Details", onBackClick = { navController.popBackStack() },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 20.dp)
            )
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
                    .padding(horizontal = Spacing.large, vertical = Spacing.extraSmall),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order #MHO00300010",
                    style = AppTypography.bodyMedium,
                    color = TextBlack
                )
                Text(
                    text = "Placed on 17 Oct, 2025",
                    style = AppTypography.bodyMedium,
                    color = TextGrey
                )
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            // Order For Container
            Box(
                modifier = Modifier
                    .padding(horizontal = Spacing.large)
                    .fillMaxWidth()
                    .background(White, Shapes.large)
                    .padding(Spacing.medium)
            ) {
                Text(
                    text = "Order for Rahman, 7894561230",
                    style = AppTypography.bodyLarge,
                    color = TextBlack
                )
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // Main White Bottom Sheet Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        White,
                        RoundedCornerShape(topStart = Spacing.large, topEnd = Spacing.large)
                    )
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(Spacing.large),
                    verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge),
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Status Row
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(SuccessGreen, CircleShape)
                                    .border(1.dp, SuccessGreen.copy(alpha = 0.1f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = null,
                                    tint = White,
                                    modifier = Modifier.size(10.dp)
                                )
                            }
                            Text(
                                "Delivered on 18 Oct",
                                style = AppTypography.bodyLarge,
                                color = TextBlack
                            )
                        }
                    }

                    // Order Items List
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.large)
                                .background(White, Shapes.large)
                                .padding(Spacing.medium),
                            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
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
                                .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.large)
                                .background(White, Shapes.large)
                        ) {
                            // Blue Header
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        PromoBlueBg,
                                        RoundedCornerShape(
                                            topStart = Spacing.medium,
                                            topEnd = Spacing.medium
                                        )
                                    )
                                    .padding(Spacing.small),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.check),
                                    contentDescription = "Promo",
                                    modifier = Modifier.size(Spacing.extraLarge),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(Spacing.small))
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text(
                                        text = "Applied 10% OFF on 100 PCS",
                                        style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                        color = PromoBlue
                                    )
                                    Text(
                                        text = "of SM Tang Orange Tub (6x2kg)",
                                        style = AppTypography.bodyMedium,
                                        color = TextGrey
                                    )
                                }
                            }
                            // Free Item Body
                            Box(modifier = Modifier.padding(Spacing.medium)) {
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
                    item {
                        CommonInvoiceSummaryCard(
                            itemsTotal = "15360.00",
                            vat = "1500.00",
                            discount = "0.00",
                            grandTotal = "16860.00"
                        )
                    }
                    item {
                        CommonOrderInfoCard(
                            orderId = "#MHO00300010",
                            paymentMethod = "Via MTN Mobile Money",
                            deliveryAddress = "S12, Kira Rd, Kampala Capital City, Central Region, 2121",
                            orderDate = "Placed on 17 Oct, 2025"
                        )
                    }
                    item {
                        CommonSupportCard(modifier = Modifier.clickable {
                            navController.navigate(
                                NavigationScreen.RaiseComplaint.route
                            )
                        })
                    }
                    item { Spacer(modifier = Modifier.height(Spacing.large)) }
                }
            }
        }
    }
}