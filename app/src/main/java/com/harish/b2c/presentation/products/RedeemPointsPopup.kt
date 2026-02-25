package com.harish.b2c.presentation.loyalty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonSvgImage
import com.harish.b2c.ui.theme.*

@Preview(showBackground = true)
@Composable
fun RedeemPointsPopupPreview() {
    RedeemPointsPopup(
        onDismiss = {},
        onRedeem = {}
    )
}

@Composable
fun RedeemPointsPopup(
    onDismiss: () -> Unit,
    onRedeem: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Full screen overlay with 20% black background to simulate backdrop-filter
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
    ) {
        // Top Left Close Button (Matching "Group 1171275003" from CSS)
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = Spacing.large, top = 12.dp) // Adjusted for exact 56px offset
                .size(40.dp)
                .background(White.copy(alpha = 0.5f), CircleShape)
                .border(1.dp, TextBlack.copy(alpha = 0.08f), CircleShape)
                .clip(CircleShape)
                .clickable { onDismiss() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close, // Using standard close icon
                contentDescription = "Close",
                tint = TextBlack,
                modifier = Modifier.size(20.dp)
            )
        }

        // Centered Popup Card
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(327.dp)
                .background(White, RoundedCornerShape(24.dp))
                .padding(horizontal = Spacing.medium, vertical = Spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.large) // 24px gap between main sections
        ) {
            // Section 1: Image & Title
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                CommonSvgImage(resId = R.raw.booking, modifier = Modifier.size(62.dp))

                Text(
                    text = "Honeymoon Package Free Tickets",
                    style = AppTypography.titleLarge.copy(
                        fontSize = 22.sp,
                        lineHeight = 27.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = (-0.3).sp
                    ),
                    color = TextBlack,
                    textAlign = TextAlign.Center
                )
            }

            // Section 2: Coins Badge
            Row(
                modifier = Modifier
                    .background(LoyaltyBg, RoundedCornerShape(16.dp))
                    .border(2.dp, LoyaltyBorder, RoundedCornerShape(16.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = "Coins",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "2500 Coins",
                    style = AppTypography.bodyLarge.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = DarkGrey
                )
            }

            // Section 3: Offer Details
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.medium) // 16px gap
            ) {
                // Header Row with lines
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.large) // 24px gap
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = TextBlack.copy(alpha = 0.1f)
                    )
                    Text(
                        text = "Offer Details",
                        style = AppTypography.titleMedium.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextBlack,
                        textAlign = TextAlign.Center
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = TextBlack.copy(alpha = 0.1f)
                    )
                }

                // Description Text
                Text(
                    text = "1. Valid for select romantic destinations and partner airlines\n" +
                            "2. Easy point redemption with zero booking hassle\n" +
                            "3. Perfect reward for newly married couples\n" +
                            "4. Limited-time offer, subject to availability",
                    style = AppTypography.bodyLarge.copy(
                        fontSize = 14.sp,
                        lineHeight = 17.sp
                    ),
                    color = TextGrey,
                    textAlign = TextAlign.Start
                )
            }

            // Section 4: Redeem Button
            CommonButton(
                text = "Redeem",
                onClick = onRedeem,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}