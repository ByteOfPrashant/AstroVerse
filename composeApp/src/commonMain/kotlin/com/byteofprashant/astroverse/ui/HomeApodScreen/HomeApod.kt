package com.byteofprashant.astroverse.ui.HomeApodScreen

import UiState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import astroverse.composeapp.generated.resources.Res
import astroverse.composeapp.generated.resources.apod_title
import astroverse.composeapp.generated.resources.no_explanation_available
import astroverse.composeapp.generated.resources.open_in_browser
import astroverse.composeapp.generated.resources.video_content
import com.byteofprashant.astroverse.AsyncImage
import com.byteofprashant.domain.model.Apod
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeApod() {
    val viewModel: ApodViewModel = koinViewModel()
    val uiState by viewModel.apodUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getApod()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                ApodContent(state.data)
            }

            is UiState.Error -> {
                ErrorContent(state.message)
            }
        }
    }
}

@Composable
fun ApodContent(apod: Apod) {
    // Get safe area insets
    val safeAreaInsets = WindowInsets.safeDrawing.asPaddingValues()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = safeAreaInsets.calculateTopPadding() + 16.dp,
                bottom = safeAreaInsets.calculateBottomPadding() + 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = apod.title ?: stringResource(Res.string.apod_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Date
        apod.date?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Image Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            when (apod.media_type) {
                "image" -> {
                    AsyncImage(
                        url = apod.url ?: "",
                        contentDescription = stringResource(Res.string.apod_title),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f)
                    )
                }

                "video" -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        contentAlignment = Alignment.Center
                    ) {
                        // Video placeholder - in a real app, this would be a video player
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "▶️",
                                style = MaterialTheme.typography.displayMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(Res.string.video_content),
                                style = MaterialTheme.typography.labelLarge
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = {}) {
                                Text(stringResource(Res.string.open_in_browser))
                            }
                        }
                    }
                }

                else -> {
                    // Default case - unknown media type
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Unsupported media type: ${apod.media_type}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Explanation
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = apod.explanation ?: stringResource(Res.string.no_explanation_available),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun ErrorContent(message: String) {
    // Get safe area insets
    val safeAreaInsets = WindowInsets.safeDrawing.asPaddingValues()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = safeAreaInsets.calculateTopPadding() + 16.dp,
                bottom = safeAreaInsets.calculateBottomPadding() + 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}