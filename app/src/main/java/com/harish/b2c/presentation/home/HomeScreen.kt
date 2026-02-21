package com.harish.b2c.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.harish.b2c.ui.theme.appSpacing

@Composable
fun HomeScreen() {
    Scaffold(
        containerColor = Color.Transparent,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.appSpacing.medium)
        ) {
            Text(
                text = "Welcome"
            )
        }
    }
}