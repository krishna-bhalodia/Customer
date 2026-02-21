package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White
import com.harish.b2c.ui.theme.appShapes
import com.harish.b2c.ui.theme.regularBody

@Composable
fun CommonLoyaltyCard(
    points: Int,
    onRedeemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val loyaltyBg = Color(0xFFFFF5DC)
    val loyaltyBorder = Color(0xFFFFC50B).copy(alpha = 0.3f)
    val orangeIconBg = Color(0xFFFC9E15).copy(alpha = 0.2f)
    val greyText = Color(0xFF7C858C)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(loyaltyBg, MaterialTheme.appShapes.large)
            .border(2.dp, loyaltyBorder, MaterialTheme.appShapes.large)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(orangeIconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFC9E15)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = points.toString(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFF212121)
                )
                Text(
                    text = "Loyalty Points Earned",
                    style = MaterialTheme.typography.regularBody.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = greyText
                )
            }
        }

        Button(
            onClick = onRedeemClick,
            colors = ButtonDefaults.buttonColors(containerColor = White),
            shape = MaterialTheme.appShapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
        ) {
            Text(
                text = "Redeem Now",
                color = TextBlack
            )
        }
    }
}