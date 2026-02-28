package com.harish.b2c.presentation.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun OrderSuccessScreen(
    navController: NavController
) {
    // GradientBackground provides the red/purple decorative blurs from your CSS
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.large)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))


        Icon(
            painter = painterResource(id = R.drawable.success),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .padding(end = Spacing.large),
            tint = Color.Unspecified // Keeps the multi-color SVG look
        )


        Spacer(modifier = Modifier.height(40.dp)) // Keeping 40.dp as it's a specific large gap

        // --- Success Text ---
        Text(
            text = "Your Order has been\naccepted",
            style = AppTypography.headlineMedium.copy(
                fontSize = 28.sp,
                lineHeight = 34.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            ),
            color = Gray900 // Using theme color
        )

        Spacer(modifier = Modifier.height(Spacing.mediumSmall))

        Text(
            text = "Your items has been placed and is on\nit’s way to being processed",
            style = AppTypography.bodyMedium.copy(
                color = TextGrey, // Using theme color
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        // --- Loyalty Points Card (Styled with Theme Colors) ---
        Row(
            modifier = Modifier
                .wrapContentSize()
                .background(LoyaltyBg, Shapes.large) // Using custom theme shape
                .border(2.dp, LoyaltyBorder, Shapes.large) // Using custom theme shape
                .padding(horizontal = Spacing.medium, vertical = Spacing.mediumSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(Spacing.small))
            Text(
                text = "16 loyalty points earned on this order",
                style = AppTypography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = DarkGrey // Using theme color
                )
            )
        }

        Spacer(modifier = Modifier.weight(1.2f))

        // --- Action Buttons ---
        CommonButton(
            text = "Download Invoice",
            onClick = {  },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.invoice),
                    contentDescription = null,
                    tint = White // Using theme color
                )
            }
        )

        TextButton(
            onClick = { navController.navigate(NavigationScreen.MainContainer.route)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spacing.small)
        ) {
            Text(
                text = "Back to Home",
                style = AppTypography.bodyLarge.copy(
                    color = TextBlack, // Using theme color
                    fontWeight = FontWeight.Normal
                )
            )
        }

        Spacer(modifier = Modifier.height(Spacing.large))
    }
}


@Preview(showBackground = true)
@Composable
fun OrderSuccessScreenPreview() {
    GradientBackground { // Using your project theme for fonts/colors
        OrderSuccessScreen(
            navController = NavController(LocalContext.current)
        )
    }
}