package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.ui.theme.*

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
            .border(1.dp, BorderLight, Shapes.large)
            .background(White, Shapes.large)
            .clickable { onClick() }
            .padding(Spacing.medium),
        verticalArrangement = Arrangement.spacedBy(Spacing.mediumSmall)
    ) {
        // Top Row: Info and Arrow
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
            ) {
                Text(
                    text = complaintId,
                    style = AppTypography.labelSmall,
                    color = TextGrey
                )
                Text(
                    text = title,
                    style = AppTypography.bodyLarge,
                    color = TextBlack
                )
            }

            Spacer(modifier = Modifier.width(Spacing.medium))

            // Arrow Box
            Box(
                modifier = Modifier
                    .size(Spacing.large)
                    .background(BorderLight, CircleShape)
                    .border(1.dp, White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "View Details",
                    tint = TextBlack,
                    modifier = Modifier.size(Spacing.medium)
                )
            }
        }

        // Bottom Row: Status Pill
        Box(
            modifier = Modifier
                .background(BorderLight, Shapes.large)
                .padding(horizontal = Spacing.small, vertical = Spacing.extraSmall),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = status,
                style = AppTypography.titleMedium,
                color = TextBlack
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FA)
@Composable
private fun CommonComplaintCardPreview() {
    B2CTheme {
        Box(modifier = Modifier.padding(Spacing.medium)) {
            CommonComplaintCard(
                complaintId = "#CMP12345",
                title = "Damaged Product Received",
                status = "In Progress",
                onClick = {}
            )
        }
    }
}