package com.example.animenium.presentation.anime_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animenium.data.model.AnimeDtoItem
import com.example.animenium.presentation.common_components.AnimeTopAppBar
import com.example.animenium.presentation.common_components.CoilImage

@Composable
fun AnimeListScreen(
    viewModel: AnimeListViewModel = hiltViewModel(), onAnimeClick: (Int) -> Unit
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        AnimeTopAppBar("Top Anime")
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Text("Error: ${state.error}", color = Color.Red)
            }

            else -> {
                LazyColumn {
                    items(state.animeList.size) { anime ->
                        AnimeListItem(
                            anime = state.animeList[anime],
                            onClick = { onAnimeClick(it) })
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeListItem(anime: AnimeDtoItem, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(anime.mal_id) },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            CoilImage(
                data = anime.images.jpg.image_url,
                contentDescription = anime.title,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = anime.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = "Episodes: ${anime.episodes ?: "N/A"}")
                Text(text = "Score: ${anime.score ?: "N/A"}")
            }
        }
    }
}

