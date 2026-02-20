package com.harish.b2c.ui.theme

import androidx.compose.material3.Typography
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

// 2. Map them to M3 Typography roles
val Typography = Typography(
    // Large Titles (Bold)
    headlineLarge = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    // Standard Screen Titles (SemiBold)
    titleLarge = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    // Subtitles or Section Headers (Medium)
    titleMedium = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    // Main Body Text (Regular)
    bodyLarge = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // Small captions or hints (Light)
    labelSmall = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp
    )
)