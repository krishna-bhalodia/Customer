package com.harish.b2c.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonPhoneNumberInput
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.TextGrey


@Composable
fun LoginScreen(navController: NavController, isSignUp: Boolean = false) {
    val spacing = Spacing // Using your custom Spacing accessor
    var phoneNumber by remember { mutableStateOf("") }

    val screenTitle = if (isSignUp) "Create an Account" else "Login"
    val bottomPrimaryText = if (isSignUp) "Already have an account?" else "Are you a new user?"
    val bottomActionText = if (isSignUp) "Login" else "Sign Up"

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = screenTitle,
                onBackClick = { navController.popBackStack() },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = spacing.mediumLarge) // Use theme spacing
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = spacing.large), // 24.dp from theme
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing.extraExtraLarge)) // 40.dp

            Text(
                text = "Enter Your Mobile Number",
                color = MaterialTheme.colorScheme.onBackground, // Dynamic color
                style = AppTypography.headlineLarge.copy(fontSize = 24.sp), // Signika Bold
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(spacing.small))

            Text(
                text = "We just send you 5-digit code to verify your mobile number",
                color = TextGrey, // From centralized colors
                style = AppTypography.bodyLarge, // Signika Regular
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(spacing.extraLarge))

            CommonPhoneNumberInput(
                value = phoneNumber,
                onValueChange = { phoneNumber = it }
            )

            Spacer(modifier = Modifier.height(spacing.mediumLarge))

            CommonButton(
                text = "Continue",
                isEnabled = phoneNumber.length == 10,
                onClick = {
                    navController.navigate(
                        if (isSignUp) NavigationScreen.OtpforSignUp.route
                        else NavigationScreen.Otp.route
                    )
                }
            )

            Spacer(modifier = Modifier.weight(1f)) // Push bottom row down

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = spacing.large),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bottomPrimaryText,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = AppTypography.bodyLarge
                )

                Spacer(modifier = Modifier.width(spacing.mediumSmall))

                Text(
                    text = bottomActionText,
                    color = BrandRed, // Brand color
                    style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.clickable {
                        if (isSignUp) navController.navigate(NavigationScreen.Login.route)
                        else navController.navigate(NavigationScreen.SignUp.route)
                    }
                )
            }
        }
    }
}