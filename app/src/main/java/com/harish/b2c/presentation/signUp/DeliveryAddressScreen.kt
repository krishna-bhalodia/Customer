package com.harish.b2c.presentation.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.*

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

    val labelStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.signika_regular)),
        fontSize = 18.sp,
        color = Color(0xFF030304)
    )

    GradientBackground {
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
                    modifier = Modifier.statusBarsPadding().padding(top = 20.dp)
                )
            },
            bottomBar = {
                Box(modifier = Modifier.padding(24.dp)) {
                    CommonButton(
                        text = if (currentStep == 1) "Next" else "Submit",
                        onClick = { if (currentStep == 1) currentStep = 2 else { /* Handle Submit */ } }
                    )
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // --- Progress Bar ---
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(8.dp)
                        .background(Color(0xFF030304).copy(alpha = 0.1f), RoundedCornerShape(100.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(if (currentStep == 1) 0.5f else 1f)
                            .fillMaxHeight()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFFFF7185), Color(0xFFEA0A2A))
                                ),
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                val text= if (currentStep == 1) "customer details" else "delivery address"
                Text(
                    text = "Please complete the $text in the form below.",
                    color = Color(0xFF7C858C),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.signika_regular))
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (currentStep == 1) {
                    // --- STEP 1: Basic Information ---
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

                        // 1. Store Name
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Store Name", style = labelStyle)
                            CommonInput(
                                value = storeName,
                                onValueChange = { storeName = it },
                                placeholder = "Enter Store Name",
                                leadingIcon = {
                                    Icon(painterResource(R.drawable.store_front), "Store", tint = Color(0xFF030304))
                                }
                            )
                        }

                        // 2. Owner Name
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Owner Name", style = labelStyle)
                            CommonInput(
                                value = ownerName,
                                onValueChange = { ownerName = it },
                                placeholder = "Enter Owner Name",
                                leadingIcon = {
                                    Icon(painterResource(R.drawable.person), "Owner", tint = Color(0xFF030304))
                                }
                            )
                        }

                        // 3. Email Address
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Email Address", style = labelStyle)
                            CommonInput(
                                value = email,
                                onValueChange = { email = it },
                                placeholder = "Enter Email Address",
                                leadingIcon = {
                                    Icon(painterResource(R.drawable.email), "Email", tint = Color(0xFF030304))
                                }
                            )
                        }
                    }
                } else {
                    // --- STEP 2: Address Details ---
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

                        // 1. Address (Two Input Boxes Stacked)
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("City", style = labelStyle)
                            CommonInput(
                                value = city,
                                onValueChange = { city = it },
                                placeholder = "e.g. Abu Dhabi"
                            )
                        }

                        // 3. Postal Code
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Postal code", style = labelStyle)
                            CommonInput(
                                value = postalCode,
                                onValueChange = { postalCode = it },
                                placeholder = "e.g.20004"
                            )
                        }

                        // 4. State (With trailing dropdown icon)
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("State", style = labelStyle)
                            CommonInput(
                                value = state,
                                onValueChange = { state = it },
                                placeholder = "Select",
                                trailingIcon = {
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Select State",
                                        tint = Color(0xFF030304)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}