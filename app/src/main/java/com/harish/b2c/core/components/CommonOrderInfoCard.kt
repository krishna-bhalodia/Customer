package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

@Composable
fun CommonOrderInfoCard(
    orderId: String,
    paymentMethod: String,
    deliveryAddress: String,
    orderDate: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .background(White, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Order Details",
            fontFamily = Signika,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = TextBlack
        )

        InfoItem(label = "Order Id", value = orderId)
        InfoItem(label = "Payment", value = paymentMethod)
        InfoItem(label = "Deliver To", value = deliveryAddress)
        InfoItem(label = "Order placed", value = orderDate)
    }
}

@Composable
private fun InfoItem(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label, fontFamily = Signika, fontSize = 12.sp, color = TextGrey)
        Text(text = value, fontFamily = Signika, fontSize = 14.sp, color = TextBlack)
    }
}

@Composable
fun CommonSupportCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .background(White, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Need help with your order?",
            fontFamily = Signika,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = TextBlack
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(HelpPurpleBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(R.drawable.notepad), contentDescription = null, tint = HelpPurple, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Raise complaint", fontFamily = Signika, fontSize = 14.sp, color = TextBlack)
                Text(text = "About any issues related to your order", fontFamily = Signika, fontSize = 12.sp, color = TextGrey)
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(TextBlack.copy(alpha = 0.08f), CircleShape)
                    .border(1.dp, White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = TextBlack, modifier = Modifier.size(16.dp))
            }
        }
    }
}