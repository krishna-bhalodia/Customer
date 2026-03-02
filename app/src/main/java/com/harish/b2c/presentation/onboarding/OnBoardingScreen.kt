package com.harish.b2c.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonSvgImage
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*
import android.graphics.Shader
import android.os.Build
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.draw.clip
import com.harish.b2c.core.utils.glassShadow

@Composable
fun OnboardingScreen(navController: NavController) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing.large) // Standard 24.dp width padding
                .navigationBarsPadding()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Push content down to match the 102px top margin in CSS
            Spacer(modifier = Modifier.height(58.dp))

            // Logo Container (Glassmorphic Effect from CSS)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    // Shadow (0px 8px 24px #EA0A2A1F)
                    .glassShadow(
                    color = Color(0xFFEA0A2A),
                    alpha = 0.12f,
                    shape = CircleShape,
                    shadowRadius = 24.dp,
                    offsetY = 8.dp
                )
                    
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.6f))
                    .border(1.dp, Color.White.copy(alpha = 0.8f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CommonSvgImage(
                    resId = R.raw.hariss_icon,
                    modifier = Modifier.size(width = 41.dp, height = 63.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp)) // Gap 30px

            // Title
            Text(
                text = "Order Stock. Fast & Easy.",
                style = AppTypography.headlineMedium.copy(
                    fontSize = 28.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(20.dp)) // Gap 20px

            // Subtitle
            Text(
                text = "Place bulk orders for daily essentials and get them delivered directly to your retail shop—on time, every time.",
                style = AppTypography.bodyLarge.copy(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
                color = TextGrey
            )

            // Center Graphic
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboard_group),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = 40.dp) // adjust slightly down if needed
                        .width(210.dp),
                    alpha = 0.4f
                )
            }

            // Bottom Button
            CommonButton(
                text = "Create an Account",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp), // Matched CSS 57px height
                onClick = { navController.navigate(NavigationScreen.SignUp.route) }
            )

            Spacer(modifier = Modifier.height(Spacing.large)) // 24px gap

            // Login Text
            Row(
                modifier = Modifier.padding(bottom = Spacing.extraLarge), // Bottom padding
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    style = AppTypography.bodyLarge.copy(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.2.sp
                    ),
                    color = TextBlack
                )
                Text(
                    text = "Login",
                    style = AppTypography.bodyLarge.copy(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.2.sp
                    ),
                    color = BrandRed,
                    modifier = Modifier.clickable { navController.navigate(NavigationScreen.Login.route) }
                )
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    GradientBackground {
    OnboardingScreen(navController = NavController(LocalContext.current))}
}