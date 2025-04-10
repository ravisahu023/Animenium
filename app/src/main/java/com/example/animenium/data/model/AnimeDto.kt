package com.example.animenium.data.model

data class AnimeDto(
    val data: List<AnimeDtoItem>,
//    val links: Links,
//    val meta: Meta,
    val pagination: Pagination
)

data class AnimeDetailsDto(
    val data: AnimeDtoItem
)
