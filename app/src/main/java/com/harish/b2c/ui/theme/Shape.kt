package com.harish.b2c.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

val MaterialTheme.appShapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = AppShapes

// Global accessor for direct use in UI
val Shapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.appShapes