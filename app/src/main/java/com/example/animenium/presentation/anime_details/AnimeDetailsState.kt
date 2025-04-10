package com.example.animenium.presentation.anime_details

import com.example.animenium.data.model.AnimeDtoItem

data class AnimeDetailState(
    val isLoading: Boolean = false,
    val anime: AnimeDtoItem? = null,
    val error: String? = null
)
