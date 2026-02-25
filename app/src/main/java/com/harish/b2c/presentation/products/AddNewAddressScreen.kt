package com.harish.b2c.presentation.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.TextBlack

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddNewAddressScreenPreview() {
    GradientBackground {

    AddNewAddressScreen(
        onBackClick = {},
        onSubmitClick = {}
    )}
}

@Composable
fun AddNewAddressScreen(
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    var storeName by remember { mutableStateOf("") }
    var building by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") } // Used for ActionInput

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Add New Address",
                    onBackClick = onBackClick,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 20.dp)
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .navigationBarsPadding()
                        .imePadding() // Adjusts padding when keyboard opens
                ) {
                    CommonButton(
                        text = "Submit",
                        onClick = onSubmitClick
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Frame 1: Store Name
                InputSection(label = "Title of the Address") {
                    CommonInput(
                        value = storeName,
                        onValueChange = { storeName = it },
                        placeholder = "e.g. Store Name, Address 1, etc..."
                    )
                }

                // Frame 2: Address (Two Inputs)
                InputSection(label = "Address") {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CommonInput(
                            value = building,
                            onValueChange = { building = it },
                            placeholder = "Street Address"
                        )
                        CommonInput(
                            value = street,
                            onValueChange = { street = it },
                            placeholder = "Apt, suite, etc"
                        )
                    }
                }

                // Frame 3: City
                InputSection(label = "City") {
                    CommonInput(
                        value = city,
                        onValueChange = { city = it },
                        placeholder = "e.g. Abu Dhabi"
                    )
                }

                // Frame 4: Zip Code
                InputSection(label = "Postal Code") {
                    CommonInput(
                        value = zipCode,
                        onValueChange = { zipCode = it },
                        placeholder = "e.g.20004"
                    )
                }

                // Frame 5: Region (Dropdown/Trailing Icon)
                InputSection(label = "State") {
                    CommonActionInput(
                        value = region,
                        placeholder = "Select",
                        options = listOf("Jaunpur", "Kampala"),
                        icon = Icons.Default.KeyboardArrowDown,
                        iconPosition = ActionIconPosition.Trailing,
                        onOptionSelected = {
                            region = it
                        }
                    )
                }

                // Frame 6: Pin on Map (Leading Icon)
                InputSection(label = "Location") {
                    CommonInput(
                        value = "",
                        onValueChange = {},
                        placeholder = "7878945610",
                        leadingIcon ={
                            Icon(
                                painter = painterResource(id = R.drawable.telephone),
                                contentDescription = null
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }


// Helper wrapper to keep labels consistent and margins clean
@Composable
private fun InputSection(
    label: String,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            style = AppTypography.bodyLarge.copy(fontSize = 18.sp),
            color = TextBlack
        )
        content()
    }
}