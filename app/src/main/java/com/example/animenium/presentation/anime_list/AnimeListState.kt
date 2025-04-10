package com.example.animenium.presentation.anime_list

import com.example.animenium.data.model.AnimeDtoItem

data class AnimeListState(
    val isLoading: Boolean = false,
    val animeList: List<AnimeDtoItem> = emptyList(),
    val error: String? = null
)