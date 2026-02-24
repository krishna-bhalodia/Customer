package com.harish.b2c.presentation.checkout

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonProductCard
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey
import com.harish.b2c.ui.theme.White

// Local constant for the promo code section
private val PromoBlue = Color(0xFF0088FF)

// Dummy Data Class specific to Checkout
data class CheckoutProductItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val price: String,
    var quantity: Int,
    val imageUrl: Int
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckoutScreenPreview() {
    GradientBackground { CheckoutScreen(onBackClick = {}) }
}

@Composable
fun CheckoutScreen(
    onBackClick: () -> Unit
) {
    // Dummy Data
    var checkoutItems by remember {
        mutableStateOf(
            listOf(
                CheckoutProductItem(
                    1,
                    "Oner Apple 1 LTR * 6",
                    "10 CSE • 12 PCS",
                    "240.00",
                    10,
                    R.drawable.oner_apple
                ),
                CheckoutProductItem(
                    2,
                    "SM Tang Orange Tub (6x2kg)",
                    "1 CSE • 1 PCS",
                    "30.00",
                    5,
                    R.drawable.sm_tang
                ),
                CheckoutProductItem(
                    3,
                    "Predator Energy Gold, 250ml",
                    "5 CSE • 120 PCS",
                    "2400.00",
                    5,
                    R.drawable.predator
                )
            )
        )
    }

    var orderNote by remember { mutableStateOf("Today, 28 Dec 2025") }

    Scaffold(
        containerColor = Color.Transparent, // Let GradientBackground show through the top
        topBar = {
            CommonAppBar(
                title = "Checkout",
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = Spacing.mediumLarge),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .navigationBarsPadding()
        ) {
            // Top section elements
            Column(
                modifier = Modifier.padding(horizontal = Spacing.large),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                Spacer(modifier = Modifier.height(Spacing.small))

                // Order Info Card
                CustomerInfoCard(name = "Rahman", phone = "7894561230")

                // Specific Input Field
                CheckoutInputField(
                    label = "Choose delivery date",
                    placeholder = "Today, 28 Dec 2025",
                    value = orderNote,
                    onValueChange = { orderNote = it },
                    leadingIcon = R.drawable.truck,
                    trailingIcon = R.drawable.calender
                )

                Spacer(modifier = Modifier.height(Spacing.small))
            }

            // White Bottom Sheet-like Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(topStart = Spacing.large, topEnd = Spacing.large)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = Spacing.large, bottom = 0.dp),
                    verticalArrangement = Arrangement.spacedBy(Spacing.large)
                ) {

                    // 1. Selected Products List
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = Spacing.large)
                                .background(White, Shapes.medium)
                                .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.medium)
                                .padding(Spacing.mediumLarge),
                            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                        ) {
                            checkoutItems.forEachIndexed { index, product ->
                                CommonProductCard(
                                    title = product.title,
                                    subtitle = product.subtitle,
                                    price = product.price,
                                    imageUrl = product.imageUrl,
                                    quantity = product.quantity,
                                    showBorder = false,
                                    onIncrease = {
                                        checkoutItems = checkoutItems.map {
                                            if (it.id == product.id)
                                                it.copy(quantity = it.quantity + 1)
                                            else it
                                        }
                                    },
                                    onDecrease = {
                                        checkoutItems = checkoutItems.map {
                                            if (it.id == product.id && it.quantity > 0)
                                                it.copy(quantity = it.quantity - 1)
                                            else it
                                        }
                                    },
                                    onQuantityChange = { newQty ->
                                        checkoutItems = checkoutItems.map {
                                            if (it.id == product.id)
                                                it.copy(quantity = newQty)
                                            else it
                                        }
                                    }
                                )

                                // Divider (except last item)
                                if (index != checkoutItems.lastIndex) {
                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        color = TextBlack.copy(alpha = 0.1f),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            // Bottom Summary Section ("Add More Items")
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(36.dp)
                                    .background(TextBlack.copy(alpha = 0.05f), Shapes.medium)
                                    .padding(Spacing.small),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Add More Items",
                                    style = AppTypography.bodyLarge,
                                    color = TextBlack
                                )
                            }
                        }
                    }

                    // 2. Promo Code
                    item {
                        PromoCodeCard(
                            modifier = Modifier.padding(horizontal = Spacing.large),
                            title = "Applied 10% OFF on 100 PCS",
                            subtitle = "of SM Tang Orange Tub (6x2kg)"
                        )
                    }

                    // 3. Invoice Summary
                    item {
                        InvoiceSummaryCard(
                            modifier = Modifier.padding(horizontal = Spacing.large),
                            itemsTotal = "AED 15360.00",
                            vat = "AED 1500.00",
                            discount = "AED 0.00",
                            grandTotal = "AED 16860.00"
                        )
                    }

                    // 4. Delivery Address
                    item {
                        DeliveryAddressCard(
                            modifier = Modifier.padding(horizontal = Spacing.large),
                            address = "S12, Kira Rd, Kampala Capital City, Central Region, 2121",
                            onChangeClick = { /* Handle Change Address */ }
                        )
                    }

                    // 5. Scrollable Bottom Bar
                    item {
                        CheckoutBottomBar(
                            totalAmount = "AED 16860.00",
                            onPlaceOrder = { /* Handle Place Order */ }
                        )
                    }
                }
            }
        }
    }
}

// ------------------------------------------------------------------------
// LOCAL CHECKOUT COMPONENTS
// ------------------------------------------------------------------------

@Composable
private fun CustomerInfoCard(name: String, phone: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White, Shapes.medium)
            .padding(horizontal = Spacing.medium, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Order for $name, $phone",
            style = AppTypography.bodyLarge.copy(fontSize = 14.sp),
            color = TextBlack
        )
    }
}

@Composable
private fun CheckoutInputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Int? = null,
    trailingIcon: Int? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    tint = TextBlack,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = label,
                style = AppTypography.bodyLarge,
                color = TextBlack
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.huge) // 48.dp
                .background(White.copy(alpha = 0.3f), Shapes.medium)
                .border(1.dp, TextBlack.copy(alpha = 0.16f), Shapes.medium)
                .padding(horizontal = Spacing.mediumLarge, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = AppTypography.bodyLarge.copy(color = TextBlack),
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    cursorBrush = SolidColor(TextBlack),
                    decorationBox = { innerTextField ->
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = AppTypography.bodyLarge.copy(color = TextBlack.copy(alpha = 0.4f))
                            )
                        }
                        innerTextField()
                    }
                )

                if (trailingIcon != null) {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = null,
                        tint = TextBlack,
                        modifier = Modifier.size(Spacing.large)
                    )
                }
            }
        }
    }
}

@Composable
private fun PaymentMethodRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Mode of Payment",
            color = TextGrey,
            style = AppTypography.bodyLarge.copy(fontSize = 14.sp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "MTN Mobile Money",
                color = TextBlack,
                style = AppTypography.titleMedium.copy(fontSize = 14.sp)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Select Payment",
                tint = TextBlack,
                modifier = Modifier.size(Spacing.medium)
            )
        }
    }
}

@Composable
private fun PromoCodeCard(
    modifier: Modifier = Modifier,
    title: String = "Apply Coupon",
    subtitle: String = "Enter your promo code"
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.medium)
            .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PromoBlue.copy(alpha = 0.08f))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            Icon(
                painter = painterResource(R.drawable.check),
                contentDescription = "Promo",
                modifier = Modifier.size(Spacing.extraLarge), // 32.dp
                tint = Color.Unspecified
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = PromoBlue
                )
                Text(
                    text = subtitle,
                    style = AppTypography.bodyLarge.copy(fontSize = 14.sp),
                    color = TextGrey
                )
            }
        }

        HorizontalDivider(thickness = 1.dp, color = TextBlack.copy(alpha = 0.1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(Spacing.mediumLarge)
        ) {
            CommonProductCard(
                title = "SM Tang Orange Tub (6x2kg)",
                subtitle = "1 CSE • 1 PCS",
                price = "00.00",
                imageUrl = R.drawable.sm_tang,
                quantity = 10,
                showBorder = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun InvoiceSummaryCard(
    modifier: Modifier = Modifier,
    itemsTotal: String,
    vat: String,
    discount: String,
    grandTotal: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.medium)
            .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(horizontal = Spacing.mediumLarge, vertical = Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            Text(
                text = "Invoice Summary",
                style = AppTypography.titleMedium.copy(fontSize = 18.sp),
                color = TextBlack
            )

            Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                InvoiceRow("Items Total", itemsTotal)
                InvoiceRow("Vat", vat)
                InvoiceRow("Discount", discount)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextGrey.copy(alpha = 0.08f))
                .border(
                    1.dp,
                    TextBlack.copy(alpha = 0.1f),
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(horizontal = Spacing.mediumLarge, vertical = Spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Grand Total",
                style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = TextBlack
            )
            Text(
                text = grandTotal,
                style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = TextBlack
            )
        }
    }
}

@Composable
private fun InvoiceRow(label: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = AppTypography.bodyLarge.copy(fontSize = 14.sp),
            color = TextGrey
        )
        Text(
            text = amount,
            style = AppTypography.titleMedium.copy(fontSize = 14.sp),
            color = TextBlack
        )
    }
}

@Composable
private fun DeliveryAddressCard(modifier: Modifier = Modifier, address: String, onChangeClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White, Shapes.medium)
            .border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.medium)
            .padding(Spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.truck2),
            contentDescription = null,
            modifier = Modifier.size(Spacing.extraExtraLarge), // 40.dp
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Delivery to",
                style = AppTypography.titleMedium.copy(fontSize = 14.sp),
                color = TextBlack
            )
            Text(
                text = address,
                style = AppTypography.bodyLarge.copy(fontSize = 12.sp),
                color = TextGrey,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = "Change",
            style = AppTypography.bodyLarge.copy(fontSize = 14.sp),
            color = BrandRed,
            modifier = Modifier.clickable { onChangeClick() }
        )
    }
}

// ------------------------------------------------------------------------
// CHECKOUT BOTTOM BAR
// ------------------------------------------------------------------------
@Composable
private fun CheckoutBottomBar(totalAmount: String, onPlaceOrder: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = Spacing.large,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                spotColor = Color.Black.copy(alpha = 0.08f),
                ambientColor = Color.Black.copy(alpha = 0.08f)
            ),
        color = White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column {
            PaymentMethodRow(Modifier.padding(horizontal = Spacing.large, vertical = Spacing.medium))

            HorizontalDivider(thickness = 1.dp, color = TextBlack.copy(alpha = 0.1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.large, vertical = Spacing.mediumLarge)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Total",
                        style = AppTypography.bodyLarge.copy(fontSize = 14.sp),
                        color = TextGrey
                    )
                    Text(
                        text = totalAmount,
                        style = AppTypography.titleMedium.copy(fontSize = 16.sp),
                        color = TextBlack
                    )
                }

                CommonButton(
                    text = "Place Order",
                    modifier = Modifier.width(157.dp).height(42.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Place Order",
                            tint = White,
                            modifier = Modifier.size(Spacing.mediumLarge)
                        )
                    },
                    onClick = onPlaceOrder
                )
            }
        }
    }
}