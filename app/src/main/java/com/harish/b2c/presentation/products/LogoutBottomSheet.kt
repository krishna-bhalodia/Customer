package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutBottomSheet(
    onDismiss: () -> Unit,
    onConfirmLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        dragHandle = {
            // Custom grey drag handle from Figma
            Box(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 24.dp)
                    .width(38.dp)
                    .height(3.dp)
                    .background(TextGrey, CircleShape)
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 34.dp), // Bottom padding for home indicator space
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // Header Section: Icon + Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(BrandRed.copy(alpha = 0.08f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout), // Make sure you have the logout.xml
                        contentDescription = "Logout",
                        tint = BrandRed,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = "Logout",
                    style = AppTypography.headlineMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = BrandRed
                )
            }

            // Divider
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = TextBlack.copy(alpha = 0.2f)
            )

            // Confirmation Text
            Text(
                text = "Are you sure you want to log out?",
                style = AppTypography.titleLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                ),
                color = TextBlack,
                modifier = Modifier.fillMaxWidth()
            )

            // Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Secondary Cancel Button
                Row(
                    modifier =Modifier.weight(1f).height(54.dp)
                        .background(TextBlack.copy(alpha = 0.05f), Shapes.large)
                        .clip(Shapes.large)
                        .clickable { /* Add new number logic */ }
                        .padding(horizontal = Spacing.small),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Cancel", // Matching the "Label" placeholder
                        style = AppTypography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = TextBlack
                    )
                }

                // Primary Logout Button
                CommonButton(
                    text = "Yes, Logout",
                    onClick = onConfirmLogout,
                    modifier = Modifier.weight(1f).height(54.dp)
                )
            }
        }
    }
}

// Preview to check the layout
@Preview(showBackground = true)
@Composable
fun LogoutBottomSheetPreview() {
    Box(modifier = Modifier.fillMaxSize().background(DarkGrey)) {
        LogoutBottomSheet(onDismiss = {}, onConfirmLogout = {})
    }
}