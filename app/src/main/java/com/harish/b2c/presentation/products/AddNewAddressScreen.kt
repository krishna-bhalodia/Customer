package com.harish.b2c.presentation.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddNewAddressScreenPreview() {
    GradientBackground {
        AddNewAddressScreen(
            navController = NavController(LocalContext.current)
        )
    }
}

@Composable
fun AddNewAddressScreen(
    navController: NavController
) {
    var storeName by remember { mutableStateOf("") }
    var building by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Add New Address",
                onBackClick = { navController.popBackStack()  },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = Spacing.mediumLarge)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.large)
                    .navigationBarsPadding()
                    .imePadding()
            ) {
                CommonButton(
                    text = "Submit",
                    onClick = { }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Spacing.large)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge)
        ) {
            Spacer(modifier = Modifier.height(Spacing.extraSmall))

            AppInput(
                label = "Title of the Address",
                value = storeName,
                onValueChange = { storeName = it },
                placeholder = "e.g. Store Name, Address 1, etc..."
            )

            Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                AppInput(
                    label = "Address",
                    value = building,
                    onValueChange = { building = it },
                    placeholder = "Street Address"
                )
                AppInput(
                    value = street,
                    onValueChange = { street = it },
                    placeholder = "Apt, suite, etc"
                )
            }

            AppInput(
                label = "City",
                value = city,
                onValueChange = { city = it },
                placeholder = "e.g. Abu Dhabi"
            )

            // 4. Postal Code
            AppInput(
                label = "Postal Code",
                value = zipCode,
                onValueChange = { zipCode = it },
                placeholder = "e.g. 20004",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            AppInput(
                label = "State",
                value = region,
                onValueChange = {}, // Handled by onOptionSelected
                placeholder = "Select",
                options = listOf("Jaunpur", "Kampala", "Abu Dhabi", "Dubai"),
                onOptionSelected = { region = it },
                trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null, tint = TextBlack) }
            )

            AppInput(
                label = "Delivery Contact Number",
                value = "7878945610",
                isEditable = false,
                onValueChange = {},
                placeholder = "",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.telephone),
                        contentDescription = null,
                        tint = TextBlack
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            Spacer(modifier = Modifier.height(Spacing.large))
        }
    }
}

@Composable
private fun InputSection(
    label: String,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.extraSmall)) {
        Text(
            text = label,
            style = AppTypography.bodyLarge,
            color = TextBlack
        )
        content()
    }
}