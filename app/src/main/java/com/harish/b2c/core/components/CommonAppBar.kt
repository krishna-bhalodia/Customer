package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R

@Preview(
    name = "Common AppBar - All States",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonAppBarPreview() {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 1️⃣ Default
        CommonAppBar(
            title = "Login",
            onBackClick = {}
        )

        // 2️⃣ Long Title
        CommonAppBar(
            title = "Personal Information",
            onBackClick = {}
        )
    }
}

@Composable
fun CommonAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val textBlack = Color(0xFF030304)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp) // Standard AppBar height
            .padding(horizontal = 24.dp)
    ) {
        // Back Button
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = textBlack.copy(alpha = 0.08f), // rgba(3, 3, 4, 0.08)
                    shape = CircleShape
                )
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = textBlack,
                modifier = Modifier.size(20.dp)
            )
        }

        // Title
        Text(
            text = title,
            color = textBlack,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.signika_medium, FontWeight.Medium)), // font-weight: 500
            modifier = Modifier.align(Alignment.Center)
        )
    }
}