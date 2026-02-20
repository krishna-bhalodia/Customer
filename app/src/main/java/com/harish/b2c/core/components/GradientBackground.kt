package com.harish.b2c.core.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harish.b2c.ui.theme.GradientSoftPink
import com.harish.b2c.ui.theme.GradientSoftPurple


@Composable
@Preview(showBackground = true)
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val density = LocalDensity.current

        Canvas(modifier = Modifier.fillMaxSize()) {
            with(density) {
                // --- Ellipse 1 (Top-Left Pink Blob) ---
                val blob1Width = 582.dp.toPx()
                val blob1Radius = blob1Width / 2
                val blob1Blur = 184.143.dp.toPx()

                val blob1Left = -352.dp.toPx()
                val blob1Top = -163.dp.toPx()
                val center1 = Offset(
                    x = blob1Left + blob1Radius,
                    y = blob1Top + blob1Radius
                )

                val gradientRadius1 = blob1Radius + blob1Blur
                val color1 = GradientSoftPink.copy(alpha = 0.8f)

                drawCircle(
                    brush = Brush.radialGradient(
                        colorStops = arrayOf(
                            0.0f to color1,                                    // 100% at center
                            0.35f to color1.copy(alpha = color1.alpha * 0.9f), // 72% opacity
                            0.50f to color1.copy(alpha = color1.alpha * 0.7f), // 56% opacity
                            0.65f to color1.copy(alpha = color1.alpha * 0.45f),// 36% opacity
                            0.78f to color1.copy(alpha = color1.alpha * 0.25f),// 20% opacity
                            0.88f to color1.copy(alpha = color1.alpha * 0.12f),// 9.6% opacity
                            0.95f to color1.copy(alpha = color1.alpha * 0.05f),// 4% opacity
                            1.0f to Color.Transparent                          // 0% at edge
                        ),
                        center = center1,
                        radius = gradientRadius1
                    ),
                    center = center1,
                    radius = gradientRadius1
                )

                // --- Ellipse 2 (Bottom-Right Purple Blob) ---
                val blob2Width = 934.dp.toPx()
                val blob2Radius = blob2Width / 2
                val blob2Blur = 295.515.dp.toPx()

                val blob2RightOffset = 747.dp.toPx()
                val blob2Top = 419.dp.toPx()

                val center2X = size.width + blob2RightOffset - blob2Radius
                val center2Y = blob2Top + blob2Radius
                val center2 = Offset(x = center2X, y = center2Y)

                val gradientRadius2 = blob2Radius + blob2Blur
                val color2 = GradientSoftPurple.copy(alpha = 0.8f)

                drawCircle(
                    brush = Brush.radialGradient(
                        colorStops = arrayOf(
                            0.0f to color2,
                            0.35f to color2.copy(alpha = color2.alpha * 0.9f),
                            0.50f to color2.copy(alpha = color2.alpha * 0.7f),
                            0.65f to color2.copy(alpha = color2.alpha * 0.45f),
                            0.78f to color2.copy(alpha = color2.alpha * 0.25f),
                            0.88f to color2.copy(alpha = color2.alpha * 0.12f),
                            0.95f to color2.copy(alpha = color2.alpha * 0.05f),
                            1.0f to Color.Transparent
                        ),
                        center = center2,
                        radius = gradientRadius2
                    ),
                    center = center2,
                    radius = gradientRadius2
                )
            }
        }

        content()
    }
}