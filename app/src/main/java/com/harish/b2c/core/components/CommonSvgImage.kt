package com.harish.b2c.core.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun CommonSvgImage(
    assetName: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current

    // Optimize: Create the request only when assetName changes.
    // The ImageLoader is now handled globally by RihamApp.
    val model = remember(assetName) {
        ImageRequest.Builder(context)
            .data("file:///android_asset/$assetName")
            .build()
    }

    Image(
        painter = rememberAsyncImagePainter(model = model),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        // Apply tint if provided, otherwise leave null to show original colors
        colorFilter = tint?.let { ColorFilter.tint(it) }
    )
}