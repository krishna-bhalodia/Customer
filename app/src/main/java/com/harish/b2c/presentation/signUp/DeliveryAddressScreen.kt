package com.harish.b2c.presentation.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonInput
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.regularBody

@Composable
fun DeliveryAddressScreen(navController: NavController) {
    var currentStep by remember { mutableIntStateOf(1) }

    // Step 1 States
    var storeName by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Step 2 States
    var addressLine1 by remember { mutableStateOf("") }
    var addressLine2 by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    // Using theme typography instead of hardcoded TextStyle
    val labelStyle = AppTypography.regularBody.copy(
        fontSize = 18.sp,
        color = TextBlack
    )

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = if (currentStep == 1) "Basic Information" else {
                    "Delivery Address"
                },
                onBackClick = {
                    if (currentStep == 2) currentStep = 1 else navController.navigateUp()
                },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = Spacing.mediumLarge)
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(Spacing.large).navigationBarsPadding()) {
                CommonButton(
                    text = if (currentStep == 1) "Next" else "Submit",
                    onClick = {
                        if (currentStep == 1) currentStep = 2 else {
                            navController.navigate(
                                NavigationScreen.HomeScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = Spacing.large)
                .verticalScroll(rememberScrollState())
        ) {
            // --- Progress Bar ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.medium)
                    .height(Spacing.small)
                    .background(TextBlack.copy(alpha = 0.1f), CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(if (currentStep == 1) 0.5f else 1f)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFF7185),
                                    BrandRed
                                ) // Left the start color hardcoded as it wasn't in Color.kt
                            ),
                            shape = CircleShape
                        )
                )
            }

            Spacer(modifier = Modifier.height(Spacing.large))
            val text = if (currentStep == 1) "customer details" else "delivery address"

            Text(
                text = "Please complete the $text in the form below.",
                color = Color(0xFF7C858C), // Left hardcoded as it wasn't defined in Color.kt
                style = AppTypography.regularBody.copy(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(Spacing.large))

            if (currentStep == 1) {
                // --- STEP 1: Basic Information ---
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge)) {

                    // 1. Store Name
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("Store Name", style = labelStyle)
                        CommonInput(
                            value = storeName,
                            onValueChange = { storeName = it },
                            placeholder = "Enter Store Name",
                            leadingIcon = {
                                Icon(
                                    painterResource(R.drawable.store_front),
                                    "Store",
                                    tint = TextBlack
                                )
                            }
                        )
                    }

                    // 2. Owner Name
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("Owner Name", style = labelStyle)
                        CommonInput(
                            value = ownerName,
                            onValueChange = { ownerName = it },
                            placeholder = "Enter Owner Name",
                            leadingIcon = {
                                Icon(painterResource(R.drawable.person), "Owner", tint = TextBlack)
                            }
                        )
                    }

                    // 3. Email Address
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("Email Address", style = labelStyle)
                        CommonInput(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "Enter Email Address",
                            leadingIcon = {
                                Icon(painterResource(R.drawable.email), "Email", tint = TextBlack)
                            }
                        )
                    }
                }
            } else {
                // --- STEP 2: Address Details ---
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge)) {

                    // 1. Address (Two Input Boxes Stacked)
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("Address", style = labelStyle)
                        CommonInput(
                            value = addressLine1,
                            onValueChange = { addressLine1 = it },
                            placeholder = "Street Address"
                        )
                        CommonInput(
                            value = addressLine2,
                            onValueChange = { addressLine2 = it },
                            placeholder = "Apt, suite, etc"
                        )
                    }

                    // 2. City
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("City", style = labelStyle)
                        CommonInput(
                            value = city,
                            onValueChange = { city = it },
                            placeholder = "e.g. Abu Dhabi"
                        )
                    }

                    // 3. Postal Code
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("Postal code", style = labelStyle)
                        CommonInput(
                            value = postalCode,
                            onValueChange = { postalCode = it },
                            placeholder = "e.g.20004"
                        )
                    }

                    // 4. State (With trailing dropdown icon)
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("State", style = labelStyle)
                        CommonInput(
                            value = state,
                            onValueChange = { state = it },
                            placeholder = "Select",
                            trailingIcon = {
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Select State",
                                    tint = TextBlack
                                )
                            }
                        )
                    }
                }
            }
        }
    }

}