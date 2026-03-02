package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonChipRow
import com.harish.b2c.core.components.CommonItemCard
import com.harish.b2c.core.components.CommonSearchBar
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.White

@Preview(
    name = "Products Screen Preview"
)
@Composable
fun ProductsScreenPreview() {
    GradientBackground {
        ProductsScreen(
            navController = NavController(LocalContext.current),
        )
    }
}

// Dummy Data Class
data class ProductItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val price: String,
    var quantity: Int,
    val imageUrl: Int,
    val sku: String? = null,
)

@Composable
fun ProductsScreen(
    navController: NavController,
) {
    // Dummy Data mimicking your CSS values
    var productList by remember {
        mutableStateOf(
            listOf(
                ProductItem(
                    1,
                    "Oner Apple 1 LTR * 6",
                    "10 CSE • 12 PCS",
                    "240.00",
                    -1,
                    R.drawable.oner_apple
                ),
                ProductItem(
                    2,
                    "SM Tang Orange Tub (6x2kg)",
                    "1 CSE • 1 PCS",
                    "30.00",
                    1,
                    R.drawable.sm_tang
                ),
                ProductItem(
                    3,
                    "Orchid Valley Guava Delight",
                    "1 CSE • 24 PCS",
                    "360.00",
                    100,
                    R.drawable.orchid
                ),
                ProductItem(
                    4,
                    "Predator Energy Gold, 250ml",
                    "5 CSE • 120 PCS",
                    "2400.00",
                    -1,
                    R.drawable.predator
                ),
                ProductItem(
                    5,
                    "Oner Apple 1 LTR * 6",
                    "10 CSE • 12 PCS",
                    "240.00",
                    -1,
                    R.drawable.oner_apple
                ),
                ProductItem(
                    6,
                    "SM Tang Orange Tub (6x2kg)",
                    "1 CSE • 1 PCS",
                    "30.00",
                    -1,
                    R.drawable.sm_tang
                ),
                ProductItem(
                    7,
                    "Orchid Valley Guava Delight",
                    "1 CSE • 24 PCS",
                    "360.00",
                    100,
                    R.drawable.orchid
                )
            )
        )
    }

    val categories =
        listOf("All", "CSD", "Candy", "Lolly Pop", "Gum", "Energy Drink", "Juice", "Water", "Cake")
    var selectedCategory by remember { mutableStateOf(categories[0]) }
    var searchQuery by remember { mutableStateOf("") }



        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CommonAppBar(
                title = "Products",
                modifier = Modifier.statusBarsPadding(),
                onBackClick = { navController.popBackStack() }
            )
            Box(modifier = Modifier.padding(horizontal = Spacing.large, vertical = Spacing.small)) {
                CommonSearchBar(value = searchQuery, onValueChange = { searchQuery = it }, placeholder = "Search...")
            }

            CommonChipRow(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                modifier = Modifier.padding(vertical = Spacing.medium)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(White, shape = RoundedCornerShape(topStart = Spacing.large, topEnd = Spacing.large))
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = Spacing.large),
                    contentPadding = PaddingValues(top = Spacing.large, bottom = Spacing.large),
                    verticalArrangement = Arrangement.spacedBy(Spacing.mediumSmall)
                ) {
                    items(productList, key = { it.id }) { product ->
                        CommonItemCard(
                            title = product.title,
                            subtitle = product.subtitle,
                            price = product.price,
                            imageUrl = product.imageUrl,

                            // 1. Pass the quantity
                            quantity = product.quantity,

                            // 2. TELL the card to make the quantity interactive (shows Add / +/- widget)
                            isEditableQuantity = true,

                            // Optional: If you want the SKU to show up on the bottom left like it used to
                            sku = "HFG0000136 • ${product.title.split(" ").lastOrNull() ?: "Item"}",

                            // 3. Keep all your exact same state-updating callbacks
                            onAddClick = {
                                productList = productList.map {
                                    if (it.id == product.id) it.copy(quantity = 1) else it
                                }
                            },
                            onIncrease = {
                                productList = productList.map {
                                    if (it.id == product.id) it.copy(quantity = it.quantity + 1) else it
                                }
                            },
                            onDecrease = {
                                productList = productList.map {
                                    if (it.id == product.id && it.quantity > 0) it.copy(quantity = it.quantity - 1) else it
                                }
                            },
                            onQuantityChange = { newQty ->
                                productList = productList.map {
                                    if (it.id == product.id) it.copy(quantity = newQty) else it
                                }
                            }
                        )
                    }
                }
            }
        }
    }
