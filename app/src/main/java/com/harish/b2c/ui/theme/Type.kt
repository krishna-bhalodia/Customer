package com.harish.b2c.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.harish.b2c.R

val Signika = FontFamily(
    Font(R.font.signika_light, FontWeight.Light),
    Font(R.font.signika_regular, FontWeight.Normal),
    Font(R.font.signika_medium, FontWeight.Medium),
    Font(R.font.signika_semibold, FontWeight.SemiBold),
    Font(R.font.signika_bold, FontWeight.Bold)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp
    )
)

val Typography.boldTitle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = this.headlineLarge

val Typography.regularBody: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = this.bodyLarge

// Add a global accessor for Typography too
val AppTypography: Typography
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.typography
val Typography.mediumTitle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = this.titleMedium