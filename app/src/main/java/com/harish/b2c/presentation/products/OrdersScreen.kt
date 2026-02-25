package com.harish.b2c.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonRecentOrderCard
import com.harish.b2c.core.components.CommonSegmentedTab
import com.harish.b2c.core.components.GradientBackground

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun OrdersScreenPreview() {
    GradientBackground {

        OrdersScreen(
            onBackClick = {}
        )

    }
}

@Composable
fun OrdersScreen(
    onBackClick: () -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Ongoing", "History")

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Your Orders",
                onBackClick = onBackClick,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 20.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Tab Bar
            CommonSegmentedTab(
                tabs = tabs,
                selectedIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it },
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 2. Orders List using your existing CommonRecentOrderCard
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Dummy items loop based on Figma
                items(5) {
                    CommonRecentOrderCard(
                        orderId = "#MHO00300010",
                        date = "Placed on 17 Oct, 2025",
                        amount = "1240.00",
                        status = if (selectedTabIndex == 0) "Expected delivery on 18 Oct" else "Order Delivered",
                        deliveryIcon =if (selectedTabIndex == 0) R.drawable.truck_yellow else R.drawable.green_check,
                    )
                }
            }
        }
    }
}
