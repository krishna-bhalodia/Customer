package com.harish.b2c.presentation.promotions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.core.components.*
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun PromotionsScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val allBanners = listOf("oner_banner.svg", "energy_banner.svg", "soft_drink_banner.svg", "pulpe_banner.svg")
    val filteredBanners = remember(searchQuery) {
        if (searchQuery.isEmpty()) allBanners else allBanners.filter { it.contains(searchQuery, ignoreCase = true) }
    }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CommonAppBar(
                title = "Checkout",
                onBackClick = { navController.popBackStack() },
                modifier = Modifier.statusBarsPadding()
            )
            Spacer(modifier = Modifier.height(Spacing.medium))
            CommonSearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search promotions...",
                modifier = Modifier.padding(horizontal = Spacing.large, vertical = Spacing.small)
            )
            Spacer(modifier = Modifier.height(Spacing.medium))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = White, shape = RoundedCornerShape(topStart = Spacing.large, topEnd = Spacing.large))
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = Spacing.large, bottom = 0.dp),
                    verticalArrangement = Arrangement.spacedBy(Spacing.large),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(filteredBanners) { bannerUrl ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = Spacing.large)
                                .height(150.dp)
                                .clip(Shapes.large)
                                .background(TextBlack.copy(alpha = 0.1f))
                        ) {
                            Text("Banner Image Placeholder", style = AppTypography.bodyLarge, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }

@Preview(showSystemUi = true)
@Composable
fun PromotionsScreenPreview() {
        GradientBackground {
            PromotionsScreen(navController = NavController(LocalContext.current))
        }
}