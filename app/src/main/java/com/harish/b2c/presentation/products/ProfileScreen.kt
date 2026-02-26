package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.*
import com.harish.b2c.ui.theme.*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    GradientBackground {
        ProfileScreen(
            navController = NavController(LocalContext.current)
        )
    }
}

@Composable
fun ProfileScreen(
   navController: NavController
) {
    var name by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("johndoe@email.com") }
    val phone = "+123456789" // Representing the read-only state from Figma

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Personal Info",
                onBackClick = { navController.popBackStack() },
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
                    text = "Update",
                    onClick = {  },
                    isEnabled = false
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
            Spacer(modifier = Modifier.height(Spacing.extraLarge))

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
                        .size(Spacing.extraLarge)
                        .border(1.5.dp, White, CircleShape)
                        .background(BrandRed, CircleShape)
                        .padding(Spacing.extraSmall),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Edit Picture",
                        tint = White
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // White Bottom Sheet containing fields
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        White,
                        RoundedCornerShape(topStart = Spacing.large, topEnd = Spacing.large)
                    )
                    .padding(horizontal = Spacing.large, vertical = Spacing.large)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Spacing.mediumLarge),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CommonProfileInput(
                        label = "Name",
                        value = name,
                        onValueChange = { name = it },
                        leadingIcon = R.drawable.person
                    )

                    CommonProfileInput(
                        label = "Email",
                        value = email,
                        onValueChange = { email = it },
                        leadingIcon = R.drawable.email
                    )

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