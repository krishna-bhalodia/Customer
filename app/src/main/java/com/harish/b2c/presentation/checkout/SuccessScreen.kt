package com.harish.b2c.presentation.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.B2CTheme
import com.harish.b2c.ui.theme.DarkGrey
import com.harish.b2c.ui.theme.Gray900
import com.harish.b2c.ui.theme.LoyaltyBg
import com.harish.b2c.ui.theme.LoyaltyBorder
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.TextGrey

@Composable
fun OrderSuccessScreen(
    onDownloadInvoice: () -> Unit,
    onBackToHome: () -> Unit
) {
    // GradientBackground provides the red/purple decorative blurs from your CSS
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))


            Icon(
                painter = painterResource(id = R.drawable.success),
                contentDescription = null,
                modifier = Modifier.size(240.dp).padding(end=24.dp),
                tint = Color.Unspecified // Keeps the multi-color SVG look
            )


            Spacer(modifier = Modifier.height(40.dp))

            // --- Success Text ---
            Text(
                text = "Your Order has been\naccepted",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                ),
                color = Gray900 // Using theme color
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your items has been placed and is on\nit’s way to being processed",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextGrey, // Using theme color
                    textAlign = TextAlign.Center,
                    lineHeight = 21.sp
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- Loyalty Points Card (Styled with Theme Colors) ---
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .background(LoyaltyBg, MaterialTheme.shapes.large) // Using theme
                    .border(2.dp, LoyaltyBorder, MaterialTheme.shapes.large) // Using theme
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "16 loyalty points earned on this order",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = DarkGrey // Using theme color
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1.2f))

            // --- Action Buttons ---
            CommonButton(
                text = "Download Invoice",
                onClick = onDownloadInvoice,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.invoice),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )

            TextButton(
                onClick = onBackToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Back to Home",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TextBlack, // Using theme color
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSuccessScreenPreview() {
    B2CTheme { // Using your project theme for fonts/colors
        OrderSuccessScreen(
            onDownloadInvoice = {},
            onBackToHome = {}
        )
    }
}