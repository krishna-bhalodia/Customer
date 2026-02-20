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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonOtpInput
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen

@Composable
fun OtpScreen(navController: NavController,
    phoneNumber: String = "+256 94*****53" // Pass this dynamically in a real app
) {
    val textBlack = Color(0xFF030304)
    val textGrey = Color(0xFF7C858C)

    var otpValue by remember { mutableStateOf("") }

    GradientBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = "Verify Number",
                    onBackClick = { navController.navigateUp() },
                    modifier = Modifier.statusBarsPadding().padding(top = 20.dp)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp), // Overall screen padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // --- Header Texts ---
                Text(
                    text = "Enter Your 5-Digit Code",
                    color = textBlack,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.signika_semibold, FontWeight.SemiBold)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "We’ve send an SMS with an activation code to your phone $phoneNumber.",
                    color = textGrey,
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                // --- OTP Input Row ---
                CommonOtpInput(
                    otpText = otpValue,
                    onOtpTextChange = { otpValue = it },
                    otpCount = 5
                )

                Spacer(modifier = Modifier.height(20.dp))

                // --- Verify Button ---
                CommonButton(
                    text = "Verify",
                    // Button becomes red/active only when all 5 digits are entered
                    isEnabled = otpValue.length == 5,
                    onClick = { navController.navigate(NavigationScreen.DeliveryAddress.route) }
                )

                Spacer(modifier = Modifier.height(104.dp))

                // --- Bottom Resend Section ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Send code again",
                        color = textBlack,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
                        modifier = Modifier.clickable { /* Handle Resend */ }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "00:20", // You would tie this to a timer state in your ViewModel
                        color = textGrey,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal))
                    )
                }
            }
        }
    }
}