package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            modifier = Modifier.padding(Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            CommonProductCard(
                title = "Oner Apple 1 LTR * 6",
                subtitle = "10 CSE • 12 PCS",
                price = "240.00",
                imageUrl = R.drawable.oner_apple,
                quantity = 0
            )

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
    showBorder: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(if (showBorder) Modifier.height(100.dp) else Modifier.wrapContentHeight())
            .background(White, Shapes.large)
            .then(if (showBorder) Modifier.border(1.dp, TextBlack.copy(alpha = 0.1f), Shapes.large) else Modifier)
            .padding(if (showBorder) Spacing.small else 0.dp),
        verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
    ) {
        // ---------------- TOP ROW ----------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.extraLarge)
        ) {
            // Image + Title Block
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.small)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(Shapes.small),
                    contentScale = ContentScale.Crop
                )

                Column(
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
                        style = AppTypography.bodyMedium,
                        color = TextBlack
                    )
                }
            }

            // Price
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "AED",
                    style = AppTypography.bodyMedium.copy(fontSize = 14.sp),
                    color = TextGrey,
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
                Text(
                    text = price,
                    style = AppTypography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp),
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
            Text(
                text = "HFG0000136 • ${title.split(" ").lastOrNull() ?: "Item"}",
                style = AppTypography.bodyMedium,
                color = TextGrey
            )

            if (quantity == 0) {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .border(1.dp, SuccessGreen, Shapes.large)
                        .clip(Shapes.large)
                        .clickable { onAddClick() }
                        .padding(horizontal = Spacing.medium, vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Add",
                        color = SuccessGreen,
                        style = AppTypography.labelLarge.copy(fontSize = 14.sp, letterSpacing = 0.2.sp)
                    )
                }
            } else {
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
                        .border(1.dp, TextBlack.copy(alpha = 0.6f), Shapes.large)
                        .clip(Shapes.large)
                        .padding(horizontal = 6.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.size(18.dp).clip(CircleShape).clickable { onDecrease() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(painter = painterResource(id = R.drawable.subtract), contentDescription = "Decrease", tint = TextBlack.copy(alpha = 0.3f), modifier = Modifier.size(14.dp))
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
                        textStyle = AppTypography.labelLarge.copy(fontSize = 14.sp, letterSpacing = 0.2.sp, color = TextBlack, textAlign = TextAlign.Center),
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        singleLine = true,
                        cursorBrush = SolidColor(SuccessGreen),
                        decorationBox = { innerTextField -> Box(contentAlignment = Alignment.Center) { innerTextField() } }
                    )

                    Box(
                        modifier = Modifier.size(18.dp).clip(CircleShape).clickable { onIncrease() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(painter = painterResource(id = R.drawable.add), contentDescription = "Increase", tint = SuccessGreen, modifier = Modifier.size(14.dp))
                    }
                }
            }
        }
    }
}