package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harish.b2c.ui.theme.*

@Composable
fun CommonOrderItemCard(
    title: String,
    subtitle: String,
    price: String,
    imageUrl: Int,
    sku: String,
    quantity: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
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
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Gray900,
                    letterSpacing = 0.5.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "AED",
                    fontFamily = Signika,
                    fontSize = 14.sp,
                    color = TextGrey
                )
                Text(
                    text = price,
                    fontFamily = Signika,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = TextBlack
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sku,
                fontFamily = Signika,
                fontSize = 12.sp,
                color = TextGrey
            )
            Text(
                text = "Qty. $quantity",
                fontFamily = Signika,
                fontSize = 12.sp,
                color = TextGrey
            )
        }
    }
}