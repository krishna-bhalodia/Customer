package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.Signika
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey
import com.harish.b2c.ui.theme.White

@Composable
fun CommonComplaintCard(
    complaintId: String,
    title: String,
    status: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .background(White, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Top Row: Info and Arrow
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = complaintId,
                    fontFamily = Signika,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = TextGrey
                )
                Text(
                    text = title,
                    fontFamily = Signika,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = TextBlack
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Arrow Box
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(TextBlack.copy(alpha = 0.08f), CircleShape)
                    .border(1.dp, White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "View Details",
                    tint = TextBlack,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Bottom Row: Status Pill
        Box(
            modifier = Modifier
                .background(TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = status,
                fontFamily = Signika,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = TextBlack
            )
        }
    }
}