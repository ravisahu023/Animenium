package com.example.animenium.data.repository

import com.example.animenium.data.model.AnimeDetailsDto
import com.example.animenium.data.model.AnimeDto

interface AnimeRepository {
    suspend fun getTopAnime(): AnimeDto

    suspend fun getAnimeDetails(id: Int): AnimeDetailsDto

}