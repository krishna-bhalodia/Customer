package com.harish.b2c.presentation.products


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.Spacing

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationsScreenPreview() {
    GradientBackground {
        NotificationsScreen(onBackClick = {})}
}

@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit
) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Notifications",
                    onBackClick = onBackClick,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = Spacing.mediumLarge)
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = Spacing.large, vertical = Spacing.medium),
                verticalArrangement = Arrangement.spacedBy(Spacing.mediumSmall) // 10px gap in CSS
            ) {
                // 1. Unread Delivery
                item {
                    CommonNotificationCard(
                        title = "Order #MHO00300010",
                        description = "781 Hilll Junctions",
                        time = "6:30 pm",
                        isUnread = true, // Solid White Background
                        type = NotificationType.DELIVERY,
                        onClick = { /* Handle click */ }
                    )
                }

                // 2. Read Delivery
                item {
                    CommonNotificationCard(
                        title = "Order #MHO00300010",
                        description = "781 Hilll Junctions",
                        time = "6:30 pm",
                        isUnread = false, // 40% White Background
                        type = NotificationType.SUCCESS,
                        onClick = { /* Handle click */ }
                    )
                }

                // 3. Read Promo
                item {
                    CommonNotificationCard(
                        title = "16 loyalty points earned",
                        description = "You have earned new loyalty points on your last order.",
                        time = "28 Dec, 2025",
                        isUnread = false,
                        type = NotificationType.PROMO,
                        onClick = { /* Handle click */ }
                    )
                }

                // 4. Read Delivery (Repeating layout from CSS)
                item {
                    CommonNotificationCard(
                        title = "Order #MHO00300010",
                        description = "781 Hilll Junctions",
                        time = "6:30 pm",
                        isUnread = false,
                        type = NotificationType.DELIVERY,
                        onClick = { /* Handle click */ }
                    )
                }
            }
        }

}