package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CommonProductCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // State: Default with border
            CommonProductCard(
                title = "Oner Apple 1 LTR * 6",
                subtitle = "10 CSE • 12 PCS",
                price = "240.00",
                imageUrl = R.drawable.oner_apple,
                quantity = 0
            )

            // State: No border (For Checkout grouping)
            CommonProductCard(
                title = "SM Tang Orange Tub (6x2kg)",
                subtitle = "1 CSE • 1 PCS",
                price = "30.00",
                imageUrl = R.drawable.sm_tang,
                quantity = 5,
                showBorder = false
            )
        }
    }
}

@Composable
fun CommonProductCard(
    title: String,
    subtitle: String,
    price: String,
    imageUrl: Int,
    quantity: Int = 0,
    onAddClick: () -> Unit = {},
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},
    onQuantityChange: (Int) -> Unit = {},
    showBorder: Boolean = true, // 🔥 Added parameter here
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            // Drop fixed height and internal padding if border is hidden (fits nicely in parent containers)
            .then(if (showBorder) Modifier.height(100.dp) else Modifier.wrapContentHeight())
            .background(Color.White, RoundedCornerShape(16.dp))
            .then(if (showBorder) Modifier.border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp)) else Modifier)
            .padding(if (showBorder) 12.dp else 0.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // ---------------- TOP ROW ----------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            // Image + Title Block
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = title,
                        fontFamily = Signika,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = TextBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = subtitle,
                        fontFamily = Signika,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = TextBlack
                    )
                }
            }

            // Price (Right aligned)
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "AED",
                    fontFamily = Signika,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    color = TextGrey,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )

                Text(
                    text = price,
                    fontFamily = Signika,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    color = TextBlack,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
            }
        }

        // ---------------- BOTTOM ROW ----------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // SKU text (Grey)
            Text(
                text = "HFG0000136 • ${title.split(" ").lastOrNull() ?: "Item"}",
                fontFamily = Signika,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = TextGrey
            )

            if (quantity == 0) {
                // ADD BUTTON
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .border(1.dp, SuccessGreen, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAddClick() }
                        .padding(horizontal = 16.dp, vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Add",
                        color = SuccessGreen,
                        fontFamily = Signika,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        letterSpacing = 0.2.sp
                    )
                }

            } else {

                // 🔥 QUANTITY SELECTOR WITH ROBUST TYPING
                var textValue by remember { mutableStateOf(quantity.toString()) }

                LaunchedEffect(quantity) {
                    val parsed = textValue.toIntOrNull() ?: 0
                    if (quantity != parsed) {
                        textValue = quantity.toString()
                    }
                }

                Row(
                    modifier = Modifier
                        .width(104.dp)
                        .height(30.dp)
                        .border(1.dp, TextBlack.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 6.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // MINUS BUTTON
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .clickable { onDecrease() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.subtract),
                            contentDescription = "Decrease",
                            tint = TextBlack.copy(alpha = 0.3f),
                            modifier = Modifier.size(14.dp)
                        )
                    }

                    // EDITABLE QUANTITY TEXT
                    BasicTextField(
                        value = textValue,
                        onValueChange = { newValue ->
                            if (newValue.isEmpty()) {
                                textValue = ""
                                onQuantityChange(0)
                            } else if (newValue.all { it.isDigit() }) {
                                val cleanValue = if (newValue.length > 1 && newValue.startsWith("0")) {
                                    newValue.trimStart('0').ifEmpty { "0" }
                                } else {
                                    newValue
                                }
                                textValue = cleanValue
                                onQuantityChange(cleanValue.toIntOrNull() ?: 0)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            fontFamily = Signika,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            letterSpacing = 0.2.sp,
                            color = TextBlack,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        singleLine = true,
                        cursorBrush = SolidColor(SuccessGreen),
                        decorationBox = { innerTextField ->
                            Box(contentAlignment = Alignment.Center) {
                                innerTextField()
                            }
                        }
                    )

                    // PLUS BUTTON
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .clickable { onIncrease() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Increase",
                            tint = SuccessGreen,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}