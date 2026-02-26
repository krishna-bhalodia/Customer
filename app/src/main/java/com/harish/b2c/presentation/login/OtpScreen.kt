package com.harish.b2c.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonOtpInput
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun OtpScreen(
    navController: NavController,
    isSignUp: Boolean = false,
    phoneNumber: String = "+256 94*****53"
) {
    var otpValue by remember { mutableStateOf("") }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Verify Number",
                    onBackClick = { navController.navigateUp() },
                    modifier = Modifier.statusBarsPadding().padding(top = Spacing.mediumLarge)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = Spacing.large),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(Spacing.extraExtraLarge))

                Text(
                    text = "Enter Your 5-Digit Code",
                    color = TextBlack,
                    style = AppTypography.headlineLarge.copy(fontSize = 24.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Spacing.small))

                Text(
                    text = "We’ve send an SMS with an activation code to your phone $phoneNumber.",
                    color = TextGrey,
                    style = AppTypography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Spacing.extraLarge))

                CommonOtpInput(
                    otpText = otpValue,
                    onOtpTextChange = { otpValue = it },
                    otpCount = 5
                )

                Spacer(modifier = Modifier.height(Spacing.mediumLarge))

                CommonButton(
                    text = "Verify",
                    isEnabled = otpValue.length == 5,
                    onClick = {
                        if (isSignUp) navController.navigate(NavigationScreen.DeliveryAddress.route)
                        else navController.navigate(NavigationScreen.HomeScreen.route)
                    }
                )

                Spacer(modifier = Modifier.height(104.dp)) // Keeping specific structural spacing

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Send code again",
                        color = TextBlack,
                        style = AppTypography.bodyLarge,
                        modifier = Modifier.clickable { /* Handle Resend */ }
                    )

                    Spacer(modifier = Modifier.width(Spacing.mediumSmall))

                    Text(
                        text = "00:20",
                        color = TextGrey,
                        style = AppTypography.bodyLarge
                    )
                }
            }
        }
    }
}