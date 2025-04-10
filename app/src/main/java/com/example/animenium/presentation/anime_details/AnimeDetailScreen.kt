package com.example.animenium.presentation.anime_details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animenium.presentation.common_components.AnimeTopAppBar
import com.example.animenium.presentation.common_components.TrailerOrPoster

@Composable
fun AnimeDetailsScreen(
    animeDetailsID: Int,
    viewModel: AnimeDetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNavigationUp: () -> Unit
) {
    val state = viewModel.state
    BackHandler(onBack = { onNavigationUp() })

    Column(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        AnimeTopAppBar("Details", isBackIconVisible = true, onBackClick = { onNavigationUp() })
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Text("Error: ${state.error}", color = Color.Red)
            }

            state.anime != null -> {
                val anime = state.anime
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    item {
                        Text(text = anime.title, style = MaterialTheme.typography.headlineLarge)

                        Spacer(modifier = Modifier.height(8.dp))

                        TrailerOrPoster(anime = anime)

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Rating: ${anime.score ?: "N/A"}")
                        Text("Episodes: ${anime.episodes ?: "N/A"}")
                        Text("Genres: ${anime.genres.joinToString { it.name }}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = anime.synopsis ?: "No synopsis available")
                    }
                }
            }
        }
    }
}
