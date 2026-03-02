package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Composable
fun CommonItemCard(
    title: String,
    subtitle: String,
    imageUrl: Any, // Can be R.drawable.xxx or URL String
    price: String,
    modifier: Modifier = Modifier,
    // Bottom Left Parameters
    sku: String? = null,
    statusText: String? = null,
    statusIcon: Int? = null,
    // Bottom Right / Trailing Parameters
    quantity: Int? = null,
    isEditableQuantity: Boolean = false,
    showTrailingArrow: Boolean = false,
    showBorder: Boolean = true,
    // Callbacks
    onAddClick: () -> Unit = {},
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},
    onQuantityChange: (Int) -> Unit = {},
    onClick: (() -> Unit)? = null
) {
    var hasBeenAdded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .background(White, Shapes.large)
            .then(if (showBorder) Modifier.border(1.dp, BorderLight, Shapes.large) else Modifier)
            .padding(
                horizontal = if (showBorder) Spacing.mediumSmall else 0.dp,
                vertical = if (showBorder) Spacing.mediumSmall else 8.dp // or Spacing.small
            ),
        verticalArrangement = Arrangement.spacedBy(Spacing.mediumSmall)
    ) {
        // ---------------- TOP ROW ----------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            // 1. Image or Icon Box
            if (imageUrl is Int && imageUrl == R.drawable.cart) {
                Box(
                    modifier = Modifier.size(38.dp).background(OrderIconBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painterResource(imageUrl), null, tint = OrderIconOrange, modifier = Modifier.size(20.dp))
                }
            } else {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier.size(38.dp).clip(Shapes.small),
                    contentScale = ContentScale.Crop
                )
            }

            // 2. Title and Subtitle
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
                    color = TextBlack,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    style = AppTypography.bodyMedium.copy(fontSize = 12.sp),
                    color = TextGrey
                )
            }

            // 3. Price Block
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text("AED", style = AppTypography.bodyMedium.copy(fontSize = 12.sp), color = TextGrey)
                Text(price, style = AppTypography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp), color = TextBlack)
            }

            // 4. Trailing Arrow (For Recent Orders)
            if (showTrailingArrow) {
                Box(
                    modifier = Modifier
                        .size(24.dp)

                        .background(BorderLight.copy(alpha = 0.08f), CircleShape)

                        .border(1.dp, White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "View Details",
                        // Assuming TextBlack is already mapped to #030304 in your theme
                        tint = TextBlack,
                        modifier = Modifier.size(16.dp) // 16x16 inner frame size
                    )
                }
            }
        }

        // ---------------- BOTTOM ROW ----------------
        val hasBottomRow = sku != null || statusText != null || quantity != null
        if (hasBottomRow) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Bottom Left: SKU or Status
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (sku != null) {
                        Text(text = sku, style = AppTypography.bodyMedium.copy(fontSize = 12.sp), color = TextGrey)
                    } else if (statusText != null) {
                        Text(text = statusText, color = TextBlack, style = AppTypography.bodyLarge.copy(fontSize = 12.sp))
                        if (statusIcon != null) {
                            Icon(painterResource(statusIcon), contentDescription = null, tint = Color.Unspecified, modifier = Modifier.size(14.dp))
                        }
                    }
                }
                // Bottom Right: Quantity or Static Qty Text
                if (quantity != null) {
                    if (isEditableQuantity) {
                        if (quantity < 0 && !hasBeenAdded){
                            // "Add" Button
                            Box(
                                modifier = Modifier
                                    .height(30.dp)
                                    .border(1.dp, SuccessGreen, Shapes.large)
                                    .clip(Shapes.large)
                                    .clickable { onAddClick() }
                                    .padding(horizontal = Spacing.medium, vertical = 5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Add", color = SuccessGreen, style = AppTypography.labelLarge.copy(fontSize = 14.sp))
                            }
                        } else {
                            // Quantity + / - Widget
                            QuantitySelectorWidget(quantity, onIncrease, onDecrease, onQuantityChange)
                        }
                    } else {
                        // Static Quantity for Order Items
                        Text(text = "Qty. $quantity", style = AppTypography.bodyMedium.copy(fontSize = 12.sp), color = TextGrey)
                    }
                }
            }
        }
    }
}

// Reusable internal widget for the quantity selector
@Composable
private fun QuantitySelectorWidget(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    var textValue by remember { mutableStateOf(quantity.toString()) }

    LaunchedEffect(quantity) {
        if (quantity.toString() != textValue) textValue = quantity.toString()
    }

    Row(
        modifier = Modifier
            .width(104.dp)
            .height(30.dp)
            .border(1.dp, TextBlack.copy(alpha = 0.6f), Shapes.large)
            .clip(Shapes.large)
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.size(18.dp).clip(CircleShape).clickable { onDecrease() }, contentAlignment = Alignment.Center) {
            Icon(painterResource(R.drawable.subtract), "Decrease", tint = TextBlack.copy(alpha = 0.3f), modifier = Modifier.size(14.dp))
        }

        BasicTextField(
            value = textValue,
            onValueChange = { newValue ->
                if (newValue.isEmpty()) {
                    textValue = ""
                    onQuantityChange(0)
                } else if (newValue.all { it.isDigit() }) {
                    val cleanValue = if (newValue.length > 1 && newValue.startsWith("0")) newValue.trimStart('0').ifEmpty { "0" } else newValue
                    textValue = cleanValue
                    onQuantityChange(cleanValue.toIntOrNull() ?: 0)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            textStyle = AppTypography.labelLarge.copy(fontSize = 14.sp, color = TextBlack, textAlign = TextAlign.Center),
            modifier = Modifier.weight(1f),
            singleLine = true,
            cursorBrush = SolidColor(SuccessGreen)
        )

        Box(modifier = Modifier.size(18.dp).clip(CircleShape).clickable { onIncrease() }, contentAlignment = Alignment.Center) {
            Icon(painterResource(R.drawable.add), "Increase", tint = SuccessGreen, modifier = Modifier.size(14.dp))
        }
    }
}

// -----------------------------------------------------------------------
// PREVIEWS DEMONSTRATING ALL STATES
// -----------------------------------------------------------------------

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun CommonItemCardPreview_AllStates() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("1. Product State (Add)", fontWeight = FontWeight.Bold)
            CommonItemCard(
                title = "Oner Apple 1 LTR * 6",
                subtitle = "10 CSE • 12 PCS",
                imageUrl = R.drawable.oner_apple,
                price = "240.00",
                sku = "HFG0000136 • Apple",
                quantity = 0,
                isEditableQuantity = true
            )

            Text("2. Product State (Quantity Selected)", fontWeight = FontWeight.Bold)
            CommonItemCard(
                title = "Oner Apple 1 LTR * 6",
                subtitle = "10 CSE • 12 PCS",
                imageUrl = R.drawable.oner_apple,
                price = "240.00",
                sku = "HFG0000136 • Apple",
                quantity = 5,
                isEditableQuantity = true
            )

            Text("3. Order Item State (Static Quantity)", fontWeight = FontWeight.Bold)
            CommonItemCard(
                title = "SM Tang Orange Tub",
                subtitle = "1 CSE • 1 PCS",
                imageUrl = R.drawable.sm_tang,
                price = "30.00",
                sku = "SKU-9922110",
                quantity = 1,
                isEditableQuantity = false // Shows "Qty. 1"
            )

            Text("4. Recent Order State (Status & Arrow)", fontWeight = FontWeight.Bold)
            CommonItemCard(
                title = "Order #827364",
                subtitle = "12 Oct 2023, 10:30 AM",
                imageUrl = R.drawable.cart,
                price = "500.00",
                showTrailingArrow = true,         // Adds the arrow next to Price
                statusText = "Delivered",         // Shows text on bottom left
                statusIcon = R.drawable.green_check // Shows icon next to status text
            )
        }
    }
}