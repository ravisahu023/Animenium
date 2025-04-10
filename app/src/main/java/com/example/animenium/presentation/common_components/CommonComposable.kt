package com.example.animenium.presentation.common_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.example.animenium.R
import com.example.animenium.data.model.AnimeDetailsDto
import com.example.animenium.data.model.AnimeDtoItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun TrailerOrPoster(anime: AnimeDtoItem) {

    if (anime.trailer.youtube_id.isNullOrEmpty()) {
        CoilImage(
            data = anime.images.jpg.image_url,
            contentDescription = anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    } else {
        //Youtube native player
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current

        AndroidView(
            factory = {
                YouTubePlayerView(context).apply {
                    lifecycleOwner.lifecycle.addObserver(this)

                    this.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(anime.trailer.youtube_id, 0f)
                        }
                    })
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
    }
}

@Composable
fun CoilImage(
    data: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = data,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AnimeTopAppBar(
    title: String = "Details",
    modifier: Modifier = Modifier,
    isBackIconVisible: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(title = { Text(title) }, navigationIcon = {
        if (isBackIconVisible) {
            Icon(Icons.Default.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.left),
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .size(36.dp)
                    .clickable { onBackClick() })
        }
    })
}
