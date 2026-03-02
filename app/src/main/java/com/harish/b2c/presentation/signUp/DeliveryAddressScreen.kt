package com.harish.b2c.presentation.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun DeliveryAddressScreen(navController: NavController) {
    var currentStep by remember { mutableIntStateOf(1) }
    var storeName by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Step 2 States
    var addressLine1 by remember { mutableStateOf("") }
    var addressLine2 by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    val labelStyle = AppTypography.bodyLarge.copy(
        fontSize = 18.sp,
        color = TextBlack
    )

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = if (currentStep == 1) "Basic Information" else "Delivery Address",
                onBackClick = {
                    if (currentStep == 2) currentStep = 1 else navController.navigateUp()
                },
                modifier = Modifier.statusBarsPadding().padding(top = Spacing.mediumLarge)
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(Spacing.large).navigationBarsPadding()) {
                CommonButton(
                    text = if (currentStep == 1) "Next" else "Submit",
                    onClick = {
                        if (currentStep == 1) currentStep = 2
                        else  navController.navigate(NavigationScreen.MainContainer.route) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
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
                                colors = listOf(BrandRed.copy(alpha = 0.5f), BrandRed)
                            ),
                            shape = CircleShape
                        )
                )
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            val text = if (currentStep == 1) "customer details" else "delivery address"
            Text(
                text = "Please complete the $text in the form below.",
                color = TextGrey,
                style = AppTypography.bodyLarge
            )

            Spacer(modifier = Modifier.height(Spacing.large))

            if (currentStep == 1) {
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge)) {
                    AppInput(
                        label = "Store Name",
                        value = storeName,
                        onValueChange = { storeName = it },
                        placeholder = "Enter Store Name",
                        leadingIcon = { Icon(painterResource(R.drawable.store_front), null, tint = TextBlack) }
                    )
                    AppInput(
                        label = "Owner Name",
                        value = ownerName,
                        onValueChange = { ownerName = it },
                        placeholder = "Enter Owner Name",
                        leadingIcon = { Icon(painterResource(R.drawable.person), null, tint = TextBlack) }
                    )
                    AppInput(
                        label = "Email Address",
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Enter Email Address",
                        leadingIcon = { Icon(painterResource(R.drawable.email), null, tint = TextBlack) }
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge)) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        AppInput(
                            label = "Address",
                            value = addressLine1,
                            onValueChange = { addressLine1 = it },
                            placeholder = "Street Address"
                        )
                        AppInput(
                            value = addressLine2,
                            onValueChange = { addressLine2 = it },
                            placeholder = "Apt, suite, etc"
                        )
                    }
                    AppInput(
                        label = "City",
                        value = city,
                        onValueChange = { city = it },
                        placeholder = "e.g. Abu Dhabi"
                    )

                    AppInput(
                        label = "Postal code",
                        value = postalCode,
                        onValueChange = { postalCode = it },
                        placeholder = "e.g. 20004"
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        AppInput(
                            label = "State",
                            value = state,
                            options = listOf("Jaunpur", "Kampala", "Abu Dhabi", "Dubai"),
                            onOptionSelected = { state = it }
                            ,onValueChange = {}, // Handled by onOptionSelected
                            placeholder = "Select",
                            trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null, tint = TextBlack) }
                        )
                    }
                }
            }
        }
    }
}

