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
import com.harish.b2c.core.components.CommonPhoneNumberInput
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen

@Composable
fun LoginScreen(navController: NavController,isSignUp: Boolean = false ) {
    val textBlack = Color(0xFF030304)
    val textGrey = Color(0xFF7C858C)
    val brandRed = Color(0xFFEA0A2A)

    var phoneNumber by remember { mutableStateOf("") }
    val screenTitle = if (isSignUp) "Create an Account" else "Login"
    val bottomPrimaryText = if (isSignUp) "Already have an account?" else "Are you a new user?"

    val bottomActionText = if (isSignUp) "Login" else "Sign Up"

    // Wrap the entire screen in the custom gradient background
        Scaffold(
            // Set transparent so the gradient shows through
            containerColor = Color.Transparent,
            topBar = {
                CommonAppBar(
                    title = screenTitle,
                    onBackClick = { /* Handle Back Navigation */ },
                    modifier = Modifier.statusBarsPadding().padding(top = 20.dp)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp), // Screen-wide left/right padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Spacer matching the gap from AppBar to the text frame (~40dp based on Figma)
                Spacer(modifier = Modifier.height(40.dp))

                // --- Frame 5: Header Texts ---
                Text(
                    text = "Enter Your Mobile Number",
                    color = textBlack,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.signika_semibold, FontWeight.SemiBold)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp)) // gap: 8px

                Text(
                    text = "We just send you 5-digit code to verify your mobile number",
                    color = textGrey,
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp, // 140% of 16px
                    fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                // Spacer matching gap between text and inputs (gap: 30px)
                Spacer(modifier = Modifier.height(30.dp))

                // --- Frame 8: Input & Button ---
                CommonPhoneNumberInput(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it }
                )

                Spacer(modifier = Modifier.height(20.dp)) // gap: 20px

                CommonButton(
                    text = "Continue",
                    // Button is enabled only if they typed a 10-digit number
                    isEnabled = phoneNumber.length == 10,
                    onClick = {navController.navigate(if (isSignUp) NavigationScreen.OtpforSignUp.route else NavigationScreen.Otp.route)}
                )

                // --- Bottom Spacing ---
                // Using weight(1f) to push the bottom row down, or a fixed spacer to match the ~100px gap in Figma
                Spacer(modifier = Modifier.height(104.dp))

                // --- Frame 3: Sign Up Section ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = bottomPrimaryText,
                        color = textBlack,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal))
                    )

                    Spacer(modifier = Modifier.width(10.dp)) // gap: 10px

                    Text(
                        text = bottomActionText,
                        color = brandRed,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
                        modifier = Modifier.clickable { if (isSignUp) navController.navigate(NavigationScreen.Login.route) else navController.navigate(NavigationScreen.SignUp.route) }
                    )
                }
            }

    }
}