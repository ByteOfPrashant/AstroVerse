package com.byteofprashant.astroverse

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

/**
 * Loads an image asynchronously from a URL.
 * Each platform (Android, iOS) has its own implementation.
 * Shows a loading indicator while the image is loading.
 */
@Composable
expect fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
)
