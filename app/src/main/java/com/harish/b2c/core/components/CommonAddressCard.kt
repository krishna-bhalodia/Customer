package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import com.harish.b2c.ui.theme.PrimaryPurple
import com.harish.b2c.ui.theme.PrimaryPurpleBg
import com.harish.b2c.ui.theme.Signika
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey
import com.harish.b2c.ui.theme.White

@Composable
fun CommonAddressCard(
    storeName: String,
    address: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(16.dp))
            .border(1.dp, TextBlack.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Icon Box (Store)
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(PrimaryPurpleBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location), // Using existing store_front.xml
                contentDescription = "Store",
                tint = PrimaryPurple,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Center Text
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = storeName,
                fontFamily = Signika,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = TextBlack
            )
            Text(
                text = address,
                fontFamily = Signika,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = TextGrey,
                lineHeight = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Right Edit Button
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(TextBlack.copy(alpha = 0.08f), CircleShape)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "Edit Address",
                tint = TextBlack,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}