package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    GradientBackground {

    ProfileScreen(
        onBackClick = {},
        onUpdateClick = {}
    )}
}

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onUpdateClick: () -> Unit
) {
    var name by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("johndoe@email.com") }
    val phone = "+123456789" // Representing the read-only state from Figma

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Personal Info",
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
                        .imePadding()
                ) {
                    CommonButton(
                        text = "Update",
                        onClick = onUpdateClick,
                        isEnabled = false
                        // Custom greyed-out state matching the CSS #91989D
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                // Profile Avatar Container
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder Person Icon inside White Circle
                    Icon(
                        painter = painterResource(R.drawable.dark_man),
                        contentDescription = "Profile Picture",
                        tint = TextBlack.copy(alpha = 0.7f),
                        modifier = Modifier.size(52.dp)
                    )

                    // Floating Edit Button
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .border(1.5.dp, White, CircleShape)
                            .background(BrandRed, CircleShape)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Small Edit/Camera Vector
                        Icon(
                            painter = painterResource(id = R.drawable.camera), // Replace with your standard edit icon or camera XML
                            contentDescription = "Edit Picture",
                            tint = White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // White Bottom Sheet containing fields
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Name Field
                        CommonProfileInput(
                            label = "Name",
                            value = name,
                            onValueChange = { name = it },
                            leadingIcon = R.drawable.person
                        )

                        // Email Field
                        CommonProfileInput(
                            label = "Email",
                            value = email,
                            onValueChange = { email = it },
                            leadingIcon = R.drawable.email
                        )

                        // Phone Field (Read-only with "Change" action)
                        CommonProfileInput(
                            label = "Phone Number",
                            value = phone,
                            onValueChange = { },
                            leadingIcon = R.drawable.telephone,
                            isEditable = false,
                            trailingActionText = "Change",
                            onTrailingActionClick = { /* Handle Phone Change */ }
                        )

                        Spacer(modifier = Modifier.height(64.dp)) // Extra space at bottom before button
                    }
                }
            }
        }
    }
