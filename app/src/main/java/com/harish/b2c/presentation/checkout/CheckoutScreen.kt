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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Signika
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey

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
    // Dummy Data mapping to your CSS
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
                    .padding(top = 20.dp),
                onBackClick = onBackClick
            )
        }
        // Removed fixed BottomBar so it scrolls
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).navigationBarsPadding()
        ) {
            // Top section elements (overlapping the gradient background)
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Order Info Card
                CustomerInfoCard(name = "Rahman", phone = "7894561230")

                // Specific Input Field mapped from CSS
                CheckoutInputField(
                    label = "Choose delivery date",
                    placeholder = "Today, 28 Dec 2025",
                    value = orderNote,
                    onValueChange = { orderNote = it },
                    leadingIcon = R.drawable.truck,
                    trailingIcon = R.drawable.calender
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            // White Bottom Sheet-like Container matching CSS Rectangle 6
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), // Removed horizontal padding here
                    contentPadding = PaddingValues(top = 24.dp, bottom = 0.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    // 1. Selected Products List
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp) // Applied padding to the item instead
                                .background(
                                    Color.White,
                                    RoundedCornerShape(16.dp)
                                )
                                .border(
                                    1.dp,
                                    TextBlack.copy(alpha = 0.1f),
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
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

                                // 🔥 Divider (except last item)
                                if (index != checkoutItems.lastIndex) {
                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        color = TextBlack.copy(alpha = 0.1f),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            // 🔥 Bottom Summary Section
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(36.dp)
                                    .background(
                                        TextBlack.copy(alpha = 0.05f),
                                        RoundedCornerShape(16.dp)
                                    )
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Add More Items",
                                    fontFamily = Signika,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = TextBlack
                                )
                            }
                        }
                    }

                    // 2. Mode of Payment

                    // 3. Promo Code
                    item {
                        PromoCodeCard(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            title = "Applied 10% OFF on 100 PCS",
                            subtitle = "of SM Tang Orange Tub (6x2kg)"
                        )
                    }

                    // 4. Invoice Summary
                    item {
                        InvoiceSummaryCard(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            itemsTotal = "AED 15360.00",
                            vat = "AED 1500.00",
                            discount = "AED 0.00",
                            grandTotal = "AED 16860.00"
                        )
                    }

                    // 5. Delivery Address
                    item {
                        DeliveryAddressCard(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            address = "S12, Kira Rd, Kampala Capital City, Central Region, 2121",
                            onChangeClick = { /* Handle Change Address */ }
                        )
                    }


                    // 6. Scrollable Bottom Bar (Full Width)
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
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Order for $name, $phone",
            fontFamily = Signika,
            fontSize = 14.sp,
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
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                fontFamily = Signika,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = TextBlack
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    Color.White.copy(alpha = 0.3f),
                    RoundedCornerShape(16.dp)
                )
                .border(
                    1.dp,
                    TextBlack.copy(alpha = 0.16f),
                    RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        fontFamily = Signika,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = TextBlack
                    ),
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    cursorBrush = SolidColor(TextBlack),
                    decorationBox = { innerTextField ->
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                fontFamily = Signika,
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                color = TextBlack.copy(alpha = 0.4f)
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
                        modifier = Modifier.size(24.dp)
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
            fontFamily = Signika,
            fontSize = 14.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "MTN Mobile Money",
                color = TextBlack,
                fontFamily = Signika,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Select Payment",
                tint = TextBlack,
                modifier = Modifier.size(16.dp)
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
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0088FF).copy(alpha = 0.08f))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painterResource(R.drawable.check), // Fallback if Check doesn't exist use Icons.Default.Star
                contentDescription = "Promo",
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    fontFamily = Signika,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF0088FF)
                )
                Text(
                    text = subtitle,
                    fontFamily = Signika,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = TextGrey
                )
            }
        }

        HorizontalDivider(thickness = 1.dp, color = TextBlack.copy(alpha = 0.1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
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
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp, 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Invoice Summary",
                fontFamily = Signika,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = TextBlack
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                InvoiceRow("Items Total", itemsTotal)
                InvoiceRow("Vat", vat)
                InvoiceRow("Discount", discount)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF7C858C).copy(alpha = 0.08f))
                .border(
                    1.dp,
                    TextBlack.copy(alpha = 0.1f),
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(20.dp, 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Grand Total",
                fontFamily = Signika,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = TextBlack
            )
            Text(
                text = grandTotal,
                fontFamily = Signika,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
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
        Text(text = label, fontFamily = Signika, color = TextGrey, fontSize = 14.sp)
        Text(
            text = amount,
            fontFamily = Signika,
            color = TextBlack,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun DeliveryAddressCard(modifier: Modifier = Modifier, address: String, onChangeClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.truck2),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Delivery to",
                fontFamily = Signika,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = TextBlack
            )
            Text(
                text = address,
                fontFamily = Signika,
                fontSize = 12.sp,
                color = TextGrey,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = "Change",
            fontFamily = Signika,
            fontSize = 14.sp,
            color = BrandRed,
            modifier = Modifier.clickable { onChangeClick() }
        )
    }
}

// ------------------------------------------------------------------------
// REFACTORED SCROLLABLE BOTTOM BAR
// ------------------------------------------------------------------------
@Composable
private fun CheckoutBottomBar(totalAmount: String, onPlaceOrder: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                spotColor = Color.Black.copy(alpha = 0.08f),
                ambientColor = Color.Black.copy(alpha = 0.08f)
            ),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column {
             PaymentMethodRow(Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            // Divider replicating Line 3 from CSS
            HorizontalDivider(thickness = 1.dp, color = TextBlack.copy(alpha = 0.1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .navigationBarsPadding(), // Ensures it doesn't collide with system nav
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Total",
                        fontFamily = Signika,
                        color = TextGrey,
                        fontSize = 14.sp
                    )
                    Text(
                        text = totalAmount,
                        fontFamily = Signika,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = TextBlack
                    )
                }

                // Place Order Button explicitly matching the 157x42 constraints & gradients
                CommonButton(text = "Place Order",
                    modifier = Modifier.width(157.dp).height(42.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Place Order",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    },onClick={})
            }
        }
    }
}