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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.B2CTheme
import com.harish.b2c.ui.theme.Shapes
import com.harish.b2c.ui.theme.TextBlack

@Composable
fun PromotionsScreen(
    onBackClick: () -> Unit = {}
) {
    // State for managing the search query
    var searchQuery by remember { mutableStateOf("") }

    // Filtered list based on search query
    val allBanners = listOf(
        "oner_banner.svg",
        "energy_banner.svg",
        "soft_drink_banner.svg",
        "pulpe_banner.svg"
    )

    val filteredBanners = remember(searchQuery) {
        if (searchQuery.isEmpty()) allBanners
        else allBanners.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Promotions",
                onBackClick = onBackClick,
                modifier = Modifier.statusBarsPadding().padding(top = 20.dp)
            )
        },
        bottomBar = {
            CommonBottomBar(
                selectedIndex = 2, // 2 representing the 'promotion' tab
                onItemSelected = { index ->
                    // Handle Navigation based on index
                },
                onFabClick = {
                    // Handle FAB (e.g., Cart) click
                }, modifier = Modifier.navigationBarsPadding()
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Improved Search Bar Integration
            CommonSearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search promotions...",
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                onSearch = { /* Handle explicit search if needed */ }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), // Removed horizontal padding here
                    contentPadding = PaddingValues(top = 24.dp, bottom = 0.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    items(filteredBanners) { bannerUrl ->
//                    CommonSvgImage(
//                        assetName = bannerUrl,
//                        contentDescription = "Promotion Banner",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(180.dp)
//                            .padding(horizontal = 16.dp)
//                            .clip(Shapes.large),
//                        contentScale = ContentScale.FillBounds
//                    )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(horizontal = 24.dp)
                                .height(150.dp)
                                .clip(Shapes.large) // Uses AppShapes from Shape.kt
                                .background(TextBlack.copy(alpha = 0.1f))
                        ) {
                            Text(
                                "Banner Image Placeholder",
                                style = AppTypography.bodyLarge,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
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
            PromotionsScreen()
        }
}