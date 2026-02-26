package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonPaymentMethodRow
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentMethodScreenPreview() {
    GradientBackground {
    PaymentMethodScreen(navController = NavController(LocalContext.current))}
}

@Composable
fun PaymentMethodScreen(
    navController: NavController
) {
    // State to hold the currently selected payment method
    var selectedMethod by remember { mutableStateOf("COD") } // Default is Cash on Delivery

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Payment Method",
                    onBackClick = { navController.popBackStack() },
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = Spacing.mediumLarge)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = Spacing.large)
            ) {
                Spacer(modifier = Modifier.height(Spacing.large))

                // Payment Options Container
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White, Shapes.large)
                        .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.large)
                        .padding(Spacing.medium), // 16dp outer padding
                    verticalArrangement = Arrangement.spacedBy(12.dp) // 12px gap
                ) {

                    // 1. Cash on Delivery
                    CommonPaymentMethodRow(
                        title = "Cash on Delivery",
                        iconRes = R.drawable.cash, // Closest matching asset in your resources
                        iconBgColor = PrimaryPurpleBg,
                        iconTintColor = PrimaryPurple,
                        isSelected = selectedMethod == "COD",
                        onClick = { selectedMethod = "COD" }
                    )

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = TextBlack.copy(alpha = 0.1f)
                    )

                    // 2. MTN Mobile Money Group
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Spacing.medium) // 16px gap
                    ) {
                        CommonPaymentMethodRow(
                            title = "MTN Mobile Money",
                            iconRes = R.drawable.wallet,
                            iconBgColor = PrimaryBlueBg,
                            iconTintColor = PrimaryBlue,
                            isSelected = selectedMethod == "MTN",
                            onClick = { selectedMethod = "MTN" }
                        )

                        // Add New Number Button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(36.dp)
                                .background(TextBlack.copy(alpha = 0.05f), Shapes.large)
                                .clip(Shapes.large)
                                .clickable { /* Add new number logic */ }
                                .padding(horizontal = Spacing.small),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add New",
                                tint = TextBlack,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(Spacing.small))
                            Text(
                                text = "Link your MoMo wallet", // Matching the "Label" placeholder
                                style = AppTypography.bodyLarge.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                ),
                                color = TextBlack
                            )
                        }
                    }
                }
            }
        }
    }
