package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.*

@Composable
fun CommonInvoiceSummaryCard(
    itemsTotal: String,
    vat: String,
    discount: String,
    grandTotal: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .background(White, RoundedCornerShape(16.dp))
    ) {
        // Top Section
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Invoice Summary",
                fontFamily = Signika,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = TextBlack
            )

            InvoiceRow(label = "Items Total", value = itemsTotal)
            InvoiceRow(label = "Vat", value = vat)
            InvoiceRow(label = "Discount", value = discount)
        }

        // Bottom Grand Total Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    TextGrey.copy(alpha = 0.08f),
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .border(
                    width = 1.dp,
                    color = TextBlack.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                    text = "AED $grandTotal",
                    fontFamily = Signika,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = TextBlack
                )
            }
        }
    }
}

@Composable
private fun InvoiceRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontFamily = Signika,
            fontSize = 14.sp,
            color = TextGrey
        )
        Text(
            text = "AED $value",
            fontFamily = Signika,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = TextBlack
        )
    }
}