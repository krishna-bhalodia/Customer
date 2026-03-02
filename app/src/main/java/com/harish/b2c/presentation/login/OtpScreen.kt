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
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun OtpScreen(
    navController: NavController,
    isSignUp: Boolean = false,
    viewModel: LoginViewModel
) {
    // 1. Observe the country code along with the phone number
    val countryCode by viewModel.countryCode.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()

    var otpValue by remember { mutableStateOf("") }

    // Timer State
    val timeLeft by viewModel.timerValue.collectAsState()
    val isResendEnabled by viewModel.isResendEnabled.collectAsState()

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Verify Number",
                onBackClick = { navController.navigateUp() },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = Spacing.mediumLarge)
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

            // 2. Add the $countryCode here to display the full number
            Text(
                text = "We’ve send an SMS with an activation code to your phone $countryCode $phoneNumber.",
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
                    if (isSignUp) {
                        navController.navigate(NavigationScreen.DeliveryAddress.route)
                    } else {
                        navController.navigate(NavigationScreen.MainContainer.route) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(104.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Send code again",
                    // 3. Change color conditionally so it looks grey when disabled, and active when enabled
                    color = if (isResendEnabled) TextBlack else TextGrey,
                    style = AppTypography.bodyLarge,
                    modifier = Modifier.clickable(enabled = isResendEnabled) {
                        viewModel.startTimer()
                    }
                )

                Spacer(modifier = Modifier.width(Spacing.mediumSmall))

                Text(
                    text = "00:${timeLeft.toString().padStart(2, '0')}",
                    color = TextGrey,
                    style = AppTypography.bodyLarge
                )
            }
        }
    }
}