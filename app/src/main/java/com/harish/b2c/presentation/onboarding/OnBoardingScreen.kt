package com.harish.b2c.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.Spacing
import com.harish.b2c.ui.theme.boldTitle
import com.harish.b2c.ui.theme.regularBody

@Composable
fun OnboardingScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.extraLarge, vertical = Spacing.huge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(Spacing.extraExtraLarge))
        //      Surface(
//            shape = CircleShape,
//            color = White.copy(alpha = 0.9f),
//            modifier = Modifier.size(110.dp),
//            shadowElevation = 8.dp
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_logo),
//                contentDescription = "App Logo",
//                modifier = Modifier.padding(24.dp)
//            )
//        }

        Spacer(modifier = Modifier.height(Spacing.huge))

        Text(
            text = "Order Stock. Fast & Easy.",
            style = AppTypography.boldTitle,
            textAlign = TextAlign.Center,
            color = Color(0xFF1A1A1A)
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        Text(
            text = "Place bulk orders for daily essentials and get them delivered directly to your retail shop—on time, every time.",
            style = AppTypography.regularBody,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(Spacing.extraExtraLarge))

        Image(
            painter = painterResource(id = R.drawable.onboard_group),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            alpha = 0.6f
        )

        Spacer(modifier = Modifier.weight(1f))

        CommonButton(
            text = "Create an Account",
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onClick = { navController.navigate(NavigationScreen.Login.route) }
        )

        Spacer(modifier = Modifier.height(Spacing.mediumLarge))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
                style = AppTypography.regularBody,
                color = Color.DarkGray
            )
            Text(
                text = "Login",
                style = AppTypography.regularBody,
                color = Color(0xFFF13E50),
                modifier = Modifier.clickable { navController.navigate(NavigationScreen.Login.route) }
            )
        }
    }
}