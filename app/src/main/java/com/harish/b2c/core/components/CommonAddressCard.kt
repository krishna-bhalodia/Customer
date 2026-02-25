package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.R
import com.harish.b2c.ui.theme.*

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
            .background(White, Shapes.large)
            .border(1.dp, BorderLight, Shapes.large)
            .padding(Spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Icon Box
        Box(
            modifier = Modifier
                .size(42.dp) // Specific UI size for the avatar-style circle
                .background(PrimaryPurpleBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Store",
                tint = PrimaryPurple,
                modifier = Modifier.size(Spacing.large)
            )
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        // Center Text
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
        ) {
            Text(
                text = storeName,
                style = AppTypography.titleMedium, // Removed hardcoded Signika/14.sp
                color = TextBlack
            )
            Text(
                text = address,
                style = AppTypography.labelSmall, // Removed hardcoded Signika/12.sp
                color = TextGrey
            )
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        // Right Edit Button
        Box(
            modifier = Modifier
                .size(Spacing.extraLarge)
                .background(BorderLight, CircleShape)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.edit),
                contentDescription = "Edit Address",
                tint = TextBlack,
                modifier = Modifier.size(Spacing.medium)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FA)
@Composable
private fun CommonAddressCardPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        CommonAddressCard(
            storeName = "S12, Kira Rd",
            address = "Kampala Capital City, Uganda",
            onEditClick = {}
        )
    }
}