package com.harish.b2c.presentation.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.core.components.CommonAddressCard
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.BrandRed

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SavedAddressesScreenPreview() {
    GradientBackground{
    SavedAddressesScreen(
        onBackClick = {},
        onAddNewAddressClick = {},
        onEditAddressClick = {}
    )}
}

@Composable
fun SavedAddressesScreen(
    onBackClick: () -> Unit,
    onAddNewAddressClick: () -> Unit,
    onEditAddressClick: (String) -> Unit
) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Saved Addresses",
                    onBackClick = onBackClick,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 20.dp)
                )
            },
            bottomBar = {
                // Outlined Add New Address Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .navigationBarsPadding()
                ) {
                    CommonButton(
                        text = "Add New Address",
                        isOutlined = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = BrandRed,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        onClick = onAddNewAddressClick
                    )
                }
            }
        ) { paddingValues ->
            // List of Addresses
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    CommonAddressCard(
                        storeName = "Store Name 1",
                        address = "S12, Kira Rd, Kampala Capital City, Central Region, 2121",
                        onEditClick = { onEditAddressClick("store_1") }
                    )
                }

                item {
                    CommonAddressCard(
                        storeName = "Store Name 2",
                        address = "S12, Kira Rd, Kampala Capital City, Central Region, 2121",
                        onEditClick = { onEditAddressClick("store_2") }
                    )
                }
            }
        }
    }
