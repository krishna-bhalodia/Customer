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
                        else navController.navigate(NavigationScreen.HomeScreen.route)
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
                    FormInputGroup("Store Name", storeName, "Enter Store Name", R.drawable.store_front) { storeName = it }
                    FormInputGroup("Owner Name", ownerName, "Enter Owner Name", R.drawable.person) { ownerName = it }
                    FormInputGroup("Email Address", email, "Enter Email Address", R.drawable.email) { email = it }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge)) {
                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("Address", style = labelStyle)
                        CommonInput(addressLine1, { addressLine1 = it }, "Street Address")
                        CommonInput(addressLine2, { addressLine2 = it }, "Apt, suite, etc")
                    }
                    FormInputGroup("City", city, "e.g. Abu Dhabi", null) { city = it }
                    FormInputGroup("Postal code", postalCode, "e.g. 20004", null) { postalCode = it }

                    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
                        Text("State", style = labelStyle)
                        CommonInput(
                            value = state,
                            onValueChange = { state = it },
                            placeholder = "Select",
                            trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null, tint = TextBlack) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FormInputGroup(label: String, value: String, placeholder: String, iconRes: Int?, onValueChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.small)) {
        Text(label, style = AppTypography.bodyLarge.copy(fontSize = 18.sp, color = TextBlack))
        CommonInput(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            leadingIcon = iconRes?.let { { Icon(painterResource(it), label, tint = TextBlack) } }
        )
    }
}