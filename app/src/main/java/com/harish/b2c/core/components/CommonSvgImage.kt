package com.harish.b2c.core.components

import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun CommonSvgImage(
    @RawRes resId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current

    val model = remember(resId) {
        ImageRequest.Builder(context)
            .data(resId)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    }

    Image(
        painter = rememberAsyncImagePainter(model = model),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        colorFilter = tint?.let { ColorFilter.tint(it) }
    )
}